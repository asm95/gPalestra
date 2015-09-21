package modulos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class TratamentoDados {

	public static String nomePalestrante(String linha) {
		System.out.println(linha.replace("Nome: ","").replace(".",""));
		return linha.replace("Nome: ","").replace(".","");
	}
	
	public static LinkedList<LocalDateTime[]> disponibilidadePalestrante(String linha) {
		LinkedList<LocalDateTime[]> disponibilidade = new LinkedList<LocalDateTime[]>();
		String[] disponibilidades = linha.replace("Disponibilidade: ","").replace(".","").split(";");
        for(String d:disponibilidades) {
        	String[] camposDisp = d.split(",");
        	String[] data = camposDisp[1].split("/");
        	int dia = Integer.valueOf(data[0].trim());
        	int mes = Integer.valueOf(data[1].trim());
        	int ano = Integer.valueOf(data[2].trim());
        	String[] horas = camposDisp[2].split("-");
        	String[] inicio = horas[0].split(":");
        	int horaInicio = Integer.valueOf(inicio[0].trim());
        	int minutoInicio = Integer.valueOf(inicio[1].trim());
        	String[] fim = horas[1].split(":");
        	int horaFim = Integer.valueOf(fim[0].trim());
        	int minutoFim = Integer.valueOf(fim[1].trim());
        	LocalDateTime[] disp = new LocalDateTime[2];
        	disp[0] = LocalDateTime.of(ano,mes,dia,horaInicio,minutoInicio);
        	System.out.println(disp[0].toString());
        	disp[1] = LocalDateTime.of(ano,mes,dia,horaFim,minutoFim);
        	System.out.println(disp[1].toString());
        	disponibilidade.add(disp);
        }
		return disponibilidade;
	}
	
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
	
	public static String localPalestra(String linha) {
		System.out.println(linha.replace("Local: ","").replace(".",""));
		return linha.replace("Local: ","").replace(".","");
	}
	
	public static long duracaoMinutosPalestra(String linha) {
		String[] horaMin = linha.replace("Duracao: ","").replace("h.","").split(":");
		long hora = Integer.valueOf(horaMin[0].trim()) * 60;
		long min = Integer.valueOf(horaMin[1].trim());
		System.out.println(String.valueOf(hora + min));
		return hora + min;
	}
}
