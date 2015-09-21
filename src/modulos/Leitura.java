package modulos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Leitura {

	private static Scanner scan;

	public static enum diasDaSemana {
		Dom, Seg, Ter, Qua, Qui, Sex, Sab
	}
	
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
	
	public static LinkedList<Palestra> lePalestras (String arq, LinkedHashMap<String,Palestrante> palestrantes) {
		LinkedList<Palestra> palestras = new LinkedList<Palestra>();
		try {
	    	scan = new Scanner(new File(arq));
	    	int numeroLinha = 0;
	    	
	        while(scan.hasNextLine()) {
	        	String linha = scan.nextLine();
	        	numeroLinha ++;
	        	if(linha.startsWith("Nome: ")) {
	        		Palestra novaPalestra = new Palestra();
	        		novaPalestra.setNome(TratamentoDados.nomePalestra(linha));
	        		
		            if(scan.hasNextLine()) {
		            	linha = scan.nextLine();
		            	numeroLinha ++;
			            if(linha.startsWith("Palestrante: ")) {
			            	novaPalestra.setPalestrante(TratamentoDados.palestrantePalestra(linha, palestrantes));
			            	if(novaPalestra.getPalestrante() == null) {
			            		throw new IllegalArgumentException("Palestrante não identificado na linha " + numeroLinha + " do arquivo " + arq);
			            	}
			            	
			            	if(scan.hasNextLine()) {
			            		linha = scan.nextLine();
				            	numeroLinha ++;
				            	if(linha.startsWith("Tema: ")) {
				            		novaPalestra.setTema(TratamentoDados.temaPalestra(linha));
				            		
				            		if(scan.hasNextLine()) {
				            			linha = scan.nextLine();
						            	numeroLinha ++;
						            	if(linha.startsWith("Local: ")) {
						            		novaPalestra.setLocal(TratamentoDados.localPalestra(linha));
						            		
						            		if(scan.hasNextLine()) {
						            			linha = scan.nextLine();
								            	numeroLinha ++;
								            	if(linha.startsWith("Duracao: ")) {
								            		novaPalestra.setDuracaoMinutos(TratamentoDados.duracaoMinutosPalestra(linha));
								            		palestras.add(novaPalestra);
								            	} else {
									            	throw new IllegalArgumentException("Esperado \"Duracao: \" na linha " + numeroLinha + " do arquivo " + arq);
									            }
						            		} else {
								            	throw new IllegalArgumentException("Última Palestra não possui Duracao");
								            }
						            	} else {
							            	throw new IllegalArgumentException("Esperado \"Local: \" na linha " + numeroLinha + " do arquivo " + arq);
							            }
				            		} else {
						            	throw new IllegalArgumentException("Última Palestra não possui Local e Duracao");
						            }
				            	} else {
					            	throw new IllegalArgumentException("Esperado \"Tema: \" na linha " + numeroLinha + " do arquivo " + arq);
					            }
			            	} else {
				            	throw new IllegalArgumentException("Última Palestra não possui Tema, Local e Duracao");
				            }
			            } else {
			            	throw new IllegalArgumentException("Esperado \"Palestrante: \" na linha " + numeroLinha + " do arquivo " + arq);
			            }
		            } else {
		            	throw new IllegalArgumentException("Última Palestra não possui Palestrante, Tema, Local e Duracao");
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
		return palestras;
	}
}
