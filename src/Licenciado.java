import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Licenciado</h1>
 */
public class Licenciado extends Bolseiro{
    private ArrayList<Docente> orientadores = new ArrayList<>();
    private long duracao_bolsa;

    /**
     * Empty Constructor
     */
    Licenciado(){}

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

    public ArrayList<String> getOrientadoresNome() {
        ArrayList<String> doc= new ArrayList<>();
        for(Docente o: orientadores){
            doc.add(o.getNome());
        }
        return doc;
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
    public long calculaCusto(String dataInicial, String dataFinal) {
        duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(dataInicial).withDayOfMonth(1),  LocalDate.parse(dataFinal).withDayOfMonth(1));
        return duracao_bolsa*500;
    }

    public int getCusto() {
        return 500;
    }

    public long getDuracao_bolsa() {
        return duracao_bolsa;
    }

    @Override
    public String toString() {
        return "Licenciado{" + super.toString() +
                ", orientadores=" + orientadores +
                ", custo bolsa=" + calculaCusto(getDataInicio(), getDataFim()) +
                ", duração bolsa=" + duracao_bolsa +
                '}';
    }
}
