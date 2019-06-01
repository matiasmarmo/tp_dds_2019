package dds.utn.ju_ma.group7.QueMePongo.Main;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.ConfiguracionNoInicializadaException;

public class QueMePongoConfiguration {
	
	private static QueMePongoConfiguration configuration = null;
	
	public static void inicializar(int limiteGuardarropasLimitado) {
		if(QueMePongoConfiguration.configuration == null) {
			QueMePongoConfiguration.configuration = new QueMePongoConfiguration(limiteGuardarropasLimitado);
		}
	}
	
	public static QueMePongoConfiguration instance() {
		if(QueMePongoConfiguration.configuration == null) {
			throw new ConfiguracionNoInicializadaException("La configuración de QueMePongo no se encuentra inicializada.");
		}
		return QueMePongoConfiguration.configuration;
	}
	
	private int limiteGuardarropasLimitado;
	
	private QueMePongoConfiguration(int limiteGuardarropasLimitado) {
		this.limiteGuardarropasLimitado = limiteGuardarropasLimitado;
	}
	
	public int getLimiteGuardarropasLimitado() {
		return this.limiteGuardarropasLimitado;
	}

}
