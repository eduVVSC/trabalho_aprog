
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
			String		description;
			double[][]	PercentageLeft;//Cada index -> percentagem de bateria que cada carro tem no final de cada dia da viagem
            int[][]		RecargasDiarias; //Cada index -> número de recargas de cada carro dia
			int[][]		TripInfo;
			int[]		CarTripSum;
			int			dayNumbers;
            int			carNumbers;

			//=================everything out====================//
            /* String line = scanner.nextLine();
            String[] sizes = line.split(" ");
            dayNumbers = Integer.parseInt(sizes[0]);
            carNumbers = Integer.parseInt(sizes[1]);*/

			description = read_Description();
            carNumbers = scanner.nextInt();
			dayNumbers = scanner.nextInt();
            TripInfo = new int[carNumbers][dayNumbers];
			CarTripSum = new int[carNumbers]; //Cada index -> viagem total de cada carro

            read_Planning(TripInfo, dayNumbers, carNumbers);
			printA(TripInfo, carNumbers, dayNumbers);

            calculate_TripSum(CarTripSum, TripInfo, carNumbers, dayNumbers);
			printB(CarTripSum, carNumbers);

            RecargasDiarias = calculate_Recargas_Day(TripInfo, carNumbers, dayNumbers);
			System.out.println();
			printA(RecargasDiarias, carNumbers, dayNumbers);
           	PercentageLeft = calculate_Percentage_Left(TripInfo, RecargasDiarias, carNumbers, dayNumbers);

			printA(PercentageLeft, carNumbers, dayNumbers);
/*             double[] AverageDayFleet = calculate_Average_Day_Fleet(TripInfo, carNumbers, dayNumbers);
            boolean[] VehiclesAboveAverage = calculate_Vehicles_Above_Average(TripInfo, AverageDayFleet);
            int LatestDayRecharging = calculate_Latest_Day_Recharging(Recargas);

            double TotalRechargesCost = calculate_Total_Recharges_Cost(Recargas); */

        }

        // Leitura de dados -----------------------------------------------------------
        public static String read_Description(){
           return scanner.nextLine();
        }

        public static void read_Planning(int[][] TripInfo, int dayNumbers, int carNumbers){
			for (int i = 0; i < carNumbers; i++)
			{
				for (int j = 0; j < dayNumbers; j++)
					TripInfo[i][j] = scanner.nextInt();
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

        public static void calculate_TripSum(int[] CarTripSum, int[][] TripInfo, int carNumbers, int dayNumbers){
            for (int i = 0; i < carNumbers; i++) {
                for (int j = 0; j < dayNumbers; j++) {
                    CarTripSum[i] += TripInfo[i][j];
                }
            }
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

/*         public static int calculate_Latest_Day_Recharging(int[][] Recargas){
            // Este método vai calcular o dia em que houve mais recargas
        }

        public static double calculate_Total_Recharges_Cost(int[][] Recargas){
            // Este método vai calcular o total de recargas feitas
        }

        public static int calculate_Prevention_Car(int[][] TripInfo){
            // Ler no enunciado o que é para fazer
        }
z */
    // TODO: Fazer todos os métodos de print ( chato :( )

        public static void print_Days(int dayNumbers, int carNumbers){
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

		public static void printA(int[][] array, int carNumbers, int dayNumbers){
			print_Days(dayNumbers, carNumbers);
			for (int i = 0; i < carNumbers; i++) {
				System.out.printf("V%d :", i);
				for (int j = 0; j < dayNumbers; j++)
					System.out.printf("	%d", array[i][j]);
				System.out.println();
			}
			System.out.println();
		}

		public static void printB(int[] CarTripSum, int carNumbers)
		{
			System.out.println("b) total de km a percorrer");
			for (int i = 0; i < carNumbers; i++)
				System.out.printf("V%d :	%d\n",i, CarTripSum[i]);
		}

}

