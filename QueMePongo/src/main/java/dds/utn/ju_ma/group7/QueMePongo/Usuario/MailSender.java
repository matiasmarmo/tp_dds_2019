package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;;

public class MailSender implements InteresEnNotificaciones {
	private String destinatario;
	
	public MailSender(String mail) {
		this.destinatario = mail;
	}
	
	public void notificar(EventoUnico evento, String notificacion) {
		String cuerpoMail = this.generarTextoDeLaNotificacion(evento, notificacion);
		this.enviarMail(cuerpoMail);
	}
	
	private void enviarMail(String notificacion) {
		String remitente = "losmallocos@gmail.com";
        String clave = "los5mallocos.";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage mensaje = new MimeMessage(session);
        
        try{
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject("Nuevas sugerencias han llegado!");
            mensaje.setText(notificacion);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();
        }catch (Exception e){
        	// TODO: excepcion propia
            e.printStackTrace();
        }
	}
	
	private String generarTextoDeLaNotificacion(EventoUnico evento, String notificacion) {
		return "Evento: " + evento.getDescripcion() + "\n" +
				"Fecha: " + this.fechaEvento(evento) + "\n" +
				notificacion + "\n";
	}
	
	private String fechaEvento(EventoUnico evento) {
		Calendar cal = evento.getProximaFecha(Calendar.getInstance());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(cal.getTime()); 
	}
}
