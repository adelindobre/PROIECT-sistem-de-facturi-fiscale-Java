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
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class ChangeProduct extends JPanel {
    JTable table;
    JFrame jf;
    ProdusTableModel tableModel;
    Produs pmod;
    Produs p;
    JTextField tn, tc;
    JTextField tt,tp, jtf;
    String line;
    int row;
    
    public ChangeProduct()
    {
        super(null);
        setSize(new Dimension(600,400));
        
        ArrayList<Produs> listProd = new ArrayList<Produs>();
        for (int i = 0; i < Gestiune.getInstance().getProduse().size(); i++)
            if (Gestiune.getInstance().getProduse().get(i).getPret() != 0)
                listProd.add(Gestiune.getInstance().getProduse().get(i));
        
        tableModel = new ProdusTableModel(listProd);
        table = new JTable(tableModel);
        JScrollPane scrollPane  = new JScrollPane(table);
        scrollPane.setBounds(80,40,620,170);
        add(scrollPane);
        table.setAutoCreateColumnsFromModel(true);
        table.getColumnModel().getColumn(0).setHeaderValue("Denumire");
        table.getColumnModel().getColumn(1).setHeaderValue("Categorie");
        table.getColumnModel().getColumn(2).setHeaderValue("Tara_Origine");
        table.getColumnModel().getColumn(3).setHeaderValue("Pret");
        
        table.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {   
                    JTable target = (JTable)e.getSource();
                    row = target.getSelectedRow();
                    jf = new JFrame("Delete Item");
                    jf.setLayout(null);
                    jf.setLocation(540,540);
                    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jf.setVisible(true);
                    jf.setSize(new Dimension(270,290));
                    
                    JLabel ln, lc, lt, lp;
                    JButton delete;
                    
                    ln = new JLabel("Nume:");
                    lc = new JLabel("Categorie:");
                    lt = new JLabel("Tara:");
                    lp = new JLabel("Pret:");
                    
                    tn = new JTextField(30);
                    tc = new JTextField(30);
                    tt = new JTextField(30);
                    tp = new JTextField(30);
                    
                    delete = new JButton("Delete");
                    
                    ln.setBounds(30,30,50,20);
                    tn.setBounds(95,30,120,20);
                    lc.setBounds(30,70,70,20);
                    tc.setBounds(95,70,120,20);
                    lt.setBounds(30,110,50,20);
                    tt.setBounds(95,110,120,20);                    
                    lp.setBounds(30,150,50,20);
                    tp.setBounds(95,150,120,20);
                    delete.setBounds(95,190,80,30);
                  
                    
                    tn.setText((String)tableModel.getValueAt(row, 0));
                    tc.setText((String)tableModel.getValueAt(row, 1));
                    tt.setText((String)tableModel.getValueAt(row, 2));
                    tp.setText(Double.toString((double) tableModel.getValueAt(row, 3)));
                    
                    jf.add(ln);
                    jf.add(lc);
                    jf.add(lt);
                    jf.add(lp);
                    jf.add(tn);
                    jf.add(tc);
                    jf.add(tt);
                    jf.add(tp);
                    jf.add(delete);
                    
                    p = new Produs(tn.getText(), tc.getText(), tt.getText(), Double.parseDouble(tp.getText()));
                    
                    delete.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                RandomAccessFile x = new RandomAccessFile(new File("produse.txt"), "r");                                
                                Vector<String> v;
                                int length, flag;
                                String line  = x.readLine();                                
                                StringTokenizer st = new StringTokenizer(line," ");
                                Vector<String> tari = new Vector<String>();
                                while (st.hasMoreElements())
                                    tari.add(st.nextToken());
                                x.seek(0);
                                Vector<String> sline = new Vector<String>();
                                while ((line = x.readLine()) != null)
                                    sline.add(line);
                                x.close();
                                StringBuilder sb;
                                PrintWriter pw = new PrintWriter(new File("produse.txt"));
                                for (int i = 1; i < sline.size(); i++)
                                    if (sline.get(i).contains(p.getDenumire()) && sline.get(i).contains(p.getCategorie()) && sline.get(0).contains(p.getTaraOrigine()))
                                    {
                                        length = sline.get(i).length();
                                        st = new StringTokenizer(sline.get(i), " ");
                                        v = new Vector<String>();
                                        while (st.hasMoreElements())
                                            v.add(st.nextToken());
                                        
                                        for (int j = 2; j < v.size(); j++)
                                            if (p.getTaraOrigine().compareTo(tari.get(j)) == 0)
                                            {
                                                v.remove(j);
                                                v.add(j, "0");
                                            }
                                        for (int j = 0; j < v.size(); j++)
                                            System.out.println(v.get(j));
                                            
                                        sline.remove(i);
                                        sb = new StringBuilder("");
                                        sb.append(v.get(0) + " ");
                                        sb.append(v.get(1) + " ");
                                        flag = 0;
                                        for (int j = 2; j < v.size() - 1; j++)
                                        {
                                            sb.append(v.get(j) + " ");
                                            if (v.get(j).compareTo("0") != 0)
                                                flag = 1;
                                        }   
                                        sb.append(v.lastElement());
                                        if (v.lastElement().compareTo("0") != 0)
                                                flag = 1;
                                        
                                        if (flag != 0)                                            
                                            sline.add(i, sb.toString());                                                              
                                        break;
                                    }
                                for (int i = 0; i < sline.size() - 1; i++ )
                                    pw.println(sline.get(i));
                                pw.print(sline.lastElement());
                                pw.close();
                                tableModel.removeRow(row);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ChangeProduct.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(ChangeProduct.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            jf.dispose();
                        

                        }
                    });
                   
        
                }
            }
        });
    }
}
