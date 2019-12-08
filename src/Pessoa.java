import java.util.ArrayList;

/**
 * <h1>Class Pessoa: </h1>
 */

public abstract class Pessoa {

    private String nome;
    private String mail;
    private int numeroMecanografico;
    private ArrayList<Projeto> projetos;

    /**
     * Constructor inicializing numeroMecanografico.
     */

    Pessoa() {
        numeroMecanografico = 0;
        projetos = new ArrayList<>();
    }

    /**
     * Constructor
     * @param nome
     * @param mail
     */
    public Pessoa(String nome, String mail) {
        this.nome = nome;
        this.mail = mail;
    }

    /**
     * Constructor
     * @param nome
     * @param mail
     * @param projetos
     */
    public Pessoa(String nome, String mail, ArrayList<Projeto> projetos) {
        this.nome = nome;
        this.mail = mail;
        this.projetos = projetos;
    }

    /**
     * Get numeroMecanografic
     * @return numeroMecanografico
     */
    public int getNumeroMecanografico() {
        return numeroMecanografico;
    }

    /**
     * Set numeroMecanografico
     * @param numeroMecanografico
     */
    public void setNumeroMecanografico(int numeroMecanografico){
        this.numeroMecanografico = numeroMecanografico;
    }

    /**
     * Get nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set Nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Get Mail
     * @return mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Set mail
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Get projetos
     * @return projetos
     */
    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    /**
     * Set projetos
     * @param projetos
     */
    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }

    /**
     * Abstract method
     */
    public abstract double calculaCusto(String dataInicial, String dataFinal);

    public abstract Boolean isDoutorado();

    public String toString() {
        return "\nNome:" + nome + "\nMail:" + mail;
    }
}

