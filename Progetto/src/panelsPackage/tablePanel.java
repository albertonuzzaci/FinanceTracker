package panelsPackage;

import utilityPackage.tableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Classe che mette a disposizione la tabella principale e una label aggiornata a ogni operazione che visualizzerà il saldo totale.
 */
public class tablePanel extends JPanel {
    /**
     * Costruttore della classe
     * @param t tabella
     * @param tm table model
     * @param saldoTot label che mostra il saldo delle voci visualizzate
     */
    public tablePanel(JTable t, tableModel tm, JLabel saldoTot){
        /**
         * NORTH
         */
        this.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        JTableHeader header = t.getTableHeader();
        JScrollPane scrollPane = new JScrollPane(t);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        t.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        t.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        t.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        scrollPane.setColumnHeaderView(header);
        t.setPreferredScrollableViewportSize(new Dimension(650, 500));
        t.getColumnModel().getColumn(0).setPreferredWidth(200);
        t.getColumnModel().getColumn(1).setPreferredWidth(800);
        t.getColumnModel().getColumn(2).setPreferredWidth(150);
        centerPanel.add(scrollPane);

        t.getTableHeader().setOpaque(false);
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setBackground(new Color(49, 49, 49));
        t.setRowHeight(20);

        /**
         * CENTER
         */
        JPanel southPanel = new JPanel();
        saldoTot.setText("Total Balance: "+tm.getSaldo()+" €");
        southPanel.add(saldoTot);

        this.add(centerPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.CENTER);
    }
}
