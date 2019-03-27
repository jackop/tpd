package com.tpd.criteria;

import java.util.Arrays;
import java.util.List;

public interface Criterium {
	
	void process(List<Integer> countsBuy, List<Integer> countsSell, int priceBuy, int priceSell);
}
