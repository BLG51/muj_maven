package com.company.servlet;

import com.company.model.User;
import com.company.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    UserService us;

    @Override
    public void init() throws ServletException {
        us = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("autorized", "false");
        Cookie[] carr = req.getCookies();
        Cookie c = null;
        for (Cookie item : carr) {
            if (item.getName().equals("auth")) {
                c = item;
            }
        }
        if (c != null) {
            if (!c.getValue().equals("false")) ;
            {
                req.getSession().setAttribute("autorized", c.getValue());
            }
        }

        if (req.getSession().getAttribute("authorized").equals("false")) {
            req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            //
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("email"));

        resp.addCookie(new Cookie("auth", "false"));

        if (us.isRegistered(user.getEmail())) {
            User userdb = us.get(user.getEmail());
            if (user.getPassword().equals(userdb.getPassword())) ;
            req.getSession().setAttribute("autorized", user.getEmail());

            resp.addCookie(new Cookie("auth", user.getEmail()));
        }

    }

}
