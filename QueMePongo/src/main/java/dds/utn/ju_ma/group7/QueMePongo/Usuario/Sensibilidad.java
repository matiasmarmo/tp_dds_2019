package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sensibilidad {
	
	private Map<TipoSensibilidad, Integer> valores;
	
	public Sensibilidad() {
		this.valores = new HashMap<TipoSensibilidad, Integer>();
		TipoSensibilidad[] tiposSensibilidadArray = TipoSensibilidad.class.getEnumConstants();
		List<TipoSensibilidad> tiposSensibilidadList = Arrays.asList(tiposSensibilidadArray);
		tiposSensibilidadList.forEach(tipo -> this.valores.put(tipo, 0));
	}
	
	public void tuvoFrio(TipoSensibilidad tipo) {
		this.valores.put(tipo, this.valores.get(tipo) + 1);
	}
	
	public void tuvoCalor(TipoSensibilidad tipo) {
		this.valores.put(tipo, this.valores.get(tipo) - 1);
	}
	
	public Integer obtenerNivelSensibilidad(TipoSensibilidad tipo) {
		return this.valores.get(tipo);
	}
	
	public void setNivelSensibilidad(TipoSensibilidad tipo, Integer valor) {
		this.valores.put(tipo, valor);
	}
	
	public Integer sensibilidadGlobal() {
		return this.valores.values().stream().mapToInt(valor -> valor).sum();
	}
}
