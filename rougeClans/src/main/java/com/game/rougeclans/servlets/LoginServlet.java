package com.game.rougeclans.servlets;

import com.game.rougeclans.model.beans.Jugador;
import com.game.rougeclans.model.daos.JugadorDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    JugadorDao jugadorDao = new JugadorDao();

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

                if (jugadorDao.login(usuarioInput, contrasenaInput)){

                    Jugador jugador = jugadorDao.obtenerJugadorUsuario(usuarioInput);
                    HttpSession session = request.getSession();

                    session.setAttribute("jugador", jugador);

                    session.setMaxInactiveInterval(1800); // 1800 segundos = 30 minutos

                    response.sendRedirect("/rougeClans/game");
                }else{
                    response.sendRedirect("login?action=login");
                }


                break;

            case "register":
                String nombre = request.getParameter("nombre");
                String edad = request.getParameter("edad");
                String correo = request.getParameter("correo");
                String usuario = request.getParameter("usuario");
                String contrasena = request.getParameter("contrasena");

                jugadorDao.crearJugador(nombre, edad, correo, usuario, contrasena);

                response.sendRedirect("login?action=confirmation");
                break;

            case "auth":
                response.sendRedirect("login?action=game");
                break;
        }

    }
}

