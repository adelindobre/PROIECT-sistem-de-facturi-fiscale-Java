/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.*;

/**
 *
 * @author Dobre
 */
public class ProdusComandat 
{
    private Produs produs;
    private double taxa;
    private int cantitate;
    
    
    public ProdusComandat(String nume, StreamTokenizer s) throws IOException
    {
        int tip = s.nextToken();
        tip = s.nextToken();
        for (int i = 0; i<Gestiune.getInstance().getProduse().size(); i++)
            if (Gestiune.getInstance().getProduse().get(i).getDenumire().equals(nume) && Gestiune.getInstance().getProduse().get(i).getTaraOrigine().equals(s.sval))
            {
                produs = Gestiune.getInstance().getProduse().get(i);
                break;
            }
        tip = s.nextToken();
        tip = s.nextToken();
        this.cantitate = (int) s.nval;
    }
    
    public void setProdus(Produs produs)
    {
        this.produs = produs;
    }
    public Produs getProdus()
    {
        return this.produs;
    }
    public void setTaxa(int taxa)
    {
        this.taxa = (this.produs.getPret() * (double)taxa) / (double)100 ;
    }
    public double getTaxa()
    {
        return this.taxa;
    }
    public void setCantitate(int cantitate)
    {
        this.cantitate = cantitate;
    }
    public int getCantitate()
    {
        return this.cantitate;
    }
}
