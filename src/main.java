
import java.util.Scanner;

public class main {

        //Declaração de constantes globais

        //Cada Km -> 1% da bateria
        public static final int AUTONOMY_KM = 100;
        public static final double RECHARGE_COST = 5.5;
        public static final int PREVENTION_DAY = 4;
        //_________________________

        //Declaração de Variáveis globais
        public static Scanner scanner = new Scanner(System.in);
        //-------------------------
        public static void main(String[] args) {
            int dayNumbers = 0;
            int carNumbers = 0;
            
            String description = read_Description();
            
            read_DaycarNumbers(carNumbers, dayNumbers);
            int[][] TripInfo = new int[carNumbers][dayNumbers];
            read_Planning(TripInfo, dayNumbers, carNumbers);
            
            int[] CarTripSum = new int[carNumbers]; //Cada index -> viagem total de cada carro
            CarTripSum = calculate_TripSum(TripInfo, carNumbers, dayNumbers);

            int[][] RecargasDiarias; //Cada index -> número de recargas de cada carro dia
            RecargasDiarias = calculate_Recargas_Day(TripInfo, carNumbers, dayNumbers);

            double[][] PercentageLeft;//Cada index -> percentagem de bateria que cada carro tem no final de cada dia da viagem
            PercentageLeft = calculate_Percentage_Left(TripInfo, RecargasDiarias, carNumbers, dayNumbers);

            double[] AverageDayFleet = calculate_Average_Day_Fleet(TripInfo, carNumbers, dayNumbers);
            boolean[] VehiclesAboveAverage = calculate_Vehicles_Above_Average(TripInfo, AverageDayFleet);
            int LatestDayRecharging = calculate_Latest_Day_Recharging(Recargas);

            double TotalRechargesCost = calculate_Total_Recharges_Cost(Recargas);

        }

        // Leitura de dados -----------------------------------------------------------
        public static String read_Description(){
           return scanner.nextLine();
        }
        public static void read_DaycarNumbers(int carNumbers, int dayNumbers){
            String line = scanner.nextLine();
            String[] sizes = line.split(" ");
            dayNumbers = Integer.parseInt(sizes[0]);
            carNumbers = Integer.parseInt(sizes[1]);
        }
        public static void read_Planning(int[][] TripInfo, int dayNumbers, int carNumbers){
            for (int i = 0; i < carNumbers; i++) {
                String line = scanner.nextLine();
                String[] values = line.split(" ");
                for (int j = 0; j < dayNumbers; j++) {
                    TripInfo[i][j] = Integer.parseInt(values[j]);
                }
            }
        }
        //----------------------------------------------------------------------------

        public static void for_Loop_Car_Day(int[][] TripInfo, int carNumbers, int dayNumbers){
            for (int i = 0; i < carNumbers; i++) {
                for (int j = 0; j < dayNumbers; j++) {
                    System.out.println(TripInfo[i][j]);
                }
            }
        }

        public static int[] calculate_TripSum(int[][] TripInfo, int carNumbers, int dayNumbers){
            int[] CarTripSum = new int[carNumbers];
            for (int i = 0; i < carNumbers; i++) {
                for (int j = 0; j < dayNumbers; j++) {
                    CarTripSum[i] += TripInfo[i][j];
                }
            }
            return CarTripSum;
        }


        public static int calculate_Batteries_Used(int tripDay){
            return tripDay / AUTONOMY_KM;
        }
        public static int[][] calculate_Recargas_Day(int[][] TripInfo, int carNumbers, int dayNumbers){
            int[][] Recargas = new int[carNumbers][dayNumbers];
            for (int i = 0; i < carNumbers; i++) {
                for (int j = 0; j < dayNumbers; j++) {
                    Recargas[i][j] = calculate_Batteries_Used(TripInfo[i][j]);
                }
            }
            return Recargas;
        }


        public static double calculate_To_percentage(int initialBattery){
            return (initialBattery % AUTONOMY_KM) * 100;
        }
        public static double[][] calculate_Percentage_Left(int[][] TripInfo, int[][] Recargas,int carNumbers,int dayNumbers){
            double[][] PercentageLeft = new double[carNumbers][dayNumbers];
            for (int i = 0; i < carNumbers; i++) {
                for (int j = 0; j < dayNumbers; j++) {
                    PercentageLeft[i][j] = calculate_To_percentage(TripInfo[i][j]);
                }
            }
            return PercentageLeft;
        }

        public static double calculate_Average_Km(int sum, int carNumbers){
            return (double)sum/carNumbers;
        }

        public static double[] calculate_Average_Day_Fleet(int[][] TripInfo, int carNumbers, int dayNumbers){
            int sum = 0;
            double[] AverageDayFleet = new double[dayNumbers];
            for (int i = 0; i < carNumbers; i++) {
                for (int j = 0; j < dayNumbers; j++) {
                    sum += TripInfo[i][j];
                }
                AverageDayFleet[i] = calculate_Average_Km(sum, carNumbers);
            }
           return AverageDayFleet;
        }

        public static boolean[] calculate_Vehicles_Above_Average(int[][] TripInfo, double Average, int carNumbers, int dayNumbers){
            int sum = 0;
            boolean[] VehiclesAboveAverage = new boolean[carNumbers];
            for (int i = 0; i < carNumbers; i++) {
                sum = 0;
                for (int j = 0; j < dayNumbers; j++) {
                    sum += TripInfo[i][j];
                }
                if (calculate_Average_Km(sum, carNumbers) > Average) {
                    VehiclesAboveAverage[i] = true;
                }
            }
            return VehiclesAboveAverage;
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

    // TODO: Fazer todos os métodos de print ( chato :( )

        public static void print_Days(int dayNumbers){
            System.out.printf("dia :");
            for (int i = 0; i < dayNumbers; i++) {
                System.out.printf("       %d", i);
            }
            System.out.println();
            System.out.printf("----|");
            for (int i = 0; i < dayNumbers; i++) {
                System.out.printf("-------|");
            }
            System.out.println();
        }



}

