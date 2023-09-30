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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            RequestDispatcher dispatcher = req.getRequestDispatcher("./login.jsp");
            dispatcher.forward(req, resp);
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./user/hello.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Users.getInstance().getUsers().contains(req.getParameter("login")) && !req.getParameter("password").isEmpty()){
            req.getSession().setAttribute("user", req.getParameter("login"));
            resp.sendRedirect("./user/hello.jsp");
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./login.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
