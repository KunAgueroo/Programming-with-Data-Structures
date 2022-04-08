package app;

import java.util.Iterator;
import java.util.List;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertFirst(T elem) {
    if (elem == null) {
      throw new NullPointerException("Element is NULL");
    }
    Node<T> node = new Node<T>(elem, head);
    head = node;
    this.size++;
  }

  @Override
  public void insertLast(T elem) {
    if (elem == null) {
      throw new NullPointerException("Element is NULL");
    }
    Node<T> node = new Node<T>(elem, null);
    if (size == 0) {
      head = node;
    } else {
      insertLastHelper(node, head, size);
    }
    this.size++;

  }

  private void insertLastHelper(Node<T> node, Node<T> cur, int index) {
    if (index == 1) {
      cur.setNext(node);
      return;
    } else {
      insertLastHelper(node, cur.getNext(), --index);
    }
  }

  @Override
  public void insertAt(int index, T elem) {
    if (elem == null) {
      throw new NullPointerException("Element is NULL");
    }
    if (index > size) {
      throw new IndexOutOfBoundsException("Index is out of bounds");
    }
    if (index == 0) {
      insertFirst(elem);
    } else {
      Node<T> node = new Node<T>(elem, null);
      insertAtHelper(node, head, index);
      this.size++;
    }
  }

  private void insertAtHelper(Node<T> node, Node<T> cur, int index) {
    if (index == 1) {
      node.setNext(cur.getNext());
      cur.setNext(node);
      return;
    } else {
      insertAtHelper(node, cur.getNext(), --index);
    }
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      throw new IllegalStateException("Is Empty");
    }
    return removeAt(0);
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      throw new IllegalStateException("Is Empty");
    }
    return removeAt(size - 1);
  }

  @Override
  public T removeAt(int i) {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException();
    }
    T elem;
    if (i == 0) {
      elem = head.getData();
      head = head.getNext();
    } else {
      elem = removeHelper(head, i);
    }
    size--;
    return elem;
  }

  private T removeHelper(Node<T> curr, int i) {
    if (i == 1) {
      T elem = curr.getNext().getData();
      curr.setNext(curr.getNext().getNext());
      return elem;
    }
    return removeHelper(curr.getNext(), i - 1);
  }

  @Override
  public T getFirst() {
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    return get(0);
  }

  @Override
  public T getLast() {
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    return get(size - 1);
  }

  @Override
  public T get(int i) {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException();
    }
    return getHelper(head, i);
  }

  private T getHelper(Node<T> curr, int i) {
    if (i == 0)
      return curr.getData();
    return getHelper(curr.getNext(), i - 1);
  }

  @Override
  public void remove(T elem) {
    if (elem == null) {
      throw new NullPointerException("Element is NULL");
    }
    if (!removeHelper(elem, head)) {
      throw new ItemNotFoundException("Item Not Found");
    }
  }

  private boolean removeHelper(T elem, Node<T> curr) {
    if (head.getData() == elem) {
      head = head.getNext();
      size--;
      return true;
    } else if (curr.getNext() == null) {
      return false;
    } else if (curr.getNext().getData() == elem) {
      curr.setNext(curr.getNext().getNext());
      size--;
      return true;
    } else {
      return removeHelper(elem, curr.getNext());
    }
  }

  @Override
  public int indexOf(T elem) {
    if (elem == null) {
      throw new NullPointerException("Element is NULL");
    }
    return indexOfHelper(elem, head, 0);

  }

  private int indexOfHelper(T target, Node<T> cur, int count) {
    if (cur == null) {
      return -1;
    }
    if (cur.getData() == target) {
      return count;
    } else {
      cur = cur.getNext();
      count++;
      return indexOfHelper(target, cur, count);
    }
  }

  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  public Iterator<T> iterator() {
    return this.iterator();
  }

}
