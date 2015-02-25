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
public class MiniMarket extends Magazin 
{
    public MiniMarket(String nume, StreamTokenizer s) throws IOException 
    {
        super(nume,s);
    }
    @Override
    public double calculScutiriTaxe()
    { 
        int k = 0;
        Vector<String> tari = new Vector<String>();
        for (int i = 0; i < this.getf().size(); i++)
            for (int j = 0; j < this.getf().get(i).getPc().size(); j++)
                if (tari.contains(this.getf().get(i).getPc().get(j).getProdus().getTaraOrigine()) != true)
                {
                    tari.add(this.getf().get(i).getPc().get(j).getProdus().getTaraOrigine());
                    if (this.getTotalTaraCuTaxe(tari.get(k++)) > 0.5 * this.getTotalCuTaxe())
                        return 0.1;
                }
        return 0;
    }
}
