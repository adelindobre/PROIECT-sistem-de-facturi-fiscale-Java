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
import java.awt.*;
import java.awt.event.*;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.border.BevelBorder;


public class SearchProduct extends JPanel{
    JTable table;
    ProdusTableModel tableModel;
    ArrayList<Produs> listProd;
    
    public SearchProduct()
    {
        super(null);
        setSize(new Dimension(600,400));
        
        listProd = new ArrayList<Produs>();
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
        
        createPopUp();   
    }
    
    public void createPopUp()
    {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem item = new JMenuItem("Search");
        popup.add(item);                          
        popup.setBorder(new BevelBorder(BevelBorder.RAISED));
        popup.setVisible(true);                
        MouseListener popListener = new MousePopUpListener(popup);
        table.addMouseListener(popListener);
        
        item.addActionListener(new PopMenuListener());
    }
 
        class MousePopUpListener extends MouseAdapter {
            
            private JPopupMenu pop;
            
            MousePopUpListener(JPopupMenu pop)
            {
                this.pop = pop;
            }
            public void mousePressed(MouseEvent e)
            {
                check(e);
            }
            public void mouseClicked(MouseEvent e)
            {
                check(e);
            }
            public void mouseReleased(MouseEvent e)
            {
                if (table.getSelectedRow() != -1)
                    check(e);
            }
            private void check(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    pop.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
        
        class PopMenuListener implements ActionListener{

             JFrame jf;
             JTextField tn, tc;
             JTextField tt,tp;
             
            @Override
            public void actionPerformed(ActionEvent e) {
                    jf = new JFrame("Search for Item");
                    jf.setLayout(null);
                    jf.setLocation(540,540);
                    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jf.setVisible(true);
                    jf.setSize(new Dimension(270,290));
                    
                    JLabel ln, lc, lt, lp;
                    JButton search;
                    
                    ln = new JLabel("Nume:");
                    lc = new JLabel("Categorie:");
                    lt = new JLabel("Tara:");
                    lp = new JLabel("Pret:");
                    
                    tn = new JTextField(30);
                    tc = new JTextField(30);
                    tt = new JTextField(30);
                    tp = new JTextField(30);
                    
                    search = new JButton("Search");
                    search.setBounds(130,190,80,30);  
                    
                    ln.setBounds(30,30,50,20);
                    tn.setBounds(95,30,120,20);
                    lc.setBounds(30,70,70,20);
                    tc.setBounds(95,70,120,20);
                    lt.setBounds(30,110,50,20);
                    tt.setBounds(95,110,120,20);                    
                    lp.setBounds(30,150,50,20);
                    tp.setBounds(95,150,120,20);
                    
                    jf.add(ln);
                    jf.add(lc);
                    jf.add(lt);
                    jf.add(lp);
                    jf.add(tn);
                    jf.add(tc);
                    jf.add(tt);
                    jf.add(tp);
                    jf.add(search);
                    
                    search.addActionListener(new CautaListener()); 
                    
                   
            }
            class CautaListener implements ActionListener{
                int flag = 0;
                Produs p;
                @Override
                public void actionPerformed(ActionEvent e) {

                    p = new Produs(tn.getText(), tc.getText(), tt.getText(), Double.parseDouble(tp.getText()));
                    for (int i = 0; i < listProd.size(); i++)
                        if(listProd.get(i).getDenumire().compareTo(p.getDenumire()) == 0 && listProd.get(i).getCategorie().compareTo(p.getCategorie()) == 0 && listProd.get(i).getTaraOrigine().compareTo(p.getTaraOrigine()) == 0 && listProd.get(i).getPret() == p.getPret())
                        {          
                            table.setRowSelectionInterval(i, i);
                            flag = 1;
                            break;
                        }
                    if (flag == 0)
                        JOptionPane.showMessageDialog(null, "Produsul cautat nu exista!");
                    jf.setVisible(false);
                    jf.dispose();
                }       
            }
        }                       
}



