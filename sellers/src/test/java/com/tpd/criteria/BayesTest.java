package com.tpd.criteria;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BayesTest {

Criterium minimaks = new BayesImpl();
	
	@Test
	public void testCase_criteriumWald() {
		List<Integer> countsBuy = Arrays.asList(100, 120, 150, 200);
		List<Integer> countsSell = Arrays.asList(100, 130, 180, 200);
		int priceBuy = 8;
		int priceSell = 10;
		
		minimaks.process(countsBuy, countsSell, priceBuy, priceSell);
		
	}
}
