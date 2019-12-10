import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Documentacao</h1>
 */
class Documentacao extends Tarefa{

    /**
     * Empty Constructor
     */
    Documentacao(){}

    /**
     * Constructor
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     * @param responsavel
     * @param duracaoEstimada
     */
    public Documentacao(String dataInicio, int percentagemConclusao, String dataFim, Pessoa responsavel, long duracaoEstimada) {
        super(dataInicio, percentagemConclusao, dataFim, responsavel, duracaoEstimada);
    }

    /**
     * Constructor
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     * @param duracaoEstimada
     */
    public Documentacao(String dataInicio, int percentagemConclusao, String dataFim, long duracaoEstimada) {
        super(dataInicio, percentagemConclusao, dataFim, duracaoEstimada);
    }

    /**
     * Method that returns the effort rate of the task.
     * @return effort rate of the task
     */
    public double getTaxaEsforco() {
        return 0.25;
    }

    @Override
    public String toString() {
        return "Documentacao{" + super.toString() +
                '}';
    }
}