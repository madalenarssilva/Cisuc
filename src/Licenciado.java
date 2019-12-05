import java.util.ArrayList;

/**
 * <h1>Class Licenciado</h1>
 */
class Licenciado extends Bolseiro{
    private ArrayList<Docente> orientadores;

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
     * @param orientadores
     */
    public Licenciado(String nome, String mail, String dataInicio, String dataFim, ArrayList<Docente> orientadores) {
        super(nome, mail, dataInicio, dataFim, 800);
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
        return "Licenciado{" +
                "orientadores=" + orientadores +
                '}';
    }
}
