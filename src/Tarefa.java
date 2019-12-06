/**
 * <h1>Class Tarefa: </h1>
 * In this class we can add a responsible to the task and calculate the effort tax of each task.
 * We can also calculate the duration of the task.
 */

class Tarefa {
    private String dataInicio;
    private int percentagemConclusao;
    private String dataFim;
    private String responsavel;

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
     */
    public Tarefa(String dataInicio, int percentagemConclusao, String dataFim, String responsavel) {
        this.dataInicio = dataInicio;
        this.percentagemConclusao = percentagemConclusao;
        this.dataFim = dataFim;
        this.responsavel = responsavel;
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
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * Set Responsable
     * @param responsavel
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * method to calculate the duration of the task based on it's initial date and the ending date
     * @param dataInicio
     * @param DataFim
     * @return duracao
     */
    public int duracao(String dataInicio, String DataFim){
        int duracao = 0;
        return duracao;
    }


}