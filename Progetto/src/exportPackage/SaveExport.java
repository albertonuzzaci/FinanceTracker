package exportPackage;

import java.io.*;
import utilityPackage.tableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Classe che mette a disposizione il salvataggio in un file della tabella
 */
public class SaveExport extends Export {
    /**
     * Costruttore della classe che richiama il costruttore della classe padre tramite super
     *
     * @param tm table model
     */
    public SaveExport(tableModel tm) {
        super(tm);
    }

    /**
     * Override del metodo della classe padre
     */
    @Override
    public void esporta() {
        if (tm.getArrMovement().size() == 0) {
            String errorMessage = "ERROR: table is empty.";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            File homeUser = new File(System.getProperty("user.home"));

            JFileChooser fileToSave = new JFileChooser(homeUser);
            fileToSave.setFileFilter(new FileNameExtensionFilter("*.mtm", "mtm"));

            if (fileToSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filename = fileToSave.getSelectedFile().getAbsolutePath();
                if (!filename.endsWith(".mtm"))
                    filename = filename + ".mtm";

                File newFile = new File(filename);
                if (newFile.exists()) {
                    String message = new String("Do you want to overwrite the file?");
                    if (JOptionPane.showConfirmDialog(null, message, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION)
                        writeToFile(filename);
                }
                else
                    writeToFile(filename);

            }
        }

    }

    /**
     * Funzione che dato un filename crea un file con il nome filename con i dati della tabella
     * @param filename filename
     */
    public void writeToFile(String filename) {
        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(f);
            os.writeObject(this.tm.getArrMovementTotal());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
