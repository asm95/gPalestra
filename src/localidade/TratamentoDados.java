package localidade;

import java.time.LocalTime;

public class TratamentoDados {
	
	public static String ajustaEndereço (String linha){
		linha = linha.replace("Endereço: ", "");
		
		return linha;
	}
	
	public static LocalTime ajustaHora (String linha){
		linha = linha.replace("Hora: ","");
		
		String[] data = linha.split(":");
		int horaInicio = Integer.valueOf(data[0].trim());
    	int minutoInicio = Integer.valueOf(data[1].trim());
    	
    	LocalTime hora = LocalTime.of(horaInicio, minutoInicio);
    	
    	return hora;
	}
	
	public static Responsavel ajustaResponsável (String linha){
		String[] 	info;
		int[] 		numero;
		
		
		info = linha.replace("Responsável: ", "").split(";");
		
		if (info.length != 3)
			return null;
		
		numero = string_to_phoneNumber(info[1]);
		
		if (numero == null)
			return null;
		
		
		Responsavel resp = new Responsavel();
		
		resp.setNome(info[0]);
		resp.setNumero(numero);
		resp.setEmail(info[2]);
		
		return resp;
	}
	
	
	/**
	 * Este recebe uma string e tenta transformar para número.
	 * Se esta estiver entre 10 a 11 digitos é valido.
	 * @param str String contento o número
	 * @return int [] Array de inteiro com resultado
	 */
	private static int[] string_to_phoneNumber(String str){
		int[] number = new int[11];
		int i = 0;
		
		for (char c: str.toCharArray()){
			
			if ( '0' <= c && c <= '9' ){
				number[i] = c-'0';
				i++;
			}
		}
		
		return (10 <= i && i <= 11) ? number : null;
	}
}
