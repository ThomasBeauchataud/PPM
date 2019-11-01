package common.logger.formatter;

import org.apache.http.Header;
import org.json.simple.JSONObject;

import java.util.List;

public interface LogWebserviceCallFormatterInterface extends LogFormatterInterface {

    void addRequest(List<JSONObject> logContent, String url, String request, Header[] headers, String method);

    void addResponse(List<JSONObject> logContent, String response);

}
