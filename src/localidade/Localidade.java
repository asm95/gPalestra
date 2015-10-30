package localidade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.time.LocalTime;

import localidade.TratamentoDados;

public class Localidade {
	private String endereço;
	private LocalTime hora;
	private Responsavel responsael;
	
	private static Scanner scan;
	
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
	
	public static LinkedHashMap<String,Localidade> leLocalidades(String arq) {
		
		LinkedHashMap<String,Localidade> localidades = new LinkedHashMap<String,Localidade>();
		
		try {
	    	scan = new Scanner(new File(arq));
	    	int numeroLinha = 0;
	    	
	        while(scan.hasNextLine()) {
	        	String linha = scan.nextLine();
	        	numeroLinha ++;
	        	
	        	if(linha.startsWith("Endereço: ")) {
	        		Localidade novaLocalidade = new Localidade();
	        		novaLocalidade.setEndereço(TratamentoDados.ajustaEndereço(linha));
	        		
	        		if(scan.hasNextLine()) {
		            	linha = scan.nextLine();
		            	numeroLinha ++;
			            if(linha.startsWith("Hora: ")) {
			            	novaLocalidade.setHora(TratamentoDados.ajustaHora(linha));
			            	localidades.put(novaLocalidade.getEndereço(), novaLocalidade);
			            } 
			            else 
			            {
			            	throw new IllegalArgumentException("Esperado \"Hora: \" na linha " + numeroLinha + " do arquivo " + arq);
			            }
	        		}
	        	}
	        }
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