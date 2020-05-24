# JavaEEJMSConsumer
Java EE 5 JMS Consumer (EXAMPLE)

**Приложение**  
Вычитывает сообщения из JMS очереди и отображать полученные данные на отдельной странице в табличном виде с сортировкой по дате.  
Реализация ограничена стеком Java EE 5.  
![Форма ввода значений](https://github.com/AntonyGlim/JavaEEJMSConsumer/blob/master/about/page.png)  
В методе doGet() данные вычитываются из jms очереди, десериализуются из XML, добавляются а List и сортируются по дате, 
после чего отображаются на странице  
```java
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xml = receiveMessageFromJmsQueue();
        Message message = unmarshallMessageFromXml(xml);
        if (message != null){
            messages.add(message);
            messagesSortByDate(messages);
        }
        printMessagesOnPage(response.getWriter());
    }
```
![Интерфейс activemq](https://github.com/AntonyGlim/JavaEEJMSConsumer/blob/master/about/activemq.png)  
В качестве сервера сервера jms сообщений выступает apache-activemq-5.15.12.  
Параметры подключения:  
```java
  URL = ActiveMQConnection.DEFAULT_BROKER_URL;
  JMS_QUEUE_NAME = "MyJmsQueueName";
```
**Тестирование:**  
Тесты покрывают десерриализацию сообщений из XML и проверку корректности сортировки.
