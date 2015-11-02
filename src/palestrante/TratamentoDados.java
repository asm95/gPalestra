package palestrante;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import calendario.controleData;

public class TratamentoDados {
	
	public static String nomePalestrante(String linha) {
		return linha.replace("Nome: ","").replace(".","");
	}
	
	/**
	 * Recebe uma string S que precisa estar no formato: XX/XX/XXXX HH:mm - HH:mm; ... (n);
	 * Retorna uma lista do tipo LocalDateTime de tamanho m, sendo este o número de datas no formato correto.
	 * @param linha
	 * @return LinkedList<LocalTime> t
	 */
	public static LinkedList<LocalDateTime[]> ajustaDisponibilidade(String linha) {
		LinkedList<LocalDateTime[]> disponibilidade = new LinkedList<LocalDateTime[]>();
		
		String[] disponibilidades = linha.replace("Disponibilidade: ","").replace(".","").split(";");
		
        for(String d:disponibilidades) {
        	String[] camposDisp = d.split(",");
        	
        	String[] data = camposDisp[1].split("/");
        	int dia = Integer.valueOf(data[0].trim());
        	int mes = Integer.valueOf(data[1].trim());
        	int ano = Integer.valueOf(data[2].trim());
        	
        	if ( controleData.isValidDay(dia) && controleData.isValidMonth(mes) && controleData.isValidYear(ano) ){
        		
        		String[] duração = camposDisp[2].split("-");
        		
        		if ( duração.length == 2 ){
        			LocalTime[] instante = new LocalTime[2];
        			
        			instante[0] = controleData.string_to_localTime(duração[0]);
        			instante[1] = controleData.string_to_localTime(duração[1]);
        			
        			if ( instante[0] != null && instante[1] != null ){
        				
        				if ( controleData.getDurationBetween(instante[1], instante[0]) > 0 ){
        					
        					LocalDateTime[] disp = new LocalDateTime[2];
        					
        					disp[0] = LocalDateTime.of(ano,mes,dia,instante[0].getHour(),instante[0].getMinute());
        					disp[1] = LocalDateTime.of(ano,mes,dia,instante[1].getHour(),instante[1].getMinute());
        					
        					disponibilidade.add(disp);
        				}
        			}
        		}
        	}
        }
        
		return disponibilidade;
	}
}
