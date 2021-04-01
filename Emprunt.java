package com.ensta.librarymanager.model;
import java.time.LocalDate;

public class Emprunt {

    private int id;
    private com.ensta.librarymanager.model.Membre membre;
    private com.ensta.librarymanager.model.Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    //constructeur
    public Emprunt(){}

    //getters


    public int getId() {
        return id;
    }

    public com.ensta.librarymanager.model.Membre getMembre() {
        return membre;
    }

    public com.ensta.librarymanager.model.Livre getLivre() {
        return livre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setMembre(com.ensta.librarymanager.model.Membre membre) {
        this.membre = membre;
    }

    public void setLivre(com.ensta.librarymanager.model.Livre livre) {
        this.livre = livre;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", Membre=" + membre +
                ", Livre=" + livre +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
