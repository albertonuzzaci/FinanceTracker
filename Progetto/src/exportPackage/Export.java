package exportPackage;

import utilityPackage.tableModel;

/**
 * Classe astratta che mette a disposizione l'esportazione.
 * Ogni suo figlio implementerà in modo diverso il metodo esporta.
 */
public abstract class Export{
    /**
     * Table model
     */
    protected tableModel tm;

    /**
     * Costruttore della classe
     * @param tm table model
     */
    public Export(tableModel tm){
        this.tm = tm;
    }
    /**
     * Metodo astratto implementato dai figli che esporterà la tabella in base alle richieste dell'utente.
     */
    public abstract void esporta();
}
