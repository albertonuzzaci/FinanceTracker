package exportPackage;

import utilityPackage.tableModel;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Classe che mette a disposizione l'esportazione in formato txt
 */
public class TextExport extends Export{
    /**
     * Costruttore della classe che richiama il costruttore della classe padre tramite super
     * @param tm table model
     */
    public TextExport(tableModel tm){
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
                if(!fileToSave.getSelectedFile().getAbsolutePath().endsWith(".txt"))
                    f = new File(fileToSave.getSelectedFile().getAbsolutePath()+".txt");
                else
                    f = new File(fileToSave.getSelectedFile().getAbsolutePath());
                try {
                    FileWriter writer = new FileWriter(f);

                    for(int i = 0; i < this.tm.getArrMovement().size(); i++){
                        writer.write(this.tm.getArrMovement().get(i).getTextString());
                        if(this.tm.getArrMovement().size()-i != 1)
                            writer.write("\n");
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