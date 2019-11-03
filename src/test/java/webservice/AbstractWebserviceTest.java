package webservice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

abstract class AbstractWebserviceTest {

    static String sendPost(String url, String jsonRequest) throws Exception {
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(jsonRequest);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

}
