package Arboles.ArbolHeap.Heap;
public class Nodo {
    public int value;
    public Heap Padre;
    public Heap Hder;
    public Heap Hizq;


    public Nodo(int value, Heap Padre, Heap Hder, Heap Hizq) {
        this.value = value;
        this.Padre = Padre;
        this.Hder = Hder;
        this.Hizq = Hizq;
    }


    public Nodo(int value) {
        this.value = value;
    }


    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Heap getPadre() {
        return this.Padre;
    }

    public void setPadre(Heap Padre) {
        this.Padre = Padre;
    }

    public Heap getHder() {
        return this.Hder;
    }

    public void setHder(Heap Hder) {
        this.Hder = Hder;
    }

    public Heap getHizq() {
        return this.Hizq;
    }

    public void setHizq(Heap Hizq) {
        this.Hizq = Hizq;
    }
    

}

