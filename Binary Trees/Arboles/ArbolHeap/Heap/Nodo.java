package Arboles.ArbolHeap.Heap;

/**
 * La clase Nodo representa un nodo en la estructura de un árbol heap.
 * Cada nodo contiene un valor entero, así como referencias a su padre, hijo derecho e hijo izquierdo.
 */
public class Nodo {
    /**
     * El valor almacenado en el nodo.
     */
    public int value;

    /**
     * Referencia al nodo padre.
     */
    public Heap Padre;

    /**
     * Referencia al nodo hijo derecho.
     */
    public Heap Hder;

    /**
     * Referencia al nodo hijo izquierdo.
     */
    public Heap Hizq;

    /**
     * Constructor de la clase Nodo con especificación del valor, padre, hijo derecho e hijo izquierdo.
     *
     * @param value El valor del nodo.
     * @param Padre El nodo padre.
     * @param Hder  El nodo hijo derecho.
     * @param Hizq  El nodo hijo izquierdo.
     */
    public Nodo(int value, Heap Padre, Heap Hder, Heap Hizq) {
        this.value = value;
        this.Padre = Padre;
        this.Hder = Hder;
        this.Hizq = Hizq;
    }

    /**
     * Constructor de la clase Nodo con especificación del valor.
     *
     * @param value El valor del nodo.
     */
    public Nodo(int value) {
        this.value = value;
    }

    /**
     * Obtiene el valor del nodo.
     *
     * @return El valor del nodo.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Establece el valor del nodo.
     *
     * @param value El nuevo valor del nodo.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Obtiene el nodo padre.
     *
     * @return El nodo padre.
     */
    public Heap getPadre() {
        return this.Padre;
    }

    /**
     * Establece el nodo padre.
     *
     * @param Padre El nuevo nodo padre.
     */
    public void setPadre(Heap Padre) {
        this.Padre = Padre;
    }

    /**
     * Obtiene el nodo hijo derecho.
     *
     * @return El nodo hijo derecho.
     */
    public Heap getHder() {
        return this.Hder;
    }

    /**
     * Establece el nodo hijo derecho.
     *
     * @param Hder El nuevo nodo hijo derecho.
     */
    public void setHder(Heap Hder) {
        this.Hder = Hder;
    }

    /**
     * Obtiene el nodo hijo izquierdo.
     *
     * @return El nodo hijo izquierdo.
     */
    public Heap getHizq() {
        return this.Hizq;
    }

    /**
     * Establece el nodo hijo izquierdo.
     *
     * @param Hizq El nuevo nodo hijo izquierdo.
     */
    public void setHizq(Heap Hizq) {
        this.Hizq = Hizq;
    }
}

