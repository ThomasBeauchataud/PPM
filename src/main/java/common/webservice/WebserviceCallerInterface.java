package common.webservice;

public interface WebserviceCallerInterface {

    String sendPost(String url, String jsonRequest) throws Exception;

}
