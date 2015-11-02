package ps_trab1;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import calendario.Calendario;
import calendario.ControleTempo;
import calendario.Persistencia;
import palestra.Palestra;
import palestrante.Palestrante;
import localidade.Localidade;

public class Principal {
	
	public static void main(String[] args) {

		LinkedHashMap<String,Palestrante> palestrantes = Palestrante.lePalestrantes("Palestrantes.txt");
		
		LinkedList<Localidade> localidades = Localidade.leLocalidades("Localidades.txt");
		
		LinkedList<Palestra> palestras = Palestra.lePalestras("Palestras.txt", palestrantes, localidades);
		
		Calendario calendario = ControleTempo.organizaPalestras(palestras, localidades);
		
		Persistencia.geraArquivoCalendario(calendario,"Calendario.txt");
	}
}
