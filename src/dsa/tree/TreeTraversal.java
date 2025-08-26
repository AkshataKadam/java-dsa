package dsa.tree;

import dsa.tree.util.TreeNode;

import java.util.*;

public class TreeTraversal {

    public static void preorderTraversal(TreeNode node) {
        if(node == null) return;

        System.out.print(node.data + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public static void inorderTraversal(TreeNode node) {
        if(node == null) return;

        inorderTraversal(node.left);
        System.out.print(node.data + " ");
        inorderTraversal(node.right);
    }

    public static void postorderTraversal(TreeNode node) {
        if(node == null) return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    public static void BFS(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapperList = new LinkedList<>();

        queue.offer(node);
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> list = new LinkedList<>();

            for(int i = 0; i < levelSize; i++) {
                if(queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                if(queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                list.add(queue.poll().data);
            }
            wrapperList.add(list);
        }
        wrapperList.forEach(System.out::println);
    }

    public static void preorderViaStack(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        if(node == null) return;
        stack.push(node);

        while(!stack.isEmpty()) {
            node = stack.pop();
            list.add(node.data);
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        list.forEach(ele -> System.out.print(ele + " "));
    }

    public static void inorderViaStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;

        if(node == null) return;

        while(true) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if(stack.isEmpty()) break;
                node = stack.pop();
                list.add(node.data);
                node = node.right;
            }
        }
        list.forEach(ele -> System.out.print(ele + " "));
    }

    public static void postorderVia2Stacks(TreeNode node) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> list = new ArrayList<>();

        if(node == null) return;
        stack1.push(node);

        while(!stack1.isEmpty()) {
            node = stack1.pop();
            stack2.push(node);

            if(node.left != null) stack1.push(node.left);
            if(node.right != null) stack1.push(node.right);
        }

        while(!stack2.isEmpty()) list.add(stack2.pop().data);

        list.forEach(ele -> System.out.print(ele + " "));

    }

}
