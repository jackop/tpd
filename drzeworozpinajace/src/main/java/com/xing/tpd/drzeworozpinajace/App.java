package com.xing.tpd.drzeworozpinajace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Started");
		App app = new App();
		app.start();
		System.out.println("Finished");
	}

	public void start() {
		// Tree tree = prepareExerciseData();
		Tree tree = prepareTestData();

		List<Edge> edgePath = new ArrayList();
		Node firstNode = tree.getNodeById(1);
		firstNode.setChecked();

		int sumOfPaths = 0;
		for (int i = 0; i < tree.getNodes().size() - 1; i++) {

			Edge minEdge = null;
			int minValue = Integer.MAX_VALUE;
			List<Edge> edges = firstNode.getEdges().stream()
					.filter(edge -> edge.getConnectedNodes().stream().anyMatch(node -> !node.isChecked()))
					.collect(Collectors.toList());
			for (Edge edge : edges) {
				if (edge.getValue() <= minValue) {
					minEdge = edge;
					minValue = edge.getValue();
				}
			}
			sumOfPaths += minValue;
			for (Node node : minEdge.getConnectedNodes()) {
				if (!node.isChecked()) {
					firstNode = node;
				}
			}
			minEdge.getConnectedNodes().forEach(Node::setChecked);
			edgePath.add(minEdge);
		}
		edgePath.forEach(edge -> System.out.println(edge));
		System.out.println(sumOfPaths);
	}

	private Tree prepareTestData() {
		Tree tree = new Tree();

		Node n1 = new Node();
		Node n2 = new Node();
		Node n3 = new Node();
		Node n4 = new Node();
		Node n5 = new Node();
		Node n6 = new Node();

		Edge e12 = new Edge(n1, n2, 15);
		Edge e13 = new Edge(n1, n3, 20);
		Edge e14 = new Edge(n1, n4, 18);
		n1.addEdges(e12, e13, e14);

		Edge e23 = new Edge(n2, n3, 8);
		Edge e25 = new Edge(n2, n5, 8);
		Edge e26 = new Edge(n2, n6, 11);
		n2.addEdges(e23, e25, e26);

		Edge e34 = new Edge(n3, n4, 7);
		Edge e35 = new Edge(n3, n5, 12);
		Edge e36 = new Edge(n3, n6, 7);
		n3.addEdges(e34, e35, e36);

		Edge e45 = new Edge(n4, n5, 18);
		Edge e46 = new Edge(n4, n6, 13);
		n4.addEdges(e45, e46);

		Edge e56 = new Edge(n5, n6, 5);
		n5.addEdges(e56);
		
		tree.putNodes(n1, n2, n3, n4, n5, n6);
		tree.putEdges(e12, e13, e14, e23, e25, e26, e34, e35, e36, e45, e46);

		return tree;
	}

	private Tree prepareExerciseData() {
		Tree tree = new Tree();
		Node n1 = new Node();
		Node n2 = new Node();
		Node n3 = new Node();
		Node n4 = new Node();
		Node n5 = new Node();
		Node n6 = new Node();
		Node n7 = new Node();
		Node n8 = new Node();

		Edge e12 = new Edge(n1, n2, 132);
		Edge e13 = new Edge(n1, n3, 231);
		Edge e14 = new Edge(n1, n4, 311);
		Edge e15 = new Edge(n1, n5, 423);
		Edge e16 = new Edge(n1, n6, 322);
		Edge e17 = new Edge(n1, n7, 143);
		Edge e18 = new Edge(n1, n8, 243);
		n1.addEdges(e12, e13, e14, e15, e16, e17, e18);

		Edge e23 = new Edge(n2, n3, 231);
		Edge e24 = new Edge(n2, n4, 542);
		Edge e25 = new Edge(n2, n5, 311);
		Edge e26 = new Edge(n2, n6, 221);
		Edge e27 = new Edge(n2, n7, 264);
		Edge e28 = new Edge(n2, n8, 327);
		n2.addEdges(e23, e24, e25, e26, e27, e28);

		Edge e34 = new Edge(n3, n4, 423);
		Edge e35 = new Edge(n3, n5, 231);
		Edge e36 = new Edge(n3, n6, 254);
		Edge e37 = new Edge(n3, n7, 278);
		Edge e38 = new Edge(n3, n8, 396);
		n3.addEdges(e34, e35, e36, e37, e38);

		Edge e45 = new Edge(n4, n5, 254);
		Edge e46 = new Edge(n4, n6, 132);
		Edge e47 = new Edge(n4, n7, 254);
		Edge e48 = new Edge(n4, n8, 398);
		n4.addEdges(e45, e46, e47, e48);

		Edge e56 = new Edge(n5, n6, 213);
		Edge e57 = new Edge(n5, n7, 187);
		Edge e58 = new Edge(n5, n8, 195);
		n5.addEdges(e56, e57, e58);

		Edge e67 = new Edge(n6, n7, 231);
		Edge e68 = new Edge(n6, n8, 213);
		n6.addEdges(e67, e68);

		Edge e78 = new Edge(n7, n8, 213);
		n7.addEdges(e78);

		tree.putEdges(e12, e13, e14, e15, e16, e17, e18, e23, e24, e25, e26, e27, e28, e34, e35, e36, e37, e38, e45,
				e46, e47, e48, e56, e57, e58, e67, e68, e78);

		tree.putNodes(n1, n2, n3, n4, n5, n6, n7, n8);

		return tree;
	}
}
