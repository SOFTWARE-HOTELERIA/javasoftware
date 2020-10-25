/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa1_presentacion.util;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author josel
 */
public class CellRenderTable extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
       if(value instanceof JButton){
           JButton btn = (JButton)value;
           return btn;
       }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
