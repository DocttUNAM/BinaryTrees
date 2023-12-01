package HEAP;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private final List<HeapNode> nodo;

    public MinHeap() {
        nodo = new ArrayList<>();
    }

    public void Insertar(int Valor) {
        HeapNode newNode = new HeapNode(Valor);
        nodo.add(newNode);
        HeapUtils.heapifyUpMin(nodo, nodo.size() - 1);
    }

    public int extractMin() {
        if (nodo.isEmpty()) {
            throw new IllegalStateException("Heap vacio");
        }

        int minValue = nodo.get(0).getDato();
        int lastIndex = nodo.size() - 1;

        // Reemplaza la raiz con el ultimo nodo y remueve el nuevo ultimo nodo
        nodo.set(0, nodo.get(lastIndex));
        nodo.remove(lastIndex);

        HeapUtils.heapifyDownMin(nodo, 0);

        return minValue;
    }

    public void printHeap() {
        HeapUtils.printHeap(nodo, "Min Heap");
    }
}


