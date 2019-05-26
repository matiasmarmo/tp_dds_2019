package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

public class JobEventos extends TimerTask {

	@Override
	public void run() {
		List<Evento> eventosProximos = RepositorioEventos.eventosProximos(Calendar.getInstance());
		eventosProximos.stream().forEach(unEvento -> unEvento.serSugerido());
	}

}
