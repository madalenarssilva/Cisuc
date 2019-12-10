import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Doutorado</h1>
 */
public class Doutorado extends Bolseiro{

    private long duracao_bolsa;

    /**
     * Empty Constructor
     */
    Doutorado(){
    }

    /**
     * Constructor
     * @param nome
     * @param mail
     * @param dataInicio
     * @param dataFim
     */
    public Doutorado(String nome, String mail, String dataInicio, String dataFim) {
        super(nome, mail, dataInicio, dataFim);
    }

    /**
     * Method that calculates the cost for the project of a Bolseiro.
     * @return total cost for the project
     */
    public long calculaCusto(String dataInicio, String dataFim) {
        duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(dataInicio).withDayOfMonth(1),  LocalDate.parse(dataFim).withDayOfMonth(1));
        return duracao_bolsa*1000;
    }

    public int getCusto() {
        return 1000;
    }

    public long getDuracao_bolsa() {
        return duracao_bolsa;
    }

    @Override
    public String toString() {
        return "Doutorado{" + super.toString() +
                ", custo bolsa=" + calculaCusto(getDataInicio(), getDataFim()) +
                ", duração bolsa=" + duracao_bolsa +
                '}';
    }
}

