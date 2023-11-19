package com.game.rougeclans.servlets;

import com.game.rougeclans.model.Dtos.PersonaEnLista;
import com.game.rougeclans.model.Dtos.Top10Jugadores;
import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Jugador;
import com.game.rougeclans.model.beans.Persona;
import com.game.rougeclans.model.daos.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ControllerServlet", value = "/game")
public class ControllerServlet extends HttpServlet {

    CivilizacionDao civilizacionDao = new CivilizacionDao();
    JugadorDao jugadorDao = new JugadorDao();
    GuerraDao guerraDao = new GuerraDao();
    Top10JugadoresDao top10JugadoresDao = new Top10JugadoresDao();
    HistorialGuerrasDao historialGuerrasDao = new HistorialGuerrasDao();
    PersonaDao personaDao = new PersonaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "home" : request.getParameter("action");

        HttpSession session = request.getSession(false);
        Jugador jugador = (Jugador) session.getAttribute("jugador");
        Civilizacion civilizacion = (Civilizacion) session.getAttribute("civilizacion");

        switch (action){

            case "personas":
                ArrayList<PersonaEnLista> listaPersonas = personaDao.listaPersonasXCivilizacion(civilizacion);
                request.setAttribute("listaPersonas", listaPersonas);
                request.getRequestDispatcher("pages/usuario/gestion_personas/personas.jsp").forward(request, response);
                break;

            case "create_person":
                request.getRequestDispatcher("pages/usuario/gestion_personas/new_persona.jsp").forward(request, response);
                break;

            case "edit_person":
                String id = request.getParameter("id") == null? "home" : request.getParameter("id");
                Persona persona = personaDao.obtenerPersona(Integer.parseInt(id));
                request.setAttribute("persona",persona);
                request.getRequestDispatcher("pages/usuario/gestion_personas/edit_persona.jsp").forward(request, response);
                break;

            case "del_person":
                String idP = request.getParameter("id") == null? "home" : request.getParameter("id");
                personaDao.exiliarPersona(Integer.parseInt(idP), civilizacion.getIdCivilizacion());
                ArrayList<PersonaEnLista> listaPersonasN = personaDao.listaPersonasXCivilizacion(civilizacion);
                request.setAttribute("listaPersonas", listaPersonasN);
                request.getRequestDispatcher("pages/usuario/gestion_personas/personas.jsp").forward(request, response);
                break;

            case "recursos":
                request.getRequestDispatcher("pages/usuario/gestion_recursos/recursos.jsp").forward(request, response);
                break;

            case "pass_hours":
                civilizacionDao.pasarLasHoras(civilizacion.getIdCivilizacion());
                session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
                request.getRequestDispatcher("pages/usuario/gestion_recursos/recursos.jsp").forward(request, response);
                break;

            case "finish_day":
                civilizacionDao.actualizarTimeAndDaysElapsed(civilizacion.getIdCivilizacion());
                session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
                request.getRequestDispatcher("pages/usuario/gestion_recursos/recursos.jsp").forward(request, response);
                break;

            case "guerra":
                request.setAttribute("historial", historialGuerrasDao.listarHistorial(civilizacion.getIdCivilizacion()));
                request.setAttribute("oponentes", civilizacionDao.listarCivilizaciones(civilizacion.getIdCivilizacion()));
                if(!historialGuerrasDao.listarHistorial(civilizacion.getIdCivilizacion()).isEmpty()){
                    request.setAttribute("puesto", top10JugadoresDao.obtenerPuesto(historialGuerrasDao.listarHistorial(civilizacion.getIdCivilizacion()).get(0).getCivilizacionOponente().getIdCivilizacion(),"fuerza_total"));
                }
                else{
                    request.setAttribute("puesto",0);
                }
                request.getRequestDispatcher("pages/usuario/gestion_guerra/guerra.jsp").forward(request, response);
                break;

            case "leaderboard":
                String orderBy = request.getParameter("order_by") == null ? "dias_jugados" : request.getParameter("order_by");
                request.setAttribute("top10", top10JugadoresDao.listarTop10(orderBy));
                //request.setAttribute("top10", top10JugadoresDao.listarTop10(orderBy));
                session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
                request.getRequestDispatcher("pages/usuario/leaderboard/leaderboard.jsp").forward(request, response);
                break;

            case "home":

                if (session.getAttribute("jugador") != null){
                    /* Obtención de Estadísticas de la Civilización Logueada */
                    int poblacionTotal = civilizacionDao.obtenerPoblacionTotal(civilizacion.getIdCivilizacion());
                    int moralTotal = civilizacionDao.obtenerMoralTotalCivilizacion(civilizacion.getIdCivilizacion());
                    int guerrasGanadas = guerraDao.calcularGuerrasGanadas(civilizacion.getIdCivilizacion());
                    double winRate = guerraDao.obtenerWinRate(civilizacion.getIdCivilizacion());
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
                    request.setAttribute("puesto", top10JugadoresDao.obtenerPuesto(civilizacion.getIdCivilizacion(),"fuerza_total"));

                    request.setAttribute("top10", top10JugadoresDao.listarTop10("fuerza_total")); //para hallar al usuario más poderoso en fuerza
                    request.setAttribute("ultima_guerra", historialGuerrasDao.obtenerUltimaGuerra(civilizacion.getIdCivilizacion()));
                    if(historialGuerrasDao.obtenerUltimaGuerra(civilizacion.getIdCivilizacion()).getResultado()!=null){
                        request.setAttribute("puesto_oponente", top10JugadoresDao.obtenerPuesto(historialGuerrasDao.obtenerUltimaGuerra(civilizacion.getIdCivilizacion()).getCivilizacionOponente().getIdCivilizacion(),"fuerza_total"));
                    }
                    else{
                        request.setAttribute("puesto_oponente",0);
                    }

                    session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
                    request.getRequestDispatcher("pages/usuario/inicio/civilizacion.jsp").forward(request, response);

                } else {

                    session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
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
            case "create_per":
                String profesion = request.getParameter("profesion");
                String genero = request.getParameter("genero");
                String nombre = request.getParameter("nombre");

                personaDao.crearPersona(civilizacion.getIdCivilizacion(), genero, nombre, profesion);
                session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
                response.sendRedirect("game?action=personas");
                break;

            case "edit_per":
                String id = request.getParameter("id") == null? "home" : request.getParameter("id");
                String nuevoNombre = request.getParameter("nombre");
                personaDao.editarPersona(Integer.parseInt(id), nuevoNombre);
                session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
                response.sendRedirect("game?action=personas");
                break;

            case "declarar_guerra":
                int idAtacante = Integer.parseInt(request.getParameter("idAtacante"));
                int idDefensor = Integer.parseInt(request.getParameter("idDefensor"));
                guerraDao.calcularGanador(idAtacante,idDefensor);
                guerraDao.asignarPuntajes(guerraDao.obtenerUltimoIdGuerra());
                session.setAttribute("civilizacion",civilizacionDao.obtenerCivilizacion(civilizacion.getIdCivilizacion()));
                response.sendRedirect("game?action=guerra");
                break;


        }

    }
}

