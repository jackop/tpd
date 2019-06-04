package com.xing.tpd.rpt;

import java.util.List;

public class Rpt {
	private List<Processor> processors;
	private List<Job> jobs;

	public Rpt(List<Processor> processors, List<Job> jobs) {
		this.processors = processors;
		this.jobs = jobs;
	}

	public void start() {
		jobs.sort((first, second) -> first.getCost() - second.getCost());

		Processor minProcessor = processors.get(0);
		for (Job job : jobs) {
			for (Processor processor : processors) {
				if (processor.getOverload() <= minProcessor.getOverload()) {
					minProcessor = processor;
				}
			}
			minProcessor.addJob(job);
		}
	}

	public void printResults() {
		processors.forEach(System.out::println);
	}
}
