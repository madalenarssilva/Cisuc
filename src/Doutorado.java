import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <h1>Class Doutorado</h1>
 */
public class Doutorado extends Bolseiro{

    private int custo = 1000;

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
    public double calculaCusto() {
        long duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(super.getDataInicio()).withDayOfMonth(1),  LocalDate.parse(super.getDataFim()).withDayOfMonth(1));
        return duracao_bolsa*custo;
    }

    @Override
    public String toString() {
        return "Doutorado{" + super.toString() +
                ", custo=" + custo +
                '}';
    }
}

