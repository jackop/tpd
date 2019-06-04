package com.xing.tpd.rpt;

public class Job {
	private static int idCounter = 1;
	private int id;
	private int cost;

	private int inTime;
	private int outTime; // minimizing max(outTime)
	private int flowTime; // minimizing average(outTime - inTime)

	public Job(int cost) {
		id = idCounter++;
		this.cost = cost;
		inTime = 0;
	}

	public int getId() {
		return id;
	}

	public int getCost() {
		return cost;
	}

	public void setInTime(int inTime) {
		this.inTime = inTime;
	}

	public void setOutTime(int outTime) {
		this.outTime = outTime;
		this.flowTime = outTime - inTime;
	}

	public int getInTime() {
		return inTime;
	}

	public int getOutTime() {
		return outTime;
	}

	public int getFlowTime() {
		return flowTime;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", cost=" + cost + "]";
	}

}
