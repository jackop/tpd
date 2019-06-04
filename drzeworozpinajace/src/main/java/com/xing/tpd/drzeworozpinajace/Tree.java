package com.xing.tpd.drzeworozpinajace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {

	private List<Node> nodes = new ArrayList();
	private List<Edge> edges = new ArrayList();

	public void put(Node node) {
		nodes.add(node);
	}

	public Tree fluentPut(Node node) {
		put(node);
		return this;
	}

	public void put(Edge edge) {
		edges.add(edge);
	}

	public void putEdges(Edge... edges) {
		Arrays.stream(edges).forEach(edge -> put(edge));
	}

	public void putNodes(Node... nodes) {
		Arrays.stream(nodes).forEach(node -> put(node));
	}

	public Tree fluentPut(Edge edge) {
		put(edge);
		return this;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public Node getNodeById(int nodeId) {
		Node node = nodes.stream().filter(n -> n.getId() == nodeId).findFirst().get();
		return node;
	}

	public Edge getEdgeById(int edgeId) {
		Edge edge = edges.stream().filter(e -> e.getId() == edgeId).findFirst().get();
		return edge;
	}

}
