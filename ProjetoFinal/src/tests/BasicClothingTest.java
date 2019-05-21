package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.IClothing;
import tiposRoupa.BasicClothing;

public class BasicClothingTest {

	IClothing roupa;
	IClothing roupa2;
	
	@Before
	public void setup() {
		roupa = new BasicClothing("code", "description", 0, "type", "color", "size", 11.29, 8);
		roupa2 = new BasicClothing("code", "description", 0, "type", "color", "size", 14.78, 8);
	}
	
	@Test
	public void testCreation() {
		assertEquals(11.29, roupa.unitPrice(), 0.0001);
		assertEquals(8, roupa.unitShippingCost(), 0.0001);
	}
	
	@Test
	public void test1() {
		test(1, 8, 19.29);
	}

	@Test
	public void test2() {
		test(29, 232.0, 559.41);
	}
	
	@Test
	public void test3() {
		test(1000, 8000.0, 19290.0);
	}
	
	@Test
	public void test4() {
		test(0, 0.0, 0.0);
	}
	
	private void test(int nrItems, double orderShippingCost, double orderPrice) {
		roupa.setNrItemsOrdered(nrItems);
		assertEquals(nrItems, roupa.nrItemsOrdered());
		assertEquals(orderShippingCost, roupa.orderShippingCost(), 0.0001);
		assertEquals(orderPrice, roupa.orderPrice(), 0.0001);
	}
}
