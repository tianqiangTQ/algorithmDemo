package com.tq.tree;

import lombok.Data;

public class BinaryTreeDemo {
    public static void main(String[] args) {

        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        BinaryTree binaryTree = new BinaryTree(root);

//        System.out.println("前序遍历"); // 1,2,3,5,4
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.inOrder(); // 2,1,5,3,4
//        System.out.println("后序遍历");
//        binaryTree.postOrder(); // 2,5,4,3,1

//        Node s1 = binaryTree.preOrderSearch(5);//4次
//        Node s2 = binaryTree.inOrderSearch(5);//3次
//        Node s3 = binaryTree.postOrderSearch(5);//2次

        binaryTree.preOrder();
        binaryTree.del(3);
        System.out.println("删除后：");
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }

    public void inOrder() {
        if (root != null) {
            root.inOrder();
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        }
    }

    public Node preOrderSearch(int key) {
        if (root != null) {
            return root.preOrderSearch(key);
        }
        return null;
    }

    public Node inOrderSearch(int key) {
        if (root != null) {
            return root.inOrderSearch(key);
        }
        return null;
    }

    public Node postOrderSearch(int key) {
        if (root != null) {
            return root.postOrderSearch(key);
        }
        return null;
    }

    public void del(int key) {
        if (root == null) {
            System.out.println("空树，无法删除~");
            return;
        }
        if (root.getId() == key) {
            root = null;
            return;
        }
        root.del(key);
    }
}

@Data
class Node {
    private int id;
    private Node left;
    private Node right;

    public Node(int id) {
        this.id = id;
    }

    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    public void inOrder() {
        if (left != null) {
            left.inOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.inOrder();
        }
    }

    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.println(this);
    }

    public Node preOrderSearch(int key) {
        System.out.println("前序查找~");
        if (key == this.id) {
            return this;
        }
        Node node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(key);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.preOrderSearch(key);
        }
        return node;
    }

    public Node inOrderSearch(int key) {
        Node node = null;
        if (this.left != null) {
            node = this.left.inOrderSearch(key);
        }
        if (node != null) {
            return node;
        }
        System.out.println("中序查找~");
        if (key == this.id) {
            return this;
        }
        if (this.right != null) {
            node = this.right.inOrderSearch(key);
        }
        return node;
    }

    public Node postOrderSearch(int key) {
        Node node = null;
        if (this.left != null) {
            node = this.left.postOrderSearch(key);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.postOrderSearch(key);
        }
        if (node != null) {
            return node;
        }
        System.out.println("后序查找~");
        if (this.id == key) {
            return this;
        }
        return null;
    }

    public void del(int key) {
        if (this.left != null && this.left.id == key) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.id == key) {
            this.right =  null;
            return;
        }
        if (this.left != null) {
            this.left.del(key);
        }
        if (this.right != null) {
            this.right.del(key);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}
