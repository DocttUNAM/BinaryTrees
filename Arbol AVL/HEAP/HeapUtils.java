package HEAP;
import java.util.List;

public class HeapUtils {
    public static void printHeap(List<HeapNode> nodes, String heapType) {
        if (nodes.isEmpty()) {
            System.out.println(heapType + " vacio");
        } else {
            System.out.println(heapType + ":");
            printHeapRecursive(nodes, 0, 0);
        }
    }

    private static void printHeapRecursive(List<HeapNode> nodes, int index, int depth) {
        if (index < nodes.size()) {
            printHeapRecursive(nodes, 2 * index + 2, depth + 1); // imprime el subarbol derecho
            System.out.print("  ".repeat(depth)); // Identacion basada en la profundidad del arbol
            System.out.println(nodes.get(index).getDato());
            printHeapRecursive(nodes, 2 * index + 1, depth + 1); // imprime el subarbol izquierdo
        }
    }

    public static void heapifyUpMax(List<HeapNode> nodes, int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (nodes.get(index).getDato() > nodes.get(parentIndex).getDato()) {
                swapear(nodes, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public static void heapifyDownMax(List<HeapNode> nodes, int index) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < nodes.size() && nodes.get(leftChild).getDato() > nodes.get(largest).getDato()) {
            largest = leftChild;
        }

        if (rightChild < nodes.size() && nodes.get(rightChild).getDato() > nodes.get(largest).getDato()) {
            largest = rightChild;
        }

        if (largest != index) {
            swapear(nodes, index, largest);
            heapifyDownMax(nodes, largest);
        }
    }

    public static void heapifyUpMin(List<HeapNode> nodes, int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (nodes.get(index).getDato() < nodes.get(parentIndex).getDato()) {
                swapear(nodes, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public static void heapifyDownMin(List<HeapNode> nodes, int index) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < nodes.size() && nodes.get(leftChild).getDato() < nodes.get(largest).getDato()) {
            largest = leftChild;
        }

        if (rightChild < nodes.size() && nodes.get(rightChild).getDato() < nodes.get(largest).getDato()) {
            largest = rightChild;
        }

        if (largest != index) {
            swapear(nodes, index, largest);
            heapifyDownMax(nodes, largest);
        }
    }

    private static void swapear(List<HeapNode> nodes, int i, int j) {
        HeapNode temp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, temp);
    }
}

