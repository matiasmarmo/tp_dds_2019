package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.TipoAlerta;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Excepciones.NotificationError;
import dds.utn.ju_ma.group7.QueMePongo.Main.QueMePongoConfiguration;;

public class MailSender extends InteresEnNotificaciones {
	private String destinatario;
	private String username;
	private String password;
	
	public MailSender(String mail) {
		this.destinatario = mail;
		this.username = QueMePongoConfiguration.instance().getMailAccount();
		this.password = QueMePongoConfiguration.instance().getMailPassword();
	}
	
	public void notificar(EventoUnico evento, String notificacion) {
		String cuerpoMail = this.generarTextoNotificacionSugerencia(evento, notificacion);
		this.enviarMail(cuerpoMail);
	}
	
	private void enviarMail(String notificacion) {           
		Session session = crearSession();
       
        MimeMessage mensaje = new MimeMessage(session);
        
        armarMensaje(mensaje, notificacion);
        enviarMensaje(session, mensaje);
	}
	
	private Session crearSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.clave", password);
        
        return Session.getDefaultInstance(props);
	}
	
	private void armarMensaje(MimeMessage mensaje, String notificacion) {
		try {
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
	        mensaje.setSubject("Nuevas sugerencias han llegado!");
	        mensaje.setText(notificacion);
		}catch (Exception e){
        	throw new NotificationError("No se pudo armar el mensaje");
        } 
	}
	
	private void enviarMensaje(Session session, MimeMessage mensaje) {
		try {
			Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();
        }catch (Exception e){
        	throw new NotificationError("No se pudo notificar al usuario " + destinatario);
        }
	}
	
	public void notificarAlerta(Calendar fecha, TipoAlerta alerta) {
		this.enviarMail(alerta.getDescripcion());
	}
}
