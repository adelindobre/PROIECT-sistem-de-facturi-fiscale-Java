/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

/**
 *
 * @author Dobre
 */
public class Produs {

    /**
     * @param args the command line arguments
     */
    private String denumire;
    private String categorie;
    private String tara_origine;
    private double pret;
    
    public Produs(String denumire, String categorie, String tara_origine, double pret)
    {
        this.setDenumire(denumire);
        this.setCategorie(categorie);
        this.setTaraOrigine(tara_origine);
        this.setPret(pret); 
    }        
    public void setDenumire(String denumire)
    {
        this.denumire = denumire;
    }
    public String getDenumire()
    {
        return this.denumire;
    }
    public void setCategorie(String categorie)
    {
        this.categorie = categorie;
    }
    public String getCategorie()
    {
        return this.categorie;
    }
    public void setTaraOrigine(String tara_origine)
    {
        this.tara_origine = tara_origine;
    }
    public String getTaraOrigine()
    {
        return this.tara_origine;
    }
    public void setPret( double pret)
    {
        this.pret = pret;
    }
    public double getPret()
    {
        return this.pret;
    }
    
}
