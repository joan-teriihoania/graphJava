package graphImplementation; 

import graph.*;

public class TestGraphImpl {
  public static void main (String[] args){
    IncidenceArrayGraph graph = new IncidenceArrayGraph (10); 
    Vertex v1 = new Vertex();
    Vertex v2 = new Vertex();
    Vertex v3 = new Vertex();

    graph.addVertex(v1);
    graph.addVertex(v2);
    graph.addVertex(v3);
    
    EdgeKind kind = EdgeKind.DIRECTED; 

    System.out.println("graph is connected ? (Should return false): " + graph.isConnected()); //Should return false 

    graph.addEdge(v1, v2, kind); 
    System.out.println("v1 is connected to v2 ? (Should return true): " + graph.isConnected(v1, v2)); //Should return true 

    System.out.println("v1 is connected to v3 ? : (Should return false) " + graph.isConnected(v1, v3)); //Should return false 

    graph.addEdge(v2, v3, kind);
    System.out.println("v1 is connected to v3 ? (Shloud return true) : " + graph.isConnected(v1, v3)); //Should return true 

    System.out.println("List of vertices : " + graph.getEdges().toString()); //Show the list of vertices
  }
}
