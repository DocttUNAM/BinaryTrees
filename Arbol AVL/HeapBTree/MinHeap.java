package HeapBTree;

public class MinHeap {
    private final Nodo[] heap;
    private int tamanio;
    private final int capacidad;

    public MinHeap(int capacidad) {
        this.capacidad = capacidad;
        this.tamanio = 0;
        this.heap = new Nodo[capacidad + 1]; // El índice 0 no se utiliza para simplificar los cálculos
    }

    public boolean esVacio() {
        return tamanio == 0;
    }

    public void agregarNodo(Nodo nodo) {
        if (tamanio < capacidad) {
            tamanio++;
            heap[tamanio] = nodo;
            ajustarArriba(tamanio);
        } else {
            System.out.println("El montículo está lleno, no se puede agregar más nodos.");
        }
    }

    public Nodo eliminarMin() {
        if (tamanio > 0) {
            Nodo nodoMin = heap[1];
            heap[1] = heap[tamanio];
            tamanio--;
            ajustarAbajo(1);
            return nodoMin;
        } else {
            System.out.println("El montículo está vacío, no se puede eliminar el mínimo.");
            return null;
        }
    }

    private void ajustarAbajo(int indice) {
        int hijoIzquierdo = 2 * indice;
        int hijoDerecho = 2 * indice + 1;
        int indiceMenor = indice;

        if (hijoIzquierdo <= tamanio && heap[hijoIzquierdo].getValor() < heap[indiceMenor].getValor()) {
            indiceMenor = hijoIzquierdo;
        }

        if (hijoDerecho <= tamanio && heap[hijoDerecho].getValor() < heap[indiceMenor].getValor()) {
            indiceMenor = hijoDerecho;
        }

        if (indice != indiceMenor) {
            intercambiarNodos(indice, indiceMenor);
            ajustarAbajo(indiceMenor);
        }
    }

    public void eliminarRaiz() {
        if (tamanio > 0) {
            heap[1] = heap[tamanio];
            tamanio--;
            ajustarAbajo(1);
        } else {
            System.out.println("El montículo está vacío, no se puede eliminar la raíz.");
        }
    }

    private void ajustarArriba(int indice) {
        while (indice > 1 && heap[indice].getValor() < heap[indice / 2].getValor()) {
            intercambiarNodos(indice, indice / 2);
            indice = indice / 2;
        }
    }

    private void intercambiarNodos(int i, int j) {
        Nodo temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void imprimirMonticuloConNiveles() {
        for (int i = 1; i <= tamanio; i++) {
            System.out.print("Nivel " + obtenerNivel(i) + ": " + heap[i].getValor());

            int hijoIzquierdo = 2 * i;
            int hijoDerecho = 2 * i + 1;

            if (hijoIzquierdo <= tamanio) {
                System.out.print(", Hijo Izquierdo: " + heap[hijoIzquierdo].getValor());
            }

            if (hijoDerecho <= tamanio) {
                System.out.print(", Hijo Derecho: " + heap[hijoDerecho].getValor());
            }

            System.out.println();
        }
    }

    private int obtenerNivel(int indice) {
        return (int) Math.floor(Math.log(indice) / Math.log(2)) + 1;
    }

    public void imprimirMonticuloGraficamente() {
        imprimirMonticuloGraficamente(1, 0);
    }

    private void imprimirMonticuloGraficamente(int indice, int espacio) {
        if (indice <= tamanio) {
            imprimirMonticuloGraficamente(2 * indice + 1, espacio + 4);

            for (int i = 0; i < espacio; i++) {
                System.out.print(" ");
            }

            System.out.println(heap[indice].getValor());

            imprimirMonticuloGraficamente(2 * indice, espacio + 4);
        }
    }
}
