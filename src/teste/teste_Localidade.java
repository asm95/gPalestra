package teste;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import localidade.Localidade;
import localidade.Responsavel;

public class teste_Localidade {
	@Ignore
	public static void imprimeResponsável(Responsavel res){
		System.out.print("Responsável: " + res.getNome() + "\n" + res.getNumero() + "\n" + res.getEmail());
	}
	
	@Ignore
	public static void imprimeLocalidades(LinkedHashMap<String, Localidade>lista){
		int i=1;
		
		for(Entry<String, Localidade> entry : lista.entrySet()){
			Localidade loc = entry.getValue();
			
			System.out.println("Localidade " + i + ": " + loc.getEndereço() + ".\nHora: " + loc.getHora().toString() + "\nResponsável: " );
					
			i++;
		}
	}
	
	@Test
	public void carregaLocalidade(){
		String arq;
		LinkedHashMap<String, Localidade> localidades;
		
		arq = "tst_localidade.txt";
		
		localidades = Localidade.leLocalidades(arq);
		
		imprimeLocalidades(localidades);
		
		System.out.println("Num: "+ localidades.size());
		
		assertEquals ( 2, localidades.size() );
		
	}
}
