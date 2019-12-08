import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.security.krb5.internal.crypto.Des;

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

public class Cisuc {

    private ArrayList<Pessoa> pessoas;
    private ArrayList<Projeto> projetos;
    private ArrayList<Tarefa> tarefas;
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
        pessoas = new ArrayList<>();
        projetos = new ArrayList<>();
        tarefas = new ArrayList<>();

        lerFicheirosPessoas();
        lerFicheirosProjetos();
        lerFicheirosTarefas();
        //printProjetos();
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
        System.out.println("5. Criar Tarefas");
        System.out.println("6. Sair");
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
                criarTarefas();
                printTarefas();
                break;
            case 6:
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

    public void lerFicheirosPessoas() {

        /**
         * Method to read people's info from file and use it to create instances of object Pessoa.
         * Docentes appear in the top of the file so that we can get Licenciados' and Mestres' orientadores.
         * If objects file exists, that is the file that's read. Otherwise, text file is read.
         */

        File fich_objetos = new File("pessoas");
        File fich_texto = new File("pessoas.txt");

        try {
            FileInputStream f = new FileInputStream(fich_objetos);
            ObjectInputStream o = new ObjectInputStream(f);
            pessoas = (ArrayList<Pessoa>) o.readObject();

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
                        String[] s = line.split("[|]"); //Separar cada linha nos vários campos.

                        //Array com todos os docentes.
                        ArrayList<Docente> docentes = getDocentes();

                        switch (s[0]) {
                            case "DOCENTE":
                                int numMecanografico = Integer.parseInt(s[3]);
                                Docente docente = new Docente(s[1], s[2], numMecanografico, s[4]);
                                pessoas.add(docente);
                                break;

                            case "LICENCIADO":
                                ArrayList<Docente> orientadores_lic = new ArrayList<>();

                                //Obter objetos Docente dos orientadores.
                                for (String orientador : s[5].split("[,]")) {
                                    int numOrientador = Integer.parseInt(orientador);
                                    for (Docente doc : docentes) {
                                        if (doc.getNumeroMecanografico() == numOrientador)
                                            orientadores_lic.add(doc);
                                    }
                                }
                                Licenciado licenciado = new Licenciado(s[1], s[2], s[3], s[4], orientadores_lic);
                                pessoas.add(licenciado);
                                break;

                            case "MESTRE":
                                ArrayList<Docente> orientadores_mestre = new ArrayList<>();

                                //Obter objetos Docente dos orientadores.
                                for (String orientador : s[5].split("[,]")) {
                                    int numOrientador = Integer.parseInt(orientador);
                                    for (Docente doc : docentes) {
                                        if (doc.getNumeroMecanografico() == numOrientador)
                                            orientadores_mestre.add(doc);
                                    }
                                }
                                Mestre mestre = new Mestre(s[1], s[2], s[3], s[4], orientadores_mestre);
                                pessoas.add(mestre);
                                break;

                            case "DOUTORADO":
                                Doutorado doutorado = new Doutorado(s[1], s[2], s[3], s[4]);
                                pessoas.add(doutorado);
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
    }

    public void lerFicheirosProjetos() {

        /**
         * Method to read projects' info from file and use it to create instances of object Projeto.
         * If objects file exists, that is the file that's read. Otherwise, text file is read.
         */

        File fich_objetos = new File("projetos");
        File fich_texto = new File("projetos.txt");

        try {
            FileInputStream f = new FileInputStream(fich_objetos);
            ObjectInputStream o = new ObjectInputStream(f);
            projetos = (ArrayList<Projeto>) o.readObject();

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
                        String[] s = line.split("[|]"); //Separar cada linha nos vários campos.

                        //Array com todos os docentes.
                        ArrayList<Docente> docentes = getDocentes();
                        //Array com todos os bolseiros.
                        ArrayList<Bolseiro> bolseiros = getBolseiros();

                        //Procurar Docente com o número mecanográfico do ip contido no ficheiro de texto.
                        //Procurar docentes com números mecanográficos iguais aos dos investigadores contidos no ficheiro de texto.
                        int numMecanograficoIp = Integer.parseInt(s[4]);
                        Docente ip = new Docente();
                        ArrayList<Pessoa> pessoas_envolvidas = new ArrayList<>();

                        for (String investigador : s[5].split("[,]")) {
                            int numInvestigador = Integer.parseInt(investigador);
                            for (Docente doc : docentes) {
                                //É possível verificar as duas condições em simultâneo porque o ip não vai estar contido na lista dos investigadores.
                                if (doc.getNumeroMecanografico() == numMecanograficoIp)
                                    ip = doc;
                                else if (doc.getNumeroMecanografico() == numInvestigador)
                                    pessoas_envolvidas.add(doc);
                            }
                        }

                        //Se o projeto contiver bolseiros, adicioná-los ao array das pessoas envolvidas no projeto.
                        if (s.length == 7) {
                            for (String bolseiro : s[6].split("[,]")) {
                                for (Bolseiro bols : bolseiros) {
                                    if (bols.getMail().equals(bolseiro))
                                        pessoas_envolvidas.add(bols);
                                }
                            }
                        }

                        Projeto projeto = new Projeto(s[0], s[1], ip, s[2], s[3], pessoas_envolvidas);
                        projetos.add(projeto);
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
    }

    public void lerFicheirosTarefas() {

        /**
         * Method to read tasks' info from file and use it to create instances of object Tarefa.
         * If objects file exists, that is the file that's read. Otherwise, text file is read.
         * We assumed that all the tasks in the text file have not been initialized.
         */

        File fich_objetos = new File("tarefas");
        File fich_texto = new File("tarefas.txt");

        try {
            FileInputStream f = new FileInputStream(fich_objetos);
            ObjectInputStream o = new ObjectInputStream(f);
            tarefas = (ArrayList<Tarefa>) o.readObject();

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
                        String[] s = line.split("[|]"); //Separar cada linha nos vários campos.

                        //Encontrar o objeto Pessoa correspondente à pessoa responsável pela tarefa (MELHORAR).
                        Pessoa responsavel = pessoas.get(0);
                        for (Pessoa pessoa : pessoas) {
                            if (pessoa.getMail().equals(s[4])) {
                                responsavel = pessoa;
                            }
                        }
                        switch (s[0]) {
                            case "DESIGN":
                                Design design = new Design(s[1], 0, s[2], responsavel);

                                //Adicionar à lista de tarefas do projeto a que a tarefa está associada a tarefa em questão.
                                for (Projeto proj : projetos) {
                                    if (proj.getAcronimo().equals(s[3])) {
                                        proj.getTarefas().add(design);
                                    }
                                }
                                tarefas.add(design);
                                break;
                            case "DESENVOLVIMENTO":
                                Desenvolvimento des = new Desenvolvimento(s[1], 0, s[2], responsavel);

                                //Adicionar à lista de tarefas do projeto a que a tarefa está associada a tarefa em questão.
                                for (Projeto proj : projetos) {
                                    if (proj.getAcronimo().equals(s[3])) {
                                        proj.getTarefas().add(des);
                                    }
                                }
                                tarefas.add(des);
                                break;
                            case "DOCUMENTACAO":
                                Documentacao doc = new Documentacao(s[1], 0, s[2], responsavel);

                                //Adicionar à lista de tarefas do projeto a que a tarefa está associada a tarefa em questão.
                                for (Projeto proj : projetos) {
                                    if (proj.getAcronimo().equals(s[3])) {
                                        proj.getTarefas().add(doc);
                                    }
                                }
                                tarefas.add(doc);
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
    }

    public void escreverFicheirosObjetos() {

        /**
         * Method used to write all of the read information from text files to object files pessoas, projetos and locais.
         */

        //Ficheiro das Pessoas.
        try {
            File f1 = new File("pessoas");
            FileOutputStream f = new FileOutputStream(f1);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(pessoas);
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

            o.writeObject(projetos);
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

            o.writeObject(tarefas);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de objetos das tarefas não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro de objetos das tarefas");
        }
    }

    //                                           CRIAR PROJETOS, PESSOAS E TAREFAS

    public void criarProjetos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação do Projeto------");

        // NOME
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        // Não aceitar inputs vazios
        while(nome.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Nome: ");
            nome = scanner.nextLine();
        }
        // Não aceitar nomes repetidos
        Boolean flag= true;
            for (Projeto p : projetos) {
                while(flag) {
                if (p.getNome().equals(nome)) {
                    System.out.println("Nome já existente. Introduza outro.");
                    System.out.println("Nome: ");
                    nome = scanner.nextLine();
                } else {
                    flag = false;
                }
            }
        }

        // ACRONIMO
        System.out.println("Acrónimo: ");
        String acronimo = scanner.nextLine();
        // Não aceitar inputs vazios
        while(acronimo.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Acrónimo: ");
            acronimo = scanner.nextLine();
        }
        // Não aceitar acronimos repetidos
        Boolean flagA= true;
        for (Projeto p : projetos) {
            while(flagA) {
                if (p.getAcronimo().equals(acronimo)) {
                    System.out.println("Acrónimo já existente. Introduza outro.");
                    System.out.println("Acrónimo: ");
                    acronimo = scanner.nextLine();
                } else {
                    flagA = false;
                }
            }
        }

        // DATA INICIO
        System.out.println("DataInicio (yyyy-MM-dd): ");
        String dataInicio = scanner.nextLine();
        //Não aceitar inputs vazios nem datas inválidas.
        while(dataInicio.isEmpty() || !validarData1(dataInicio)) {
            if (dataInicio.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataInicio (yyyy-MM-dd): ");
            dataInicio = scanner.nextLine();
        }

        // DATA FIM
        System.out.println("DataFim (yyyy-MM-dd): ");
        String dataFim = scanner.nextLine();
        //Não aceitar inputs vazios nem datas inválidas.
        while(dataFim.isEmpty() || !validarData1(dataFim)) {
            if (dataFim.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataFim (yyyy-MM-dd): ");
            dataFim = scanner.nextLine();
        }

        //Não aceitar datas incoerentes.
        while(!validarData2(dataInicio, dataFim)) {
            System.out.println("DataInicio (yyyy-MM-dd): ");
            dataInicio = scanner.nextLine();
            System.out.println("DataFim (yyyy-MM-dd): ");
            dataFim = scanner.nextLine();
        }

        // Adicionar Projeto
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
        System.out.println("-----Criação da Pessoa------");

        // NOME
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        // Não aceitar inputs vazios
        while(nome.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Nome: ");
            nome = scanner.nextLine();
        }
        // Não aceitar nomes repetidos
        Boolean flag= true;
        for (Pessoa p : pessoas) {
            while(flag) {
                if (p.getNome().equals(nome)) {
                    System.out.println("Nome já existente. Introduza outro.");
                    System.out.println("Nome: ");
                    nome = scanner.nextLine();
                } else {
                    flag = false;
                }
            }
        }

        // MAIL
        System.out.println("Mail: ");
        String mail = scanner.nextLine();
        // Não aceitar inputs vazios
        while(mail.isEmpty()){
            System.out.println("Input vazio");
            System.out.println("Mail: ");
            mail = scanner.nextLine();
        }
        // Não aceitar mails repetidos
        Boolean flagM= true;
        for (Pessoa p : pessoas) {
            while(flagM) {
                if (p.getMail().equals(mail)) {
                    System.out.println("Mail já existente. Introduza outro.");
                    System.out.println("Nome: ");
                    mail = scanner.nextLine();
                } else {
                    flagM = false;
                }
            }
        }
        System.out.println("Escolha entre as opções:");
        System.out.println("1.Docente");
        System.out.println("2.Bolseiro");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Numero mecanográfico: ");
                int numMecanografico = scanner.nextInt();
                while(numMecanografico == 0 || numMecanografico < 0){
                    System.out.println("Numero mecanográfico: ");
                    numMecanografico = scanner.nextInt();
                }
                System.out.println("Área Investigação: ");
                String aI = scanner.next();
                // Não aceitar inputs vazios
                while(aI.isEmpty()){
                    System.out.println("Input vazio");
                    System.out.println("Área Investigação: ");
                    aI = scanner.next();
                }
                Docente d = new Docente(nome, mail, numMecanografico, aI);
                pessoas.add(d);
                break;
            case 2:
                System.out.println("Data inicio: ");
                String dataInicio = scanner.next();

                //Não aceitar inputs vazios nem datas inválidas.
                while(dataInicio.isEmpty() || !validarData1(dataInicio)) {
                    if (dataInicio.isEmpty())
                        System.out.println("Input vazio");
                    System.out.println("DataInicio (yyyy-MM-dd): ");
                    dataInicio = scanner.nextLine();
                }

                System.out.println("Data fim: ");
                String dataFim = scanner.next();

                //Não aceitar inputs vazios nem datas inválidas.
                while(dataFim.isEmpty() || !validarData1(dataFim)) {
                    if (dataFim.isEmpty())
                        System.out.println("Input vazio");
                    System.out.println("DataFim (yyyy-MM-dd): ");
                    dataFim = scanner.nextLine();
                }

                //Não aceitar datas incoerentes.
                while(!validarData2(dataInicio, dataFim)) {
                    System.out.println("DataInicio (yyyy-MM-dd): ");
                    dataInicio = scanner.nextLine();
                    System.out.println("DataFim (yyyy-MM-dd): ");
                    dataFim = scanner.nextLine();
                }

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

    public void criarTarefas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação de Tarefa------");

        // PROJETO ESCOLHIDO
        System.out.println("Escolha o projeto:");
        for (int i = 0; i < projetos.size(); i++) {
            System.out.println((i+1) + ": " + projetos.get(i).getNome());
        }

        //Verificar se a opção escolhida existe.
        int numProj = scanner.nextInt();
        while (numProj <= 0 || numProj > projetos.size()) {
            System.out.println("Opção inválida. Volte a escolher o projeto:");
            numProj = scanner.nextInt();
        }

        Projeto projeto = projetos.get(numProj-1);

        scanner.nextLine();   //Skip the Newline Character.

        // DATA INICIO
        System.out.println("DataInicio (yyyy-MM-dd) entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
        String dataInicio = scanner.nextLine();
        //Não aceitar inputs vazios nem datas inválidas.
        while(dataInicio.isEmpty() || !validarData1(dataInicio) || !validarData2(projeto.getDataInicio(), dataInicio)) {
            if (dataInicio.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataInicio (yyyy-MM-dd) entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
            dataInicio = scanner.nextLine();
        }

        // DATA FIM
        System.out.println("DataFim (yyyy-MM-dd): entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
        String dataFim = scanner.nextLine();
        //Não aceitar inputs vazios nem datas inválidas.
        while(dataFim.isEmpty() || !validarData1(dataFim) || !validarData2(dataFim, projeto.getDataFim())) {
            if (dataFim.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataFim (yyyy-MM-dd): entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
            dataFim = scanner.nextLine();
        }

        //Não aceitar datas incoerentes.
        while(!validarData2(dataInicio, dataFim)) {
            System.out.println("DataInicio (yyyy-MM-dd): ");
            dataInicio = scanner.nextLine();
            System.out.println("DataFim (yyyy-MM-dd): ");
            dataFim = scanner.nextLine();
        }

        // PESSOA RESPONSÁVEL

        //Pessoas que não ficarão sobrecarregadas.
        ArrayList<Pessoa> pessoasDisponiveis = new ArrayList<>();
        Pessoa responsavel;

        System.out.println("Escolha entre as opções:");
        System.out.println("1.Design");
        System.out.println("2.Desenvolvimento");
        System.out.println("3.Documentação");
        int opcao = scanner.nextInt();
        scanner.nextLine();   //Skip the Newline Character.

        switch (opcao) {
            case 1:
                Design design = new Design(dataInicio, 0, dataFim);

                if (!isSobrecarregada(projeto.getIp(), design))
                    pessoasDisponiveis.add(projeto.getIp());
                for (Pessoa pessoa : projeto.getPessoasEnvolvidas()) {
                    if (!isSobrecarregada(pessoa, design)) {
                        pessoasDisponiveis.add(pessoa);
                    }
                }

                System.out.println("Escolha a pessoa responsável pela tarefa:");
                for (int i = 0; i < pessoasDisponiveis.size(); i++) {
                    System.out.println((i+1) + ": " + pessoasDisponiveis.get(i).getNome());
                }

                //Verificar se a opção escolhida existe.
                int numPessoa = scanner.nextInt();
                while (numPessoa <= 0 || numPessoa > pessoasDisponiveis.size()) {
                    System.out.println("Opção inválida. Escolha a pessoa responsável pela tarefa:");
                    numPessoa = scanner.nextInt();
                }
                responsavel = pessoasDisponiveis.get(numPessoa-1);

                design.setResponsavel(responsavel);
                tarefas.add(design);
                break;
            case 2:
                Desenvolvimento des = new Desenvolvimento(dataInicio, 0, dataFim);

                if (!isSobrecarregada(projeto.getIp(), des))
                    pessoasDisponiveis.add(projeto.getIp());
                for (Pessoa pessoa : projeto.getPessoasEnvolvidas()) {
                    if (!isSobrecarregada(pessoa, des)) {
                        pessoasDisponiveis.add(pessoa);
                    }
                }

                System.out.println("Escolha a pessoa responsável pela tarefa:");
                for (int i = 0; i < pessoasDisponiveis.size(); i++) {
                    System.out.println((i+1) + ": " + pessoasDisponiveis.get(i).getNome());
                }

                //Verificar se a opção escolhida existe.
                numPessoa = scanner.nextInt();
                while (numPessoa <= 0 || numPessoa > pessoasDisponiveis.size()) {
                    System.out.println("Opção inválida. Escolha a pessoa responsável pela tarefa:");
                    numPessoa = scanner.nextInt();
                }
                responsavel = pessoasDisponiveis.get(numPessoa-1);

                des.setResponsavel(responsavel);
                tarefas.add(des);
                break;
            case 3:
                Documentacao doc = new Documentacao(dataInicio, 0, dataFim);

                if (!isSobrecarregada(projeto.getIp(), doc))
                    pessoasDisponiveis.add(projeto.getIp());
                for (Pessoa pessoa : projeto.getPessoasEnvolvidas()) {
                    if (!isSobrecarregada(pessoa, doc)) {
                        pessoasDisponiveis.add(pessoa);
                    }
                }

                System.out.println("Escolha a pessoa responsável pela tarefa:");
                for (int i = 0; i < pessoasDisponiveis.size(); i++) {
                    System.out.println((i+1) + ": " + pessoasDisponiveis.get(i).getNome());
                }

                //Verificar se a opção escolhida existe.
                numPessoa = scanner.nextInt();
                while (numPessoa <= 0 || numPessoa > pessoasDisponiveis.size()) {
                    System.out.println("Opção inválida. Escolha a pessoa responsável pela tarefa:");
                    numPessoa = scanner.nextInt();
                }
                responsavel = pessoasDisponiveis.get(numPessoa-1);

                doc.setResponsavel(responsavel);
                tarefas.add(doc);
            default:
                System.out.println("Escolha uma opção existente.");
        }
    }

    public void printTarefas() {
        for (Tarefa t : tarefas) {
            System.out.println(t);
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
                    continue;
                }
            }
            if(docentes.size() == 0) {
                //Não existem docentes
                System.out.println("Insira Docentes na aplicação");
                Menu();
            }
        } else {
            //A lista das pessoas está vazia
            System.out.println("Insira Pessoas na aplicação.");
            Menu();
        }
        return docentes;
    }

    public ArrayList<Bolseiro> getBolseiro() {
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
                    continue;
                }
            }
            if(bolseiros.size() == 0) {
                //Não existem docentes
                System.out.println("Insira Bolseiros na aplicação");
                Menu();
            }
        } else {
            //A lista das pessoas está vazia
            System.out.println("Insira Pessoas na aplicação.");
            Menu();
        }
        return bolseiros;
    }

    public ArrayList<Docente> getDocentes() {
        /**
         * Method to get a list of all People who are "Docentes".
         * @return ArrayList of docentes
         */
        ArrayList<Docente> docentes = new ArrayList<Docente>();
        for (Pessoa p : pessoas) {
            // Ver se é Docente ou Bolseiro
            if (p.getNumeroMecanografico() > 0) {
                Docente dc = (Docente) p;
                docentes.add(dc);
            }
        }
        return docentes;
    }

    public ArrayList<Bolseiro> getBolseiros() {
        /**
         * Method to get a list of all People who are "Bolseiros".
         * @return ArrayList of bolseiros
         */
        ArrayList<Bolseiro> bolseiros = new ArrayList<Bolseiro>();
        for (Pessoa p : pessoas) {
            // Ver se é Docente ou Bolseiro
            if (p.getNumeroMecanografico() == 0) {
                Bolseiro bs = (Bolseiro) p;
                bolseiros.add(bs);
            }
        }
        return bolseiros;
    }

    public Boolean validarData1(String data) {

        /**
         * Method that checks if a date is in correct format.
         * @param data
         * return true if date is in correct format and false otherwise
         */

        String[] dataElems = data.split("-");
        int ano, mes, dia;

        try {
            ano = Integer.parseInt(dataElems[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ano válido.");
            return false;
        }
        try {
            mes = Integer.parseInt(dataElems[1]);
        } catch (NumberFormatException e) {
            System.out.println("Mês válido.");
            return false;
        }
        try {
            dia = Integer.parseInt(dataElems[2]);
        } catch (NumberFormatException e) {
            System.out.println("Dia válido.");
            return false;
        }

        if (((int) Math.log10(ano) + 1) == 4 && (((int) Math.log10(mes) + 1) == 2 || ((int) Math.log10(mes) + 1) == 1) && (((int) Math.log10(dia) + 1) == 2 || ((int) Math.log10(dia) + 1) == 1 ) && ano>0 && mes>0 && dia>0 && mes<=12 && dia<=31) {
            return true;
        }
        else {
            System.out.println("Data Inválida.");
            return false;
        }
    }

    public Boolean validarData2(String dataIni, String dataFim) {

        /**
         * Method that compares beginning date and end date and checks if end date is or is after beginning date.
         * @param dataIni (beginning date)
         * @param dataFim (end date)
         * @return true if dates are right and false otherwise
         */

        String data1 = dataIni.replaceAll("-", "");
        String data2 = dataFim.replaceAll("-", "");

        if (Integer.parseInt(data1) <= Integer.parseInt(data2)) {
            return true;
        }
        else {
            System.out.println("Datas incoerentes.");
            return false;
        }
    }

    public Boolean isSobrecarregada(Pessoa pessoa, Tarefa tarefa) {

        /**
         * Method that checks if a new task will make a person overloaded with work.
         * @param pessoa
         * @param tarefa (new task to associate to person)
         * @return true if person would be overloaded and false otherwise
         */

        long carga = 0;

        for (Tarefa t : tarefas) {
            if (t.getResponsavel() == pessoa)
                carga += t.calculaTaxa();
        }
        if (carga + tarefa.calculaTaxa() >= 1)
            return true;
        return false;
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


