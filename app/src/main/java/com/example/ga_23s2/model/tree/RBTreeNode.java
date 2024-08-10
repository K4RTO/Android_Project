package com.example.ga_23s2.model.tree;

public class RBTreeNode<K extends Comparable<K>, V> {

  K key;
  V value;
  RBTreeNode<K, V> parent;
  RBTreeNode<K, V> left;
  RBTreeNode<K, V> right;
  private boolean red = true;

  RBTreeNode() {
    this.key = null;
    this.value = null;
    this.left = null;
    this.right = null;
    this.red = false;
  }

  public RBTreeNode(K key, V value) {
    this.key = key;
    this.value = value;
    this.left = new RBTreeNode<>();
    this.right = new RBTreeNode<>();
  }

  void setBlack() {
    this.red = false;
  }

  void setRed() {
    this.red = true;
  }

  public boolean red() {
    return red;
  }

  public boolean isNotNIL() {
    return key != null;
  }
}
