package dds.utn.ju_ma.group7.QueMePongo.Main;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.ConfiguracionNoInicializadaException;

public class QueMePongoConfiguration {
	
	private static QueMePongoConfiguration configuration = null;
	
	public static void inicializar(int limiteGuardarropasLimitado, String igUsername, String igPassword) {
		if(QueMePongoConfiguration.configuration == null) {
			QueMePongoConfiguration.configuration = new QueMePongoConfiguration(limiteGuardarropasLimitado, igUsername, igPassword);
		}
	}
	
	public static QueMePongoConfiguration instance() {
		if(QueMePongoConfiguration.configuration == null) {
			throw new ConfiguracionNoInicializadaException("La configuración de QueMePongo no se encuentra inicializada.");
		}
		return QueMePongoConfiguration.configuration;
	}
	
	private int limiteGuardarropasLimitado;
	private String igUsername;
	private String igPassword;
	
	private QueMePongoConfiguration(int limiteGuardarropasLimitado, String igUsername, String igPassword) {
		this.limiteGuardarropasLimitado = limiteGuardarropasLimitado;
		this.igUsername = igUsername;
		this.igPassword = igPassword;
	}
	
	public int getLimiteGuardarropasLimitado() {
		return this.limiteGuardarropasLimitado;
	}

	public String getIgUsername() {
		return igUsername;
	}

	public String getIgPassword() {
		return igPassword;
	}
	
	

}
