package exportPackage;

import org.jopendocument.dom.spreadsheet.SpreadSheet;
import utilityPackage.tableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.IOException;
/**
 * Classe che mette a disposizione l'esportazione in un file in formato ods
 */
public class OpenDocumentExport extends Export{
    /**
     * Costruttore della classe che richiama il costruttore della classe padre tramite super
     * @param tm table model
     */
    public OpenDocumentExport(tableModel tm){
        super(tm);
    }
    /**
     * Override del metodo della classe padre
     */
    @Override
    public void esporta() {
        File homeUser = new File(System.getProperty("user.home"));

        JFileChooser fileToSave = new JFileChooser(homeUser);
        if(fileToSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f;
            if (!fileToSave.getSelectedFile().getAbsolutePath().endsWith(".ods"))
                f = new File(fileToSave.getSelectedFile().getAbsolutePath() + ".ods");
            else
                f = new File(fileToSave.getSelectedFile().getAbsolutePath());
            try {
                //creo un table model con i dati trasformati in oggetti
                TableModel exportModel = new DefaultTableModel(tm.getMovementObjects(tm.getArrMovement()), tm.getColName());
                //creo il file
                SpreadSheet.createEmpty(exportModel).saveAs(f);
            } catch (IOException e) {

            }
        }

    }
}
