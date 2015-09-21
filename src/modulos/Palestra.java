package modulos;

public class Palestra {

	private String nome;
	private Palestrante palestrante;
	private String tema;
	private String local;
	private long duracaoMinutos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Palestrante getPalestrante() {
		return palestrante;
	}
	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public long getDuracaoMinutos() {
		return duracaoMinutos;
	}
	public void setDuracaoMinutos(long duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}
}
