package calendario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Módulo que contém uma livraria de funções para conversão e verificação de datas.
 * As datas, em quaisquer formatos (String, LocalDateTime), precisam conversar.
 * Esta livraria irá não só permitir essa comunicação (conversão de um tipo de dado para o outro)
 * como também confirmar se estas datas estão no formato correto, a fim de não prejudicar
 * o funcionamento do programa.
 * @author 	Cristiano
 * @since 	0.2
 *
 */
public class controleData {
	
	private static HashMap<String, Integer> diasSemana;
	static {
		diasSemana = new HashMap<String,Integer>();
        
		diasSemana.put("Seg",1);
		diasSemana.put("Ter",2);
		diasSemana.put("Qua",3);
		diasSemana.put("Qui",4);
		diasSemana.put("Sex",5);
		diasSemana.put("Sab",6);
		diasSemana.put("Dom",7);
	}
	
	
	
	public static LocalTime string_to_localTime(String str){
		String[] instant;
		
		instant = str.split(":");
		
		if (instant.length != 2)
			return null;
		
		int houer = Integer.valueOf(instant[0].trim());
		int minute = Integer.valueOf(instant[1].trim());
		
		if ( !isValid24_time(houer, minute) )
			return null;
		
		return LocalTime.of(houer, minute);
	}
	
	public static String localdateTime_to_weekString(LocalDateTime loc){
		return getDayFromInt(loc.getDayOfWeek().getValue()) + ", " + loc.toLocalTime().toString();
	}
	
	
	public static boolean isValid24_time(int houer, int minute){
		if (0 <= houer && houer <= 24)
			if (0 <= minute && minute <= 59)
				return true;
	
		return false;	
	}
	
	public static boolean isValidYear (int y){
		return (y>0);
	}
	
	public static boolean isValidMonth (int m){
		return (1 <= m && m <= 12);
	}
	
	public static boolean isValidDay (int d){
		return (1 <= d && d <= 31);
	}
	
	public static int getDayFromString(String day)
    {
        return diasSemana.get(day).intValue();
    }
	
	public static String getDayFromInt(Integer day){
		for ( Entry<String, Integer> entry : diasSemana.entrySet()) {
		    String key = entry.getKey();
		    int id = entry.getValue();
		   
		    if (id == day)
		    	return key;
		}
		
		return null;
	}
	
	public static int getDurationBetween(LocalTime a, LocalTime b){
		int minute_a, minute_b;
		
		minute_a = a.getHour()*60 + a.getMinute();
		minute_b = b.getHour()*60 + b.getMinute();
		
		return minute_a - minute_b;
	}
}
