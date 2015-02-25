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
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

public class Statistics implements ActionListener {
    JFrame jf;
    JComboBox jcb;
    ShowOption opt;
    
    public class ShowOption extends JPanel
    {
        private String optiune;
        JTextArea jta;
        public ShowOption()
        {   
            super(null);
            setVisible(true);
            setBounds(15,60,250,300);
            setSize(new Dimension(350,380));
            jta = new JTextArea();  
            jta.setBorder(new BevelBorder(BevelBorder.RAISED, Color.black, Color.DARK_GRAY.darker(), Color.WHITE.brighter(), Color.BLACK.brighter()));
            JScrollPane jp = new JScrollPane(jta);
            jp.setSize(new Dimension(350,380)); 
            super.add(jp);
        }
        public void setOptiune(String optiune)
        {
            this.optiune = optiune;
        }
        public void showInf() throws FileNotFoundException, IOException
        {
            StringBuilder sb;
            DecimalFormat format;
            RandomAccessFile x; 
            
            if (optiune.compareTo("Magazinul cu cele mai mari vanzari") == 0)
            {
               sb = new StringBuilder();
               Magazin max = Gestiune.getInstance().getMagazine().get(0);
               for (int i = 1; i < Gestiune.getInstance().getMagazine().size(); i++)
                   if (Gestiune.getInstance().getMagazine().get(i).getTotalCuTaxe() > max.getTotalCuTaxe())
                       max = Gestiune.getInstance().getMagazine().get(i);
               sb.append("\n\n" +"  Denumire Magazin: " + max.getNume() + "\n\n\n");
               format = new DecimalFormat("#.####");
               
               sb.append("  Total magazin fara taxe: " + format.format(max.getTotalFaraTaxe()) + "\n\n");
               sb.append("  Total magazin cu taxe: "  +  format.format(max.getTotalCuTaxe()) + "\n\n");
               sb.append("  Total magazin cu taxe scutite: " + format.format(max.getTotalCuTaxeScutite()));
               jta.setText(sb.toString());               
            }
            
            if (optiune.compareTo("Magazinul cu cele mai mari vanzari pentru fiecare tara") == 0)
            {
                x = new RandomAccessFile(new File("produse.txt"), "r");
                StringTokenizer st = new StringTokenizer(x.readLine());
                st.nextToken();
                st.nextToken();
                Vector<String> tari = new Vector<String>();
                while (st.hasMoreTokens())
                    tari.add(st.nextToken());
                x.close();
                
                sb = new StringBuilder();
                for (int i = 0; i < tari.size(); i++)
                {
                    Magazin max = Gestiune.getInstance().getMagazine().get(0);
                    for (int j = 1; j < Gestiune.getInstance().getMagazine().size(); j++)
                        if (Gestiune.getInstance().getMagazine().get(j).getTotalTaraCuTaxe(tari.get(i)) > max.getTotalTaraCuTaxe(tari.get(i)))
                            max = Gestiune.getInstance().getMagazine().get(j);
                    sb.append("\n  " + tari.get(i) + "\n\n");
                    sb.append("  Denumire Magazin: " + max.getNume() + "\n\n");
                    format = new DecimalFormat("#.####");         
                    sb.append("  Total magazin fara taxe: " + format.format(max.getTotalFaraTaxe()) + "\n");
                    sb.append("  Total magazin cu taxe: "  +  format.format(max.getTotalCuTaxe()) + "\n");
                    sb.append("  Total magazin cu taxe scutite: " + format.format(max.getTotalCuTaxeScutite()) + "\n");
                }
                jta.setText(sb.toString());                
            }
            
            if (optiune.compareTo("Magazinul cu cele mai mari vanzari pentru fiecare categorie") == 0)
            {
                x = new RandomAccessFile(new File("produse.txt"), "r");
                StringTokenizer st;
                Vector<String> categorie = new Vector<String>();
                x.readLine();
                String line = x.readLine();
                
                while (line != null)
                {
                    st = new StringTokenizer(line);
                    st.nextToken();
                    categorie.add(st.nextToken());
                    line = x.readLine();
                }                    
                sb = new StringBuilder();
                for (int i = 0; i < categorie.size(); i++)
                {
                    Magazin max = Gestiune.getInstance().getMagazine().get(0);
                    for (int j = 1; j < Gestiune.getInstance().getMagazine().size(); j++)
                        if (Gestiune.getInstance().getMagazine().get(j).getTotalCategorieCuTaxe(categorie.get(i)) > max.getTotalCategorieCuTaxe(categorie.get(i)))
                            max = Gestiune.getInstance().getMagazine().get(j);
                    
                    sb.append("\n  " + categorie.get(i) + "\n\n");
                    sb.append("  Denumire Magazin: " + max.getNume() + "\n\n");
                    format = new DecimalFormat("#.####");         
                    sb.append("  Total magazin fara taxe: " + format.format(max.getTotalFaraTaxe()) + "\n");
                    sb.append("  Total magazin cu taxe: "  +  format.format(max.getTotalCuTaxe()) + "\n");
                    sb.append("  Total magazin cu taxe scutite: " + format.format(max.getTotalCuTaxeScutite()) + "\n");
                    
                }
                jta.setText(sb.toString());
            }
            
            if (optiune.compareTo("factura cea mai mare") == 0)
            {
                sb = new StringBuilder();
                Factura max = Gestiune.getInstance().getMagazine().get(0).getf().firstElement();
                for (int i = 0; i < Gestiune.getInstance().getMagazine().size(); i++)
                    for (int j = 0; j < Gestiune.getInstance().getMagazine().get(i).getf().size(); j++)
                        if ( Gestiune.getInstance().getMagazine().get(i).getf().get(j).getTotalFaraTaxe() > max.getTotalFaraTaxe())
                            max = Gestiune.getInstance().getMagazine().get(i).getf().get(j);
                sb.append("\n\n  " + max.getDenumire() + "\n\n\n");
                format = new DecimalFormat("#.####");
                sb.append("  Total factura fara taxe: " + format.format(max.getTotalFaraTaxe()) + "\n\n");
                sb.append("  Total factura cu taxe: "  +  format.format(max.getTotalCuTaxe()));
                
                jta.setText(sb.toString());
            }
        }
    }
    
    public Statistics()
    {
        jf = new JFrame("Statistics");
        jf.setLayout(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
        jf.setSize(new Dimension(400,500));
        jf.setBackground(Color.WHITE);
        String[] options = {"Magazinul cu cele mai mari vanzari",
                            "Magazinul cu cele mai mari vanzari pentru fiecare tara",
                            "Magazinul cu cele mai mari vanzari pentru fiecare categorie",
                            "factura cea mai mare"};
        jcb = new JComboBox(options);
        jcb.setSelectedIndex(0);
        jcb.addActionListener(this);
        jcb.setBounds(15,20,350,30);
        jf.add(jcb);
        
        opt = new ShowOption();
        jf.add(opt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String optiune  = (String)cb.getSelectedItem();
        opt.setOptiune(optiune);
        try {        
            opt.showInf();
        } catch (IOException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
