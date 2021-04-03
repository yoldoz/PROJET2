package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;

public interface EmpruntService {
	 List<Emprunt> getList() throws ServiceException;
	 List<Emprunt> getListCurrent() throws ServiceException;
	 List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException;
	 List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException;
	 Emprunt getById(int id) throws ServiceException;
	 void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException;
	 void returnBook(int id) throws ServiceException;
	 int count() throws ServiceException;
	 boolean isLivreDispo(int idLivre) throws ServiceException;
	 boolean isEmpruntPossible(Membre membre) throws ServiceException ;
}
