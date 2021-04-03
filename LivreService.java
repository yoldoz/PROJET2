package com.ensta.librarymanager.service;



import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.LivreDaoImpl;


public interface LivreService {

	 List<Livre> getList() throws ServiceException;
	 List<Livre> getListDispo() throws ServiceException;
	 Livre getById(int id) throws ServiceException;
	 int create(String titre, String auteur, String isbn) throws ServiceException;
	 void update(Livre livre) throws ServiceException;
	 void delete(int id) throws ServiceException;
	 int count() throws ServiceException;
}
