/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.io.IOException;
import java.io.StreamTokenizer;
import tema.Magazin.MarketType;

/**
 *
 * @author Dobre
 */
public class MarketFactory {
    public static Magazin buildMarket (MarketType model, String nume, StreamTokenizer s) throws IOException
    {
        Magazin market = null;
        switch(model){
            case MiniMarket:
                market = new MiniMarket(nume,s);
                Gestiune.getInstance().getMagazine().add(market);
                break;
            case MediumMarket:
                market = new MediumMarket(nume,s);
                Gestiune.getInstance().getMagazine().add(market);
                break;
            case HyperMarket:
                market = new HyperMarket(nume,s);
                Gestiune.getInstance().getMagazine().add(market);
                break;
            default:
                break;
        }
        return market;
    }
}
