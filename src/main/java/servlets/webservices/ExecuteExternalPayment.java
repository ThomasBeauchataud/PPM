package servlets.webservices;

import beans.entities.OutTransaction;
import managers.OutTransactionManagerInterface;
import managers.PayPalManagerInterface;
import models.external.NetworkDaoInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "api-execute-external-payment", urlPatterns = "/api/execute-external-payment")
public class ExecuteExternalPayment extends AbstractWebservice {

    @Inject
    private OutTransactionManagerInterface outTransactionManager;
    @Inject
    private PayPalManagerInterface payPalManager;
    @Inject
    private NetworkDaoInterface networkDao;

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
