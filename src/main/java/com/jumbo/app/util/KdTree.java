package com.jumbo.app.util;

import com.jumbo.app.ErrorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KdTree {

    private int dimensions = 2;
    private double bestDistance = 0;

    private Node root;
    private Node best;

    public KdTree(List<Node> nodes) {
        root = makeTree(nodes, 0, nodes.size(), 0);
    }

    public List<Node> findNearest(Node target, int n) {
        if (root == null)
            throw new RuntimeException(ErrorType.TreeIsEmpty.toString());

        List<Node> listOfNodes = new ArrayList<Node>();
        List<String> ignoreList = new ArrayList<String>(Arrays.asList(target.getUuid()));

        for (int i = 0; i < n; i++) {
            best = null;
            bestDistance = 0;

            nearest(root, target, 0, ignoreList);

            listOfNodes.add(best);
            if (best == root)
                break;

            ignoreList.add(best.getUuid());
        }

        return listOfNodes;
    }

    private void nearest(Node root, Node target, int index, List ignoreList) {
        if (root == null)
            return;

        double distance = root.distance(target);

        if ((best == null) || (distance < bestDistance && !ignoreList.contains(root.getUuid()))) {
            bestDistance = distance;
            best = root;
        }

        double dx = root.get(index) - target.get(index);
        index = (index + 1) % dimensions;

        nearest(dx > 0 ? root.left : root.right, target, index, ignoreList);
        if (dx * dx >= bestDistance)
            return;

        nearest(dx > 0 ? root.right : root.left, target, index, ignoreList);
    }

    private Node makeTree(List<Node> nodes, int begin, int end, int index) {
        if (end <= begin)
            return null;

        int n = begin + (end - begin)/2;
        Node node = QuickSelect.select(nodes, begin, end - 1, n, new NodeComparator(index));
        index = (index + 1) % dimensions;
        node.left = makeTree(nodes, begin, n, index);
        node.right = makeTree(nodes, n + 1, end, index);
        return node;
    }

    private static class NodeComparator implements Comparator<Node> {
        private int index;

        private NodeComparator(int index) {
            this.index = index;
        }

        public int compare(Node n1, Node n2) {
            return Double.compare(n1.get(index), n2.get(index));
        }
    }

    public static class Node {

        private double[] coordinates;
        private String uuid;
        private Node left = null;
        private Node right = null;

        public Node(double x, double y, String uuid) {
            this.coordinates = new double[] { x, y };
            this.uuid = uuid;
        }

        double get(int index) {
            return coordinates[index];
        }

        double distance(Node node) {
            double dist = 0;
            for (int i = 0; i < coordinates.length; ++i) {
                double d = coordinates[i] - node.coordinates[i];
                dist += d * d;
            }
            return dist;
        }

        public String getUuid() {
            return uuid;
        }

        public String toString() {
            return String.format("Node %s ( x : %f, y : %f )", uuid, coordinates[0], coordinates[1]);
        }
    }
}
