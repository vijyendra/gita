package common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MathUtilsTest {
	
	
	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void buyOneGetOneFreeTest() {
		
		double cartTotal = MathUtils.buyOneGetOneFree(0.0, 99, 0.6, 2);
		
		int number1 =2;
		double quantity = 99;
		double value = quantity % number1;
		int quantityActual = (int) Math.floor(quantity / number1);
		assertEquals(quantityActual, 49);
		assertEquals(cartTotal, 30.0, 0);
		
	}
	
	@Test
	public void threeForTwoTest() {
		
		Double currentTotalValue = 0.0;
		int number1 = 3;
		int quantity = 13;
		int quantityActual = (int) Math.floor(quantity / number1);
		Double unitValue = 0.25;
		currentTotalValue = MathUtils.threeForTwo(currentTotalValue, quantity, unitValue, 3);
		
		assertEquals(quantityActual, 4);
		assertEquals(currentTotalValue, 2.25, 0);
	}

}
