package modulos;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Palestrante {

	private String nome;
	private LinkedList<LocalDateTime[]> disponibilidade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LinkedList<LocalDateTime[]> getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(LinkedList<LocalDateTime[]> disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
}
