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
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class FileChooser extends JPanel implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    
    public FileChooser() {
        super(new BorderLayout());
        log = new JTextArea(5, 30);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        
        fc = new JFileChooser();
        openButton = new JButton("Deschidere fisier... ", createImageIcon("Open16.gif"));
        openButton.addActionListener(this);
        saveButton = new JButton("Salvare fisier... ", createImageIcon("Save16.gif"));
        saveButton.addActionListener(this);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        
        add(buttonPanel,BorderLayout.NORTH);
        add(logScrollPane,BorderLayout.CENTER);
        fc.setFileFilter(new FileNameExtensionFilter("txt","txt"));
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == openButton)
        {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file  = fc.getSelectedFile();
                try {
                    RandomAccessFile x = new RandomAccessFile(file, "r");
                    try {
                        FileShow fs = new FileShow(x);
                    } catch (IOException ex) {
                        Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                }
                log.append("Deschide: " + file.getName() + "." + newline);                
            } else
            {
                log.append("Comanda de deschidere anulata!" + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        } else if (e.getSource() == saveButton)
        {
            File dir = new File(System.getProperty("user.dir"));
            fc.setCurrentDirectory(dir);
            int returnVal = fc.showSaveDialog(this);            
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
            
                File f = fc.getSelectedFile();
                File mfile = new File( System.getProperty("user.dir") + "\\" + f.getName());
                try {
                    InputStream inStream = new FileInputStream(f);
                    OutputStream outStream = new FileOutputStream(mfile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while((length = inStream.read(buffer)) > 0)
                    {
                        outStream.write(buffer, 0, length);
                    }
                    inStream.close();
                    outStream.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                log.append("Salveaza: " + f.getName() + "." + newline);
                f.delete();
            } else{
                log.append("Comanda de salvare anulata!" + newline);
            }

            log.setCaretPosition(log.getDocument().getLength());
        }
    }
    public static ImageIcon createImageIcon(String path)
    {
        return new ImageIcon(path);
    }
    public static  void createAndShowGUI()
    {
        JFrame frame = new JFrame("Add files");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new FileChooser());
        frame.pack();
        frame.setVisible(true);
    }
}
