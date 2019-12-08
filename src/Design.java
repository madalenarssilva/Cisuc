import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Design</h1>
 */
class Design extends Tarefa{

    /**
     * Empty Constructor
     */
    Design(){}

    /**
     * Constructor
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     * @param responsavel
     */
    public Design(String dataInicio, int percentagemConclusao, String dataFim, Pessoa responsavel, ArrayList<Pessoa> responsaveis) {
        super(dataInicio, percentagemConclusao, dataFim, responsavel, responsaveis);
    }

    /**
     * Constructor
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     */
    public Design(String dataInicio, int percentagemConclusao, String dataFim) {
        super(dataInicio, percentagemConclusao, dataFim);
    }

    /**
     * Method that calculates the execution rate of the task.
     * @return execution rate of the task
     */
    public long calculaTaxa() {
        long dias_passados = 0;

        return (long) (dias_passados*0.5);
    }
}