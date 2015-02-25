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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Load{
     JFrame jfrm;
     JTextPane jtp;
     static Gestiune g = Gestiune.getInstance();
     public static final String text = "   Instructiuni de urmat!\n"
        + "     Dupa ce ati intrat in contul d-voastra, pentru rularea programului" 
        + " trebuie sa parcurgeti urmatorii pasi:\n\n"
        + "1. Din meniul FILE, selectati optiunea OPEN.\n"
        + "    Se va deschide o noua fereastra pentru a incarca fisierele necesare aplicatiei\n" 
        + "    facturi.txt, taxe.txt si produse.txt in directorul curent al aplicatiei.\n\n"
        + "2. In fereastra nou deschisa, puteti vizualiza fisierele existente prin apasarea\n" 
        + "    butonului OPEN FILE sau puteti incarca fisierele necesare in directorul aplicatiei prin\n"
        + "    apasarea butonului SAVE FILE.\n\n"
        + "3. Dupa realizarea pasului precedent, trebuie creat un nou fisier out.txt in care se vor\n"
        + "    afisa rezultatele. Acest lucru se realizeaza prin optiunea CREATE OUTPUT FILE din meniul OPTIONS.\n\n"
        + "4. In continuare, puteti reveni la pagina de logare prin apasarea butonului CLOSE din meniul FILE.\n"
        + "    De asemenea, puteti vizualiza fisierele incarcate si din meniul OPTION, prin apasarea butonului SHOW.\n\n" 
        + "5. In final, rularea aplicatiei se va face prin apasarea butonului PROCESSING, din meniul OPTION.\n";     
    public Load() throws BadLocationException
    {
        jfrm = new JFrame("Load Page");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(800,500);
        jfrm.setLocation(500,280);
        jfrm.setVisible(true);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiClose = new JMenuItem("Close");
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiExit);
        
        jmb.add(jmFile);
        
        JMenu jmOptions  = new JMenu("Options");
        JMenu jmShow = new JMenu("Show file");
        
        JMenuItem jmiProduse = new JMenuItem("Produse");
        JMenuItem jmiFacturi = new JMenuItem("Facturi");
        JMenuItem jmiTaxe = new JMenuItem("Taxe");
        
        jmShow.add(jmiProduse);
        jmShow.add(jmiFacturi);
        jmShow.add(jmiTaxe);
        
        jmOptions.add(jmShow);
        JMenuItem jmiCreate = new JMenuItem("Create output file");
        JMenuItem jmiProcess = new JMenuItem("Processing");
        jmOptions.add(jmiCreate);
        jmOptions.add(jmiProcess);
        
        JMenu jmEdit = new JMenu("Edit");
        JMenuItem jmiEditProducts = new JMenuItem("Edit Product file");
        jmEdit.add(jmiEditProducts);
        
        jmb.add(jmOptions);
        jmb.add(jmEdit);
        
         JMenu jmResults = new JMenu("Results");        
        JMenuItem jmiShowResults = new JMenuItem("Open results");
        jmResults.add(jmiShowResults);
  
        jmb.add(jmResults);
        
        jfrm.setJMenuBar(jmb);
        
        
         JPanel jp = new JPanel();
         
         StyleContext sc = new StyleContext();
         final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
         jtp = new JTextPane(doc);
         jtp.setSize(400, 400);
         jtp.setFont(new Font("Courier",Font.LAYOUT_LEFT_TO_RIGHT, 14));
         jtp.setForeground(Color.DARK_GRAY);
         final Style heading = sc.addStyle("Heading", null);
         heading.addAttribute(StyleConstants.Foreground , Color.red);
         heading.addAttribute(StyleConstants.FontSize, new Integer(22));
         heading.addAttribute(StyleConstants.FontFamily, "Arial");
         heading.addAttribute(StyleConstants.ALIGN_LEFT, new Boolean(true));
         heading.addAttribute(StyleConstants.SpaceBelow, new Float(5));
         final Style heading2 = sc.addStyle("Heading2",null);
         heading2.addAttribute(StyleConstants.Foreground, Color.DARK_GRAY);
         heading2.addAttribute(StyleConstants.FontFamily, "Arial Black");
         heading2.addAttribute(StyleConstants.Bold, new Boolean(true));
         heading2.addAttribute(StyleConstants.FontSize, new Integer(14));
           
        doc.insertString(0, text, null);
        doc.setParagraphAttributes(0, 1, heading, false);
        doc.setCharacterAttributes(151, 4, heading2,false);
        doc.setCharacterAttributes(175, 5, heading2,false);   
        doc.setCharacterAttributes(438, 9, heading2,false);
        doc.setCharacterAttributes(539, 9, heading2,false);
        doc.setCharacterAttributes(703, 18, heading2,false);
        doc.setCharacterAttributes(733, 7, heading2,false); 
        doc.setCharacterAttributes(819, 5, heading2,false);
        doc.setCharacterAttributes(836, 4, heading2,false);
        doc.setCharacterAttributes(910, 6, heading2,false);
        doc.setCharacterAttributes(942, 4, heading2,false);
        doc.setCharacterAttributes(1016, 10, heading2,false);
        doc.setCharacterAttributes(1039, 6, heading2,false);
         jp.add(new JScrollPane(jtp));
         jp.setVisible(true);
         jfrm.getContentPane().add(jp);
         
        jmiOpen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(  UIManager.getCrossPlatformLookAndFeelClassName());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FileChooser.createAndShowGUI();
                       
                    }
                });
                
            }
             
        });
        
        jmiClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Login logs = new Login();
                jfrm.dispose();
            }
        });
        jmiExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmiProduse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File("produse.txt");
                try {
                    RandomAccessFile x = new RandomAccessFile(f, "r");
                    FileShow fs = new FileShow(x);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
         jmiFacturi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File("facturi.txt");
                try {
                    RandomAccessFile x = new RandomAccessFile(f, "r");
                    FileShow fs = new FileShow(x);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
         
          jmiTaxe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File("taxe.txt");
                try {
                    RandomAccessFile x = new RandomAccessFile(f, "r");
                    FileShow fs = new FileShow(x);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
          jmiCreate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File out = new File("out.txt");
                try {
                    if (out.exists() == true)
                        JOptionPane.showMessageDialog(null, "The file is already created!");
                    else{
                    out.createNewFile();
                    JOptionPane.showMessageDialog(null, "The file was successfully created!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });  
          
          jmiEditProducts.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        UIManager.put("swing.boldMetal",Boolean.FALSE);
                        try {
                            new AdminProduse("Edit Products").runConstruct();
                        } catch (IOException ex) {
                            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Throwable ex) {
                            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
                  
          jmiShowResults.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Statistics stat = new Statistics();
            }
        });
          
          
          jmiProcess.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    g.getMagazine().clear();
                    g.getProduse().clear();
                    g.getTaxe().clear();
                    
                    Vector tara = new Vector();
                    Vector produs = new Vector();                
                    File f = new File("produse.txt");
                    RandomAccessFile x = new RandomAccessFile(f,"r");
                    String line;
                    line = x.readLine();
                    StringTokenizer st = new StringTokenizer(line," ");
                    while (st.hasMoreElements())
                        tara.add(st.nextToken());
                    line = x.readLine();
                    while (line != null)
                    {
                        st = new StringTokenizer(line," ");
                        while (st.hasMoreElements())
                            produs.add(st.nextToken());
                        for (int i = 2; i<tara.size();i++)
                            Gestiune.getInstance().getProduse().add(new Produs(produs.elementAt(0).toString(), produs.elementAt(1).toString(), tara.elementAt(i).toString(), Double.parseDouble(produs.get(i).toString()) ) );
                                                                        
                        produs.clear();
                        line = x.readLine();                
                    }
 
                    tara.clear();
                    x.close();

                    BufferedReader br = new BufferedReader(new FileReader("facturi.txt"));
                    StreamTokenizer s = new StreamTokenizer(br);
                    s.eolIsSignificant(true);
                    s.parseNumbers();
                    s.ordinaryChar(58);
                    s.ordinaryChar(32);
                    s.wordChars('_', '_');
                    int tip = s.nextToken();
                    while (tip != StreamTokenizer.TT_EOF && s.sval != null)
                    {
                        switch(tip)
                        {
                            case StreamTokenizer.TT_WORD:
                                if ( s.sval != null && s.sval.equals("Magazin"))
                                {
                                    tip = s.nextToken();
                                    tip = s.nextToken();
                                    if (s.sval.equals( "MiniMarket"))
                                    {
                                        tip = s.nextToken();
                                        tip = s.nextToken();
                                        MarketFactory.buildMarket(Magazin.MarketType.MiniMarket, s.sval, s);

                                    } else 
                                        if (s.sval.equals("MediumMarket"))
                                        {
                                            tip = s.nextToken();
                                            tip = s.nextToken();
                                            MarketFactory.buildMarket(Magazin.MarketType.MediumMarket, s.sval, s);                            
                                        }                        
                                     else   
                                        if (s.sval.equals("HyperMarket"))
                                        {
                                            tip = s.nextToken();
                                            tip = s.nextToken();
                                            MarketFactory.buildMarket(Magazin.MarketType.HyperMarket, s.sval, s);
                                        }

                                }
                                break;
                        }
                    }
                    br.close();
                                       
                    f = new File("taxe.txt");
                    x = new RandomAccessFile(f, "r");
                    String categorie;
                    int p;
                    line = x.readLine();
                    st = new StringTokenizer(line, "\\ ");
                    while (st.hasMoreElements())
                        tara.add(st.nextToken());
                    line = x.readLine();
                    while (line != null)
                    {
                        st = new StringTokenizer(line," ");
                        categorie = st.nextToken();
                        for (int i = 2; i<tara.size(); i++)               
                            g.getTaxe().put(tara.get(i).toString(), categorie, Integer.parseInt(st.nextToken()), i-1);
                       line  = x.readLine();
                    }
                    x.close();                  

                    for (int i = 0; i < g.getMagazine().size(); i++)
                        for (int j = 0; j < g.getMagazine().get(i).getf().size(); j++)
                            for (int k = 0; k < g.getMagazine().get(i).getf().get(j).getPc().size(); k++)
                                if (g.getMagazine().get(i).getf().get(j).getPc().get(k).getTaxa() == 0)
                                    g.getMagazine().get(i).getf().get(j).getPc().get(k).setTaxa(g.getTaxe().getHmapTaxa(g.getMagazine().get(i).getf().get(j).getPc().get(k).getProdus().getTaraOrigine(), g.getMagazine().get(i).getf().get(j).getPc().get(k).getProdus().getCategorie()));

                    PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));
                    pw.flush();
                    
                    Tiparire.iter = 1;
                    Tiparire mini = new Tiparire(pw);
                    mini.tiparireFisier(Magazin.MarketType.MiniMarket, MiniMarket.class);
                    Tiparire medium = new Tiparire(pw);
                    medium.tiparireFisier(Magazin.MarketType.MediumMarket, MediumMarket.class);
                    Tiparire hyper = new Tiparire(pw);
                    hyper.tiparireFisier(Magazin.MarketType.HyperMarket, HyperMarket.class);
                    pw.close(); 
                    
                } catch(IOException | NumberFormatException f)
                {
                     f.printStackTrace();
                }
          }
        });         
          
    }    
}


