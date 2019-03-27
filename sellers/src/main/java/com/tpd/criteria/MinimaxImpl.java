package com.tpd.criteria;

import java.util.List;
import com.tpd.criteria.Criterium;
import org.springframework.stereotype.Component;

@Component
public class MinimaxImpl implements Criterium {

	@Override
	public void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell) {
		
		for(Integer countBuy: countsBuy) {
			System.out.println(countBuy);
		}
			
	}

	
}
