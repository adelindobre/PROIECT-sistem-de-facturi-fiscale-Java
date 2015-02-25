/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;
import sun.swing.MenuItemLayoutHelper;
/**
 *
 * @author Dobre
 */
public class ShowProduct extends JPanel {
    JTable table;
    TableRowSorter<TableModel> sorter;
    ArrayList<RowSorter.SortKey> sortKeys;
    int columnIndextoSort;
    
    public ShowProduct()
    {
        super(null);
        setSize(new Dimension(600,400));
        JLabel l = new JLabel("Sort products:");
        JRadioButton r1 = new JRadioButton("alphabetically");
        JRadioButton r2 = new JRadioButton("by country");
        r1.setBounds(340,10,150,30);
        r2.setBounds(500,10,150,30);
        r1.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,20));
        r1.setForeground(Color.BLACK);
        r2.setForeground(Color.BLACK);
        r2.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,20));
        l.setBounds(190,10,200,30);
        l.setRequestFocusEnabled(true);
        l.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        r2.setForeground(Color.BLACK);
        add(l);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);  
        add(r1);
        add(r2);
              
        ArrayList<Produs> listProd = new ArrayList<Produs>();
        for (int i = 0; i < Gestiune.getInstance().getProduse().size(); i++)
            if (Gestiune.getInstance().getProduse().get(i).getPret() != 0)
                listProd.add(Gestiune.getInstance().getProduse().get(i));
        
        ProdusTableModel tableModel = new ProdusTableModel(listProd);
        table = new JTable(tableModel);
        JScrollPane scrollPane  = new JScrollPane(table);
        scrollPane.setBounds(100,60,600,150);
        add(scrollPane);
        table.setAutoCreateColumnsFromModel(true);
        table.getColumnModel().getColumn(0).setHeaderValue("Denumire");
        table.getColumnModel().getColumn(1).setHeaderValue("Categorie");
        table.getColumnModel().getColumn(2).setHeaderValue("Tara_Origine");
        table.getColumnModel().getColumn(3).setHeaderValue("Pret");
        
        r1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter = new TableRowSorter<>(table.getModel());
                table.setRowSorter(sorter);
                sortKeys = new ArrayList<>(); 
                columnIndextoSort = 0;
                sortKeys.add(new RowSorter.SortKey(columnIndextoSort,SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();
            }
        });
        
        r2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                sorter = new TableRowSorter<>(table.getModel());
                table.setRowSorter(sorter);
                sortKeys = new ArrayList<>(); 
                columnIndextoSort = 2;
                sortKeys.add(new RowSorter.SortKey(columnIndextoSort,SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();
            }
        
        
        });
    }
}
