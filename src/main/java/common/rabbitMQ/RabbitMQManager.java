package common.rabbitMQ;

import org.json.simple.parser.JSONParser;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.Serializable;

@Default
@ApplicationScoped
public class RabbitMQManager implements RabbitMQManagerInterface, Serializable {

    @Override
    public void sendToRabbit(String content) {
        if(content != null && isJSONValid(content)) {
            try {
                Context env = (Context)new InitialContext().lookup("java:comp/env");
                new Thread(new RabbitMQThread(
                        (String) env.lookup("rabbitMQ-host"),
                        (String) env.lookup("rabbitMQ-user"),
                        (String) env.lookup("rabbitMQ-password"),
                        (String) env.lookup("rabbitMQ-exchange-logger"),
                        (String) env.lookup("rabbitMQ-routingKey-logger"),
                        content
                )).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isJSONValid(String test) {
        try {
            new JSONParser().parse(test);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
