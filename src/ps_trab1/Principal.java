package ps_trab1;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import modulos.Calendario;
import modulos.ControleTempo;
import modulos.Leitura;
import modulos.Palestra;
import modulos.Palestrante;
import modulos.Persistencia;

public class Principal {
	
	public static void main(String[] args) {

		LinkedHashMap<String,Palestrante> palestrantes = Leitura.lePalestrantes("Palestrantes.txt");
		
		LinkedList<Palestra> palestras = Leitura.lePalestras("Palestras.txt", palestrantes);
		
		Calendario calendario = ControleTempo.organizaPalestras(palestras);
		
		Persistencia.geraArquivoCalendario(calendario,"Calendario.txt");
	}
}
