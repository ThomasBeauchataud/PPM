package common.logger.formatter;

import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.List;

@Default
@ApplicationScoped
public class LogDaoFormatter extends LogFormatter implements LogDaoFormatterInterface {

    @Override
    public void addRequest(List<JSONObject> logContent, String request) {
        add(logContent,"request", request);
    }

    @Override
    public void addResponse(List<JSONObject> logContent, Object response) {
        add(logContent,"response", response);
    }

}
