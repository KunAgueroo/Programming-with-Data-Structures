//Harshavardhan Reddipalli
//CS 187 Spring 2021
//Graph
//No.of TODOS:9
//read zybooks
//Project 8
//works
//tests run
package graph;

import java.util.ArrayList;

/**
 * This class implements general operations on a graph as specified by
 * UndirectedGraphADT. It implements a graph where data is contained in Vertex
 * class instances. Edges between verticies are unweighted and undirected. A
 * graph coloring algorithm determines the chromatic number. Colors are
 * represented by integers. The maximum number of vertices and colors must be
 * specified when the graph is instantiated. You may implement the graph in the
 * manner you choose. See instructions and course material for background.
 */

public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T> {
  // private class variables here.

  private int MAX_VERTICES;
  private int MAX_COLORS;
  private ArrayList<ArrayList<Vertex<T>>> vtx;

  // TODO: Declare class variables here.
  // easy create new array list
  // private or public?
  // works

  /**
   * Initialize all class variables and data structures.
   */
  public UndirectedUnweightedGraph(int maxVertices, int maxColors) {
    MAX_VERTICES = maxVertices;
    MAX_COLORS = maxColors;
    vtx = new ArrayList<ArrayList<Vertex<T>>>();

    // TODO: Implement the rest of this method.
    // easy initialise the before one
    // see how to initiliase array again
    // works

  }

  /**
   * Add a vertex containing this data to the graph. Throws Exception if trying to
   * add more than the max number of vertices.
   */
  public void addVertex(T data) throws Exception {
    // TODO: Implement this method.
    // create size
    // seems pretty straighforward
    // create temp arraylist
    // works
    int size;
    size = vtx.size();
    if (size >= MAX_VERTICES) {
      throw new Exception("Trying to add more than the max number of vertices");
    }
    ArrayList<Vertex<T>> Tem;
    Tem = new ArrayList<Vertex<T>>();
    Vertex<T> data1;
    data1 = new Vertex<T>(data);
    Tem.add(data1);
    vtx.add(Tem);
  }

  /**
   * Return true if the graph contains a vertex with this data, false otherwise.
   */
  public boolean hasVertex(T data) {
    for (ArrayList<Vertex<T>> Current : vtx) {
      T currentData = Current.get(0).getData();
      if (currentData.equals(data)) {
        return true;
      }
    }
    return false;
    // TODO: Implement this method.
    // easy
    // works
  }

  /**
   * Add an edge between the vertices that contain these data. Throws Exception if
   * one or both vertices do not exist.
   */

  public void addEdge(T data1, T data2) throws Exception {
    // TODO: Implement this method.
    // throw 2 exceptions
    // method similar to the one above?
    // look on style once done
    // works
    //new method works too
    if (hasVertex(data1) == false) {
      throw new Exception("data1 does not exist");

    }
    if (hasVertex(data2) == false) {
      throw new Exception("data2 does not exist");
    }
    Vertex<T> o2;
    o2 = Lvtx(data2).get(0);
    Vertex<T> o1;
    o1 = Lvtx(data1).get(0);
    Lvtx(data1).add(o2);
    Lvtx(data2).add(o1);
  }

  /**
   * Get an ArrayList of the data contained in all vertices adjacent to the vertex
   * that contains the data passed in. Returns an array of zero length if no
   * adjacencies exist in the graph. Throws Exception if a vertex containing the
   * data passed in does not exist.
   */
  public ArrayList<T> getAdjacentData(T data) throws Exception {
    if (hasVertex(data) == false){
      throw new Exception("Vertex containing the data passed in does not exist");
    }
    ArrayList<T> xLis;
    xLis = new ArrayList<>();
    ArrayList<Vertex<T>> dataX;
    dataX= Lvtx(data);
    for (Vertex<T> Current : dataX) {
      T cData = Current.getData();
      xLis.add(cData);
    }
    xLis.remove(0);
    return xLis;

    // TODO: Implement this method.
    // seems pretty easy too
    // iterate?
    // should be zero lenght!!!
    // initially got error with length check that again
    // works
  }

  /**
   * Returns the total number of vertices in the graph.
   */
  public int getNumVertices() {
    int vsize = vtx.size();
    return vsize;

    // TODO: Implement this method.
    // easy
    // better create another variable for safety
    // works
  }

  /**
   * Returns the total number of edges in the graph.
   */
  public int getNumEdges() {
    int tempo = 0;
    int numEdges;
    for (ArrayList<Vertex<T>> Current : vtx) {
      int Csize = Current.size();
      tempo = tempo + (Csize - 1);
    }
    numEdges = (tempo / 2);
    return numEdges;

    // TODO: Implement this method.
    // easy again
    // pretty much straightforward no need to spend a lot of time on this
    // works
  }

  /**
   * Returns the minimum number of colors required for this graph as determined by
   * a graph coloring algorithm. Throws an Exception if more than the maximum
   * number of colors are required to color this graph.
   */
  public int getChromaticNumber() throws Exception {
    if (MAX_COLORS < MAX_VERTICES) {
      throw new Exception("More than the maximum number of colors are required to color this graph");
    }
    int Lcolour;
    Lcolour = -1;
    int colour;
    colour = -1;
    int minNum;
    for (ArrayList<Vertex<T>> Current : vtx) {
      int gColour = Current.get(0).getColor();
      if (gColour == -1) {
        colour = colourL(Current);
        Vertex<T> gcColour;
        gcColour = Current.get(0);
        gcColour.setColor(colour);
        if (colour > Lcolour) {
          Lcolour = colour;
        }
      }
    }
    minNum = Lcolour + 1;
    return minNum;
  }
  // TODO: Implement this method.
  //hard spend a lot of time on this
  //create one more method to get
  //works

  private int colourL(ArrayList<Vertex<T>> nex) {
    int mColour = -1;
    for (Vertex<T> Current : nex) {
      int gColour = Current.getColor();
      if (gColour > mColour) {
        mColour = Current.getColor();
      }
    }
    mColour = mColour + 1;
    return mColour;
  }

  public ArrayList<Vertex<T>> Lvtx(T data) {
    for (ArrayList<Vertex<T>> Current : vtx) {
      T currentData = Current.get(0).getData();
      if (currentData.equals(data)) {
        return Current;
      }
    }
    return null;
  }

}