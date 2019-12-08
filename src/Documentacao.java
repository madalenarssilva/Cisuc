import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
     */
    public Documentacao(String dataInicio, int percentagemConclusao, String dataFim, Pessoa responsavel) {
        super(dataInicio, percentagemConclusao, dataFim, responsavel);
    }

    /**
     * Constructor
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     */
    public Documentacao(String dataInicio, int percentagemConclusao, String dataFim) {
        super(dataInicio, percentagemConclusao, dataFim);
    }

    /**
     * Method that calculates the execution rate of the task.
     * @return execution rate of the task
     */
    public long calculaTaxa() {
        long dias_passados = 0;

        return (long) (dias_passados*0.25);
    }
}