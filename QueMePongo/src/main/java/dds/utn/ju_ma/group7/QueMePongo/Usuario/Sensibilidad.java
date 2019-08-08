package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.ParteCuerpo;

public class Sensibilidad {
	
	private Map<ParteCuerpo, Integer> valores;
	
	public Sensibilidad() {
		this.valores = new HashMap<ParteCuerpo, Integer>();
		ParteCuerpo[] tiposSensibilidadArray = ParteCuerpo.class.getEnumConstants();
		List<ParteCuerpo> tiposSensibilidadList = Arrays.asList(tiposSensibilidadArray);
		tiposSensibilidadList.forEach(tipo -> this.valores.put(tipo, 0));
	}
	
	public void tuvoFrio(ParteCuerpo tipo) {
		this.valores.put(tipo, this.valores.get(tipo) + 1);
	}
	
	public void tuvoCalor(ParteCuerpo tipo) {
		this.valores.put(tipo, this.valores.get(tipo) - 1);
	}
	
	public Integer obtenerNivelSensibilidad(ParteCuerpo tipo) {
		return this.valores.get(tipo);
	}
	
	public void setNivelSensibilidad(ParteCuerpo tipo, Integer valor) {
		this.valores.put(tipo, valor);
	}
	
}
