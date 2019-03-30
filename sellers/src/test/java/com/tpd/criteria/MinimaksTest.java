package com.tpd.criteria;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MinimaksTest {
	
	Criterium minimaks = new MinimaxImpl();
	
	@Test
	public void testCase_criteriumMinimaks() {
		List<Integer> countsBuy = Arrays.asList(100, 120, 150, 200);
		List<Integer> countsSell = Arrays.asList(100, 130, 180, 200);
		int priceBuy = 8;
		int priceSell = 10;
		
		minimaks.process(countsBuy, countsSell, priceBuy, priceSell);
		
	}
}
