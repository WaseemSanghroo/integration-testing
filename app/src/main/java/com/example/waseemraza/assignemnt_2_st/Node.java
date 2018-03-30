package com.example.waseemraza.assignemnt_2_st;

/**
 * Created by Waseem Raza on 30/03/2018.
 */
public class Node<T>
{
    public T data;
    public Node<T> left, right;

    public Node(T data, Node<T> l, Node<T> r)
    {
        left = l; right = r;
        this.data = data;
    }

    public Node(T data)
    {
        this(data, null, null);
    }

    public String toString()
    {
        return data.toString();
    }
} //end of Node
