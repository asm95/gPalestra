package modulos;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import palestra.Palestra;

public class ControleTempo {

	public static Calendario organizaPalestras(LinkedList<Palestra> palestras) {
		Calendario calendario = new Calendario();
		calendario.setMeses(new LinkedHashMap<String,AnoMesCalendario>());
		
		for(Palestra palestra:palestras) {
			boolean marcado = false;
			Iterator<LocalDateTime[]> it = palestra.getPalestrante().getDisponibilidade().iterator();
			while (!marcado && it.hasNext()) {
				LocalDateTime[] dataHora = it.next();
				if(java.time.Duration.between(dataHora[0], dataHora[1]).toMinutes() >= palestra.getDuracaoMinutos()) {
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
						hora.setPalestrante(palestra.getPalestrante());
						hora.setPalestra(palestra);
						diaCal.getHoras().put(dataHora[0], hora);
						marcado = true;
						System.out.println("Marcou a palestra " + palestra.getNome() + " em " + dataHora[0].toString());
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
