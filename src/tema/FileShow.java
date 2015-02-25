/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import java.io.RandomAccessFile;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.IOException;
/**
 *
 * @author Dobre
 */
public class FileShow {
    StringBuilder text;
    public FileShow(RandomAccessFile x) throws IOException
    {
        JFrame f = new JFrame("Show File");
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);
        byte[] arr = new byte[(int)x.length()];
        x.readFully(arr);
        text = new StringBuilder();
        for (int i = 0; i<arr.length; i++)
            text.append((char)arr[i]);
        
        
        try{
            SwingUtilities.invokeLater(new Runnable(){
                public void run()
                {
                    try{
                        doc.insertString(0, text.toString(), null);
                    } catch(BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(400, 300);
        f.setVisible(true);
    }
}
