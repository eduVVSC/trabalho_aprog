import java.util.Scanner;
public class Main {

        //Declaração de constantes globais

        //Cada Km -> 1% da bateria
        public final int AUTONOMY_KM = 100;
        public final double RECHARGE_COST = 5.5;
        public final int PREVENTION_DAY = 4;
        //_________________________

        //Declaração de Variáveis globais
        Scanner scanner = new Scanner(System.in);
        //-------------------------
        public static void main(String[] args) {

            int DayNumbers; //Nº Colunas -> Nº dias de viagem
            int CarNumbers; //Nº Linhas ->  Nº carros
            int[][] TripInfo = new int[CarNumbers][DayNumbers];

            int[] CarTripSum = new int[L]; //Cada index -> viagem total de cada carro
            CarTripSum = calculate_TripSum(TripInfo, CarTripSum);

            int[][] Recargas = new int[L]; //Cada index -> número de recargas de cada carro dia
            Recargas = calculate_Recargas_Dia(CarTripSum);

            double[] PercentageLeft = new double[L];//Cada index -> percentagem de bateria que cada carro tem no final de cada dia da viagem
            PercentageLeft = calculate_Percentage_Left(TripInfo, Recargas);

            int AverageDayFleet = calculate_Average_Day_Fleet(TripInfo);
            int LatestDayRecharging = calculate_Latest_Day_Recharging(Recargas);

            double TotalRechargesCost = calculate_Total_Recharges_Cost(Recargas);

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

        public static int[] calculate_TripSum(int[][] TripInfo){
            // Este método vai calcular a viagem total de cada carro
            // Somando os valores de cada dia
        }

        public static int[][] calculate_Recargas_Dia(int[][] TripInfo){
            // Este método vai calcular o número de recargas de cada carro diaria
            // Cada carro tem uma autonomia de 100km
            // Cada km percorrido -> 1% da bateria
            // Cada recarga -> 100% da bateria
        }

        public static double[] calculate_Percentage_Left(int[][] TripInfor, int[][] Recargas){
            // Este método vai calcular a percentagem de bateria que cada carro tem no final de cada dia da viagem
            // Cada carro tem uma autonomia de 100 por bateria
            // Cada km percorrido -> 1% da bateria
        }

        public static double calculate_Average_Day_Fleet(int[][] TripInfo){
            // Este método vai calcular a média de km percorridos por dia
            // Somando os valores de cada dia e dividindo pelo número de carros
            // Somando depois as medias diárias e dividindo pelo número de dias
        }

        public static void calculate_Vehicles_Above_Average(int[][] TripInfo, double Average){
            // Este método vai imprimir os carros que percorreram mais km que a média diária da frota
        }

        public static void calculate_Vehicles_Charged_In_Row(int[][] Recargas){
            // Este método vai imprimir os carros que fizeram mais recargas seguidas
        }

        public static int calculate_Latest_Day_Recharging(int[][] Recargas){
            // Este método vai calcular o dia em que houve mais recargas
        }

        public static double calculate_Total_Recharges_Cost(int[][] Recargas){
            // Este método vai calcular o total de recargas feitas
        }

        public static int calculate_Prevention_Car(int[][] TripInfo){
            // Ler no enunciado o que é para fazer
        }
}