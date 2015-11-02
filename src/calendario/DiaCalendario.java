package calendario;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * Módulo que representa objeto do tipo Dia.
 * Sua abragência é como se fosse uma lista com pastas entituladas
 * de acordo com o dia do evento, sendo a gaveta onde elas estão
 * inseridas um objeto do tipo AnoMes
 * Ex: 13, 14, 15, ... da pasta:'Novembro/2015'
 * @author 	Marcos
 * @since 	0.1
 *
 */
public class DiaCalendario {

	private int dia;
	private LinkedHashMap<LocalDateTime,HoraCalendario> horas;

	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public LinkedHashMap<LocalDateTime, HoraCalendario> getHoras() {
		return horas;
	}
	public void setHoras(LinkedHashMap<LocalDateTime, HoraCalendario> horas) {
		this.horas = horas;
	}
}
