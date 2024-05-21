package utilityPackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe che mette a disposizione l'oggetto che rappresenterà la riga della tabella ossia un singolo movimento bancario
 */
public class bankingMovement implements Serializable {
    /**
     * Descrizione del movimento
     */
    private String description;
    /**
     * Formatter per la data
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Data del movimento
     */
    private Date data;
    /**
     * Ammontare del movimento
     */
    private float amount;

    /**
     * Costruttore della classe bankingMovement
     * @param data data con cui verrà inizializzato il movimento
     * @param description descrizione con cui verrà inizializzato il movimento
     * @param amount ammontare con cui verrà inizializzato il movimento
     */
    public bankingMovement(Date data, String description, float amount){
        this.data = data;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Getter per l'attributo "Data"
     */
    public Date getData() {
        return this.data;
    }

    /**
     * Getter per l'attributo "Description"
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter per l'attributo "Amount"
     * Ogni amount viene convertito, prima di essere restituto, con 2 cifre decimale approssimando.
     */
    public float getAmount() {
        BigDecimal amountRounded = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        amount = amountRounded.floatValue();
        return amount;
    }

    /**
     * Restitusice la data sottoforma di stringa la data utilizzando un DateFormatter.
     */
    public String toStringDate(){
        return sdf.format(this.data);
    }

    /**
     * Restituisce l'oggetto bankingMovement sottoforma di oggetto
     * @return bankingMovement trasformato
     */
    public Object[] toObject(){
        return new Object[] { this.sdf.format(this.data), this.description, this.amount};
    }
    /**
     * Restituisce un'istanza della classe sottoforma di un'unica stringa.
     */
    public String getTextString(){
        return this.toStringDate() + " " + this.description + " " + Float.toString(this.amount);
    }

    /**
     * Restituisce un'istanza della classe sottoforma di una riga CSV.
     */
    public String[] getCSVstring(){
        String[] mCSV = {this.toStringDate(), this.description, Float.toString(this.amount)};
        return mCSV;
    }
}

