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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

public class AddProduct extends JPanel {
    JTable table;
    ProdusTableModel tableModel;
    ArrayList<Produs> listProd;
    JLabel eticheta;
    JLabel numel;
    JTextField nume;
    JLabel categoriel;
    JTextField categorie;
    JLabel taral;
    JTextField tara;
    JLabel pretl;
    JTextField pret;
    JButton create;
    
    public AddProduct() throws IOException
    {
        super(null);
        setSize(new Dimension(600,400));
        eticheta = new JLabel("Add a new product:");
        numel = new JLabel("Nume:");
        categoriel = new JLabel("Categorie:");
        taral = new JLabel("Tara:");
        pretl = new JLabel("Pretl:");
        nume = new JTextField(15);
        categorie = new JTextField(15);
        tara  = new JTextField(15);
        pret = new JTextField(15);
        create  = new JButton("Create");
        
        eticheta.setBounds(15,30 ,200 ,20);
        numel.setBounds(15,80,40,20);
        nume.setBounds(80, 80, 100,20);
        categoriel.setBounds(15,115,60,20);
        categorie.setBounds(80,115,100,20);
        taral.setBounds(15,150,40,20);
        tara.setBounds(80,150,100,20);
        pretl.setBounds(15,185,40,20);
        pret.setBounds(80,185,100,20);
        create.setBounds(15,230,80,30);
        
      add(eticheta);
      add(nume);
      add(numel);
      add(categorie);  
      add(categoriel);
      add(tara);
      add(taral);
      add(pret);
      add(pretl);
      add(create); 
      
        listProd = Gestiune.getInstance().getProduse();
        tableModel = new ProdusTableModel(listProd);
        table = new JTable(tableModel);
        JScrollPane scrollPane  = new JScrollPane(table);
        scrollPane.setBounds(200,60,550,150);
        add(scrollPane);
        table.setAutoCreateColumnsFromModel(true);
        table.getColumnModel().getColumn(0).setHeaderValue("Denumire");
        table.getColumnModel().getColumn(1).setHeaderValue("Categorie");
        table.getColumnModel().getColumn(2).setHeaderValue("Tara_Origine");
        table.getColumnModel().getColumn(3).setHeaderValue("Pret");
      
      create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RandomAccessFile f;
                try {
                    
                    f = new RandomAccessFile(new File("produse.txt"),"rw");                    
                    StringTokenizer st = new StringTokenizer(f.readLine());
                    Vector<String> taraS = new Vector<String>();
                    while(st.hasMoreElements())
                    {
                        taraS.add(st.nextToken());
                    }
                    
                    f.seek(0);
                    Vector<String> sline = new Vector<String>();
                    String line = f.readLine();
                    while(line != null)
                    {
                        sline.add(line);
                        line = f.readLine();
                    }
                    f.close();
                    PrintWriter pw = new PrintWriter(new File("produse.txt"));
                    pw.flush();
                    Produs p = new Produs(nume.getText(),categorie.getText(),tara.getText(), Double.parseDouble(pret.getText()));
                    listProd = Gestiune.getInstance().getProduse();
                    int flag = 0;
                    for (int k = 1; k < sline.size(); k++)
                    {
                        if (sline.get(k).contains(p.getDenumire() + " " + p.getCategorie()) == true && sline.get(0).contains(p.getTaraOrigine()) == true)
                        {
                            JOptionPane.showMessageDialog(null, "The product is already added!");
                            flag = 1;
                            
                            for (int i = 0; i < sline.size() - 1; i++)
                                pw.println(sline.get(i));
                            pw.print(sline.lastElement());
                            break;
                        }
                    }
                    if (flag == 0)
                    {
                        for(int j = 1; j < sline.size(); j++)
                            if(sline.get(j).contains(p.getDenumire() + " " + p.getCategorie()) == true)
                            {                                
                                pw.println(sline.get(0).concat(" " + p.getTaraOrigine()));
                                int i = 1;
                                while(i<sline.size() - 1)
                                {  
                                    if (i == j)
                                        pw.println(sline.get(i++).concat(" " + p.getPret()));
                                    else
                                        pw.println(sline.get(i++).concat(" 0"));
                                }
                                if (i == j)
                                        pw.print(sline.get(i++).concat(" " + p.getPret()));
                                    else
                                        pw.print(sline.get(i++).concat(" 0"));                                
                                                                                                
                                flag = 1;                                
                                break;
                            }
                        if (flag == 0)
                        {
                            if (taraS.contains(p.getTaraOrigine()) == true)
                            {
                                line = p.getDenumire() + " " + p.getCategorie();
                                for (int i = 2; i < taraS.size(); i++)
                                {
                                    if (taraS.get(i).compareTo(p.getTaraOrigine()) == 0)
                                        line = line.concat(" " + p.getPret());
                                    else
                                        line = line.concat(" 0");
                                }
                                sline.add(line);
                                for (int i = 0; i< sline.size() - 1; i++)
                                    pw.println(sline.get(i));
                                pw.print(sline.lastElement());
                            }
                            else
                            {
                                line = p.getDenumire() + " " + p.getCategorie();
                                for (int i = 2; i < taraS.size(); i++)
                                    line = line.concat(" 0");
                                line = line.concat(" " + p.getPret());
                                sline.add(line);
                                
                                 pw.println(sline.get(0).concat(" " + p.getTaraOrigine()));
                                 for(int i = 1; i < sline.size() - 1; i++)
                                     pw.println(sline.get(i).concat(" 0"));
                                 pw.print(sline.lastElement());
                            }
                        }
                        tableModel.addRow(p);                   
                    }                    
                    pw.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      
      
    }
    
}
