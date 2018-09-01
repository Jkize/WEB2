/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Curso;
import dao.DaoCurso;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LabingXEON
 */
public class controlador extends HttpServlet {

    DaoCurso daocurso;

    //es el constructor del Servlet
    @Override
    public void init() throws ServletException {
        try {
            this.daocurso = new DaoCurso();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Capturar los parametros.
        // nombre--> el name de la clase donde está el login.
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = (request.getParameter("nombre") + "                  ").substring(0, 10);
        //Validaciones - SQL Inyection.

        if (id != 0 && nombre.length() > 0) {
            Curso curso = new Curso(id, nombre);
            this.daocurso.crear(curso);
            ArrayList<Curso> cursos = this.daocurso.obtenercursos();
//request enviar páginas de una a otra.
            // Es necesario para enviar la lista al index.josp, enviar datos de una página a otra.
            RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
            request.setAttribute("lista", cursos);

            rq.forward(request, response);

        } else {
            //si no se captura bien los datos
            response.sendRedirect("index.jsp?error=IngreseDatos");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
