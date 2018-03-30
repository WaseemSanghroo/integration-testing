package com.example.waseemraza.assignemnt_2_st;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Waseem Raza on 30/03/2018.
 */
public class BSTTest {
    @Test
    public void insert() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        Integer input = 1;
        bst.insert(input);

        assertEquals(input,bst.root.data);
    }

    @Test
    public void search() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);

        boolean output = bst.search(3);
        assertTrue(output);
    }

    @Test
    public void delete() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);

        bst.delete(2);
        boolean output = bst.search(2);
        assertFalse(output);
    }

    @Test
    public void preOrderTraversal() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);

        String preOrderInput = "2134";
        bst.preOrderTraversal();
        String preOrder = bst.toString();
        assertEquals(preOrderInput,preOrder);
    }

    @Test
    public void inOrderTraversal() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);

        String preOrderInput = "1234";
        String preOrder = bst.toString();
    }

    @Test
    public void height() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);

        int height = bst.height();
        assertEquals(height,2);
    }

    @Test
    public void countLeaves() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);

        int height = bst.countLeaves();
        assertEquals(height,2);
    }

    @Test
    public void diameter() throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);

        int diameter = bst.diameter();
        assertEquals(diameter,4);
    }
}