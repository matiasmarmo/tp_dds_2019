package dds.utn.ju_ma.group7.QueMePongo.Guardarropa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Guardarropa {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "guardarropa")
	protected List<Prenda> prendas;
	
	@Transient
	private RepositorioEventos repositorioEventos;

	public Guardarropa() {
	}

	public Guardarropa(Usuario usuarioCreador, RepositorioEventos repositorioEventos) {
		this.prendas = new ArrayList<Prenda>();
		this.repositorioEventos = repositorioEventos;
		usuarioCreador.agregarGuardarropa(this);
	}

	private List<Prenda> getPrendasDisponibles(Calendar fechaReferencia) {
		List<Prenda> prendasOcupadas = this.repositorioEventos
				.obtenerEventosSugeridosDeUnGuardarropasParaFecha(this, fechaReferencia).stream()
				.flatMap(evento -> evento.getSugerenciasAceptadas(fechaReferencia).stream())
				.flatMap(sugerencia -> sugerencia.todasLasPrendas().stream()).collect(Collectors.toList());
		return this.prendas.stream().filter(prenda -> !prendasOcupadas.contains(prenda)).collect(Collectors.toList());
	}

	public void agregarPrendas(List<Prenda> prendas) {
		prendas.forEach(prenda -> this.agregarPrenda(prenda));
	}

	public void agregarPrenda(Prenda prenda) {
		this.prendas.add(prenda);
	}

	public void agregarPrenda(Prenda prenda, String imagen) {
		prenda.setImagen(imagen);
		this.agregarPrenda(prenda);
	}

	public boolean usuarioTieneAcceso(Usuario usuarioBuscado) {
		return usuarioBuscado.esDuenioDeGuardarropas(this);
	}

	public Stream<Atuendo> generarStreamDeAtuendos() {
		return GeneradorCombinaciones.generarStreamDeAtuendos(this.prendas);
	}

	public List<Atuendo> generarAtuendos(Calendar fechaReferencia) {
		return GeneradorCombinaciones.generarAtuendos(this.getPrendasDisponibles(fechaReferencia));
	}

}
