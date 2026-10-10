package modele;

import java.io.Serializable;

public class MonIMC implements Serializable {

    private int id;
    private String nom, prenom, interpretation;
    private double taille, masse, imc;

    public MonIMC() {
        super();
    }

    public MonIMC(String nom, String prenom, double masse, double taille) {
        this.nom = nom;
        this.prenom = prenom;
        this.masse = masse;
        this.taille = taille;
        this.calculerIMC();
    }

    // Méthode métier : calcule l'IMC + interprétation selon OMS
    public void calculerIMC() {
        if (taille > 0) {
            this.imc = masse / (taille * taille);
            if (imc < 18.5)         interpretation = "Insuffisance pondérale";
            else if (imc < 25)      interpretation = "Corpulence normale";
            else if (imc < 30)      interpretation = "Surpoids";
            else if (imc < 35)      interpretation = "Obésité modérée";
            else if (imc < 40)      interpretation = "Obésité sévère";
            else                    interpretation = "Obésité morbide";
        }
    }

    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getInterpretation() { return interpretation; }
    public void setInterpretation(String interpretation) { this.interpretation = interpretation; }

    public double getTaille() { return taille; }
    public void setTaille(double taille) { this.taille = taille; }

    public double getMasse() { return masse; }
    public void setMasse(double masse) { this.masse = masse; }

    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }
}