package com.tpd.criteria;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.tpd.model.FarmerScenario;

public class OptimisticImpl extends MinimaxImpl implements Criterium {

	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {
		
		List<FarmerScenario> scenarios = setFarmerScenarios(countsBuy, countsSell);
		List<Integer> uniqueBuys = getUniqueBuyAmounts(scenarios);
		Map<Integer, Integer> findMax = new HashMap();
		uniqueBuys.stream().forEach(buy -> {
			int max = Collections.max(scenarios.stream()
					.filter(s -> s.getBuyAmount()==buy)
					.map(s -> s.getProfit())
					.collect(Collectors.toList()));
			findMax.put(buy, max);
		});
		
		AtomicInteger decision = new AtomicInteger(0);
		AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
		findMax.forEach((k,v) -> {
			if(v > max.get()) {
				max.set(v);
				decision.set(k);
			}
		});
		
		System.out.println("The best of the worst scenario: " + max);
		System.out.println("How many to buy: " + decision);
		
	}

}
