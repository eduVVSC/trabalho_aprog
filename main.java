import java.util.Scanner;
public class Main {

    //Declaração de constantes globais

    //Cada Km -> 1% da bateria
    public final int AUTONOMY_KM = 100;

    //_________________________

    //Declaração de Variáveis globais
    Scanner scanner = new Scanner(System.in);
    //-------------------------
    public static void main(String[] args) {

        int DayNumbers; //Nº Colunas -> Nº dias de viagem
        int CarNumbers; //Nº Linhas ->  Nº carros
        int[][] TripInfo = new int[CarNumbers][DayNumbers];
        int[] CarTripSum = new int[L]; //Cada index -> viagem total de cada carro
    }

    // Leitura de dados -----------------------------------------------------------
    public static String read_Description(){
       // Este método vai ler a descrição do planeamento
    }
    public static void read_DayCarNumbers(int DayNumbers, int CarNumbers){
        // Este método vai ler o número de dias de viagem na mesma linha separado por um espaço
        // ex: 3 6 -> 3 carros e 6 dias de viagem
    }
    public static void read_Planning(int[][] TripInfo){
        // Este método vai ler o numero que cada carro percorreu em cada dia
        // Na mesma linha e separados por um espaço
        // ex: 30 40 50 60 70 -> dia 0 percorreu 30km, dia 1 percorreu 40km, etc
    }
    //----------------------------------------------------------------------------

    public static int[] calculate_TripSum(int[][] TripInfo, int[] CarTripSum){
        // Este método vai calcular a viagem total de cada carro
        // Somando os valores de cada dia
    }

    public static int[] calculate_Recargas(int[] CarTripSum){
        // Este método vai calcular o número de recargas de cada carro
        // Cada carro tem uma autonomia de 100km
        // Cada km percorrido -> 1% da bateria
        // Cada recarga -> 100% da bateria
    }

    public static double[] calculate_Percentage_Left(int[][] TripInfor){
        // Este método vai calcular a percentagem de bateria que cada carro tem no final de cada dia da viagem
        // Cada carro tem uma autonomia de 100 por bateria
        // Cada km percorrido -> 1% da bateria
    }


}