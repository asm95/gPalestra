package calendario;

import java.util.LinkedHashMap;

/**
 * Módulo que representa objeto do tipo Calendario.
 * Sua abragência é máxima para este módulo, pois ele engloba
 * os anos (máxima unidade de medida para um calendário).
 * @author 	Marcos
 * @since 	0.1
 *
 */
public class Calendario {

	private LinkedHashMap<String,AnoMesCalendario> meses;

	public LinkedHashMap<String, AnoMesCalendario> getMeses() {
		return meses;
	}

	public void setMeses(LinkedHashMap<String, AnoMesCalendario> meses) {
		this.meses = meses;
	}
	
}
