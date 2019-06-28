package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class EventoRepetitivo implements Evento {
	
	private Usuario usuario;
	private Guardarropa guardarropa;
	private String descripcion;
	private Calendar fechaInicio;
	private TipoRecurrencia tipoRecurrencia;
	private List<EventoUnico> instancias;
	
	public EventoRepetitivo(Usuario usuario, Guardarropa guardarropa, String descripcion, Calendar fechaInicio, TipoRecurrencia tipoRecurrencia) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.tipoRecurrencia = tipoRecurrencia;
		this.instancias = new ArrayList<EventoUnico>();
		this.cargarSiguienteInstancia(this.fechaInicio);
	}
	
	@Override
	public Guardarropa getGuardarropa() {
		return this.guardarropa;
	}
	
	private EventoUnico obtenerProximaInstancia(Calendar fechaMinima) {
		EventoUnico ultimaInstanciaRegistrada = this.instancias.get(this.instancias.size() - 1);
		if(ultimaInstanciaRegistrada.esPosteriorA(fechaMinima)) {
			return ultimaInstanciaRegistrada;
		}
		this.cargarSiguienteInstancia(fechaMinima);
		return this.instancias.get(this.instancias.size() - 1);
	}
	
	private Calendar fechaSiguienteInstancia(Calendar fechaMinima) {
		if(this.instancias.isEmpty()) {
			return this.tipoRecurrencia.obtenerFechaSiguienteIntancia(this.fechaInicio, fechaMinima);
		}
		
		EventoUnico ultimaInstanciaRegistrada = this.instancias.get(this.instancias.size() - 1);
		if(ultimaInstanciaRegistrada.fueSugerido(fechaMinima) || ultimaInstanciaRegistrada.esPosteriorA(fechaMinima)) {
			return this.tipoRecurrencia.obtenerFechaSiguienteIntancia(this.fechaInicio, fechaMinima);
		}
		
		return this.instancias.get(this.instancias.size() - 1).getProximaFecha(fechaMinima);
	}
	
	private void cargarSiguienteInstancia(Calendar fechaMinima) {
		Calendar fechaSiguienteInstancia = this.fechaSiguienteInstancia(fechaMinima);
		EventoUnico siguienteInstancia = new EventoUnico(this.usuario, this.guardarropa, fechaSiguienteInstancia, this.descripcion);
		this.instancias.add(siguienteInstancia);
	}
	
	@Override
	public boolean esProximo(Calendar unaFecha) {
		return ChronoUnit.DAYS.between(unaFecha.toInstant(), this.fechaSiguienteInstancia(unaFecha).toInstant()) < 5;
	}
	
	@Override
	public boolean fueSugerido(Calendar fechaActual) {
		EventoUnico proximaInstancia = this.obtenerProximaInstancia(fechaActual);
		return proximaInstancia.fueSugerido(fechaActual);
	}

	public void serSugerido(List<Sugerencia> sugerencias, Calendar fechaReferencia) {
		this.obtenerProximaInstancia(fechaReferencia).serSugerido(sugerencias);
	}
	
	@Override
	public void serSugerido(List<Sugerencia> sugerencias) {
		this.serSugerido(sugerencias, Calendar.getInstance());
	}

	@Override
	public boolean esDeUsuario(Usuario usuario) {
		return this.usuario == usuario;
	}
	
	@Override
	public List<Sugerencia> getSugerencias() {
		return this.instancias.stream().flatMap(evento -> evento.getSugerencias().stream()).collect(Collectors.toList());
	}
	
	@Override
	public Calendar getProximaFecha(Calendar fechaMinima) {
		return this.fechaSiguienteInstancia(fechaMinima);
	}
}
