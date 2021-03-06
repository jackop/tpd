package com.tpd.criteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpd.model.FarmerScenario;

public class WaldImpl extends MinimaxImpl implements Criterium {

	@Autowired
	FarmerScenario farmerScenario;
	
	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {
		
		List<FarmerScenario> scenarios = setFarmerScenarios(countsBuy, countsSell);
		List<Integer> uniqueBuys = getUniqueBuyAmounts(scenarios);
		Map<Integer, Integer> findMin = new HashMap();
		uniqueBuys.stream().forEach(buy -> {
			int min = Collections.min(scenarios.stream()
					.filter(s -> s.getBuyAmount()==buy)
					.map(s -> s.getProfit())
					.collect(Collectors.toList()));
			findMin.put(buy, min);
		});
		
		AtomicInteger decision = new AtomicInteger(0);
		AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
		findMin.forEach((k,v) -> {
			if(v > max.get()) {
				max.set(v);
				decision.set(k);
			}
		});
		
		System.out.println("The best of the worst scenario: " + max);
		System.out.println("How many to buy: " + decision);
		
		
	}

	
}
