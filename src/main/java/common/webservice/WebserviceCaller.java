package common.webservice;

import common.logger.formatter.LogType;
import common.logger.formatter.LogWebserviceCallFormatterInterface;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Default
@ApplicationScoped
public class WebserviceCaller implements WebserviceCallerInterface {

    @Inject
    private LogWebserviceCallFormatterInterface logWebserviceCallFormatter;

    @Override
    public String sendPost(String url, String jsonRequest) throws Exception {
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(jsonRequest);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        return executePost(request);
    }

    private String executePost(HttpPost request) throws IOException {
        List<JSONObject> logContent = logWebserviceCallFormatter.init(LogType.WEBSERVICE_CALL);
        logWebserviceCallFormatter.addRequest(
                logContent,
                request.getURI().getQuery(),
                EntityUtils.toString(request.getEntity(), "UTF-8"),
                request.getAllHeaders(),
                request.getMethod()
        );
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(request);
            String responseEntity = EntityUtils.toString(response.getEntity(), "UTF-8");
            logWebserviceCallFormatter.addResponse(logContent, responseEntity);
            return responseEntity;
        } catch (Exception e) {
            logWebserviceCallFormatter.addException(logContent, e);
            throw e;
        } finally {
            logWebserviceCallFormatter.close(logContent);
        }
    }

}
