import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Projeto {

    static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    static final SimpleDateFormat formato3 = new SimpleDateFormat("dd-MM-yyyy");
    static final DateFormat formato2 = new SimpleDateFormat("EEEE");
    static final  Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        String segundaFeiraString = "2022-01-10";
        Date segundaFeiraData = formato.parse(segundaFeiraString);
        String mensagemSegundaFeiraReferencia = formato2.format(segundaFeiraData);

        int indexInicio = -1;
        int indexFinal = -1;
        int indexInicio1 = -1;
        int indexFinal1 = -1;
        int indexInicio2 = -1;
        int indexFinal2 = -1;
        boolean modoPrevisao = false;
        boolean existeArgumentoR = false;
        boolean existeArgumentoT = false;
        boolean ficheirosValidos = false;
        boolean dataInicialExiste = false;
        boolean dataFinalExiste = false;
        boolean dataInicialExiste1 = false;
        boolean dataFinalExiste1 = false;
        boolean dataInicialExiste2 = false;
        boolean dataFinalExiste2 = false;
        boolean argumentosValidos = false;
        String resolucaoTemporal = "-1";
        String dataInicial = "";
        String dataFinal = "";
        String dataInicial1 = "";
        String dataFinal1 = "";
        String dataInicial2 = "";
        String dataFinal2 = "";
        String ficheiroEntradaTotal = "";
        String ficheiroEntrada = "";
        String ficheiroSaida = "";
        String ficheiroMatriz = "";
        String dataPrevisao = "";


        /****************************************************************
         *                                                              *
         *                INICIO MODO NÃO INTERATIVO                    *
         *                                                              *
         ****************************************************************/

        if (args.length >= 5 && args.length <= 20) { // verificar se é um numero válido de argumentos
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]) {
                    case "-r": {
                        resolucaoTemporal = args[i + 1];
                        existeArgumentoR = true;
                        break;
                    }
                    case "-di": {
                        dataInicial = args[i + 1];
                        dataInicialExiste = true;
                        break;
                    }
                    case "-df": {
                        dataFinal = args[i + 1];
                        dataFinalExiste = true;
                        break;
                    }
                    case "-di1": {
                        dataInicial1 = args[i + 1];
                        dataInicialExiste1 = true;
                        break;
                    }
                    case "-df1": {
                        dataFinal1 = args[i + 1];
                        dataFinalExiste1 = true;
                        break;
                    }
                    case "-di2": {
                        dataInicial2 = args[i + 1];
                        dataInicialExiste2 = true;
                        break;
                    }
                    case "-df2": {
                        dataFinal2 = args[i + 1];
                        dataFinalExiste2 = true;
                        break;
                    }
                    case "-T": {
                        dataPrevisao = args[i + 1];
                        existeArgumentoT = true;
                    }

                }
            }

            if (existeArgumentoR == true && existeArgumentoT == false) {
                File ficheiroEntradaTemp = new File(args[args.length - 2]);
                File ficheiroSaidaTemp = new File(args[args.length - 1]);
                if (ficheiroEntradaTemp.exists() && ficheiroSaidaTemp.exists()) {
                    ficheiroEntrada = args[args.length - 2];
                    ficheiroSaida = args[args.length - 1];
                    ficheirosValidos = true;
                } else {
                    System.out.println("Os ficheiros introduzidos não foram encontrados.");
                }
                if (dataInicialExiste == true && dataFinalExiste == true && dataInicialExiste1 == true && dataFinalExiste1 == true && dataInicialExiste2 == true && dataFinalExiste2 == true){
                    argumentosValidos = true;
                } else {
                    System.out.println("Argumentos inválidos! Utilize um dos seguintes sintaxes:");
                    System.out.println();
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MM-AAAA -di1 DD-MMAAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MM-AAAA -T DD-MM-AAAA registoNumeroTotalCovid19.csv registoNumerosAcumuladosCovid19.csv matrizTransicao.txt nome_ficheiro_saida.txt");
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MMAAAA -di1 DD-MM-AAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MMAAAA registoNumerosAcumuladosCovid19.csv nome_ficheiro saida.txt");
                    System.out.println("java -jar nome_programa.jar -T DD-MM-AAAA registoNumerosTotalCovid19.csv matrizTransicao.csv nome_ficheiro_saida.txt");
                }
            }

            if (existeArgumentoR == false && existeArgumentoT == true) {
                File ficheiroEntradaTotalTemp = new File(args[args.length - 3]);
                File ficheiroSaidaTemp = new File(args[args.length - 1]);
                File ficheiroMatrizTemp = new File(args[args.length - 2]);
                if (ficheiroEntradaTotalTemp.exists() && ficheiroSaidaTemp.exists() && ficheiroMatrizTemp.exists()) {
                    ficheiroEntradaTotal = args[args.length - 3];
                    ficheiroSaida = args[args.length - 1];
                    ficheiroMatriz = args[args.length - 2];
                    ficheirosValidos = true;
                    modoPrevisao = true;
                } else {
                    System.out.println("Os ficheiros introduzidos não foram encontrados.");
                }
            }

            if (existeArgumentoR == true && existeArgumentoT == true) {
                File ficheiroEntradaTotalTemp = new File(args[args.length - 4]);
                File ficheiroEntradaTemp = new File(args[args.length - 3]);
                File ficheiroSaidaTemp = new File(args[args.length - 1]);
                File ficheiroMatrizTemp = new File(args[args.length - 2]);
                if (ficheiroEntradaTemp.exists() && ficheiroSaidaTemp.exists() && ficheiroMatrizTemp.exists() && ficheiroEntradaTotalTemp.exists()) {
                    ficheiroEntrada = args[args.length - 3];
                    ficheiroSaida = args[args.length - 1];
                    ficheiroMatriz = args[args.length - 2];
                    ficheiroEntradaTotal = args[args.length - 4];
                    ficheirosValidos = true;
                    modoPrevisao = true;
                } else {
                    System.out.println("Os ficheiros introduzidos não foram encontrados.");
                }
                if (dataInicialExiste == true && dataFinalExiste == true && dataInicialExiste1 == true && dataFinalExiste1 == true && dataInicialExiste2 == true && dataFinalExiste2 == true){
                    argumentosValidos = true;
                } else {
                    System.out.println("Argumentos inválidos! Utilize um dos seguintes sintaxes:");
                    System.out.println();
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MM-AAAA -di1 DD-MMAAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MM-AAAA -T DD-MM-AAAA registoNumeroTotalCovid19.csv registoNumerosAcumuladosCovid19.csv matrizTransicao.txt nome_ficheiro_saida.txt");
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MMAAAA -di1 DD-MM-AAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MMAAAA registoNumerosAcumuladosCovid19.csv nome_ficheiro saida.txt");
                    System.out.println("java -jar nome_programa.jar -T DD-MM-AAAA registoNumerosTotalCovid19.csv matrizTransicao.csv nome_ficheiro_saida.txt");
                }
            }

            PrintWriter pw = new PrintWriter(ficheiroSaida);
            if (ficheirosValidos == true){
                pw = new PrintWriter(ficheiroSaida);
            }

            if (ficheirosValidos == true && argumentosValidos == true) {
                int numeroDeLinhas = ContarLinhasDoFicheiro(ficheiroEntrada) - 1;

                Date[] listaDeDatas = new Date[numeroDeLinhas];
                listaDeDatas = PreencherListaDeDatas(ficheiroEntrada, listaDeDatas);
                int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
                listaDeCasosNaoInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 1, listaDeCasosNaoInfetados);
                int[] listaDeInfetados = new int[numeroDeLinhas];
                listaDeInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 2, listaDeInfetados);
                int[] listaDeHospitalizados = new int[numeroDeLinhas];
                listaDeHospitalizados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 3, listaDeHospitalizados);
                int[] listaDeInternadosUCI = new int[numeroDeLinhas];
                listaDeInternadosUCI = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 4, listaDeInternadosUCI);
                int[] listaDeMortes = new int[numeroDeLinhas];
                listaDeMortes = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 5, listaDeMortes);


                indexInicio = ProcurarPosicaoData(dataInicial, listaDeDatas);
                indexFinal = ProcurarPosicaoData(dataFinal, listaDeDatas);
                indexInicio1 = ProcurarPosicaoData(dataInicial1, listaDeDatas);
                indexFinal1 = ProcurarPosicaoData(dataFinal1, listaDeDatas);
                indexInicio2 = ProcurarPosicaoData(dataInicial2, listaDeDatas);
                indexFinal2 = ProcurarPosicaoData(dataFinal2, listaDeDatas);


                int modoTemporal = Integer.parseInt(resolucaoTemporal);


                if (indexInicio != -1 && indexFinal != -1) {

                    //ANALISE SEMANAL
                    if (modoTemporal == 1) {
                        if (listaDeDatas[indexInicio].before(listaDeDatas[indexFinal])) {
                            ModoSemanalMostrarDados(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                            ModoSemanalMostrarDadosFicheiro(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, pw);
                        } else {
                            System.out.println("O intervalo de datas que foi introduzido é inválido.");
                        }
                    }


                    // FIM DA ANALISE SEMANAL


                    // ANALISE DIARIA
                    if (modoTemporal == 0) {
                        if (listaDeDatas[indexInicio].before(listaDeDatas[indexFinal])) {
                            MostrarValoresDoDia(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                            MostrarNovosCasosDiarios(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                            MostrarValoresDoDiaFicheiro(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio, pw);
                            MostrarNovosCasosDiariosFicheiro(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio, pw);
                        } else {
                            System.out.println("O intervalo de datas que foi introduzido é inválido.");
                        }
                    }



                    // FIM ANALISE DIARIA

                    //ANALISE MENSAL
                    if (modoTemporal == 2) {
                        if (listaDeDatas[indexInicio].before(listaDeDatas[indexFinal])) {
                            ModoMensalMostrarDadosMes(indexInicio, indexFinal, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                            ModoMensalMostrarDadosMesFicheiro(indexInicio, indexFinal, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, pw);
                            int indexPrimeiroDiaMes = ProcurarPrimeiroDiaMesParaAnalizar(indexInicio, indexFinal, listaDeDatas);
                            int indexUltimoDiaMes = ProcurarUltimoDiaMesParaAnalizar(indexInicio, indexFinal, listaDeDatas);
                            int qtMeses = (CalcularQuantidadeDeMesesParaAvaliar(indexPrimeiroDiaMes, indexUltimoDiaMes, listaDeDatas));
                            if (qtMeses > 1) {
                                ModoMensalMostrarDados(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                                ModoMensalMostrarDadosFicheiro(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, pw);
                            } else{
                                System.out.println("Impossivel fazer a comparação mensal, especifique um intervalo de datas maior");

                            }
                        } else {
                            System.out.println("O intervalo de datas que foi introduzido é inválido.");
                        }


                    }
                    // FIM ANALISE MENSAL

                    //COMPARAÇÕES 2.2
                    double[] medias = new double[3];
                    double[] desvioPadrao = new double[3];
                    System.out.println();
                    System.out.println("infetados");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeInfetados, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();
                    System.out.println("hospitalizados");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();
                    System.out.println("internados na UCI");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeInternadosUCI, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();
                    System.out.println("obitos");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeMortes, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();

                } else {
                    System.out.println("As datas introduzidas não se encontram no ficheiro especificado.");
                    System.out.println();
                }
                //FIM COMPARAÇÕES 2.2


            }

            //INICIO PREVISOES 2.3

            if (modoPrevisao == true){

                double [][] matrizProbalidades = LerFicheiroMatriz(ficheiroMatriz);
                double [][] matrizQ = new double[4][4];
                for (int i = 0; i < matrizQ.length ; i++) {
                    for (int j = 0; j < matrizQ[i].length; j++) {
                        matrizQ[i][j] = matrizProbalidades[i][j];
                    }
                }
                double [][] matrizI = new double[4][4];
                for (int i = 0; i < matrizI.length ; i++) {
                    for (int j = 0; j < matrizI[i].length; j++) {
                        matrizQ[i][j] = 0;
                    }
                }
                matrizI[0][0] = 1;
                matrizI[1][1] = 1;
                matrizI[2][2] = 1;
                matrizI[3][3] = 1;

                double [][] matrizL = new double[4][4];
                double [][] matrizU = new double[4][4];

                int numeroDeLinhas = ContarLinhasDoFicheiro(ficheiroEntradaTotal) - 1;
                Date[] listaDeDatas = new Date[numeroDeLinhas];
                listaDeDatas = PreencherListaDeDatas(ficheiroEntradaTotal, listaDeDatas);
                int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
                listaDeCasosNaoInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 1, listaDeCasosNaoInfetados);
                int[] listaDeInfetados = new int[numeroDeLinhas];
                listaDeInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 2, listaDeInfetados);
                int[] listaDeHospitalizados = new int[numeroDeLinhas];
                listaDeHospitalizados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 3, listaDeHospitalizados);
                int[] listaDeInternadosUCI = new int[numeroDeLinhas];
                listaDeInternadosUCI = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 4, listaDeInternadosUCI);
                int[] listaDeObitos = new int[numeroDeLinhas];
                listaDeObitos = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 5, listaDeObitos);

                int indexDataMaisProxima = ProcurarDataMaisProxima(dataPrevisao, listaDeDatas);

                if (indexDataMaisProxima == -1){
                    System.out.println("Data de previsão inválida.");
                } else {

                    Date dataMaisProxima = listaDeDatas[indexDataMaisProxima];
                    int qtDiasDiferenca = CalcularQuantidadeDiasPrevisao(dataPrevisao, dataMaisProxima);
                    int valorNaoInfetadosInicial = listaDeCasosNaoInfetados[indexDataMaisProxima];
                    int valorInfetadosInicial = listaDeInfetados[indexDataMaisProxima];
                    int valorHospitalizadosInicial = listaDeHospitalizados[indexDataMaisProxima];
                    int valorHospitalizadosUCIInicial = listaDeInternadosUCI[indexDataMaisProxima];
                    int valorObitosInicial = listaDeObitos[indexDataMaisProxima];

                    double[][] matrizValores = new double[5][1];
                    matrizValores[0][0] = valorNaoInfetadosInicial;
                    matrizValores[1][0] = valorInfetadosInicial;
                    matrizValores[2][0] = valorHospitalizadosInicial;
                    matrizValores[3][0] = valorHospitalizadosUCIInicial;
                    matrizValores[4][0] = valorObitosInicial;

                    double[][] matrizProbalidadesTemporaria = matrizProbalidades;
                    for (int i = 1; i < qtDiasDiferenca; i++) {
                        matrizProbalidades = MultiplicarMatrizes(matrizProbalidades, matrizProbalidadesTemporaria);
                    }
                    double[][] matrizResultados = MultiplicarMatrizes(matrizProbalidades, matrizValores);

                    double valorNaoInfetadosPrevisto = matrizResultados[0][0];
                    double valorInfetadosPrevisto = matrizResultados[1][0];
                    double valorHospitalizadosPrevisto = matrizResultados[2][0];
                    double valorHospitalizadosUCIPrevisto = matrizResultados[3][0];
                    double valorObitosPrevisto = matrizResultados[4][0];

                    System.out.println("O numero previsto de não infetados para o dia " + dataPrevisao + " é " + valorNaoInfetadosPrevisto + ".");
                    System.out.println("O numero previsto de infetados para o dia " + dataPrevisao + " é " + valorInfetadosPrevisto + ".");
                    System.out.println("O numero previsto de hospitalizados para o dia " + dataPrevisao + " é " + valorHospitalizadosPrevisto + ".");
                    System.out.println("O numero previsto de hospitalizados UCI para o dia " + dataPrevisao + " é " + valorHospitalizadosUCIPrevisto + ".");
                    System.out.println("O numero previsto de óbitos para o dia " + dataPrevisao + " é " + valorObitosPrevisto + ".");
                    pw.println("O numero previsto de não infetados para o dia " + dataPrevisao + " é " + valorNaoInfetadosPrevisto + ".");
                    pw.println("O numero previsto de infetados para o dia " + dataPrevisao + " é " + valorInfetadosPrevisto + ".");
                    pw.println("O numero previsto de hospitalizados para o dia " + dataPrevisao + " é " + valorHospitalizadosPrevisto + ".");
                    pw.println("O numero previsto de hospitalizados UCI para o dia " + dataPrevisao + " é " + valorHospitalizadosUCIPrevisto + ".");
                    pw.println("O numero previsto de óbitos para o dia " + dataPrevisao + " é " + valorObitosPrevisto + ".");



                }
            }


            pw.close();
            //FIM PREVISOES 2.3
            /****************************************************************
             *                                                              *
             *                   FIM MODO NÃO INTERATIVO                    *
             *                                                              *
             ****************************************************************/

        } else {
            System.out.println("Modo interativo iniciado.");


            /****************************************************************
             *                                                              *
             *                    INICIO MODO INTERATIVO                    *
             *                                                              *
             ****************************************************************/


            boolean modoInterativo = true;
            for (int z = 0; z < 1; z++){
                System.out.println("Introduza o nome do ficheiro de saida a ser utilizado ou sair se não pretender a utilização de um.");
                String nomeDoFicheiroDeOutput = sc.nextLine();
                File ficheiroSaidaTemp = new File(nomeDoFicheiroDeOutput);
                boolean ficheiroSaidaValido = true;
                while (!(ficheiroSaidaTemp.exists()) && !(nomeDoFicheiroDeOutput.equals("sair"))) {
                    System.out.println("O ficheiro introduzido não é valido, insira um ficheiro valido para começar, ou escreva sair se não pretender usar um ficheiro de saida.");
                    nomeDoFicheiroDeOutput = sc.nextLine();
                    ficheiroSaidaTemp = new File(ficheiroEntrada);
                }
                if (nomeDoFicheiroDeOutput.equals("sair")){
                    ficheiroSaidaValido = false;
                }

                PrintWriter pw = new PrintWriter(nomeDoFicheiroDeOutput);
                String comando1;
                String comando = "";
                System.out.println("Introduza o nome do ficheiro de entrada a ser utilizado.");
                ficheiroEntrada = sc.nextLine();
                if (ficheiroEntrada.equals("sair")){
                    break;
                }
                File ficheiroEntradaTemp = new File(ficheiroEntrada);

                while (!(ficheiroEntradaTemp.exists()) && !(ficheiroEntrada.equals("sair"))) {
                    System.out.println("O ficheiro introduzido não é valido, insira um ficheiro valido para começar, ou escreva sair se quiser sair do programa");
                    ficheiroEntrada = sc.nextLine();
                    ficheiroEntradaTemp = new File(ficheiroEntrada);
                }

                if (ficheiroEntradaTemp.exists()) {
                    while (!(comando.equals("sair"))) {

                        int numeroDeLinhas = ContarLinhasDoFicheiro(ficheiroEntrada) - 1;
                        Date[] listaDeDatas = new Date[numeroDeLinhas];
                        listaDeDatas = PreencherListaDeDatas(ficheiroEntrada, listaDeDatas);
                        int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
                        listaDeCasosNaoInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 1, listaDeCasosNaoInfetados);
                        int[] listaDeInfetados = new int[numeroDeLinhas];
                        listaDeInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 2, listaDeInfetados);
                        int[] listaDeHospitalizados = new int[numeroDeLinhas];
                        listaDeHospitalizados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 3, listaDeHospitalizados);
                        int[] listaDeInternadosUCI = new int[numeroDeLinhas];
                        listaDeInternadosUCI = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 4, listaDeInternadosUCI);
                        int[] listaDeMortes = new int[numeroDeLinhas];
                        listaDeMortes = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 5, listaDeMortes);

                        System.out.println();
                        System.out.println("Introduza o comando 'sair' para encerrar o programa.");
                        System.out.println("Introduza o número da alínea que quer usar.");
                        System.out.println("1) visualizar o valores da pandemia.");
                        System.out.println("2) análise comparativa de dados.");
                        System.out.println("3) previsão da evolução da pandemia.");
                        System.out.println("4) mudar de ficheiro de entrada.");
                        comando = sc.nextLine();
                        switch (comando) {
                            case "1": {

                                System.out.println("Introduza a data inicial na forma dd-MM-aaaa.");
                                dataInicial = sc.nextLine();

                                while (ValidarDatasInseridas(dataInicial, listaDeDatas) == false && !(dataInicial.equals("sair"))) {
                                    System.out.println("A data inserida não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa.");
                                    dataInicial = sc.nextLine();
                                }
                                if (!(dataInicial.equals("sair"))) {
                                    System.out.println("Introduza a data final na forma dd-MM-aaaa.");
                                    dataFinal = sc.nextLine();

                                    while (ValidarDatasInseridas(dataFinal, listaDeDatas) == false && !(dataFinal.equals("sair"))) {
                                        System.out.println("A data inserida não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair para voltar ao menu principal.");
                                        dataFinal = sc.nextLine();
                                    }
                                    if (!(dataInicial.equals("sair")) && !(dataFinal.equals("sair"))) {

                                        indexInicio = ProcurarPosicaoData(dataInicial, listaDeDatas);
                                        indexFinal = ProcurarPosicaoData(dataFinal, listaDeDatas);
                                        System.out.println("Para modo diario introduza 0, para modo semanal introduza 1, para modo mensal introduza 2.");
                                        comando = sc.nextLine();
                                        switch (comando) {
                                            case "0": {
                                                MostrarValoresDoDia(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                                                MostrarNovosCasosDiarios(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                                                System.out.println();
                                                break;
                                            }
                                            case "1": {
                                                ModoSemanalMostrarDados(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                                                System.out.println();
                                                break;
                                            }
                                            case "2": {
                                                int indexPrimeiroDiaMes = ProcurarPrimeiroDiaMesParaAnalizar(indexInicio, indexFinal, listaDeDatas);
                                                int indexUltimoDiaMes = ProcurarUltimoDiaMesParaAnalizar(indexInicio, indexFinal, listaDeDatas);
                                                int qtMeses = (CalcularQuantidadeDeMesesParaAvaliar(indexPrimeiroDiaMes, indexUltimoDiaMes, listaDeDatas));
                                                ModoMensalMostrarDadosMes(indexInicio, indexFinal, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                                                if (qtMeses > 1) {
                                                    ModoMensalMostrarDados(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                                                } else
                                                    System.out.println("Impossivel fazer a comparação mensal, especifique um intervalo de datas maior.");
                                                break;
                                            }
                                        }
                                        if (ficheiroSaidaValido == true){
                                            System.out.println("Quer gravar o resultado obtido? Escreva sim para gravar ou carregue ENTER para continuar.");
                                            comando1 = sc.nextLine();
                                            switch (comando1){
                                                case "sim":{
                                                    if (comando.equals("0")){
                                                        MostrarValoresDoDiaFicheiro(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio,pw);
                                                        MostrarNovosCasosDiariosFicheiro(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio,pw);
                                                        pw.println();
                                                    }
                                                    if(comando.equals("1")){
                                                        ModoSemanalMostrarDadosFicheiro(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes,pw);
                                                        pw.println();
                                                    }
                                                    if (comando.equals("2")){
                                                        int indexPrimeiroDiaMes = ProcurarPrimeiroDiaMesParaAnalizar(indexInicio, indexFinal, listaDeDatas);
                                                        int indexUltimoDiaMes = ProcurarUltimoDiaMesParaAnalizar(indexInicio, indexFinal, listaDeDatas);
                                                        int qtMeses = (CalcularQuantidadeDeMesesParaAvaliar(indexPrimeiroDiaMes, indexUltimoDiaMes, listaDeDatas));
                                                        ModoMensalMostrarDadosMesFicheiro(indexInicio, indexFinal, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, pw);
                                                        if (qtMeses > 1) {
                                                            ModoMensalMostrarDadosFicheiro(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, pw);
                                                        } else {
                                                            pw.println("Impossivel fazer a comparação mensal, especifique um intervalo de datas maior.");
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            case "2": {
                                System.out.println("Introduza a data inicial do primeiro periodo temporal na forma dd-MM-aaaa");
                                dataInicial1 = sc.nextLine();

                                while (ValidarDatasInseridas(dataInicial1,listaDeDatas)==false && !(dataInicial1.equals("sair"))){
                                    System.out.println("A data inserida não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                    dataInicial1= sc.nextLine();
                                }
                                if (!(dataInicial1.equals("sair"))) {
                                    System.out.println("Introduza a data final do primeiro periodo temporal na forma dd-MM-aaaa");
                                    dataFinal1 = sc.nextLine();

                                    while (ValidarDatasInseridas(dataFinal1,listaDeDatas)==false && !(dataFinal1.equals("sair"))){
                                        System.out.println("A data inserida não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                        dataFinal1 = sc.nextLine();
                                    }
                                    if (!(dataInicial1.equals("sair")) && !(dataFinal1.equals("sair"))) {
                                        System.out.println("Introduza a data inicial do segundo periodo temporal na forma dd-MM-aaaa");
                                        dataInicial2 = sc.nextLine();

                                        while (ValidarDatasInseridas(dataInicial2,listaDeDatas)==false && !(dataInicial2.equals("sair"))){
                                            System.out.println("A data inserida não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                            dataInicial2 = sc.nextLine();
                                        }
                                        if (!(dataInicial1.equals("sair")) && !(dataFinal1.equals("sair")) && !(dataInicial2.equals("sair"))){
                                            System.out.println("Introduza a data final do primeiro periodo temporar na forma dd-MM-aaaa");
                                            dataFinal2 = sc.nextLine();

                                            while (ValidarDatasInseridas(dataFinal2,listaDeDatas)==false && !(dataFinal2.equals("sair"))){
                                                System.out.println("A data inserida não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                                dataFinal2 = sc.nextLine();
                                            }
                                            if (!(dataInicial1.equals("sair")) && !(dataFinal1.equals("sair")) && !(dataInicial2.equals("sair")) && !(dataFinal2.equals("sair"))){

                                                indexInicio1 = ProcurarPosicaoData(dataInicial1, listaDeDatas);
                                                indexFinal1 = ProcurarPosicaoData(dataFinal1, listaDeDatas);
                                                indexInicio2 = ProcurarPosicaoData(dataInicial2, listaDeDatas);
                                                indexFinal2 = ProcurarPosicaoData(dataFinal2, listaDeDatas);
                                                double[] medias = new double[3];
                                                double[] desvioPadrao = new double[3];
                                                System.out.println();
                                                System.out.println("infetados");
                                                CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeInfetados, indexInicio1, indexInicio2);
                                                System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                System.out.println();
                                                System.out.println("hospitalizados");
                                                CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                                                System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                System.out.println();
                                                System.out.println("internados na UCI");
                                                CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeInternadosUCI, indexInicio1, indexInicio2);
                                                System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                System.out.println();
                                                System.out.println("obitos");
                                                CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeMortes, indexInicio1, indexInicio2);
                                                System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                System.out.println();

                                                if (ficheiroSaidaValido == true){

                                                    System.out.println("Quer gravar os resultados num ficheiro?");
                                                    comando = sc.nextLine();
                                                    switch (comando) {
                                                        case "sim": {
                                                            // String nomeDoFicheiroDeOutput = "";
                                                            System.out.println("Introduza o nome do ficheiro de output");
                                                            //  nomeDoFicheiroDeOutput = sc.nextLine();

                                                            pw.println("infetados");
                                                            CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados, listaDeDatas, pw);
                                                            medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                                            desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                                                            pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                            pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                            pw.println();
                                                            pw.println("hospitalizados");
                                                            CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados, listaDeDatas, pw);
                                                            medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                                            desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                                                            pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                            pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                            pw.println();
                                                            pw.println("internados na UCI");
                                                            CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI, listaDeDatas, pw);
                                                            medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                                                            desvioPadrao = CalcularDesvioPadrao(medias, listaDeInternadosUCI, indexInicio1, indexInicio2);
                                                            pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                            pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                            pw.println();
                                                            pw.println("obitos");
                                                            CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes, listaDeDatas, pw);
                                                            medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                                                            desvioPadrao = CalcularDesvioPadrao(medias, listaDeMortes, indexInicio1, indexInicio2);
                                                            pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                            pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                            pw.println();

                                                            break;
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            case "3": {
                                System.out.println("Introduza o nome do ficheiro da matriz transição.");
                                String ficheiroMatrizTransicao = sc.nextLine();
                                ficheiroEntradaTemp = new File(ficheiroMatrizTransicao);
                                while (!(ficheiroEntradaTemp.exists()) && ficheiroMatrizTransicao != "sair"){
                                    System.out.println("Ficheiro da matriz inválido, verifique o nome inserido.");
                                    ficheiroMatrizTransicao = sc.nextLine();
                                    ficheiroEntradaTemp = new File(ficheiroMatrizTransicao);

                                }

                                if (ficheiroMatrizTransicao != "sair"){
                                    System.out.println("Introduza a data que pretende prever no formato dd-MM-aaaa");
                                    dataPrevisao = sc.nextLine();
                                    double [][] matrizProbalidades = LerFicheiroMatriz(ficheiroMatrizTransicao);
                                    double [][] matrizQ = new double[4][4];
                                    for (int i = 0; i < matrizQ.length ; i++) {
                                        for (int j = 0; j < matrizQ[i].length; j++) {
                                            matrizQ[i][j] = matrizProbalidades[i][j];
                                        }
                                    }
                                    double [][] matrizI = new double[4][4];
                                    for (int i = 0; i < matrizI.length ; i++) {
                                        for (int j = 0; j < matrizI[i].length; j++) {
                                            matrizQ[i][j] = 0;
                                        }
                                    }
                                    matrizI[0][0] = 1;
                                    matrizI[1][1] = 1;
                                    matrizI[2][2] = 1;
                                    matrizI[3][3] = 1;

                                    double [][] matrizL = new double[4][4];
                                    double [][] matrizU = new double[4][4];

                                    int indexDataMaisProxima = ProcurarDataMaisProxima(dataPrevisao, listaDeDatas);

                                    if (indexDataMaisProxima == -1){
                                        System.out.println("Data de previsão inválida.");
                                    } else {

                                        Date dataMaisProxima = listaDeDatas[indexDataMaisProxima];
                                        int qtDiasDiferenca = CalcularQuantidadeDiasPrevisao(dataPrevisao, dataMaisProxima);
                                        int valorNaoInfetadosInicial = listaDeCasosNaoInfetados[indexDataMaisProxima];
                                        int valorInfetadosInicial = listaDeInfetados[indexDataMaisProxima];
                                        int valorHospitalizadosInicial = listaDeHospitalizados[indexDataMaisProxima];
                                        int valorHospitalizadosUCIInicial = listaDeInternadosUCI[indexDataMaisProxima];
                                        int valorObitosInicial = listaDeMortes[indexDataMaisProxima];

                                        double[][] matrizValores = new double[5][1];
                                        matrizValores[0][0] = valorNaoInfetadosInicial;
                                        matrizValores[1][0] = valorInfetadosInicial;
                                        matrizValores[2][0] = valorHospitalizadosInicial;
                                        matrizValores[3][0] = valorHospitalizadosUCIInicial;
                                        matrizValores[4][0] = valorObitosInicial;

                                        double[][] matrizProbalidadesTemporaria = matrizProbalidades;
                                        for (int i = 1; i < qtDiasDiferenca; i++) {
                                            matrizProbalidades = MultiplicarMatrizes(matrizProbalidades, matrizProbalidadesTemporaria);
                                        }
                                        double[][] matrizResultados = MultiplicarMatrizes(matrizProbalidades, matrizValores);

                                        double valorNaoInfetadosPrevisto = matrizResultados[0][0];
                                        double valorInfetadosPrevisto = matrizResultados[1][0];
                                        double valorHospitalizadosPrevisto = matrizResultados[2][0];
                                        double valorHospitalizadosUCIPrevisto = matrizResultados[3][0];
                                        double valorObitosPrevisto = matrizResultados[4][0];

                                        System.out.println("O numero previsto de não infetados para o dia " + dataPrevisao + " é " + valorNaoInfetadosPrevisto + ".");
                                        System.out.println("O numero previsto de infetados para o dia " + dataPrevisao + " é " + valorInfetadosPrevisto + ".");
                                        System.out.println("O numero previsto de hospitalizados para o dia " + dataPrevisao + " é " + valorHospitalizadosPrevisto + ".");
                                        System.out.println("O numero previsto de hospitalizados UCI para o dia " + dataPrevisao + " é " + valorHospitalizadosUCIPrevisto + ".");
                                        System.out.println("O numero previsto de óbitos para o dia " + dataPrevisao + " é " + valorObitosPrevisto + ".");








                                        if (ficheiroSaidaValido == true){
                                            System.out.println("Quer gravar os resultados num ficheiro?");
                                            comando = sc.nextLine();
                                            switch (comando) {
                                                case "sim": {
                                                    // String nomeDoFicheiroDeOutput = "";
                                                    System.out.println("Introduza o nome do ficheiro de output");
                                                    //  nomeDoFicheiroDeOutput = sc.nextLine();
                                                    pw.println("O numero previsto de não infetados para o dia " + dataPrevisao + " é " + valorNaoInfetadosPrevisto + ".");
                                                    pw.println("O numero previsto de infetados para o dia " + dataPrevisao + " é " + valorInfetadosPrevisto + ".");
                                                    pw.println("O numero previsto de hospitalizados para o dia " + dataPrevisao + " é " + valorHospitalizadosPrevisto + ".");
                                                    pw.println("O numero previsto de hospitalizados UCI para o dia " + dataPrevisao + " é " + valorHospitalizadosUCIPrevisto + ".");
                                                    pw.println("O numero previsto de óbitos para o dia " + dataPrevisao + " é " + valorObitosPrevisto + ".");

                                                }

                                            }

                                        }
                                        break;
                                    }

                                }

                            }
                            case "4": {
                                System.out.println("Introduza o nome de um novo ficheiro de entrada valido");
                                String ficheiroEntradaBackup = ficheiroEntrada;
                                ficheiroEntrada = sc.nextLine();
                                ficheiroEntradaTemp = new File(ficheiroEntrada);
                                while (!(ficheiroEntradaTemp.exists()) && !(ficheiroEntrada.equals("sair"))){
                                    System.out.println("O ficheiro introduzido não era valido, introduza um ficheiro valido, se quiser continuar a usar o ficheiro anterior escreva sair, ou escreva o nome deste");
                                    ficheiroEntrada = sc.nextLine();
                                    ficheiroEntradaTemp = new File(ficheiroEntrada);
                                }
                                if (ficheiroEntrada.equals("sair")){
                                    ficheiroEntrada = ficheiroEntradaBackup;
                                }
                                break;
                            }
                        }
                    }
                    pw.close();
                }else {
                    System.out.println("Saida por escolha do utilizador");
                }
            }
        }
        /***************************************************************
         *                                                              *
         *                     FIM MODO INTERATIVO                      *
         *                                                              *
         ****************************************************************/



    }


    public static Date StringParaData(String data) throws ParseException {
        Date date;
        String [] valoresData = data.split("-");
        if (valoresData[0].length() != 4){
            date = (formato3.parse(data));
        } else {
            date = (formato.parse(data));
        }

        return date;
    }
    public static int[] PreencherValoresDosDadosPrimeiraFase(String ficheiroEntrada, int tipoDeDado, int[] listaDeDados) throws FileNotFoundException {

        int count = 0;
        String splitBy = ",";
        Scanner fin = new Scanner(new File(ficheiroEntrada));
        fin.nextLine();
        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] dados = line.split(splitBy);
            listaDeDados[count] = Integer.parseInt(dados[tipoDeDado]);
            count++;
        }
        fin.close();

        return listaDeDados;
    }

    public static Date[] PreencherListaDeDatas(String ficheiroEntrada, Date[] listaDeDatas) throws FileNotFoundException, ParseException {
        int count = 0;
        String splitBy = ",";
        Scanner fin = new Scanner(new File(ficheiroEntrada));
        fin.nextLine();
        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] dados = line.split(splitBy);

            if (dados[0].split("-")[0].length() != 4) {
                listaDeDatas[count] = formato3.parse(dados[0]);

            } else {
                listaDeDatas[count] = formato.parse(dados[0]);
            }
            count++;

        }

        fin.close();
        return listaDeDatas;
    }

    public static int ProcurarPosicaoData (String data, Date [] listaDeDatas) throws ParseException {
        int posiçãoDaData = -1;
        String [] valoresData = data.split("-");
        if (valoresData[0].length() != 4){
            for (int i = 0; i < listaDeDatas.length; i++) {
                if (listaDeDatas[i].equals(formato3.parse(data))){
                    posiçãoDaData = i;
                }
            }
        } else {
            for (int i = 0; i < listaDeDatas.length; i++) {
                if (listaDeDatas[i].equals(formato.parse(data))){
                    posiçãoDaData = i;
                }
            }
        }

        return posiçãoDaData;
    }

    public static int ProcurarValorDoDia (String data, int [] listaDeValores, Date [] listaDeDatas) throws ParseException {
        int valor;

        int posiçãoDaData = ProcurarPosicaoData(data, listaDeDatas);

        if (posiçãoDaData == -1) {
            valor = -1;
        }
        else valor = listaDeValores[posiçãoDaData];

        return valor;
    }


    public static int ContarLinhasDoFicheiro(String ficheiroEntrada) throws FileNotFoundException {

        Scanner fin = new Scanner(new File(ficheiroEntrada));
        int counter = 0;

        while (fin.hasNextLine()) {
            counter++;
            fin.nextLine();
        }
        fin.close();
        return counter;
    }

    //ANALISE SEMANAL

    public static void ModoSemanalMostrarDados(int indexFinal,int indexInicio,String mensagemSegundaFeiraReferencia,Date[] listaDeDatas,int[] listaDeInfetados,int[] listaDeHospitalizados,int[] listaDeInternadosUCI,int[] listaDeMortes) {
        if (indexFinal - indexInicio >= 7) {
            int primeiraSegundaFeiraIndex = ProcurarPrimeiraSegundaFeira(indexInicio, indexFinal, mensagemSegundaFeiraReferencia, listaDeDatas);
            if (primeiraSegundaFeiraIndex == -1) {
                System.out.println("A partir da primeira segunda-feira não existem dias suficientes para a analise semanal");
            } else {
                int[] numeroTotalInfetados = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeInfetados);
                int[] numeroTotalDeHospitalizados = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeHospitalizados);
                int[] numeroTotalDeInternadosNaUCI = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeInternadosUCI);
                int[] numeroTotalDeObitos = SomarDadosDaSemanaObitos(primeiraSegundaFeiraIndex, indexFinal, listaDeMortes);

                for (int i = 0; i < (indexFinal - primeiraSegundaFeiraIndex) / 7; i++) {
                    System.out.print("Na semana de " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * i)]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * i) - 1]))));
                    System.out.println(" houve: " + numeroTotalInfetados[i] + " infetados, " + numeroTotalDeHospitalizados[i] + " hospitalizados, " + numeroTotalDeInternadosNaUCI[i] + " internados na UCI, " + numeroTotalDeObitos[i] + " obitos.");
                }
                System.out.println();
                if ((indexFinal - primeiraSegundaFeiraIndex)/ 7 > 1) {
                    for (int i = 0; i < ((indexFinal - primeiraSegundaFeiraIndex) / 7) - 1; i++) {
                        System.out.print("Entre as semanas de " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * i)]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * i) - 1]))));
                        System.out.println(" e " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * (i + 1))]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * (i + 1)) - 1]))));
                        System.out.println("houve " + (numeroTotalInfetados[i + 1] - numeroTotalInfetados[i]) + " infetados, " + (numeroTotalDeHospitalizados[i + 1] - numeroTotalInfetados[i]) + " hospitalizados, " + (numeroTotalDeInternadosNaUCI[i + 1] - numeroTotalDeInternadosNaUCI[i]) + " internados na UCI, " + (numeroTotalDeObitos[i + 1] - numeroTotalDeObitos[i]) + " obitos");
                    }
                }else {
                    System.out.println("Como só foi selecionada uma semana não é possivel fazer comparações");
                }

            }

        }
        if (indexFinal - indexInicio < 7) {
            System.out.println("O periodo de tempo escolhido é muito curto para analise semanal.");
        }
    }

    public static int ProcurarPrimeiraSegundaFeira (int indexInicial,int indexFinal,String mensagemSegundaFeiraReferencia, Date[] listaDeDatas){
        while (!(mensagemSegundaFeiraReferencia.equals(formato2.format(listaDeDatas[indexInicial]))) && indexInicial <= indexFinal ){
            indexInicial++;
        }
        if (indexFinal - indexInicial >= 6){
            return indexInicial;
        }
        return -1;
    }

    public static int[] SomarDadosDaSemana (int primeiraSegundaFeiraIndex,int indexFinal, int[] dados){
        int[] somas = new int[((indexFinal - primeiraSegundaFeiraIndex)/ 7)];
        for (int i = 0; i < somas.length; i++) {
            int soma = 0;
            soma = dados[primeiraSegundaFeiraIndex + 6] - dados[primeiraSegundaFeiraIndex];
            somas[i] = soma;
            primeiraSegundaFeiraIndex = primeiraSegundaFeiraIndex + 7;
        }

        return somas;

    }

    public static int[] SomarDadosDaSemanaObitos(int primeiraSegundaFeiraIndex, int indexFinal, int[] dados){

        int[] somas = new int[((indexFinal - primeiraSegundaFeiraIndex)/ 7)];
        int numeroDeSemanas = ((indexFinal - primeiraSegundaFeiraIndex )/7);
        for (int i = 0; i < numeroDeSemanas; i++) {
            int soma = 0;
            for (int j = 0; j < 7; j++) {
                soma = soma + dados[primeiraSegundaFeiraIndex];
                primeiraSegundaFeiraIndex++;
            }
            somas[i] = soma;
        }
        return somas;
    }

    // FIM DA ANALISE SEMANAL

    //ANALISE DIARIA

    public static void MostrarValoresDoDia (int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas,int indexFinal, int indexInicio) throws ParseException {

        for (int i = indexInicio; i < indexFinal + 1; i++) {
            System.out.println("No dia " + formato.format(listaDeDatas[i])  + " foram registados " + listaDeInfetados[i] + " infetados, " + listaDeHospitalizados[i] + " hospitalizados, " + listaDeInternadosUCI[i] + " internados em UCI e " + listaDeMortes[i] + " óbitos.");
        }
    }

    public static void MostrarNovosCasosDiarios(int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas, int indexFinal, int indexInicio){
        for (int i = indexInicio; i < indexFinal; i++) {
            System.out.println("Entre os dias "+formato.format(listaDeDatas[i])+" e "+formato.format(listaDeDatas[i + 1])+" houve ");
            System.out.print((listaDeInfetados[i + 1]-listaDeInfetados[i])+ " infetados, " + (listaDeHospitalizados[i + 1]-listaDeHospitalizados[i])+ " hospitalizados, ");
            System.out.println((listaDeInternadosUCI[i + 1]- listaDeInternadosUCI[i])+" internados na UCI, "+ (listaDeMortes[i+1]-listaDeMortes[i])+" obitos.");
        }
    }
    //FIM DA ANALISE DIARIA

    // ANALISE MENSAL
    public static int ProcurarPrimeiroDiaMesParaAnalizar(int indexInicial, int indexFinal, Date[] listaDeDatas) {
        String[] data = formato.format(listaDeDatas[indexInicial]).split("-");
        while (!data[2].equals("01") && indexInicial < indexFinal) {
            indexInicial++;
            data = formato.format(listaDeDatas[indexInicial]).split("-");
        }


        return indexInicial;
    }

    public static int ProcurarUltimoDiaMesParaAnalizar(int indexInicial, int indexFinal, Date[] listaDeDatas) {
        DateTimeFormatter formato4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data2 = formato.format(listaDeDatas[indexFinal]);
        LocalDate ultimoDiaMes = LocalDate.parse(data2, DateTimeFormatter.ofPattern("yyyy-M-d"));
        ultimoDiaMes = ultimoDiaMes.withDayOfMonth(ultimoDiaMes.getMonth().length(ultimoDiaMes.isLeapYear()));
        String ultimoDiaMes1 = ultimoDiaMes.format(formato4);
        String[] data = formato.format(listaDeDatas[indexFinal]).split("-");
        String[] data1 = formato.format(listaDeDatas[indexFinal]).split("-");
        if (!(ultimoDiaMes1.equals(data2))){
            while (data1[1].equals(data[1]) && indexFinal > indexInicial) {
                indexFinal--;
                data1 = formato.format(listaDeDatas[indexFinal]).split("-");
            }
        }

        return indexFinal;
    }

    public static int CalcularQuantidadeDeMesesParaAvaliar(int indexPrimeiroDiaMes, int indexFinal, Date[] listaDeDatas) {
        String[] data = formato.format(listaDeDatas[indexPrimeiroDiaMes]).split("-");
        String mes = data[1];
        int qtMeses = 1;
        while (indexPrimeiroDiaMes < indexFinal) {
            indexPrimeiroDiaMes++;
            data = formato.format(listaDeDatas[indexPrimeiroDiaMes]).split("-");
            if (!data[1].equals(mes)) {
                qtMeses++;
            }
            mes = data[1];
        }
        return qtMeses;
    }

    public static int SomarDadosMes(int indexInicial, int indexFinal, int[] dados, Date[] listaDeDatas) {
        int indexInicialOriginal = indexInicial;
        String[] data = formato.format(listaDeDatas[indexInicial]).split("-");
        String mes = data[1];
        while (data[1].equals(mes) && indexInicial < indexFinal) {
            indexInicial++;
            data = formato.format(listaDeDatas[indexInicial]).split("-");
        }
        int variacao = dados[indexInicial] - dados[indexInicialOriginal];
        return variacao;
    }

    public static int ProcurarPrimeiroDiaMes(String dia, Date[] listaDeDatas) throws ParseException {
        int indexInicio = ProcurarPosicaoData(dia, listaDeDatas);
        String[] data = formato.format(listaDeDatas[indexInicio]).split("-");
        String dia1 = data[2];

        while (!(dia1.equals("01"))){
            indexInicio--;
            data = formato.format(listaDeDatas[indexInicio]).split("-");
            dia1 = data[2];
        }
        return indexInicio;

    }
    public static int ProcurarUltimoDiaMes(String dia, Date[] listaDeDatas) throws ParseException {
        DateTimeFormatter formato4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int indexDia = ProcurarPosicaoData(dia, listaDeDatas);
        String data2 = formato.format(listaDeDatas[indexDia]);
        LocalDate ultimoDiaMes = LocalDate.parse(data2, DateTimeFormatter.ofPattern("yyyy-M-d"));
        ultimoDiaMes = ultimoDiaMes.withDayOfMonth(ultimoDiaMes.getMonth().length(ultimoDiaMes.isLeapYear()));
        String ultimoDiaMes1 = ultimoDiaMes.format(formato4);
        int indexUltimoDia = ProcurarPosicaoData(ultimoDiaMes1,listaDeDatas);

        return indexUltimoDia;
    }
    public static void ModoMensalMostrarDadosMes(int indexInicio, int indexFinal, Date[] listaDeDatas, int[] listaDeInfetados, int[] listaDeHospitalizados, int[] listaDeInternadosUCI, int[] listaDeMortes) throws ParseException {
        String dataInicial = formato.format(listaDeDatas[indexInicio]);
        String dataFinal = formato.format(listaDeDatas[indexFinal]);
        indexInicio = ProcurarPrimeiroDiaMes(dataInicial, listaDeDatas);
        indexFinal = ProcurarUltimoDiaMes(dataFinal, listaDeDatas);
        int qtMeses = CalcularQuantidadeDeMesesParaAvaliar(indexInicio, indexFinal, listaDeDatas);
        for (int i = 0; i < qtMeses; i++) {
            String[] data = formato.format(listaDeDatas[indexInicio]).split("-");
            String data1 = formato.format(listaDeDatas[indexInicio]);
            int indexInicialMes = indexInicio;
            int indexFinalMes = ProcurarUltimoDiaMes(data1, listaDeDatas);
            String mes = data[1];
            String ano = data[0];
            int diferencaInfetados = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeInfetados, listaDeDatas);
            int diferencaHospitalizados = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeHospitalizados, listaDeDatas);
            int diferencaUCI = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeInternadosUCI, listaDeDatas);
            int diferencaObitos = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeMortes, listaDeDatas);
            while (data[1].equals(mes) && indexInicio < indexFinal) {
                indexInicio++;
                data = formato.format(listaDeDatas[indexInicio]).split("-");
            }
            System.out.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaInfetados + " infetados.");
            System.out.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaHospitalizados + " hospitalizados.");;
            System.out.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaUCI + " hospitalizados em UCI.");
            System.out.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaObitos + " obitos.");
            System.out.println();

        }

    }
    public static void ModoMensalMostrarDados(int indexInicio, int indexFinal, int qtMeses, Date[] listaDeDatas, int[] listaDeInfetados, int[] listaDeHospitalizados, int[] listaDeInternadosUCI, int[] listaDeMortes) {
        int[] listaDeDados = new int[4];
        int[] listaDeDados1 = new int[4];
        for (int i = 1; i < qtMeses; i++) {
            String[] data = formato.format(listaDeDatas[indexInicio]).split("-");
            String mes = data[1];
            String ano = data[0];
            listaDeDados[0] = SomarDadosMes(indexInicio, indexFinal, listaDeInfetados, listaDeDatas);
            listaDeDados[1] = SomarDadosMes(indexInicio, indexFinal, listaDeHospitalizados, listaDeDatas);
            listaDeDados[2] = SomarDadosMes(indexInicio, indexFinal, listaDeInternadosUCI, listaDeDatas);
            listaDeDados[3] = SomarDadosMes(indexInicio, indexFinal, listaDeMortes, listaDeDatas);
            while (data[1].equals(mes) && indexInicio <= indexFinal) {
                indexInicio++;
                data = formato.format(listaDeDatas[indexInicio]).split("-");
            }

            String[] data2 = formato.format(listaDeDatas[indexInicio]).split("-");
            String mes2 = data2[1];
            String ano2 = data2[0];
            listaDeDados1[0] = SomarDadosMes(indexInicio, indexFinal, listaDeInfetados, listaDeDatas);
            listaDeDados1[1] = SomarDadosMes(indexInicio, indexFinal, listaDeHospitalizados, listaDeDatas);
            listaDeDados1[2] = SomarDadosMes(indexInicio, indexFinal, listaDeInternadosUCI, listaDeDatas);
            listaDeDados1[3] = SomarDadosMes(indexInicio, indexFinal, listaDeMortes, listaDeDatas);

            int diferencaInfetados = listaDeDados1[0] - listaDeDados[0];
            int diferencaHospitalizados = listaDeDados1[1] - listaDeDados[1];
            int diferencaUCI = listaDeDados1[2] - listaDeDados[2];
            int diferencaObitos = listaDeDados1[3] - listaDeDados[3];


            System.out.println("Entre os meses " + mes + "-" + ano + " e " + mes2 + "-" + ano2 +  " houve uma alteração de " + diferencaInfetados + " infetados");
            System.out.println("Entre os meses " + mes + "-" + ano + " e " + mes2 + "-" + ano2 + " houve uma alteração de " + diferencaHospitalizados + " hospitalizados");
            System.out.println("Entre os meses " + mes + "-" + ano +  " e " + mes2 + "-" + ano2 + " houve uma alteração de " + diferencaUCI + " hospitalizados em UCI");
            System.out.println("Entre os meses " + mes + "-" + ano +  " e " + mes2 + "-" + ano2 + " houve uma alteração de " + diferencaObitos + " óbitos");
            System.out.println();

        }
    }



    // FIM ANALISE MENSAL POR ACABAR

    public static void CompararPeriodosDeTempo(int indexInicio1,int indexFinal1,int indexInicio2,int indexFinal2,int[] arrayDeDados){

        while (indexFinal1 > indexInicio1 && indexFinal2 > indexInicio2){

            System.out.print(arrayDeDados[indexInicio1]+ "     ");
            System.out.print(arrayDeDados[indexInicio2]+ "     ");
            System.out.println((arrayDeDados[indexInicio2]) - (arrayDeDados[indexInicio1]));
            indexInicio1++;
            indexInicio2++;

        }

    }
    public static double[] MostrarMedias (int indexInicio1,int indexFinal1,int indexInicio2,int indexFinal2,int[] arrayDeDados){

        double media1 = 0;
        double media2 = 0;
        double media3 = 0;
        double count = 0;

        while (indexFinal1 > indexInicio1 && indexFinal2 > indexInicio2){

            media1 = media1 + arrayDeDados[indexInicio1];
            media2 = media2 + arrayDeDados[indexInicio2];
            int diferençaDeValores = arrayDeDados[indexInicio2] - arrayDeDados[indexInicio1];
            if (diferençaDeValores < 0){
                diferençaDeValores = diferençaDeValores * - 1;
            }
            media3 = media3 + diferençaDeValores;
            indexInicio1++;
            indexInicio2++;
            count++;
        }

        media1 = media1 / count;
        media2 = media2 / count;
        media3 = media3 / count;


        double[] mediasECounter = {media1,media2,media3,count};

        System.out.println();

        return mediasECounter;

    }

    public static double[] CalcularDesvioPadrao(double[] medias, int[] dados, int indexInicio1, int indexInicio2){

        double[] variacao=CalcularVariacao(medias,dados,indexInicio1,indexInicio2);
        double[] desvioPadrao = new double[3];

        for (int i = 0; i < desvioPadrao.length; i++) {
            desvioPadrao[i] = Math.sqrt(variacao[i]);
        }

        return desvioPadrao;

    }

    public static double[] CalcularVariacao(double[] medias, int[] dados, int indexInicio1, int indexInicio2){


        double[] variacao = new double[3];
        double[] desvio = new double[(int)medias[3]];
        double somaVariacao = 0;
        int index1Aux = indexInicio1;
        int index2Aux = indexInicio2;


        for (int i = 0; i < desvio.length ; i++) {
            desvio[i] = medias[0] - dados[indexInicio1];
            indexInicio1++;
        }

        for (int i = 0; i < desvio.length; i++) {
            somaVariacao = (desvio[i] * desvio[i]) + somaVariacao;
        }

        variacao[0] = somaVariacao/desvio.length;
        somaVariacao = 0;

        for (int i = 0; i < desvio.length ; i++) {
            desvio[i] = medias[1] - dados[indexInicio2];
            indexInicio2++;
        }

        for (int i = 0; i < desvio.length; i++) {
            somaVariacao = (desvio[i] * desvio[i]) + somaVariacao;
        }

        variacao[1] = somaVariacao/ desvio.length;
        somaVariacao = 0;


        for (int i = 0; i < desvio.length ; i++) {
            desvio[i] = medias[2] - (dados[index2Aux] - dados[index1Aux]);
            index2Aux++;
            index1Aux++;
        }



        for (int i = 0; i < desvio.length; i++) {
            somaVariacao = (desvio[i] * desvio[i]) + somaVariacao;
        }

        variacao[2] = somaVariacao/ desvio.length;

        return variacao;
    }

    public static void CompararPeriodosDeTempoParaFicheiro (int indexInicio1,int indexFinal1,int indexInicio2,int indexFinal2,int[] arrayDeDados,Date[] listaDeDatas,PrintWriter pw) throws FileNotFoundException {


        while (indexFinal1 > indexInicio1 && indexFinal2 > indexInicio2) {

            pw.print(formato.format(listaDeDatas[indexInicio1])+ ": " + arrayDeDados[indexInicio1] + "     ");
            pw.print(formato.format(listaDeDatas[indexInicio2])+ ": " + arrayDeDados[indexInicio2] + "     ");
            pw.println(("Diferença entre dias"+ ": " + ((arrayDeDados[indexInicio2]) - (arrayDeDados[indexInicio1]))));
            indexInicio1++;
            indexInicio2++;
        }
    }

    //FIM 2.2


    public static boolean ValidarDatasInseridas(String data, Date[] listaDeDatas) throws ParseException {

        boolean resultado = false;
        for (int i = 0; i < listaDeDatas.length; i++) {
            if (data.equals(formato3.format(listaDeDatas[i]))){
                resultado = true;
            }
        }
        return resultado;
    }

    public static void MostrarValoresDoDiaFicheiro (int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas,int indexFinal, int indexInicio,PrintWriter pw) throws ParseException {

        for (int i = indexInicio; i < indexFinal + 1; i++) {
            pw.println("No dia " + formato.format(listaDeDatas[i])  + " foram registados " + listaDeInfetados[i] + " infetados, " + listaDeHospitalizados[i] + " hospitalizados, " + listaDeInternadosUCI[i] + " internados em UCI e " + listaDeMortes[i] + " óbitos.");
        }
    }

    public static void MostrarNovosCasosDiariosFicheiro(int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas, int indexFinal, int indexInicio,PrintWriter pw){
        for (int i = indexInicio; i < indexFinal; i++) {
            pw.println("Entre os dias "+formato.format(listaDeDatas[i])+" e "+formato.format(listaDeDatas[i + 1])+" houve ");
            pw.print((listaDeInfetados[i + 1]-listaDeInfetados[i])+ " infetados, " + (listaDeHospitalizados[i + 1]-listaDeHospitalizados[i])+ " hospitalizados, ");
            pw.println((listaDeInternadosUCI[i + 1]- listaDeInternadosUCI[i])+" internados na UCI, "+ (listaDeMortes[i+1]-listaDeMortes[i])+" obitos.");
        }
    }

    public static void ModoSemanalMostrarDadosFicheiro(int indexFinal,int indexInicio,String mensagemSegundaFeiraReferencia,Date[] listaDeDatas,int[] listaDeInfetados,int[] listaDeHospitalizados,int[] listaDeInternadosUCI,int[] listaDeMortes,PrintWriter pw) {
        if (indexFinal - indexInicio >= 7) {
            int primeiraSegundaFeiraIndex = ProcurarPrimeiraSegundaFeira(indexInicio, indexFinal, mensagemSegundaFeiraReferencia, listaDeDatas);
            if (primeiraSegundaFeiraIndex == -1) {
                pw.println("A partir da primeira segunda-feira não existem dias suficientes para a analise semanal");
            } else {
                int[] numeroTotalInfetados = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeInfetados);
                int[] numeroTotalDeHospitalizados = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeHospitalizados);
                int[] numeroTotalDeInternadosNaUCI = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeInternadosUCI);
                int[] numeroTotalDeObitos = SomarDadosDaSemanaObitos(primeiraSegundaFeiraIndex, indexFinal, listaDeMortes);

                for (int i = 0; i < (indexFinal - primeiraSegundaFeiraIndex) / 7; i++) {
                    pw.print("Na semana de " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * i)]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * i) - 1]))));
                    pw.println(" houve: " + numeroTotalInfetados[i] + " infetados, " + numeroTotalDeHospitalizados[i] + " hospitalizados, " + numeroTotalDeInternadosNaUCI[i] + " internados na UCI, " + numeroTotalDeObitos[i] + " obitos.");
                }
                System.out.println();
                if ((indexFinal - primeiraSegundaFeiraIndex)/ 7 > 1) {
                    for (int i = 0; i < ((indexFinal - primeiraSegundaFeiraIndex) / 7) - 1; i++) {
                        pw.print("Entre as semanas de " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * i)]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * i) - 1]))));
                        pw.println(" e " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * (i + 1))]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * (i + 1)) - 1]))));
                        pw.println("houve " + (numeroTotalInfetados[i + 1] - numeroTotalInfetados[i]) + " infetados, " + (numeroTotalDeHospitalizados[i + 1] - numeroTotalInfetados[i]) + " hospitalizados, " + (numeroTotalDeInternadosNaUCI[i + 1] - numeroTotalDeInternadosNaUCI[i]) + " internados na UCI, " + (numeroTotalDeObitos[i + 1] - numeroTotalDeObitos[i]) + " obitos");
                    }
                }else {
                    pw.println("Como só foi selecionada uma semana não é possivel fazer comparações");
                }

            }

        }
        if (indexFinal - indexInicio < 7) {
            pw.println("O periodo de tempo escolhido é muito curto para analise semanal.");
        }
    }

    public static void ModoMensalMostrarDadosFicheiro(int indexInicio, int indexFinal, int qtMeses, Date[] listaDeDatas, int[] listaDeInfetados, int[] listaDeHospitalizados, int[] listaDeInternadosUCI, int[] listaDeMortes,PrintWriter pw) {
        int[] listaDeDados = new int[4];
        int[] listaDeDados1 = new int[4];
        for (int i = 1; i < qtMeses; i++) {
            String[] data = formato.format(listaDeDatas[indexInicio]).split("-");
            String mes = data[1];
            String ano = data[0];
            listaDeDados[0] = SomarDadosMes(indexInicio, indexFinal, listaDeInfetados, listaDeDatas);
            listaDeDados[1] = SomarDadosMes(indexInicio, indexFinal, listaDeHospitalizados, listaDeDatas);
            listaDeDados[2] = SomarDadosMes(indexInicio, indexFinal, listaDeInternadosUCI, listaDeDatas);
            listaDeDados[3] = SomarDadosMes(indexInicio, indexFinal, listaDeMortes, listaDeDatas);
            while (data[1].equals(mes) && indexInicio <= indexFinal) {
                indexInicio++;
                data = formato.format(listaDeDatas[indexInicio]).split("-");
            }

            String[] data2 = formato.format(listaDeDatas[indexInicio]).split("-");
            String mes2 = data2[1];
            String ano2 = data2[0];
            listaDeDados1[0] = SomarDadosMes(indexInicio, indexFinal, listaDeInfetados, listaDeDatas);
            listaDeDados1[1] = SomarDadosMes(indexInicio, indexFinal, listaDeHospitalizados, listaDeDatas);
            listaDeDados1[2] = SomarDadosMes(indexInicio, indexFinal, listaDeInternadosUCI, listaDeDatas);
            listaDeDados1[3] = SomarDadosMes(indexInicio, indexFinal, listaDeMortes, listaDeDatas);

            int diferencaInfetados = listaDeDados1[0] - listaDeDados[0];
            int diferencaHospitalizados = listaDeDados1[1] - listaDeDados[1];
            int diferencaUCI = listaDeDados1[2] - listaDeDados[2];
            int diferencaObitos = listaDeDados1[3] - listaDeDados[3];


            pw.println("Entre os meses " + mes + "-" + ano + " e " + mes2 + "-" + ano2 + " houve uma alteração de " + diferencaInfetados + " infetados");
            pw.println("Entre os meses " + mes + "-" + ano + " e " + mes2 + "-" + ano2 + " houve uma alteração de " + diferencaHospitalizados + " hospitalizados");
            pw.println("Entre os meses " + mes + "-" + ano + " e " + mes2 + "-" + ano2 + " houve uma alteração de " + diferencaUCI + " hospitalizados em UCI");
            pw.println("Entre os meses " + mes + "-" + ano + " e " + mes2 + "-" + ano2 + " houve uma alteração de " + diferencaObitos + " óbitos");
            pw.println();

        }
    }

    public static void ModoMensalMostrarDadosMesFicheiro(int indexInicio, int indexFinal, Date[] listaDeDatas, int[] listaDeInfetados, int[] listaDeHospitalizados, int[] listaDeInternadosUCI, int[] listaDeMortes, PrintWriter pw) throws ParseException {
        String dataInicial = formato.format(listaDeDatas[indexInicio]);
        String dataFinal = formato.format(listaDeDatas[indexFinal]);
        indexInicio = ProcurarPrimeiroDiaMes(dataInicial, listaDeDatas);
        indexFinal = ProcurarUltimoDiaMes(dataFinal, listaDeDatas);
        int qtMeses = CalcularQuantidadeDeMesesParaAvaliar(indexInicio, indexFinal, listaDeDatas);
        for (int i = 0; i < qtMeses; i++) {
            String[] data = formato.format(listaDeDatas[indexInicio]).split("-");
            String data1 = formato.format(listaDeDatas[indexInicio]);
            int indexInicialMes = indexInicio;
            int indexFinalMes = ProcurarUltimoDiaMes(data1, listaDeDatas);
            String mes = data[1];
            String ano = data[0];
            int diferencaInfetados = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeInfetados, listaDeDatas);
            int diferencaHospitalizados = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeHospitalizados, listaDeDatas);
            int diferencaUCI = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeInternadosUCI, listaDeDatas);
            int diferencaObitos = SomarDadosMes(indexInicialMes, indexFinalMes, listaDeMortes, listaDeDatas);
            while (data[1].equals(mes) && indexInicio < indexFinal) {
                indexInicio++;
                data = formato.format(listaDeDatas[indexInicio]).split("-");
            }
            pw.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaInfetados + " infetados.");
            pw.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaHospitalizados + " hospitalizados.");;
            pw.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaUCI + " hospitalizados em UCI.");
            pw.println("No mes " + mes + "-" + ano + " houve uma alteração de " + diferencaObitos + " obitos.");
            pw.println();

        }

    }
    //INICIO PREVISAO
    public static double[][] MultiplicarMatrizes(double[][] primeiraMatriz, double[][] segundaMatriz) {
        int qtLinhas1 = primeiraMatriz.length;
        int qtColunas1 = primeiraMatriz[0].length;
        int qtColunas2 = segundaMatriz[0].length;

        double[][] matrizResultante = new double[qtLinhas1][qtColunas2];

        for(int i = 0; i < qtLinhas1; i++) {
            for (int j = 0; j < qtColunas2; j++) {
                for (int k = 0; k < qtColunas1; k++) {
                    matrizResultante[i][j] += primeiraMatriz[i][k] * segundaMatriz[k][j];
                }
            }
        }
        return matrizResultante;
    }

    public static double[][] ElevarMatriz(double[][] matriz, int n){
        for (int i = 0; i < n; i++) {
            MultiplicarMatrizes(matriz, matriz);
        }
        return matriz;
    }

    public static void MostrarValoresMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.println(i + " " + j + ": " + matriz[i][j]);
            }
        }
    }

    public static double[][] LerFicheiroMatriz(String ficheiroMatriz) throws FileNotFoundException {
        Scanner fin = new Scanner(new File(ficheiroMatriz));
        Scanner fin1 = new Scanner(new File(ficheiroMatriz));
        int qtLinhas = 0;
        int qtLinhasValidas = 0;
        String ultimaLinha = "";
        while (fin.hasNextLine()) {
            String linha = fin.nextLine();
            if (linha != ""){
                ultimaLinha = linha;
                qtLinhasValidas++;
            }
            qtLinhas++;
        }
        fin.close();

        int tamanhoMatriz = (int) Math.sqrt(qtLinhasValidas);
        double[][] matrizProbalidades = new double[tamanhoMatriz][tamanhoMatriz];

        while (fin1.hasNextLine()){
            String linha = fin1.nextLine();
            if (linha != ""){
                String[] valoresLinha = linha.split("=");
                int i = Integer.parseInt(valoresLinha[0].split("")[1]) - 1;
                int j = Integer.parseInt(valoresLinha[0].split("")[2]) - 1;
                double valor = Double.parseDouble(valoresLinha[1]);
                matrizProbalidades[i][j] = valor;
            }
        }

        fin1.close();

        return matrizProbalidades;
    }

    public static int ProcurarDataMaisProxima(String dataPrevisao, Date[] listaDeDatas) throws ParseException {
        Date date = StringParaData(dataPrevisao);
        if (date.before(listaDeDatas[0])){
            return -1;
        }
        int posicaoData = ProcurarPosicaoData(dataPrevisao, listaDeDatas) - 1;
        if (posicaoData == -2){
            posicaoData = listaDeDatas.length - 1;
        }

        return posicaoData;
    }

    public static int CalcularQuantidadeDiasPrevisao(String dataPrevisao, Date dataMaisProxima) throws ParseException {

        long qtMilisegundos = dataMaisProxima.getTime() - StringParaData(dataPrevisao).getTime();
        int qtDias = (int)(qtMilisegundos / (1000*60*60*24)) * -1;

        return qtDias;
    }

    public static Double[][] SubtrairMatrizes (Double[][] primeiraMatriz, Double[][] segundaMatriz){
        Double [][] matrizResultado = new Double[primeiraMatriz.length][segundaMatriz[0].length];

        for (int i = 0; i < primeiraMatriz.length; i++) {
            for (int j = 0; j < segundaMatriz.length; j++) {
                matrizResultado[i][j] = primeiraMatriz[i][j] - segundaMatriz[i][j];
            }

        }

        return matrizResultado;
    }
}