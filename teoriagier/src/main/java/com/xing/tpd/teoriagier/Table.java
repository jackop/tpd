package com.xing.tpd.teoriagier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Table {

	private int sizeRows;
	private int sizeColumns;

	private double[][] table;
	private String[] rowHeaders = new String[0];
	private String[] columnHeaders = new String[0];
	private double[] maxInRow;
	private double[] maxInColumn;
	private double[] minInRow;
	private double[] minInColumn;

	public Table(int sizeX, int sizeY) {
		this.sizeRows = sizeX;
		this.sizeColumns = sizeY;
		this.table = new double[sizeX][sizeY];
		this.maxInRow = new double[sizeY];
		this.minInRow = new double[sizeY];
		this.maxInColumn = new double[sizeX];
		this.minInColumn = new double[sizeX];
	}

	public void addRowHeader(String header) {
		String[] newRowHeaders = new String[rowHeaders.length + 1];
		IntStream.range(0, rowHeaders.length).forEach(i -> newRowHeaders[i] = rowHeaders[i]);
		newRowHeaders[newRowHeaders.length - 1] = header;
		rowHeaders = newRowHeaders;
	}

	public void addColumnHeader(String header) {
		String[] newColumnHeaders = new String[columnHeaders.length + 1];
		IntStream.range(0, columnHeaders.length).forEach(i -> newColumnHeaders[i] = columnHeaders[i]);
		newColumnHeaders[newColumnHeaders.length - 1] = header;
		columnHeaders = newColumnHeaders;
	}

	public String getRowHeader(int row) {
		return rowHeaders[row];
	}

	public String getColumnHeader(int column) {
		return columnHeaders[column];
	}

	public int getSizeRows() {
		return sizeRows;
	}

	public int getSizeColumns() {
		return sizeColumns;
	}

	public void setCell(int x, int y, double value) {
		table[x][y] = value;
	}

	public double getCell(int x, int y) {
		return table[x][y];
	}

	public double getMinInRow(int x) {
		double min = Double.MAX_VALUE;
		for (int index = 0; index < sizeColumns; index++) {
			if (table[x][index] <= min) {
				min = table[x][index];
			}
		}
		return min;
	}

	public double getMaxInColumn(int y) {
		double max = -Double.MAX_VALUE;
		for (int index = 0; index < sizeRows; index++) {
			if (table[index][y] >= max) {
				max = table[index][y];
			}
		}
		return max;
	}

	private Table removeColumn(int columnNumberToRemove) {
		Table newTable = new Table(sizeRows, sizeColumns - 1);
		for (int y1 = 0, y2 = 0; y1 < sizeColumns; y1++, y2++) {
			if (y1 == columnNumberToRemove) {
				y2--;
				continue;
			}
			newTable.addColumnHeader(this.columnHeaders[y1]);
			for (int x = 0; x < sizeRows; x++) {
				newTable.setCell(x, y2, this.getCell(x, y1));
			}
		}
		for (int x = 0; x < sizeRows; x++) {
			newTable.addRowHeader(this.rowHeaders[x]);
		}
		return newTable;
	}

	private Table removeRow(int rowNumberToRemove) {
		Table newTable = new Table(sizeRows - 1, sizeColumns);
		for (int x1 = 0, x2 = 0; x1 < sizeRows; x1++, x2++) {
			if (x1 == rowNumberToRemove) {
				x2--;
				continue;
			}
			newTable.addRowHeader(this.rowHeaders[x1]);
			for (int y = 0; y < sizeColumns; y++) {
				newTable.setCell(x2, y, this.getCell(x1, y));
			}
		}
		for (int y = 0; y < sizeColumns; y++) {
			newTable.addColumnHeader(this.columnHeaders[y]);
		}
		return newTable;
	}

	/**
	 * Jeśli w kolumnie są odpowiednio większe wartości niż w innej kolumnie, ta
	 * kolumna jest zdominowana i może być usunięta. Jeśli w wierszu są odpowiednio
	 * mniejsze wartości niż w innym wierszu, ten wiersz jest zdominowany i może być
	 * usunięty.
	 */
	public Table simplifyTable() {
		return this.simplifyTableColumns().simplifyTableRows();
	}

	private Table simplifyTableColumns() {
		Table newTable = this;
		List<Integer> columnsToRemove = new ArrayList<>();
		IntStream.range(0, sizeColumns).forEach(bigColumn -> {
			boolean isColumnRedundant = IntStream.range(0, sizeColumns).filter(y -> y != bigColumn)
					.anyMatch(column -> IntStream.range(0, sizeRows)
							.allMatch(row -> table[row][bigColumn] >= table[row][column]));
			if (isColumnRedundant) {
				columnsToRemove.add(bigColumn);
			}
		});

		for (Integer columnNumber : columnsToRemove) {
			newTable = newTable.removeColumn(columnNumber);
		}
		return newTable;
	}

	private Table simplifyTableRows() {
		Table newTable = this;
		List<Integer> rowsToRemove = new ArrayList<>();
		IntStream.range(0, sizeRows).forEach(bigRow -> {
			boolean isRowRedundant = IntStream.range(0, sizeRows).filter(x -> x != bigRow).anyMatch(row -> IntStream
					.range(0, sizeColumns).allMatch(column -> table[bigRow][column] <= table[row][column]));
			if (isRowRedundant) {
				rowsToRemove.add(bigRow);
			}
		});
		for (Integer rowNumber : rowsToRemove) {
			newTable = newTable.removeRow(rowNumber);
		}
		return newTable;
	}

	public void printTable() {
		String result = "";
		for (int y = 0; y < sizeColumns; y++) {
			result += "\t|" + columnHeaders[y] + "|";
		}
		result += "\n----------------------------------\n";
		for (int x = 0; x < sizeRows; x++) {
			result += rowHeaders[x] + "\t";
			for (int y = 0; y < sizeColumns; y++) {
				result += "|" + table[x][y] + "\t|";
			}
			result += "\n----------------------------------\n";
		}

		System.out.print(result);
	}

}
