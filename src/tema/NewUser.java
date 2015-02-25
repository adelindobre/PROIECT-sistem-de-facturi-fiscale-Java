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
import javax.swing.text.StyleConstants;

public class NewUser extends JFrame{
    JButton create;
    JPanel newUserPanel;
    JTextField user;
    JTextField pass;
    JLabel username;
    JLabel  password;
    JLabel cerere;
    JLabel Titlu;
    
    public NewUser()
    {
        super("Registration");
        create = new JButton("Create");
        newUserPanel = new JPanel();
        user = new JTextField(15);
        pass = new JPasswordField(15);
        username = new JLabel("Username :");
        password = new JLabel("Password :");
        cerere = new JLabel("Please create an username and a password:");
        ImageIcon im = new ImageIcon("13732.png");
        Titlu = new JLabel("Registration", im, 0);
        Font labelfont = Titlu.getFont();
        Titlu.setFont(new Font(labelfont.getName(), Font.PLAIN,30));
        Titlu.setForeground(Color.GRAY);
        cerere.setForeground(Color.DARK_GRAY);
        username.setForeground(Color.DARK_GRAY);
        password.setForeground(Color.DARK_GRAY);
        create.setFont(new Font(labelfont.getName(), Font.LAYOUT_LEFT_TO_RIGHT, 15));
        username.setFont(new Font(labelfont.getName(), Font.LAYOUT_LEFT_TO_RIGHT, 15));
        password.setFont(new Font(labelfont.getName(), Font.LAYOUT_LEFT_TO_RIGHT, 15));
        cerere.setFont(new Font(labelfont.getName(), Font.ITALIC, 15));
        
        setSize(500,300);
        setLocation(500,280);
        newUserPanel.setLayout(null);
        newUserPanel.setBackground(Color.WHITE);
        
        create.setBounds(200, 205, 100, 30);
        user.setBounds(210,130,200,20);
        pass.setBounds(210,165,200,20); 
        username.setBounds(130,130,100,20);
        password.setBounds(130,165,100,20);
        cerere.setBounds(130,90,300,25);
        Titlu.setBounds(90,30,200,40);
        
        
        newUserPanel.add(create);
        newUserPanel.add(user);
        newUserPanel.add(pass);
        newUserPanel.add(username);
        newUserPanel.add(password);
        newUserPanel.add(cerere);
        newUserPanel.add(Titlu);
        
        getContentPane().add(newUserPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    File f = new File("userPass.txt");
                    FileWriter filewrite = new FileWriter(f,true);
                    filewrite.write(user.getText() + "\r\n" + pass.getText() + "\r\n");
                    filewrite.close();
                    JOptionPane.showMessageDialog(null, "Account has been created.");
                    dispose();
                    Login log = new Login();
                    
                
                } catch(IOException d)
                {
                    d.printStackTrace();
                }
            }
        });
    }
}
