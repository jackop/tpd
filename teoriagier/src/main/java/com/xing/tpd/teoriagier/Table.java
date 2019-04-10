package com.xing.tpd.teoriagier;

public class Table {

	private int sizeX;
	private int sizeY;

	private double[][] table;
	private double[] maxInX;
	private double[] maxInY;
	private double[] minInX;
	private double[] minInY;

	public Table(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.table = new double[sizeX][sizeY];
		this.maxInX = new double[sizeX];
		this.minInX = new double[sizeX];
		this.maxInY = new double[sizeY];
		this.minInY = new double[sizeY];
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setCell(int x, int y, double value) {
		table[x][y] = value;
	}

	public double getCell(int x, int y) {
		return table[x][y];
	}

	public double getMinInX(int x) {
		double min = Double.MAX_VALUE;
		for (int index = 0; index < sizeY; index++) {
			if (table[x][index] <= min) {
				min = table[x][index];
			}
		}
		return min;
	}

	public double getMaxInX(int x) {
		double max = Double.MIN_VALUE;
		for (int index = 0; index < sizeY; index++) {
			if (table[x][index] >= max) {
				max = table[x][index];
			}
		}
		return max;
	}

	public double getMinInY(int y) {
		double min = Double.MAX_VALUE;
		for (int index = 0; index < sizeX; index++) {
			if (table[index][y] <= min) {
				min = table[index][y];
			}
		}
		return min;
	}

	public double getMaxInY(int y) {
		double max = Double.MIN_VALUE;
		for (int index = 0; index < sizeX; index++) {
			if (table[index][y] >= max) {
				max = table[index][y];
			}
		}
		return max;
	}

	public void printTable() {
		String result = "";
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				result += "|" + table[x][y] + "|";
			}
			result += "\n-----------------\n";
		}

		System.out.println(result);
	}

}
