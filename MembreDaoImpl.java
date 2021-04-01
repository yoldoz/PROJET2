package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MembreDaoImpl {

    private static MembreDaoImpl instance;
    private MembreDaoImpl() { }
    public static MembreDao getInstance() {
        if(instance == null) {
            instance = new MembreDaoImpl();
        }
        return instance;
    }

    private static final String SELECT_ALL_QUERY = "SELECT * FROM Membre;";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Membre WHERE id=?;";
    private static final String CREATE_QUERY = "INSERT INTO membre (nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?,\n" +
            "abonnement = ?, WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM membre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT COUNT (id) AS count FROM membre WHERE id=?;";

    public List<Membre> getlist() throws DaoException {
        List<Membre> membres = new ArrayList<>();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            res = preparedStatement.executeQuery();
            while(res.next()) {
                Membre f = new Membre (res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"));
                membres.add(f);
            }
            System.out.println("GET: " + membres);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la r�cup�ration de la liste des membres", e);
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
        return membres;
    }


    public Membre getById(int id) throws DaoException {
        Membre membre = new Membre();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            if(res.next()) {
                membre.setId(res.getInt("id"));
                membre.setNom(res.getString("nom"));
                membre.setPrenom(res.getString("prenom"));
                membre.setAdresse(res.getString("adresse"));
                membre.setEmail(res.getString("email"));
                membre.setTelephone(res.getString("telephone"));
            }

            System.out.println("GET: " + membre);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la r�cup�ration du membre: id=" + id, e);
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
        return membre;
    }


    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = -1;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, telephone);
            preparedStatement.executeUpdate();
            res = preparedStatement.getGeneratedKeys();
            if(res.next()){
                id = res.getInt(1);
            }

            System.out.println("CREATE: id=" + id +" nom= "+nom+" prenom= "+prenom+" adresse "+adresse+" email "+email+" telephone "+telephone);
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


    public void update(Membre membre) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setString(3, membre.getAdresse());
            preparedStatement.setString(4, membre.getEmail());
            preparedStatement.setString(5, membre.getTelephone());
            preparedStatement.setString(6, membre.getAbonnement());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE: " + membre);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la mise � jour du membre: " + membre, e);
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

    public void delete (int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("DELETE: " + id);
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la suppression du membre: " + id, e);
        }  finally {
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
        List<Membre> membres = new ArrayList<>();
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
                Membre f = new Membre(res.getInt("id"), res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                membres.add(f);
            }
        } catch (SQLException e) {
            throw new DaoException("Probl�me lors de la r�cup�ration de la liste des membres", e);
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
