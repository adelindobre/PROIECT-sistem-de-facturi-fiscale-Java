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
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.*;

public abstract class Magazin implements IMagazin {
    public enum MarketType
    { MiniMarket, MediumMarket, HyperMarket };
    private String nume;
    private Vector<Factura> f;
    
    public Magazin(String nume, StreamTokenizer s) throws IOException
    {
        this.nume = nume;
        f = new Vector<Factura>();
        int tip = s.nextToken();
        while (tip != StreamTokenizer.TT_EOF)
        {            
            if (tip == StreamTokenizer.TT_WORD && s.sval.equals("Magazin"))
                break;
            else
                if (tip == StreamTokenizer.TT_WORD && s.sval.contains("Factura"))
                {
                    f.add(new Factura(s.sval,s));
                }
            tip = s.nextToken();           
        }
    }
    public Vector<Factura> getf()
    {
        return f;
    }
    public String getNume()
    {
        return this.nume;
    }
    public double getTotalFaraTaxe()
    {
        double sum = 0;
        for (int i = 0; i<f.size(); i++)
                sum = sum + f.elementAt(i).getTotalFaraTaxe();
        return sum;
    }
    public double getTotalCuTaxe()
    {
        double sum = 0;
        for (int i = 0 ; i < f.size(); i++)
            sum = sum + f.elementAt(i).getTotalCuTaxe();
        return sum;
    }
    public double getTotalTaraFaraTaxe(String tara)
    {
        double sum = 0;
        for (int i = 0; i < f.size(); i++)
                sum = sum + f.elementAt(i).getTotalTaraFaraTaxe(tara);
        return sum;
    }
    public double getTotalTaraCuTaxe(String tara)
    {
        double sum = 0;
        for (int i = 0; i < f.size(); i++)
                sum = sum + f.elementAt(i).getTotalTaraCuTaxe(tara);
        return sum;
    }
    public double getTotalCategorieCuTaxe(String categorie)
    {
        double sum = 0;
        for (int i = 0; i < f.size(); i++)
            sum = sum + f.get(i).getTotalCategorieCuTaxe(categorie);
        return sum;
    }
    public double getTotalCuTaxeScutite()
    {
        double sum = 0;
        sum = this.getTotalCuTaxe() - this.getTotalCuTaxe() * this.calculScutiriTaxe();
       return sum; 
    }
    public double getTotalTaraCuTaxeScutite(String tara)
    {
        double sum = 0;
        sum = this.getTotalTaraCuTaxe(tara) - this.calculScutiriTaxe() * this.getTotalTaraCuTaxe(tara);
        return sum;
    }    
}


