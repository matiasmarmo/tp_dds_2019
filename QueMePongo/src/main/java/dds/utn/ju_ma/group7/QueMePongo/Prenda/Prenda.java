package dds.utn.ju_ma.group7.QueMePongo.Prenda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Prenda {
	private final TipoPrenda tipoPrenda;
	private final TipoTela tipoTela;
	private final Color colorPrimario;
	private final Color colorSecundario;
	private BufferedImage imagen;
	
	private static Prenda prendaNula = null;
	
	public static Prenda PrendaNula() {
		if(Prenda.prendaNula == null) {
			Prenda.prendaNula = new Prenda(null, null, null, null);
		}
		return Prenda.prendaNula;
	}
	
	public boolean esPrendaNula() {
		return Prenda.prendaNula == null ? false : this == Prenda.prendaNula;
	}

	public Prenda(TipoPrenda tipoPrenda, TipoTela tipoTela, Color colorPrimario, Color colorSecundario) {
		this.tipoPrenda = tipoPrenda;
		this.tipoTela = tipoTela;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

	public TipoPrenda getTipoPrenda() {
		return tipoPrenda;
	}

	public TipoTela getTipoTela() {
		return tipoTela;
	}

	public Color getColorPrimario() {
		return colorPrimario;
	}

	public Color getColorSecundario() {
		return colorSecundario;
	}

	public CategoriaPrenda getCategoria() {
		return this.tipoPrenda.getCategoria();
	}
	
	public int getJerarquia() {
		return this.tipoPrenda.getJerarquia();
	}
	
	public BufferedImage getImagen() {
		return this.imagen;
	}
	
	public void setImagen(String path) {
		try {
			this.imagen = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new ImagenInvalidaException("No se pudo cargar la imagen");
		}
	}
	
	public boolean esDeCategoria(CategoriaPrenda categoria) {
		return this.getCategoria() == categoria;
	}
	
}
