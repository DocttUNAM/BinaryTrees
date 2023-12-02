/**
 * La clase Nodo representa un nodo en un árbol AVL. Cada nodo contiene un valor, referencias a los
 * nodos hijos izquierdo y derecho, una referencia al padre, y datos relacionados con la altura y el equilibrio.
 *
 * @author [Daniel Hernandez]
 * @version 1.0
 */
package Arboles.ArbolAVL.AVL;
public class Nodo {
    /**
     * Referencia al padre del nodo.
     */
    AVL Padre;
    /**
     * Referencia al subárbol izquierdo del nodo.
     */
    AVL Hizq;

    /**
     * Referencia al subárbol derecho del nodo.
     */
    AVL Hder;

    /**
     * Valor almacenado en el nodo.
     */
    int value;

    /**
     * Factor de equilibrio del nodo.
     */
    int eq;

    /**
     * Altura del subárbol izquierdo del nodo.
     */
    int deepizq;

    /**
     * Altura del subárbol derecho del nodo.
     */
    int deepder;

    /**
     * Altura total del nodo en el árbol AVL.
     */
    int deep;
    /**
     * Construye un nodo con referencias dadas y valores de inicialización.
     *
     * @param Padre    Referencia al padre del nodo.
     * @param Hizq     Referencia al subárbol izquierdo del nodo.
     * @param Hder     Referencia al subárbol derecho del nodo.
     * @param value    Valor almacenado en el nodo.
     * @param eq       Factor de equilibrio del nodo.
     * @param deepizq  Altura del subárbol izquierdo del nodo.
     * @param deepder  Altura del subárbol derecho del nodo.
     */
    public Nodo(AVL Padre, AVL Hizq, AVL Hder, int value, int eq, int deepizq, int deepder) {
        this.Padre = Padre;
        this.Hizq = Hizq;
        this.Hder = Hder;
        this.value = value;
        this.eq = eq;
        this.deepizq = deepizq;
        this.deepder = deepder;
        this.deep = 0;
    }

       /**
     * Construye un nodo con el valor dado.
     *
     * @param value Valor almacenado en el nodo.
     */
    public Nodo(int value) {
        this.value = value;
    }

    /**
     * Obtiene la referencia al padre del nodo.
     *
     * @return La referencia al padre del nodo.
     */
    public AVL getPadre() {
        return this.Padre;
    }

    /**
     * Establece la referencia al padre del nodo.
     *
     * @param Padre La nueva referencia al padre del nodo.
     */
    public void setPadre(AVL Padre) {
        this.Padre = Padre;
    }

    /**
     * Obtiene la referencia al subárbol izquierdo del nodo.
     *
     * @return La referencia al subárbol izquierdo del nodo.
     */
    public AVL getHizq() {
        return this.Hizq;
    }

    /**
     * Establece la referencia al subárbol izquierdo del nodo.
     *
     * @param Hizq La nueva referencia al subárbol izquierdo del nodo.
     */
    public void setHizq(AVL Hizq) {
        this.Hizq = Hizq;
    }

    /**
     * Obtiene la referencia al subárbol derecho del nodo.
     *
     * @return La referencia al subárbol derecho del nodo.
     */
    public AVL getHder() {
        return this.Hder;
    }

    /**
     * Establece la referencia al subárbol derecho del nodo.
     *
     * @param Hder La nueva referencia al subárbol derecho del nodo.
     */
    public void setHder(AVL Hder) {
        this.Hder = Hder;
    }

    /**
     * Obtiene el valor almacenado en el nodo.
     *
     * @return El valor almacenado en el nodo.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Establece el valor almacenado en el nodo.
     *
     * @param value El nuevo valor almacenado en el nodo.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Obtiene el factor de equilibrio del nodo.
     *
     * @return El factor de equilibrio del nodo.
     */
    public int getEq() {
        return this.eq;
    }

    /**
     * Establece el factor de equilibrio del nodo.
     *
     * @param eq El nuevo factor de equilibrio del nodo.
     */
    public void setEq(int eq) {
        this.eq = eq;
    }

    /**
     * Obtiene la altura del subárbol izquierdo del nodo.
     *
     * @return La altura del subárbol izquierdo del nodo.
     */
    public int getDeepizq() {
        return this.deepizq;
    }

    /**
     * Establece la altura del subárbol izquierdo del nodo.
     *
     * @param deepizq La nueva altura del subárbol izquierdo del nodo.
     */
    public void setDeepizq(int deepizq) {
        this.deepizq = deepizq;
    }

    /**
     * Obtiene la altura del subárbol derecho del nodo.
     *
     * @return La altura del subárbol derecho del nodo.
     */
    public int getDeepder() {
        return this.deepder;
    }

    /**
     * Establece la altura del subárbol derecho del nodo.
     *
     * @param deepder La nueva altura del subárbol derecho del nodo.
     */
    public void setDeepder(int deepder) {
        this.deepder = deepder;
    }
}


