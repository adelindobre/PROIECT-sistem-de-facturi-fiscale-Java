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
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ProdusTableModel extends AbstractTableModel{
    private static final int Column_Nume = 0; 
    private static final int Column_Categ = 1;
    private static final int Column_Tara = 2;
    private static final int Column_Pret = 3;
    
    private String[] columnNames = {"Denumire", "Categorie", "Tara_Origine", "Pret"};
    
    private ArrayList<Produs> listProdus;
    
    public ProdusTableModel(ArrayList<Produs> listProdus)
    {
        this.listProdus = listProdus;
    }
    
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
    @Override
    public int getRowCount()
    {
        return listProdus.size();
    }
    
    public void addRow(Produs p)
    {
        listProdus.add(p);
    }
    public void removeRow(int row)
    {
        listProdus.remove(row);
        super.fireTableRowsDeleted(row, row);
        super.fireTableDataChanged();
    }
    
    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }
    @Override
    public Class<?> getColumnClass (int columnIndex)
    {
        if (listProdus.isEmpty())
        {
            return Object.class;
        }
        return getValueAt(0,columnIndex).getClass();
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Produs prod = listProdus.get(rowIndex);
        Object val = null;
        
        switch(columnIndex){
            
            case Column_Nume:
                val = prod.getDenumire();
                break;
            case Column_Categ:
               val = prod.getCategorie();
                break;
            case Column_Tara:
                val = prod.getTaraOrigine();
                break;
            case Column_Pret:
                val = prod.getPret();
                break;
            default:
                throw new IllegalArgumentException("error");
        }
        return val;
    }
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        Produs prod = listProdus.get(rowIndex);
        if (columnIndex == Column_Nume)
            prod.setDenumire((String)value);
        if (columnIndex == Column_Categ)
            prod.setCategorie((String)value);
        if (columnIndex == Column_Tara)
            prod.setTaraOrigine((String)value);
        if (columnIndex == Column_Pret)
            prod.setPret((int)value);        
    }
    
}
