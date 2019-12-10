import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
    // Listas
    private JList pessoas;
    private JList projetos;
    private JList tarefas;
    private JList docentes;
    private Projeto projetoEscolhido;

    //Botoes
    private JButton buttonCP;
    private JButton buttonAP;
    private JButton buttonLPNC;
    private JButton buttonPC;
    private JButton buttonE;
    private JButton buttonS;
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
    private JButton atualizatTaxaExecução;
    private JButton next;


    //Labels
    private JLabel label;
    private  JLabel label2;

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

    /**
     * vai permitir o acesso a variaveis da main, e vai permitir a escrita
     * de informaçoes das jlists no espaço apropriado
     */
    public Cisuc cisuc;
    DefaultListModel<String> Nomeproj = new DefaultListModel<>();
    DefaultListModel<String> Nomepessoa = new DefaultListModel<>();


    public GUI(Cisuc cisuc) {
        this.cisuc = cisuc;

        setTitle("Gestão Projetos Cisuc");
        setSize(1500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        JPanel panel = new JPanel();
        // Absolute
        panel.setLayout(null);

        // Label
        label = new JLabel("Escolha uma das opcoes:", SwingConstants.CENTER);
        label.setBounds(500, 50, 350, 30);

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
        buttonS = new JButton("Sair");
        buttonS.setBounds(500, 300, 350, 30);

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
        buttonVoltarM.setBounds(340, 380, 200, 30);
        buttonVoltarM.setVisible(false);
        panel.add(buttonVoltarM);

        for(Projeto p: cisuc.getProjetos()) {
            Nomeproj.addElement(p.getNome() + " | Acrónimo: " + p.getAcronimo() + " | Custo Projeto: " + p.calculaCustoPorTarefa() + " | Investigador Principal: " + p.getIp().getNome() + " | Pessoas Envolvidas: " + p.getPessoasEnvolvidasNome());
        }
        projetos = new JList<>(Nomeproj);
        projetos.setBackground(new Color(178,178,178));
        projetos.setBounds(300, 40, 1100, 300);
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

        buttoncriarPsD = new JButton("Associar Pessoa");
        buttoncriarPsD.setBounds(20, 380, 200, 30);
        buttoncriarPsD.setVisible(false);
        panel.add(buttoncriarPsD);

        for(Pessoa p: cisuc.getPessoas()) {
            if(p.getNumeroMecanografico()>0){
                Docente dc = (Docente) p;
                Nomepessoa.addElement(p.getNome() + "[Docente] " +  " | mail: " + p.getMail()  +" | número Mecanográfico: " + p.getNumeroMecanografico() + " | Área Investigação: " );
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

        AreaInv = new JLabel("Área Investigação");
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
        buttoncriarPsL = new JButton("Associar Pessoa");
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
        buttoncriarPsM = new JButton("Associar Pessoa");
        buttoncriarPsM.setBounds(20, 380, 200, 30);
        buttoncriarPsM.setVisible(false);
        panel.add(buttoncriarPsM);

        // Associar Doutorados
        buttoncriarPsDr = new JButton("Associar Pessoa");
        buttoncriarPsDr.setBounds(20, 380, 200, 30);
        buttoncriarPsDr.setVisible(false);
        panel.add(buttoncriarPsDr);

        // Editar Projetos - next
        next = new JButton("Continuar");
        next.setBounds(20, 380, 200, 30);
        next.setVisible(false);
        panel.add(next);

        label2 = new JLabel("Escolha um projeto:", SwingConstants.CENTER);
        label2.setBounds(500, 20, 350, 30);

        criarTarefa= new JButton("Criar Tarefa");
        criarTarefa.setBounds(500, 100, 350, 30);
        criarTarefa.setVisible(false);
        associarTarefaPessoa = new JButton("Associar pessoa a tarefa.");
        associarTarefaPessoa.setBounds(500, 140, 350, 30);
        associarTarefaPessoa.setVisible(false);
        associarPessoaProjeto = new JButton("Associar pessoa a projeto.");
        associarPessoaProjeto.setBounds(500, 180, 350, 30);
        associarPessoaProjeto.setVisible(false);
        eliminarTarefa = new JButton("Eliminar tarefa.");
        eliminarTarefa.setBounds(500, 220, 350, 30);
        eliminarTarefa.setVisible(false);
        atualizatTaxaExecução= new JButton("Atualizar taxa execução");
        atualizatTaxaExecução.setBounds(500, 260, 350, 30);
        atualizatTaxaExecução.setVisible(false);



        panel.add(label);
        panel.add(buttonCP);
        panel.add(buttonAP);
        panel.add(buttonLPNC);
        panel.add(buttonPC);
        panel.add(buttonE);
        panel.add(buttonS);
        panel.add(criarTarefa);
        panel.add(associarTarefaPessoa);
        panel.add(associarPessoaProjeto);
        panel.add(eliminarTarefa);
        panel.add(atualizatTaxaExecução);

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

        add(panel);
        setVisible(true);
    }

    private class ActionProjeto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean a = verificaInputsVazios();
            Boolean b = verificaRepetições();
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
            Boolean b = verificaRepetiçõesPessoas();
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
            Boolean b = verificaRepetiçõesPessoas();
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
            Boolean b = verificaRepetiçõesPessoas();
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
            Boolean b = verificaRepetiçõesPessoas();
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

    private void addProjeto() {
        Projeto p = new Projeto(tfNameP.getText(), tfAcronimoP.getText(), tfDataInicioP.getText(), tfDataFimP.getText());
        cisuc.getProjetos().add(p);
        Nomeproj.addElement(p.getNome() + " | Acrónimo: " + p.getAcronimo() + " | Custo Projeto: " + p.calculaCustoPorTarefa() );
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
            Nomepessoa.addElement(d.getNome() + "[Docente]" +  " | mail: " + d.getMail()  +" | número Mecanográfico: " + d.getNumeroMecanografico() + " | Área Investigação: " + d.getAreaInvestigacao());
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
        Nomepessoa.addElement(l.getNome() + "[Licenciatura] "  + " | mail: " + l.getMail() +  " | Custo Bolsa: " + l.calculaCusto(tfdataInicioB.getText(), tfdataFimB.getText()) + " | Duração Bolsa: " + l.getDuracao_bolsa());
        tfNamePs.setText(null);
        tfMail.setText(null);
        tfdataInicioB.setText(null);
        tfdataFimB.setText(null);
    }

    private void addPessoaM() {
        Mestre m = new Mestre(tfNamePs.getText(), tfMail.getText(), tfdataInicioB.getText(), tfdataFimB.getText());
        cisuc.getPessoas().add(m);
        Nomepessoa.addElement(m.getNome() + "[Mestrado] "  + " | mail: " + m.getMail() +  " | Custo Bolsa: " + m.calculaCusto(tfdataInicioB.getText(), tfdataFimB.getText()) + " | Duração Bolsa: " + m.getDuracao_bolsa());
        tfNamePs.setText(null);
        tfMail.setText(null);
        tfdataInicioB.setText(null);
        tfdataFimB.setText(null);
    }

    private void addPessoaDr() {
        Doutorado dr = new Doutorado(tfNamePs.getText(), tfMail.getText(), tfdataInicioB.getText(), tfdataFimB.getText());
        cisuc.getPessoas().add(dr);
        Nomepessoa.addElement(dr.getNome() + "[Doutorado] "  + " | mail: " + dr.getMail() +  " | Custo Bolsa: " + dr.calculaCusto(tfdataInicioB.getText(), tfdataFimB.getText()) + " | Duração Bolsa: " + dr.getDuracao_bolsa());
        tfNamePs.setText(null);
        tfMail.setText(null);
        tfdataInicioB.setText(null);
        tfdataFimB.setText(null);
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
            JOptionPane.showMessageDialog(null, "Escreva o a Área de Investigação");
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

    private Boolean verificaRepetições(){
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

    private Boolean verificaRepetiçõesPessoas(){
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

        if (((int) Math.log10(ano) + 1) == 4 && (((int) Math.log10(mes) + 1) == 2 || ((int) Math.log10(mes) + 1) == 1) && (((int) Math.log10(dia) + 1) == 2 || ((int) Math.log10(dia) + 1) == 1 ) && ano>0 && mes>0 && dia>0 && mes<=12 && dia<=31) {
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
            buttonS.setVisible(false);
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
            buttonS.setVisible(false);
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
            atualizatTaxaExecução.setVisible(false);
            buttonVoltarM.setVisible(false);

            // Menu visivel
            label.setVisible(true);
            buttonS.setVisible(true);
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

    // Click no botão Next em editar Projeto
    private class buttonNextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            buttonDocente.setVisible(false);
            buttonBolseiro.setVisible(false);
            buttonLicenciado.setVisible(false);
            buttonMestrado.setVisible(false);
            buttonDoutorado.setVisible(false);
            label.setVisible(false);
            buttonS.setVisible(false);
            buttonPC.setVisible(false);
            buttonLPNC.setVisible(false);
            buttonE.setVisible(false);
            buttonCP.setVisible(false);
            buttonAP.setVisible(false);
            projetos.setVisible(false);
            next.setVisible(false);
            buttonE.setVisible(false);

            label.setVisible(true);
            criarTarefa.setVisible(true);
            associarTarefaPessoa.setVisible(true);
            associarPessoaProjeto.setVisible(true);
            eliminarTarefa.setVisible(true);
            atualizatTaxaExecução.setVisible(true);
            buttonVoltarM.setVisible(true);
        }
    }

    // Click no botão Editar Projeto
    private class buttonEditarProjetoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            label2.setVisible(true);
            projetos.setVisible(true);
            next.setVisible(true);
            buttonE.setVisible(false);

            MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int index = projetos.locationToIndex(e.getPoint());
                        Object item = projetos.getModel().getElementAt(index);
                        JOptionPane.showMessageDialog(null, "Projeto escolhido: " + item.toString());
                        projetoEscolhido = cisuc.getProjetos().get(index);
                        JOptionPane.showMessageDialog(null, projetoEscolhido);
                    }
                }
            };
            projetos.addMouseListener(mouseListener);

        }
    }

}
