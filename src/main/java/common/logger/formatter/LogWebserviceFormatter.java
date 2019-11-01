package common.logger.formatter;

import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.List;

@Default
@ApplicationScoped
public class LogWebserviceFormatter extends LogFormatter implements LogWebserviceFormatterInterface {

    @Override
    public void addRequest(List<JSONObject> logContent, String url, String request, String method) {
        add(logContent, "url", url);
        add(logContent, "content", request);
        add(logContent, "method", method);
    }

    @Override
    public void addResponse(List<JSONObject> logContent, String response) {
        add(logContent, "response", response);
    }

}
