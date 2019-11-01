package servlets;

import beans.common.SessionManagementInterface;
import beans.entities.IncTransaction;
import managers.AppNotifierInterface;
import managers.IncTransactionManagerInterface;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirmation", urlPatterns = "/confirmation")
public class ConfirmationServlet extends HttpServlet {

    @Inject
    private SessionManagementInterface sessionManagement;
    @Inject
    private IncTransactionManagerInterface incTransactionManager;
    @Inject
    private AppNotifierInterface appNotifier;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        incTransactionManager.createIncTransaction(new IncTransaction(
                sessionManagement.getValue(),
                sessionManagement.getAppName(),
                sessionManagement.getId()
        ));
        appNotifier.notifyApp();
        response.sendRedirect(sessionManagement.getRedirect());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
