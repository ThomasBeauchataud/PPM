package servlets;

import beans.common.SessionManagementInterface;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "home", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    @Inject
    private SessionManagementInterface sessionManagement;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sessionManagement.setAppName(request.getParameter("app"));
        sessionManagement.setId(Integer.parseInt(request.getParameter("id")));
        sessionManagement.setRedirect(request.getParameter("redirect"));
        sessionManagement.setValue(Double.parseDouble(request.getParameter("value")));
        sessionManagement.setApi(request.getParameter("api"));
        response.sendRedirect("payment/index.xhtml");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
