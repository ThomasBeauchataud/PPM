package common.logger.formatter;

import org.json.simple.JSONObject;

import java.util.List;

public interface LogWebserviceFormatterInterface extends LogFormatterInterface {

    void addRequest(List<JSONObject> logContent, String url, String request, String method);

    void addResponse(List<JSONObject> logContent, String response);

}
