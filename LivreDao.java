package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Livre;

public interface LivreDao {
	 List<Livre> getList() throws DaoException;
	 Livre getById(int id) throws DaoException;
	 int create(String titre, String auteur, String isbn) throws DaoException;
	 void update(Livre livre) throws DaoException;
	 void delete(int id) throws DaoException;
	 int count() throws DaoException;
}
