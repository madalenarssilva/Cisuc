import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <h1>Class Bolseiro</h1>
 */
class Bolseiro extends Pessoa{
    private String dataInicio;
    private String dataFim;
    private long custo;

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
     * @param custo
     */
    public Bolseiro(String nome, String mail, String dataInicio, String dataFim, long custo) {
        super(nome, mail);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.custo = custo;
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

    /**
     * Get custo
     * @return custo
     */
    public long getCusto() {
        return custo;
    }

    /**
     * Set custo
     * @param custo
     */
    public void setCusto(long custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        return "Bolseiro{" +
                "dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", custo=" + custo +
                '}';
    }

    /**
     * Method that calculates the cost for the project of a Bolseiro.
     * @return total cost for the project
     */
    public long calculaCusto() {
        long duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(dataInicio).withDayOfMonth(1),  LocalDate.parse(dataFim).withDayOfMonth(1));

        return duracao_bolsa*custo;
    }
}
