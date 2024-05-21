package utilityPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Classe che mette a disposizione il salvataggio automatico
 */
public class backupThread extends Thread{
    /**
     * TableModel
     */
    private tableModel tm;

    /**
     * Costruttore della classe
     * @param tm tableModel
     */
    public backupThread(tableModel tm){
        this.tm = tm;
        setDaemon(true);
    }

    /**
     * Nel momento in cui si chiamerà la funzione .start() allora il
     * thread salverà ogni 30 secondi i dati presenti nel table model
     * in un file chiamato backup.mtm
     */
    @Override
    public void run(){
        File newFile;
        while(true){
            try {
                this.sleep(30000); //ogni 30 secondi eseguo un backup
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                newFile = new File("backup.mtm");
                FileOutputStream f = new FileOutputStream(newFile);
                ObjectOutputStream os = new ObjectOutputStream(f);
                os.writeObject(this.tm.getArrMovementTotal());
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
