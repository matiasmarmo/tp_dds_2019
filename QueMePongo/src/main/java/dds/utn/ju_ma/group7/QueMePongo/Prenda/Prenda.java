package dds.utn.ju_ma.group7.QueMePongo.Prenda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.ImagenInvalidaException;

@Entity
public class Prenda implements InterfazPrenda {
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoPrenda tipoPrenda;
	@Enumerated(EnumType.STRING)
	private TipoTela tipoTela;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Color colorPrimario;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Color colorSecundario;
	@Transient
	private BufferedImage imagen;
	private String rutaPrenda;

	public boolean esPrendaNula() {
		return false;
	}

	public Prenda() {
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

	public ParteCuerpo getParteCuerpo() {
		return this.tipoPrenda.getParteCuerpo();
	}

	public int getNivelAbrigo() {
		return this.tipoPrenda.getNivelAbrigo();
	}

	public int getJerarquia() {
		return this.tipoPrenda.getJerarquia();
	}

	public BufferedImage getImagen() {
		if(this.rutaPrenda != null) {
			this.setImagen(this.rutaPrenda);
		}
		return this.imagen;
	}

	public void setImagen(String path) {
		try {
			this.rutaPrenda = path;
			this.imagen = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new ImagenInvalidaException("No se pudo cargar la imagen");
		} catch (NullPointerException e) {
			throw new ImagenInvalidaException("El path no puede ser nulo");
		}
	}

	public boolean esDeCategoria(CategoriaPrenda categoria) {
		return this.getCategoria() == categoria;
	}

}
