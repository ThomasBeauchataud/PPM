package servlets.webservices;

import beans.entities.OutTransaction;
import com.github.ffcfalcos.commonwebservice.handler.AbstractWebservice;
import managers.OutTransactionManagerInterface;
import managers.PayPalManagerInterface;
import models.external.ApplicationDaoInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "api-execute-external-payment", urlPatterns = "/api/execute-external-payment")
@SuppressWarnings("unchecked")
public class ExecuteExternalPayment extends AbstractWebservice {

    @Inject
    private OutTransactionManagerInterface outTransactionManager;
    @Inject
    private PayPalManagerInterface payPalManager;
    @Inject
    private ApplicationDaoInterface networkDao;

    @Override
    protected void handleGet(JSONObject jsonRequest, JSONObject jsonResponse) throws Exception {
        jsonResponse.put("message", "This webservice doesn't handle GET request");
    }

    @Override
    protected void handlePost(JSONObject jsonRequest, JSONObject jsonResponse) throws Exception {
        String appName = (String)jsonRequest.get("app");
        String token = (String)jsonRequest.get("token");
        if(networkDao.getToken(appName).equals(token)) {
            JSONArray jsonArray = (JSONArray) jsonRequest.get("transactions");
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                payPalManager.pay((String) jsonObject.get("email"), (double) jsonObject.get("value"));
                outTransactionManager.createOutTransaction(new OutTransaction((double) jsonObject.get("value"), appName, (String) jsonObject.get("email")));
            }
        } else {
            throw new Exception("You need a valid token to execute this command");
        }
    }

}
