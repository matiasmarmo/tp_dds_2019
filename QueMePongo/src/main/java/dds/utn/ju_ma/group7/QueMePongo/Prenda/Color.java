package dds.utn.ju_ma.group7.QueMePongo.Prenda;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Color {
	@Id
	@GeneratedValue
	private Long id;

	private Integer rojo;
	private Integer verde;
	private Integer azul;
	private String rgb;

	public Color() {
	}

	public Color(int rojo, int verde, int azul) {
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
		this.rgb = "#" + Integer.toHexString(rojo) + Integer.toHexString(verde) + Integer.toHexString(azul);
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
	
	public String getRgb() {
		return this.rgb;
	}

	public List<Integer> getComponentes() {
		return Arrays.asList(this.rojo, this.verde, this.azul);
	}

	public boolean esIgualA(Color otroColor) {
		return this.getAzul() == otroColor.getAzul() && this.getRojo() == otroColor.getRojo()
				&& this.getVerde() == otroColor.getVerde();
	}

}
