package managers;

import beans.common.SessionManagementInterface;
import com.github.ffcfalcos.commonwebservice.caller.WebserviceCaller;
import com.github.ffcfalcos.commonwebservice.caller.WebserviceCallerInterface;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@ApplicationScoped
@SuppressWarnings("unchecked")
public class AppNotifier implements AppNotifierInterface {

    @Inject
    private SessionManagementInterface sessionManagement;
    @Inject
    private WebserviceCallerInterface webserviceCaller;

    @Override
    public void notifyApp() {
        try {
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("id", sessionManagement.getId());
            final String url = sessionManagement.getApi();
            webserviceCaller.sendPost(url, jsonRequest.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
