package com.xing.tpd.teoriagier;

import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("=== Start ===");
		Table table = new Table(3, 3);

		table.setCell(0, 0, 3);
		table.setCell(0, 1, 2);
		table.setCell(0, 2, 1);

		table.setCell(1, 0, 4);
		table.setCell(1, 1, 1);
		table.setCell(1, 2, -3);

		table.setCell(2, 0, 5);
		table.setCell(2, 1, 0);
		table.setCell(2, 2, -5);

		Table minsInX = new Table(3, 1);
		Table maxsInY = new Table(1, 3);

		IntStream.range(0, 3).forEach(i -> minsInX.setCell(i, 0, table.getMinInX(i)));
		IntStream.range(0, 3).forEach(i -> maxsInY.setCell(0, i, table.getMaxInY(i)));

		// table.printTable();
		// minsInX.printTable();
		// maxsInY.printTable();

		double minmaxij = minsInX.getMaxInY(0);
		double maxminij = maxsInY.getMinInX(0);

		if (minmaxij == maxminij) {
			System.out.println("Game is solved with value =[" + minmaxij + "]");
		}

		System.out.println("=== Finish ===");
	}
}
