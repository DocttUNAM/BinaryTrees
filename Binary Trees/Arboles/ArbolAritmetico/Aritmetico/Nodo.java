package Arboles.ArbolAritmetico.Aritmetico;
public class Nodo {
    int value;
    String op = null;
    Nodo Padre = null;
    Nodo Hder = null;
    Nodo Hizq = null;


    public Nodo(int value, String op, Nodo Padre, Nodo Hder, Nodo Hizq) {
        this.value = value;
        this.op = op;
        this.Padre = Padre;
        this.Hder = Hder;
        this.Hizq = Hizq;
    }

    public Nodo(int value) {
                this.value = value;
    }

   public Nodo(String op) {
            this.op = op;
    }



    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Nodo getPadre() {
        return this.Padre;
    }

    public void setPadre(Nodo Padre) {
        this.Padre = Padre;
    }

    public Nodo getHder() {
        return this.Hder;
    }

    public void setHder(Nodo Hder) {
        this.Hder = Hder;
    }

    public Nodo getHizq() {
        return this.Hizq;
    }

    public void setHizq(Nodo Hizq) {
        this.Hizq = Hizq;
    }













}
