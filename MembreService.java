package com.ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Membre;

public interface MembreService {

	 List<Membre> getList() throws ServiceException;
	 List<Membre> getListMembreEmpruntPossible() throws ServiceException;
	 Membre getById(int id) throws ServiceException;
	 int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException;
	 void update(Membre membre) throws ServiceException;
	 void delete(int id) throws ServiceException;
	 int count() throws ServiceException;

}
