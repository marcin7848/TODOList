package TODOList.dao;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    private String username;
    private String password;
    private String fromEmail;
    private String host;
    private String port;

    SendEmail(){
    }

    SendEmail(String username, String password, String fromEmail, String host, String port) {
        this.username = username;
        this.password = password;
        this.fromEmail = fromEmail;
        this.host = host;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean send(String toEmail, String subject, String message){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
        });

        try {
            Message createMessage = new MimeMessage(session);
            createMessage.setFrom(new InternetAddress(fromEmail));
            createMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            createMessage.setSubject(subject);
            createMessage.setText(message);
            Transport.send(createMessage);

            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
