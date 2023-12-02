package Arboles.ArbolHeap.Heap;
import java.util.LinkedList;
import java.util.Queue;


public class Heap {
    public static int nv = 0;

    public static Heap MainRaiz;
    public Nodo Raiz;
    public static Heap Ultimo;

    public Heap(Nodo Raiz, boolean b) {
        this.Raiz = Raiz;
        MainRaiz = this;
        Ultimo = this;
        nv++;
    }

    public Heap(Nodo Raiz) {
        this.Raiz = Raiz;
    }
    public boolean insertar(Nodo node){
        if(Ultimo.Raiz.value >= node.value){
            if(Ultimo.Raiz.Hizq ==null){
                Ultimo.Raiz.Hizq = new Heap(node);
                Ultimo.Raiz.Hizq.Raiz.Padre = Ultimo;
                return true;
            }else{
                Ultimo.Raiz.Hder = new Heap(node);
                Ultimo.Raiz.Hder.Raiz.Padre = Ultimo;
                EncontrarUltimo();
                return true;
            }
        }
        if(Ultimo.Raiz.value< node.value){
            Nodo aux = new Nodo(Ultimo.Raiz.value);
              int swap;
              Nodo aux2 = null;
            if(Ultimo.Raiz.Padre!=null){
            aux2 = Ultimo.Raiz;
            aux2.value = node.value;              
            }else{
                swap = MainRaiz.Raiz.value;
                MainRaiz.Raiz.value = node.value;
                node.value = swap;
                Ultimo = MainRaiz;
                return insertar(node);
            }

          
            while(true ){
                if(aux2.Padre!=null){
                    if(aux2.value > aux2.Padre.Raiz.value){
                    swap = aux2.Padre.Raiz.value;
                    aux2.Padre.Raiz.value = aux2.value;
                    aux2.value= swap;
                    aux2 = aux2.Padre.Raiz;
                    if(aux2.Padre==null)
                    MainRaiz.Raiz = aux2;
                }else{
                    break;
                }
                }else{
                    break;
                }

            }
            EncontrarUltimo();
            insertar(aux);      
        }  
  
        return  true;
    }
    
     public void bfs(){
        if (MainRaiz == null) {
            System.out.println("Vacio");
            return;
        }


        Queue<Nodo> cola = new LinkedList<>();
        cola.add(this.Raiz);
        System.out.println("Ultimo: "+ Heap.Ultimo.Raiz.value);
        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            for(int i = 0; i< nodosEnNivel; i++){
                Nodo actual = cola.poll();
                /* */
                System.out.print("||Node: " + actual.value);
                if(actual.Padre != null)
                    System.out.print(" Father: " + actual.Padre.Raiz.getValue());
                if(actual.Hizq != null)
                    System.out.print(" Hizq: " + actual.Hizq.getRaiz().getValue());
                System.out.print("||");
                if(actual.Hder != null)
                    System.out.print(" Hder: " + actual.Hder.getRaiz().getValue());
                //System.out.print( (char) actual.value + " ");
                if (actual.Hizq != null) {
                cola.add(actual.Hizq.getRaiz());
                }
                if (actual.Hder != null) {
                cola.add(actual.Hder.getRaiz());
                }
            }
            System.out.println();
        }
    }

    public static void EncontrarUltimo() {
        Queue<Heap> cola = new LinkedList<>();
        cola.add(MainRaiz);

        while (!cola.isEmpty()) {
            Heap actual = cola.poll();

            if (actual.Raiz.Hizq == null) {
                Ultimo = actual;
                break;
            } else {
                cola.add(actual.Raiz.Hizq);
            }

            if (actual.Raiz.Hder  == null) {
                Ultimo = actual;
                break;
            } else {
                cola.add(actual.Raiz.Hder);
            }
        }
    }
    public Nodo getRaiz() {
        return this.Raiz;
    }

    public void setRaiz(Nodo Raiz) {
        this.Raiz = Raiz;
    }
    
    public int extraerRaiz(){
        int root;
        int vaux;
        int swap;
        root = MainRaiz.Raiz.value;
        Heap ultimo = obtenerUltimoNodo();
        Heap aux;
        if(MainRaiz ==ultimo){
            MainRaiz = null;
            Ultimo = null;
            this.Raiz = null;
            nv = 0;
            return root;
        }else{
            aux = MainRaiz;
            MainRaiz.Raiz.value = ultimo.Raiz.value;
            if(ultimo.Raiz.Padre.Raiz.Hder!= null)
                if(ultimo.Raiz.Padre.Raiz.Hder.Raiz.value == ultimo.Raiz.value)
                    ultimo.Raiz.Padre.Raiz.Hder=null;
            if(ultimo.Raiz.Padre.Raiz.Hizq!= null)
                if(ultimo.Raiz.Padre.Raiz.Hizq.Raiz.value == ultimo.Raiz.value)
                    ultimo.Raiz.Padre.Raiz.Hizq=null;


                EncontrarUltimo();
            while (true) {
                if(aux.Raiz.Hder == null && aux.Raiz.Hizq == null){
                    return root;
                }else if(aux.Raiz.Hder != null && aux.Raiz.Hizq == null){
                    if(aux.Raiz.Hder.Raiz.value > MainRaiz.Raiz.value){
                        vaux = MainRaiz.Raiz.value;
                        MainRaiz.Raiz.value = aux.Raiz.Hder.Raiz.value;
                        aux.Raiz.Hder.Raiz.value = vaux;
                    }
                    return root;
                }else if(aux.Raiz.Hder == null && aux.Raiz.Hizq != null){
                    if(aux.Raiz.Hizq.Raiz.value > MainRaiz.Raiz.value){
                        vaux = MainRaiz.Raiz.value;
                        MainRaiz.Raiz.value = aux.Raiz.Hizq.Raiz.value;
                        aux.Raiz.Hizq.Raiz.value = vaux;
                    }
                    return root;
                }else{
                        //MainRaiz.bfs();
                        if(aux.Raiz.Hder.Raiz.value > aux.Raiz.Hizq.Raiz.value && aux.Raiz.Hder.Raiz.value >aux.Raiz.value ){
                            swap = aux.Raiz.value;
                            aux.Raiz.value = aux.Raiz.Hder.Raiz.value;
                            aux.Raiz.Hder.Raiz.value = swap;
                            aux = aux.Raiz.Hder;
                            
                        }else if(aux.Raiz.Hizq.Raiz.value >aux.Raiz.value){
                            swap = aux.Raiz.value;
                            aux.Raiz.value = aux.Raiz.Hizq.Raiz.value;
                            aux.Raiz.Hizq.Raiz.value = swap;
                            aux = aux.Raiz.Hizq;
                        }else{
                            return root;
                        }
     

                     
                }
            }
        }
     

    }




    
        private Heap obtenerUltimoNodo() {
            Queue<Heap> cola = new LinkedList<>();
            cola.add(Heap.MainRaiz);
            Heap ultimo = null;
    
            while (!cola.isEmpty()) {
                ultimo = cola.poll();
                if (ultimo.Raiz.Hizq != null) {
                    cola.add(ultimo.Raiz.Hizq);
                }
                if (ultimo.Raiz.Hder != null) {
                    cola.add(ultimo.Raiz.Hder);
                }
            }
            //System.out.println("El ultimo ultimo es: " + ultimo.Raiz.value);
            return ultimo;
        }





}
