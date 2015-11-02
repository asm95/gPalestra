package palestra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

import palestra.TratamentoDados;
import palestrante.Palestrante;
import localidade.Localidade;

public class Palestra {
	private String nome;
	private Palestrante palestrante;
	private String tema;
	private Localidade local;
	private long duracaoMinutos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Palestrante getPalestrante() {
		return palestrante;
	}
	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public Localidade getLocal() {
		return local;
	}
	public void setLocal(Localidade local) {
		this.local = local;
	}
	public long getDuracaoMinutos() {
		return duracaoMinutos;
	}
	public void setDuracaoMinutos(long duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}
	
	
	
	private static Scanner scan;

	public static enum diasDaSemana {
		Dom, Seg, Ter, Qua, Qui, Sex, Sab
	}
	
	public static LinkedList<Palestra> lePalestras (String arq, LinkedHashMap<String,Palestrante> palestrantes, LinkedList<Localidade>localidades) {
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
						            		novaPalestra.setLocal(TratamentoDados.localPalestra(linha, localidades));
						            		if (novaPalestra.getLocal() == null){
						            			throw new IllegalArgumentException("Localidade não identificada na linha " + numeroLinha + " do arquivo " + arq);
						            		}
						            		
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
