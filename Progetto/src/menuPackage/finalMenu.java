package menuPackage;

import utilityPackage.tableModel;

import javax.swing.*;

/**
 * Classe che mette a disposizione la menùBar con le seguenti voci:
 * <br>
 * - File
 * <br>
 * - Export as
 * <br>
 */
public class finalMenu extends JMenuBar {
    /**
     * Costruttore della classe
     *
     * @param tm       table model
     * @param t        tabella
     * @param totalBalance label per l'aggiornamento del saldo totale in caso di importazione
     */
    public finalMenu(tableModel tm, JTable t, JLabel totalBalance){
        fileMenu fMenu = new fileMenu("File", tm, t, totalBalance);
        exportMenu eMenu = new exportMenu("Export as", tm);
        this.add(fMenu);
        this.add(eMenu);
    }
}
