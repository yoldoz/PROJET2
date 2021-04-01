package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDaoImpl implements EmpruntDao{

    private static EmpruntDaoImpl instance;
    private EmpruntDaoImpl() { }
    public static EmpruntDao getInstance() {
        if(instance == null) {
            instance = new EmpruntDaoImpl();
        }
        return instance;
    }

    private static final String SELECT_ALL_QUERY = "SELECT * FROM emprunt;";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Livre WHERE id=?;";
    private static final String CREATE_QUERY = "INSERT INTO Livre (titre, auteur, isbn) VALUES (?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE Livre SET titre=?,  auteur=?, isbn=?, WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT COUNT (id) AS count FROM livre WHERE id=?;";

    public List<Livre> getlist() throws DaoException {
        List<Livre> livres = new ArrayList<>();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            res = preparedStatement.executeQuery();
            while(res.next()) {
                Livre f = new Livre(res.getInt("id"), res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                livres.add(f);
            }
            System.out.println("GET: " + livres);
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
        return livres;
    }


    public Livre getById(int id) throws DaoException {
        Livre livre = new Livre();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            if(res.next()) {
                livre.setId(res.getInt("id"));
                livre.setTitre(res.getString("titre"));
                livre.setAuteur(res.getString("auteur"));
                livre.setIsbn(res.getString("isbn"));
            }

            System.out.println("GET: " + livre);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la r�cup�ration du film: id=" + id, e);
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
        return livre;
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
