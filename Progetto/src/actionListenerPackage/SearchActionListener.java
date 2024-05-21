package actionListenerPackage;

import panelsPackage.searchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Action listener che implementa il la ricerca per date
 */
public class SearchActionListener  implements ActionListener {
    /**
     * Pannello dove viene implementata la ricerca
     */
    private searchPanel sp;

    /**
     * Costruttore della classe
     * @param sp pannello dove viene implementata la ricerca
     */
    public SearchActionListener(searchPanel sp){
        this.sp = sp;
    }

    /**
     * Quando uno dei 4 pulsanti presenti sul pannello viene premuto si entra in questo actionPerformed
     * @param e evento generato
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * CASO RICERCA
         */
        if(e.getSource() == sp.getSearchButton()){
            if(sp.getSearchField().getText().length() == 0){
                String errorMessage = new String("Enter something to search for before clicking the button.");
                JOptionPane.showConfirmDialog(null, errorMessage, "Warning", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
            }
            else{
                sp.setOccurrences(sp.getTm().occurrencesArrayJava(sp.getSearchField().getText(), sp.getTm().getArrMovement()));
                if(sp.getOccurrences().size() == 0){
                    String warningMessage = new String("No occurrences were found.");
                    JOptionPane.showConfirmDialog(null, warningMessage, "Warning", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
                    sp.getSearchField().setText("");
                }
                else{
                    //In search
                    sp.getfPanel().disableAll();
                    sp.inSearch();
                    sp.getTm().setIndexSearch(0);
                    sp.getT().setRowSelectionInterval(sp.getOccurrences().get(sp.getTm().getIndexSearch()), sp.getOccurrences().get(sp.getTm().getIndexSearch()));
                    sp.getCountOcc().setText((sp.getTm().getIndexSearch()+1)+"/"+sp.getOccurrences().size());
                    sp.getT().setSelectionBackground(new Color(242, 220, 51));
                }
            }

        }
        /**
         * CASO UP
         */
        else if(e.getSource() == sp.getUpButton()){
            sp.getTm().decrementIndexSearch();
            if(sp.getTm().getIndexSearch() == -1)
                sp.getTm().setIndexSearch((sp.getOccurrences().size())-1);
            sp.getCountOcc().setText((sp.getTm().getIndexSearch()+1)+"/"+sp.getOccurrences().size());
            sp.getT().setRowSelectionInterval(sp.getOccurrences().get(sp.getTm().getIndexSearch()), sp.getOccurrences().get(sp.getTm().getIndexSearch()));
        }
        /**
         * CASO DOWN
         */
        else if(e.getSource() == sp.getDownButton()){
            sp.getTm().incrementIndexSearch();
            if(sp.getTm().getIndexSearch() == sp.getOccurrences().size())
                sp.getTm().setIndexSearch(0);
            sp.getCountOcc().setText((sp.getTm().getIndexSearch()+1)+"/"+sp.getOccurrences().size());
            sp.getT().setRowSelectionInterval(sp.getOccurrences().get(sp.getTm().getIndexSearch()), sp.getOccurrences().get(sp.getTm().getIndexSearch()));
        }
        /**
         * CASO X
         */
        else if(e.getSource() == sp.getxButton()){
            sp.getTm().setIndexSearch(-1);
            sp.getT().clearSelection();
            sp.getSearchField().setText("");
            sp.getCountOcc().setText("");
            sp.getT().setSelectionBackground(new Color(184,207,229));
            //Out of search
            sp.getfPanel().ableAll();
            sp.outOfSearch();
        }
    }
}
