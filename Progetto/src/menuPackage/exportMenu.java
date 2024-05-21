package menuPackage;

import exportPackage.*;
import utilityPackage.tableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che mette a disposizione il menù "Export As"  nella MenùBar con le seguenti funzioni
 * <br>
 * - CSV - esporta la tabella in formato .CSV
 * <br>
 * - Text - esporta la tabella in formato .txt
 * <br>
 * - OpenDocument - esporta la tabella in formato .odt
 * <br>
 */

public class exportMenu extends JMenu implements ActionListener {
    /**
     * Table Model
     */
    private tableModel tm;

    /**
     * Costruttore della classe
     * @param name nome del menù che apparira nella menù bar
     * @param tm table model
     */
    public exportMenu(String name, tableModel tm){
        super(name);
        this.tm = tm;
        JMenuItem csvItem = new JMenuItem("CSV");
        this.add(csvItem);
        csvItem.addActionListener(this);

        JMenuItem textItem = new JMenuItem("Text");
        this.add(textItem);
        textItem.addActionListener(this);

        JMenuItem openDItem = new JMenuItem("Open Document");
        this.add(openDItem);
        openDItem.addActionListener(this);
    }
    /**
     * Action performed
     * @param e assume un valore in base alla voce cliccata
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Export exportC;
        switch (e.getActionCommand()){
            case "CSV":
                exportC = new CSVExport(this.tm);
                exportC.esporta();
                break;
            case "Text":
                exportC = new TextExport(this.tm);
                exportC.esporta();
                break;
            case "Open Document":
                exportC = new OpenDocumentExport(this.tm);
                exportC.esporta();
                break;
            default:
                System.out.println("Azione non contemplata");
                break;
        }
    }
}
