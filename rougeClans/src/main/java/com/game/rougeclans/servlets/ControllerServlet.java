package com.game.rougeclans.servlets;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Jugador;
import com.game.rougeclans.model.daos.CivilizacionDao;
import com.game.rougeclans.model.daos.GuerraDao;
import com.game.rougeclans.model.daos.JugadorDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/game")
public class ControllerServlet extends HttpServlet {

    CivilizacionDao civilizacionDao = new CivilizacionDao();
    JugadorDao jugadorDao = new JugadorDao();
    GuerraDao guerraDao = new GuerraDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "home" : request.getParameter("action");

        HttpSession session = request.getSession(false);
        Jugador jugador = (Jugador) session.getAttribute("jugador");
        Civilizacion civilizacion = (Civilizacion) session.getAttribute("civilizacion");

        switch (action){

            case "personas":
                request.getRequestDispatcher("pages/usuario/gestion_personas/personas.jsp").forward(request, response);
                break;

            case "create_person":
                request.getRequestDispatcher("pages/usuario/gestion_personas/new_persona.jsp").forward(request, response);
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

                if (session.getAttribute("jugador") != null){

                    /* Obtención de Estadísticas de la Civilización Logueada */
                    int poblacionTotal = civilizacionDao.obtenerPoblacionTotal(civilizacion.getIdCivilizacion());
                    int moralTotal = civilizacionDao.obtenerMoralTotalCivilizacion(civilizacion.getIdCivilizacion());
                    int guerrasGanadas = guerraDao.calcularGuerrasGanadas(civilizacion);
                    double winRate = guerraDao.obtenerWinRate(civilizacion);
                    int fuerzaTotal = civilizacionDao.fuerzaTotalAtacante(civilizacion.getIdCivilizacion());
                    int edadAnciano = civilizacionDao.obtenerAncianoDelPueblo(civilizacion);
                    int produccionAlimento = civilizacionDao.obtenerAlimentoTotal(civilizacion.getIdCivilizacion());

                    request.setAttribute("poblacionTotal", poblacionTotal);
                    request.setAttribute("moralTotal", moralTotal);
                    request.setAttribute("guerrasGanadas", guerrasGanadas);
                    request.setAttribute("winRate", winRate);
                    request.setAttribute("fuerzaTotal", fuerzaTotal);
                    request.setAttribute("edadAnciano", edadAnciano);
                    request.setAttribute("produccionAlimento", produccionAlimento);

                    request.getRequestDispatcher("pages/usuario/inicio/civilizacion.jsp").forward(request, response);

                } else {

                    request.getRequestDispatcher("pages/sistema/no_session.jsp").forward(request, response);
                }




                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "home" : request.getParameter("action");

        HttpSession session = request.getSession(false);
        Jugador jugador = (Jugador) session.getAttribute("jugador");
        Civilizacion civilizacion = (Civilizacion) session.getAttribute("civilizacion");

        switch (action){

            case "home":
                response.sendRedirect("login?action=home");
                break;
        }

    }
}

