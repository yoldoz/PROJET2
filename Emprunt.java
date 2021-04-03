package com.ensta.librarymanager.model;
import java.time.LocalDate;

public class Emprunt {

    private int id;
    private Membre membre;
    private Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    //constructeur
    public Emprunt(){}

    public Emprunt(int id, Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour){
        this.id=id;
        this.membre=membre;
        this.livre=livre;
        this.dateEmprunt=dateEmprunt;
        this.dateRetour=dateRetour;
    }

    //getters


    public int getId() {
        return id;
    }

    public Membre getMembre() {
        return membre;
    }

    public Livre getIdLivre() {
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

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public void setLivre(Livre livre) {
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
                ", membre=" + membre +
                ", livre=" + livre +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
