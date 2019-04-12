package com.xing.tpd.teoriagier;

public class MathHelper {

	private double a1, a2, b1, b2; // Dane
	private double v, x1, x2; // Szukane

	public MathHelper(double a1, double a2, double b1, double b2) {
		this.a1 = a1;
		this.a2 = a2;
		this.b1 = b1;
		this.b2 = b2;
		calculate();
	}

	public double getX1() {
		return x1;
	}

	public double getX2() {
		return x2;
	}

	public double getV() {
		return v;
	}

	private void calculate() {
		x2 = (b1-a1) / (a2-a1+b1-b2);
		x1 = 1 - x2;
		v = a1 * x1 + a2 * x2;
	}

}
