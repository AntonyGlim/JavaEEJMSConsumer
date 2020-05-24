package glim.antony;

import glim.antony.dto.Message;
import junit.framework.TestCase;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConsumerServletUnmarshallMessageFromXmlTest extends TestCase {

    ConsumerServlet consumerServlet;
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd"); //2020-05-23

    @Override
    protected void setUp() throws Exception {
        consumerServlet = new ConsumerServlet();
    }

    @Override
    protected void tearDown() throws Exception {
        consumerServlet = null;
    }

    public void test1unmarshallMessageFromXml() throws Throwable {
        Method method = consumerServlet.getClass().getDeclaredMethod("unmarshallMessageFromXml", String.class);
        method.setAccessible(true);
        Message message = (Message) method.invoke(consumerServlet, "abcd");
        assertNull(message);
    }

    public void test2unmarshallMessageFromXml() throws Throwable {
        String xml ="" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<message>\n" +
                "    <inputDate>2020-05-22T00:00:00+04:00</inputDate>\n" +
                "    <inputNumber>0</inputNumber>\n" +
                "    <inputText>text_0</inputText>\n" +
                "</message>";
        Message expectMessage = new Message(0L, "text_0", FORMAT.parse("2020-05-22"));

        Method method = consumerServlet.getClass().getDeclaredMethod("unmarshallMessageFromXml", String.class);
        method.setAccessible(true);
        Message message = (Message) method.invoke(consumerServlet, xml);
        assertEquals(expectMessage, message);
    }

    public void test3unmarshallMessageFromXml() throws Throwable {
        String xml ="" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<message>\n" +
                "    <inputNumber>100</inputNumber>\n" +
                "    <inputText>text_8</inputText>\n" +
                "</message>";
        Message expectMessage = new Message(100L, "text_8", null);

        Method method = consumerServlet.getClass().getDeclaredMethod("unmarshallMessageFromXml", String.class);
        method.setAccessible(true);
        Message message = (Message) method.invoke(consumerServlet, xml);
        assertEquals(expectMessage, message);
    }

    public void test4unmarshallMessageFromXml() throws Throwable {
        String xml ="" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<message>\n" +
                "    <inputDate>2021-07-27T00:00:00+04:00</inputDate>\n" +
                "    <inputText>text_2</inputText>\n" +
                "</message>";
        Message expectMessage = new Message(null, "text_2", FORMAT.parse("2021-07-27"));

        Method method = consumerServlet.getClass().getDeclaredMethod("unmarshallMessageFromXml", String.class);
        method.setAccessible(true);
        Message message = (Message) method.invoke(consumerServlet, xml);
        assertEquals(expectMessage, message);
    }

    public void test5unmarshallMessageFromXml() throws Throwable {
        String xml ="" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<message>\n" +
                "    <inputText>text_3</inputText>\n" +
                "</message>";
        Message expectMessage = new Message(null, "text_3", null);

        Method method = consumerServlet.getClass().getDeclaredMethod("unmarshallMessageFromXml", String.class);
        method.setAccessible(true);
        Message message = (Message) method.invoke(consumerServlet, xml);
        assertEquals(expectMessage, message);
    }

}