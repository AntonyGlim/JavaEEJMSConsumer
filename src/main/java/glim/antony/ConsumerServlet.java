package glim.antony;

import glim.antony.dto.Message;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ConsumerServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(ConsumerServlet.class.getName());
    private List<Message> messages = new ArrayList<Message>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xml = receiveMessageFromJmsQueue();
        Message message = unmarshallMessageFromXml(xml);
        if (message != null){
            messages.add(message);
            messagesSortByDate(messages);
        }
        printMessagesOnPage(response.getWriter());
    }

    private String receiveMessageFromJmsQueue() {
        String xml = null;
        try {
            xml = MessageReceiver.receive();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return xml;
    }

    private Message unmarshallMessageFromXml(String xml) {
        Message message = null;
        StringReader reader = new StringReader(xml);
        try {
            JAXBContext context = JAXBContext.newInstance(Message.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            message = (Message) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            log.warning("Exception cause message: " + e.getCause().getMessage());
            log.warning("Exception message: " + e.getMessage());
        } finally {
            reader.close();
        }
        log.info(" [i] message: " + (message == null ? null : message.toString()));
        return message;
    }

    private void messagesSortByDate(List<Message> messages){
        Collections.sort(messages);
    }

    private void printMessagesOnPage(PrintWriter pw){
        pw.println("<html>");
        pw.println("<table>\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th>number</th>\n" +
                "        <th>text</th>\n" +
                "        <th>date</th>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n");

        for (Message message : messages) {
            pw.println("    <tr>\n");
            pw.println("        <td>"+ message.getInputNumber() + "</td>\n");
            pw.println("        <td>"+ message.getInputText() + "</td>\n");
            pw.println("        <td>"+ message.getInputDate() + "</td>\n");
            pw.println("    </tr>\n");

        }
        pw.println("    </tbody>\n" +
                "</table>");
        pw.println("</html>");
    }
}
