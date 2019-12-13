import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame{
    // Listas
    private JList pessoas;
    private JList pessoasDisponiveisTarefa = new JList<>();
    private JList pessoasDisponiveisProjeto = new JList<>();
    private JList orientadores = new JList<>();
    private JList pessoasProjeto = new JList<>();
    private JList ips = new JList<>();
    private JList projetos;
    private JList tarefas = new JList<>();
    private JList tarefasForaPrazo = new JList<>();
    private JList tarefasConcluidas = new JList<>();
    private JList tarefasInacab = new JList<>();
    private Projeto projetoEscolhido;
    private Tarefa tarefaEscolhida;
    private Pessoa pessoaEscolhida;
    private Docente ipEscolhido;
    private ArrayList<Docente> orientadoresEscolhidos = new ArrayList<>();

    private int indexT;

    //Botoes
    private JButton buttonCP;
    private JButton buttonAP;
    private JButton buttonLPNC;
    private JButton buttonPC;
    private JButton buttonE;
    private JButton buttoncriarP;
    private JButton buttoncriarPsD;
    private JButton buttonVoltarM;
    private JButton buttonDocente;
    private JButton buttonBolseiro;
    private JButton buttonLicenciado;
    private JButton buttonMestrado;
    private JButton buttonDoutorado;
    private JButton buttoncriarPsL;
    private JButton buttoncriarPsM;
    private JButton buttoncriarPsDr;
    private JButton criarTarefa;
    private JButton associarTarefaPessoa;
    private JButton associarPessoaProjeto;
    private JButton eliminarTarefa;
    private JButton atualizarTaxaExecucao;
    private JButton next;
    private JButton next2;
    private JButton nextTarefas;
    private JButton nextPessoas;
    private JButton nextPessoas2;
    private JButton addOrientadores;
    private JButton addIp;
    private JButton design;
    private JButton desenvolvimento;
    private JButton documentacao;
    private JButton criarTarefaDesign;
    private JButton criarTarefaDocumentacao;
    private JButton criarTarefaDesenvolvimento;
    private JButton listarTarefasInacabadas;
    private JButton listarTarefasConcluidas;
    private JButton listarTarefasNaoIniciadas;
    private JButton delete;

    //Labels
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;

    //Inputs
    private JTextField tfNameP;
    private JTextField tfAcronimoP;
    private JTextField tfDataInicioP;
    private JTextField tfDataFimP;
    private JTextField tfNamePs;
    private JTextField tfMail;
    private JTextField tfNumMecanografico;
    private JTextField tfAreaInv;
    private JTextField tfdataInicioB;
    private JTextField tfdataFimB;
    private JTextField tfdataFimT;
    private JTextField tfdataInicioT;

    //Labels
    private JLabel NomeP;
    private JLabel AcronimoP;
    private JLabel DataInicioP;
    private JLabel DataFimP;
    private JLabel NomePs;
    private JLabel Mail;
    private JLabel NumMecanografico;
    private JLabel AreaInv;
    private JLabel dataInicioB;
    private JLabel dataFimB;
    private JLabel dataInicioT;
    private JLabel dataFimT;

    private JPanel panel;

    /**
     * vai permitir o acesso a variaveis da main, e vai permitir a escrita
     * de informaçoes das jlists no espaço apropriado
     */
    public Cisuc cisuc;
    DefaultListModel<String> Nomeproj = new DefaultListModel<>();
    DefaultListModel<String> Nomepessoa = new DefaultListModel<>();
    DefaultListModel<String> Nometarefa = new DefaultListModel<>();
    DefaultListModel<String> NomePessoasDisponiveisTarefa = new DefaultListModel<>();
    DefaultListModel<String> NomePessoasDisponiveisProjeto = new DefaultListModel<>();
    DefaultListModel<String> NomePessoasProjeto = new DefaultListModel<>();
    DefaultListModel<String> NomeOrientadores = new DefaultListModel<>();
    DefaultListModel<String> NomeIpsPossiveis = new DefaultListModel<>();
    DefaultListModel<String> TarefaForaPrazo = new DefaultListModel<>();
    DefaultListModel<String> TarefasConcluidas = new DefaultListModel<>();
    DefaultListModel<String> TarefasNaoIniciadas = new DefaultListModel<>();

    public GUI(Cisuc cisuc) {
        this.cisuc = cisuc;

        setTitle("Gestão Projetos Cisuc");
        setSize(1500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cisuc.escreverFicheirosObjetos();
                e.getWindow().dispose();
            }
        });

        setResizable(true);

        panel = new JPanel();
        // Absolute
        panel.setLayout(null);

        // Label
        label = new JLabel("Escolha uma das opcoes:", SwingConstants.CENTER);
        label.setBounds(500, 50, 350, 30);

        label3 = new JLabel("Escolha o projeto que pretende editar:", SwingConstants.CENTER);
        label3.setBounds(232, 40, 350, 30);

        label4 = new JLabel("Escolha a tarefa:", SwingConstants.CENTER);
        label4.setBounds(232, 40, 350, 30);

        label5 = new JLabel("Escolha o responsável pela tarefa:", SwingConstants.CENTER);
        label5.setBounds(232, 40, 350, 30);

        label6 = new JLabel("Tarefas do Projeto:", SwingConstants.CENTER);
        label6.setBounds(232, 40, 350, 30);

        label7 = new JLabel("Escolha a pessoa que prentende adicionar:", SwingConstants.CENTER);
        label7.setBounds(232, 40, 350, 30);

        label8 = new JLabel("Escolha o(s) orientador(es) com CTRL+C:", SwingConstants.CENTER);
        label8.setBounds(232, 40, 350, 30);

        label9 = new JLabel("Pessoas do Projeto:", SwingConstants.CENTER);
        label9.setBounds(232, 40, 350, 30);

        label10 = new JLabel("Escolha o IP do projeto:", SwingConstants.CENTER);
        label10.setBounds(232, 40, 350, 30);

        // Menu
        buttonCP = new JButton("Criar Projeto");
        buttonCP.setBounds(500, 100, 350, 30);
        buttonAP = new JButton("Adicionar Pessoas à app");
        buttonAP.setBounds(500, 140, 350, 30);
        buttonLPNC = new JButton("Listar projetos não concluidos na data estimada");
        buttonLPNC.setBounds(500, 180, 350, 30);
        buttonPC = new JButton("Listar projetos concluidos");
        buttonPC.setBounds(500, 220, 350, 30);
        buttonE = new JButton("Editar Projeto");
        buttonE.setBounds(500, 260, 350, 30);

        // CRIAR PROJETO
        NomeP = new JLabel("Nome");
        NomeP.setBounds(20,30,80,30);
        NomeP.setVisible(false);
        panel.add(NomeP);

        tfNameP = new JTextField();
        tfNameP.setBounds(20,70,200,30);
        tfNameP.setEditable(true);
        tfNameP.setVisible(false);
        panel.add(tfNameP);

        AcronimoP = new JLabel("Acronimo");
        AcronimoP.setBounds(20,110,80,30);
        AcronimoP.setVisible(false);
        panel.add(AcronimoP);

        tfAcronimoP = new JTextField();
        tfAcronimoP.setBounds(20,150,200,30);
        tfAcronimoP.setEditable(true);
        tfAcronimoP.setVisible(false);
        panel.add(tfAcronimoP);

        DataInicioP = new JLabel("Data Inicio (yyyy-MM-dd)");
        DataInicioP.setBounds(20,190,200,30);
        DataInicioP.setVisible(false);
        panel.add(DataInicioP);

        tfDataInicioP = new JTextField();
        tfDataInicioP.setBounds(20,230,200,30);
        tfDataInicioP.setEditable(true);
        tfDataInicioP.setVisible(false);
        panel.add(tfDataInicioP);

        DataFimP = new JLabel("Data Fim (yyyy-MM-dd)");
        DataFimP.setBounds(20,270,200,30);
        DataFimP.setVisible(false);
        panel.add(DataFimP);

        tfDataFimP = new JTextField();
        tfDataFimP.setBounds(20,310,200,30);
        tfDataFimP.setEditable(true);
        tfDataFimP.setVisible(false);
        panel.add(tfDataFimP);

        buttoncriarP = new JButton("Criar Projeto");
        buttoncriarP.setBounds(20, 380, 200, 30);
        buttoncriarP.setVisible(false);
        panel.add(buttoncriarP);

        buttonVoltarM = new JButton("Voltar Menu");
        buttonVoltarM.setBounds(500, 380, 350, 30);
        buttonVoltarM.setVisible(false);
        panel.add(buttonVoltarM);

        Nomeproj.clear();
        // JList de projetos
        for(Projeto p: cisuc.getProjetos()) {
            if (p.getIp() != null)
                Nomeproj.addElement(p.getNome() + " | Acrónimo: " + p.getAcronimo() + " | Custo Projeto: " + p.calculaCustoPorTarefa() + " | Investigador Principal: " + p.getIp().getNome() + " | Pessoas Envolvidas: " + p.getPessoasEnvolvidasNome());
            else
                Nomeproj.addElement(p.getNome() + " | Acrónimo: " + p.getAcronimo() + " | Custo Projeto: " + p.calculaCustoPorTarefa() + " | Pessoas Envolvidas: " + p.getPessoasEnvolvidasNome());
        }
        projetos = new JList<>(Nomeproj);
        projetos.setBackground(new Color(178,178,178));
        projetos.setBounds(300, 70, 1100, 300);
        projetos.setVisible(false);
        panel.add(projetos);

        // CRIAR PESSOA

        NomePs = new JLabel("Nome");
        NomePs.setBounds(20,30,80,30);
        NomePs.setVisible(false);
        panel.add(NomePs);

        tfNamePs = new JTextField();
        tfNamePs.setBounds(20,60,200,30);
        tfNamePs.setEditable(true);
        tfNamePs.setVisible(false);
        panel.add(tfNamePs);

        Mail = new JLabel("Mail");
        Mail.setBounds(20,90,80,30);
        Mail.setVisible(false);
        panel.add(Mail);

        tfMail = new JTextField();
        tfMail.setBounds(20,120,200,30);
        tfMail.setEditable(true);
        tfMail.setVisible(false);
        panel.add(tfMail);

        buttoncriarPsD = new JButton("Criar Pessoa");
        buttoncriarPsD.setBounds(20, 380, 200, 30);
        buttoncriarPsD.setVisible(false);
        panel.add(buttoncriarPsD);

        // JList pessoas
        for(Pessoa p: cisuc.getPessoas()) {
            if(p.getNumeroMecanografico()>0){
                Docente dc = (Docente) p;
                Nomepessoa.addElement(p.getNome() + "[Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
            }else {
                if (p.getCusto() == 1000) {
                    Doutorado d = (Doutorado) p;
                    Nomepessoa.addElement(p.getNome() + "[Doutorado] " + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(d.getDataInicio(), d.getDataFim()) + " | Duração Bolsa: " + d.getDuracao_bolsa());
                }
                else if (p.getCusto() == 800) {
                    Mestre m = (Mestre) p;
                    Nomepessoa.addElement(p.getNome() + "[Mestre]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(m.getDataInicio(), m.getDataFim()) + " | Duração Bolsa: " + m.getDuracao_bolsa() + " | Orientadores: " + m.getOrientadoresNome());
                } else if (p.getCusto() == 500){
                    Licenciado l = (Licenciado) p;
                    Nomepessoa.addElement(p.getNome() + "[Licenciado]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(l.getDataInicio(), l.getDataFim()) +" | Duração Bolsa: " + l.getDuracao_bolsa() + " | Orientadores: " + l.getOrientadoresNome());
                }
            }
        }

        pessoas = new JList<>(Nomepessoa);
        pessoas.setBackground(new Color(178,178,178));
        pessoas.setBounds(300, 40, 900, 300);
        pessoas.setVisible(false);
        panel.add(pessoas);

        buttonDocente = new JButton("Docente");
        buttonDocente.setBounds(20, 160, 100, 30);
        buttonDocente.setVisible(false);
        panel.add(buttonDocente);

        NumMecanografico = new JLabel("Numero Mecanografico");
        NumMecanografico.setBounds(20,190,200,30);
        NumMecanografico.setVisible(false);
        panel.add(NumMecanografico);

        tfNumMecanografico = new JTextField();
        tfNumMecanografico.setBounds(20,220,200,30);
        tfNumMecanografico.setEditable(true);
        tfNumMecanografico.setVisible(false);
        panel.add(tfNumMecanografico);

        AreaInv = new JLabel("Area Investigação");
        AreaInv.setBounds(20,250,200,30);
        AreaInv.setVisible(false);
        panel.add(AreaInv);

        tfAreaInv = new JTextField();
        tfAreaInv.setBounds(20,280,200,30);
        tfAreaInv.setEditable(true);
        tfAreaInv.setVisible(false);
        panel.add(tfAreaInv);

        buttonBolseiro = new JButton("Bolseiro");
        buttonBolseiro.setBounds(120, 160, 100, 30);
        buttonBolseiro.setVisible(false);
        panel.add(buttonBolseiro);

        buttonLicenciado= new JButton("Licenciado");
        buttonLicenciado.setBounds(20, 160, 100, 30);
        buttonLicenciado.setVisible(false);
        panel.add(buttonLicenciado);

        buttonMestrado= new JButton("Mestrado");
        buttonMestrado.setBounds(20, 190, 100, 30);
        buttonMestrado.setVisible(false);
        panel.add(buttonMestrado);

        buttonDoutorado= new JButton("Doutorado");
        buttonDoutorado.setBounds(20, 220, 100, 30);
        buttonDoutorado.setVisible(false);
        panel.add(buttonDoutorado);

        // Associar Licenciados
        buttoncriarPsL = new JButton("Criar Pessoa");
        buttoncriarPsL.setBounds(20, 380, 200, 30);
        buttoncriarPsL.setVisible(false);
        panel.add(buttoncriarPsL);

        dataInicioB = new JLabel("Data Inicio Bolsa");
        dataInicioB.setBounds(20,150,200,30);
        dataInicioB.setVisible(false);
        panel.add(dataInicioB);

        tfdataInicioB = new JTextField();
        tfdataInicioB.setBounds(20,180,200,30);
        tfdataInicioB.setEditable(true);
        tfdataInicioB.setVisible(false);
        panel.add(tfdataInicioB);

        dataFimB = new JLabel("Data Fim Bolsa");
        dataFimB.setBounds(20,210,200,30);
        dataFimB.setVisible(false);
        panel.add(dataFimB);

        tfdataFimB = new JTextField();
        tfdataFimB.setBounds(20,240,200,30);
        tfdataFimB.setEditable(true);
        tfdataFimB.setVisible(false);
        panel.add(tfdataFimB);

        // Associar Mestres
        buttoncriarPsM = new JButton("Criar Pessoa");
        buttoncriarPsM.setBounds(20, 380, 200, 30);
        buttoncriarPsM.setVisible(false);
        panel.add(buttoncriarPsM);

        // Associar Doutorados
        buttoncriarPsDr = new JButton("Criar Pessoa");
        buttoncriarPsDr.setBounds(20, 380, 200, 30);
        buttoncriarPsDr.setVisible(false);
        panel.add(buttoncriarPsDr);

        // Editar Projetos - next
        next = new JButton("Continuar");
        next.setBounds(20, 380, 200, 30);
        next.setVisible(false);
        panel.add(next);

        // Associar Tarefas a Pessoas - next
        nextTarefas = new JButton("Continuar");
        nextTarefas.setBounds(20, 380, 200, 30);
        nextTarefas.setVisible(false);
        panel.add(nextTarefas);

        // Associar Tarefas a Pessoas - next2
        nextPessoas = new JButton("Continuar");
        nextPessoas.setBounds(20, 380, 200, 30);
        nextPessoas.setVisible(false);
        panel.add(nextPessoas);

        // Associar Pessoa a Projeto - next
        nextPessoas2 = new JButton("Continuar");
        nextPessoas2.setBounds(20, 380, 200, 30);
        nextPessoas2.setVisible(false);
        panel.add(nextPessoas2);

        // Adicionar Orientadores
        addOrientadores = new JButton("Adicionar");
        addOrientadores.setBounds(20, 380, 200, 30);
        addOrientadores.setVisible(false);
        panel.add(addOrientadores);

        // Adicionar Ips
        addIp = new JButton("Adicionar Ip");
        addIp.setBounds(20, 380, 200, 30);
        addIp.setVisible(false);
        panel.add(addIp);

        // Associar Pessoa a Projeto - next2
        next2 = new JButton("Continuar");
        next2.setBounds(20, 380, 200, 30);
        next2.setVisible(false);
        panel.add(next2);

        label2 = new JLabel("Escolha um projeto:");
        label2.setBounds(0, 20, 350, 30);

        criarTarefa= new JButton("Criar Tarefa");
        criarTarefa.setBounds(500, 60, 350, 30);
        criarTarefa.setVisible(false);
        associarTarefaPessoa = new JButton("Associar pessoa a tarefa.");
        associarTarefaPessoa.setBounds(500, 100, 350, 30);
        associarTarefaPessoa.setVisible(false);
        associarPessoaProjeto = new JButton("Associar pessoa a projeto.");
        associarPessoaProjeto.setBounds(500, 140, 350, 30);
        associarPessoaProjeto.setVisible(false);
        atualizarTaxaExecucao= new JButton("Atualizar taxa execução");
        atualizarTaxaExecucao.setBounds(500, 180, 350, 30);
        atualizarTaxaExecucao.setVisible(false);
        listarTarefasInacabadas = new JButton("Tarefas não concluídas na data estimada.");
        listarTarefasInacabadas.setBounds(500, 220, 350, 30);
        listarTarefasInacabadas.setVisible(false);
        listarTarefasConcluidas = new JButton("Tarefas concluídas.");
        listarTarefasConcluidas.setBounds(500, 260, 350, 30);
        listarTarefasConcluidas.setVisible(false);
        listarTarefasNaoIniciadas = new JButton("Tarefas não iniciadas.");
        listarTarefasNaoIniciadas.setBounds(500, 300, 350, 30);
        listarTarefasNaoIniciadas.setVisible(false);
        eliminarTarefa = new JButton("Eliminar tarefa.");
        eliminarTarefa.setBounds(500, 340, 350, 30);
        eliminarTarefa.setVisible(false);


        criarTarefaDesign= new JButton("Criar Tarefa");
        criarTarefaDesign.setBounds(20, 380, 200, 30);
        criarTarefaDesign.setVisible(false);
        panel.add(criarTarefaDesign);

        criarTarefaDesenvolvimento= new JButton("Criar Tarefa");
        criarTarefaDesenvolvimento.setBounds(20, 380, 200, 30);
        criarTarefaDesenvolvimento.setVisible(false);
        panel.add(criarTarefaDesenvolvimento);

        criarTarefaDocumentacao= new JButton("Criar Tarefa");
        criarTarefaDocumentacao.setBounds(20, 380, 200, 30);
        criarTarefaDocumentacao.setVisible(false);
        panel.add(criarTarefaDocumentacao);

        // Delete
        delete = new JButton("Apagar");
        delete.setBounds(20, 380, 200, 30);
        delete.setVisible(false);
        panel.add(delete);

        panel.add(label);
        panel.add(buttonCP);
        panel.add(buttonAP);
        panel.add(buttonLPNC);
        panel.add(buttonPC);
        panel.add(buttonE);
        panel.add(criarTarefa);
        panel.add(associarTarefaPessoa);
        panel.add(associarPessoaProjeto);
        panel.add(eliminarTarefa);
        panel.add(atualizarTaxaExecucao);
        panel.add(listarTarefasConcluidas);
        panel.add(listarTarefasInacabadas);
        panel.add(listarTarefasNaoIniciadas);

        // Listeners
        buttonCP.addActionListener(new buttonCPListener());
        buttonAP.addActionListener(new buttonAPListener());
        buttonVoltarM.addActionListener(new buttonVoltarMListener());
        buttonBolseiro.addActionListener(new buttonBolseiroListener());
        buttonDocente.addActionListener(new buttonDocenteListener());
        buttonLicenciado.addActionListener(new buttonLicenciadoListener());
        buttonMestrado.addActionListener(new buttonMestradoListener());
        buttonDoutorado.addActionListener(new buttonDoutoradoListener());
        buttonE.addActionListener(new buttonEditarProjetoListener());
        next.addActionListener(new buttonNextListener());
        next2.addActionListener(new buttonNextListener());
        nextTarefas.addActionListener(new buttonNextListener());
        nextPessoas.addActionListener(new buttonNextListener());
        nextPessoas2.addActionListener(new buttonNextListener());
        listarTarefasInacabadas.addActionListener(new tarefasInacabadas());
        listarTarefasConcluidas.addActionListener(new tarefasConcluidas());
        listarTarefasNaoIniciadas.addActionListener(new tarefasNaoIniciadas());
        delete.addActionListener(new buttonDeleteListener());
        eliminarTarefa.addActionListener(new eliminarTarefaListener());

        // Criar Projeto
        buttoncriarP.addActionListener(new ActionProjeto());
        // Associar Docente à app
        buttoncriarPsD.addActionListener(new ActionPessoaD());
        // Associar Licenciado à app
        buttoncriarPsL.addActionListener(new ActionPessoaL());
        // Associar Mestrado à app
        buttoncriarPsM.addActionListener(new ActionPessoaM());
        // Associar Doutorado à app
        buttoncriarPsDr.addActionListener(new ActionPessoaDr());
        // Criar tarefa
        criarTarefa.addActionListener(new ActionCriarTarefa());
        // Criar Tarefa Design
        criarTarefaDesign.addActionListener(new ActionCriarTarefa2());
        // Criar Tarefa Desenvolvimento
        criarTarefaDesenvolvimento.addActionListener(new ActionCriarTarefa2());
        // Criar Tarefa Documentação
        criarTarefaDocumentacao.addActionListener(new ActionCriarTarefa2());
        // Associar Pessoa a Tarefa
        associarTarefaPessoa.addActionListener(new ActionPessoaATarefa());
        // Associar Pessoa a Projeto
        associarPessoaProjeto.addActionListener(new ActionPessoaAProjeto());
        // Adicionar Ip
        addIp.addActionListener(new ActionAddIp());
        // Adicionar Orientadores
        addOrientadores.addActionListener(new ActionAddOrientadores());

        add(panel);
        setVisible(true);
    }

    private class ActionProjeto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean a = verificaInputsVazios();
            Boolean b = verificaRepeticoes();
            Boolean c = validarData1(tfDataInicioP.getText());
            Boolean d = validarData1(tfDataFimP.getText());

            if(!a && !b && c && d) {
                Boolean f = validarData2(tfDataInicioP.getText(), tfDataFimP.getText());
                if(f){
                    addProjeto();
                }
            }
        }
    }

    // Adicionar Docente
    private class ActionPessoaD implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean a = verificaInputsVaziosDocente();
            Boolean b = verificaRepeticoesPessoas();
            if(!a && !b) {
                addPessoaD();
            }
        }
    }

    // Adicionar licenciado
    private class ActionPessoaL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean a = verificaInputsVaziosPessoaBolseiro();
            Boolean b = verificaRepeticoesPessoas();
            Boolean c = validarData1(tfdataInicioB.getText());
            Boolean d = validarData1(tfdataFimB.getText());
            if(!a && !b && c && d) {
                Boolean f = validarData2(tfdataInicioB.getText(), tfdataFimB.getText());
                if(f) {
                    addPessoaL();
                }
            }
        }
    }

    // Adicionar Mestre
    private class ActionPessoaM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Boolean a = verificaInputsVaziosPessoaBolseiro();
            Boolean b = verificaRepeticoesPessoas();
            Boolean c = validarData1(tfdataInicioB.getText());
            Boolean d = validarData1(tfdataFimB.getText());
            if(!a && !b && c && d) {
                Boolean f = validarData2(tfdataInicioB.getText(), tfdataFimB.getText());
                if(f) {
                    addPessoaM();
                }
            }
        }
    }

    // Adicionar Doutorado
    private class ActionPessoaDr implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Boolean a = verificaInputsVaziosPessoaBolseiro();
            Boolean b = verificaRepeticoesPessoas();
            Boolean c = validarData1(tfdataInicioB.getText());
            Boolean d = validarData1(tfdataFimB.getText());
            if(!a && !b && c && d) {
                Boolean f = validarData2(tfdataInicioB.getText(), tfdataFimB.getText());
                if(f) {
                    addPessoaDr();
                }
            }
        }
    }

    // Criar Tarefa
    private class ActionCriarTarefa2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Boolean a = verificaInputsVaziosTarefas();
            Boolean b = validarData1(tfdataInicioT.getText());
            Boolean c = validarData1(tfdataFimT.getText());
            Boolean d = validarData2(tfdataFimT.getText(), projetoEscolhido.getDataFim());
            Boolean f = validarData2(projetoEscolhido.getDataInicio(), tfdataInicioT.getText());

            if(!a && b && c && d && f) {
                Boolean g = validarData2(tfdataInicioT.getText(), tfdataFimT.getText());
                if (g) {
                    design.setVisible(true);
                    desenvolvimento.setVisible(true);
                    documentacao.setVisible(true);

                    if (e.getSource() == criarTarefaDesign) {
                        addTarefaDesign();
                        criarTarefaDesign.setVisible(false);
                    }
                    else if (e.getSource() == criarTarefaDesenvolvimento) {
                        addTarefaDesenvolvimento();
                        criarTarefaDesenvolvimento.setVisible(false);
                    }
                    else if (e.getSource() == criarTarefaDocumentacao) {
                        addTarefaDocumentacao();
                        criarTarefaDocumentacao.setVisible(false);
                    }
                }
            }
        }
    }

    //Adicionar IP
    private class ActionAddIp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            adicionarIp();
            label10.setVisible(false);
            addIp.setVisible(false);
            next2.setVisible(true);
        }
    }

    //Adicionar Orientador(es)
    private class ActionAddOrientadores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            adicionarOrientadores();
            addOrientadores.setVisible(false);
            label8.setVisible(false);

            if (projetoEscolhido.getIp() == null) {
                System.out.println(projetoEscolhido.getIp());
                orientadores.setVisible(false);
                label9.setVisible(false);
                label10.setVisible(true);
                panel.add(label10);

                NomeIpsPossiveis.clear();
                for(Pessoa p: cisuc.getIpsPossiveis(projetoEscolhido)) {
                    Docente dc = (Docente) p;
                    NomeIpsPossiveis.addElement(p.getNome() + "[Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
                }
                ips = new JList<>(NomeIpsPossiveis);
                ips.setBounds(350, 70, 900, 300);
                ips.setBackground(new Color(178, 178, 178));
                ips.setVisible(true);
                panel.add(ips);
                panel.setSize(panel.getWidth()-1, panel.getHeight());

                MouseListener mouseListener = new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                            int index = ips.locationToIndex(e.getPoint());
                            ipEscolhido = cisuc.getIpsPossiveis(projetoEscolhido).get(index);
                            addIp.setVisible(true);
                        }
                    }
                };
                ips.addMouseListener(mouseListener);
            }
            else {
                next2.setVisible(true);
                label9.setVisible(true);
            }
        }
    }

    private void addProjeto() {
        Projeto p = new Projeto(tfNameP.getText(), tfAcronimoP.getText(), tfDataInicioP.getText(), tfDataFimP.getText());
        cisuc.getProjetos().add(p);
        Nomeproj.addElement(p.getNome() + " | Acrónimo: " + p.getAcronimo() + " | Custo Projeto: " + p.calculaCustoPorTarefa() + " | Investigador Principal: " + (p.getIp() != null? p.getIp().getNome(): "Não Atribuído") + " | Pessoas Envolvidas: " + (p.getPessoasEnvolvidasNome() != null? p.getPessoasEnvolvidasNome(): "Não Atribuído"));
        tfNameP.setText(null);
        tfAcronimoP.setText(null);
        tfDataInicioP.setText(null);
        tfDataFimP.setText(null);
    }

    private void addPessoaD() {
        try {
            String nM = tfNumMecanografico.getText();
            int num = Integer.parseInt(nM);
            Docente d = new Docente(tfNamePs.getText(), tfMail.getText(), num, tfAreaInv.getText());
            cisuc.getPessoas().add(d);
            Nomepessoa.addElement(d.getNome() + "[Docente]" +  " | mail: " + d.getMail()  +" | número Mecanográfico: " + d.getNumeroMecanografico() + " | Area Investigação: " + d.getAreaInvestigacao());
            tfNamePs.setText(null);
            tfMail.setText(null);
            tfNumMecanografico.setText(null);
            tfAreaInv.setText(null);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Número Mecanográfico tem que ser um inteiro");
        }
    }

    private void addPessoaL() {
        Licenciado l = new Licenciado(tfNamePs.getText(), tfMail.getText(), tfdataInicioB.getText(), tfdataFimB.getText());
        cisuc.getPessoas().add(l);
        Nomepessoa.addElement(l.getNome() + "[Licenciatura] "  + " | mail: " + l.getMail() +  " | Custo Bolsa: " + l.calculaCusto(tfdataInicioB.getText(), tfdataFimB.getText()) + " | Duração Bolsa: " + l.getDuracao_bolsa() + " | Orientadores: " + (l.getOrientadoresNome()!=null?l.getOrientadoresNome(): "Não Atribuído"));
        tfNamePs.setText(null);
        tfMail.setText(null);
        tfdataInicioB.setText(null);
        tfdataFimB.setText(null);
    }

    private void addPessoaM() {
        Mestre m = new Mestre(tfNamePs.getText(), tfMail.getText(), tfdataInicioB.getText(), tfdataFimB.getText());
        cisuc.getPessoas().add(m);
        Nomepessoa.addElement(m.getNome() + "[Mestrado] "  + " | mail: " + m.getMail() +  " | Custo Bolsa: " + m.calculaCusto(tfdataInicioB.getText(), tfdataFimB.getText()) + " | Duração Bolsa: " + m.getDuracao_bolsa() + " | Orientadores: " + (m.getOrientadoresNome()!=null?m.getOrientadoresNome(): "Não Atribuído"));
        tfNamePs.setText(null);
        tfMail.setText(null);
        tfdataInicioB.setText(null);
        tfdataFimB.setText(null);
    }

    private void addPessoaDr() {
        Doutorado dr = new Doutorado(tfNamePs.getText(), tfMail.getText(), tfdataInicioB.getText(), tfdataFimB.getText());
        cisuc.getPessoas().add(dr);
        Nomepessoa.addElement(dr.getNome() + "[Doutorado] "  + " | mail: " + dr.getMail() +  " | Custo Bolsa: " + dr.calculaCusto(tfdataInicioB.getText(), tfdataFimB.getText()) + " | Duração Bolsa: " + dr.getDuracao_bolsa() );
        tfNamePs.setText(null);
        tfMail.setText(null);
        tfdataInicioB.setText(null);
        tfdataFimB.setText(null);
    }

    private void addTarefaDesign() {

        //Fazer cálculo da duração estimada, com base nas datas de início e fim e colocá-la na class da Tarefa.
        long duracaoEstimada = ChronoUnit.DAYS.between(LocalDate.parse(tfdataInicioT.getText()),  LocalDate.parse(tfdataFimT.getText()));

        Design design = new Design(tfdataInicioT.getText(), 0, tfdataFimT.getText(), duracaoEstimada);
        cisuc.getTarefas().add(design);
        cisuc.getTarefasProjeto(projetoEscolhido).add(design);
        for(Projeto p: cisuc.getProjetos()){
            if(p == projetoEscolhido){
                p.getTarefas().add(design);
            }
        }
        Nometarefa.addElement("[Tarefa de Design]" + " | Responsável: " + (design.getResponsavel() != null? design.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + design.getDataInicio() + " | Data Fim: " + design.getDataFim() + " | Percentagem Conclusão: " + design.getPercentagemConclusao() + " | Duracao Estimada: "+ design.getDuracaoEstimada());
        tfdataInicioT.setText(null);
        tfdataFimT.setText(null);
    }

    private void addTarefaDesenvolvimento() {

        //Fazer cálculo da duração estimada, com base nas datas de início e fim e colocá-la na class da Tarefa.
        long duracaoEstimada = ChronoUnit.DAYS.between(LocalDate.parse(tfdataInicioT.getText()),  LocalDate.parse(tfdataFimT.getText()));

        Desenvolvimento desenvolvimento = new Desenvolvimento(tfdataInicioT.getText(), 0, tfdataFimT.getText(), duracaoEstimada);
        cisuc.getTarefas().add(desenvolvimento);
        for (Projeto p : cisuc.getProjetos()){
            if(projetoEscolhido == p) {
                p.getTarefas().add(desenvolvimento);
            }
        }
        Nometarefa.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (desenvolvimento.getResponsavel() != null? desenvolvimento.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + desenvolvimento.getDataInicio() + " | Data Fim: " + desenvolvimento.getDataFim() + " | Percentagem Conclusão: " + desenvolvimento.getPercentagemConclusao() + " | Duracao Estimada: "+ desenvolvimento.getDuracaoEstimada());
        tfdataInicioT.setText(null);
        tfdataFimT.setText(null);
    }

    private void addTarefaDocumentacao() {

        //Fazer cálculo da duração estimada, com base nas datas de início e fim e colocá-la na class da Tarefa.
        long duracaoEstimada = ChronoUnit.DAYS.between(LocalDate.parse(tfdataInicioT.getText()),  LocalDate.parse(tfdataFimT.getText()));

        Documentacao documentacao = new Documentacao(tfdataInicioT.getText(), 0, tfdataFimT.getText(), duracaoEstimada);
        cisuc.getTarefas().add(documentacao);
        cisuc.getTarefasProjeto(projetoEscolhido).add(documentacao);
        for (Projeto p : cisuc.getProjetos()){
            if(projetoEscolhido == p) {
                p.getTarefas().add(documentacao);
            }
        }
        Nometarefa.addElement("[Tarefa de Documentação]" + " | Responsável: " + (documentacao.getResponsavel() != null? documentacao.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + documentacao.getDataInicio() + " | Data Fim: " + documentacao.getDataFim() + " | Percentagem Conclusão: " + documentacao.getPercentagemConclusao() + " | Duracao Estimada: "+ documentacao.getDuracaoEstimada());
        tfdataInicioT.setText(null);
        tfdataFimT.setText(null);
    }

    private void associarPessoaATarefa() {

        for (Tarefa t : cisuc.getTarefas()){
            if(tarefaEscolhida == t) {
                t.setResponsavel(pessoaEscolhida);
            }
        }
        for (Tarefa t : cisuc.getTarefasProjeto(projetoEscolhido)){
            if(tarefaEscolhida == t) {
                t.setResponsavel(pessoaEscolhida);
            }
        }
    }

    private void associarPessoaAProjeto() {

        for (Projeto p : cisuc.getProjetos()){
            if(p == projetoEscolhido) {
                p.getPessoasEnvolvidas().add(pessoaEscolhida);
            }
        }
    }

    private void adicionarIp() {
        for (Projeto p : cisuc.getProjetos()){
            if(projetoEscolhido == p) {
                p.setIp(ipEscolhido);
            }
        }
    }

    private void adicionarOrientadores() {
        next2.setVisible(true);

        for (int i=0; i<cisuc.getPessoas().size(); i++) {
            Pessoa p = cisuc.getPessoas().get(i);
            if (pessoaEscolhida == p && pessoaEscolhida.getCusto() == 500) {
                Licenciado l = (Licenciado) p;
                for (Docente orientador : orientadoresEscolhidos)
                    l.getOrientadores().add(orientador);
                cisuc.getPessoas().set(i, l);
            }
            if (p == pessoaEscolhida && pessoaEscolhida.getCusto() == 800) {
                Mestre m = (Mestre) p;
                for (Docente orientador : orientadoresEscolhidos)
                    m.getOrientadores().add(orientador);
                cisuc.getPessoas().set(i, m);
            }
        }
        for (int i=0; i<cisuc.getProjetos().size(); i++) {
            Projeto proj = cisuc.getProjetos().get(i);

            if (projetoEscolhido == proj) {
                for (Pessoa p : projetoEscolhido.getPessoasEnvolvidas()) {
                    if (p == pessoaEscolhida && pessoaEscolhida.getCusto() == 500) {
                        Licenciado l = (Licenciado) p;
                        for (Docente orientador : orientadoresEscolhidos)
                            l.getOrientadores().add(orientador);
                        cisuc.getPessoas().set(i, l);
                    }
                    if (p == pessoaEscolhida && pessoaEscolhida.getCusto() == 800) {
                        Mestre m = (Mestre) p;
                        for (Docente orientador : orientadoresEscolhidos)
                            m.getOrientadores().add(orientador);
                        cisuc.getPessoas().set(i, m);
                    }
                }
            }
        }
    }

    private Boolean verificaInputsVazios(){
        if(tfNameP.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o Nome do Projeto");
            return true;
        }
        if(tfAcronimoP.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o Acrónimo do Projeto");
            return true;
        }
        if(tfDataInicioP.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o a Data Inicial do Projeto");
            return true;
        }
        if(tfDataFimP.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o a Data Final do Projeto");
            return true;
        }
        return false;
    }

    private Boolean verificaInputsVaziosDocente(){
        if(tfNamePs.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o Nome.");
            return true;
        }
        if(tfMail.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o Mail.");
            return true;
        }
        if(tfNumMecanografico.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o número mecanográfico");
            return true;
        }
        if(tfAreaInv.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o a Area de Investigação");
            return true;
        }
        return false;
    }

    private Boolean verificaInputsVaziosPessoaBolseiro(){
        if(tfNamePs.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o Nome da Pessoa");
            return true;
        }
        if(tfMail.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o Mail da Pessoa");
            return true;
        }
        if(tfdataInicioB.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o a Data Inicial do Projeto");
            return true;
        }
        if(tfdataFimB.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Escreva o a Data Final do Projeto");
            return true;
        }
        return false;
    }

    private Boolean verificaInputsVaziosTarefas(){
        if(tfdataInicioT.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Introduza a Data Inicial da Tarefa.");
            return true;
        }
        if(tfdataFimT.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Introduza a Data Final da Tarefa.");
            return true;
        }
        return false;
    }

    private Boolean verificaRepeticoes(){
        // Não aceitar nomes repetidos
        for (Projeto p : cisuc.getProjetos()) {
            if (p.getNome().equals(tfNameP.getText())) {
                JOptionPane.showMessageDialog(null, "Nome do projeto já existe.");
                return true;
            }
            if (p.getAcronimo().equals(tfAcronimoP.getText())) {
                JOptionPane.showMessageDialog(null, "Acrónimo já existe.");
                return true;
            }

        }
        return false;
    }

    private Boolean verificaRepeticoesPessoas(){
        // Não aceitar nomes repetidos
        for (Pessoa p : cisuc.getPessoas()) {
            if (p.getNome().equals(tfNamePs.getText())) {
                JOptionPane.showMessageDialog(null, "Nome já existe.");
                return true;
            }
            if (p.getMail().equals(tfMail.getText())) {
                JOptionPane.showMessageDialog(null, "Mail já existe.");
                return true;
            }

        }
        return false;
    }

    public Boolean validarData1(String data) {

        String[] dataElems = data.split("-");
        int ano, mes, dia;

        try {
            ano = Integer.parseInt(dataElems[0]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ano inválido.");
            return false;
        }
        try {
            mes = Integer.parseInt(dataElems[1]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Mês inválido.");
            return false;
        }
        try {
            dia = Integer.parseInt(dataElems[2]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dia inválido.");
            return false;
        }


        if (dataElems[0].length() == 4 && dataElems[1].length() == 2 && dataElems[2].length() == 2 && ano>0 && mes>0 && dia>0 && mes<=12 && dia<=31) {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Data inválida.");
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
            JOptionPane.showMessageDialog(null, "Datas incoerentes.");
            return false;
        }
    }

    // Criar Projetos click no botão
    private class buttonCPListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Por os conteúdos da nova página visíveis
            NomeP.setVisible(true);
            AcronimoP.setVisible(true);
            DataInicioP.setVisible(true);
            DataFimP.setVisible(true);
            tfNameP.setVisible(true);
            tfAcronimoP.setVisible(true);
            tfDataInicioP.setVisible(true);
            tfDataFimP.setVisible(true);
            buttoncriarP.setVisible(true);
            buttonVoltarM.setVisible(true);
            projetos.setVisible(true);

            // Resto invisivel
            label.setVisible(false);
            buttonPC.setVisible(false);
            buttonLPNC.setVisible(false);
            buttonE.setVisible(false);
            buttonCP.setVisible(false);
            buttonAP.setVisible(false);
            NomePs.setVisible(false);
            Mail.setVisible(false);
            tfNamePs.setVisible(false);
            tfMail.setVisible(false);
            buttoncriarPsD.setVisible(false);
            pessoas.setVisible(false);
            buttonDocente.setVisible(false);
            buttonBolseiro.setVisible(false);
            tfAreaInv.setVisible(false);
            AreaInv.setVisible(false);
            tfNumMecanografico.setVisible(false);
            NumMecanografico.setVisible(false);
            buttonMestrado.setVisible(false);
            buttonDoutorado.setVisible(false);
            dataInicioB.setVisible(false);
            dataFimB.setVisible(false);
            tfdataInicioB.setVisible(false);
            tfdataFimB.setVisible(false);
            buttoncriarPsL.setVisible(false);
            buttoncriarPsDr.setVisible(false);
        }
    }

    // Criar Pessoas - click no botão
    private class buttonAPListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Por os conteúdos da nova página visíveis
            NomePs.setVisible(true);
            Mail.setVisible(true);
            tfNamePs.setVisible(true);
            tfMail.setVisible(true);
            buttonVoltarM.setVisible(true);
            pessoas.setVisible(true);
            buttonDocente.setVisible(true);
            buttonBolseiro.setVisible(true);

            // Resto invisível
            label.setVisible(false);
            buttonPC.setVisible(false);
            buttonLPNC.setVisible(false);
            buttonE.setVisible(false);
            buttonCP.setVisible(false);
            buttonAP.setVisible(false);
            NomeP.setVisible(false);
            AcronimoP.setVisible(false);
            DataInicioP.setVisible(false);
            DataFimP.setVisible(false);
            tfNameP.setVisible(false);
            tfAcronimoP.setVisible(false);
            tfDataInicioP.setVisible(false);
            tfDataFimP.setVisible(false);
            buttoncriarP.setVisible(false);
            projetos.setVisible(false);
            buttoncriarPsD.setVisible(false);
            tfAreaInv.setVisible(false);
            AreaInv.setVisible(false);
            tfNumMecanografico.setVisible(false);
            NumMecanografico.setVisible(false);
            buttonLicenciado.setVisible(false);
            buttonMestrado.setVisible(false);
            buttonDoutorado.setVisible(false);
            dataInicioB.setVisible(false);
            dataFimB.setVisible(false);
            tfdataInicioB.setVisible(false);
            tfdataFimB.setVisible(false);
            buttoncriarPsL.setVisible(false);
            buttoncriarPsM.setVisible(false);
            buttoncriarPsDr.setVisible(false);
        }
    }

    // VOLTAR AO MENU INICIAL
    private class buttonVoltarMListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            NomeP.setVisible(false);
            AcronimoP.setVisible(false);
            DataInicioP.setVisible(false);
            DataFimP.setVisible(false);
            tfNameP.setVisible(false);
            tfAcronimoP.setVisible(false);
            tfDataInicioP.setVisible(false);
            tfDataFimP.setVisible(false);
            buttoncriarP.setVisible(false);
            buttonVoltarM.setVisible(false);
            projetos.setVisible(false);
            NomePs.setVisible(false);
            Mail.setVisible(false);
            tfNamePs.setVisible(false);
            tfMail.setVisible(false);
            buttoncriarPsD.setVisible(false);
            buttonVoltarM.setVisible(false);
            pessoas.setVisible(false);
            buttonDocente.setVisible(false);
            buttonBolseiro.setVisible(false);
            tfAreaInv.setVisible(false);
            AreaInv.setVisible(false);
            tfNumMecanografico.setVisible(false);
            NumMecanografico.setVisible(false);
            buttonLicenciado.setVisible(false);
            buttonMestrado.setVisible(false);
            buttonDoutorado.setVisible(false);
            dataInicioB.setVisible(false);
            dataFimB.setVisible(false);
            tfdataInicioB.setVisible(false);
            tfdataFimB.setVisible(false);
            buttoncriarPsL.setVisible(false);
            buttoncriarPsM.setVisible(false);
            buttoncriarPsDr.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);
            buttonVoltarM.setVisible(false);
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            delete.setVisible(false);
            tarefas.setVisible(false);
            pessoasDisponiveisTarefa.setVisible(false);
            pessoasDisponiveisProjeto.setVisible(false);
            orientadores.setVisible(false);
            ips.setVisible(false);
            pessoasProjeto.setVisible(false);
            label3.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            label6.setVisible(false);
            label7.setVisible(false);
            label8.setVisible(false);
            label9.setVisible(false);

            // Menu visivel
            label.setVisible(true);
            buttonPC.setVisible(true);
            buttonLPNC.setVisible(true);
            buttonE.setVisible(true);
            buttonCP.setVisible(true);
            buttonAP.setVisible(true);
        }
    }

    // CLICK botão bolseiro
    private class buttonBolseiroListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttonDocente.setVisible(false);
            dataInicioB.setVisible(false);
            dataFimB.setVisible(false);
            tfdataInicioB.setVisible(false);
            tfdataFimB.setVisible(false);
            buttoncriarPsL.setVisible(false);
            buttoncriarPsM.setVisible(false);
            buttoncriarPsDr.setVisible(false);

            buttonBolseiro.setVisible(false);
            buttonLicenciado.setVisible(true);
            buttonMestrado.setVisible(true);
            buttonDoutorado.setVisible(true);
        }
    }

    // Click botão Docente
    private class buttonDocenteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttoncriarPsD.setVisible(true);
            buttonDocente.setVisible(false);
            buttonBolseiro.setVisible(false);
            buttonLicenciado.setVisible(false);
            dataInicioB.setVisible(false);
            dataFimB.setVisible(false);
            tfdataInicioB.setVisible(false);
            tfdataFimB.setVisible(false);
            buttoncriarPsL.setVisible(false);
            buttoncriarPsM.setVisible(false);
            buttoncriarPsDr.setVisible(false);

            tfAreaInv.setVisible(true);
            AreaInv.setVisible(true);
            tfNumMecanografico.setVisible(true);
            NumMecanografico.setVisible(true);


        }
    }

    // Click no botão Licenciado
    private class buttonLicenciadoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttoncriarPsL.setVisible(true);
            buttonDocente.setVisible(false);
            buttonBolseiro.setVisible(false);
            buttonLicenciado.setVisible(false);
            buttonMestrado.setVisible(false);
            buttonDoutorado.setVisible(false);
            buttoncriarPsM.setVisible(false);
            buttoncriarPsDr.setVisible(false);

            dataInicioB.setVisible(true);
            dataFimB.setVisible(true);
            tfdataInicioB.setVisible(true);
            tfdataFimB.setVisible(true);
        }
    }

    // Click no botão Mestrado
    private class buttonMestradoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttoncriarPsM.setVisible(true);
            buttonDocente.setVisible(false);
            buttonBolseiro.setVisible(false);
            buttonLicenciado.setVisible(false);
            buttonMestrado.setVisible(false);
            buttonDoutorado.setVisible(false);
            buttoncriarPsDr.setVisible(false);

            dataInicioB.setVisible(true);
            dataFimB.setVisible(true);
            tfdataInicioB.setVisible(true);
            tfdataFimB.setVisible(true);
        }
    }

    // Click no botão Doutorado
    private class buttonDoutoradoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttoncriarPsDr.setVisible(true);

            buttonDocente.setVisible(false);
            buttonBolseiro.setVisible(false);
            buttonLicenciado.setVisible(false);
            buttonMestrado.setVisible(false);
            buttonDoutorado.setVisible(false);

            dataInicioB.setVisible(true);
            dataFimB.setVisible(true);
            tfdataInicioB.setVisible(true);
            tfdataFimB.setVisible(true);
        }
    }

    // Click num botão Continuar
    private class buttonNextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == next) {
                projetos.setVisible(false);
                next.setVisible(false);
                label3.setVisible(false);

                criarTarefa.setVisible(true);
                associarTarefaPessoa.setVisible(true);
                associarPessoaProjeto.setVisible(true);
                eliminarTarefa.setVisible(true);
                listarTarefasNaoIniciadas.setVisible(true);
                atualizarTaxaExecucao.setVisible(true);
                buttonVoltarM.setVisible(true);
                listarTarefasConcluidas.setVisible(true);
                listarTarefasInacabadas.setVisible(true);
            }
            else if (e.getSource() == nextTarefas) {
                label4.setVisible(false);
                tarefas.setVisible(false);
                nextTarefas.setVisible(false);
                label5.setVisible(true);
                panel.add(label5);

                NomePessoasDisponiveisTarefa.clear();

                // JList pessoas
                for(Pessoa p: cisuc.getPessoasDisponiveisTarefa(projetoEscolhido, tarefaEscolhida)) {
                    if(p.getNumeroMecanografico()>0){
                        Docente dc = (Docente) p;
                        NomePessoasDisponiveisTarefa.addElement(p.getNome() + "[Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
                    } else {
                        if (p.getCusto() == 1000) {
                            Doutorado d = (Doutorado) p;
                            NomePessoasDisponiveisTarefa.addElement(p.getNome() + "[Doutorado] " + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(d.getDataInicio(), d.getDataFim()) + " | Duração Bolsa: " + d.getDuracao_bolsa());
                        }
                        else if (p.getCusto() == 800) {
                            Mestre m = (Mestre) p;
                            NomePessoasDisponiveisTarefa.addElement(p.getNome() + "[Mestre]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(m.getDataInicio(), m.getDataFim()) + " | Duração Bolsa: " + m.getDuracao_bolsa() + " | Orientadores: " + m.getOrientadoresNome());
                        } else if (p.getCusto() == 500){
                            Licenciado l = (Licenciado) p;
                            NomePessoasDisponiveisTarefa.addElement(p.getNome() + "[Licenciado]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(l.getDataInicio(), l.getDataFim()) +" | Duração Bolsa: " + l.getDuracao_bolsa() + " | Orientadores: " + l.getOrientadoresNome());
                        }
                    }
                }

                pessoasDisponiveisTarefa = new JList<>(NomePessoasDisponiveisTarefa);
                pessoasDisponiveisTarefa.setBounds(350, 70, 900, 300);
                pessoasDisponiveisTarefa.setBackground(new Color(178, 178, 178));
                pessoasDisponiveisTarefa.setVisible(true);
                panel.add(pessoasDisponiveisTarefa);
                panel.setSize(panel.getWidth() - 1, panel.getHeight());

                MouseListener mouseListener = new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                            int index = pessoasDisponiveisTarefa.locationToIndex(e.getPoint());
                            pessoaEscolhida = cisuc.getPessoasDisponiveisTarefa(projetoEscolhido, tarefaEscolhida).get(index);
                            nextPessoas.setVisible(true);
                        }
                    }
                };
                pessoasDisponiveisTarefa.addMouseListener(mouseListener);
            }
            else if (e.getSource() == nextPessoas) {
                associarPessoaATarefa();

                pessoasDisponiveisTarefa.setVisible(false);
                nextPessoas.setVisible(false);
                label5.setVisible(false);
                label6.setVisible(true);
                panel.add(label6);

                Nometarefa.clear();

                for (Tarefa t : cisuc.getTarefasProjeto(projetoEscolhido)) {
                    // Design
                    if (t.getTaxaEsforco() == 0.5) {
                        Nometarefa.addElement("[Tarefa de Design]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                    }
                    // Desenvolvimento
                    else if (t.getTaxaEsforco() == 1) {
                        Nometarefa.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                    }
                    // Documentação
                    else if (t.getTaxaEsforco() == 0.25) {
                        Nometarefa.addElement("[Tarefa de Documentação]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                    }
                }

                tarefas = new JList<>(Nometarefa);
                tarefas.setBounds(350, 70, 900, 300);
                tarefas.setBackground(new Color(178, 178, 178));
                tarefas.setVisible(true);
                panel.add(tarefas);
                panel.setSize(panel.getWidth()-1, panel.getHeight());
            }
            else if (e.getSource() == nextPessoas2) {
                pessoasDisponiveisProjeto.setVisible(false);
                nextPessoas2.setVisible(false);
                label7.setVisible(false);

                //Se a pessoa escolhida for um Bolseiro Licenciado ou um Bolseiro Mestre, pedir orientador(es).
                if (pessoaEscolhida.getNumeroMecanografico() == 0 && pessoaEscolhida.getCusto() != 1000) {
                    label8.setVisible(true);
                    panel.add(label8);
                    NomeOrientadores.clear();
                    for(Pessoa p: cisuc.getOrientadoresDisponiveis(projetoEscolhido, pessoaEscolhida)) {
                        Docente dc = (Docente) p;
                        NomeOrientadores.addElement(p.getNome() + "[Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
                    }
                    orientadores = new JList<>(NomeOrientadores);
                    orientadores.setBounds(350, 70, 900, 300);
                    orientadores.setBackground(new Color(178, 178, 178));
                    orientadores.setVisible(true);
                    panel.add(orientadores);
                    panel.setSize(panel.getWidth()-1, panel.getHeight());

                    MouseListener mouseListener = new MouseAdapter() {
                        public void mouseReleased(MouseEvent e) {
                            if (e.getClickCount() == 1) {
                                int index = orientadores.locationToIndex(e.getPoint());
                                orientadoresEscolhidos.add(cisuc.getOrientadoresDisponiveis(projetoEscolhido, pessoaEscolhida).get(index));
                                addOrientadores.setVisible(true);
                            }
                        }
                    };
                    orientadores.addMouseListener(mouseListener);
                }
                else {
                    if (projetoEscolhido.getIp() == null) {
                        associarPessoaAProjeto();
                        System.out.println(projetoEscolhido.getIp());
                        orientadores.setVisible(false);
                        label9.setVisible(false);
                        label10.setVisible(true);
                        panel.add(label10);

                        NomeIpsPossiveis.clear();
                        for (Pessoa p : cisuc.getIpsPossiveis(projetoEscolhido)) {
                            Docente dc = (Docente) p;
                            NomeIpsPossiveis.addElement(p.getNome() + "[Docente] " + " | mail: " + p.getMail() + " | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
                        }
                        ips = new JList<>(NomeIpsPossiveis);
                        ips.setBounds(350, 70, 900, 300);
                        ips.setBackground(new Color(178, 178, 178));
                        ips.setVisible(true);
                        panel.add(ips);
                        panel.setSize(panel.getWidth() - 1, panel.getHeight());

                        MouseListener mouseListener = new MouseAdapter() {
                            public void mouseReleased(MouseEvent e) {
                                if (e.getClickCount() == 1) {
                                    int index = ips.locationToIndex(e.getPoint());
                                    ipEscolhido = cisuc.getIpsPossiveis(projetoEscolhido).get(index);
                                    addIp.setVisible(true);
                                }
                            }
                        };
                        ips.addMouseListener(mouseListener);
                    } else {
                        label9.setVisible(true);
                        panel.add(label9);
                        associarPessoaAProjeto();

                        orientadores.setVisible(false);
                        next2.setVisible(false);

                        NomePessoasProjeto.clear();

                        // JList pessoas
                        if (projetoEscolhido.getIp() != null) {
                            Docente p = projetoEscolhido.getIp();
                            NomePessoasProjeto.addElement(p.getNome() + "[IP] [Docente] " + " | mail: " + p.getMail() + " | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + p.getAreaInvestigacao());
                        }
                        for (Pessoa p : projetoEscolhido.getPessoasEnvolvidas()) {
                            if (p.getNumeroMecanografico() > 0) {
                                Docente dc = (Docente) p;
                                NomePessoasProjeto.addElement(p.getNome() + "[Docente] " + " | mail: " + p.getMail() + " | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
                            } else {
                                if (p.getCusto() == 1000) {
                                    Doutorado d = (Doutorado) p;
                                    NomePessoasProjeto.addElement(p.getNome() + "[Doutorado] " + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(d.getDataInicio(), d.getDataFim()) + " | Duração Bolsa: " + d.getDuracao_bolsa());
                                } else if (p.getCusto() == 800) {
                                    Mestre m = (Mestre) p;
                                    NomePessoasProjeto.addElement(p.getNome() + "[Mestre]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(m.getDataInicio(), m.getDataFim()) + " | Duração Bolsa: " + m.getDuracao_bolsa() + " | Orientadores: " + m.getOrientadoresNome());
                                } else if (p.getCusto() == 500) {
                                    Licenciado l = (Licenciado) p;
                                    NomePessoasProjeto.addElement(p.getNome() + "[Licenciado]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(l.getDataInicio(), l.getDataFim()) + " | Duração Bolsa: " + l.getDuracao_bolsa() + " | Orientadores: " + l.getOrientadoresNome());
                                }
                            }
                        }
                        pessoasProjeto = new JList<>(NomePessoasProjeto);
                        pessoasProjeto.setBounds(350, 70, 900, 300);
                        pessoasProjeto.setBackground(new Color(178, 178, 178));
                        pessoasProjeto.setVisible(true);
                        panel.add(pessoasProjeto);
                        panel.setSize(panel.getWidth() - 1, panel.getHeight());
                    }
                }
            }
            else if (e.getSource() == next2) {
                label9.setVisible(true);
                panel.add(label9);
                addIp.setVisible(false);
                label10.setVisible(false);
                associarPessoaAProjeto();

                ips.setVisible(false);
                orientadores.setVisible(false);
                next2.setVisible(false);

                NomePessoasProjeto.clear();

                // JList pessoas
                if (projetoEscolhido.getIp() != null) {
                    Docente p = projetoEscolhido.getIp();
                    NomePessoasProjeto.addElement(p.getNome() + "[IP] [Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + p.getAreaInvestigacao());
                }
                for(Pessoa p: projetoEscolhido.getPessoasEnvolvidas()) {
                    if(p.getNumeroMecanografico()>0){
                        Docente dc = (Docente) p;
                        NomePessoasProjeto.addElement(p.getNome() + "[Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
                    }else {
                        if (p.getCusto() == 1000) {
                            Doutorado d = (Doutorado) p;
                            NomePessoasProjeto.addElement(p.getNome() + "[Doutorado] " + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(d.getDataInicio(), d.getDataFim()) + " | Duração Bolsa: " + d.getDuracao_bolsa());
                        }
                        else if (p.getCusto() == 800) {
                            Mestre m = (Mestre) p;
                            NomePessoasProjeto.addElement(p.getNome() + "[Mestre]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(m.getDataInicio(), m.getDataFim()) + " | Duração Bolsa: " + m.getDuracao_bolsa() + " | Orientadores: " + m.getOrientadoresNome());
                        } else if (p.getCusto() == 500){
                            Licenciado l = (Licenciado) p;
                            NomePessoasProjeto.addElement(p.getNome() + "[Licenciado]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(l.getDataInicio(), l.getDataFim()) +" | Duração Bolsa: " + l.getDuracao_bolsa() + " | Orientadores: " + l.getOrientadoresNome());
                        }
                    }
                }
                pessoasProjeto = new JList<>(NomePessoasProjeto);
                pessoasProjeto.setBounds(350, 70, 900, 300);
                pessoasProjeto.setBackground(new Color(178, 178, 178));
                pessoasProjeto.setVisible(true);
                panel.add(pessoasProjeto);
                panel.setSize(panel.getWidth()-1, panel.getHeight());
            }
        }
    }

    // Click no botão Editar Projeto
    private class buttonEditarProjetoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            buttonCP.setVisible(false);
            buttonAP.setVisible(false);
            label.setVisible(false);
            buttonPC.setVisible(false);
            buttonLPNC.setVisible(false);
            buttonE.setVisible(false);
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);
            buttonVoltarM.setVisible(false);

            label3.setVisible(true);
            panel.add(label3);
            projetos.setVisible(true);

            MouseListener mouseListener = new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        int index = projetos.locationToIndex(e.getPoint());
                        Object item = projetos.getModel().getElementAt(index);
                        projetoEscolhido = cisuc.getProjetos().get(index);
                        //JOptionPane.showMessageDialog(null, projetoEscolhido);
                        //JOptionPane.showMessageDialog(panel, item.toString());
                        next.setVisible(true);
                    }
                }
            };
            projetos.addMouseListener(mouseListener);
        }
    }

    // Click no botão Criar Tarefa
    private class ActionCriarTarefa implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);
            buttonVoltarM.setVisible(true);
            pessoas.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);

            Nometarefa.clear();

            // JList Tarefa
            for (Tarefa t : cisuc.getTarefasProjeto(projetoEscolhido)) {

                // Design
                if (t.getTaxaEsforco() == 0.5) {
                    System.out.println("JLIST: " + Nometarefa);
                    Nometarefa.addElement("[Tarefa de Design]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Desenvolvimento
                else if (t.getTaxaEsforco() == 1) {
                    Nometarefa.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Documentação
                else if (t.getTaxaEsforco() == 0.25) {
                    Nometarefa.addElement("[Tarefa de Documentação]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }

            }
            tarefas = new JList<>(Nometarefa);
            tarefas.setBounds(350, 70, 1100, 300);
            tarefas.setBackground(new Color(178, 178, 178));
            tarefas.setVisible(true);
            panel.add(tarefas);
            panel.setSize(panel.getWidth()-1, panel.getHeight());

            // CRIAR Tarefa
            dataInicioT = new JLabel("Data Inicio (yyyy-MM-dd) entre " + projetoEscolhido.getDataInicio() + " e " +  projetoEscolhido.getDataFim());
            dataInicioT.setBounds(20,30,350,30);
            dataInicioT.setVisible(true);
            panel.add(dataInicioT);

            tfdataInicioT = new JTextField();
            tfdataInicioT.setBounds(20,70,200,30);
            tfdataInicioT.setEditable(true);
            tfdataInicioT.setVisible(true);
            panel.add(tfdataInicioT);

            dataFimT = new JLabel("Data Fim (yyyy-MM-dd) entre " + projetoEscolhido.getDataInicio() + " e " +  projetoEscolhido.getDataFim());
            dataFimT.setBounds(20,110,350,30);
            dataFimT.setVisible(true);
            panel.add(dataFimT);

            tfdataFimT = new JTextField();
            tfdataFimT.setBounds(20,150,200,30);
            tfdataFimT.setEditable(true);
            tfdataFimT.setVisible(true);
            panel.add(tfdataFimT);

            design = new JButton("Design");
            design.setBounds(20, 210, 200, 30);
            design.setVisible(true);
            panel.add(design);

            desenvolvimento= new JButton("Desenvolvimento");
            desenvolvimento.setBounds(20, 260, 200, 30);
            desenvolvimento.setVisible(true);
            panel.add(desenvolvimento);

            documentacao= new JButton("Documentação");
            documentacao.setBounds(20, 310, 200, 30);
            documentacao.setVisible(true);
            panel.add(documentacao);

            buttonVoltarM.addActionListener(new buttonVoltarMTarefaListener());
            design.addActionListener(new buttonDesignListener());
            desenvolvimento.addActionListener(new buttonDesenvolvimentoListener());
            documentacao.addActionListener(new buttonDocumentacaoListener());
        }
    }

    //Click no botão Associar Pessoa a Tarefa
    private class ActionPessoaATarefa implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);
            buttonVoltarM.setVisible(true);
            pessoas.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            label4.setVisible(true);
            panel.add(label4);

            Nometarefa.clear();

            for (Tarefa t : cisuc.getTarefasDisponiveis(projetoEscolhido)) {
                // Design
                if (t.getTaxaEsforco() == 0.5) {
                    Nometarefa.addElement("[Tarefa de Design]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Desenvolvimento
                else if (t.getTaxaEsforco() == 1) {
                    Nometarefa.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Documentação
                else if (t.getTaxaEsforco() == 0.25) {
                    Nometarefa.addElement("[Tarefa de Documentação]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
            }

            tarefas = new JList<>(Nometarefa);
            tarefas.setBounds(350, 70, 900, 300);
            tarefas.setBackground(new Color(178, 178, 178));
            tarefas.setVisible(true);
            panel.add(tarefas);
            panel.setSize(panel.getWidth()-1, panel.getHeight());

            MouseListener mouseListener = new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        int index = tarefas.locationToIndex(e.getPoint());
                        Object item = tarefas.getModel().getElementAt(index);
                        tarefaEscolhida = cisuc.getTarefasDisponiveis(projetoEscolhido).get(index);
                        nextTarefas.setVisible(true);
                    }
                }
            };
            tarefas.addMouseListener(mouseListener);
        }
    }

    //Click no botão Associar Pessoa a Projeto
    private class ActionPessoaAProjeto implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);
            buttonVoltarM.setVisible(true);
            pessoas.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            label7.setVisible(true);
            panel.add(label7);

            NomePessoasDisponiveisProjeto.clear();

            // JList pessoas
            for(Pessoa p: cisuc.getPessoasDisponiveisProjeto(projetoEscolhido)) {
                if(p.getNumeroMecanografico()>0){
                    Docente dc = (Docente) p;
                    NomePessoasDisponiveisProjeto.addElement(p.getNome() + "[Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Area Investigação: " + dc.getAreaInvestigacao());
                }else {
                    if (p.getCusto() == 1000) {
                        Doutorado d = (Doutorado) p;
                        NomePessoasDisponiveisProjeto.addElement(p.getNome() + "[Doutorado] " + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(d.getDataInicio(), d.getDataFim()) + " | Duração Bolsa: " + d.getDuracao_bolsa());
                    }
                    else if (p.getCusto() == 800) {
                        Mestre m = (Mestre) p;
                        NomePessoasDisponiveisProjeto.addElement(p.getNome() + "[Mestre]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(m.getDataInicio(), m.getDataFim()) + " | Duração Bolsa: " + m.getDuracao_bolsa() + " | Orientadores: " + m.getOrientadoresNome());
                    } else if (p.getCusto() == 500){
                        Licenciado l = (Licenciado) p;
                        NomePessoasDisponiveisProjeto.addElement(p.getNome() + "[Licenciado]" + " | mail: " + p.getMail() + " | Custo Bolsa: " + p.calculaCusto(l.getDataInicio(), l.getDataFim()) +" | Duração Bolsa: " + l.getDuracao_bolsa() + " | Orientadores: " + l.getOrientadoresNome());
                    }
                }
            }
            pessoasDisponiveisProjeto = new JList<>(NomePessoasDisponiveisProjeto);
            pessoasDisponiveisProjeto.setBounds(350, 70, 900, 300);
            pessoasDisponiveisProjeto.setBackground(new Color(178, 178, 178));
            pessoasDisponiveisProjeto.setVisible(true);
            panel.add(pessoasDisponiveisProjeto);
            panel.setSize(panel.getWidth()-1, panel.getHeight());

            MouseListener mouseListener = new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        int index = pessoasDisponiveisProjeto.locationToIndex(e.getPoint());
                        pessoaEscolhida = cisuc.getPessoasDisponiveisProjeto(projetoEscolhido).get(index);
                        nextPessoas2.setVisible(true);
                    }
                }
            };
            pessoasDisponiveisProjeto.addMouseListener(mouseListener);
        }
    }

    private class buttonVoltarMTarefaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tarefas.setVisible(false);
            dataFimT.setVisible(false);
            dataInicioT.setVisible(false);
            tfdataFimT.setVisible(false);
            tfdataInicioT.setVisible(false);
            design.setVisible(false);
            desenvolvimento.setVisible(false);
            documentacao.setVisible(false);
            criarTarefaDesign.setVisible(false);
            criarTarefaDesenvolvimento.setVisible(false);
            criarTarefaDocumentacao.setVisible(false);
        }
    }

    // Click Design
    private class buttonDesignListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            design.setVisible(false);
            desenvolvimento.setVisible(false);
            documentacao.setVisible(false);
            criarTarefaDesign.setVisible(true);
        }
    }

    // Click Desenvolvimento
    private class buttonDesenvolvimentoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            design.setVisible(false);
            desenvolvimento.setVisible(false);
            documentacao.setVisible(false);
            criarTarefaDesenvolvimento.setVisible(true);
        }
    }

    // Click Documentacao
    private class buttonDocumentacaoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            design.setVisible(false);
            desenvolvimento.setVisible(false);
            documentacao.setVisible(false);
            criarTarefaDocumentacao.setVisible(true);
        }
    }



    // Listar tarefas inacabadas
    private class tarefasInacabadas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TarefaForaPrazo.clear();
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);

            for(Tarefa t:cisuc.getTarefasForaPrazo(projetoEscolhido)){
                if (t.getTaxaEsforco() == 0.5) {
                    TarefaForaPrazo.addElement("[Tarefa de Design]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Desenvolvimento
                else if (t.getTaxaEsforco() == 1) {
                    TarefaForaPrazo.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Documentação
                else if (t.getTaxaEsforco() == 0.25) {
                    TarefaForaPrazo.addElement("[Tarefa de Documentação]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
            }
            tarefasForaPrazo = new JList<>(TarefaForaPrazo);
            tarefasForaPrazo.setBackground(new Color(178,178,178));
            tarefasForaPrazo.setBounds(300, 70, 900, 300);
            tarefasForaPrazo.setVisible(true);
            panel.add(tarefasForaPrazo);
            panel.setSize(panel.getWidth()-1, panel.getHeight());

            buttonVoltarM.addActionListener(new buttonVoltarMTarefaForaPrazoListener());
        }
    }

    private class buttonVoltarMTarefaForaPrazoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           tarefasForaPrazo.setVisible(false);
        }
    }

    private class tarefasConcluidas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);

            for(Tarefa t:cisuc.getTarefasConcluidas(projetoEscolhido)){
                if (t.getTaxaEsforco() == 0.5) {
                    TarefasConcluidas.addElement("[Tarefa de Design]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Desenvolvimento
                else if (t.getTaxaEsforco() == 1) {
                    TarefasConcluidas.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Documentação
                else if (t.getTaxaEsforco() == 0.25) {
                    TarefasConcluidas.addElement("[Tarefa de Documentação]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
            }
            tarefasConcluidas = new JList<>(TarefasConcluidas);
            tarefasConcluidas.setBackground(new Color(178,178,178));
            tarefasConcluidas.setBounds(300, 70, 900, 300);
            tarefasConcluidas.setVisible(true);
            panel.add(tarefasConcluidas);
            panel.setSize(panel.getWidth()-1, panel.getHeight());

            buttonVoltarM.addActionListener(new buttonVoltarMTarefaConcluidasListener());
        }
    }

    private class buttonVoltarMTarefaConcluidasListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tarefasConcluidas.setVisible(false);
        }
    }

    private class tarefasNaoIniciadas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);

            for(Tarefa t:cisuc.getTarefasNaoIniciadas(projetoEscolhido)){
                if (t.getTaxaEsforco() == 0.5) {
                    TarefasNaoIniciadas.addElement("[Tarefa de Design]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Desenvolvimento
                else if (t.getTaxaEsforco() == 1) {
                    TarefasNaoIniciadas.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Documentação
                else if (t.getTaxaEsforco() == 0.25) {
                    TarefasNaoIniciadas.addElement("[Tarefa de Documentação]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
            }
            tarefasInacab = new JList<>(TarefasNaoIniciadas);
            tarefasInacab.setBackground(new Color(178,178,178));
            tarefasInacab.setBounds(300, 70, 900, 300);
            tarefasInacab.setVisible(true);
            panel.add(tarefasInacab);
            panel.setSize(panel.getWidth()-1, panel.getHeight());

            buttonVoltarM.addActionListener(new buttonVoltarMTarefaInacabadasListener());
        }
    }

    private class buttonVoltarMTarefaInacabadasListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tarefasInacab.setVisible(false);
        }
    }

    // Click no botão Eliminar tarefa
    private class eliminarTarefaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            buttonCP.setVisible(false);
            buttonAP.setVisible(false);
            label.setVisible(false);
            buttonPC.setVisible(false);
            buttonLPNC.setVisible(false);
            buttonE.setVisible(false);
            listarTarefasConcluidas.setVisible(false);
            listarTarefasInacabadas.setVisible(false);
            label.setVisible(false);
            criarTarefa.setVisible(false);
            associarTarefaPessoa.setVisible(false);
            associarPessoaProjeto.setVisible(false);
            eliminarTarefa.setVisible(false);
            listarTarefasNaoIniciadas.setVisible(false);
            atualizarTaxaExecucao.setVisible(false);
            buttonVoltarM.setVisible(false);

            Nometarefa.clear();

            // JList Tarefa
            for (Tarefa t : cisuc.getTarefasProjeto(projetoEscolhido)) {

                // Design
                if (t.getTaxaEsforco() == 0.5) {
                    System.out.println("JLIST: " + Nometarefa);
                    Nometarefa.addElement("[Tarefa de Design]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Desenvolvimento
                else if (t.getTaxaEsforco() == 1) {
                    Nometarefa.addElement("[Tarefa de Desenvolvimento]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }
                // Documentação
                else if (t.getTaxaEsforco() == 0.25) {
                    Nometarefa.addElement("[Tarefa de Documentação]" + " | Responsável: " + (t.getResponsavel() != null? t.getResponsavel().getNome():"Não Atribuído") + " | Data Inicio: " + t.getDataInicio() + " | Data Fim: " + t.getDataFim() + " | Percentagem Conclusão: " + t.getPercentagemConclusao() + " | Duracao Estimada: "+ t.getDuracaoEstimada());
                }

            }
            tarefas = new JList<>(Nometarefa);
            tarefas.setBounds(350, 70, 1100, 300);
            tarefas.setBackground(new Color(178, 178, 178));
            tarefas.setVisible(true);
            panel.add(tarefas);
            panel.setSize(panel.getWidth()-1, panel.getHeight());

            MouseListener mouseListener = new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        indexT = tarefas.locationToIndex(e.getPoint());
                        tarefaEscolhida = cisuc.getTarefas().get(indexT);
                        JOptionPane.showMessageDialog(null, indexT);
                        //JOptionPane.showMessageDialog(panel, item.toString());
                        delete.setVisible(true);
                    }
                }
            };
            tarefas.addMouseListener(mouseListener);

            buttonVoltarM.addActionListener(new buttonVoltarMDeleteistener());
        }
    }

    private class buttonDeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Eliminar tarefa do array das tarefas do projeto.
            for (Projeto p : cisuc.getProjetos()){
                if(projetoEscolhido == p) {
                    p.getTarefas().remove(tarefaEscolhida);
                }
            }
            //Eliminar tarefa do array que guarda todas as tarefas.
            cisuc.getTarefas().remove(tarefaEscolhida);
            tarefas.remove(indexT);

            buttonVoltarM.setVisible(true);

        }
    }

    private class buttonVoltarMDeleteistener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

           tarefas.setVisible(false);

        }
    }

}
