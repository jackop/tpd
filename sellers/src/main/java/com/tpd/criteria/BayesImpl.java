package com.tpd.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.tpd.model.FarmerScenario;

public class BayesImpl extends MinimaxImpl implements Criterium {

	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {

		List<FarmerScenario> farmerScenarios = setFarmerScenarios(countsBuy, countsSell);
		List<Integer> uniqueBuyAmounts = getUniqueBuyAmounts(farmerScenarios);
		List<Integer> uniqueSellAmounts = getUniqueSellAmounts(farmerScenarios);
		Map<Integer, Double> sellWeights = new HashMap<>();
		sellWeights.put(100, 0.15);
		sellWeights.put(130, 0.70);
		sellWeights.put(180, 0.10);
		sellWeights.put(200, 0.05);
		
		
		for (Integer uniqueSellAmount : uniqueSellAmounts) {
			farmerScenarios.stream().filter(scenario -> scenario.getSellAmount() == uniqueSellAmount)
					.forEach(scenario -> scenario.setWeight(sellWeights.get(uniqueSellAmount)));
		}
		


		Map<Integer, Double> decisions = new HashMap<>();
		for (Integer uniqueBuyAmount : uniqueBuyAmounts) {
			AtomicReference<Double> sum = new AtomicReference<>();
			sum.set(0.0);
			farmerScenarios.stream().filter(scenario -> scenario.getBuyAmount() == uniqueBuyAmount)
					.forEach(scenario -> sum.set(sum.get() + scenario.getWeightedProfit()));
			decisions.put(uniqueBuyAmount, sum.get());
		}

		AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
		AtomicInteger maxDecision = new AtomicInteger(0);
		decisions.forEach((k, v) -> {
			if(v > max.get()) {
				max.set(v.intValue());
				maxDecision.set(k);
			}
		});

		System.out.println("Decision to buy =" + maxDecision.get());
		System.out.println("Profit of decision =" + max.get());
		
	}

}
