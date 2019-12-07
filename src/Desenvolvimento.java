import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <h1>Class Desenvolvimento</h1>
 */
class Desenvolvimento extends Tarefa{

    /**
     * Empty Constructor
     */
    Desenvolvimento(){}

    /**
     * Constructor
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     * @param responsavel
     */
    public Desenvolvimento(String dataInicio, int percentagemConclusao, String dataFim, Pessoa responsavel) {
        super(dataInicio, percentagemConclusao, dataFim, responsavel);
    }

    /**
     * Method that calculates the execution rate of the task.
     * @return execution rate of the task
     */
    public long calculaTaxa() {
        long dias_passados = 0;

        return (long) (dias_passados*1);
    }
}