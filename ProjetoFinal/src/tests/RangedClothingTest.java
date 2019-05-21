package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.IClothing;
import tiposRoupa.RangedClothing;

public class RangedClothingTest {
	
	IClothing roupa;


	@Before
	public void setUp() {
		roupa = new RangedClothing("code", "description", 0, "type", "color", "size", 14.78, 8);
	}

	@Test
	public void test1() {
		test(1, 8, 22.78);
	}
	
	public void test2() {
		test(10, 80, 227.8);
	}
	
	public void test3() {
		test(20, 160, 440.82);
	}
	
	public void test4() {
		test(21, 168, 447.342);
	}

	public void test5() {
		test(51, 408, 1048.713);
	}

	public void test6() {
		test(201, 1608.0, 3954.9162);
	}

	private void test(int nrItems, double orderShippingCost, double orderPrice) {
		roupa.setNrItemsOrdered(nrItems);
		assertEquals(nrItems, roupa.nrItemsOrdered());
		assertEquals(orderShippingCost, roupa.orderShippingCost(), 0.0001);
		assertEquals(orderPrice, roupa.orderPrice(), 0.0001);
	}
}
