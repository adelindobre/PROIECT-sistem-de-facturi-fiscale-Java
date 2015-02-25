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
import javax.swing.plaf.basic.BasicButtonUI;

public class ButtonTab extends JPanel {
    private final JTabbedPane pane;

    public ButtonTab(final JTabbedPane pane)
    {
        super(new FlowLayout(FlowLayout.LEFT,0,0));
        if (pane == null)
            throw  new NullPointerException("Is null");
        this.pane = pane;
        setOpaque(false);
        
        JLabel label = new JLabel() {
            public String getText() {
                int i = pane.indexOfTabComponent(ButtonTab.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };
        
        add(label);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        
        JButton b = new TabButton(new ImageIcon("exit.png"));
        add(b);
        b.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
    }
    
    
    private class TabButton extends JButton implements ActionListener{
        public TabButton(ImageIcon x){
            super(x);
            int size = 16;
            setPreferredSize(new Dimension(size,size));
            setToolTipText("close this tab");
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
             
            addMouseListener(new buttonMouseListener()); ;       
            setRolloverEnabled(true);
            addActionListener(this);
        }
        
        public void actionPerformed(ActionEvent e)
        {
            int i = pane.indexOfTabComponent(ButtonTab.this);
            if (i != -1)
                pane.remove(i);
        }    
    }
    
     public class buttonMouseListener extends MouseAdapter {
            public void mouseEntered(MouseEvent e)
            {
                Component component = e.getComponent();
                if (component instanceof AbstractButton)
                {
                    AbstractButton button = (AbstractButton) component;
                    button.setBorderPainted(true);
                }
            }
            
            public void mouseExited(MouseEvent e) 
            {
                Component component = e.getComponent();
                if (component instanceof AbstractButton)
                {
                    AbstractButton button = (AbstractButton) component;
                    button.setBorderPainted(false);
                }
            }
    }

}
   
    

