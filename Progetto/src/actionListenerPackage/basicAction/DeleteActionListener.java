package actionListenerPackage.basicAction;

import utilityPackage.tableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che mette a disposizione l'eliminazione di una riga selezionata
 */

public class DeleteActionListener implements ActionListener {
    /**
     * Table model
     */
    private tableModel tm;
    /**
     * Tabella
     */
    private JTable t;
    /**
     * Label che visualizza il bilancio
     */
    private JLabel balance;
    /**
     * Combo box con le diverse modalità di visualizzazione
     */
    private JComboBox mode;

    /**
     * Costruttore della classe
     * @param tm table model
     * @param t tabella
     * @param balance label che visualizza il bilancio
     * @param mode combo box con le diverse modalità di visualizzazione
     */
    public DeleteActionListener(tableModel tm, JTable t, JLabel balance, JComboBox mode){
        this.tm = tm;
        this.t = t;
        this.balance = balance;
        this.mode = mode;
    }
    /**
     * Funzione avviata al momento della pressione del tasto per l'eliminazione delle voci
     * @param e evento generato
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel valuesPanel = new JPanel();
        /**
         * CASO ELIMINAZIONE DURANTE RICERCA
         */
        if (this.tm.getIndexSearch() != -1){
            String errorMessage = new String("ERROR: exit from search by pressing button X before doing every action.");
            JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        /**
         * CASO ELIMINAZIONE DURANTE VISUALIZZAZIONE NON COMPLETA
         */
        else if(!mode.getSelectedItem().toString().equals("All")){
            String errorMessage = new String("ERROR: select \"All\" as the display mode and retry.");
            JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        /**
         * CASO TABELLA VUOTA
         */
        else if(this.tm.getArrMovement().size() == 0){
            String errorMessage = new String("ERROR: there're no movement. Add something and retry.");
            JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        else{
            int i = t.getSelectedRow();
            /**
             * CASO IN CUI NON è STATA SELEZIONATA NESSUNA RIGA
             */
            if(i == -1){
                String errorMessage = new String("ERROR: select a line to remove.");
                JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            }
            else{
                tm.getArrMovement().remove(i);
                balance.setText("Total Balance: "+ tm.getSaldo()+"€");
                tm.fireTableDataChanged();
            }
        }
    }
}
