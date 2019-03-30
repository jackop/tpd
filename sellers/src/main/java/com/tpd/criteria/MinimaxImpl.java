package com.tpd.criteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.tpd.criteria.Criterium;
import com.tpd.model.FarmerScenario;

import org.springframework.stereotype.Component;

@Component
public class MinimaxImpl implements Criterium {

	private List<FarmerScenario> setFarmerScenarios(List<Integer> countsBuy, List<Integer> countsSell) {
		List<FarmerScenario> farmerScenarios = new ArrayList<>();
		countsBuy.forEach(cb -> {
			countsSell.forEach(cs -> {
				farmerScenarios.add(new FarmerScenario(cb, cs));
			});
		});
		return farmerScenarios;
	}

	private List<Integer> getUniqueSellAmounts(List<FarmerScenario> farmerScenarios) {
		List<Integer> uniqueSellAmounts = new ArrayList<>();
		farmerScenarios.forEach(s -> {
			if (!uniqueSellAmounts.contains(s.getSellAmount())) {
				uniqueSellAmounts.add(s.getSellAmount());
			}
		});
		return uniqueSellAmounts;
	}

	private List<Integer> getUniqueBuyAmounts(List<FarmerScenario> farmerScenarios) {
		List<Integer> uniqueBuyAmounts = new ArrayList<>();
		farmerScenarios.forEach(s -> {
			if (!uniqueBuyAmounts.contains(s.getBuyAmount())) {
				uniqueBuyAmounts.add(s.getBuyAmount());
			}
		});
		return uniqueBuyAmounts;
	}

	private List<FarmerScenario> findMaxRelativeLosses(List<FarmerScenario> farmerScenarios) {
		List<FarmerScenario> maxScenarios = new ArrayList<>();
		List<Integer> uniqueBuyAmounts = getUniqueBuyAmounts(farmerScenarios);
		for (Integer uniqueBuyAmount : uniqueBuyAmounts) {
			AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
			FarmerScenario maxScenario = null;
			List<FarmerScenario> scenarios = farmerScenarios.stream().filter(s -> s.getBuyAmount() == uniqueBuyAmount)
					.collect(Collectors.toList());
			for (FarmerScenario scenario : scenarios) {
				if (scenario.getRelativeLose() >= max.get()) {
					maxScenario = scenario;
					max.set(scenario.getRelativeLose());
				}
			}
			maxScenarios.add(maxScenario);
		}
		return maxScenarios;
	}
	
	private FarmerScenario findMinMaxRelativeLoss(List<FarmerScenario> farmerScenarios) {
		FarmerScenario minScenario = null;
		
		AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
		for(FarmerScenario scenario : farmerScenarios) {
			if(scenario.getRelativeLose() <= min.get()) {
				minScenario = scenario;
				min.set(scenario.getRelativeLose()); 
			}
		}
		
		return minScenario;
	}

	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {

		List<FarmerScenario> farmerScenarios = setFarmerScenarios(countsBuy, countsSell);
		List<Integer> uniqueSellAmounts = getUniqueSellAmounts(farmerScenarios);
		for (Integer uniqueSellAmount : uniqueSellAmounts) {
			FarmerScenario.calculateRelativeLose(farmerScenarios.stream()
					.filter(s -> s.getSellAmount() == uniqueSellAmount).collect(Collectors.toList()));
		}

		farmerScenarios.forEach(System.out::println);
		System.out.println("==========");
		List<FarmerScenario> maxDecisions = findMaxRelativeLosses(farmerScenarios);
		maxDecisions.forEach(System.out::println);
		System.out.println("==========");
		FarmerScenario minMaxScenario = findMinMaxRelativeLoss(maxDecisions);
		System.out.println(minMaxScenario);
		System.out.println("Farmer should choose buyAmount=" + minMaxScenario.getBuyAmount());
	}

	private List<Integer> calculateIncomes(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy,
			int priceSell) {
		List<Integer> incomeList = new ArrayList<>();
		for (int i = 0; i < countsSell.size(); i++) {

			incomeList.add(countsSell.get(i) * priceSell - countsBuy.get(i) * priceBuy);
		}
		return incomeList;
	}
}
