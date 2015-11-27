package com.bruskajp.fisttablets.artificialintelligence;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by meliteja on 11/3/15
 */
/***
 *
 * A node which can contain an arbitrary number of children.
 */
public class Node<T> {

    private T data;
    private List<Node<T>> children;

    public Node(T data) {
        this(data, null);
    }


    public Node(T data, List<Node<T>> children) {
        this.data = data;
        this.children = (children == null ?  new LinkedList<Node<T>>() : children );
    }

    /***
     * *
     * Adds a child containing {@code child} to this node.
     *
     * @param childData The data to initialize the child node with.
     * @return The child node.
     */
    public Node<T> addChild(T childData) {
        return addChild(new Node<T>(childData));
    }

    /***
     * Adds a child to this node.
     * @param child The child to add to this node.
     * @return The child node.
     */
    public Node<T> addChild(Node<T> child){
        this.children.add(child);
        return child;
    }


    /***
     * Returns the data stored in this node.
     * @return The data stored in this node.
     */
    public T getData(){
        return data;
    }

    /***
     * *
     * Removes a node from this node's list of children.
     *
     * @param child The child node to remove.
     * @return The removed node, null if the child is not in the list of
     * children.
     */
    public Node<T> removeChild(Node<T> child) {
        return children.remove(child) ? child : null;
    }

    /***
     * *
     * Returns the list of this node's children.
     *
     * @return The list of this node's children.
     */
    public List<Node<T>> getChildren() {
        return this.children;
    }

    // -----------------------------------
    // Methods for displaying the tree.
    // -----------------------------------

    @Override
    public String toString() {
        String str = data.toString();
        return str;
    }

    /***
     * *
     * Prints the entire tree below and including this node.
     */
    public void printTree() {
        printTree(0);
    }

    /***
     * Prints the entire tree by recursively calling printTree on all nodes.
     * @param numTabs The number of tabs to print this iteration.
     */
    private void printTree(int numTabs) {
        printTabs(numTabs);
        System.out.println(this.toString());
        if (children != null) {
            for (Node<T> node : children) {
                node.printTree(numTabs + 1);
            }
        }
    }

    /***
     * Prints out a number of tabs equal to {@code numTabs}.
     * @param numTabs The number of tabs to print.
     */
    private void printTabs(int numTabs) {
        for (int i = 0; i < numTabs; ++i) {
            System.out.print('\t');
        }
    }
}
