package common.logger.formatter;

import org.apache.http.Header;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.List;

@Default
@ApplicationScoped
@SuppressWarnings("unchecked")
public class LogWebserviceCallFormatter extends LogFormatter implements LogWebserviceCallFormatterInterface {

    @Override
    public void addRequest(List<JSONObject> logContent, String url, String request, Header[] headers, String method) {
        add(logContent, "url", url);
        add(logContent, "content", request);
        add(logContent, "method", method);
        this.addHeaders(logContent, headers);
    }

    @Override
    public void addResponse(List<JSONObject> logContent, String response) {
        add(logContent, "response", response);
    }

    private void addHeaders(List<JSONObject> logContent, Header[] headers) {
        JSONArray jsonArray = new JSONArray();
        for(Header header : headers) {
            jsonArray.add(header.getName() + ": " + header.getValue());
        }
        add(logContent, "headers", jsonArray);
    }

}
