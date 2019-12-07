import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Licenciado</h1>
 */
public class Licenciado extends Bolseiro{
    private ArrayList<Docente> orientadores;
    private int custo = 500;

    /**
     * Empty Constructor
     */
    Licenciado(){
        ArrayList<Docente> orientadores;
    }

    /**
     * Constructor
     * @param nome
     * @param mail
     * @param dataInicio
     * @param dataFim
     */
    public Licenciado(String nome, String mail, String dataInicio, String dataFim, ArrayList<Docente> orientadores) {
        super(nome, mail, dataInicio, dataFim);
        this.orientadores = orientadores;
    }

    public Licenciado(String nome, String mail, String dataInicio, String dataFim) {
        super(nome, mail, dataInicio, dataFim);
    }

    /**
     * Get Custo
     * @return Custo
     */
    public int getCusto() {
        return custo;
    }

    /**
     * Set dataInicio
     * @param custo
     */
    public void setCusto(int custo) {
        this.custo = custo;
    }

    /**
     * Method that calculates the cost for the project of a Bolseiro.
     * @return total cost for the project
     */
    public double calculaCusto() {
        long duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(super.getDataFim()).withDayOfMonth(1),  LocalDate.parse(super.getDataFim()).withDayOfMonth(1));

        return duracao_bolsa*custo;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLicenciado:" +
                "\norientadores=" + orientadores +
                "\nCusto:" + custo;
    }
}
