package com.tpd.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class FarmerScenario {

	private static final int buyPrice = 8;
	private static final int sellPrice = 10;

	private int buyAmount;
	private int sellAmount;

	private int profit;
	private int relativeLose;
	private double weight;

	public FarmerScenario(int buyAmount, int sellAmount) {
		super();
		this.buyAmount = buyAmount;
		this.sellAmount = sellAmount;
		calculateProfit();
	}

	public static void calculateRelativeLose(List<FarmerScenario> scenarios) {
		
		
		AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
		scenarios.stream().filter(s -> s.getProfit() > max.get()).forEach(s -> max.set(s.getProfit()));
		scenarios.forEach(s -> s.relativeLose = max.get() - s.profit);
	}

	private void calculateProfit() {
		int profit = 0;
		if (buyAmount >= sellAmount) {
			profit = sellAmount * sellPrice - buyAmount * buyPrice;
		} else {
			profit = -buyAmount * buyPrice; // Scenario 1
			// profit = buyAmount * sellPrice - buyAmount * buyPrice; // Scenario 2
		}

		this.profit = profit;
	}

	public int getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}

	public int getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(int sellAmount) {
		this.sellAmount = sellAmount;
	}

	public int getProfit() {
		return profit;
	}

	public static int getBuyprice() {
		return buyPrice;
	}

	public static int getSellprice() {
		return sellPrice;
	}
	
	public int getRelativeLose() {
		return relativeLose;
	}

	public void setRelativeLose(int relativeLose) {
		this.relativeLose = relativeLose;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeightedProfit() {
		calculateProfit();
		return profit * weight;
	}

	@Override
	public String toString() {
		return "FarmerScenario [buyAmount=" + buyAmount + ", sellAmount=" + sellAmount + ", profit=" + profit
				+ ", relativeLose=" + relativeLose + "]";
	}

}
