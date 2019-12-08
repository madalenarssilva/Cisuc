import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Doutorado</h1>
 */
public class Doutorado extends Bolseiro{

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

    public Boolean isDoutorado(){
        return true;
    }

    /**
     * Method that calculates the cost for the project of a Bolseiro.
     * @return total cost for the project
     */
    public double calculaCusto(String dataInicio, String dataFim) {
        long duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(dataInicio).withDayOfMonth(1),  LocalDate.parse(dataFim).withDayOfMonth(1));
        System.out.println("Duração da bolsa: " + duracao_bolsa + " meses");
        return duracao_bolsa*1000;
    }

    @Override
    public String toString() {
        return "Doutorado{" + super.toString() +
                ", custo bolsa=" + calculaCusto(getDataInicio(), getDataFim()) +
                '}';
    }
}

