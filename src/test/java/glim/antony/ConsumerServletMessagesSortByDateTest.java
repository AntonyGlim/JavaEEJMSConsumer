package glim.antony;

import glim.antony.dto.Message;
import junit.framework.TestCase;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ConsumerServletMessagesSortByDateTest extends TestCase {

    private ConsumerServlet consumerServlet;
    private List<Message> expectMessages;
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd"); //2020-05-23

    @Override
    protected void setUp() throws Exception {
        consumerServlet = new ConsumerServlet();
        expectMessages = new ArrayList<Message>();
    }

    @Override
    protected void tearDown() throws Exception {
        consumerServlet = null;
        expectMessages = null;
    }

    public void test1messagesSortByDate() throws Throwable {

        expectMessages.add(new Message(-76847L, "111", FORMAT.parse("2020-05-01")));
        expectMessages.add(new Message(6765742L, "22", FORMAT.parse("2020-05-02")));
        expectMessages.add(new Message(3L, "333333", FORMAT.parse("2020-05-03")));
        expectMessages.add(new Message(44L, "44", FORMAT.parse("2020-05-04")));
        expectMessages.add(new Message(-67238765L, "-5", FORMAT.parse("2020-05-05")));

        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(44L, "44", FORMAT.parse("2020-05-04")));
        messages.add(new Message(6765742L, "22", FORMAT.parse("2020-05-02")));
        messages.add(new Message(-67238765L, "-5", FORMAT.parse("2020-05-05")));
        messages.add(new Message(3L, "333333", FORMAT.parse("2020-05-03")));
        messages.add(new Message(-76847L, "111", FORMAT.parse("2020-05-01")));

        Method method = consumerServlet.getClass().getDeclaredMethod("messagesSortByDate", List.class);
        method.setAccessible(true);
        method.invoke(consumerServlet, messages);
        assertEquals(expectMessages, messages);
    }

}