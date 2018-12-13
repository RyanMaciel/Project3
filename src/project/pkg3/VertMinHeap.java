package project.pkg3;

class VertMinHeap{
    private Vertex[] heap;
    private int max;
    private int size;
    
    /**
     * Class constructor
     * @param h an array of Vertices to be built into a heap
     * @param m the max size of the heap
     * @param s the current size of the heap
     */
    public VertMinHeap(Vertex[] h){
        heap = h;
        max = h.length;
        size = h.length;
    }
    
    /**
     * @return the current size of the heap
     */
    public int size(){
        return size;
    }
    
    /**
     * Builds a heap from the array so that it is partially ordered.
     */
    public void buildHeap(){
        //Starting from the last internal node, sift every internal node up
        for(int i = size/2 - 1; i >= 0; i--)
            siftup(i);
    }
    
    /**
     * @param pos the position to be checked
     * @return true if the node at position pos is a leaf, false otherwise
     */
    public boolean isLeaf(int pos){
        return (pos >= size/2) && (pos < size);
    }
    
    /**
     * Swaps the element at position pos with one of its children until its weight is 
     * greater than its children and less than its parent
     * @param pos the position of the node to be moved
     */
    private void siftup(int pos){
        while(!isLeaf(pos)){
            //Pointer to the left child
            int j = 2*pos + 1;
            
            //Moves pointer to the greater of the children
            if((j < (size - 1)) && (heap[j].d > heap[j + 1].d))
                j++;
            
            //If its position is already valid, rerturn
            if(heap[pos].d <= heap[j].d) return;
            
            //Otherwise swap the element with the greater of its children
            Vertex temp = heap[pos];
            heap[pos] = heap[j];
            heap[j] = temp;
            
            //Update position
            pos = j;
        }
    }
    
    /**
     * Removes the smallest element from the heap and then makes the remaining elements into a valid minheap
     * @return the element with the smallest weight
     */
    public Vertex removeMin(){
        //Swap the root with the last element in the heap
        Vertex temp = heap[0];
        heap[0] = heap[--size];
        heap[size] = temp;
        
        //Make the tree valid again
        if(size != 0)
            siftup(0);
        
        return heap[size];
    }
}