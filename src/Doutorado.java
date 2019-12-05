/**
 * <h1>Class Doutorado</h1>
 */
class Doutorado extends Bolseiro{

    /**
     * Empty Constructor
     */
    Doutorado(){}

    /**
     * Constructor
     * @param nome
     * @param mail
     * @param dataInicio
     * @param dataFim
     */
    public Doutorado(String nome, String mail, String dataInicio, String dataFim) {
        super(nome, mail, dataInicio, dataFim, 1200);

    }
}

