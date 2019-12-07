import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <h1>Class Bolseiro</h1>
 */
public abstract class Bolseiro extends Pessoa{
    private String dataInicio;
    private String dataFim;

    /**
     * Empty Constructor
     */
    Bolseiro(){}

    /**
     * Constructor
     * @param nome
     * @param mail
     * @param dataInicio
     * @param dataFim
     */
    public Bolseiro(String nome, String mail, String dataInicio, String dataFim) {
        super(nome, mail);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    /**
     * Get dataInicio
     * @return dataInicio
     */
    public String getDataInicio() {
        return dataInicio;
    }

    /**
     * Set dataInicio
     * @param dataInicio
     */
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Get dataFim
     * @return dataFim
     */
    public String getDataFim() {
        return dataFim;
    }

    /**
     * Set dataFim
     * @param dataFim
     */
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }


    @Override
    public String toString() {
        return "Bolseiro{" +
                "dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                '}';
    }
}
