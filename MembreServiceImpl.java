package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MembreServiceImpl {

    private static MembreServiceImpl instance = new MembreServiceImpl();
    private MembreServiceImpl() { }
    public static MembreService getInstance() {
        return instance;
    }


    List<Membre> getList() throws ServiceException{
        MembreDao membreDao = MembreDaoImpl.getInstance();
        List<Membre> membres = new ArrayList<>();
        try {
            membres = membreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return membres;
    }


    public  List<Membre> getListMembreEmpruntPossible() throws ServiceException{

        MembreDao membreDao = MembreDaoImpl.getInstance();
        List<Membre> membres = new ArrayList<>();
        try {
            membres = membreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return membres;
    }

    public Membre getById(int id) throws ServiceException{
        MembreDao membreDao = MembreDaoImpl.getInstance();
        Membre membre;
        try {
            membre = membreDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return membre;
    }

    public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException{
        Membre membre = new Membre();
        MembreDao membreDao = MembreDaoImpl.getInstance();
        int i = -1;
        try {
            if((nom!=null) && (prenom!=null))
                i = membreDao.create(nom.toUpperCase(),prenom, adresse, email, telephone);
        }  catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    }

    public void update(Membre membre) throws DaoException{
        MembreDao filmDao = MembreDaoImpl.getInstance();
        int i = -1;
        try {
            String nom=membre.getNom();
            String prenom=membre.getPrenom();
            if((nom!=null) && (prenom!=null)) {
                membre.setNom(nom.toUpperCase());
                i = LivreDaoImpl.update(membre);
            }
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        } catch (NumberFormatException e2) {
            throw new ServiceException("Erreur lors du parsing: id=" + membre, e2);
        }
    }

    public void delete(int id) throws DaoException{
        MembreDao membreDao = MembreDaoImpl.getInstance();
        int i = -1;
        try {
            Membre membre = membreDao.getById(Integer.parseInt(id));
            i = MembreDaoImpl.delete(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        } catch (NumberFormatException e2) {
            throw new ServiceException("Erreur lors du parsing: id=" + id, e2);
        }
        return i;
    }

    public int count() throws DaoException{

        MembreDao membreDao = MembreDaoImpl.getInstance();
        List<Membre> membres = new ArrayList<>();
        int i=0;
        try {
            i = membreDao.count();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    }
}
