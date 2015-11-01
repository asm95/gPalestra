package ps_trab1;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import calendário.Calendario;
import calendário.ControleTempo;
import calendário.Persistencia;
import palestra.Palestra;
import palestrante.Palestrante;
import localidade.Localidade;

public class Principal {
	
	public static void main(String[] args) {

		LinkedHashMap<String,Palestrante> palestrantes = Palestrante.lePalestrantes("Palestrantes.txt");
		
		LinkedList<Palestra> palestras = Palestra.lePalestras("Palestras.txt", palestrantes);
		
		LinkedHashMap<String,Localidade> localidades = Localidade.leLocalidades("Localidade");
		
		Calendario calendario = ControleTempo.organizaPalestras(palestras);
		
		Persistencia.geraArquivoCalendario(calendario,"Calendario.txt");
	}
}
