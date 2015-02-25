/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.io.IOException;
import java.io.StreamTokenizer;

/**
 *
 * @author Dobre
 */
public class HyperMarket extends Magazin {
    public HyperMarket(String nume, StreamTokenizer s) throws IOException 
    {
        super(nume,s);
    } 
    public double calculScutiriTaxe()
    {
        for (int i = 0; i < this.getf().size(); i++)
            if (this.getf().get(i).getTotalCuTaxe() > 0.1 * this.getTotalCuTaxe())
                return 0.01;
        return 0;
    }
}
