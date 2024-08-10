package com.example.ga_23s2.model.tree;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {
  LinkedNode<T> head;
  LinkedNode<T> tail;
  private int size;

  public LinkedList() {
    this.head = new LinkedNode<>();
    this.tail = head;
    this.size = 0;
  }

  public boolean add(LinkedNode<T> node) {
    LinkedNode<T> current = head;
    while (current.next != null) {
      if (current.next.value.equals(node.value)) return false;
      current = current.next;
    }
    current.next = node;
    node.prev = current;
    tail = node;
    size += 1;
    return true;
  }

  public boolean removeValue(T value) {
    LinkedNode<T> current = head;
    while (current.next != null) {
      if (current.next.value.equals(value)) {
        current.next = current.next.next;
        if (current.next != null) {
          current.next.prev = current;
        } else {
          // If the removed node was the last node, update the tail
          tail = current;
        }
        size--;
        return true;
      }
      current = current.next;
    }
    return false; // Value not found
  }

  public int size() {
    return size;
  }

  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    LinkedNode<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    return current.value;
  }

  public List<T> toList() {
    List<T> list = new ArrayList<>();
    LinkedNode<T> current = head;
    while (current.next != null) {
      list.add(current.next.value);
      current = current.next;
    }
    return list;
  }

  public List<T> toListReverse() {
    List<T> list = new ArrayList<>();
    LinkedNode<T> current = tail;
    while (current != null && current.value != null) {
      list.add(current.value);
      current = current.prev;
    }
    return list;
  }

  public boolean contains(T valueToFind) {
    LinkedNode<T> current = head;
    while (current.next != null) {
      if (current.next.value.equals(valueToFind)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }
}
