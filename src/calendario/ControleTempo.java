package calendario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import calendario.controleData;
import palestra.Palestra;
import palestrante.Palestrante;
import localidade.Localidade;

/**
 * Este módulo tem por seu objetivo marcar os eventos no calendário
 * conforme as cirunstâncias. Se há disponibilidade de um palestrante
 * para uma palestra, o evento está alocado. Se há local para a palestra
 * neste horário, o evento está confirmado.
 * @author 	Marcos, Cristiano
 * @version 0.1
 * @since	2015-09-19
 */
public class ControleTempo {
	// altera as cores do terminal (funciona perfeitamente em unix)
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	/**
	 * <h1> Organiza Palestras </h1>
	 * Recebe lista com localidades e palestrantes, tenta fazer o casamento dos dois de acordo com os critérios da palestra
	 * Ex: Palestra1 precisa do Palestrante1 na Localidade1
	 * Retorna um calendário com os eventos marcados com sucesso
	 * @param LinkedList<Palestra>
	 * @return Calendario
	 */
	public static Calendario organizaPalestras(LinkedList<Palestra> palestras, LinkedList<Localidade> localidades) {
		Calendario calendario = new Calendario();
		calendario.setMeses(new LinkedHashMap<String,AnoMesCalendario>());
		
		
		for(Palestra palestra:palestras) {
			System.out.println("A encontrar disponibilidade de horário de palestrate e local para palestra " + palestra.getNome() + " com duração de " + palestra.getDuracaoMinutos() + " minutos(s)...");
			
			boolean marcado = false;
			Iterator<LocalDateTime[]> it = palestra.getPalestrante().getDisponibilidade().iterator();
			
			
			while (!marcado && it.hasNext()) {
				LocalDateTime[] dataHora = it.next();
				
				if(java.time.Duration.between(dataHora[0], dataHora[1]).toMinutes() >= palestra.getDuracaoMinutos()) {
					// std_war: disponibility of object:'Palestrante' can be used twice
					// std_fix: remove disponibility object from LinkedList<LocalDateTime[]> (auto_fix) @ 2015-10-02
					System.out.println("Encontrado palestrante com disponibilidade no dia " + controleData.localdateTime_to_weekString(dataHora[0]));
					it.remove();
					
					
					// procure por um local
					Localidade local = palestra.getLocal();
					Palestrante palestrante = palestra.getPalestrante();
					String nome_palestrante = palestrante.getNome();
					
					Iterator<LocalTime[]> itDispLoc = local.getDisponibilidade().iterator();
					Iterator<Integer> itDispLocDia = local.getDias().iterator();
					LinkedHashMap<String,Palestrante> pilha_palestrante = local.getPilha();
					
					while ( !marcado && itDispLoc.hasNext() && itDispLocDia.hasNext() ){
						LocalTime[] dataHoraLoc = itDispLoc.next();
						int dataDiaLoc = itDispLocDia.next();
						
						if ( !pilha_palestrante.containsKey(nome_palestrante) ){ // rodizio
							
							if ( dataHora[0].getDayOfWeek().getValue() == dataDiaLoc ){
								
								if ( java.time.Duration.between(dataHoraLoc[0], dataHoraLoc[1]).toMinutes() >= palestra.getDuracaoMinutos() ){
								
									itDispLoc.remove();
									itDispLocDia.remove();
									pilha_palestrante.put(nome_palestrante, palestrante);
								
									System.out.println(ANSI_RED + "Marcada palestra " + palestra.getNome() + " em " + controleData.localdateTime_to_weekString(dataHora[0]) +ANSI_RESET);
									marcado = true;
								}
							}
							
							if (!marcado)
								System.out.println("Disponibilidade do local " + local.getEndereço() + " (" + Localidade.printDisponibilidade(dataDiaLoc, dataHoraLoc) + ") não se encaica com o dia da palestra.");
						}
						else
							System.out.println(nome_palestrante + " não pode dar uma palestra no local mais de uma vez (rodízio)");
					
					
					// se encontrou, adicione ao calendário
					if (marcado){
						String anoMes = String.format("%04d", dataHora[0].getYear()) + String.format("%02d", dataHora[0].getMonthValue());
						AnoMesCalendario anoMesCal = calendario.getMeses().get(anoMes);
						if(anoMesCal == null) {
							anoMesCal = new AnoMesCalendario();
							anoMesCal.setAno(dataHora[0].getYear());
							anoMesCal.setMes(dataHora[0].getMonthValue());
							anoMesCal.setDias(new LinkedHashMap<String,DiaCalendario>());
							calendario.getMeses().put(anoMes, anoMesCal);
						}
						
						String dia = String.format("%02d", dataHora[0].getDayOfMonth());
						DiaCalendario diaCal = anoMesCal.getDias().get(dia);
						if(diaCal == null) {
							diaCal = new DiaCalendario();
							diaCal.setDia(dataHora[0].getDayOfMonth());
							diaCal.setHoras(new LinkedHashMap<LocalDateTime,HoraCalendario>());
							anoMesCal.getDias().put(dia, diaCal);
						}
						
						HoraCalendario hora = diaCal.getHoras().get(dataHora[0]);
						if(hora == null) {
							
							hora = new HoraCalendario();
							hora.setDataHoraInicio(dataHora[0]);
							hora.setDataHoraFim(dataHora[1]);
							hora.setPalestrante(palestrante);
							hora.setPalestra(palestra);
							diaCal.getHoras().put(dataHora[0], hora);
							
							
							}
						}
					}
				}
			}
			
			if(!marcado){
				throw new IllegalArgumentException("Não foi possível marcar a palestra " + palestra.getNome() + " por indisponibilidade de horário");
			}
		}

		return calendario;
	}
}
