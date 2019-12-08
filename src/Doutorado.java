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

    public Doutorado(String nome, String mail, String dataInicio, String dataFim, ArrayList<Projeto> projetos) {
        super(nome, mail, dataInicio, dataFim, projetos);
    }

    public Boolean isDoutorado(){
        return true;
    }

    /**
     * Method that calculates the cost for the project of a Bolseiro.
     * @return total cost for the project
     */
    public double calculaCusto(String dataInicio, String dataFim) {
        long duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(super.getDataInicio()).withDayOfMonth(1),  LocalDate.parse(super.getDataFim()).withDayOfMonth(1));
        return duracao_bolsa*1000;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDoutorado" + "\nCusto Doutorado:" + calculaCusto(getDataInicio(), getDataFim());
    }
}

