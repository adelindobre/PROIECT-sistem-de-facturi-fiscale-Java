/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.util.*;
import java.io.*;
/**
 *
 * @author Dobre
 */
public class Gestiune {
    
    private ArrayList<Produs> produse;
    private ArrayList<Magazin> magazine;
    private Hmap taxe;
    
    private static Gestiune gestiune = new Gestiune();
    
    private Gestiune() { 
        magazine = new ArrayList<Magazin>();        
        produse = new ArrayList<Produs>();
        taxe = new Hmap();
    };
    public ArrayList<Produs> getProduse()
    {
        return produse;
    }   
    public ArrayList<Magazin> getMagazine()
    {
        return magazine;
    }        
    public static Gestiune getInstance()
    { 
        return gestiune; 
    }
    public Hmap getTaxe()
    {
        return taxe;
    }
}
