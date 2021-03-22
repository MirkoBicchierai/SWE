package agentManager;

import org.javatuples.Pair;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public final class NotificationEmail implements Observer{

    @Override
    public void update(Object obj) {
        Order o = (Order)obj;
        String to = "";
        for (User u : Program.getInstance().getUsers()){
            if(u instanceof Administrator)
                to += u.getEmail() + ",";
        }
        to = to.substring(0, to.length() - 1);
        
        String products="";
        for (Pair<Article, Integer> a : o.getRows()){
            products += "--"+a.getValue0().getName()+" qta: "+a.getValue1()+" <br>";
        }
        
        String text;
        text = "<html><meta charset='UTF-8'>" +
                "<p style='line-height: 2em; font-size: 16px; font-family: Calibri;'>" +
                "We require your attention regarding the following order: <br>" +
                "<table style='width:100%;'>" +
                "   <tbody>" +
                "      <tr style='background:#f5f2f2'>" +
                "          <td style='font-weight: bold; width:20%'>Order number:</td>" +
                "          <td>"+o.getId()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#e1e1e1'>" +
                "          <td style='font-weight: bold; width:20%'>Agent:</td>" +
                "          <td>"+o.getAgent().getName()+" -- "+o.getAgent().getEmail()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#f5f2f2'>" +
                "          <td style='font-weight: bold; width:20%'>Customer:</td>" +
                "          <td>"+o.getClient().getBusinessName()+" -- "+o.getClient().getEmail()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#e1e1e1'>" +
                "          <td style='font-weight: bold; width:20%'>Total:</td>" +
                "          <td>"+o.getTotal()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#f5f2f2'>" +
                "          <td style='font-weight: bold; width:20%'>Commission:</td>" +
                "          <td>"+o.getCommissionTot()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#e1e1e1'>" +
                "          <td style='font-weight: bold; width:20%'>Products:</td>" +
                "          <td>"+products+"</td>" +
                "      </tr>" +
                "    </tbody>" +
                "</table>" +
                "</p>" +
                "</html>";
        sendEmail(to, "A new order has been issued!", text);
        text = "<html><meta charset='UTF-8'>" +
                "<p style='line-height: 2em; font-size: 16px; font-family: Calibri;'>" +
                "We require your attention regarding the following order: <br>" +
                "<table style='width:100%;'>" +
                "   <tbody>" +
                "      <tr style='background:#f5f2f2'>" +
                "          <td style='font-weight: bold; width:20%'>Order number:</td>" +
                "          <td>"+o.getId()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#e1e1e1'>" +
                "          <td style='font-weight: bold; width:20%'>Agent:</td>" +
                "          <td>"+o.getAgent().getName()+" -- "+o.getAgent().getEmail()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#f5f2f2'>" +
                "          <td style='font-weight: bold; width:20%'>Customer:</td>" +
                "          <td>"+o.getClient().getBusinessName()+" -- "+o.getClient().getEmail()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#e1e1e1'>" +
                "          <td style='font-weight: bold; width:20%'>Total:</td>" +
                "          <td>"+o.getTotal()+"</td>" +
                "      </tr>" +
                "      <tr style='background:#f5f2f2'>" +
                "          <td style='font-weight: bold; width:20%'>Products:</td>" +
                "          <td>"+products+"</td>" +
                "      </tr>" +
                "    </tbody>" +
                "</table>" +
                "</p>" +
                "</html>";
        sendEmail(o.getClient().getEmail(), "Your order has been taken over!", text);

    }

    private void sendEmail(String to, String obj, String text){

        final String username = "mirkomacaluso.swe@gmail.com";
        final String password = "SWEMirkoMacaluso";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(obj);
            message.setContent(text, "text/html");

            Transport trans = session.getTransport("smtp");
            trans.connect("smtp.gmail.com", 465, "mirkomacaluso.swe@gmail.com", "SWEMirkoMacaluso");
            trans.sendMessage(message, message.getAllRecipients());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
