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
    private Pessoa responsavel;
    private ArrayList<Pessoa> responsaveis;

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
     *
     */
    public Tarefa(String dataInicio, int percentagemConclusao, String dataFim, Pessoa responsavel, ArrayList<Pessoa> responsaveis) {
        this.dataInicio = dataInicio;
        this.percentagemConclusao = percentagemConclusao;
        this.dataFim = dataFim;
        this.responsavel = responsavel;
        this.responsaveis = responsaveis;
    }

    public Tarefa(String dataInicio, int percentagemConclusao, String dataFim) {
        this.dataInicio = dataInicio;
        this.percentagemConclusao = percentagemConclusao;
        this.dataFim = dataFim;
    }

    /**
     * Get DataInicio
     * @return dataInicio
     */
    public ArrayList<Pessoa> getResponsaveis() {
        return responsaveis;
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
     * Get Responsable
     * @return responsable
     */
    public Pessoa getResponsavel() {
        return responsavel;
    }

    /**
     * Set Responsable
     * @param responsavel
     */
    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * method to calculate the duration of the task based on it's initial date and the ending date
     * @param dataInicio
     * @param dataFim
     * @return duracao
     */
    public int duracao(String dataInicio, String dataFim){
        int duracao = 0;
        return duracao;
    }

    public abstract long calculaTaxaExecucao();

    public abstract double getTaxaEsforco();

    @Override
    public String toString() {
        return "dataInicio='" + dataInicio + '\'' +
                ", percentagemConclusao=" + percentagemConclusao +
                ", dataFim='" + dataFim + '\'' +
                ", responsavel=" + responsavel +
                '}';
    }
}
