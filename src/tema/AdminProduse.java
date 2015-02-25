/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
/**
 *
 * @author Dobre
 */
public class AdminProduse extends JFrame {
    private final int tabNumber = 4;
    private final JTabbedPane pane  = new JTabbedPane();
    
    public AdminProduse(String title) throws Throwable
    {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        initMenu();
        add(pane);
        pane.setBackground(Color.white);
    }
    public void runConstruct() throws IOException
    {
        pane.add("Show product file", new ShowProduct());
        initTabComponent(0);
        pane.add("Add product Item", new  AddProduct());
        initTabComponent(1);
        pane.add("Delete product Item", new  ChangeProduct() );
        initTabComponent(2);
        pane.add("Search product Item", new SearchProduct());
        initTabComponent(3);
        
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        setSize(new Dimension(800,400));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void initTabComponent(int i)
    {
        pane.setTabComponentAt(i, new ButtonTab(pane));
    }
    private void initMenu()
    {
        
        JMenuBar menuBar = new JMenuBar();
        JMenu OptionsMenu = new JMenu("Options");
        JMenuItem jmiExit = new JMenuItem("Exit");
        OptionsMenu.add(jmiExit);
        menuBar.add(OptionsMenu);
        setJMenuBar(menuBar);
        
        jmiExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
}
    

