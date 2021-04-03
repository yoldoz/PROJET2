package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class EmpruntDaoImpl {

    private static EmpruntDaoImpl instance;
    private EmpruntDaoImpl() { }
    public static EmpruntDaoImpl getInstance() {
        if(instance == null) {
            instance = new EmpruntDaoImpl();
        }
        return instance;
    }

    private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
            "dateRetour\n" +
            "FROM emprunt AS e\n" +
            "INNER JOIN membre ON membre.id = e.idMembre\n" +
            "INNER JOIN livre ON livre.id = e.idLivre\n" +
            "ORDER BY dateRetour DESC;ORDER BY dateRetour DESC";
    private static final String SELECT_CURRENT_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
            "dateRetour\n" +
            "FROM emprunt AS e\n" +
            "INNER JOIN membre ON membre.id = e.idMembre\n" +
            "INNER JOIN livre ON livre.id = e.idLivre\n" +
            "WHERE dateRetour IS NULL;";
    private static final String SELECT_CURRENT_BY_MEMBRE_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
            "dateRetour\n" +
            "FROM emprunt AS e\n" +
            "INNER JOIN membre ON membre.id = e.idMembre\n" +
            "INNER JOIN livre ON livre.id = e.idLivre\n" +
            "WHERE dateRetour IS NULL AND membre.id = ?;";
    private static final String SELECT_CURRENT_BY_LIVRE_QUERY="SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
            "dateRetour\n" +
            "FROM emprunt AS e\n" +
            "INNER JOIN membre ON membre.id = e.idMembre\n" +
            "INNER JOIN livre ON livre.id = e.idLivre\n" +
            "WHERE dateRetour IS NULL AND livre.id = ?;";
    private static final String SELECT_CURRENT_BY_ID_QUERY="SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email,\n" +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
            "dateRetour\n" +
            "FROM emprunt AS e\n" +
            "INNER JOIN membre ON membre.id = e.idMembre\n" +
            "INNER JOIN livre ON livre.id = e.idLivre\n" +
            "WHERE e.id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour)\n" +
            "VALUES (?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE emprunt\n" +
            "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ?\n" +
            "WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";



    public List<Emprunt> getList() throws DaoException {
        List<Emprunt> emprunts = new ArrayList<>();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            res = preparedStatement.executeQuery();
            while(res.next()) {
                /*
                int m=res.getInt("idMembre");
                int l=res.getInt("idLivre");
                List<Membre> membres=getList();
                ResultSet res_m = null;
                Connection connection_m = null;
                PreparedStatement preparedStatement_m = null;
                try {
                    connection_m = ConnectionManager.getConnection();
                    preparedStatement_m = connection.prepareStatement(SELECT_ALL_QUERY);
                    res_m = preparedStatement.executeQuery();

                }*/

                Emprunt f = new Emprunt(res.getInt("id"), res.getInt("idMembre"), res.getInt("idLivre"), res.getDate("dateEmprunt"), res.getDate("dateRetour"));
                emprunts.add(f);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la r�cup�ration de la liste des emprunts", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return emprunts;
    }


    List<Emprunt> getListCurrent(int id) throws DaoException {
        Emprunt emprunt = new Emprunt();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_CURRENT_QUERY);
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            if(res.next()) {
                emprunt.setId(res.getInt("id"));
                emprunt.setIdMembre(res.getString("idMembre"));
                emprunt.setIdLivre(res.getString("idLivre"));
                emprunt.setDateEmprunt(res.getString("dateEmprunt"));
                emprunt.setDateRetour(res.getString("dateRetour"));
            }

            System.out.println("GET: " + emprunt);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la r�cup�ration de l'emprunt: id=" + id, e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return emprunt;
    }


    public int create(String titre, String auteur, String isbn) throws DaoException {
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = -1;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, isbn);
            preparedStatement.executeUpdate();
            res = preparedStatement.getGeneratedKeys();
            if(res.next()){
                id = res.getInt(1);
            }

            System.out.println("CREATE: id=" + id +" titre= "+titre+"auteur="+auteur+"isbn"+isbn);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la cr�ation du livre: " + id, e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return id;
    }


    public void update(Livre livre) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getId());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE: " + livre);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la mise � jour du livre: " + livre, e);
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public int count() throws DaoException {
        List<Livre> livres = new ArrayList<>();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int i=0;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            res = preparedStatement.executeQuery();
            while(res.next()) {
                i++;
                Livre f = new Livre(res.getInt("id"), res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                livres.add(f);
            }
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la r�cup�ration de la liste des livres", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }



}
