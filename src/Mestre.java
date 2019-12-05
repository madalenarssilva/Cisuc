import java.util.ArrayList;

/**
 * <h1>Class Mestre</h1>
 */
class Mestre extends Bolseiro{
    private ArrayList<Docente> orientadores;

    /**
     * Empty Constructor
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
        super(nome, mail, dataInicio, dataFim, 1000);
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

    @Override
    public String toString() {
        return "Mestre{" +
                "orientadores=" + orientadores +
                '}';
    }
}