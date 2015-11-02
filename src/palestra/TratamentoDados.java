package palestra;

import java.util.HashMap;
import java.util.LinkedList;

import palestrante.Palestrante;
import localidade.Localidade;

public class TratamentoDados {
	public static String nomePalestra(String linha) {
		System.out.println(linha.replace("Nome: ","").replace(".",""));
		return linha.replace("Nome: ","").replace(".","");
	}
	
	public static Palestrante palestrantePalestra(String linha, HashMap<String,Palestrante> palestrantes) {
		System.out.println(linha.replace("Palestrante: ","").replace(".",""));
		return palestrantes.get(linha.replace("Palestrante: ","").replace(".",""));
	}
	
	public static String temaPalestra(String linha) {
		System.out.println(linha.replace("Tema: ","").replace(".",""));
		return linha.replace("Tema: ","").replace(".","");
	}
	
	public static Localidade localPalestra(String linha, LinkedList<Localidade> localidades) {
		linha = linha.replace("Local: ","").replace(".","");
		
		for(Localidade loc:localidades){
			
			if (loc.getEndereço().equals(linha))
				return loc;
		}
		
		return null;
	}
	
	public static long duracaoMinutosPalestra(String linha) {
		String[] horaMin = linha.replace("Duracao: ","").replace("h.","").split(":");
		long hora = Integer.valueOf(horaMin[0].trim()) * 60;
		long min = Integer.valueOf(horaMin[1].trim());
		System.out.println(String.valueOf(hora + min));
		return hora + min;
	}
}
