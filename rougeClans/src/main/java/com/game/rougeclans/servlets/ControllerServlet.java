package com.game.rougeclans.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/game")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "home" : request.getParameter("action");

        switch (action){

            case "personas":
                request.getRequestDispatcher("pages/usuario/gestion_personas/personas.jsp").forward(request, response);
                break;

            case "recursos":
                request.getRequestDispatcher("pages/usuario/gestion_recursos/recursos.jsp").forward(request, response);
                break;

            case "guerra":
                request.getRequestDispatcher("pages/usuario/gestion_guerra/guerra.jsp").forward(request, response);
                break;

            case "leaderboard":
                request.getRequestDispatcher("pages/usuario/leaderboard/leaderboard.jsp").forward(request, response);
                break;

            case "home":
                request.getRequestDispatcher("pages/usuario/inicio/civilizacion.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

