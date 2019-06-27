// shortened and modified version of N.java from CSCI1933 canvas & Project 3
// used as containers for Riders per Bus

public class RiderList {
    public RiderList() {
        head = new N("no data here",null);
    }
    // Adds the given object to the end of the list.
    // Returns false if the input is null, true otherwise.
    // Modified version of Linked List, content used from Class Canvas.
    public boolean add(Object o){
        if (o==null) return false;
        else {
            N toAdd = new N(o,null);  // creates new N with o as data

            // note this general algorithm for finding a N in the list is used frequently
            N ptr = head;
            while (ptr.getNext() != null) ptr = ptr.getNext();  // update ptr to the next item until it's the last N

            ptr.setNext(toAdd);
            return true;
        }
    }

    // Clears all data from from the list.
    public void clear(){
        head.setNext(null);
    }

    // If the given object is in the list, return true otherwise false. If the input object is null, also return false.
    public boolean contains(Object o){
        N ptr = head;
        while (ptr.getNext() != null) {
            if (ptr.getNext().equals(o)) return true;
            ptr = ptr.getNext();
        }
        return false;  // if no element matching o was found
    }

    // If a rider in the list has the stop, return true otherwise false.
    public boolean containsStop(int stop) {
        N ptr = head;
        Rider r;
        while (ptr.getNext() != null) {
            r = (Rider) ptr.getNext().getData();
            if (r.getDestination()==stop) return true;
            ptr = ptr.getNext();
        }
        return false;  // if no element matching o was found
    }

    // Returns the object at a given index. Returns null if index is out of bounds.
    public Object get(int index){
        if (index >= 0 && index < size()){
            int i=-1;
            N ptr = head;
            while (i<index) {
                ptr = ptr.getNext();
                i++;
            }
            return ptr.getData();
        }
        else return null;

    }

    // Returns true if the list is empty and false otherwise.
    public boolean isEmpty(){
        return head.getNext().equals(null);
    }

    // Removes and returns object at the given index.
    public Object remove(int index){
        Object temp = get(index);
        set(index,null);
        return temp;
    }

    // Replaces the object at the given index with the given object.
    // If the index is out of bounds or the object is null, do nothing.
    public void set(int index, Object o){
        if (index >=0 && index < size() && o != null) {
            N ptr = head;
            int i = -1;
            while (i < index) {
                ptr = ptr.getNext();
                i++;
            }
            ptr.setData(o);
        }
    }

    // Returns the size of the list.
    public int size(){
        int count = 0;
        N ptr = head;
        while (ptr.getNext() != null) {
            count++;
            ptr = ptr.getNext();
        }
        return count;
    }

    private N head;
} // End RiderList
