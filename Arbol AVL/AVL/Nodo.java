package AVL;
public class Nodo {
   AVL Padre;
   AVL Hizq;
   AVL Hder;
   int value; 
   int eq;
   int deepizq;
   int deepder;
   int deep;

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

    public Nodo(int value){
        this.value = value;
    }

    public AVL getPadre() {
        return this.Padre;
    }

    public void setPadre(AVL Padre) {
        this.Padre = Padre;
    }

    public AVL getHizq() {
        return this.Hizq;
    }

    public void setHizq(AVL Hizq) {
        this.Hizq = Hizq;
    }

    public AVL getHder() {
        return this.Hder;
    }

    public void setHder(AVL Hder) {
        this.Hder = Hder;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getEq() {
        return this.eq;
    }

    public void setEq(int eq) {
        this.eq = eq;
    }

    public int getDeepizq() {
        return this.deepizq;
    }

    public void setDeepizq(int deepizq) {
        this.deepizq = deepizq;
    }

    public int getDeepder() {
        return this.deepder;
    }

    public void setDeepder(int deepder) {
        this.deepder = deepder;
    }


}
