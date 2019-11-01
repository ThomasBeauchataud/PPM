package common.logger.formatter;

import org.json.simple.JSONObject;

import java.util.List;

public interface LogDaoFormatterInterface extends LogFormatterInterface {

    void addRequest(List<JSONObject> logContent, String request);

    void addResponse(List<JSONObject> logContent, Object response);

}
