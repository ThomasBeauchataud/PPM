package common.logger.formatter;

import org.json.simple.JSONObject;

import java.util.List;

interface LogFormatterInterface {

    List<JSONObject> init(LogType logType);

    void addException(List<JSONObject> logContent, Exception e);

    void close(List<JSONObject> logContent);

}
