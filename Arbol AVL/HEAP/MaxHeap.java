package HEAP;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private final List<HeapNode> nodes;

    public MaxHeap() {
        nodes = new ArrayList<>();
    }

    public void insert(int value) {
        HeapNode newNode = new HeapNode(value);
        nodes.add(newNode);
        HeapUtils.heapifyUpMax(nodes, nodes.size() - 1);
    }

    public int extractMax() {
        if (nodes.isEmpty()) {
            throw new IllegalStateException("Heap vacio");
        }

        int maxValue = nodes.get(0).getDato();
        int lastIndex = nodes.size() - 1;

        // Reemplaza la raiz con el ultimo nodo y remueve el nuevo ultimo nodo
        nodes.set(0, nodes.get(lastIndex));
        nodes.remove(lastIndex);

        HeapUtils.heapifyDownMax(nodes, 0);

        return maxValue;
    }

    public void printHeap() {
        HeapUtils.printHeap(nodes, "Max Heap");
    }
}

