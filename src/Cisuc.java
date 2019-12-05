import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> Class Cisuc: </h1>
 * The Cisuc Class is the main of the application.
 * The aim of the application is to help the members of Cisuc (Informatics and Systems Center of the University of Coimbra)
 * manage and follow Investigation Projects.
 *
 * @author Inês Tabanez
 * @author Madalena Silva
 */

class Cisuc {

    private ArrayList<Pessoa> pessoas;
    private ArrayList<Projeto> projetos;
    private String nome;
    private String password;
    private boolean exitMenu = false;

    public static void main(String[] args) {
        new Cisuc();
    }

    /**
     * <h1>Constructor: </h1>
     * The Constructor has the Menu method
     */

    Cisuc(){

        System.out.println("-----------Inicio----------");
        pessoas = new ArrayList<Pessoa>();
        projetos = new ArrayList<Projeto>();

        // Ask for username and password
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Ufgdgsername: ");
        String nome = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        //Menu
        while(!exitMenu){
            Menu();
            if (!exitMenu) {
                Menu();
            }
        }
        System.exit(0);
    }

    public void Menu(){

        /**
         * This menu is only a representation of our future graphic interface.
         * The menu has multiple options and according to the option the user chooses he can do different actions
         * like: Make projects, List undone Projects, List done projects, Edit Project and Exit
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1. Criar projetos e associar pessoas.");
        System.out.println("2. Listar os projetos não concluídos na data estimada.");
        System.out.println("3. Listar os projectos concluídos.");
        System.out.println("4. Editar Projeto");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                criarProjetos();
                printProjetos();
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            case 4:
                System.out.println("4");
                break;
            case 5:
                exitMenu = true;
                System.out.println("Bye");
                break;
            default:
                System.out.println("Escolha uma opção existente.");
        }
    }

    /**
     * Get Nome
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

    /** Get Password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    public ArrayList<Pessoa> lerFicheirosPessoas() {

        /**
         * Method to read people info from file and use it to create instances of object Pessoa.
         * Docentes appear in the top of the file so that we can get Licenciados' and Mestres' orientadores.
         * If object file exists, that is the file that's read. Otherwise, text file is read.
         *
         * @return ArrayList of the objects Pessoa of people read from file.
         */

        //Array para guardar todas as pessoas lidas do ficheiro.
        ArrayList<Pessoa> pessoas_lidas = new ArrayList<>();
        File fich_objetos = new File("pessoas");
        File fich_texto = new File("pessoas.txt");

        try {
            FileInputStream f = new FileInputStream(fich_objetos);
            ObjectInputStream o = new ObjectInputStream(f);
            pessoas_lidas = (ArrayList<Pessoa>) o.readObject();

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            //Se o ficheiro de objetos não existir, tentar ler ficheiro de texto.
            if (fich_texto.exists() && fich_texto.isFile()) { //Verificar se o ficheiro de texto existe.
                try {
                    FileReader fr = new FileReader(fich_texto);
                    BufferedReader br = new BufferedReader(fr);

                    ArrayList<Docente> docentes = new ArrayList<>();

                    String line;
                    while ((line = br.readLine()) != null) { //Ler o ficheiro, linha a linha, até ao fim.
                        System.out.println(line);
                        String[] s = line.split("|"); //Separar cada linha nos vários campos.

                        switch (s[0]) {
                            case "DOCENTE":
                                int numMecanografico = Integer.parseInt(s[3]);
                                Docente docente = new Docente(s[1], s[2], numMecanografico, s[4]);
                                docentes.add(docente);
                                pessoas_lidas.add(docente);
                                break;

                            case "LICENCIADO":
                                ArrayList<Docente> orientadores_lic = new ArrayList<>();

                                //Obter objetos Docente dos orientadores.
                                for (String orientador : s[5].split(";")) {
                                    for (Docente doc : docentes) {
                                        if (doc.getNome().equals(orientador))
                                            orientadores_lic.add(doc);
                                    }
                                }
                                Licenciado licenciado = new Licenciado(s[1], s[2], s[3], s[4], orientadores_lic);
                                pessoas_lidas.add(licenciado);
                                break;

                            case "MESTRE":
                                ArrayList<Docente> orientadores_mestre = new ArrayList<>();

                                //Obter objetos Docente dos orientadores.
                                for (String orientador : s[5].split(";")) {
                                    for (Docente doc : docentes) {
                                        if (doc.getNome().equals(orientador))
                                            orientadores_mestre.add(doc);
                                    }
                                }
                                Mestre mestre = new Mestre(s[1], s[2], s[3], s[4], orientadores_mestre);
                                pessoas_lidas.add(mestre);
                                break;

                            case "DOUTORADO":
                                Doutorado doutorado = new Doutorado(s[1], s[2], s[3], s[4]);
                                pessoas_lidas.add(doutorado);
                                break;
                        }
                    }
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Erro a ler ficheiro de texto.");
                }
            } else {
                System.out.println("Não foram encontrados ficheiros.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pessoas_lidas;
    }

    public void criarProjetos(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação do Projeto------");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Acrónimo: ");
        String acronimo = scanner.nextLine();
        System.out.println("DataInicio: ");
        String dataInicioP = scanner.nextLine();
        System.out.println("DataFim: ");
        String dataFimP = scanner.nextLine();

        Projeto p = new Projeto(nome, acronimo, dataInicioP,dataFimP);
        addProjeto(p);
    }

    public void addProjeto(Projeto p){
        projetos.add(p);
    }

    public void printProjetos(){
        for (Projeto p: projetos){
            System.out.println(p);
        }
    }

}

