package mail;

/**
 * Created by zhaixt on 2018/2/24.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Mailer is a simple object created to send email with attachments.
 *
 * Usage example: <ul> <li> From command prompt:<br>
 * <code>java Mailer smtp.server
 * type from subject "message" to.addr1 to.addr2 ...</code> </li> <li> From
 * other objects:<br>
 * <code> Mailer m = new Mailer(); <br> m.setSmtpHost("smpt.server.com");
 * // optional <br> m.setUsername("smpt.username"); // optional <br>
 * m.setPassword("smpt.password"); <br> m.setType("text/html"); <i>// or
 * m.setType("text/plain"); </i><br> m.setFrom("sender@server.com"); <br>
 * m.setSubject("The Subject"); <br> m.setMessage("The message body"); <i>// can
 * be html </i><br> m.setTo("to@server.com"); <br> <i>// if t is an array with
 * e-mail addresses: m.setTos(t);<br> // attachments </i><br>
 * m.setAttachments(v); <br> <br> <i>// Send and handle the feedback </i> <br>
 * if (!m.send()) { <br> System.out.println("Error Sending message");<br> }<br>
 * </code> </li> </ul> Note: the activation.jar and mail.jar (javax.mail) have
 * to be in the classpath
 *
 */

public class Mailer {
    final static Logger logger = LoggerFactory.getLogger(Mailer.class);


    String[] tos = {"xiaotong.zhai@126.com"};//recipient email addresses
    boolean areThereAttachments = false;
    String subject = "local test 1";
    String message = "local";
    String pngMessage = "";
    String from = "data-noreply@126.com";
    String type = "text/plain";
    String username, password; // used for smtpHost authentication
    List<File> attachments;
    String smtpHost = "";
    String smtpPort;
    String secureSmtp;
    String sendError = ""; //error message if sending fails
    boolean sessionDebug = false; //debug mode for session
    String[] cc;
    String[] bcc;
    String pngUrl;
    String fullFileNameWithData;
    String attachmentFileName;
    int queryType;

    /**
     * Create a Mailer Object. <br>
     *
     * The default type is text/plain
     */

    public Mailer() {
    }


    /**
     * Set attachments<br> l is a List of valid File objects. <br> To create the
     * list for a single file, just type:<br>
     * <code> List v = new
     * ArrayList();<br> v.add(new File("string path")); </code>
     *
     * @param l
     */
    public void setAttachments(List<File> l) {
        areThereAttachments = true;

        attachments = null;
        attachments = l;
    }

    /**
     * Set username for smtp authentication (optional) <br>
     *
     * note: your smtp server need to support this feature. Leave null if not
     * used
     *
     * @param s
     */
    public void setUsername(String s) {
        username = s;
    }

    /**
     * Set password for smtp authentication (optional) <br>
     *
     * note: your smtp server need to support this feature. Leave null if not
     * used
     *
     * @param s
     */
    public void setPassword(String s) {
        password = s;
    }

    /**
     * Pack and send the email. <br>
     *
     * Exception <i>MessagingException</i> and <i>IOException</i> are thrown,
     * otherwise, in case of errors, returns false and write the exception on
     * the standard error.
     *
     * @return
     * @throws Exception
     */
    public boolean send() throws MessagingException,Exception {
        // here we should check params
        // ...
        try {
            sendError = "";
            sendEmail();
        } catch (MessagingException e){
            logger.warn("java mail exception:smtpHost:{},exception:{}",smtpHost,e.toString());
        }catch (Exception e) {
            logger.warn("Mailer,artmail: Error when sending email: {}" , e);
            sendError = e.toString();
            e.printStackTrace(System.out);
            throw new Exception("artmail exception: Error when sending email: " + e);
        }
        return true;
    }

    private void sendEmail() throws MessagingException, IOException {
        //Set smtp properties
        System.out.println("mail begin send!");
        try {
            Properties props = new Properties();
//todo 注释邮件配置
//			//enable secure smtp
//			if (secureSmtp != null && secureSmtp.equals("starttls")) {
//                props.put("mail.smtp.starttls.enable", "true");
//            }
//			//smtp port may not be 25
//			if (smtpPort != null) {
//                props.put("mail.smtp.port", smtpPort);
//            }
//
//			props.put("mail.smtp.host", smtpHost);
//
//			// If you're sending to multiple recipients, if one recipient address fails, by default no email is sent to the other recipients
//			// set the sendpartial property to true to have emails sent to the valid addresses, even if invalid ones exist
//			props.put("mail.smtp.sendpartial", "true");
//
//
//			//get the default Session
//			Session session;
//			if (username != null && password != null) {
//                //smtp authentication enabled
//                props.put("mail.smtp.auth", "true");
//                Authenticator auth = new SMTPAuthenticator();
//                session = Session.getInstance(props, auth);
//            } else {
//                session = Session.getDefaultInstance(props, null);
//            }
//todo 注释邮件配置结束

            /*
			* todo 写死邮件配置
			* */
            props.put("mail.smtp.host", "smtp.partner.outlook.cn");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.sendpartial", "true");
            Session session;
            props.put("mail.smtp.auth", "true");
            Authenticator auth = new SMTPAuthenticator();
            session = Session.getInstance(props, auth);



            //enable session debug
            session.setDebug(sessionDebug);

            // Create a message
            Message msg = new MimeMessage(session);

            // Set the FROM: address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);

            // Set the TO: address(es)
            if (tos != null) {
                InternetAddress[] addressTo = new InternetAddress[tos.length];
                for (int i = 0; i < tos.length; i++) {
                    addressTo[i] = new InternetAddress(tos[i]);
                }
                msg.setRecipients(Message.RecipientType.TO, addressTo);
            }

            // Set the CC: address(es)
            if (cc != null) {
                InternetAddress[] addressCc = new InternetAddress[cc.length];
                for (int i = 0; i < cc.length; i++) {
                    addressCc[i] = new InternetAddress(cc[i]);
                }
                msg.setRecipients(Message.RecipientType.CC, addressCc);
            }

            // Set the BCC: address(es)
            if (bcc != null) {
                InternetAddress[] addressBcc = new InternetAddress[bcc.length];
                for (int i = 0; i < bcc.length; i++) {
                    addressBcc[i] = new InternetAddress(bcc[i]);
                }
                msg.setRecipients(Message.RecipientType.BCC, addressBcc);
            }

            // Optional custom headers (?)
            //msg.addHeader("MyHeaderName", "myHeaderValue"); //removed

            // Setting the Subject and Content Type
            msg.setSubject(subject);
//            if(null != allPart){
//                msg.setContent(allPart);
//            }else{
//                msg.setContent(msg, type);
//            }
            // Send the e-mail
            Transport.send(msg);
            System.out.println("send mail end!");
        } catch (MessagingException e) {
            String exeption = String.format("java mail exception:smtpHost:{},exception:{}",smtpHost,e.toString());
            System.out.println(exeption);
//            e.printStackTrace();
            throw new MessagingException("java mail exception:smtpHost:"+smtpHost+",exception:"+e.toString());
        }
    }



    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
//			return new PasswordAuthentication(username, password);
            //todo ，去掉公司相关
            return new PasswordAuthentication("data-noreply@126.com", "lalala");
        }
    }
}