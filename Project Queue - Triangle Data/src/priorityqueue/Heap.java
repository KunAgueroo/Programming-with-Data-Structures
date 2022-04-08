//Project 7 Heap
//Harshavardhan Reddipalli
//CS 187
//Spring 2021
//only edit this file
//number of todos : 8
//make notes after this
//extension until 9,finish!!!
package priorityqueue;

import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;// try changing size to fix
  // works without changing size

  /**
   * Constructor for the heap.
   * 
   * @param comparator comparator object to define a sorting order for the heap
   *                   elements.
   * @param isMaxHeap  Flag to set if the heap should be a max heap or a min heap.
   */
  @SuppressWarnings("unchecked")
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
    // TODO1: Implement this method.
    // do you really have to use this?
    // why no initsize?
    // looks alright
    this.numElements = 0;
    this.heap = (T[]) new Object[INIT_SIZE];
    this.isMaxHeap = isMaxHeap;
    this.comparator = comparator;

  }

  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time. Note: When enqueue is called, an entry is placed at the
   * next available index in the array and then this method is called on that
   * index.
   *
   * @param index the index to bubble up
   */
  @SuppressWarnings("unchecked")
  public void bubbleUp(int index) {
    // TODO2: Implement this method.
    // Somehting is going wrong here try to fix
    // find the index of the parent and the parent itself
    //use index formula
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index is out of bounds");
    } else {
      T temp;
      temp = heap[size() - 1];
      while (compare(heap[(index - 1) / 2], temp) < 0 && index > 0) {
        heap[index] = heap[(index - 1) / 2];
        index = (index - 1) / 2;
      }
      heap[index] = temp;

    }
  }

  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time. Note: When remove is called, if there are
   * elements remaining in this the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   */
  @SuppressWarnings("unchecked")
  public void bubbleDown(int index) {
    // TODO3: Implement this method.
    // something might be wrong here too
    // should you create separate methods for the left and right childs?
    //use the formulas you found
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index is out of bounds");
    } else {
      T top;
      top = heap[0];
      int var = 0;
      boolean exc;
      exc = true;
      while (exc) {
        int indexOfChild;
        indexOfChild = (var * 2) + 1;
        if (indexOfChild <= index) {
          T cld;
          cld = heap[(var * 2) + 1];
          if ((var * 2) + 1 + 1 <= index && compare(heap[(var * 2) + 1 + 1], cld) > 0) {
            indexOfChild = (var * 2) + 1 + 1;
            cld = heap[(var * 2) + 1 + 1];
          }
          if (compare(cld, top) > 0) {
            heap[var] = cld;
            var = indexOfChild;
          } else {
            exc = false;
          }
        } else {
          exc = false;
        }
      }
      heap[var] = top;
    }
  }

  /**
   * Test for if the queue is empty.
   * 
   * @return true if queue is empty, false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean isEmpty() {
    boolean isEmpty = false;
    // TODO4: Implement this method.
    // when size is 0
    // looks alright
    if (size() == 0) {
      isEmpty = true;
    } else {
      isEmpty = false;
    }
    return isEmpty;

  }

  /**
   * Number of data elements in the queue.
   * 
   * @return the size
   */
  @SuppressWarnings("unchecked")
  public int size() {
    // TODO5: Implement this method.
    // just the number of elements
    // looks alright
    return numElements;
  }

  /**
   * Compare method to implement max/min heap behavior. It calls the comparae
   * method from the comparator object and multiply its output by 1 and -1 if max
   * and min heap respectively. TODO: implement the heap compare method
   * 
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if
   *         {@code element1 == element2}, negative int otherwise
   */
  @SuppressWarnings("unchecked")
  public int compare(T element1, T element2) {
    //dont need to change
    //useful
    int result = 0;
    int compareSign = -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap
   * without removing the element.
   * 
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  @SuppressWarnings("unchecked")
  public T peek() throws QueueUnderflowException {
    // TODO6: Implement this method.
    // first one
    // looks alright
    if (isEmpty()) {
      throw new QueueUnderflowException("It is empty");
    } else {
      return heap[0];
    }
  }

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority
   * in the heap.
   * 
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  @SuppressWarnings("unchecked")
  public T dequeue() throws QueueUnderflowException {
    T data = null;
    // TODO7: Implement this method.
    // debug says something went wrong
    // focus on this
    //look zy de and enqueue again
    if (isEmpty()) {
      throw new QueueUnderflowException("It is empty");
    } else {
      T temp;
      temp = heap[0];
      if (size() - 1 > 0) {
        heap[0] = heap[size() - 1];
        bubbleDown(size() - 1);
      } else
        heap[size() - 1] = null;
      numElements = size() - 1;

      return temp;
    }
  }

  /**
   * Enqueue the element.
   * 
   * @param the new element
   */
  @SuppressWarnings("unchecked")
  public void enqueue(T newElement) {
    // TODO8: Implement this method.
    // debug says something went wrong
    // focus on this
    // increase the spaces available
    //make a new method?
    int hLength;
    hLength = heap.length;
    if (size() == hLength) {
      T[] tArr;
      tArr = (T[]) new Object[(hLength * 4)];
      for (int j = 0; j < size(); j++)
        tArr[j] = heap[j];
      heap = tArr;
    }
    int newS;
    newS = numElements++;
    heap[newS] = newElement;
    int end;
    end = size() - 1;
    bubbleUp(end);

  }

}