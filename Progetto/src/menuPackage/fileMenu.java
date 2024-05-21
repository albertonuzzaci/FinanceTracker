package menuPackage;

import exportPackage.Export;
import exportPackage.ImportExport;
import exportPackage.SaveExport;
import utilityPackage.tableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

/**
 *  Classe che mette a disposizione il menù "File"  nella MenùBar con le seguenti funzioni
 *  <br>
 *  - New - permette di creare una nuova tabella svuotandola da tutte le voci
 *  <br>
 *  - Save - salva la tabella in un file
 *  <br>
 *  - Open - apre la tabella di un file
 *  <br>
 *  - print - stampa la tabella
 *  <br>
 */
public class fileMenu extends JMenu implements ActionListener {
    /**
     * Table model
     */
    private tableModel tm;
    /**
     * Tabella
     */
    private JTable t;
    private JLabel saldoTot;

    /**
     * Costruttore della classe
     * @param name nome del menù che apparira nella menù bar
     * @param tm table model
     * @param t tabella
     * @param totalBalance label per l'aggiornamento del saldo totale in caso di importazione
     */
    public fileMenu(String name, tableModel tm, JTable t, JLabel totalBalance){
        super(name);
        this.tm = tm;
        this.t = t;
        this.saldoTot = totalBalance;

        JMenuItem newItem = new JMenuItem("New");
        newItem.setIcon(this.returnFormattedIcon("/Images/new.png"));
        this.add(newItem);
        newItem.addActionListener(this);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setIcon(this.returnFormattedIcon("/Images/save.png"));
        this.add(saveItem);
        saveItem.addActionListener(this);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setIcon(this.returnFormattedIcon("/Images/open.png"));
        this.add(openItem);
        openItem.addActionListener(this);

        JMenuItem stampaItem = new JMenuItem("Print");
        stampaItem.setIcon(this.returnFormattedIcon("/Images/printer.png"));
        this.add(stampaItem);
        stampaItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e assume un valore in base alla voce cliccata
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Export exportC;
        switch (e.getActionCommand()) {
            case "New":
                this.tm.clearAll();
                break;
            case "Save":
                exportC = new SaveExport(this.tm);
                exportC.esporta();
                break;
            case "Open":
                exportC = new ImportExport(this.tm);
                exportC.esporta();
                this.saldoTot.setText("Total Balance: "+ tm.getSaldo()+"€");


                break;
            case "Print":
                if(tm.getArrMovement().size() == 0){
                    String errorMessage = new String("ERROR: table is empty.");
                    JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        this.t.print();
                    } catch (PrinterException ex) {
                        String errorMessage = new String("ERROR: error while trying to print");
                        JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        }

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
}
