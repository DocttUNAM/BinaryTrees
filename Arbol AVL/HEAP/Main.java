package HEAP;

public class Main {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.Insertar(20);
        minHeap.Insertar(15);
        minHeap.Insertar(8);
        minHeap.Insertar(10);
        minHeap.Insertar(5);
        minHeap.Insertar(7);
        minHeap.Insertar(6);
        minHeap.Insertar(2);
        minHeap.Insertar(9);
        minHeap.Insertar(1);
        //minHeap.insertarNodo(3);
        //minHeap.insertarNodo(7);
        //minHeap.insertarNodo(8);

        minHeap.printHeap();

        System.out.println("Min value: " + minHeap.extractMin());

        minHeap.printHeap();

        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(20);
        maxHeap.insert(15);
        maxHeap.insert(8);
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(7);
        maxHeap.insert(6);
        maxHeap.insert(2);
        maxHeap.insert(9);
        maxHeap.insert(1);
        //maxHeap.insertarNodo(3);
        //maxHeap.insertarNodo(7);
        //maxHeap.insertarNodo(8);

        maxHeap.printHeap();

        System.out.println("Max value: " + maxHeap.extractMax());

        maxHeap.printHeap();
    }
}

