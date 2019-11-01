package beans.common;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;

@Named
@ApplicationScoped
public class ApplicationManagement {

    private String ppUrl;
    private String ppToken;
    private String ppEmail;

    @PostConstruct
    public void init() {
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            ppUrl = (String) env.lookup("pp-url");
            ppEmail = (String) env.lookup("pp-email");
            ppToken = (String) env.lookup("pp-token");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPpUrl() {
        return ppUrl;
    }

    public String getPpToken() {
        return ppToken;
    }

    public String getPpEmail() {
        return ppEmail;
    }
}
