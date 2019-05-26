package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;

public class Sugeridor {
	private ProveedorClima proveedorClima;

	public ProveedorClima getProveedorClima() {
		return proveedorClima;
	}

	public void setProveedorClima(ProveedorClima proveedorClima) {
		this.proveedorClima = proveedorClima;
	}
	
	private List<Atuendo> filtrarAtuendosPorTemperatura(List<Atuendo> atuendos){
		//proveedorClima.instance();
		double temperatura = this.proveedorClima.getTemperatura(new Date());
		return atuendos.stream().filter(unAtuendo -> unAtuendo.esAdecuadoATemperatura(temperatura)).collect(Collectors.toList());
	}

	public List<Atuendo> sugerencias(List<Atuendo> atuendos) {
		return this.filtrarAtuendosPorTemperatura(atuendos);
	}

}
