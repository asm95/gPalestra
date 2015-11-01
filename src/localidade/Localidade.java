package localidade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.time.LocalTime;

import localidade.TratamentoDados;


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
	private LocalTime 	hora;
	private Responsavel responsável;
	
	private static Scanner scan;
	
	public Localidade(){
		endereço = null;
		hora = null;
		responsável = null;
	}
	
	
	public boolean possui_informações (){
		return (this.hora != null && this.responsável != null);
	}
	
	public boolean exists (){
		return (this != null);
	}
	
	public void setEndereço(String Endereço) {
		this.endereço = Endereço;
	}
	
	public String getEndereço(){
		return endereço;
	}
	
	public void setHora(LocalTime hora){
		this.hora = hora;
	}
	
	public LocalTime getHora(){
		return hora;
	}
	
	public Responsavel getResonsável (){
		return responsável;
	}
	
	public void setResponsável (Responsavel res){
		this.responsável = res;
	}
	
	
	public static LinkedHashMap<String,Localidade> leLocalidades(String arq) {
		
		LinkedHashMap<String,Localidade> localidades = new LinkedHashMap<String,Localidade>();
		
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
	    		
	    		else if ( linha.startsWith("Hora: ") ){
	        		LocalTime hora = TratamentoDados.ajustaHora(linha);
	        		
	        		if (hora == null){
	        			throw new IllegalArgumentException("Formato inválido para Hora. Linha: " + numeroLinha + ". Arquivo: " + arq);
	        		}
	        		
	        		novaLocalidade.setHora(hora);
	        	}
	    		
	    		else if ( linha.startsWith("Responsável: ") ){
	        		Responsavel res = TratamentoDados.ajustaResponsável(linha);
	        		
	        		if (res == null){
	        			throw new IllegalArgumentException("Formato inválido para Responsável. Linha: " + numeroLinha + ". Arquivo: " + arq);
	        		}
	        		
	        		novaLocalidade.setResponsável(res);
	        	}
	    		
	    		else {
	        		throw new IllegalArgumentException("Informação inválida para Localidade. Linha: " + numeroLinha + ". Arquivo: " + arq);
	        	}
	        	
    			// Se possui localidade com todos os dados corretos
        		if ( (novaLocalidade != null) && novaLocalidade.possui_informações() ){
        			
        			localidades.put(novaLocalidade.endereço, novaLocalidade);
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
	
}