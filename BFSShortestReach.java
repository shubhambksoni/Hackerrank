import java.util.Iterator;
import java.util.Scanner;

public class BFSShortestReach {
   public static void main(String[] args){
	   Scanner in=new Scanner(System.in);
	   int qu=in.nextInt();
        while(qu>0){
        	int n=in.nextInt();
        	int m=in.nextInt();
            Solution s=new Solution();
        	Graph g=s.new Graph(n);
        	while(m>0){
        		int x=in.nextInt();
        		int y=in.nextInt();
        		g.addEdge(x, y);
        		m--;
        	}
        	int source=in.nextInt();
        	BFS b=s.new BFS(g,source);
        	int[] arr=new int[g.v()];
        	for(int i=1;i<arr.length;i++){
        		int times=-1;
        		int j=i;
        		if(b.marked[i] && i!=source){
        			times=0;
        			while(j!=source){
        				j=b.edgeTo[j];
        				times+=6;
        			}
        		}
        		arr[i]=times;
        		
        	}
        	for(int i=1;i<arr.length;i++){
        		if(i!=source){
        			System.out.print(arr[i]+" ");
        		}
        	}
        	System.out.println();
        	qu--;
        }
   }
       
       class Graph{
           final int s;
           Bag[] adj;
           public Graph(int si){
               s=si+1;
               adj=new Bag[s];
               for(int i=0;i<s;i++){
                   adj[i]=new Bag();
               }
           }
           public void addEdge(int i,int j){
               adj[i].add(j);
               adj[j].add(i);
           }
           
           public int v(){
               return s;
           }
           
           public Iterable<Integer> adj(int v){
               return adj[v];
           }
       }
       class Bag implements Iterable<Integer>{
           
		Node first;
           int size=0;
           final class Node{
               int i;
               Node next;
           }
           public void add(int x){
               Node old=first;
               first=new Node();
               first.i=x;
               first.next=old;
               size++;
           }
           public Iterator<Integer> iterator() {
        		// TODO Auto-generated method stub
        		return new ListIterator();
        	}

        	final class ListIterator implements Iterator<Integer>{
        		private Node current=first;
        		public boolean hasNext(){
        			return current!=null;
        		}
        		public void remove(){}
        		public Integer next(){
        			int i=current.i;
        			current=current.next;
        			return i;
        		}
        	} 
           
       }
       class Queue{
    	   Node first,last;
           final class Node{
               int i;
               Node next;
           }
           public boolean isEmpty(){
        	   return first==null;
           }
           public void enqueue(int i){
        	   Node old=last;
        	   last=new Node();
        	   last.i=i;
        	   if(isEmpty()){
        		   first=last;
        	   }else{
        		   old.next=last;
        	   }
           }
           public int dequeue(){
        	   int k=first.i;
        	   first=first.next;
        	   return k;
           }
           
       }
       class BFS{
    	   public boolean[] marked;
    	   public int[] edgeTo;
    	   private final int s;
    	   
    	   public BFS(Graph g,int i){
    		   s=i;
    		   marked=new boolean[g.v()];
    		   edgeTo=new int[g.v()];
    		   bfs(g,s);
    	   }
    	   private void bfs(Graph g,int s){
    		   Queue q=new Queue();
    		   q.enqueue(s);
    		   marked[s]=true;
    		   while(!q.isEmpty()){
    			   int j=q.dequeue();
    			   for(int w:g.adj[j]){
    				   if(!marked[w]){
    					   q.enqueue(w);
    					   marked[w]=true;
    					   edgeTo[w]=j;
    				   }
    			   }
    		   }
    	   }
       }
}
