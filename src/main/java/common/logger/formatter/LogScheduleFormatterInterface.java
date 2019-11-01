package common.logger.formatter;

import org.json.simple.JSONObject;

import java.util.List;

public interface LogScheduleFormatterInterface extends LogFormatterInterface {

    void start(List<JSONObject> logContent, String scheduleName);

    void end(List<JSONObject> logContent);

}
