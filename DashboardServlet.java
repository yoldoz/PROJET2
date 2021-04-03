package com.ensta.librarymanager.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;

import java.io.IOException;

public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showAddForm(request, response);
                break;
            case "/insert":
                insert(request, response);
                break;
            case "/delete":
                delete(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                update(request, response);
                break;
            case "/list":
                showAllFilm(request, response);
                break;
            case "/listJSTL":
                showAllFilmJSTL(request, response);
                break;
            case "/index":
                showPublicIndex(request, response);
                break;
            case "/pindex":
                showPrivateIndex(request, response);
                break;
            default:
                System.out.println("Default redirecting case from " + action + " !");
                showAllFilm(request, response);
        }
    }
}
