package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramDirectShareRequest;
import org.brunocvcunha.instagram4j.requests.InstagramDirectShareRequest.ShareType;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.TipoAlerta;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Excepciones.NotificationError;
import dds.utn.ju_ma.group7.QueMePongo.Main.QueMePongoConfiguration;

@Entity
public class IgNotifier extends InteresEnNotificaciones {
	@Transient
	private static Instagram4j instagram = null;
	private String nombreUsuario = null;
	private String idUsuario = null;

	public IgNotifier() {

	}

	public IgNotifier(String nombreUsuario) throws ClientProtocolException, IOException {
		this.nombreUsuario = nombreUsuario;
	}

	private static void buildInstance() throws ClientProtocolException, IOException {
		String username = QueMePongoConfiguration.instance().getIgUsername();
		String password = QueMePongoConfiguration.instance().getIgPassword();
		instagram = Instagram4j.builder().username(username).password(password).build();
		instagram.setup();
		instagram.login();
	}

	private synchronized <T> T sendRequest(InstagramRequest<T> request) throws IOException {
		if (IgNotifier.instagram == null) {
			IgNotifier.buildInstance();
		}
		return IgNotifier.instagram.sendRequest(request);
	}

	private void cargarIdUsuario() {
		try {
			InstagramSearchUsernameResult response = this
					.sendRequest(new InstagramSearchUsernameRequest(this.nombreUsuario));
			this.idUsuario = Long.toString(response.getUser().getPk());
		} catch (IOException e) {
			throw new NotificationError("No se pudo obtener el id del usuario " + this.nombreUsuario);
		}
	}

	private void enviarNotificacion(String mensaje) {
		if (this.idUsuario == null) {
			this.cargarIdUsuario();
		}
		List<String> recipients = Arrays.asList(this.idUsuario);
		try {
			this.sendRequest(InstagramDirectShareRequest.builder().shareType(ShareType.MESSAGE).recipients(recipients)
					.message(mensaje).build());
		} catch (IOException e) {
			throw new NotificationError("No se pudo notificar por instagram al usuario " + this.nombreUsuario);
		}
	}

	@Override
	public void notificar(EventoUnico evento, String notificacion) {
		this.enviarNotificacion(this.generarTextoNotificacionSugerencia(evento, notificacion));
	}

	@Override
	public void notificarAlerta(Calendar fecha, TipoAlerta alerta) {
		this.enviarNotificacion(this.generarTextoNotificacionAlerta(fecha, alerta));
	}

}
