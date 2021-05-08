package graphImplementation;

import graph.*;

public class IncidenceArrayGraph implements Graph {
    private Vertex[] vertices;
    private Edge[] edges;
    private Edge[][] incidenceGraph;
    private int nbMaxVertices;

    public IncidenceArrayGraph (int nbMaxVertices) {
        this.vertices = new Vertex[nbMaxVertices];
        this.edges = new Edge[nbMaxVertices];
        this.incidenceGraph = new Edge[nbMaxVertices][nbMaxVertices];
        this.nbMaxVertices = nbMaxVertices;
    }

    public void addVertex(Vertex v){
        int i=0;
        boolean ajoute = false;
        while(i<this.vertices.length && !ajoute){
            if(this.vertices[i] == null){
                this.vertices[i] = v;
                ajoute = true;
            }
            i++;
        }
        if(ajoute){
            System.out.println("Vertex added");
        }
        else{
            System.out.println("Vertex not added");
        }
    }

    public void addEdge (Vertex v1, Vertex v2, EdgeKind kind) {
      Edge e; 
      if (kind == EdgeKind.DIRECTED) {
        e = new DirectedEdge(v1, v2); 
      } else {
        Vertex[] tmp = new Vertex [2]; 
        tmp [0] = v1; 
        tmp [1] = v2; 
        e = new UndirectedEdge(tmp); 
      }
      edges[e.getId()] = e; 

      int i = 0; 
      while (incidenceGraph[v1.getId()][i] != null) {
        i ++; 
      }
      incidenceGraph[v1.getId()][i] = e; 

      i = 0; 
      while (incidenceGraph[v2.getId()][i] != null) {
        i ++; 
      }
      incidenceGraph[v2.getId()][i] = e;
    }

    public Edge[] getEdges () {
        return this.edges;
    }

    //Return the other Vertex of a Edge
    private Vertex getOtherVertex (Edge e, Vertex v) {
        if (e.getEnds()[0].getId() == v.getId()) {
            return e.getEnds()[1];
        }
        return e.getEnds()[0];
    }

    public Edge[] getEdges (Vertex v1, Vertex v2) {
        Edge[] neighbourEdges  = new Edge[this.nbMaxVertices];
        Vertex [] tmp;
        int v1ID = v1.getId(), v2ID = v2.getId();

        int i = 0; 
        for (Edge neigh : this.incidenceGraph[v1ID]) { //We check in v1 neighbour
            if (v2ID == this.getOtherVertex(neigh, v1).getId()) {
              neighbourEdges[i] = neigh;
              i ++; 
            }
        }
        return neighbourEdges;
    }

    public Edge[] getNeighborEdges(Vertex v){
        return this.incidenceGraph[v.getId()];
    }

    public Vertex[] getVerdices(){
        return this.vertices;
    }

    public boolean isConnected(Vertex v1, Vertex v2){
        if(v1 == v2){
            return true;
        }

        int etat[] = new int[this.nbMaxVertices];
        for(int i = 0; i<this.nbMaxVertices ; i++){
            etat[i] = 0;
        }
        boolean found = false;
        int current = v1.getId();
        etat[current] = 1;

        while(etat[v2.getId()] != 1 && current != -1){
            int i = 0;
            while(i<this.nbMaxVertices && this.incidenceGraph[current][i] != null){
                Vertex v = getOtherVertex(this.incidenceGraph[current][i], this.vertices[current]);
                if(etat[v.getId()] == 0){
                    etat[v.getId()] = 1;
                }
                i++;
            }
            etat[current] = 2;

            //Find the first with a 1;
            boolean found1 = false;
            int j = 0;
            current = -1;
            while(j < etat.length && !found1){
                if(etat[j] == 1){
                    current = j;
                    found1 = true;
                }
                j++;
            }
        }
        return etat[v2.getId()] == 1; 
    }

    public boolean isConnected(){
        boolean res = true;
        int i=1;
        while(i<this.nbOfVertices() && res){
            res = this.isConnected(this.vertices[0], this.vertices[i]);
            i++;
        }

        return res;
    }

    public int nbOfEdges(){
        return this.edges.length;
    }

    public int nbOfVertices(){
        return this.vertices.length;
    }

}

