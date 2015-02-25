/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.io.*;
import java.util.*;

/**
 *
 * @author Dobre
 */
public class Factura {
    private String denumire;
    private Vector<ProdusComandat> pc;
    
    
    public Factura (String denumire, StreamTokenizer s) throws IOException
    {
        this.denumire = denumire;
        pc = new Vector<ProdusComandat>();
        int tip = s.nextToken();
        int nr = s.lineno();
        while (tip != StreamTokenizer.TT_EOF)
        {           
            if (tip == StreamTokenizer.TT_EOL)
            {
                tip = s.nextToken();
                if (s.lineno() != nr)
                    if (tip == StreamTokenizer.TT_WORD && s.sval != null)
                    {
                        pc.add(new ProdusComandat(s.sval,s));
                    }
                    else{
                        break;                    
                    }
            }
            tip = s.nextToken();
        }
    }
    public Vector<ProdusComandat> getPc()
    {
        return pc;
    }
    public String getDenumire()
    {
        return this.denumire;
    }
    public double getTotalFaraTaxe()
    {
        double sum = 0;
        for (int i = 0; i<pc.size(); i++)
            sum = sum + pc.elementAt(i).getProdus().getPret() * pc.elementAt(i).getCantitate();
        return sum;
    }
    public double getTotalCuTaxe()
    {
        double sum = 0;
        for (int i = 0; i<pc.size(); i++)
            sum = sum + ( pc.elementAt(i).getProdus().getPret() + pc.elementAt(i).getTaxa() ) * pc.elementAt(i).getCantitate();
        return sum;
    }
    public double getTaxe()
    {
        double sum = 0;
        for (int i = 0; i<pc.size(); i++)
            sum = sum + pc.elementAt(i).getTaxa() * pc.elementAt(i).getCantitate();
        return sum;
    }
    public double getTotalTaraFaraTaxe(String tara)
    {
        double sum = 0;
        for (int i = 0; i<pc.size(); i++)
            if (pc.elementAt(i).getProdus().getTaraOrigine().compareTo(tara) == 0)
                sum = sum + pc.elementAt(i).getProdus().getPret() * pc.elementAt(i).getCantitate();
        return sum;
    }
    public double getTotalCategorieCuTaxe(String categorie)
    {
        double sum = 0;
        for (int i = 0; i < pc.size(); i++)
            if (pc.get(i).getProdus().getCategorie().compareTo(categorie) == 0)
                sum = sum + ( pc.get(i).getProdus().getPret() + pc.get(i).getTaxa() ) * pc.get(i).getCantitate();
        return sum;
    }
    public double getTotalTaraCuTaxe(String tara)
    {
        double sum = 0;
        for (int i = 0; i<pc.size(); i++)
            if (pc.elementAt(i).getProdus().getTaraOrigine().compareTo(tara) == 0)
               sum = sum + ( pc.elementAt(i).getProdus().getPret() + pc.elementAt(i).getTaxa() ) * pc.elementAt(i).getCantitate();
        return sum;
    }
    public double getTaxeTara(String tara)
    {
        double sum = 0;
        for (int i = 0; i<pc.size(); i++)
            if (pc.elementAt(i).getProdus().getTaraOrigine().compareTo(tara) == 0)
                  sum = sum + pc.elementAt(i).getTaxa() * pc.elementAt(i).getCantitate();
        return sum;
    }
}