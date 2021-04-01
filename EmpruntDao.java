package com.ensta.librarymanager.dao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;


import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;

public interface EmpruntDao {
	 List<Emprunt> getList() throws DaoException;
	 List<Emprunt> getListCurrent() throws DaoException;
	 List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException;
	 List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException;
	 Emprunt getById(int id) throws DaoException;
	 void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException;
	 void update(Emprunt emprunt) throws DaoException;
	 int count() throws DaoException;
}
