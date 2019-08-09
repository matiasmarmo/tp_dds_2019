package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public interface Evento {
	
	public boolean esProximo(Calendar unaFecha);
	
	public boolean fueSugerido(Calendar fechaReferencia);
	
	public void serSugerido(List<Sugerencia> sugerencias);
	
	public boolean esDeUsuario(Usuario usuario);
	
	public List<Sugerencia> getSugerencias();
	
	public Calendar getProximaFecha(Calendar fechaMinima);
	
	public Guardarropa getGuardarropa();
	
	public String getDescripcion();
	
	public Usuario getUsuario();
	
	public List<EventoUnico> instanciasEntreFechas(Calendar fechaInicio, Calendar fechaFin);
	
	public List<Sugerencia> getSugerenciasAceptadas(Calendar fechaReferencia);
	
	public boolean suGuardarropasEs(Guardarropa guardarropa);
	
	public boolean esEnFecha(Calendar fecha);

}
