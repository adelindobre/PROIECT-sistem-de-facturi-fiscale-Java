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

public class Login extends JFrame {
    JButton login;
    JPanel logpanel;
    JTextField user;
    JTextField pass;
    JButton newuser;
    JLabel username;
    JLabel  password;
    
    
    public Login() 
    {
        super("Sistem de facturi fiscale");
        
        login = new JButton("Login");        
        logpanel = new JPanel();
        user = new JTextField(15);
        pass = new JPasswordField(15);
        newuser = new JButton("New User");
        username = new JLabel("Username :");
        password = new JLabel("Password :");
        ImageIcon im = new ImageIcon("Signing business contract.jpg");
        JLabel j = new JLabel(im);
        Font labelfont = username.getFont();
        username.setFont(new Font(labelfont.getName(), Font.LAYOUT_LEFT_TO_RIGHT, 15));
        password.setFont(new Font(labelfont.getName(), Font.LAYOUT_LEFT_TO_RIGHT, 15));
        login.setFont(new Font(labelfont.getName(), Font.LAYOUT_LEFT_TO_RIGHT, 15));
        newuser.setFont(new Font(labelfont.getName(), Font.LAYOUT_LEFT_TO_RIGHT, 15));
        
        setSize(800,600);
        setLocation(500, 280);
        logpanel.setLayout(null);
        
        j.setBounds(0, 0, 800, 600);
        user.setBounds(570, 450, 175, 20);
        pass.setBounds(570, 480, 175, 20);
        login.setBounds(570,510,70,25);
        newuser.setBounds(645, 510, 100, 25);
        username.setBounds(480,450,80,20);
        password.setBounds(480,480,80,20);
        
        
        logpanel.add(login);
        logpanel.add(user);
        logpanel.add(pass);
        logpanel.add(newuser);
        logpanel.add(username);
        logpanel.add(password);
        logpanel.add(j);
        
        getContentPane().add(logpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);                      
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                try{
                    File check = new File("userPass.txt");
                    Scanner scan = new Scanner(check);
                    int flag = 0;
                    String usercheck="";         
                    String passcheck = "";              
                    String userget = user.getText();
                    String passget = pass.getText();
                    while (scan.hasNext())
                    {
                        usercheck = scan.nextLine();
                        passcheck = scan.nextLine();
                        if ((usercheck.compareTo(userget)) == 0 && passcheck.compareTo(passget) == 0)
                        {
                            flag = 1;
                            Load pageload = new Load();
                            dispose();
                            break;
                        }
                    }                    
                    if (flag == 0)
                        JOptionPane.showMessageDialog(null,"You are not user! Please create an account!");
                    
                } catch (IOException f)
                {
                    f.printStackTrace();
                } catch (BadLocationException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        newuser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NewUser user = new NewUser();
                dispose();
            }
        });
            
    }
        
        
}

