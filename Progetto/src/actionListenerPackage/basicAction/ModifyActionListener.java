package actionListenerPackage.basicAction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import utilityPackage.bankingMovement;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import utilityPackage.tableModel;

import javax.swing.*;

/**
 * Classe che mette a disposizione la modifica delle voci
 */
public class ModifyActionListener implements ActionListener {

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
    public ModifyActionListener(tableModel tm, JTable t, JLabel balance, JComboBox mode){
        this.tm = tm;
        this.t = t;
        this.balance=balance;
        this.mode = mode;
    }
    /**
     * Funzione avviata al momento della pressione del tasto per la modifica delle voci
     * @param e evento generato
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel valuesPanel = new JPanel();
        /**
         * CASO MODIFICA DURANTE RICERCA
         */
        if (this.tm.getIndexSearch() != -1){
            String errorMessage = new String("ERROR: exit from search by pressing button X before doing every action.");
            JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        /**
         * CASO MODIFICA DURANTE VISUALIZZAZIONE NON COMPLETA
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
                String errorMessage = new String("ERROR: select a line to edit.");
                JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            }
            else{
                bankingMovement m = tm.getArrMovement().get(i);

                valuesPanel.setPreferredSize(new Dimension(350, 95));

                JLabel amountLabel = new JLabel("Amount: ");
                JTextField amountField = new JTextField(5);
                amountField.setText(String.format("%.2f", m.getAmount()));
                amountField.setHorizontalAlignment(JTextField.CENTER);

                JLabel dateLabel = new JLabel("Date : ");
                JDatePicker datePicker = new JDatePicker(new UtilDateModel(m.getData()), "dd/MM/yyyy");
                datePicker.setPreferredSize(new Dimension(130, 30));

                JLabel descriptionLabel = new JLabel("Description : ");
                JTextField descriptionField = new JTextField(20);
                descriptionField.setText(m.getDescription());

                valuesPanel.add(dateLabel);
                valuesPanel.add(datePicker);
                valuesPanel.add(amountLabel);
                valuesPanel.add(amountField);
                valuesPanel.add(descriptionLabel);
                valuesPanel.add(descriptionField);

                int answer = JOptionPane.showConfirmDialog(null, valuesPanel, "Modify Movement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if(answer == JOptionPane.OK_OPTION){
                    float money = 0;
                    String moneyString = amountField.getText();
                    Date data = (Date)datePicker.getModel().getValue();

                    if(!moneyString.equals("")){
                        moneyString = moneyString.replace(',','.');
                        try{
                            money = Float.parseFloat(moneyString);
                            tm.modificaElementoArray(i, new bankingMovement(data,descriptionField.getText(),money));
                            balance.setText("Total Balance: "+tm.getSaldo()+"€");
                        }
                        catch (NumberFormatException exception){
                            String errorMessage = new String("ERROR: unknown format in amount field");
                            JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                        }
                    }


                }
            }

        }



    }
}
