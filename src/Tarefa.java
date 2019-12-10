import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h1>Class Tarefa: </h1>
 * In this class we can add a responsible to the task and calculate the effort tax of each task.
 * We can also calculate the duration of the task.
 */

abstract class Tarefa implements Serializable {
    private String dataInicio;
    private int percentagemConclusao;
    private String dataFim;
    private long duracaoEstimada;
    private Pessoa responsavel;

    /**
     * Empty Constructor
     */
    Tarefa(){}

    /**
     * Constructor
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     * @param responsavel
     * @param duracaoEstimada
     *
     */
    public Tarefa(String dataInicio, int percentagemConclusao, String dataFim, Pessoa responsavel, long duracaoEstimada) {
        this.dataInicio = dataInicio;
        this.percentagemConclusao = percentagemConclusao;
        this.dataFim = dataFim;
        this.responsavel = responsavel;
        this.duracaoEstimada = duracaoEstimada;
    }

    /**
     * @param dataInicio
     * @param percentagemConclusao
     * @param dataFim
     * @param duracaoEstimada
     */
    public Tarefa(String dataInicio, int percentagemConclusao, String dataFim, long duracaoEstimada) {
        this.dataInicio = dataInicio;
        this.percentagemConclusao = percentagemConclusao;
        this.dataFim = dataFim;
        this.duracaoEstimada = duracaoEstimada;
    }

    /**
     * Get DataInicio
     * @return dataInicio
     */
    public String getDataInicio() {
        return dataInicio;
    }

    /**
     * Set DataInicio
     * @param dataInicio
     */
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Get percentagemConclusao
     * @return percentagemConclusao
     */
    public int getPercentagemConclusao() {
        return percentagemConclusao;
    }

    /**
     * Set percentagemConclusao
     * @param percentagemConclusao
     */
    public void setPercentagemConclusao(int percentagemConclusao) {
        this.percentagemConclusao = percentagemConclusao;
    }

    /**
     * Get DataFim
     * @return dataFim
     */
    public String getDataFim() {
        return dataFim;
    }

    /**
     * Set DataFim
     * @param dataFim
     */
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Get Person Responsible For Task
     * @return responsavel
     */
    public Pessoa getResponsavel() {
        return responsavel;
    }

    /**
     * Set Person Responsible For Task
     * @param responsavel
     */
    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * Get DuracaoEstimada
     * @return duracaoEstimada
     */
    public long getDuracaoEstimada() {
        return duracaoEstimada;
    }

    /**
     * Set duracaoEstimada
     * @param duracaoEstimada
     */
    public void setDuracaoEstimada(long duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }

    public abstract double getTaxaEsforco();

    @Override
    public String toString() {
        return "dataInicio='" + dataInicio + '\'' +
                ", percentagemConclusao=" + percentagemConclusao +
                ", dataFim='" + dataFim + '\'' +
                ", responsavel=" + responsavel + '\'' +
                ", duracaoEstimada=" + duracaoEstimada + '\'' +
                '}';
    }
}
