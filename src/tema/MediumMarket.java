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
public class MediumMarket extends Magazin {
   
    public MediumMarket(String nume, StreamTokenizer s) throws IOException 
    {
        super(nume,s);
    }
    @Override
    public double calculScutiriTaxe()
    { 
        int k = 0;
        Vector<String> categorie = new Vector<String>();
        for (int i = 0; i < this.getf().size(); i++)
            for (int j = 0; j < this.getf().get(i).getPc().size(); j++)
                if (categorie.contains(this.getf().get(i).getPc().get(j).getProdus().getCategorie()) != true)
                {
                    categorie.add(this.getf().get(i).getPc().get(j).getProdus().getCategorie());
                    if (this.getTotalCategorieCuTaxe(categorie.get(k++)) > 0.5 * this.getTotalCuTaxe())
                        return 0.05;
                }
        return 0;
    }
    
}
