import java.util.ArrayList;

/**
 * <h1>Class Docente</h1>
 */
public class Docente extends Pessoa {
    private int numeroMecanografico;
    private String areaInvestigacao;

    /**
     * Empty Constructor
     */
    Docente() {
    }

    /**
     * Constructor
     *
     * @param nome
     * @param mail
     * @param numeroMecanografico
     * @param areaInvestigacao
     */
    public Docente(String nome, String mail, int numeroMecanografico, String areaInvestigacao) {
        super(nome, mail);
        this.numeroMecanografico = numeroMecanografico;
        this.areaInvestigacao = areaInvestigacao;
    }

    /**
     * Get numeroMecanografico
     *
     * @return numeroMecanografico
     */
    public int getNumeroMecanografico() {
        return numeroMecanografico;
    }

    /**
     * Set numeroMecanografico
     *
     * @param numeroMecanografico
     */
    public void setNumeroMecanografico(int numeroMecanografico) {
        this.numeroMecanografico = numeroMecanografico;
    }

    /**
     * Get areaInvestigacao
     *
     * @return areaInvestigacao
     */
    public String getAreaInvestigacao() {
        return areaInvestigacao;
    }

    /**
     * Set areaInvestigacao
     *
     * @param areaInvestigacao
     */
    public void setAreaInvestigacao(String areaInvestigacao) {
        this.areaInvestigacao = areaInvestigacao;
    }

    /**
     * Method that calculates the cost for the project of a Docente.
     *
     * @return total cost for the project
     */


    public double calculaCusto(String dataInicial, String dataFinal) {
        return 0;
    }

    public int getCusto(){
        return 0;
    }

    @Override
    public String toString() {
        return "Docente{" + super.toString() +
                ", NumeroMecanografico='" + numeroMecanografico + '\'' +
                ", areaInvestigacao='" + areaInvestigacao + '\'' + ", custo='" + calculaCusto(null, null) + '\'' +
                '}';
    }
}
