package com.tpd.criteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaldImpl implements Criterium {

	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {
		
		List<Integer> findMin = new ArrayList<>();
		Map<Integer, Integer> result = new HashMap<>();
		
		for (int d=0; d<countsBuy.size(); d++) {
			findMin.clear();
			findMin.add(countsBuy.get(d));
			findMin.add(countsSell.get(d));
			findMin.add(countsSell.get(d)*priceSell - countsBuy.get(d)*priceBuy);
			int min = Collections.min(findMin);
			System.out.println(min);
			System.out.println(findMin.get(2));
			result.put(d, min);
		}
		
		System.out.println("------------");
		int resultMin = Collections.min(result.values());
		
		result.forEach((k, v) -> {
			if (v == resultMin) {
				System.out.println("Choise key: " + k + " value: " + v);
			}
		});
	}

	
}
