package binaryTree;

import java.util.ArrayList;
import java.util.Comparator;

import queue.Queue;

public class Heap<T> {

    private ArrayList<T> items;
    private Comparator<T> comparator;
    
    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
        items = new ArrayList<>();
    }
    
    public void insert(T t) {
        items.add(t);
        shiftUp();
    }
    
    public T delete() {
        
        if (items.isEmpty()) return null;
        
        if (items.size() == 1) return items.remove(0);
        
        T t = items.remove(0);
        items.set(0, items.remove(items.size()-1));
        shiftDown();
        return t;
    }
    
    private void shiftDown() {
        int parentIndex = 0;
        int childIndex = 2*parentIndex+1;
        
        while (childIndex < items.size()) {
            int max = childIndex, rightChild = childIndex+1;
            
            if (rightChild < items.size())
                if (comparator.compare(items.get(rightChild), items.get(childIndex)) > 0) max++;
            
            if (comparator.compare(items.get(parentIndex), items.get(max)) < 0) {
                T temp = items.get(parentIndex);
                items.set(parentIndex, items.get(max));
                items.set(max, temp);
                parentIndex = max;
                childIndex = 2*parentIndex+1;
            } else break;
        }
        
    }
    
    private void shiftUp() {
        int childIndex = items.size() -1;
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;
            T childItem = items.get(childIndex);
            T parentItem = items.get(parentIndex);
            if (comparator.compare(childItem, parentItem) > 0) {
                items.set(childIndex, parentItem);
                items.set(parentIndex, childItem);
                childIndex = parentIndex;
            } else break;
        }
    }
    
    public void breathFirstSearch() {
        Queue<T> queue = new Queue<>();
        int index = 0;
        queue.enqueue(items.get(index));
        
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
            
            if (index * 2 + 1 < items.size()) queue.enqueue(items.get(index * 2 + 1));
            if (index * 2 + 2 < items.size()) queue.enqueue(items.get(index * 2 + 1));
            
            index++;
        }
    }
    
    public void preOrder() {
        preOrder(items.get(0), 0);
    }
    
    public void inOrder() {
        inOrder(items.get(0), 0);
    }
    
    public void postOrder() {
        postOrder(items.get(0), 0);
    }
    
    public void preOrder(T t, int index) {
        if (t == null) return;
        
        System.out.println(t);
        if (index * 2 + 1 < items.size()) preOrder(items.get(index * 2 + 1), index * 2 + 1);
        if (index * 2 + 2 < items.size()) preOrder(items.get(index * 2 + 2), index * 2 + 2);
        
    }
    
    public void inOrder(T t, int index) {
        if (t == null) return;
        
        if (index * 2 + 1 < items.size()) inOrder(items.get(index * 2 + 1), index * 2 + 1);
        System.out.println(t);
        if (index * 2 + 2 < items.size()) inOrder(items.get(index * 2 + 2), index * 2 + 2);
        
    }
    
    public void postOrder(T t, int index) {
        if (t == null) return;
        
        if (index * 2 + 1 < items.size()) postOrder(items.get(index * 2 + 1), index * 2 + 1);
        if (index * 2 + 2 < items.size()) postOrder(items.get(index * 2 + 2), index * 2 + 2);
        System.out.println(t);
        
    }
    
    
}
