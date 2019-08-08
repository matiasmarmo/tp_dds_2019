package dds.utn.ju_ma.group7.QueMePongo.Prenda;

public enum ParteCuerpo {
	
	CABEZA, CUELLO, MANOS, TORSO, PIERNAS, PIES;
	
	private double coeficienteAbrigoMinimo;
	
	static {
		CABEZA.coeficienteAbrigoMinimo = 0.0;
		CUELLO.coeficienteAbrigoMinimo = 0.0;
		MANOS.coeficienteAbrigoMinimo = 0.0;
		TORSO.coeficienteAbrigoMinimo = 0.5;
		PIERNAS.coeficienteAbrigoMinimo = 0.3;
		PIES.coeficienteAbrigoMinimo = 0.3;
	}

	public double getCoeficienteAbrigoMinimo() {
		return coeficienteAbrigoMinimo;
	}

}
