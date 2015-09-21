package modulos;

import java.util.LinkedHashMap;

public class Calendario {

	private LinkedHashMap<String,AnoMesCalendario> meses;

	public LinkedHashMap<String, AnoMesCalendario> getMeses() {
		return meses;
	}

	public void setMeses(LinkedHashMap<String, AnoMesCalendario> meses) {
		this.meses = meses;
	}
	
}
