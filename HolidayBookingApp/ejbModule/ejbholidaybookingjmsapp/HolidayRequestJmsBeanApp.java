package ejbholidaybookingjmsapp;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/HolidayRequestQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		},
		mappedName = "java:/jms/HolidayRequestQueue")
public class HolidayRequestJmsBeanApp implements MessageListener {

    public HolidayRequestJmsBeanApp() {
    }

    public void onMessage(Message message) {
    	System.out.println("Message received by MDB");
    	try
        {
            MapMessage calculationMsg = (MapMessage) message;
          
            int x = calculationMsg.getInt("x");
            int y = calculationMsg.getInt("y");
            System.out.println("x: " + x + " y: " + y);
             
            deliverResult(calculationMsg, x + y);
        } 
        catch(Exception e) 
        {
            e.printStackTrace();;
        }
    }

    public void deliverResult (MapMessage calculationMsg, int result) throws JMSException, NamingException
    {
    	Context jndiContext = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("java:/ConnectionFactory");
		Topic resultTopic =(Topic)jndiContext.lookup("java:/jms/HolidayRequestResultTopic");
        Connection connect = connectionFactory.createConnection();
        Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer sender = session.createProducer(resultTopic);
        TextMessage message = session.createTextMessage();
        message.setText("Result: " + result);
        sender.send(message);
        connect.close();
    }

}
