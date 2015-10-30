package localidade;

import java.time.LocalTime;

public class TratamentoDados {
	public static LocalTime ajustaHora (String linha){
		linha = linha.replace("Horario: ","").replace(".","");
		
		String[] data = linha.split(":");
		int horaInicio = Integer.valueOf(data[0].trim());
    	int minutoInicio = Integer.valueOf(data[1].trim());
    	
    	LocalTime hora = LocalTime.of(horaInicio, minutoInicio);
    	
    	return hora;
	}
	
	public static String ajustaEndereço (String linha){
		linha = linha.replace("Endereço: ", "").replace(".", "");
		
		return linha;
	}
}
