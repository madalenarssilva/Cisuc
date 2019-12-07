import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Projeto: </h1>
 * In each Project we can List, Make and eliminate tasks; Assign tasks to Project members; Update Conclusion Percentage
 * of each task, List tasks not initiated yet; List tasks not concluded; List tasks concluded; Indicate project cost and
 * Finish project
 */

public class Projeto {

    private String nome;
    private String acronimo;
    private String dataInicio;
    private String dataFim;
    private Docente ip;
    private ArrayList<Pessoa> pessoasEnvolvidas;
    private ArrayList<Tarefa> tarefas;

    /**
     * Constructor
     * Initiate List of People Involved in the project
     */

    Projeto() {
        pessoasEnvolvidas = new ArrayList<Pessoa>();
        tarefas = new ArrayList<Tarefa>();
    }

    /**
     * Constructor
     * @param nome
     * @param acronimo
     * @param ip
     * @param dataInicio
     * @param dataFim
     * @param pessoasEnvolvidas
     */
    public Projeto(String nome, String acronimo, Docente ip, String dataInicio, String dataFim, ArrayList<Pessoa> pessoasEnvolvidas) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ip = ip;
        this.pessoasEnvolvidas = pessoasEnvolvidas;
    }

    /**
     * Constructor
     * @param nome
     * @param acronimo
     * @param ip
     * @param dataInicio
     * @param dataFim
     */
    public Projeto(String nome, String acronimo, Docente ip, String dataInicio, String dataFim) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    /**
     * Get nome
     * @return nome
     */

    public String getNome() {
        return nome;
    }

    /**
     * Set nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Get Acronimo
     * @return
     */
    public String getAcronimo() {
        return acronimo;
    }

    /**
     * Set Acronimo
     * @param acronimo
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    /**
     * Get data Inicio
     * @return data Inicio
     */
    public String getDataInicio() {
        return dataInicio;
    }

    /**
     * Set data Inicio
     * @param dataInicio
     */
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Get Data Fim
     * @return dataFm
     */
    public String getDataFim() {
        return dataFim;
    }

    /**
     * Set Data Fim
     * @param dataFim
     */
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Get Ip
     * @return ip
     */
    public Docente getIp() {
        return ip;
    }

    /**
     * Set Ip
     * @param ip
     */
    public void setIp(Docente ip) {
        this.ip = ip;
    }

    /**
     * Get Pessoas Envolvidas
     * @return pessoasEnvolvidas
     */
    public ArrayList<Pessoa> getPessoasEnvolvidas() {
        return pessoasEnvolvidas;
    }

    /**
     * Set Pessoas Envolvidas
     * @param pessoasEnvolvidas
     */
    public void setPessoasEnvolvidas(ArrayList<Pessoa> pessoasEnvolvidas) {
        this.pessoasEnvolvidas = pessoasEnvolvidas;
    }

    /**
     * Get Tarefas
     * @return tarefas
     */
    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    /**
     * Set tarefas
     * @param tarefas
     */
    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    /**
     * Method to calculate the duration of the project based on its beginning date and its ending date.
     * @param dataInicio
     * @param dataFim
     * @return duracao
     */
    public long duracao(String dataInicio, String dataFim){

        long duracao_meses = ChronoUnit.MONTHS.between(LocalDate.parse(dataInicio).withDayOfMonth(1),  LocalDate.parse(dataFim).withDayOfMonth(1));
        System.out.println(duracao_meses);
        return duracao_meses;
    }

    public String toString(){
        return "Nome Projeto: " + nome + "\nAcronimo: " + acronimo + "\nData Inicio: " + dataInicio +
                "\nData Fim: " + dataFim + "\nDuracao: " + duracao(dataInicio,dataFim);
    }
}
