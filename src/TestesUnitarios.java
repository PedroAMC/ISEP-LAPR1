import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TestesUnitarios {
    static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    static final SimpleDateFormat formato3 = new SimpleDateFormat("dd-MM-yyyy");
    static final DateFormat formato2 = new SimpleDateFormat("EEEE");
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        String segundaFeiraString = "2022-01-10";
        Date segundaFeiraData = formato.parse(segundaFeiraString);
        String mensagemSegundaFeiraReferencia = formato2.format(segundaFeiraData);
        String dataInicial = "01-04-2020";
        String dataFinal = "31-05-2020";
        String dataPrevisao = "18-04-2020";
        String dataInicial1 = "01-04-2020";
        String dataFinal1 = "20-04-2020";
        String dataInicial2 = "23-04-2020";
        String dataFinal2 = "31-05-2020";
        String ficheiroEntrada = "exemploRegistoNumerosCovid19.csv";
        String ficheiroEntradaTotal = "totalPorEstadoCovid19EmCadaDia.csv";
        String ficheiroMatrizProbalidades = "exemploMatrizTransicoes.txt";
        String resolucaoTemporal = "1";
        int numeroDeLinhas = Projeto.ContarLinhasDoFicheiro(ficheiroEntrada) - 1;

        Date[] listaDeDatas = new Date[numeroDeLinhas];
        listaDeDatas = Projeto.PreencherListaDeDatas(ficheiroEntrada, listaDeDatas);
        int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
        listaDeCasosNaoInfetados = Projeto.PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 1, listaDeCasosNaoInfetados);
        int[] listaDeInfetados = new int[numeroDeLinhas];
        listaDeInfetados = Projeto.PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 2, listaDeInfetados);
        int[] listaDeHospitalizados = new int[numeroDeLinhas];
        listaDeHospitalizados = Projeto.PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 3, listaDeHospitalizados);
        int[] listaDeInternadosUCI = new int[numeroDeLinhas];
        listaDeInternadosUCI = Projeto.PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 4, listaDeInternadosUCI);
        int[] listaDeMortes = new int[numeroDeLinhas];
        listaDeMortes = Projeto.PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 5, listaDeMortes);


        int indexInicio = Projeto.ProcurarPosicaoData(dataInicial, listaDeDatas);
        int indexFinal = Projeto.ProcurarPosicaoData(dataFinal, listaDeDatas);
        int indexInicio1 = Projeto.ProcurarPosicaoData(dataInicial1, listaDeDatas);
        int indexFinal1 = Projeto.ProcurarPosicaoData(dataFinal1, listaDeDatas);
        int indexInicio2 = Projeto.ProcurarPosicaoData(dataInicial2, listaDeDatas);
        int indexFinal2 = Projeto.ProcurarPosicaoData(dataFinal2, listaDeDatas);


        int modoTemporal = Integer.parseInt(resolucaoTemporal);

        executarTestes(listaDeDatas, listaDeInfetados, mensagemSegundaFeiraReferencia, indexInicio, indexFinal, dataInicial, dataPrevisao);

    }

    private static boolean ProcurarPosicaoDataTeste(String data, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarPosicaoData(data, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean ContarLinhasFicheiroTeste(String ficheiro, int valorExpectado) throws ParseException, FileNotFoundException {
        if( Projeto.ContarLinhasDoFicheiro(ficheiro) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean ProcurarValorDiaTeste(String data, int [] listaDeValores, Date [] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarValorDoDia(data, listaDeValores, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean ProcurarPrimeiraSegundaFeiraTeste(int indexInicial,int indexFinal,String mensagemSegundaFeiraReferencia, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarPrimeiraSegundaFeira(indexInicial, indexFinal, mensagemSegundaFeiraReferencia, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean ProcurarPrimeiroDiaMesParaAnalizarTeste(int indexInicial,int indexFinal, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarPrimeiroDiaMesParaAnalizar(indexInicial, indexFinal, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean ProcurarUltimoDiaMesParaAnalizarTeste(int indexInicial,int indexFinal, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarUltimoDiaMesParaAnalizar(indexInicial, indexFinal, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean CalcularQuantidadeMesesParaAvaliarTeste(int indexInicial,int indexFinal, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.CalcularQuantidadeDeMesesParaAvaliar(indexInicial, indexFinal, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean SomarDadosMesTeste(int indexInicial,int indexFinal, int[] dados, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.SomarDadosMes(indexInicial, indexFinal, dados, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean ProcurarPrimeiroDiaMesTeste(String dia, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarPrimeiroDiaMes(dia, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean ProcurarUltimoDiaMesTeste(String dia, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarUltimoDiaMes(dia, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean ValidarDatasInseridasTeste(String dia, Date[] listaDeDatas, boolean valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ValidarDatasInseridas(dia, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean ProcurarDataMaisProximaTeste(String dia, Date[] listaDeDatas, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.ProcurarDataMaisProxima(dia, listaDeDatas) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean CalcularQuantidadeDiasPrevisao(String dia, Date dataMaisProxima, int valorExpectado) throws ParseException, FileNotFoundException {
        if (Projeto.CalcularQuantidadeDiasPrevisao(dia, dataMaisProxima) == valorExpectado){
            return true;
        }
        else{
            return false;
        }
    }



    public static void executarTestes(Date[] listaDeDatas, int [] listaDeInfetados, String mensagemSegundaFeiraReferencia, int indexInicial, int indexFinal, String data, String dataPrevisao) throws FileNotFoundException, ParseException {
        System.out.println("ContarLinhasFicheiro:" + (ContarLinhasFicheiroTeste("exemploRegistoNumerosCovid19.csv", 62)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarPosicaoData:" + (ProcurarPosicaoDataTeste("01-04-2020", listaDeDatas, 0)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarValorDia:" + (ProcurarValorDiaTeste("01-04-2020", listaDeInfetados, listaDeDatas, 8251)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarPrimeiraSegundaFeira:" + (ProcurarPrimeiraSegundaFeiraTeste(indexInicial, indexFinal, mensagemSegundaFeiraReferencia, listaDeDatas, 5)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarPrimeiroDiaMesAnalizar:" + (ProcurarPrimeiroDiaMesParaAnalizarTeste(indexInicial, indexFinal, listaDeDatas, 0)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarUltimoDiaMesAnalizar:" + (ProcurarUltimoDiaMesParaAnalizarTeste(indexInicial, indexFinal, listaDeDatas, 60)?"OK":"NOT OK"+"\n"));
        System.out.println("CalcularQuantidadeMesesParaAvaliar:" + (CalcularQuantidadeMesesParaAvaliarTeste(indexInicial, indexFinal, listaDeDatas, 2)?"OK":"NOT OK"+"\n"));
        System.out.println("SomarDadosMes:" + (SomarDadosMesTeste(indexInicial, indexFinal, listaDeInfetados, listaDeDatas, 17100)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarPrimeiroDiaMes:" + (ProcurarPrimeiroDiaMesTeste(data, listaDeDatas, 0)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarUltimoDiaMes:" + (ProcurarUltimoDiaMesTeste(data, listaDeDatas, 29)?"OK":"NOT OK"+"\n"));
        System.out.println("ValidarDatasInseridas:" + (ValidarDatasInseridasTeste(data, listaDeDatas, true)?"OK":"NOT OK"+"\n"));
        System.out.println("ProcurarDataMaisProxima:" + (ProcurarDataMaisProximaTeste(dataPrevisao, listaDeDatas, 16)?"OK":"NOT OK"+"\n"));
        System.out.println("CalcularQuantidadeDiasPrevisao:" + (CalcularQuantidadeDiasPrevisao(dataPrevisao, listaDeDatas[16], 1)?"OK":"NOT OK"+"\n"));



    }

}
