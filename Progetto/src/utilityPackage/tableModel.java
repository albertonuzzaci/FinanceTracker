package utilityPackage;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.Date;
import java.util.List;

/**
 * Classe che mette a disposizione il table model a cui ruoterà intorno l'intero programma
 */
public class tableModel extends AbstractTableModel {
    /**
     * Nome delle colonne
     */
    private String[] ColName = {"Data", "Description", "Amount"};
    /**
     * Array totale
     */
    private List<bankingMovement> arrMovement;
    /**
     * Array visualizzato
     */
    private List<bankingMovement> arrMovementFiltered;
    /**
     * Boolean che indica se il filtro per date è attivo (true) o meno (false).
     */
    private boolean filter = false;
    /**
     * Date di riferimento per il filtro per date.
     */
    private Date fromDate, toDate;
    /**
     * Index a cui è arrivata la ricerca.
     */
    private Integer indexSearch = -1;

    /**
     * Costruttore della classe tableModel, inizializza l'array dei movimenti bancari.
     */
    public tableModel() {
        this.arrMovement = new ArrayList<>();
    }

    /**
     * Getter per l'array di movimenti completo.
     * @return Array di movimento completo.
     */
    public List<bankingMovement> getArrMovementTotal() {
        return arrMovement;
    }
    /**
     * Getter per l'array che contiene i movimenti bancari visualizzati.
     * @return Array filtrato se il filtro è attivo altrimenti l'array completo
     */
    public List<bankingMovement> getArrMovement() {
        if(filter)
            return arrMovementFiltered;
        return arrMovement;
    }

    /**
     * Getter per il filtro
     */
    public boolean isFilter() {
        return filter;
    }
    /**
     * Svuota sia l'array filtrato che quello completo
     */
    public void clearAll(){
        try {
            arrMovement.clear();
        }
        catch(Exception e) {}
        try {
            arrMovementFiltered.clear();
        }
        catch(Exception e) {}
        this.fireTableDataChanged();
    }

    /**
     * Trasforma l'array in un array di oggetti
     * @param v array movimenti da trasformare
     * @return array di oggetti
     */
    public Object[][] getMovementObjects(List<bankingMovement> v){
        Object[][] object = new Object[this.getRowCount()][3];
        for(int i = 0; i<this.getRowCount(); i++){
            object[i] = v.get(i).toObject();
        }
        return object;
    }

    /**
     * Getter per il nome delle colonne
     * @return vettore di stringhe delle colonne
     */
    public String[] getColName() {
        return ColName;
    }

    /**
     * Modifica un elemento dell'array completo.
     * @param index Posizione dell'elemento da modificare
     * @param newM Nuovo movimento bancario che andrà in posizione index
     */
    public void modificaElementoArray(int index, bankingMovement newM) {
        this.arrMovement.set(index, newM);
        this.fireTableDataChanged();
    }

    /**
     * Getter per il nome della colonna
     * @param col Numero della colonna richiesta
     * @return Nome della colonna
     */
    @Override
    public String getColumnName(int col){
        return ColName[col];
    }
    /**
     * Getter per il numero totale di colonne
     * @return Numero di colonne
     */
    @Override
    public int getColumnCount() {
        return ColName.length;
    }

    /**
     * Getter per il numero totale di righe. Se il filtro è attivo allora il numero di righe
     * corrisponderà al numero di elementi dell'array filtrato,
     * altrimenti a quello totale.
     * @return Numero totale di righe visualizzate
     */
    @Override
    public int getRowCount() {
        if(filter)
            return arrMovementFiltered.size();
        else
            return arrMovement.size();
    }

    /**Getter per il movimento bancario della tabella, utilizzato quindi per crearla.    *
     * @param row Riga del movimento bancario
     * @param col Colonna del movimento bancario
     * @return Movimento bancario in posizione [row,col]
     */
    @Override
    public Object getValueAt(int row, int col) {
        bankingMovement m;
        if(filter)
            m = (bankingMovement) this.arrMovementFiltered.get(row);
        else
            m = (bankingMovement) this.arrMovement.get(row);
        switch (col) {
            case 0:
                return m.toStringDate();
            case 1:
                return m.getDescription();
            case 2:
                return m.getAmount();
            default:
                return "";
        }
    }

    /**
     * Aggiunge una riga alla tabella.
     * @param m Movimento bancario aggiunto alla tabella
     */
    public void addRow(bankingMovement m) {
        this.arrMovement.add(m);
        this.fireTableDataChanged();
    }

    /**
     * Copia l'array di movimenti passato come parametro in quello totale
     * @param v Array da copiare in quello totale
     */
    public void loadArray(Object v){
        this.arrMovement = (List<bankingMovement>) v;
        this.fireTableDataChanged();
    }

    /**
     * Restituisce la somma dell'ammontare di tutte le righe visualizzate, ossia il saldo totale.
     * @return Saldo totale
     */
    public String getSaldo(){
        float sum = 0;
        for(int i = 0; i < getRowCount(); i++){
            if(filter)
                sum += this.arrMovementFiltered.get(i).getAmount();
            else
                sum += this.arrMovement.get(i).getAmount();
        }
        BigDecimal sumRounded = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP);
        return String.valueOf(sumRounded);
    }

    /**
     * Setter per il filtro che indica se è attivo o meno il filtro.
     */
    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    /**
     * Setter per la data da cui inzia la visualizzazione
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    /**
     * Setter per la data in cui finisce la visualizzazione
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Aggiorna l'array visualizzato in base al filtro impostato.
     */
    public void updateArrayFiltered() {
        arrMovementFiltered = new ArrayList<>();
        for(bankingMovement m: arrMovement){
            if(this.fromDate.compareTo(m.getData()) <=0 && this.toDate.compareTo(m.getData())>=0 )
                arrMovementFiltered.add(m);
        }
        this.fireTableDataChanged();
    }

    /**
     * Trova le occorrenze presenti nell'array visualizzato.
     * @param word parola ricercata
     * @param initalV array visualizzato
     * @return Array che contiene gli indici dove sono presenti occorrenze
     */
    public List<Integer> occurrencesArrayJava(String word, List<bankingMovement> initalV){
        List<Integer> v = new  ArrayList<Integer>();
        int counter = 0;
        for(bankingMovement m: initalV){
            if(m.getDescription().contains(word))
                v.add(counter);
            counter += 1;
        }
        return v;
    }

    /**
     * Getter per l'index a cui è arrivata la ricerca
     */
    public Integer getIndexSearch() {
        return indexSearch;
    }
    /**
     * Setter per l'index a cui è arrivata la ricerca
     */
    public void setIndexSearch(Integer indexSearch) {
        this.indexSearch = indexSearch;
    }

    /**
     * Incrementa l'indice della ricerca
     */
    public void incrementIndexSearch(){
        this.indexSearch = this.indexSearch+1;
    }
    /**
     * Decrementa l'indice della ricerca
     */
    public void decrementIndexSearch(){
        this.indexSearch = this.indexSearch-1;
    }
}


