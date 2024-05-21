package mainPackage;

import javax.swing.*;
import java.awt.*;
import panelsPackage.*;
import panelsPackage.tablePanel;
import utilityPackage.tableModel;

/**
 * Pannello principale di tutta l'applicazione dove vengono aggiunti gli altri pannelli
 */
public class mainPanel  extends JPanel {
    /**
     * Table model
     */
    private static tableModel tb = new tableModel();
    /**
     * Label che mostra il saldo delle voci visualizzate
     */
    private static JLabel totalBalance = new JLabel("",SwingConstants.CENTER);
    /**
     * Tabella
     */
    private static JTable t = new JTable(tb);
    /**
     * Combobox con le varie modalitù di visualizzazione
     */
    private JComboBox vMode = new JComboBox<>();

    public mainPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        /**
         * CENTER
         */
        tablePanel centerPanel = new tablePanel(t, tb, totalBalance);
        /**
         * NORTH
         */
        basicActionPanel NorthPanel = new basicActionPanel(t, tb, totalBalance, vMode);
        /**
         * SOUTH: center, south
         */
        filterPanel centerSouthPanel = new filterPanel(tb, totalBalance, vMode);
        searchPanel southSouthPanel = new searchPanel(tb,t, centerSouthPanel);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(centerSouthPanel, BorderLayout.CENTER);
        southPanel.add(southSouthPanel, BorderLayout.SOUTH);

        this.add(NorthPanel);
        this.add(centerPanel);
        this.add(southPanel);
    }

    /**
     * Getter per il table model
     * @return table model
     */
    public static tableModel getTableModel() {
        return tb;
    }
    /**
     * Getter per la tabella
     * @return tabella
     */
    public static JTable getTable() {
        return t;
    }

    public static JLabel getTotalBalance(){ return totalBalance;}
}
