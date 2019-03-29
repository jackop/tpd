package com.tpd.criteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tpd.criteria.Criterium;
import org.springframework.stereotype.Component;

@Component
public class MinimaxImpl implements Criterium {

	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {
		
		int countBuyMax = Collections.max(countsBuy);
		int countSellMax = Collections.max(countsSell);
		List<Integer> incomeList = new ArrayList<>();

		List<Integer> results = new ArrayList<>();
		Map<Integer, Integer> mapMax = new HashMap<>();
		
		for(int i=0; i<countsSell.size(); i++) {
			incomeList.add(countsSell.get(i)*priceSell - countsBuy.get(i)*priceBuy);
		}
		int incomeListMax = Collections.max(incomeList);
		
		for(int i=0; i<countsBuy.size(); i++) {
			results.clear();
			int result = countBuyMax - countsBuy.get(i);
			results.add(result);
			int result2 = countSellMax - countsSell.get(i);
			results.add(result2);
			int income = incomeListMax - incomeList.get(i);
			results.add(income);
			int max = Collections.max(results);
			mapMax.put(i, max);
		}	
		
		System.out.println("=====================================");
		
	      int MaxValue = Collections.max(mapMax.values()); 
	      mapMax.forEach((k, v) -> {
	    	  if (v == MaxValue) {
	    		  System.out.println("KEY:");
	    		  System.out.println(k);
	    	  }
	      });
	      System.out.println("VALUE:");
	      System.out.println(MaxValue);
	}

	
}
