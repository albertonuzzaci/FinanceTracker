package exportPackage;

import com.opencsv.CSVWriter;
import utilityPackage.tableModel;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe che mette a disposizione l'esportazione in formato CSV
 */
public class CSVExport extends Export{
    /**
     * Costruttore della classe che richiama il costruttore della classe padre tramite super
     * @param tm table model
     */
    public CSVExport(tableModel tm){
        super(tm);
    }

    /**
     * Override del metodo della classe padre
     */
    @Override
    public void esporta(){
        if(tm.getArrMovement().size() == 0){
            String errorMessage = "ERROR: table is empty.";
            JOptionPane.showMessageDialog(null, errorMessage,"Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            File homeUser = new File(System.getProperty("user.home"));

            JFileChooser fileToSave = new JFileChooser(homeUser);

            if(fileToSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                File f;
                if(!fileToSave.getSelectedFile().getAbsolutePath().endsWith(".csv"))
                    f = new File(fileToSave.getSelectedFile().getAbsolutePath()+".csv");
                else
                    f = new File(fileToSave.getSelectedFile().getAbsolutePath());
                try {
                    FileWriter outputfile = new FileWriter(f);
                    CSVWriter writer = new CSVWriter(outputfile);
                    for(int i = 0; i < this.tm.getArrMovement().size(); i++){
                        writer.writeNext(this.tm.getArrMovement().get(i).getCSVstring());
                    }
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
