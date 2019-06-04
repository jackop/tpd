package com.xing.tpd.teoriagier;

import java.text.DecimalFormat;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("=== Start ===");
		Table table = prepareExampleTable4();

		table.printTable();
		System.out.println();

		// Table simplifiedTable = table.simplifyTable();
		Table simplifiedTable = table;

		Table minInRows = new Table(simplifiedTable.getSizeRows(), 1);
		Table maxInCollumns = new Table(1, simplifiedTable.getSizeColumns());

		IntStream.range(0, simplifiedTable.getSizeRows())
				.forEach(i -> minInRows.setCell(i, 0, simplifiedTable.getMinInRow(i)));
		IntStream.range(0, simplifiedTable.getSizeColumns())
				.forEach(i -> maxInCollumns.setCell(0, i, simplifiedTable.getMaxInColumn(i)));

		double minmaxij = minInRows.getMaxInColumn(0);
		double maxminij = maxInCollumns.getMinInRow(0);

		simplifiedTable.printTable();
		// System.out.println(minmaxij);
		// System.out.println(maxminij);

		if (minmaxij == maxminij) {
			System.out.println("Game is solved with saddle point value =[" + minmaxij + "]");
			System.out.println("=== Finish ===");
			return;
		}
		System.out.println("The game has no saddle point, calculating mixed strategies.");

		System.out.println("==== Results ====");
		if (false) {
			MathHelper mhA = new MathHelper(simplifiedTable.getCell(0, 0), simplifiedTable.getCell(0, 1),
					simplifiedTable.getCell(1, 0), simplifiedTable.getCell(1, 1));
			MathHelper mhB = new MathHelper(simplifiedTable.getCell(0, 0), simplifiedTable.getCell(1, 0),
					simplifiedTable.getCell(0, 1), simplifiedTable.getCell(1, 1));

			DecimalFormat df = new DecimalFormat("#.##");

			System.out.println("Player 1 should user strategies with probabilities: A1=[" + df.format(mhA.getX1())
					+ "], A2=[" + df.format(mhA.getX2()) + "], to gain value=[" + df.format(mhA.getV()) + "]");
			System.out.println("Player 2 should user strategies with probabilities: B1=[" + df.format(mhB.getX1())
					+ "], B2=[" + df.format(mhB.getX2()) + "], to gain value=[" + df.format(mhB.getV()) + "]");
		} else {
			MathHelper mhA = new MathHelper(simplifiedTable, 0);
			MathHelper mhB = new MathHelper(simplifiedTable, 1);
		}
		// System.out.println(mhA.getV());
		// System.out.println(mhA.getX1());
		// System.out.println(mhA.getX2());
		//
		// System.out.println(mhB.getV());
		// System.out.println(mhB.getX1());
		// System.out.println(mhB.getX2());

		System.out.println("=== Finish ===");
	}

	private static Table prepareExampleTable1() {
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

		table.addRowHeader("A1");
		table.addRowHeader("A2");
		table.addRowHeader("A3");

		table.addColumnHeader("B1");
		table.addColumnHeader("B2");
		table.addColumnHeader("B3");

		return table;
	}

	private static Table prepareExampleTable2() {
		Table table = new Table(3, 3);
		table.setCell(0, 0, -1);
		table.setCell(0, 1, 0);
		table.setCell(0, 2, 1);

		table.setCell(1, 0, 0);
		table.setCell(1, 1, -1);
		table.setCell(1, 2, 0);

		table.setCell(2, 0, 1);
		table.setCell(2, 1, -1);
		table.setCell(2, 2, 0);

		table.addRowHeader("Dwojka");
		table.addRowHeader("Trojka");
		table.addRowHeader("Czworka");

		table.addColumnHeader("As");
		table.addColumnHeader("Krol");
		table.addColumnHeader("Dama");

		return table;
	}

	private static Table prepareExampleTable3() {
		Table table = new Table(2, 2);

		table.setCell(0, 0, 3);
		table.setCell(0, 1, -1);

		table.setCell(1, 0, -3);
		table.setCell(1, 1, 5);

		table.addRowHeader("A1");
		table.addRowHeader("A2");

		table.addColumnHeader("B1");
		table.addColumnHeader("B2");

		return table;
	}

	private static Table prepareExampleTable4() {
		Table table = new Table(4, 2);

		table.setCell(0, 0, 3);
		table.setCell(0, 1, -1);

		table.setCell(1, 0, -3);
		table.setCell(1, 1, 2);

		table.setCell(2, 0, 3);
		table.setCell(2, 1, 1);

		table.setCell(3, 0, 5);
		table.setCell(3, 1, -10);

		table.addRowHeader("A1");
		table.addRowHeader("A2");
		table.addRowHeader("A3");
		table.addRowHeader("A4");

		table.addColumnHeader("B1");
		table.addColumnHeader("B2");
		// table.addColumnHeader("B3");
		// table.addColumnHeader("B4");

		return table;
	}

}
