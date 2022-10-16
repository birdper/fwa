package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserDto;
import edu.school21.cinema.service.UserService;
import edu.school21.cinema.service.UserServiceImpl;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/signup")
public class SignUp extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
//
//        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(context);

        userService = springContext.getBean(UserService.class);

        if (userService == null) {
            System.out.println("user service is null");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/static/signup.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("email"),
                req.getParameter("phone"),
                req.getParameter("password")
        );
        userService.register(user);

//        resp.setContentType("text/html;charset=windows-1251");
        resp.setContentType("text/html;charset=utf-8");

        Map<String, String[]> parameterNames = req.getParameterMap();
        for (String[] value : parameterNames.values()) {
            resp.getWriter().write(value[0] + "\n");
        }
    }

}
