package HEAP;

public class HeapNode {
    private int dato;
    private HeapNode hijo_izquierdo, hijo_derecho, el_gfe;

    public HeapNode(int item) {
        dato = item;
        hijo_izquierdo = hijo_derecho = el_gfe = null;
    }

    // Getters
    public int getDato() {
        return dato;
    }

    public HeapNode getHijo_izquierdo() {
        return hijo_izquierdo;
    }

    public HeapNode getHijo_derecho() {
        return hijo_derecho;
    }

    public HeapNode getEl_gfe() {
        return el_gfe;
    }

    // Setters
    public void setDato(int dato) {
        this.dato = dato;
    }

    public void setHijo_izquierdo(HeapNode hijo_izquierdo) {
        this.hijo_izquierdo = hijo_izquierdo;
    }

    public void setHijo_derecho(HeapNode hijo_derecho) {
        this.hijo_derecho = hijo_derecho;
    }

    public void setEl_gfe(HeapNode el_gfe) {
        this.el_gfe = el_gfe;
    }
}
