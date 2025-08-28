package dsa.tree;

import dsa.tree.util.Utility;

public class TreeMain {
    public static void main(String[] args) {
//        System.out.println("preorderTraversal");
//        TreeTraversal.preorderTraversal(Utility.createTree());
//
//        System.out.println();
//        System.out.println("inorderTraversal");
//        TreeTraversal.inorderTraversal(Utility.createTree());
//
//        System.out.println();
//        System.out.println("postorderTraversal");
//        TreeTraversal.postorderTraversal(Utility.createTree());
//
//        System.out.println();
//        System.out.println("BFS");
//        TreeTraversal.BFS(Utility.createTree());
//
//        System.out.println();
//        System.out.println("preorderViaStack");
//        TreeTraversal.preorderViaStack(Utility.createTree());

        System.out.println();
        System.out.println("inorderViaStack");
        TreeTraversal.inorderViaStack(Utility.createTree());

        System.out.println();
        System.out.println("postorderVia2Stacks");
        TreeTraversal.postorderVia2Stacks(Utility.createTree());

    }
}
