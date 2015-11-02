package calendario;

import java.util.LinkedHashMap;

/**
 * Módulo que representa objeto do tipo AnoMes.
 * Sua abragência é como se fosse uma lista com pastas entituladas
 * de acordo com o ano e o mês do evento.
 * Ex: 10/2015, 11/2015, ...
 * @author 	Marcos
 * @since 	0.1
 *
 */
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
