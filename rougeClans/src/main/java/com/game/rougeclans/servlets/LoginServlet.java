package com.game.rougeclans.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "login" : request.getParameter("action");

        switch (action){

            case "confirmation":
                request.getRequestDispatcher("pages/sistema/confirmation.jsp").forward(request, response);
                break;

            case "register":
                request.getRequestDispatcher("pages/sistema/register.jsp").forward(request, response);
                break;

            case "login":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "auth" : request.getParameter("action");

        switch (action){

            case "login":
                String usuarioInput = request.getParameter("usuario");
                String contrasenaInput = request.getParameter("contrasena");


                break;

            case "register":
                String nombre = request.getParameter("nombre");
                String edad = request.getParameter("edad");
                String correo = request.getParameter("correo");
                String usuario = request.getParameter("usuario");
                String contrasena = request.getParameter("contrasena");
                String confirm_contra = request.getParameter("confirm_contra");

                response.sendRedirect("login?action=confirmation");
                break;

            case "auth":
                response.sendRedirect("login");
                break;
        }

    }
}

