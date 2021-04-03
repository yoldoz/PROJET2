package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;


import java.util.ArrayList;
import java.util.List;

public class LivreServiceImpl implements LivreService{

    private static LivreServiceImpl instance = new LivreServiceImpl();
    private LivreServiceImpl() { }
    public static LivreService getInstance() {
        return instance;
    }



    public List<Livre> getList() {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        List<Livre> livres = new ArrayList<>();
        try {
            livres = livreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return livres;
    }

    public List<Livre> getListDispo() throws ServiceException{
        LivreDao livreDao = LivreDaoImpl.getInstance();
        List<Livre> livres= new ArrayList<>();
        try {
            livres = livreDao.getLivreDispo();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return livres;
    }

    public Livre getById(int id) throws ServiceException {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        Livre livre;
        try {
            livre = livreDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return livre;
    }


    public int create(String titre, String auteur, String isbn) throws ServiceException {
        Livre livre = new Livre();
        LivreDao livreDao = LivreDaoImpl.getInstance();
        int i = -1;
        try {
            if(titre!=null) {
                i = livreDao.create(titre, auteur, isbn);
            }
        }  catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    }


    public void update(Livre livre) throws ServiceException {
        LivreDao filmDao = LivreDaoImpl.getInstance();
        int i = -1;
        try {
            int id =livre.getId();
            String titre=livre.getTitre();
            String auteur=livre.getAuteur();
            String isbn=livre.getIsbn();
            if(titre!=null) {
                livre = new Livre(id, titre, auteur, isbn);
                i = LivreDaoImpl.update(livre);
            }
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        } catch (NumberFormatException e2) {
            throw new ServiceException("Erreur lors du parsing: id=" + livre, e2);
        }
    }


    public int delete(String id) throws ServiceException {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        int i = -1;
        try {
            Livre livre = livreDao.getById(Integer.parseInt(id));
            i = LivreDaoImpl.delete(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        } catch (NumberFormatException e2) {
            throw new ServiceException("Erreur lors du parsing: id=" + id, e2);
        }
        return i;
    }

    public int count() throws ServiceException {

        LivreDao livreDao = LivreDaoImpl.getInstance();
        List<Livre> livres = new ArrayList<>();
        int i=0;
        try {
            i = livreDao.count();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    }

}
