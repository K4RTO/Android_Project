package com.example.ga_23s2.model.tree;

public class LinkedNode<T> {
  T value;
  LinkedNode<T> prev;
  LinkedNode<T> next;

  public LinkedNode(T value) {
    this.value = value;
  }

  public LinkedNode() {
    this.value = null;
  }
}
