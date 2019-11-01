package common.logger.formatter;

import beans.common.SessionManagement;
import common.logger.LoggerInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
abstract class LogFormatter implements LogFormatterInterface {

    @Inject
    private LoggerInterface logger;
    @Inject
    private SessionManagement sessionManagement;

    @Override
    public List<JSONObject> init(LogType logType) {
        List<JSONObject> logContent = Arrays.asList(new JSONObject(), new JSONObject(), new JSONObject());
        logContent.get(0).put("type", logType.name());
        logContent.get(0).put("start", new Date().getTime());
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            logContent.get(2).put("application", env.lookup("application-name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logContent.get(0).put("error", false);
        logContent.get(1).put("user", "SYSTEM");
        if(sessionManagement.getAppName() != null) {
            logContent.get(1).put("app-client", sessionManagement.getAppName());
            logContent.get(1).put("app-client-transaction-id", sessionManagement.getId());
        }
        return logContent;
    }

    void add(List<JSONObject> logContent, String key, Object content) {
        if(content instanceof List) {
            JSONArray jsonArray = new JSONArray();
            for(Object object : (List)content) {
                jsonArray.add(object.toString());
            }
            logContent.get(0).put(key, jsonArray);
        } else {
            logContent.get(0).put(key, content);
        }
    }

    @Override
    public void addException(List<JSONObject> logContent, Exception e) {
        logContent.get(0).put("error", true);
        logContent.get(0).put("message", e.getMessage());
        logContent.get(0).put("cause", e.getCause());
    }

    @Override
    public void close(List<JSONObject> logContent) {
        logContent.get(0).put("end", new Date().getTime());
        logContent.get(0).put("time", (long) logContent.get(0).get("end") - (long) logContent.get(0).get("start"));
        logContent.get(0).put("ctx", logContent.get(1));
        logContent.get(0).put("env", logContent.get(2));
        logger.log(logContent.get(0).toJSONString());
    }

}
