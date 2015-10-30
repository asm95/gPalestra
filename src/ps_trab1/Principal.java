package ps_trab1;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import modulos.Calendario;
import modulos.ControleTempo;
import modulos.Persistencia;
import palestra.Palestra;
import palestrante.Palestrante;

public class Principal {
	
	public static void main(String[] args) {

		LinkedHashMap<String,Palestrante> palestrantes = Palestrante.lePalestrantes("Palestrantes.txt");
		
		LinkedList<Palestra> palestras = Palestra.lePalestras("Palestras.txt", palestrantes);
		
		Calendario calendario = ControleTempo.organizaPalestras(palestras);
		
		Persistencia.geraArquivoCalendario(calendario,"Calendario.txt");
	}
}
