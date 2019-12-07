import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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

public class Cisuc {

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

    Cisuc() {

        System.out.println("-----------Inicio----------");
        pessoas = new ArrayList<Pessoa>();
        projetos = new ArrayList<Projeto>();
        // Ask for username and password
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Username: ");
        String nome = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        //Menu
        while (!exitMenu) {
            Menu();
            if (!exitMenu) {
                Menu();
            }
        }
        System.exit(0);

        System.out.println("-----------Fim----------");
    }

    public void Menu() {

        /**
         * This menu is only a representation of our future graphic interface.
         * The menu has multiple options and according to the option the user chooses he can do different actions
         * like: Make projects, List undone Projects, List done projects, Edit Project and Exit
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1. Criar projetos.");
        System.out.println("2. Adicionar pessoas à app.");
        System.out.println("1. Associar pessoas a projetos.");
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
                criarPessoas();
                printPessoas();
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
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set Nome
     *
     * @param nome
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Get Password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //                                           FICHEIROS

    public ArrayList<Pessoa> lerFicheirosPessoas() {

        /**
         * Method to read people's info from file and use it to create instances of object Pessoa.
         * Docentes appear in the top of the file so that we can get Licenciados' and Mestres' orientadores.
         * If objects file exists, that is the file that's read. Otherwise, text file is read.
         *
         * @return ArrayList of objects Pessoa, representing the people read from file.
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

                    //Array com todos os docentes.
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
                                for (String orientador : s[5].split(",")) {
                                    int numOrientador = Integer.parseInt(orientador);
                                    for (Docente doc : docentes) {
                                        if (doc.getNumeroMecanografico() == numOrientador)
                                            orientadores_lic.add(doc);
                                    }
                                }
                                Licenciado licenciado = new Licenciado(s[1], s[2], s[3], s[4], orientadores_lic);
                                pessoas_lidas.add(licenciado);
                                break;

                            case "MESTRE":
                                ArrayList<Docente> orientadores_mestre = new ArrayList<>();

                                //Obter objetos Docente dos orientadores.
                                for (String orientador : s[5].split(",")) {
                                    int numOrientador = Integer.parseInt(orientador);
                                    for (Docente doc : docentes) {
                                        if (doc.getNumeroMecanografico() == numOrientador)
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
                    System.out.println("Erro a ler ficheiro de texto das pessoas.");
                }
            } else {
                System.out.println("Não foram encontrados ficheiros de pessoas.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pessoas_lidas;
    }

    public ArrayList<Projeto> lerFicheirosProjetos() {

        /**
         * Method to read projects' info from file and use it to create instances of object Projeto.
         * If objects file exists, that is the file that's read. Otherwise, text file is read.
         *
         * @return ArrayList of objects Projeto, representing the projects read from file.
         */

        //Array para guardar todas os projetos lidos do ficheiro.
        ArrayList<Projeto> projetos_lidos = new ArrayList<>();
        File fich_objetos = new File("projetos");
        File fich_texto = new File("projetos.txt");

        try {
            FileInputStream f = new FileInputStream(fich_objetos);
            ObjectInputStream o = new ObjectInputStream(f);
            projetos_lidos = (ArrayList<Projeto>) o.readObject();

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            //Se o ficheiro de objetos não existir, tentar ler ficheiro de texto.
            if (fich_texto.exists() && fich_texto.isFile()) { //Verificar se o ficheiro de texto existe.
                try {
                    FileReader fr = new FileReader(fich_texto);
                    BufferedReader br = new BufferedReader(fr);

                    String line;
                    while ((line = br.readLine()) != null) { //Ler o ficheiro, linha a linha, até ao fim.
                        System.out.println(line);
                        String[] s = line.split("|"); //Separar cada linha nos vários campos.

                        Projeto projeto = new Projeto(s[0], s[1], s[2], s[3]);
                        projetos_lidos.add(projeto);
                    }
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Erro a ler ficheiro de texto dos projetos.");
                }
            } else {
                System.out.println("Não foram encontrados ficheiros de projetos.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return projetos_lidos;
    }

    public ArrayList<Tarefa> lerFicheirosTarefas() {

        /**
         * Method to read tasks' info from file and use it to create instances of object Tarefa.
         * If objects file exists, that is the file that's read. Otherwise, text file is read.
         *
         * @return ArrayList of objects Tarefa, representing the tasks read from file.
         */

        //Array para guardar todas as tarefas lidas do ficheiro.
        ArrayList<Tarefa> tarefas_lidas = new ArrayList<>();
        File fich_objetos = new File("tarefas");
        File fich_texto = new File("tarefas.txt");

        try {
            FileInputStream f = new FileInputStream(fich_objetos);
            ObjectInputStream o = new ObjectInputStream(f);
            tarefas_lidas = (ArrayList<Tarefa>) o.readObject();

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            //Se o ficheiro de objetos não existir, tentar ler ficheiro de texto.
            if (fich_texto.exists() && fich_texto.isFile()) { //Verificar se o ficheiro de texto existe.
                try {
                    FileReader fr = new FileReader(fich_texto);
                    BufferedReader br = new BufferedReader(fr);

                    String line;
                    while ((line = br.readLine()) != null) { //Ler o ficheiro, linha a linha, até ao fim.
                        System.out.println(line);
                        String[] s = line.split("|"); //Separar cada linha nos vários campos.

                        switch (s[0]) {
                            case "DESIGN":
                                Design design = new Design();
                                tarefas_lidas.add(design);
                                break;
                            case "DESENVOLVIMENTO":
                                Desenvolvimento desenvolvimento = new Desenvolvimento();
                                tarefas_lidas.add(desenvolvimento);
                                break;
                            case "DOCUMENTACAO":
                                Documentacao documentacao = new Documentacao();
                                tarefas_lidas.add(documentacao);
                                break;
                        }
                    }
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Erro a ler ficheiro de texto das tarefas.");
                }
            } else {
                System.out.println("Não foram encontrados ficheiros de tarefas.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tarefas_lidas;
    }

    public void escreverFicheirosObjetos(ArrayList<Pessoa> pessoas_lidas, ArrayList<Projeto> projetos_lidos, ArrayList<Tarefa> tarefas_lidas) {

        /**
         * Method used to write all of the read information from text files to object files pessoas, projetos and locais.
         */

        //Ficheiro das Pessoas.
        try {
            File f1 = new File("pessoas");
            FileOutputStream f = new FileOutputStream(f1);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(pessoas_lidas);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de objetos das pessoas não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro de objetos das pessoas");
        }

        //Ficheiro dos Projetos.
        try {
            File f1 = new File("projetos");
            FileOutputStream f = new FileOutputStream(f1);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(projetos_lidos);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de objetos dos projetos não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro de objetos dos projetos");
        }

        //Ficheiro das Tarefas.
        try {
            File f1 = new File("tarefas");
            FileOutputStream f = new FileOutputStream(f1);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(tarefas_lidas);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de objetos das tarefas não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro de objetos das tarefas");
        }
    }

    //                                          CRIAR PROJETOS E PESSOAS

    public void criarProjetos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação do Projeto------");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        // Não aceitar inputs vazios
        while(nome.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Nome: ");
            nome = scanner.nextLine();
        }

        System.out.println("Acrónimo: ");
        String acronimo = scanner.nextLine();
        // Não aceitar inputs vazios
        while(acronimo.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Acrónimo: ");
            acronimo = scanner.nextLine();
        }
        System.out.println("DataInicio (yyyy-MM-dd): ");
        String dataInicio = scanner.nextLine();
        // Não aceitar inputs vazios
        while(dataInicio.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Data início: ");
            dataInicio = scanner.nextLine();
        }
        System.out.println("DataFim (yyyy-MM-dd): ");
        String dataFim = scanner.nextLine();
        // Não aceitar inputs vazios
        while(acronimo.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Acrónimo: ");
            acronimo = scanner.nextLine();
        }

        Projeto p = new Projeto(nome, acronimo, dataInicio, dataFim);
        projetos.add(p);
    }

    public void printProjetos() {
        for (Projeto p : projetos) {
            System.out.println(p);
        }
    }

    public void criarPessoas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação Pessoas------");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Mail: ");
        String mail = scanner.nextLine();
        System.out.println("Escolha entre as opções:");
        System.out.println("1.Docente");
        System.out.println("2.Bolseiro");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                System.out.print("Numero mecanográfico: ");
                int numMecanografico = scanner.nextInt();
                System.out.print("Área Investigação: ");
                String areaInvestigacao = scanner.nextLine();

                Docente d = new Docente(nome, mail, numMecanografico, areaInvestigacao);
                pessoas.add(d);
                break;
            case 2:
                System.out.println("Data inicio: ");
                String dataInicio = scanner.nextLine();
                System.out.println("Data fim: ");
                String dataFim = scanner.nextLine();

                System.out.println("Escolha entre as opções:");
                System.out.println("1.Licenciatura");
                System.out.println("2.Mestrado");
                System.out.println("3.Doutoramento");
                int opcaoB = scanner.nextInt();

                switch (opcaoB) {
                    case 1:
                        Licenciado l = new Licenciado(nome, mail, dataInicio, dataFim);
                        pessoas.add(l);
                        break;
                    case 2:
                        Mestre m = new Mestre(nome, mail, dataInicio, dataFim);
                        pessoas.add(m);
                        break;
                    case 3:
                        Doutorado dr = new Doutorado(nome, mail, dataInicio, dataFim);
                        pessoas.add(dr);
                    default:
                        System.out.println("Escolha uma opção existente.");
                }
        }
    }

    public void printPessoas() {
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
    }

    public ArrayList<Docente> getDocente() {
        /**
         * Method to get a list of all People who are "Docentes".
         * @return ArrayList of docentes
         */
        ArrayList<Docente> docentes = new ArrayList<Docente>();
        // Imprimir lista de todos os docentes para o utilizador escolher
        if (pessoas.size() != 0) {
            for (Pessoa p : pessoas) {
                // Ver se é Docente ou Bolseiro
                if (p.getNumeroMecanografico() > 0) {
                    Docente dc = (Docente) p;
                    docentes.add(dc);
                } else {
                    //Não existem docentes
                    System.out.println("Insira Docentes na aplicação");
                    Menu();
                }
            }
        } else {
            //A lista das pessoas está vazia
            System.out.println("Insira Pessoas na aplicação.");
            Menu();
        }
        return docentes;
    }

    public ArrayList<Bolseiro> getBolseiros() {
        /**
         * Method to get a list of all People who are "Bolseiros".
         * @return ArrayList of bolseiros
         */
        ArrayList<Bolseiro> bolseiros = new ArrayList<Bolseiro>();
        // Imprimir lista de todos os bolseiros para o utilizador escolher
        if (pessoas.size() != 0) {
            for (Pessoa p : pessoas) {
                // Ver se é Docente ou Bolseiro
                if (p.getNumeroMecanografico() == 0) {
                    Bolseiro bs = (Bolseiro) p;
                    bolseiros.add(bs);
                } else {
                    //Não existem bolseiros
                    System.out.println("Insira Bolseiros na aplicação");
                    Menu();
                }
            }
        } else {
            //A lista das pessoas está vazia
            System.out.println("Insira Pessoas na aplicação.");
            Menu();
        }
        return bolseiros;
    }


    public void imprimirDocentes(ArrayList<Docente> docentes) {

        /**
         * Method to print list of all People who are Docente so user can choose his orientor.
         * @param ArrayList of docentes
         */

        if (pessoas.size() != 0) {
            // Imprimir lista de todos os docentes para o utilizador escolher
            for (Pessoa p : pessoas) {
                // Ver se é Docente ou Bolseiro
                if (p.getNumeroMecanografico() > 0) {
                    int i = 1;
                    // Imprimir todos os docentes
                    System.out.println(i + "." + p.getNome());
                    Docente dc = (Docente) p;
                    docentes.add(dc);
                    i++;
                } else {
                    //Não existem docentes
                    System.out.println("Insira Docentes na aplicação");
                    Menu();
                }
            }
        } else {
            //A lista das pessoas está vazia
            System.out.println("Insira Pessoas na aplicação.");
            Menu();
        }
    }
}


