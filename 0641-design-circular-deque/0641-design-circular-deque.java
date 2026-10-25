class MyCircularDeque {
    private int[] a; //array
    private int f; //front
    private int r; //rear
    private int size; //current size
    public MyCircularDeque(int k) {
        size = 0;
        f = 0;
         r = k - 1;
         a = new int[k];
    }
    
    public boolean insertFront(int value) {
        if(size == a.length)
            return false;
        if(f == 0)
            f = a.length;
        a[--f] = value;
        size ++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if(size == a.length)
            return false;
        if(r == a.length-1)
            r = -1;
        a[++r] = value;
        size++;
        return true;
    }
    
    public boolean deleteFront() {
        if(size == 0)
            return false;
        if(f == a.length-1)
            f = 0;
        else
            f++;
        size--;
        return true;
    }
    
    public boolean deleteLast() {
        if(size == 0)
            return false;
        if(r == 0)
            r = a.length-1;
        else
            r--;
        size--;
        return true;
    }
    
    public int getFront() {
        if(size == 0)
            return -1;
        return a[f];
    }
    
    public int getRear() {
        if(size == 0)
            return -1;
        return a[r];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == a.length;
    }
}

