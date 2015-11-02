package localidade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

import calendario.controleData;

import java.time.LocalTime;

import palestrante.Palestrante;


/**
 * <h1> Localidade <h1>
 * * Gerencia leitura de arquivos contendo localidades
 * * Faz o tratamento de dados destes dados lidos
 * * Prepara os dados armazenados para persistência
 * @author	Cristiano
 * @version 0.1
 * @since	2015-10-30
 */
public class Localidade {
	private String 		endereço;
	private LinkedList<LocalTime[]> disponibilidade;
	private LinkedList<Integer> dias;
	private Responsavel responsável;
	private LinkedHashMap<String,Palestrante> pilha;
	
	private static Scanner scan;
	
	public Localidade(){
		endereço = null;
		disponibilidade = null;
		responsável = null;
		pilha = new LinkedHashMap<String,Palestrante>();
		dias = new LinkedList<Integer>();
	}
	
	
	
	public void setEndereço(String Endereço) {
		this.endereço = Endereço;
	}
	
	public String getEndereço(){
		return endereço;
	}
	
	public LinkedList<LocalTime[]> getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(LinkedList<LocalTime[]> disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	public Responsavel getResonsável (){
		return responsável;
	}
	
	public void setResponsável (Responsavel res){
		this.responsável = res;
	}
	
	public LinkedHashMap<String,Palestrante> getPilha (){
		return pilha;
	}
	
	public void setPilha (LinkedHashMap<String,Palestrante> pilha){
		this.pilha = pilha;
	}
	
	public LinkedList<Integer> getDias (){
		return dias;
	}
	
	public void setDias(LinkedList<Integer> dias){
		this.dias = dias;
	}
	
	
	/**
	 * Recebe uma das disponibilidades da localidade e retorna uma string em formato mais legível
	 * @param dia
	 * @param hora
	 * @return String
	 */
	public static String printDisponibilidade (int dia, LocalTime[] hora){
		
		if ( controleData.isValidDay(dia) ){
			return controleData.getDayFromInt(dia) + ", " + hora[0].toString() + "-" + hora[1].toString();
		}
		
		return "(data inválida)";
	}
	
	/**
	 * <h2> leLocalidades <h2>
	 * * Lê as localidades de um arquivo
	 * * Confere se cada localidade possui todas as informações obrigatórias
	 * * Confere se essas informações não possui incosistências
	 * @param String arq : localização do arquivo
	 * @return LinkedHashMap<String,Localidade> : lista contendo localidades lidas com sucesso
	 */
	public static LinkedList<Localidade> leLocalidades(String arq) {
		
		LinkedList<Localidade> localidades = new LinkedList<Localidade>();
		
		try {
	    	scan = new Scanner(new File(arq));
	    	
	    	int numeroLinha = 0;
	    	int numeroLocalidades = 0;
	    	
	    	Localidade novaLocalidade = null;
	    	
	    	
	        while(scan.hasNextLine()) {
	        	String linha = scan.nextLine();
	        	numeroLinha++;
	        	
	        	if ( linha.startsWith("Endereço: ") ){
	        		
	        		// Leia informação básica e crie nova localidade
	        		linha = TratamentoDados.ajustaEndereço(linha);
	        		
	        		if ( linha == null ){
	        			throw new IllegalArgumentException("Formato inválido para localidade: " + novaLocalidade.endereço);
	        		}
	        		
	        		novaLocalidade = new Localidade();
	        		
	        		novaLocalidade.setEndereço(linha);
	        	}
	    		
	    		else if ( linha.startsWith("Disponibilidade: ") ){
	        		LinkedList<LocalTime[]> disp = TratamentoDados.ajustaDisponibilidade(linha, novaLocalidade.dias);
	        		
	        		if (disp == null){
	        			throw new IllegalArgumentException("Formato inválido para Hora. Linha: " + numeroLinha + ". Arquivo: " + arq);
	        		}
	        		
	        		novaLocalidade.disponibilidade = disp;
	        	}
	    		
	    		else if ( linha.startsWith("Responsável: ") ){
	        		Responsavel res = TratamentoDados.ajustaResponsável(linha);
	        		
	        		if (res == null){
	        			throw new IllegalArgumentException("Formato inválido para Responsável. Linha: " + numeroLinha + ". Arquivo: " + arq);
	        		}
	        		
	        		novaLocalidade.responsável = res;
	        	}
	    		
	    		else {
	        		throw new IllegalArgumentException("Informação inválida para Localidade. Linha: " + numeroLinha + ". Arquivo: " + arq);
	        	}
	        	
    			// Se possui localidade com todos os dados corretos
        		if ( (novaLocalidade != null) && novaLocalidade.possui_informações() ){
        			
        			localidades.add(novaLocalidade);
        			novaLocalidade = null;
        			numeroLocalidades++;
        		}
	        	
	        }
	        
	        scan.close();
	        System.out.println(numeroLocalidades + " localidades lidas com sucesso.");
		}

		
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IllegalArgumentException e){
			e.printStackTrace();
		}

		
		return localidades;
	}
	
	private boolean possui_informações (){
		return (this.disponibilidade != null && this.responsável != null);
	}

}