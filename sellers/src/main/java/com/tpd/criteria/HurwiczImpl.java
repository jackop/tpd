package com.tpd.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.tpd.model.FarmerScenario;

public class HurwiczImpl extends MinimaxImpl implements Criterium {

	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {
		List<FarmerScenario> farmerScenarios = setFarmerScenarios(countsBuy, countsSell);
		List<Integer> uniqueBuyAmounts = getUniqueBuyAmounts(farmerScenarios);

		Map<Integer, Double> decisions = new HashMap<>();
		for (Integer uniqueBuyAmount : uniqueBuyAmounts) {
			List<FarmerScenario> buyScenarios = farmerScenarios.stream()
					.filter(scenario -> scenario.getBuyAmount() == uniqueBuyAmount).collect(Collectors.toList());
			FarmerScenario lowest = getLowestScenario(buyScenarios);
			FarmerScenario highest = getHighestScenario(buyScenarios);

			lowest.setWeight(0.25);
			highest.setWeight(0.75);

			double sum = lowest.getWeightedProfit();
			sum += highest.getWeightedProfit();

			decisions.put(uniqueBuyAmount, sum);
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

	private FarmerScenario getLowestScenario(List<FarmerScenario> scenarios) {
		FarmerScenario lowestScenario = scenarios.get(0);
		for (FarmerScenario scenario : scenarios) {
			if (scenario.getProfit() < lowestScenario.getProfit()) {
				lowestScenario = scenario;
			}
		}
		return lowestScenario;
	}

	private FarmerScenario getHighestScenario(List<FarmerScenario> scenarios) {
		FarmerScenario highestScenario = scenarios.get(0);
		for (FarmerScenario scenario : scenarios) {
			if (scenario.getProfit() > highestScenario.getProfit()) {
				highestScenario = scenario;
			}
		}
		return highestScenario;
	}

}
