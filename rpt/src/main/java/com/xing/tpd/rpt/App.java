package com.xing.tpd.rpt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App {

//	private String jobCostString = "5,10,12,5,3,2,1,4,5,3,2,4,5,11";
	private String jobCostString = "15,22,14,9,17,31,27,8,15,11,11";
	private int numberOfProcessors = 3;

	public static void main(String[] args) {
		System.out.println("= Started Application");
		new App().start();
		System.out.println("= Finished Application");
	}

	private void start() {
		List<Integer> jobCosts = Arrays.asList(jobCostString.split(",")).stream().map(v -> Integer.valueOf(v))
				.collect(Collectors.toList());

		List<Job> jobs = jobCosts.stream().map(cost -> new Job(cost)).collect(Collectors.toList());

		List<Processor> processors = new ArrayList();
		IntStream.range(0, numberOfProcessors).forEach(i -> processors.add(new Processor()));

		Rpt rpt = new Rpt(processors, jobs);
		rpt.start();
		rpt.printResults();

	}
}
