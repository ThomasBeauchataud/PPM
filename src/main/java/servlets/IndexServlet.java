package servlets;

import beans.common.NavigationController;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "index", urlPatterns = "")
public class IndexServlet extends HttpServlet {

    @Inject
    private NavigationController navigationController;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        navigationController.setApplicationUrl(request.getRequestURL().toString());
        response.sendRedirect("index.xhtml");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
