package servlets.webservices;

import managers.IncTransactionManagerInterface;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "api-consult-internal-payment", urlPatterns = "/api/consult-internal-payment")
@SuppressWarnings("unchecked")
public class ConsultInternalPayment extends AbstractWebservice {

    @Inject
    private IncTransactionManagerInterface incTransactionManager;

    @Override
    protected void handlePost(JSONObject jsonRequest, JSONObject jsonResponse) throws Exception {
        String appName = (String)jsonRequest.get("app");
        int id = (int) ((long) jsonRequest.get("id"));
        jsonResponse.put("paid", incTransactionManager.isPaidIncTransaction(appName, id));
    }

}
