package dds.utn.ju_ma.group7.QueMePongo.Excepciones;

public class InvalidLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	final String username;

	public InvalidLoginException(String username, String message) {
		super(message);
		this.username = username;
	}

}
