package teste;

import java.util.LinkedList;
import java.util.Iterator;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import localidade.Localidade;
import localidade.Responsavel;


public class teste_Localidade {
	@Test
	public void carregaLocalidade(){
		String arq;
		LinkedList<Localidade> localidades;
		
		arq = "tst_localidade.txt";
		
		localidades = Localidade.leLocalidades(arq);
		
		imprimeLocalidades(localidades);
		
		System.out.println("Num: "+ localidades.size());
		
		assertEquals ( 2, localidades.size() );
		
	}
	
	@Ignore
	public static void imprimeNumero(int[] num){
		int i, len;
		
		len = num.length;
		
		if (len != 10 && len != 11)
			return;
		
		System.out.print("(" + num[0] + num[1] + ") ");
		
		for(i=2; i<len; i++){
			System.out.print(num[i]);
		}
	}
	
	@Ignore
	public static void imprimeResponsável(Responsavel res){
		System.out.print("\nResponsável: " + res.getNome() + "; ");
		imprimeNumero(res.getNumero());
		System.out.println("; " + res.getEmail());
	}
	
	@Ignore
	public static void imprimeLocalidades(LinkedList<Localidade>lista){
		int i=1;
		
		for(Localidade loc : lista){
			
			// falta hora e responsável
			System.out.print("Localidade " + i + ": " + loc.getEndereço() + "\nDisponibilidade: ");
			
			Iterator<LocalTime[]> itDisp = loc.getDisponibilidade().iterator();
			Iterator<Integer> itDia = loc.getDias().iterator();
			
			if ( itDisp.hasNext() && itDia.hasNext() ){
				int dia = itDia.next();
				LocalTime[] disp = itDisp.next();
				
				System.out.print(dia + ", " + disp[0].toString() + "-" + disp[1].toString() + "; ");
			}
			
			imprimeResponsável(loc.getResonsável());
					
			i++;
		}
	}	
}
