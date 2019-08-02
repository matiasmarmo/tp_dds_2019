package dds.utn.ju_ma.group7.QueMePongo.Guardarropa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class Guardarropa {

	protected List<Prenda> prendas;
	private Set<Usuario> usuarios;

	public Guardarropa(Usuario usuarioCreador) {
		this.prendas = new ArrayList<Prenda>();
		this.usuarios = new HashSet<Usuario>();
		this.agregarUsuario(usuarioCreador);
	}
	
	private List<Prenda> getPrendasDisponibles(Calendar fechaReferencia) {
		List<Prenda> prendasOcupadas = RepositorioEventos.obtenerPrendasEnUso(this, fechaReferencia);
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
		return this.usuarios.stream().anyMatch(unUsuario -> unUsuario == usuarioBuscado);
	}
	
	public void agregarUsuario(Usuario nuevoUsuario) {
		this.usuarios.add(nuevoUsuario);
		if(!nuevoUsuario.tieneAccesoAGuardarropas(this)) {
			nuevoUsuario.agregarGuardarropa(this);
		}
	}

	public Stream<Atuendo> generarStreamDeAtuendos() {
		return GeneradorCombinaciones.generarStreamDeAtuendos(this.prendas);
	}

	public List<Atuendo> generarAtuendos(Calendar fechaReferencia) {
		return GeneradorCombinaciones.generarAtuendos(this.getPrendasDisponibles(fechaReferencia));
	}
}
