package common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

	public static Double multiplyIntegerAndDouble(Integer value1, Double value2) {
		
		Double total = value1 * value2;
		total = new BigDecimal(total.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue(); 
		return total;
	}

	public static double buyOneGetOneFree(double currentTotalValue, Integer quantity,Double unitValue, int factor) {
		
		int remainder = quantity%factor;
		int quantityActual = (int) Math.floor(quantity / factor);
		
		currentTotalValue = currentTotalValue + ( unitValue * quantityActual )  + (unitValue * remainder);
		
		return currentTotalValue;
		
	}
	
	public static double threeForTwo(Double currentTotalValue, Integer quantity,Double unitValue, int factor) {
		
		int remainder = quantity%factor;
		int quantityActual = (int) Math.floor(quantity / factor);
		double twiceUnitValue = unitValue * 2;
		
		currentTotalValue = currentTotalValue + ( twiceUnitValue * quantityActual )  + (unitValue * remainder);
		currentTotalValue = new BigDecimal(currentTotalValue.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
		return currentTotalValue;
		
	}
	
	

}
