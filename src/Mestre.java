import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <h1>Class Mestre</h1>
 */
public class Mestre extends Bolseiro{
    private ArrayList<Docente> orientadores = new ArrayList<>();
    long duracao_bolsa;

    /**
     * Constructor Inicializing Array orientadores
     */
    Mestre(){}

    /**
     * Constructor
     * @param nome
     * @param mail
     * @param dataInicio
     * @param dataFim
     * @param orientadores
     */
    public Mestre(String nome, String mail, String dataInicio, String dataFim, ArrayList<Docente> orientadores) {
        super(nome, mail, dataInicio, dataFim);
        this.orientadores = orientadores;
    }

    public Mestre(String nome, String mail, String dataInicio, String dataFim) {
        super(nome, mail, dataInicio, dataFim);
        this.orientadores = orientadores;
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
    public long calculaCusto(String dataInicio, String dataFim) {
        duracao_bolsa = ChronoUnit.MONTHS.between(LocalDate.parse(dataInicio).withDayOfMonth(1),  LocalDate.parse(dataFim).withDayOfMonth(1));
        return duracao_bolsa*800;
    }

    public int getCusto() {
        return 800;
    }

    public long getDuracao_bolsa() {
        return duracao_bolsa;
    }

    public ArrayList<String> getOrientadoresNome() {
        ArrayList<String> doc= new ArrayList<>();
        for(Docente o: orientadores){
            doc.add(o.getNome());
        }
        return doc;
    }


    @Override
    public String toString() {
        return "Mestre{" + super.toString() +
                ", orientadores=" + orientadores +
                ", custo bolsa=" + calculaCusto(getDataInicio(), getDataFim()) +
                ", duração bolsa=" + duracao_bolsa +
                '}';
    }
}