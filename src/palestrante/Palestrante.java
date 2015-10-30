package palestrante;


import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

import palestrante.TratamentoDados;

public class Palestrante {

	private String nome;
	private LinkedList<LocalDateTime[]> disponibilidade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LinkedList<LocalDateTime[]> getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(LinkedList<LocalDateTime[]> disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	
	
	private static Scanner scan;
	
	public static LinkedHashMap<String,Palestrante> lePalestrantes(String arq) {
		
		LinkedHashMap<String,Palestrante> palestrantes = new LinkedHashMap<String,Palestrante>();
		try {
	    	scan = new Scanner(new File(arq));
	    	int numeroLinha = 0;
	    	
	        while(scan.hasNextLine()) {
	        	String linha = scan.nextLine();
	        	numeroLinha ++;
	        	if(linha.startsWith("Nome: ")) {
	        		Palestrante novoPalestrante = new Palestrante();
	        		novoPalestrante.setNome(TratamentoDados.nomePalestrante(linha));
	        		
		            if(scan.hasNextLine()) {
		            	linha = scan.nextLine();
		            	numeroLinha ++;
			            if(linha.startsWith("Disponibilidade: ")) {
			            	novoPalestrante.setDisponibilidade(TratamentoDados.disponibilidadePalestrante(linha));
			            	palestrantes.put(novoPalestrante.getNome(), novoPalestrante);
			            } else {
			            	throw new IllegalArgumentException("Esperado \"Disponibilidade: \" na linha " + numeroLinha + " do arquivo " + arq);
			            }
		            } else {
		            	throw new IllegalArgumentException("Último Palestrante não possui Disponibilidade");
		            }
	        	} else {
	            	throw new IllegalArgumentException("Esperado \"Nome: \" na linha " + numeroLinha + " do arquivo " + arq);
	            }
	        }
	        scan.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }
		
		return palestrantes;
	}
	
}