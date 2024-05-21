package actionListenerPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panelsPackage.filterPanel;

/**
 * Action listener che implementa il filtro per date
 */
public class FilterActionListener implements ActionListener {
    /**
     * Pannello dove viene implementato il filtro
     */
    private filterPanel fp;

    /**
     * Costruttore della classe
     * @param fp pannello dove viene implementato il filtro
     */
    public FilterActionListener(filterPanel fp){
        this.fp=fp;
    }

    /**
     * Quando viene modificato il combobox o una delle due date si entra in queso Action performed
     * @param e evento generato
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (fp.getMode().getSelectedItem().toString()){
            case "Year":
                fp.ableDate(true, false);
                fp.getToDatePicker().getModel().setDate(fp.getFromDatePicker().getModel().getYear(),fp.getFromDatePicker().getModel().getMonth(),fp.getFromDatePicker().getModel().getDay());
                fp.getToDatePicker().getModel().addYear(1);
                break;
            case "Month":
                fp.ableDate(true, false);
                fp.getToDatePicker().getModel().setDate(fp.getFromDatePicker().getModel().getYear(),fp.getFromDatePicker().getModel().getMonth(),fp.getFromDatePicker().getModel().getDay());
                fp.getToDatePicker().getModel().addMonth(1);
                break;
            case "Week":
                fp.ableDate(true, false);
                fp.getToDatePicker().getModel().setDate(fp.getFromDatePicker().getModel().getYear(),fp.getFromDatePicker().getModel().getMonth(),fp.getFromDatePicker().getModel().getDay());
                fp.getToDatePicker().getModel().addDay(7);
                break;
            case "Day":
                fp.ableDate(true, false);
                fp.getToDatePicker().getModel().setDate(fp.getFromDatePicker().getModel().getYear(),fp.getFromDatePicker().getModel().getMonth(),fp.getFromDatePicker().getModel().getDay());
                break;
            case "All":
                fp.ableDate(false, false);
                fp.getToDatePicker().getModel().setDate(fp.getFromDatePicker().getModel().getYear(),fp.getFromDatePicker().getModel().getMonth(),fp.getFromDatePicker().getModel().getDay());
                break;
            case "Period":
                fp.ableDate(true, true);
                break;
        }
        if(fp.getMode().getSelectedItem().toString().equals("All"))
            fp.refreshFilters(false);
        else
            fp.refreshFilters(true);
    }
}
