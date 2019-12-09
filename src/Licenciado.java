import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Licenciado</h1>
 */
public class Licenciado extends Bolseiro{
    private ArrayList<Docente> orientadores = new ArrayList<>();;

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
     * Get orientadores
     * @return orientadores
     */
    public ArrayList<Docente> getOrientadores() {
        return orientadores;
    }

    /**
     * Set orientadores
     * @param orientadores
     */
    public void setOrientadores(ArrayList<Docente> orientadores) {
        this.orientadores = orientadores;
    }

    /**
     * Method that calculates the cost for the project of a Bolseiro.
     * @return total cost for the project
     */
    public double calculaCusto(String dataInicial, String dataFinal) {
        long duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(dataInicial).withDayOfMonth(1),  LocalDate.parse(dataFinal).withDayOfMonth(1));
        System.out.println("Duração da bolsa: " + duracao_bolsa + " meses");
        return duracao_bolsa*500;
    }

    public int getCusto() {
        return 500;
    }

    @Override
    public String toString() {
        return "Licenciado{" + super.toString() +
                ", orientadores=" + orientadores +
                ", custo bolsa=" + calculaCusto(getDataInicio(), getDataFim()) +
                '}';
    }
}
