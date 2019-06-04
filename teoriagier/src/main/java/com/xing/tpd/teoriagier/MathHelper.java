package com.xing.tpd.teoriagier;

import Jama.Matrix;
import java.lang.Math.*;
import java.util.Arrays;

public class MathHelper {

	private double a1, a2, b1, b2; // Dane
	private double v, x1, x2; // Szukane

	private double[][] knowns;
	private double[] res;

	public MathHelper(Table table, int player) {
		if (player == 0) {
			changeTable(table.getTable());
			knowns = table.getTable();
			System.out.println(table.getSizeRows());
			knowns = new double[table.getSizeRows() + 1][table.getSizeColumns()];
			res = new double[table.getSizeColumns()];
			for (int index = 0; index < res.length; index++) {
				res[index] = 1;
			}
		}
		calculate();
	}

	public MathHelper(double[][] knowns) {
		this.knowns = knowns;
		calculate();
	}

	public MathHelper(double a1, double a2, double b1, double b2) {
		this.a1 = a1;
		this.a2 = a2;
		this.b1 = b1;
		this.b2 = b2;
		calculateOld();
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

	private double[][] changeTable(double[][] table) {
		for (int x = 0; x < table.length; x++) {
			System.out.println("X");
			for (int y = 0; y < table[x].length; y++) {
				System.out.println("Y");
				System.out.println(table[x][y]);
			}
		}
		return table;
	}

	private void calculate() {
		double[][] lhsArrays = { { a1, a2, -1 }, { b1, b2, -1 }, { 1, 1, 0 } };
		double[] rhsArrays = res;

		// System.out.println(knowns[0][0] + " : " + knowns[0][1]);
		// System.out.println(knowns[1][0] + " : " + knowns[1][1]);
		// System.out.println(knowns[2][0] + " : " + knowns[2][1]);
		// System.out.println(knowns[3][0] + " : " + knowns[3][1]);

		Matrix lhs = new Matrix(lhsArrays);
		Matrix rhs = new Matrix(rhsArrays, 3);

		Matrix ans = lhs.solve(rhs);
	}

	private void calculateOld() { // TODO: Dodać 'syntax (?)' do lepszego liczenia układu równań

		// Matrix rhsArrays =
		double[][] lhsArrays = { { a1, a2, -1 }, { b1, b2, -1 }, { 1, 1, 0 } };
		double[] rhsArrays = { 1, 1, 1 };

		Matrix lhs = new Matrix(lhsArrays);
		Matrix rhs = new Matrix(rhsArrays, 3);

		Matrix ans = lhs.solve(rhs);
		System.out.println("X=" + ans.get(0, 0));
		System.out.println("Y=" + ans.get(1, 0));
		System.out.println("V=" + (ans.get(2, 0) + 1));

		x1 = ans.get(0, 0);
		x2 = ans.get(1, 0);
		v = ans.get(2, 0) + 1;

		// x2 = (b1 - a1) / (a2 - a1 + b1 - b2);
		// x1 = 1 - x2;
		// v = a1 * x1 + a2 * x2;
	}

}
