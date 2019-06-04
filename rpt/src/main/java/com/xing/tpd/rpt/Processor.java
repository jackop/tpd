package com.xing.tpd.rpt;

import java.util.ArrayList;
import java.util.List;

public class Processor {
	private static int idCounter = 1;
	private int id;
	private int overload;
	private List<Job> jobs = new ArrayList();

	public Processor() {
		id = idCounter++;
	}

	public void addJob(Job job) {
		jobs.add(job);
		overload += job.getCost();
	}

	@Override
	public String toString() {
		return "Processor [id=" + id + ", overload=" + overload + ", jobs=" + jobs + "]";
	}

	public int getOverload() {
		return overload;
	}

	public List<Job> getJobs() {
		return jobs;
	}

}
