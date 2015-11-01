package calendário;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

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
