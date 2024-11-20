
import java.time.DayOfWeek;
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
			double[]	AverageDayFleet;
			double[][]	BatteryLevel;
			int[][]		RecargasDiarias;
			int[][]		TripInfo;
			int			dayNumbers;
			int			carNumbers;

			description = read_Description();
			carNumbers = scanner.nextInt();
			dayNumbers = scanner.nextInt();
			TripInfo = new int[carNumbers][dayNumbers];

			read_Planning(TripInfo, carNumbers, dayNumbers);

			DoingA(TripInfo, carNumbers, dayNumbers);

			DoingB(TripInfo, carNumbers, dayNumbers);

			RecargasDiarias = DoingC(TripInfo, carNumbers, dayNumbers);

			BatteryLevel = DoingD(TripInfo, RecargasDiarias, carNumbers, dayNumbers);

			AverageDayFleet = DoingE(TripInfo, carNumbers, dayNumbers);

			DoingF(TripInfo, AverageDayFleet, carNumbers, dayNumbers);

			DoingG(RecargasDiarias, carNumbers, dayNumbers);

			DoingH(RecargasDiarias, carNumbers, dayNumbers);

			DoingI(RecargasDiarias, carNumbers, dayNumbers);

			DoingJ(TripInfo , BatteryLevel, carNumbers);
		}

		//---------------------Exercices------------------------//
		//-------------------------A-----------------------------//

		public static void DoingA(int[][] TripInfo, int carNumbers, int dayNumbers)
		{
			printA(TripInfo, carNumbers, dayNumbers);
		}

		public static void printA(int[][] array, int carNumbers, int dayNumbers){
			print_Days(dayNumbers);
			for (int i = 0; i < carNumbers; i++) {
				System.out.printf("V%d :", i);
				for (int j = 0; j < dayNumbers; j++)
					System.out.printf("%8d", array[i][j]);
				System.out.println();
			}
			System.out.println();
		}

		//-------------------------B-----------------------------//

		public static void DoingB(int[][] TripInfo,int carNumbers, int dayNumbers)
		{
			int[] CarTripSum;
			CarTripSum = new int[carNumbers];
			calculate_TripSum(CarTripSum, TripInfo, carNumbers, dayNumbers);
			printB(CarTripSum, carNumbers);
		}

		public static void printB(int[] CarTripSum, int carNumbers)
		{
			System.out.println("b) total de km a percorrer");
			for (int i = 0; i < carNumbers; i++)
				System.out.printf("V%d :	%d km\n",i, CarTripSum[i]);
		}

		public static void calculate_TripSum(int[] CarTripSum, int[][] TripInfo, int carNumbers, int dayNumbers){
			for (int i = 0; i < carNumbers; i++) {
				for (int j = 0; j < dayNumbers; j++) {
					CarTripSum[i] += TripInfo[i][j];
				}
			}
		}

		//-------------------------C-----------------------------//

		public static int[][] DoingC(int[][] TripInfo, int carNumbers, int dayNumbers)
		{
			int[][] RecargasDiarias;

			RecargasDiarias = calculate_Recargas_Day(TripInfo, carNumbers, dayNumbers);
			System.out.println("\nc) recargas das baterias");
			printA(RecargasDiarias, carNumbers, dayNumbers);
			return (RecargasDiarias);
		}

		public static int[][] calculate_Recargas_Day(int[][] TripInfo, int carNumbers, int dayNumbers) {
			int[][] Recargas = new int[carNumbers][dayNumbers];
			int remainingBattery;
			int trip;

			for (int i = 0; i < carNumbers; i++) {
				remainingBattery = AUTONOMY_KM;
				for (int j = 0; j < dayNumbers; j++) {
					trip = TripInfo[i][j];
					Recargas[i][j] = calculate_Batteries_Used(trip, remainingBattery);
					remainingBattery = update_Remaining_Battery(trip, remainingBattery);
				}
			}
			return Recargas;
		}

		//-------------------------D-----------------------------//

		public static double[][] DoingD(int[][] TripInfo, int[][] RecargasDiarias, int carNumbers, int dayNumbers)
		{
			double[][]	PercentageLeft;

			PercentageLeft = calculate_Percentage_Left(TripInfo, RecargasDiarias, carNumbers, dayNumbers);
			System.out.println("d) carga das baterias");
			printD(PercentageLeft, carNumbers, dayNumbers);
			return (PercentageLeft);
		}

		public static double[][] calculate_Percentage_Left(int[][] TripInfo, int[][] Recargas,int carNumbers,int dayNumbers){
            double[][] PercentageLeft = new double[carNumbers][dayNumbers];
            int remainingBattery;
            for (int i = 0; i < carNumbers; i++) {
                remainingBattery = AUTONOMY_KM;
                for (int j = 0; j < dayNumbers; j++) {
                    PercentageLeft[i][j] = calculate_To_Percentage(TripInfo[i][j], remainingBattery);
                    remainingBattery = update_Remaining_Battery(TripInfo[i][j], remainingBattery);
                }
            }
            return PercentageLeft;
        }

		public static void printD(double[][] array, int carNumbers, int dayNumbers) {
			print_Days(dayNumbers);
			for (int i = 0; i < carNumbers; i++) {
				System.out.printf("V%d :", i);
				for (int j = 0; j < dayNumbers; j++)
					System.out.printf("%7.2f%%", array[i][j]);
				System.out.println();
			}
			System.out.println();
		}

		//-------------------------E-----------------------------//

		public static double[] DoingE(int[][] TripInfo, int carNumbers, int dayNumbers)
		{
			double[]	AverageDayFleet;

			AverageDayFleet = calculate_Average_Day_Fleet(TripInfo, carNumbers, dayNumbers);
			System.out.println("e) média de km diários da frota");
			printE(AverageDayFleet, carNumbers, dayNumbers);

			return (AverageDayFleet);
		}

		public static double[] calculate_Average_Day_Fleet(int[][] TripInfo, int carNumbers, int dayNumbers){
			int sum = 0;
			double[] AverageDayFleet = new double[dayNumbers];
			for (int i = 0; i < dayNumbers; i++)
			{
				for (int j = 0; j < carNumbers; j++)
				{
					sum += TripInfo[j][i];
				}
				AverageDayFleet[i] = calculate_Average_Km(sum, carNumbers);
				sum = 0;
			}
		return AverageDayFleet;
		}

		public static void printE(double[] AverageDayFleet, int carNumbers, int dayNumbers) {
			print_Days(dayNumbers);
			System.out.printf("km  :");
			for (int i = 0; i < dayNumbers; i++) {
				System.out.printf("%8.1f",AverageDayFleet[i]);
			}
			System.out.println("\n");
		}

		//-------------------------F-----------------------------//

		public static void DoingF(int[][] TripInfo, double[] AverageDayFleet, int carNumbers, int dayNumbers)
		{
			boolean[]	VehiclesAboveAverage;

			VehiclesAboveAverage = calculate_Vehicles_Above_Average(TripInfo, AverageDayFleet, carNumbers, dayNumbers);
			System.out.println("f) deslocações sempre acima da média diária");
			printF(VehiclesAboveAverage, carNumbers);
		}

		public static boolean[] calculate_Vehicles_Above_Average(int[][] TripInfo, double[] Average, int carNumbers, int dayNumbers){
			boolean[] VehiclesAboveAverage = new boolean[carNumbers];

			for (int i = 0; i < carNumbers; i++) {
				VehiclesAboveAverage[i] = true;
				for (int j = 0; j < dayNumbers; j++) {
					if (TripInfo[i][j] < Average[i]) {
						VehiclesAboveAverage[i] = false;
						break ;
					}
				}
			}
			return VehiclesAboveAverage;
		}

		public static void printF(boolean[] VehiclesAboveAverage, int carNumbers) {
			int	many = 0;

			for (int j = 0; j < carNumbers; j++) {
				if (VehiclesAboveAverage[j])
					many++;
			}
			System.out.printf("<%d> veículos :", many);
            for (int i = 0; i < carNumbers; i++) {
				if (VehiclesAboveAverage[i])
					System.out.printf(" [V%d]", i);
            }
            System.out.println("\n");
        }

		//-------------------------G-----------------------------//

		public static void DoingG(int[][] RecargasDiarias, int carNumbers, int dayNumbers)
		{
			System.out.println("g) veículos com mais dias consecutivas a necessitar de recarga");
			printG(RecargasDiarias, carNumbers, dayNumbers);
		}

		public static void printG(int[][] RecargasDiarias, int carNumbers, int dayNumbers) {
			int	highest_value = 0;
			int	sum;

			for (int i = 0; i < carNumbers; i++) {
				sum = 0;

				for (int j = 0; j < dayNumbers; j++){
					if (RecargasDiarias[i][j] == 0)
						break ;
					sum++;
				}
				if (sum > highest_value)
					highest_value = sum;

			}

			System.out.printf("<%d> dias consecutivos, veículos :", highest_value);

			for (int i = 0; i < carNumbers; i++){
				sum = 0;

				for (int j = 0; j < dayNumbers; j++) {
					if (RecargasDiarias[i][j] == 0)
						break ;
					sum++;
				}
				if (sum == highest_value)
					System.out.printf(" [V%d]", i);
            }
            System.out.println("\n");
        }

		//-------------------------H-----------------------------//

		public static void DoingH(int[][] RecargasDiarias, int carNumbers, int dayNumbers)
		{
			int LatestDayRecharging;

			LatestDayRecharging = calculate_Latest_Day_Recharging(RecargasDiarias, carNumbers, dayNumbers);
			System.out.printf("h) dia mais tardio em que todos os veículos necessitam de recarregar <%d>\n", LatestDayRecharging);

		}

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

		//-------------------------I-----------------------------//

		public static void DoingI(int[][] RecargasDiarias, int carNumbers, int dayNumbers)
		{
			Double	TotalRechargesCost;

			TotalRechargesCost = calculate_Total_Recharges_Cost(RecargasDiarias, carNumbers, dayNumbers);
			System.out.printf("\ni) custo das recargas da frota <%.2f €>\n", TotalRechargesCost);
		}

		//-------------------------J-----------------------------//

		public static void DoingJ(int[][] TripInfo, double[][] BatteryLevel, int carNumbers)
		{
			int	prevVehicle;

			prevVehicle = 0;
			for (int i = 1; i < carNumbers; i++)
			{
				if (TripInfo[i][PREVENTION_DAY] < TripInfo[i - 1][PREVENTION_DAY])
					prevVehicle = i;
				else if (TripInfo[i][PREVENTION_DAY] == TripInfo[i - 1][PREVENTION_DAY])
				{
					if (BatteryLevel[i][PREVENTION_DAY] > BatteryLevel[i - 1][PREVENTION_DAY])
						prevVehicle = i;
				}
			}
			System.out.printf("\nj) veículo de prevenção no dia <%d> : V%d\n", PREVENTION_DAY, prevVehicle);
		}

		//---------------------Utils------------------------//
        public static void for_Loop_Car_Day(int[][] TripInfo, int carNumbers, int dayNumbers){
            for (int i = 0; i < carNumbers; i++) {
                for (int j = 0; j < dayNumbers; j++) {
                    System.out.println(TripInfo[i][j]);
                }
            }
        }

        public static int calculate_Batteries_Used(int tripDay, int remainingBattery) {
            int batteriesUsed = 0;
            int tempTrip = tripDay - remainingBattery;
            while (tempTrip >= 0) {
                batteriesUsed++;
                tempTrip -= AUTONOMY_KM;
            }
            return batteriesUsed;
        }

        public static int update_Remaining_Battery(int tripDay, int remainingBattery) {
            int tempTrip = tripDay - remainingBattery;
            if (tempTrip < 0) {
                return Math.abs(tempTrip);
            }
            while (tempTrip >= 0) {
                tempTrip -= AUTONOMY_KM;
            }
            return Math.abs(tempTrip);
        }

        public static double calculate_To_Percentage(int tripDay, int remainingBattery) {
            int tempTrip = tripDay - remainingBattery;
            if (tempTrip < 0) {
                return (Math.abs(tempTrip) / (double) AUTONOMY_KM) * 100;
            }
            while (tempTrip >= 0) {
                tempTrip -= AUTONOMY_KM;
            }
            return (Math.abs(tempTrip) / (double) AUTONOMY_KM) * 100;
        }

		public static double calculate_Average_Km(int sum, int carNumbers){
            return (double)sum/carNumbers;
        }

        public static void calculate_Vehicles_Charged_In_Row(int[][] Recargas){
            // Este método vai imprimir os carros que fizeram mais recargas seguidas
        }

		public static int calculate_Latest_Day_Recharging(int[][] Recargas, int carNumbers, int dayNumbers){
			int	manyCarsCharged;
			int	highest_value = 0;
			int	date;

			date = 0;
			for (int i = 0; i < dayNumbers; i++) {
				manyCarsCharged = 0;

				for (int j = 0; j < carNumbers; j++) {
					if (Recargas[j][i] == 0)
						break ;
					manyCarsCharged++;
				}
				if (manyCarsCharged > highest_value)
				{
					highest_value  = manyCarsCharged;
					date = i;
				}
			}
			return (date);
		}

		public static double calculate_Total_Recharges_Cost(int[][] Recargas, int carNumbers, int dayNumbers){
			double	fullPrice;

			fullPrice = 0;
			for (int i = 0; i < carNumbers; i++) {
				for (int j = 0; j < dayNumbers; j++) {
					fullPrice += Recargas[i][j] * RECHARGE_COST;
				}
			}
			return (fullPrice);
		}

		// Leitura de dados -----------------------------------------------------------
		public static String read_Description(){
			return scanner.nextLine();
		}

		public static void read_Planning(int[][] TripInfo, int carNumbers, int dayNumbers){
			for (int i = 0; i < carNumbers; i++)
			{
				for (int j = 0; j < dayNumbers; j++)
					TripInfo[i][j] = scanner.nextInt();
			}
		}

}
