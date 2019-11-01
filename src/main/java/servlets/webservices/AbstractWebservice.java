package servlets.webservices;

import common.logger.formatter.LogType;
import common.logger.formatter.LogWebserviceFormatterInterface;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractWebservice extends HttpServlet {

    @Inject
    private LogWebserviceFormatterInterface logWebserviceFormatter;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<JSONObject> logContent = logWebserviceFormatter.init(LogType.WEBSERVICE);
        logWebserviceFormatter.addRequest(logContent, request.getRequestURI(), this.getBody(request), "GET");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("returnCode", 1);
        jsonResponse.put("message", "This webservice doesn't handle get requests");
        logWebserviceFormatter.addResponse(logContent, jsonResponse.toJSONString());
        logWebserviceFormatter.close(logContent);
        this.sendJsonResponse(response, jsonResponse);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        List<JSONObject> logContent = logWebserviceFormatter.init(LogType.WEBSERVICE);
        JSONObject jsonResponse = new JSONObject();
        try {
            String body = this.getBody(request);
            logWebserviceFormatter.addRequest(logContent, request.getRequestURI(), body, "POST");
            if (body.equals("")) {
                throw new Exception("Missing parameter with all his content");
            }
            JSONObject jsonRequest = (JSONObject) new JSONParser().parse(body);
            handlePost(jsonRequest, jsonResponse);
            jsonResponse.put("returnCode", 0);
        } catch (Exception e) {
            jsonResponse.put("returnCode", 1);
            jsonResponse.put("message", e.getMessage());
            jsonResponse.put("cause", e.getCause().toString());
        }
        logWebserviceFormatter.addResponse(logContent, jsonResponse.toJSONString());
        logWebserviceFormatter.close(logContent);
        this.sendJsonResponse(response, jsonResponse);
    }

    protected abstract void handlePost (JSONObject jsonRequest, JSONObject jsonResponse) throws Exception;

    private void sendJsonResponse(HttpServletResponse response, JSONObject jsonResponse) {
        try {
            PrintWriter output = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            output.print(jsonResponse);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBody(HttpServletRequest request) {
        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }
}
