package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            RequestDispatcher dispatcher = null;
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect("/login.jsp");

            } else {
                resp.sendRedirect("/user/hello.jsp");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (Users.getInstance().getUsers().contains(login) && !password.isEmpty()) {
                HttpSession session = req.getSession();
                session.setAttribute("user", login);
                resp.sendRedirect("/user/hello.jsp");

            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
