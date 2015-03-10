import java.util.Random;

public class SimulateA {
	static final double ZEROPRICE  = 60;
	static final double RISKFREE = 1.05;
	static final double COSTS = 40;
	static final double prob = 0.489583;


	static Random random;

	public static double presentValue(int year, double price) {
		double result = (price - COSTS) * 10 / Math.pow(RISKFREE, year);
		return result;
	}

	public static double nextPrice(double current) {
		double rnd = random.nextDouble();
		double newP = 0;
		if(rnd > prob) {
			newP = current * 1.4;
		} else {
			newP = current / 1.4;
		}
		return newP;
	}

	public static double oneSimulation() {

		double total = 0;
		double currentPrice = ZEROPRICE;
		for (int i = 0; i < 10; i++) {
			double thisyear = presentValue(i, currentPrice);
			total += thisyear;
			currentPrice = nextPrice(currentPrice);
		}
		return total;
	}

	public static void main(String[] args) {
		random = new Random();
		int simNumber = Integer.parseInt(args[0]);
		double simulatedSum = 0;
		for (int i = 0; i < simNumber; i++) {
			simulatedSum += oneSimulation();
		}
		double presVal = simulatedSum / simNumber;
		System.out.println(presVal);
	}
}
