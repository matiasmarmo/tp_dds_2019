package dds.utn.ju_ma.group7.QueMePongo.Prenda;

import java.util.Arrays;
import java.util.List;

public class Color {
	
	private int rojo;
	private int verde;
	private int azul;
	
	public Color(int rojo, int verde, int azul) {
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
	}

	public int getRojo() {
		return rojo;
	}

	public int getVerde() {
		return verde;
	}

	public int getAzul() {
		return azul;
	}
	
	public List<Integer> getComponentes() {
		return Arrays.asList(this.rojo, this.verde, this.azul);
	}

}
