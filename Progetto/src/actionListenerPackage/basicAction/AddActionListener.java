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
 * Action listener che implementa l'aggiunta di voci nella tabella
 */
public class AddActionListener implements ActionListener {
    /**
     * Table model
     */
    private tableModel tm;
    /**
     * Label che mostra il bilancio delle voci visualizzate
     */
    private JLabel balance;

    /**
     * Costruttore della classe
     * @param tm table model
     * @param balance label per visualizzare il saldo totale
     */
    public AddActionListener(tableModel tm, JLabel balance){
        this.tm = tm;
        this.balance=balance;
    }

    /**
     * Funzione avviata al momento della pressione del tasto per aggiungere le voci
     * @param e evento generato
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.tm.getIndexSearch() != -1){
            String errorMessage = new String("ERROR: exit from search by pressing button X before doing every action.");
            JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        else{
            JPanel valuesPanel = new JPanel();
            valuesPanel.setPreferredSize(new Dimension(350, 95));

            JLabel amountLabel = new JLabel("Amount: ");
            JTextField amountField = new JTextField(5);
            amountField.setText(String.format("%.2f", 0.0));
            amountField.setHorizontalAlignment(JTextField.CENTER);

            JLabel dateLabel = new JLabel("Date : ");
            JDatePicker datePicker = new JDatePicker(new UtilDateModel(new Date()), "dd/MM/yyyy");
            datePicker.setPreferredSize(new Dimension(130, 30));

            JLabel descriptionLabel = new JLabel("Description : ");
            JTextField descriptionField = new JTextField(20);

            valuesPanel.add(dateLabel);
            valuesPanel.add(datePicker);
            valuesPanel.add(amountLabel);
            valuesPanel.add(amountField);
            valuesPanel.add(descriptionLabel);
            valuesPanel.add(descriptionField);

            if(JOptionPane.showConfirmDialog(null, valuesPanel, "Add movement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION){
                float money = 0;
                String moneyString = amountField.getText();
                Date data = (Date)datePicker.getModel().getValue();

                if(!moneyString.equals("")){
                    moneyString = moneyString.replace(',','.');
                    try{
                        money = Float.parseFloat(moneyString);
                        tm.addRow(new bankingMovement(data,descriptionField.getText(),money));
                        balance.setText("Total Balance: "+ tm.getSaldo()+"€");
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
