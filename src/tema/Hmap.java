/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.util.*;

/**
 *
 * @author Dobre
 */
public class Hmap extends HashMap {
    private Vector<Vector<HashMap<String, Integer>>> procent;
    
    public Hmap(){
        procent = new Vector<Vector<HashMap<String,Integer>>>();
    }
    public void put(String tara, String categorie, int Procent, int i)
    {
        HashMap<String, Integer> p = new HashMap<String,Integer>();
        if (procent.size() == 0 || procent.size() < i)
        {
            procent.add(new Vector<HashMap<String, Integer>>());
            p.put(categorie, Procent);
            procent.get(i - 1).add(p);
        } 
        else 
        {
            p.put(categorie,Procent);
            procent.get(i-1).add(p);
        }
        super.put(tara,procent.get(i - 1));
    }
    
    public int size()
    {
        return super.size();
    }
    public int getHmapTaxa(String tara, String categorie)
    {
        Iterator<Map.Entry<String, Vector<HashMap<String, Integer>>>> entries = this.entrySet().iterator();
        while(entries.hasNext())
        {
            Map.Entry<String,Vector<HashMap<String, Integer>>> entry = (Map.Entry<String,Vector<HashMap<String, Integer>>>) entries.next();
            if (entry.getKey().compareTo(tara) == 0)
            {
                Iterator<HashMap<String, Integer>> x = entry.getValue().listIterator();
                while (x.hasNext())
                {
                   HashMap<String, Integer> e = new HashMap<String, Integer>();
                   e = x.next();
                   if (e.containsKey(categorie) == true)
                   {
                       return e.get(categorie);
                   }
                }
            }
        }
        return 0;
    }
}
