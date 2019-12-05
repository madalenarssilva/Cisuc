/**
 * <h1>Class Pessoa: </h1>
 */

abstract class Pessoa {

    private String nome;
    private String mail;

    /**
     * Empty Constructor
     */

    Pessoa() { }

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

    public abstract long calculaCusto();

}
