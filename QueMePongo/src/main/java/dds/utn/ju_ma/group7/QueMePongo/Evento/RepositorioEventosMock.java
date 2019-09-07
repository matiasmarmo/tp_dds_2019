package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEventosMock extends RepositorioEventos {
	
	private List<Evento> eventos;
	
	public RepositorioEventosMock() {
		this.eventos = new ArrayList<Evento>();
	}

	@Override
	protected List<Evento> todosLosEventos() {
		return this.eventos;
	}

	@Override
	protected void almacenar(Evento evento) {
		this.eventos.add(evento);
	}

}
