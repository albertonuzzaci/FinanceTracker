package exportPackage;

import utilityPackage.tableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
/**
 * Classe che mette a disposizione l'importazione da un file della tabella
 */
public class ImportExport extends Export{
    /**
     * Costruttore della classe che richiama il costruttore della classe padre tramite super
     * @param tm table model
     */
    public ImportExport(tableModel tm){
        super(tm);
    }
    /**
     * Override del metodo della classe padre
     */
    @Override
    public void esporta(){
        File homeUser = new File(System.getProperty("user.home"));

        JFileChooser fileToImport = new JFileChooser(homeUser);
        fileToImport.setFileFilter(new FileNameExtensionFilter("*.mtm","mtm"));
        if(fileToImport.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            try {
                FileInputStream f = new FileInputStream(fileToImport.getSelectedFile().getAbsolutePath());
                ObjectInputStream is = new ObjectInputStream(f);
                this.tm.loadArray(is.readObject());
                if(this.tm.isFilter())
                    this.tm.updateArrayFiltered();
                is.close();
            }
            catch (IOException e) { e.printStackTrace(); }
            catch (ClassNotFoundException e) { e.printStackTrace();}
        }
    }
}

