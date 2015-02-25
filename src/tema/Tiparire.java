/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import tema.Magazin.MarketType;
import static tema.Magazin.MarketType.MiniMarket;


/**
 *
 * @author Dobre
 */
public class Tiparire 
{
    
    private PrintWriter fisier;
    private ArrayList<Magazin> m;
    private MarketType modelM;
    private static int r = 0;
    public static int iter = 1;
    public Tiparire(PrintWriter x)
    {
        fisier = x;
        m = new ArrayList<Magazin>();
    }
    public void createVectorMarketType(MarketType model, Class modelMagazin)
    {
        modelM = model;
        Iterator<Magazin> it = Gestiune.getInstance().getMagazine().iterator();
        Magazin market;
        while (it.hasNext())
        {
            market = it.next();
            if (modelMagazin.isInstance(market))
                m.add(market);
        }
        Collections.sort(m, new Comparator<Magazin>(){
            public int compare(Magazin o1, Magazin o2)
            {
                if (o1.getTotalFaraTaxe() > o2.getTotalFaraTaxe())
                    return 1;
                else
                    return -1;
            }      
        });
    }
    public void sortVectorFacturiFromMarket()
    {
        Iterator<Magazin> it  = m.iterator();
        while (it.hasNext())
            Collections.sort(it.next().getf(), new Comparator<Factura>()
            {
                public int compare(Factura o1, Factura o2)
                {
                    if (o1.getTotalCuTaxe() > o2.getTotalCuTaxe())
                        return 1;
                    else
                        return -1;
                }
            });        
    }
    public void tiparireFisier(MarketType model, Class modelMagazin) throws IOException
    {
        this.createVectorMarketType(model, modelMagazin);
        if (this.m.isEmpty() == true)
            ;
        else
        {   
            r = 0;
            if (r != 0 || iter != 1)
            {
                fisier.println();
                fisier.println();
            }
            DecimalFormat df = new DecimalFormat("#.####");
            this.sortVectorFacturiFromMarket();
            fisier.println(this.modelM.toString());
            Iterator<Magazin> it = this.m.iterator();
            Magazin market;
            while (it.hasNext())
            {   
                r++;
                if (r != 1)
                {
                    fisier.println();
                    fisier.println();
                }
                market = it.next();
                fisier.println(market.getNume());
                fisier.println();
                fisier.println("Total" + " " + df.format(market.getTotalFaraTaxe()) + " " + df.format(market.getTotalCuTaxe()) + " " + df.format(market.getTotalCuTaxeScutite()));
                fisier.println();
                fisier.println("Tara");
                List<String> e = new ArrayList<String>(Gestiune.getInstance().getTaxe().keySet());                
                Collections.sort(e, new Comparator<String>(){

                    @Override
                    public int compare(String arg0, String arg1) {
                       return (arg0.compareTo(arg1));
                    }                    
                });
               int flag; 
               for (int i = 0; i < e.size(); i++)
               {
                   fisier.print(e.get(i) + " ");
                   flag = 0;
                   for (int j = 0; j < market.getf().size(); j++)
                   {
                       for (int k = 0; k < market.getf().get(j).getPc().size(); k++)
                           if (market.getf().get(j).getPc().get(k).getProdus().getTaraOrigine().compareTo(e.get(i)) == 0)
                           {
                               fisier.println(df.format(market.getTotalTaraFaraTaxe(e.get(i))) + " " + df.format(market.getTotalTaraCuTaxe(e.get(i))) + " " + df.format(market.getTotalTaraCuTaxeScutite(e.get(i))));
                               flag = 1;
                               break;
                           }
                       if (flag == 1)
                           break;
                   }
                   if (flag == 0)
                       fisier.println("0");
               }
               Iterator<Factura> itF = market.getf().iterator();
               Factura factura;
               while (itF.hasNext())
               {
                   factura = itF.next();
                   fisier.println();
                   fisier.println(factura.getDenumire());
                   fisier.println();
                   fisier.println("Total" + " " + df.format(factura.getTotalFaraTaxe()) + " " + df.format(factura.getTotalCuTaxe()));
                   fisier.println();
                   fisier.println("Tara");
                   for (int i = 0; i < e.size(); i++)
                   {
                       flag = 0;
                       fisier.print(e.get(i) + " ");
                       for (int j = 0; j < factura.getPc().size(); j++)
                           if (factura.getPc().get(j).getProdus().getTaraOrigine().compareTo(e.get(i)) == 0)
                           {
                               fisier.print(df.format(factura.getTotalTaraFaraTaxe(e.get(i))) + " " + df.format(factura.getTotalTaraCuTaxe(e.get(i))));
                               if (i != e.size() - 1)
                                   fisier.println();
                               flag = 1;
                               break;
                           }
                       if (flag == 0)
                       {
                           fisier.print("0");
                           if (i != e.size() - 1)
                                   fisier.println();
                       }
                   }
                   if (itF.hasNext() == true)
                                   fisier.println();
               }
               
            }
            iter++;            
        }
    }
}
