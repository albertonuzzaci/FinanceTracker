package panelsPackage;

import javax.swing.*;

import utilityPackage.tableModel;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import actionListenerPackage.SearchActionListener;

/**
 * Classe che mette a disposizione la ricerca di testo nel campo descrizione delle voci permettendo all'utente di scorrere tra le varie occorrenze.
 */
public class searchPanel extends JPanel {
    /**
     * Table model
     */
    private tableModel tm;
    /**
     * Tabella
     */
    private JTable t;
    /**
     * Bottoni per cercare/scorrere le occorrenze.
     */
    private JButton upButton, downButton, xButton, searchButton;
    /**
     * Vettore che contiene le occorrenze
     */
    private List<Integer> occurrences;
    /**
     * Campo di testo in cui l'utente inserisce cosa ricercare
     */
    private JTextField searchField;
    /**
     * Pannello che filtra per date
     */
    private filterPanel fPanel;
    private JLabel countOcc = new JLabel();

    public searchPanel(tableModel tm, JTable t, filterPanel f){
        this.tm = tm;
        this.t = t;
        this.fPanel = f;
        /**
         * CAMPO DI TESTO
         */
        searchField = new JTextField(30);
        searchField.setText("Enter something to search for");
        searchField.setForeground(new Color(111, 107, 107));
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchField.setText("");
                searchField.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        this.add(searchField);
        /**
         * BOTTONE RICERCA
         */
        searchButton = new JButton(this.returnFormattedIcon("/Images/lente.png"));
        this.add(searchButton);
        /**
         * BOTTONE FRECCIA SU
         */
        upButton = new JButton(this.returnFormattedIcon("/Images/upArrow.png"));
        this.add(upButton);
        /**
         * BOTTONE FRECCIA GIù
         */
        downButton = new JButton(this.returnFormattedIcon("/Images/downArrow.png"));
        this.add(downButton);
        /**
         * BOTTONE X
         */
        xButton = new JButton(this.returnFormattedIcon("/Images/x.png"));
        this.add(xButton);
        /**
         * LABEL
         */
        countOcc.setPreferredSize(new Dimension(30,12));
        countOcc.setFont(new Font(countOcc.getFont().toString(), Font.PLAIN, 15));
        this.add(countOcc);

        SearchActionListener searchAL = new SearchActionListener(this);
        searchButton.addActionListener(searchAL);
        upButton.addActionListener(searchAL);
        downButton.addActionListener(searchAL);
        xButton.addActionListener(searchAL);
        this.outOfSearch();

    }
    /**
     * Funzione per la formattazione delle icone dei pulsanti
     * @param filename percorso dell'icona
     * @return icona formattata
     */
    public ImageIcon returnFormattedIcon(String filename){
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(filename));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    /**
     * Funzione che setta lo stato Fuori-Ricerca abilitando solo il tasto della lente.
     */
    public void outOfSearch(){
        this.upButton.setEnabled(false);
        this.downButton.setEnabled(false);
        this.xButton.setEnabled(false);
        this.searchButton.setEnabled(true);
    }
    /**
     * Funzione che setta lo stato In-Ricerca disabilitando solo il tasto lente e abilitando tutti gli altri.
     */
    public void inSearch(){
        upButton.setEnabled(true);
        downButton.setEnabled(true);
        xButton.setEnabled(true);
        searchButton.setEnabled(false);
    }

    /**
     * Getter per il bottone UP
     * @return bottone UP
     */
    public JButton getUpButton() {
        return upButton;
    }
    /**
     * Getter per il bottone DOWN
     * @return bottone DOWN
     */
    public JButton getDownButton() {
        return downButton;
    }
    /**
     * Getter per il bottone X
     * @return bottone X
     */
    public JButton getxButton() {
        return xButton;
    }
    /**
     * Getter per il bottone SEARCH
     * @return bottone SEARCH
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Getter per il table model
     * @return table model
     */
    public tableModel getTm() {
        return tm;
    }

    /**
     * Getter per la tabella
     * @return tabella
     */
    public JTable getT() {
        return t;
    }

    /**
     * Getter per il vettore delle occorrenze
     * @return
     */
    public List<Integer> getOccurrences() {
        return occurrences;
    }

    /**
     * Getter per il campo di testo
     * @return
     */
    public JTextField getSearchField() {
        return searchField;
    }

    /**
     * Setter per il vettore delle occorrenze
     * @param occurrences nuovo vettore delle occorrenze
     */
    public void setOccurrences(java.util.List<Integer> occurrences) {
        this.occurrences = occurrences;
    }

    /**
     * Getter per il filter panel
     * @return filter panel
     */
    public filterPanel getfPanel() {
        return fPanel;
    }

    public JLabel getCountOcc() {
        return countOcc;
    }
}
