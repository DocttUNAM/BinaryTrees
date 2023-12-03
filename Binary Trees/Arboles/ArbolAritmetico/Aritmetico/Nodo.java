package Arboles.ArbolAritmetico.Aritmetico;
/**
 * La clase Nodo representa un nodo en un árbol aritmético.
 * Cada nodo contiene un valor, un operador (opcional) y referencias a su nodo padre,
 * hijo derecho e hijo izquierdo.
 */
public class Nodo {

    /**
     * Valor almacenado en el nodo.
     */
    float value;

    /**
     * Operador almacenado en el nodo (puede ser nulo si el nodo representa un valor).
     */
    String op = null;

    /**
     * Referencia al nodo padre.
     */
    Nodo Padre = null;

    /**
     * Referencia al nodo hijo derecho.
     */
    Nodo Hder = null;

    /**
     * Referencia al nodo hijo izquierdo.
     */
    Nodo Hizq = null;

    /**
     * Constructor de la clase Nodo con todos los parámetros.
     *
     * @param value Valor del nodo.
     * @param op Operador del nodo (puede ser nulo).
     * @param Padre Referencia al nodo padre.
     * @param Hder Referencia al nodo hijo derecho.
     * @param Hizq Referencia al nodo hijo izquierdo.
     */
    public Nodo(float value, String op, Nodo Padre, Nodo Hder, Nodo Hizq) {
        this.value = value;
        this.op = op;
        this.Padre = Padre;
        this.Hder = Hder;
        this.Hizq = Hizq;
    }

    /**
     * Constructor de la clase Nodo con solo el valor.
     *
     * @param value Valor del nodo.
     */
    public Nodo(float value) {
        this.value = value;
    }

    /**
     * Constructor de la clase Nodo con solo el operador.
     *
     * @param op Operador del nodo.
     */
    public Nodo(String op) {
        this.op = op;
    }

    /**
     * Obtiene el valor almacenado en el nodo.
     *
     * @return El valor del nodo.
     */
    public float getValue() {
        return this.value;
    }

    /**
     * Establece el valor del nodo.
     *
     * @param value El nuevo valor del nodo.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Obtiene el operador almacenado en el nodo.
     *
     * @return El operador del nodo.
     */
    public String getOp() {
        return this.op;
    }

    /**
     * Establece el operador del nodo.
     *
     * @param op El nuevo operador del nodo.
     */
    public void setOp(String op) {
        this.op = op;
    }

    /**
     * Obtiene la referencia al nodo padre.
     *
     * @return El nodo padre.
     */
    public Nodo getPadre() {
        return this.Padre;
    }

    /**
     * Establece la referencia al nodo padre.
     *
     * @param Padre El nuevo nodo padre.
     */
    public void setPadre(Nodo Padre) {
        this.Padre = Padre;
    }

    /**
     * Obtiene la referencia al nodo hijo derecho.
     *
     * @return El nodo hijo derecho.
     */
    public Nodo getHder() {
        return this.Hder;
    }

    /**
     * Establece la referencia al nodo hijo derecho.
     *
     * @param Hder El nuevo nodo hijo derecho.
     */
    public void setHder(Nodo Hder) {
        this.Hder = Hder;
    }

    /**
     * Obtiene la referencia al nodo hijo izquierdo.
     *
     * @return El nodo hijo izquierdo.
     */
    public Nodo getHizq() {
        return this.Hizq;
    }

    /**
     * Establece la referencia al nodo hijo izquierdo.
     *
     * @param Hizq El nuevo nodo hijo izquierdo.
     */
    public void setHizq(Nodo Hizq) {
        this.Hizq = Hizq;
    }
}
