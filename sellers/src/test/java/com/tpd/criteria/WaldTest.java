package com.tpd.criteria;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class WaldTest {

Criterium minimaks = new WaldImpl();
	
	@Test
	public void testCase_criteriumWald() {
		List<Integer> countsBuy = Arrays.asList(100, 120, 150, 200, 100, 100);
		List<Integer> countsSell = Arrays.asList(100, 130, 180, 200, 80, 50);
		int priceBuy = 8;
		int priceSell = 10;
		
		minimaks.process(countsBuy, countsSell, priceBuy, priceSell);
		
	}
}
