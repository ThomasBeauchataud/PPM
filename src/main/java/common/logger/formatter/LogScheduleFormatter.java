package common.logger.formatter;

import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.List;

@Default
@ApplicationScoped
public class LogScheduleFormatter extends LogFormatter implements LogScheduleFormatterInterface {


    @Override
    public void start(List<JSONObject> logContent, String scheduleName) {
        add(logContent, "request", "Starting Schedule " + scheduleName);
    }

    @Override
    public void end(List<JSONObject> logContent) {
        add(logContent, "response", "Success of the execution of the Schedule");
    }

}
