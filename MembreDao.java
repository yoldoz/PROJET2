package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Membre;

public interface MembreDao {
	 List<Membre> getList() throws DaoException;
	 Membre getById(int id) throws DaoException;
	 int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException;
	 void update(Membre membre) throws DaoException;
	 void delete(int id) throws DaoException;
	 int count() throws DaoException;
}
