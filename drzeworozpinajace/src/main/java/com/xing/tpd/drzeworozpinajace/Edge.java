package com.xing.tpd.drzeworozpinajace;

import java.util.ArrayList;
import java.util.List;

public class Edge {

	private static int idCounter = 1;

	private int id;

	private List<Node> connectedNodes = new ArrayList();
	private int value;

	public Edge(Node nodeOne, Node nodeTwo, int value) {
		id = idCounter;
		connectedNodes.add(nodeOne);
		connectedNodes.add(nodeTwo);
		this.value = value;

		idCounter++;
	}

	public int getId() {
		return id;
	}

	public List<Node> getConnectedNodes() {
		return connectedNodes;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Edge [id=" + id + ", value=" + value + ",path=[" + connectedNodes.get(0).getId() + "-"
				+ connectedNodes.get(1).getId() + "]]";
	}

}
