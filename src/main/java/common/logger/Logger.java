package common.logger;

import common.rabbitMQ.RabbitMQManagerInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;

@Default
@ApplicationScoped
public class Logger implements LoggerInterface, Serializable {

    @Inject
    private RabbitMQManagerInterface rabbitMQManager;

    public void log(String content) {
        rabbitMQManager.sendToRabbit(content);
    }

}
