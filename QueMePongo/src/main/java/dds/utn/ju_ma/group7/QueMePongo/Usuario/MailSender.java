package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;;

public class MailSender implements InteresEnNotificaciones {
	private String destinatario;
	
	public MailSender(String mail) {
		destinatario = mail;
	}
	
	public void notificar(Evento evento) {
		String notificacion = generarTextoDeLaNotificacion(evento);
		enviarMail(notificacion);
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
            mensaje.setSubject("Segunda prueba TP");
            mensaje.setText(notificacion);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();
        }catch (Exception e){
            e.printStackTrace(); //Esto no se si esta bueno
        }
	}
	
	private String generarTextoDeLaNotificacion(Evento evento) {
		String notificacion = "hola que tal usuarie";
		//evento.getUsuario();
		return notificacion;
	}
}
