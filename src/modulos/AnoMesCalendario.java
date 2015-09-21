package modulos;

import java.util.LinkedHashMap;

public class AnoMesCalendario {

	private int ano;
	private int mes;
	private LinkedHashMap<String,DiaCalendario> dias;
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public LinkedHashMap<String, DiaCalendario> getDias() {
		return dias;
	}
	public void setDias(LinkedHashMap<String, DiaCalendario> dias) {
		this.dias = dias;
	}
}
