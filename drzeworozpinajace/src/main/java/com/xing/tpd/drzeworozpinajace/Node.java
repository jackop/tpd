package com.xing.tpd.drzeworozpinajace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

	private static int idCounter = 1;

	private int id;
	private List<Edge> edges = new ArrayList();
	private boolean isChecked = false;

	public Node() {
		id = idCounter;
		idCounter++;
	}

	public int getId() {
		return id;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
		edge.getConnectedNodes().stream().filter(node -> !node.equals(this)).forEach(node -> node.addSimpleEdge(edge));
	}

	public void addEdges(Edge... edges) {
		Arrays.stream(edges).forEach(edge -> addEdge(edge));
	}

	public void addSimpleEdge(Edge edge) {
		edges.add(edge);
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked() {
		this.isChecked = true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", isChecked=" + isChecked + "]";
	}
	
	

}
