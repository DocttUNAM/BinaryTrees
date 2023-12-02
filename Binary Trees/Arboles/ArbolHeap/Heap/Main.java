package Arboles.ArbolHeap.Heap;
import Arboles.ArbolHeap.Heap;

public class Main {
    public static void main(String[] args) {
        Heap T1 = new Heap(new Nodo(1), false);
        System.out.println("Test 0");
        T1.bfs();
        T1.insertar(new Nodo(8));
        System.out.println("Test 1");
        T1.bfs();
        T1.insertar(new Nodo(2));
        System.out.println("Test 2");
        T1.bfs();
        T1.insertar(new Nodo(5));
        System.out.println("Test 3");
        T1.bfs();
        T1.insertar(new Nodo(7));
        System.out.println("Test 4");
        T1.bfs();
        T1.insertar(new Nodo(9));
        System.out.println("Test 5");
        T1.bfs();
        T1.insertar(new Nodo(3));
        System.out.println("Test 6");
        T1.bfs();
        T1.insertar(new Nodo(4));
        System.out.println("Test 7");
        T1.bfs();
        T1.insertar(new Nodo(6));
        System.out.println("Test 8");
        Heap.MainRaiz.bfs();
        System.out.println("Test 9");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();

        System.out.println("Test 10");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();
        System.out.println("Test 11");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();
        System.out.println("Test 12");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();
        System.out.println("Test 13");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();
        System.out.println("Test 14");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();
        System.out.println("Test 15");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();
        System.out.println("Test 16");
        Heap.MainRaiz.extraerRaiz();
        Heap.MainRaiz.bfs();
        System.out.println("Test 17");
        Heap.MainRaiz.extraerRaiz();
        if(Heap.MainRaiz == null){
            System.out.println("Vacio");
        }else
        Heap.MainRaiz.bfs();
    }
}
