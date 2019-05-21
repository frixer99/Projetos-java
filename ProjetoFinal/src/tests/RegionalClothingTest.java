package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.IClothing;
import common.OrderRegion;
import tiposRoupa.RegionalClothing;

public class RegionalClothingTest {
	
	IClothing roupa;

	@Before
	public void setUp() {
		roupa = new RegionalClothing("code", "description", 1, "type", "color", "size", 
										13.95, 5, 0.75, 8, 1, 10, 1.2);
	}

	@Test
	public void test1() {
		test(1, 5, 18.95, "EU");
	}
	@Test
	public void test2() {
		test(10, 11.75, 151.25, "EU");
	}
	@Test
	public void test3() {
		test(1, 8, 21.95, "NEAR-EU");
	}
	@Test
	public void test4() {
		test(10, 17, 156.5, "NEAR-EU");
	}
	@Test
	public void test5() {
		test(1, 10.0, 23.95, "WORLDWIDE");
	}
	@Test
	public void test6() {
		test(10, 20.8, 160.3, "WORLDWIDE");
	}

	private void test(int nrItems, double orderShippingCost, double orderPrice, String region) {
		roupa.setNrItemsOrdered(nrItems);
		roupa.setOrderRegion(OrderRegion.toOrderRegion(region));
		assertEquals(nrItems, roupa.nrItemsOrdered());
		assertEquals(orderShippingCost, roupa.orderShippingCost(), 0.0001);
		assertEquals(orderPrice, roupa.orderPrice(), 0.0001);
	}
	
}
