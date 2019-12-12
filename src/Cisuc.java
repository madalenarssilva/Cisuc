import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
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
    private ArrayList<Projeto> projetosTerminados;
    private ArrayList<Tarefa> tarefas;
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
        projetosTerminados = new ArrayList<>();
        tarefas = new ArrayList<>();

        lerFicheirosPessoas();
        lerFicheirosProjetos();
        lerFicheirosTarefas();

        // Inicializa o GUI
        try {
            GUI gui = new GUI(this);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
        System.out.println("3. Listar os projetos não concluídos na data estimada.");
        System.out.println("4. Listar os projectos concluídos.");
        System.out.println("5. Editar projeto.");
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
                // ESCOLHER PROJETO
                System.out.println();
                System.out.println("-------Editar Projeto---------");
                printProjetosNomes();
                System.out.print("Escolha o projeto: ");

                //Verificar se a opção escolhida existe.
                int n = scanner.nextInt();
                while (n <= 0 || n > projetos.size()) {
                    System.out.print("Opção inválida. Volte a escolher o projeto: ");
                    n = scanner.nextInt();
                }
                Projeto projeto = projetos.get(n - 1);

                System.out.println();
                System.out.println("Operações: ");
                System.out.println("1. Criar tarefa.");
                System.out.println("2. Associar tarefa a pessoa.");
                System.out.println("3. Associar pessoa ao projeto.");
                System.out.println("4. Eliminar tarefa.");
                System.out.println("5. Atualizar taxa de execução de uma tarefa.");
                System.out.println("6. Listar tarefas não concluídas na data estimada.");
                System.out.println("7. Listar tarefas não iniciadas.");
                System.out.println("8. Listar tarefas concluídas.");
                System.out.println("9. Indicar custo do projeto.");
                System.out.println("10. Terminar projeto.");
                System.out.println("11. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao2 = scanner.nextInt();
                switch (opcao2) {
                    case 1:
                        criarTarefas(projeto);
                        printTarefas();
                        break;
                    case 2:
                        associarTarefasAPessoas(projeto);
                        printTarefas();
                        break;
                    case 3:
                        associarPessoasAProjetos(projeto);
                        printProjetos();
                        printPessoas();
                        System.out.println("PESSOAS ENVOLVIDAS: ");
                        System.out.println(projeto.getPessoasEnvolvidas());
                        break;
                    case 4:
                        eliminarTarefas(projeto);
                        printTarefas();
                        printProjetos();
                        break;
                    case 5:
                        atualizarTaxaExecucao(projeto);
                        printTarefas();
                        break;
                    case 6:
                        ArrayList<Tarefa> tarefasForaPrazo = new ArrayList<>();
                        tarefasForaPrazo = getTarefasForaPrazo(projeto);
                        printTarefas(tarefasForaPrazo);
                        break;
                    case 7:
                        ArrayList<Tarefa> tarefasNaoIniciadas = new ArrayList<>();
                        tarefasNaoIniciadas = getTarefasNaoIniciadas(projeto);
                        printTarefas(tarefasNaoIniciadas);
                        break;
                    case 8:
                        ArrayList<Tarefa> tarefasConcluidas = new ArrayList<>();
                        tarefasConcluidas = getTarefasConcluidas(projeto);
                        printTarefas(tarefasConcluidas);
                        break;
                    case 9:
                        System.out.print("O custo do projeto é de " + projeto.calculaCustoPorTarefa() + "€.");
                        break;
                    case 10:
                        terminarProjeto(projeto);
                        printProjetos();
                        break;
                    case 11:
                        break;
                    default:
                        System.out.println("Escolha uma opção existente.");
                }
                break;
            case 6:
                exitMenu = true;
                System.out.println("Bye");
                break;
            default:
                System.out.println("Escolha uma opção existente.");
        }
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

                        //Fazer cálculo da duração estimada, com base nas datas de início e fim e colocá-la na class da Tarefa.
                        long duracaoEstimada = ChronoUnit.DAYS.between(LocalDate.parse(s[1]), LocalDate.parse(s[2]));

                        switch (s[0]) {
                            case "DESIGN":
                                Design design = new Design(s[1], 0, s[2], responsavel, duracaoEstimada);

                                //Adicionar à lista de tarefas do projeto a que a tarefa está associada a tarefa em questão.
                                for (Projeto proj : projetos) {
                                    if (proj.getAcronimo().equals(s[3])) {
                                        proj.getTarefas().add(design);
                                    }
                                }
                                tarefas.add(design);
                                break;
                            case "DESENVOLVIMENTO":
                                Desenvolvimento des = new Desenvolvimento(s[1], 0, s[2], responsavel, duracaoEstimada);

                                //Adicionar à lista de tarefas do projeto a que a tarefa está associada a tarefa em questão.
                                for (Projeto proj : projetos) {
                                    if (proj.getAcronimo().equals(s[3])) {
                                        proj.getTarefas().add(des);
                                    }
                                }
                                tarefas.add(des);
                                break;
                            case "DOCUMENTACAO":
                                Documentacao doc = new Documentacao(s[1], 0, s[2], responsavel, duracaoEstimada);

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


    //                                                PROJETOS

    public void criarProjetos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação do Projeto------");

        // NOME
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        // Não aceitar inputs vazios
        while (nome.isEmpty()) {
            System.out.println("Input vazio");
            System.out.println("Nome: ");
            nome = scanner.nextLine();
        }
        // Não aceitar nomes repetidos
        Boolean flag = true;
        for (Projeto p : projetos) {
            while (flag) {
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
        while (acronimo.isEmpty()) {
            System.out.println("Input vazio");
            System.out.println("Acrónimo: ");
            acronimo = scanner.nextLine();
        }
        // Não aceitar acronimos repetidos
        Boolean flagA = true;
        for (Projeto p : projetos) {
            while (flagA) {
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
        while (dataInicio.isEmpty() || !validarData1(dataInicio)) {
            if (dataInicio.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataInicio (yyyy-MM-dd): ");
            dataInicio = scanner.nextLine();
        }

        // DATA FIM
        System.out.println("DataFim (yyyy-MM-dd): ");
        String dataFim = scanner.nextLine();
        //Não aceitar inputs vazios nem datas inválidas.
        while (dataFim.isEmpty() || !validarData1(dataFim)) {
            if (dataFim.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataFim (yyyy-MM-dd): ");
            dataFim = scanner.nextLine();
        }

        //Não aceitar datas incoerentes.
        while (!validarData2(dataInicio, dataFim)) {
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
        for (Projeto p : projetosTerminados) {
            System.out.println(p);
        }
    }

    public void printProjetosNomes() {
        /**
         * Method that prints all project names with an indice before the name.
         */
        int i = 1;
        for (Projeto p : projetos) {
            System.out.println(i++ + ". " + p.getNome());
        }
    }

    public void terminarProjeto(Projeto projeto) {

        /** Method that ends a project. Once eliminated, it will only be avaible for consulting.
         * @param projeto that the user wants to terminate.
         */

        projetos.remove(projeto);
        projetosTerminados.add(projeto);
    }

    //                                                 TAREFAS

    public void criarTarefas(Projeto projeto) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação de Tarefa------");

        // DATA INICIO
        System.out.println("DataInicio (yyyy-MM-dd) entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
        String dataInicio = scanner.nextLine();
        //Não aceitar inputs vazios nem datas inválidas.
        while (dataInicio.isEmpty() || !validarData1(dataInicio) || !validarData2(projeto.getDataInicio(), dataInicio)) {
            if (dataInicio.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataInicio (yyyy-MM-dd) entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
            dataInicio = scanner.nextLine();
        }

        // DATA FIM
        System.out.println("DataFim (yyyy-MM-dd): entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
        String dataFim = scanner.nextLine();
        //Não aceitar inputs vazios nem datas inválidas.
        while (dataFim.isEmpty() || !validarData1(dataFim) || !validarData2(dataFim, projeto.getDataFim())) {
            if (dataFim.isEmpty())
                System.out.println("Input vazio");
            System.out.println("DataFim (yyyy-MM-dd): entre " + projeto.getDataInicio() + " e " + projeto.getDataFim() + ": ");
            dataFim = scanner.nextLine();
        }

        //Não aceitar datas incoerentes.
        while (!validarData2(dataInicio, dataFim)) {
            System.out.println("DataInicio (yyyy-MM-dd): ");
            dataInicio = scanner.nextLine();
            System.out.println("DataFim (yyyy-MM-dd): ");
            dataFim = scanner.nextLine();
        }

        //Fazer cálculo da duração estimada, com base nas datas de início e fim e colocá-la na class da Tarefa.
        long duracaoEstimada = ChronoUnit.DAYS.between(LocalDate.parse(dataInicio), LocalDate.parse(dataFim));


        System.out.println("1. Design");
        System.out.println("2. Desenvolvimento");
        System.out.println("3. Documentação");
        System.out.print("Escolha entre as opções:");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                Design design = new Design(dataInicio, 0, dataFim, duracaoEstimada);
                tarefas.add(design);
                projeto.getTarefas().add(design);
                break;
            case 2:
                Desenvolvimento des = new Desenvolvimento(dataInicio, 0, dataFim, duracaoEstimada);
                tarefas.add(des);
                projeto.getTarefas().add(des);
                break;
            case 3:
                Documentacao doc = new Documentacao(dataInicio, 0, dataFim, duracaoEstimada);
                tarefas.add(doc);
                projeto.getTarefas().add(doc);
                break;
            default:
                System.out.println("Escolha uma opção existente.");
        }
    }

    public void associarTarefasAPessoas(Projeto projeto) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Associação de Tarefa------");

        // TAREFA ESCOLHIDA
        System.out.println("Escolha a tarefa: ");

        //Array das tarefas do projeto que ainda não têm um responsável.
        ArrayList<Tarefa> tarefasDisponiveis = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < projeto.getTarefas().size(); i++) {
            if (projeto.getTarefas().get(i).getResponsavel() == null) {
                tarefasDisponiveis.add(projeto.getTarefas().get(i));
                System.out.println((j++) + ": " + projeto.getTarefas().get(i).toString());
            }
        }
        if (tarefasDisponiveis.size() == 0) {
            System.out.println("Não existem tarefas disponíveis.");
            return;
        }

        //Verificar se a opção escolhida existe.
        int numTarefa = scanner.nextInt();
        while (numTarefa <= 0 || numTarefa > j) {
            System.out.println("Opção inválida. Volte a escolher o tarefa: ");
            numTarefa = scanner.nextInt();
        }

        //Obter objeto Tarefa.
        Tarefa tarefa = tarefasDisponiveis.get(numTarefa - 1);

        // PESSOA RESPONSÁVEL
        ArrayList<Pessoa> pessoasDisponiveis = new ArrayList<>();
        Pessoa responsavel;

        //Encontrar bolseiros cuja duração da bolsa as permita ter a tarefa.
        //Encontrar Pessoas que não ficarão sobrecarregadas.
        for (Pessoa pessoa : projeto.getPessoasEnvolvidas()) {
            System.out.println(pessoa);
            if (pessoa.getNumeroMecanografico() == 0) {
                if (!isSobrecarregada(pessoa, tarefa)) {
                    Bolseiro bols = (Bolseiro) pessoa;
                    if (validarData2(bols.getDataInicio(), tarefa.getDataInicio()) && validarData2(tarefa.getDataFim(), bols.getDataFim()))
                        pessoasDisponiveis.add(bols);
                }
            } else {
                if (!isSobrecarregada(pessoa, tarefa))
                    pessoasDisponiveis.add(pessoa);
            }
        }
        //Adicionar o ip se existir e se não estiver sobrecarregado.
        if (!isSobrecarregada(projeto.getIp(), tarefa) && projeto.getIp() != null)
            pessoasDisponiveis.add(projeto.getIp());

        for (int i = 0; i < pessoasDisponiveis.size(); i++) {
            System.out.println((i + 1) + ": " + pessoasDisponiveis.get(i).getNome());
        }

        if (pessoasDisponiveis.size() == 0) {
            System.out.println("Não existem pessoas disponíveis.");
            return;
        }
        System.out.println("Escolha a pessoa responsável pela tarefa: ");

        //Verificar se a opção escolhida existe.
        int numPessoa = scanner.nextInt();
        while (numPessoa <= 0 || numPessoa > pessoasDisponiveis.size()) {
            System.out.println("Opção inválida. Escolha a pessoa responsável pela tarefa: ");
            numPessoa = scanner.nextInt();
        }
        responsavel = pessoasDisponiveis.get(numPessoa - 1);
        tarefa.setResponsavel(responsavel);
    }

    public void printTarefas(ArrayList<Tarefa>... args) {

        if (args.length == 0) {
            for (Tarefa t : tarefas) {
                System.out.println(t);
            }
        } else {
            for (int i = 0; i < args.length; i++) {
                for (Tarefa t : args[i]) {
                    System.out.println(t);
                }
            }
        }
    }

    public void atualizarTaxaExecucao(Projeto projeto) {

        /**
         * Method that allows user to change the execution percentage of a task.
         * We only allow the user to change the execution percentage of tasks that have not been registered as finished and that have a person associated to them.
         * @param projeto that contains the task we want to update.
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Atualização da Taxa de Execução de uma Tarefa------");

        //Array das tarefas do projeto que ainda não têm a taxa de execução a 100%.
        ArrayList<Tarefa> tarefasDisponiveis = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < projeto.getTarefas().size(); i++) {
            if (projeto.getTarefas().get(i).getPercentagemConclusao() != 100 && projeto.getTarefas().get(i).getResponsavel() != null) {
                tarefasDisponiveis.add(projeto.getTarefas().get(i));
                System.out.println((j++) + ": " + projeto.getTarefas().get(i).toString());
            }
        }
        if (tarefasDisponiveis.size() == 0) {
            System.out.println("Não pode atualizar a taxa de nenhuma tarefa.");
            return;
        }

        // TAREFA ESCOLHIDA
        System.out.print("Escolha a tarefa: ");

        //Verificar se a opção escolhida existe.
        int numTarefa = scanner.nextInt();
        while (numTarefa <= 0 || numTarefa > j) {
            System.out.println("Opção inválida. Volte a escolher o tarefa: ");
            numTarefa = scanner.nextInt();
        }

        //Obter objeto Tarefa.
        Tarefa tarefa = tarefasDisponiveis.get(numTarefa - 1);

        //Pedir a nova taxa de execução ao user.
        System.out.print("Insira a taxa de execução da tarefa: ");
        int novaTaxa = scanner.nextInt();

        //Verificar se a taxa introduzida é uma percentagem entre 0 e 100 e se é maior do que a anterior.
        while (novaTaxa <= 0 || novaTaxa > 100 || novaTaxa <= tarefa.getPercentagemConclusao()) {
            System.out.println("Taxa inválida. Volte a inserir a taxa: ");
            novaTaxa = scanner.nextInt();
        }
        //Atualizar taxa da tarefa.
        tarefa.setPercentagemConclusao(novaTaxa);

        //Se a nova taxa de execução for igual 100%, atualizar a data de fim da tarefa.
        if (novaTaxa == 100) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dataAtual = new Date();
            String dataAtualString = format.format(dataAtual);
            tarefa.setDataFim(dataAtualString);
        }
    }

    public ArrayList<Tarefa> getTarefasNaoIniciadas(Projeto projeto) {

        /**
         * Method that returns all the non initiated tasks.
         * @param projeto to which the task belongs.
         * @return Arraylist of non initiated tasks.
         */

        ArrayList<Tarefa> tarefasNaoIniciadas = new ArrayList<>();

        for (Tarefa t : projeto.getTarefas()) {
            if (t.getPercentagemConclusao() == 0)
                tarefasNaoIniciadas.add(t);
        }
        return tarefasNaoIniciadas;
    }

    public ArrayList<Tarefa> getTarefasConcluidas(Projeto projeto) {

        /**
         * Method that returns all the finished tasks, whether they were finished on time or not.
         * @param projeto to which the task belongs.
         * @return Arraylist of non initiated tasks.
         */

        ArrayList<Tarefa> tarefasConcluidas = new ArrayList<>();

        for (Tarefa t : projeto.getTarefas()) {
            if (t.getPercentagemConclusao() == 100)
                tarefasConcluidas.add(t);
        }
        return tarefasConcluidas;
    }

    public ArrayList<Tarefa> getTarefasForaPrazo(Projeto projeto) {

        /** Method that returns all the tasks that have not been concluded in the estimated time.
         * We assume that a person cannot start a task before the start date given for task.
         * @param projeto to which task belongs.
         * @return ArrayList of all the tasks in the condition mentioned above.
         */

        ArrayList<Tarefa> tarefasForaPrazo = new ArrayList<>();

        //Obter data atual em formato yyyy-MM-dd.
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dataAtual = new Date();
        String dataAtualString = format.format(dataAtual);

        //Variável que guarda a diferença de dias entre a datas de início registada na class da Tarefa e a data atual.
        //(Tendo em conta que a data de fim é atualizada assim que a taxa de execução for 100%).
        long diferencaDias;

        for (Tarefa t : projeto.getTarefas()) {
            diferencaDias = ChronoUnit.DAYS.between(LocalDate.parse(t.getDataInicio()), LocalDate.parse(dataAtualString));

            //Se a diferença de dias registada for igual à duração estimada para a tarefa e a tarefa não estiver concluída (a 100%), adicionar ao array das tarefas fora de prazo.
            //Se a diferença de dias registada for superior à duração estimada para a tarefa, adicionar ao array das tarefas fora de prazo.
            if ((diferencaDias == t.getDuracaoEstimada() && t.getPercentagemConclusao() != 100) || diferencaDias > t.getDuracaoEstimada())
                tarefasForaPrazo.add(t);
        }
        return tarefasForaPrazo;
    }

    public void eliminarTarefas(Projeto projeto) {

        /**
         * Method that eliminates a task.
         * @param projeto to which the task belongs.
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Eliminar uma Tarefa------");

        int j = 1;
        for (int i = 0; i < projeto.getTarefas().size(); i++) {
            System.out.println((j++) + ": " + projeto.getTarefas().get(i).toString());
        }
        if (projeto.getTarefas().size() == 0) {
            System.out.println("Não há tarefas para eliminar.");
            return;
        }

        // TAREFA ESCOLHIDA
        System.out.print("Escolha a tarefa a eliminar: ");

        //Verificar se a opção escolhida existe.
        int numTarefa = scanner.nextInt();
        while (numTarefa <= 0 || numTarefa > projeto.getTarefas().size()) {
            System.out.println("Opção inválida. Volte a escolher o tarefa: ");
            numTarefa = scanner.nextInt();
        }

        //Obter objeto Tarefa.
        Tarefa tarefa = projeto.getTarefas().get(numTarefa - 1);

        //Eliminar tarefa do array das tarefas do projeto.
        projeto.getTarefas().remove(tarefa);
        //Eliminar tarefa do array que guarda todas as tarefas.
        tarefas.remove(tarefa);
    }

    //                                                 PESSOAS

    public void criarPessoas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Criação da Pessoa------");

        // NOME
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        // Não aceitar inputs vazios
        while (nome.isEmpty()) {
            System.out.println("Input vazio");
            System.out.println("Nome: ");
            nome = scanner.nextLine();
        }
        // Não aceitar nomes repetidos
        Boolean flag = true;
        for (Pessoa p : pessoas) {
            while (flag) {
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
        while (mail.isEmpty()) {
            System.out.println("Input vazio");
            System.out.println("Mail: ");
            mail = scanner.nextLine();
        }
        // Não aceitar mails repetidos
        Boolean flagM = true;
        for (Pessoa p : pessoas) {
            while (flagM) {
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
        scanner.nextLine();   //Skip the newline character.
        switch (opcao) {
            case 1:
                System.out.println("Numero mecanográfico: ");
                int numMecanografico = scanner.nextInt();
                while (numMecanografico == 0 || numMecanografico < 0) {
                    System.out.println("Numero mecanográfico: ");
                    numMecanografico = scanner.nextInt();
                }
                System.out.println("Área Investigação: ");
                String aI = scanner.next();
                // Não aceitar inputs vazios
                while (aI.isEmpty()) {
                    System.out.println("Input vazio");
                    System.out.println("Área Investigação: ");
                    aI = scanner.next();
                }
                Docente d = new Docente(nome, mail, numMecanografico, aI);
                pessoas.add(d);
                break;
            case 2:
                // DATA INICIO
                System.out.println("DataInicio (yyyy-MM-dd): ");
                String dataInicio = scanner.nextLine();
                //Não aceitar inputs vazios nem datas inválidas.
                while (dataInicio.isEmpty() || !validarData1(dataInicio)) {
                    if (dataInicio.isEmpty())
                        System.out.println("Input vazio");
                    System.out.println("DataInicio (yyyy-MM-dd): ");
                    dataInicio = scanner.nextLine();
                }

                // DATA FIM
                System.out.println("DataFim (yyyy-MM-dd): ");
                String dataFim = scanner.nextLine();
                //Não aceitar inputs vazios nem datas inválidas.
                while (dataFim.isEmpty() || !validarData1(dataFim)) {
                    if (dataFim.isEmpty())
                        System.out.println("Input vazio");
                    System.out.println("DataFim (yyyy-MM-dd): ");
                    dataFim = scanner.nextLine();
                }

                //Não aceitar datas incoerentes.
                while (!validarData2(dataInicio, dataFim)) {
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

    public void associarPessoasAProjetos(Projeto projeto) {

        /**
         * Method that links persons to projects.
         */
        Scanner scanner = new Scanner(System.in);

        // ESCOLHER PESSOA
        //Array das pessoas disponíveis para serem associadas a um novo projeto:
        //Todas as pessoas que ainda não estiverem no projeto, excluindo bolseiros que já estejam noutro projeto.
        ArrayList<Pessoa> pessoasDisponiveis = new ArrayList<>();
        int j = 1;
        for (Pessoa pessoa : pessoas) {
            if (!verificaRepeticao(projeto, pessoa)) {
                if (pessoa.getNumeroMecanografico() == 0) {
                    if (!verificarSeAlgumProjeto(pessoa)) {
                        pessoasDisponiveis.add(pessoa);
                        System.out.println((j++) + ": " + pessoa.getNome());
                    }
                } else {
                    pessoasDisponiveis.add(pessoa);
                    System.out.println((j++) + ": " + pessoa.getNome());
                }
            }
        }
        if (pessoasDisponiveis.size() == 0) {
            System.out.println("Não existem pessoas disponíveis.");
            return;
        }

        System.out.println("Escolha uma Pessoa para associar: ");
        int n2 = scanner.nextInt();
        //Verificar se a opção escolhida existe.
        while (n2 <= 0 || n2 > pessoasDisponiveis.size()) {
            System.out.println("Opção inválida. Escolha uma pessoa: ");
            n2 = scanner.nextInt();
        }
        Pessoa pessoa = pessoasDisponiveis.get(n2 - 1);

        //Se for a pessoa escolhida for um BOLSEIRO só pode estar num projeto.
        if (pessoa.getNumeroMecanografico() == 0) {

            //Se for a pessoa escolhida for um LICENCIADO/MESTRE ver se já tem orientadores para o projeto.
            if (pessoa.getCusto() != 1000) {

                //Obter todos os docentes do projeto.
                ArrayList<Docente> docentesProjeto = getDocentesProjeto(projeto);
                ArrayList<Docente> orientadoresPossiveis = new ArrayList<>();

                // LICENCIADO
                if (pessoa.getCusto() == 500) {
                    Licenciado l = (Licenciado) pessoa;

                    String mais;
                    int num;
                    do {
                        //Obter todos os orientadores possíveis para a pessoa.
                        for (Docente d : docentesProjeto) {
                            if (!l.getOrientadores().contains(d))
                                orientadoresPossiveis.add(d);
                        }

                        //Imprimir todos os orientadores possíveis.
                        int k = 1;
                        for (int i = 0; i < orientadoresPossiveis.size(); i++)
                            System.out.println(k++ + ". " + orientadoresPossiveis.get(i).getNome());

                        if (orientadoresPossiveis.size() == 0) {
                            System.out.println("Não existem orientadores possíveis.");
                            return;
                        }

                        System.out.print("Escolha um orientador: ");
                        num = scanner.nextInt();
                        while (num <= 0 || num > orientadoresPossiveis.size()) {
                            System.out.println("Opção inválida. Tente novamente: ");
                            num = scanner.nextInt();
                        }
                        //Adicionar orientador ao licenciado.
                        l.getOrientadores().add(orientadoresPossiveis.get(num - 1));

                        System.out.print("Deseja adicionar outro orientador? (s/n): ");
                        scanner.nextLine();
                        mais = scanner.nextLine();
                        while (!mais.equals("s") && !mais.equals("n")) {
                            System.out.println("Opção inválida. Tente novamente: ");
                            mais = scanner.nextLine();
                        }
                        orientadoresPossiveis = new ArrayList<>();
                    } while (mais.equals("s"));

                    //Adicionar novo licenciado à lista de pessoas envolvidas no projeto.
                    projeto.getPessoasEnvolvidas().add(l);
                }

                // MESTRE
                else {
                    Mestre m = (Mestre) pessoa;

                    String mais;
                    int num;
                    do {
                        //Obter todos os orientadores possíveis para a pessoa.
                        for (Docente d : docentesProjeto) {
                            if (!m.getOrientadores().contains(d))
                                orientadoresPossiveis.add(d);
                        }

                        //Imprimir todos os orientadores possíveis.
                        int k = 1;
                        for (int i = 0; i < orientadoresPossiveis.size(); i++)
                            System.out.println(k++ + ". " + orientadoresPossiveis.get(i).getNome());

                        System.out.print("Escolha um orientador: ");
                        num = scanner.nextInt();
                        while (num <= 0 || num > orientadoresPossiveis.size()) {
                            System.out.println("Opção inválida. Tente novamente: ");
                            num = scanner.nextInt();
                        }
                        //Adicionar orientador ao mestre.
                        m.getOrientadores().add(orientadoresPossiveis.get(num - 1));

                        System.out.print("Deseja adicionar outro orientador? (s/n): ");
                        scanner.nextLine();
                        mais = scanner.nextLine();
                        while (!mais.equals("s") && !mais.equals("n")) {
                            System.out.println("Opção inválida. Tente novamente: ");
                            mais = scanner.nextLine();
                        }
                        orientadoresPossiveis = new ArrayList<>();
                    } while (mais.equals("s"));

                    //Adicionar novo mestre à lista de pessoas envolvidas no projeto.
                    projeto.getPessoasEnvolvidas().add(m);
                }
            }

            // DOUTORADO
            else {
                //Adicionar doutorado à lista de pessoas envolvidas no projeto.
                projeto.getPessoasEnvolvidas().add(pessoa);
            }
        }
        //DOCENTE
        else {
            //Adicionar docente à lista de pessoas envolvidas no projeto.
            projeto.getPessoasEnvolvidas().add(pessoa);
        }

        //Se o projeto ainda não tiver investigador principal (um Docente):
        if (projeto.getIp() == null) {
            ArrayList<Docente> docentes = getDocentes();
            ArrayList<Docente> ipsPossiveis = new ArrayList<>();
            int i = 1;
            //Imprimir lista de docentes.
            for (Docente d : docentes) {
                if (!projeto.getPessoasEnvolvidas().contains(d)) {
                    ipsPossiveis.add(d);
                    System.out.println(i++ + ". " + d.getNome());
                }
            }
            System.out.print("Escolha o Investigador Principal: ");
            int nIp = scanner.nextInt();
            while (nIp <= 0 || nIp > ipsPossiveis.size()) {
                System.out.println("Opção inválida. Escolha o Investigador Principal: ");
                nIp = scanner.nextInt();
            }
            projeto.setIp(ipsPossiveis.get(nIp - 1));
        }
    }

    public void printPessoas() {
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
    }

    //                                                AUXILIARES

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

        if (dataElems[0].length() == 4 && dataElems[1].length() == 2 && dataElems[2].length() == 2 && ano > 0 && mes > 0 && dia > 0 && mes <= 12 && dia <= 31) {
            return true;
        } else {
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
        } else {
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

        double carga = 0.0;
        for (Tarefa t : tarefas) {
            if (t.getResponsavel() == pessoa && t.getPercentagemConclusao() != 100)
                carga += t.getTaxaEsforco();
        }
        if (carga + tarefa.getTaxaEsforco() > 1)
            return true;
        return false;
    }

    public boolean verificaRepeticao(Projeto projeto, Pessoa pessoa) {
        /**
         * Method that verifies if a person is already associated to a project.
         * @param projeto
         * @param pessoa
         * @return true if the person is already associated to the project.
         * @return false if the person isn't already associated to the project.
         */

        if (projeto.getIp() == pessoa)
            return true;

        for (int i = 0; i < projeto.getPessoasEnvolvidas().size(); i++) {
            if (projeto.getPessoasEnvolvidas().get(i) == pessoa)
                return true;
        }
        return false;
    }

    public boolean verificarSeAlgumProjeto(Pessoa pessoa) {
        /**
         * Method that verifies if Bolseiro is already in a project.
         * @return true if Bolseiro has already a project
         * @return false if Bolseiro hasn't got a project
         */

        for (Projeto p : projetos) {
            for (int i = 0; i < p.getPessoasEnvolvidas().size(); i++) {
                if (p.getPessoasEnvolvidas().get(i) == pessoa) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Docente> getDocentesProjeto(Projeto projeto) {
        ArrayList<Docente> docentesProjeto = new ArrayList<>();

        //Orientadores têm que ser Docentes e fazer parte do projeto.
        for (Docente d : getDocentes()) {
            //Percorrer pessoas envolvidas no projeto.
            for (int i = 0; i < projeto.getPessoasEnvolvidas().size(); i++) {
                if (projeto.getPessoasEnvolvidas().get(i) == d) {
                    docentesProjeto.add(d);
                }
            }
            //Ver se o docente é investigador principal do projeto.
            if (projeto.getIp() == d)
                docentesProjeto.add(d);
        }
        return docentesProjeto;
    }


    //                                             LIGAÇÃO À INTERFACE

    /**
     * Get Projetos
     *
     * @return projetos
     */
    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    /**
     * Get Pessoas
     *
     * @return pessoas
     */
    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    /**
     * Get Tarefas
     *
     * @return tarefas
     */
    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    /**
     * Get Tarefas de um Projeto
     *
     * @return tarefasProjeto
     */
    public ArrayList<Tarefa> getTarefasProjeto(Projeto projeto) {
        ArrayList<Tarefa> tarefaProjeto = new ArrayList<>();
        for (Tarefa t : projeto.getTarefas()) {
            tarefaProjeto.add(t);
        }
        return tarefaProjeto;
    }

    /**
     * Get Tarefas de um Projeto às quais podemos associar uma pessoa.
     *
     * @param projeto
     * @return tarefas disponíveis para associação
     */
    public ArrayList<Tarefa> getTarefasDisponiveis(Projeto projeto) {

        //Array das tarefas do projeto que ainda não têm um responsável.
        ArrayList<Tarefa> tarefasDisponiveis = new ArrayList<>();
        for (int i = 0; i < projeto.getTarefas().size(); i++) {
            if (projeto.getTarefas().get(i).getResponsavel() == null) {
                tarefasDisponiveis.add(projeto.getTarefas().get(i));
            }
        }
        return tarefasDisponiveis;
    }

    /**
     * Get Pessoas disponíveis para serem associadas a uma nova tarefa.
     *
     * @param projeto
     * @param tarefa
     * @return pessoas disponíveis para associação
     */
    public ArrayList<Pessoa> getPessoasDisponiveisTarefa(Projeto projeto, Tarefa tarefa) {

        ArrayList<Pessoa> pessoasDisponiveis = new ArrayList<>();

        //Encontrar bolseiros cuja duração da bolsa as permita ter a tarefa.
        //Encontrar Pessoas que não ficarão sobrecarregadas.
        for (Pessoa pessoa : projeto.getPessoasEnvolvidas()) {
            System.out.println(pessoa);
            if (pessoa.getNumeroMecanografico() == 0) {
                if (!isSobrecarregada(pessoa, tarefa)) {
                    Bolseiro bols = (Bolseiro) pessoa;
                    if (validarData2(bols.getDataInicio(), tarefa.getDataInicio()) && validarData2(tarefa.getDataFim(), bols.getDataFim()))
                        pessoasDisponiveis.add(bols);
                }
            } else {
                if (!isSobrecarregada(pessoa, tarefa))
                    pessoasDisponiveis.add(pessoa);
            }
        }
        //Adicionar o ip se existir e se não estiver sobrecarregado.
        if (!isSobrecarregada(projeto.getIp(), tarefa) && projeto.getIp() != null)
            pessoasDisponiveis.add(projeto.getIp());

        return pessoasDisponiveis;
    }

    public ArrayList<Pessoa> getPessoasDisponiveisProjeto(Projeto projeto) {
        //Array das pessoas disponíveis para serem associadas a um novo projeto:
        //Todas as pessoas que ainda não estiverem no projeto, excluindo bolseiros que já estejam noutro projeto.
        ArrayList<Pessoa> pessoasDisponiveis = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            if (!verificaRepeticao(projeto, pessoa)) {
                if (pessoa.getNumeroMecanografico() == 0) {
                    if (!verificarSeAlgumProjeto(pessoa)) {
                        pessoasDisponiveis.add(pessoa);
                    }
                } else {
                    pessoasDisponiveis.add(pessoa);
                }
            }
        }
        return pessoasDisponiveis;
    }

    public ArrayList<Docente> getOrientadoresDisponiveis(Projeto projeto, Pessoa pessoa) {
        //Obter todos os docentes do projeto.
        ArrayList<Docente> docentesProjeto = getDocentesProjeto(projeto);
        ArrayList<Docente> orientadoresPossiveis = new ArrayList<>();

        // LICENCIADO
        if (pessoa.getCusto() == 500) {
            Licenciado l = (Licenciado) pessoa;

            //Obter todos os orientadores possíveis para a pessoa.
            for (Docente d : docentesProjeto) {
                if (!l.getOrientadores().contains(d))
                    orientadoresPossiveis.add(d);
            }
        }
        // MESTRE
        if (pessoa.getCusto() == 800) {
            Mestre m = (Mestre) pessoa;

            //Obter todos os orientadores possíveis para a pessoa.
            for (Docente d : docentesProjeto) {
                if (!m.getOrientadores().contains(d))
                    orientadoresPossiveis.add(d);
            }
        }
        return orientadoresPossiveis;
    }

    public ArrayList<Docente> getIpsPossiveis(Projeto projeto) {
        ArrayList<Docente> docentes = getDocentes();
        ArrayList<Docente> ipsPossiveis = new ArrayList<>();

        for (Docente d : docentes) {
            if (!projeto.getPessoasEnvolvidas().contains(d)) {
                ipsPossiveis.add(d);
            }
        }
        return ipsPossiveis;
    }
}
