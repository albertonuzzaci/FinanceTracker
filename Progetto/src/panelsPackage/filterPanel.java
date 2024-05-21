package panelsPackage;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import utilityPackage.tableModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import actionListenerPackage.FilterActionListener;

/**
 * Classe che mette a disposizione il filtro per date delle voci. Il filtro può essere di tipo:
 * - ALL - Nessun filtro.
 * - Period - L'utente ha la possibilità di specificare una data di inzio e una di fine.
 * - Year,Month,Week,Day - L'utente ha la possibilità di specificare una data di inizio, la data di fine invece verrà calcolata aggiungendo rispettivamente un anno/mese/settimana/giorno alla data di inizio.
 */

public class filterPanel extends JPanel{
    /**
     * Data inizio e data fine per il filtro.
     */
    private JDatePicker fromDatePicker, toDatePicker;
    /**
     * Table model di riferimento
     */
    private tableModel tb;
    /**
     * Label che verrà ogni volta aggiornata per riportare il saldo totale delle voci visualizzate
     */
    private JLabel saldoTot;
    /**
     * ComboBox con i vari filtri disponibili
     */
    JComboBox mode;

    /**
     * Costruttore della classe
     * @param tm table model
     * @param saldoTot label che mostra il saldo delle voci visualizzate
     * @param mode modalità di visualizzazione (All, Year, Month, Day, Week, Period)
     */
    public filterPanel(tableModel tm, JLabel saldoTot, JComboBox mode){
        this.tb = tm;
        this.saldoTot = saldoTot;
        this.mode = mode;
        /*
        COMBO BOX CON LE VARIE OPZIONI
         */
        JLabel visualitationLabel = new JLabel("Visualitation Mode: ");
        this.add(visualitationLabel);
        String modeV[]={"All","Year","Month","Week","Day","Period"};
        for(String s: modeV){
            mode.addItem(s);
        }
        this.add(mode);
        /*
        DATA INIZIO
         */
        JLabel fromLabel = new JLabel("From");
        this.add(fromLabel);
        fromDatePicker = new JDatePicker(new UtilDateModel(new Date()), "dd/MM/yyyy");
        fromDatePicker.setPreferredSize(new Dimension(125, 30));
        this.add(fromDatePicker);
        /*
        DATA FINE
         */
        JLabel toLabel = new JLabel("To");
        this.add(toLabel);
        toDatePicker = new JDatePicker(new UtilDateModel(new Date()), "dd/MM/yyyy");
        toDatePicker.setPreferredSize(new Dimension(125, 30));
        this.add(toDatePicker);

        this.ableDate(false, false);
        /*
        AGGIUNTA ACTION LISTENER A DATA INIZIO, DATA FINE E COMBOBOX
         */
        ActionListener filterActionListener = new FilterActionListener(this);

        mode.addActionListener(filterActionListener);
        fromDatePicker.addActionListener(filterActionListener);
        toDatePicker.addActionListener(filterActionListener);
    }

    /**
     * Funzione che aggiorna i filtri passando al tableModel data inizio, data fine e un booleano che indica se il filtro è attivo.
     * Tramite la funzione updateArrayFiltered viene richiamato l'aggiornamento dell'array da visualizzare.
     * @param filter indica se il filtro è attivo o meno
     */
    public void refreshFilters(boolean filter){
        tb.setFromDate((Date) fromDatePicker.getModel().getValue());
        tb.setToDate((Date) toDatePicker.getModel().getValue());
        tb.setFilter(filter);
        tb.updateArrayFiltered();
        saldoTot.setText("Total Balance: "+tb.getSaldo()+" €");
    }

    /**
     * Funzione che disabilita tutti i campi
     */
    public void disableAll(){
        mode.setEnabled(false);
        this.ableDate(false, false);
    }

    /**
     * Funzione che abilita i campi in base al tipo di filtro impostato
     */
    public void ableAll(){
        mode.setEnabled(true);
        switch (mode.getSelectedItem().toString()){
            case "Year":
            case "Month":
            case "Week":
            case "Day":
                this.ableDate(true, false);
                break;
            case "All":
                this.ableDate(false, false);
                break;
            case "Period":
                this.ableDate(true, true);
                break;
        }
    }

    /**
     * Funzione per l'abilitazione/disabilitazione dei due picker di date
     * @param from disabilita/abilita la data di inizio
     * @param to disabilita/abilita la data di fine
     */
    public void ableDate(boolean from, boolean to){
        this.fromDatePicker.setEnabled(from);
        this.toDatePicker.setEnabled(to);
    }

    /**
     * Getter per la data di inizio
     * @return data di inizio
     */
    public JDatePicker getFromDatePicker() {
        return fromDatePicker;
    }
    /**
     * Getter per la data di fine
     * @return data di fine
     */
    public JDatePicker getToDatePicker() {
        return toDatePicker;
    }
    /**
     * Getter per il combobox della modalità di visualizzzaione
     * @return combobox
     */
    public JComboBox getMode() {
        return mode;
    }
}
