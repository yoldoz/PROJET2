package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class EmpruntServiceImpl implements EmpruntService{

    private static EmpruntServiceImpl instance = new EmpruntServiceImpl();
    private EmpruntServiceImpl() { }
    public static EmpruntService getInstance() {
        return instance;
    }

    public List<Emprunt> getList() throws ServiceException{
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    }

    public List<Emprunt> getListCurrent() throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrent();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    }

    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByMembre(idMembre);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    }

    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    }

    public Emprunt getById(int id) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt = new Emprunt();
        try {
            emprunt = empruntDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunt;
    }

    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt = new Emprunt();
        try {
            empruntDao.create(idMembre, idLivre, dateEmprunt);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void returnBook(int id) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt= new Emprunt();
        try {
            emprunt=empruntDao.getById(id);
            emprunt.setDateRetour(LocalDate.now());
            empruntDao.update(emprunt);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    }

    public int count() throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        int i=0;
        try {
            i = empruntDao.count();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    }

    public boolean isLivreDispo(int idLivre) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt = new Emprunt();
        boolean b=true;
        try {
            emprunt = empruntDao.getById(idLivre);
            if (emprunt.getDateRetour().isBefore(LocalDate.now()))
                b=false;
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return b;
    }

    /*public boolean isEmpruntPossible(Membre membre) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt= new Emprunt();
        try {
            emprunt=empruntDao.getById(id);
            emprunt.setDateRetour(LocalDate.now());
            empruntDao.update(emprunt);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    }*/

}
