package com.ensta.librarymanager.model;

public enum Abonnement {
    BASIC, PREMIUM, VIP;

    public Abonnement valueOf(int i){
        Abonnement A=null;
        if(i==1)
            A= BASIC;
        else if(i==2)
            A=PREMIUM;
        else if(i==3)
            A=VIP;

        return A;
    }




}
