package Arboles.ArbolHeap.Heap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * La clase Heap representa una estructura de heap que utiliza nodos para organizar elementos.
 * Puede operar como un max heap, donde el valor de cada nodo es mayor o igual que el de sus nodos hijos.
 * Se utiliza para la inserción y manipulación de nodos en un Heap.
 */
public class Heap {
    /**
     * Número total de nodos en el Heap.
     */
    public static int nv = 0;
     /**
     * Raíz principal del Heap.
     */
    public static Heap MainRaiz;
     /**
     * Raíz del Heap actual.
     */
    public Nodo Raiz;
     /**
     * Último nodo del Heap.
     */
    public static Heap Ultimo;
    /**
     * Último nodo del Heap.
     */
    public static boolean Max;
    /**
     * Constructor de la clase Heap con especificación de la raíz y la creación de la estructura del Heap.
     *
     * @param Raiz La raíz inicial del Heap.
     * @param b    Indica si se es un MaxHeap o un MinHeap.
     */
    public Heap(Nodo Raiz, boolean b) {
        this.Raiz = Raiz;
        MainRaiz = this;
        Ultimo = this;
        nv++;
        Max = b;
    }
    /**
     * Constructor de la clase Heap con especificación de la raíz.
     *
     * @param Raiz La raíz inicial del Heap.
     */
    public Heap(Nodo Raiz) {
        this.Raiz = Raiz;
    }
    /**
     * Inserta un nuevo nodo en el Heap manteniendo la propiedad de heap.
     *
     * @param node El nodo a insertar en el Heap.
     * @return true si la inserción es exitosa, false en caso contrario.
     */
    public boolean insertar(Nodo node){
        if(Max){
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
        }else{
            if(Ultimo.Raiz.value <= node.value){
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
        if(Ultimo.Raiz.value> node.value){
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
                    if(aux2.value < aux2.Padre.Raiz.value){
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
        }
 
  
        return  true;
    }
    /**
    * Realiza un BFS en el Heap e imprime los nodos
    * junto con información sobre sus padres, hijos izquierdos y derechos.
    */
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
    /**
    * Encuentra el último nodo en el Heap y actualiza la referencia estática "Ultimo".
    * Utiliza un BFS para encontrar el último nodo.
    */
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
    /**
 * Obtiene la raíz asociada el Heap.
 *
 * @return La raíz del Heap.
 */
public Nodo getRaiz() {
    return this.Raiz;
}

/**
 * Establece la raíz del Heap.
 *
 * @param Raiz La nueva raíz del Heap.
 */
public void setRaiz(Nodo Raiz) {
    this.Raiz = Raiz;
}
    /**
    * Extrae la raíz del Heap, manteniendo la propiedad de heap, y devuelve su valor.
    * Si el Heap está vacío, devuelve 0.
    *
    * @return El valor de la raíz extraída del Heap.
    */
    public int extraerRaiz(){
        if(Max){
            //System.out.println("Entra ??");
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

                if (MainRaiz.Raiz.value == ultimo.Raiz.value) {
                        if(ultimo.Raiz.Padre.Raiz.Hder!= null){
                            ultimo.Raiz.Padre.Raiz.Hder=null;
                            return root;
                        }else{
                            ultimo.Raiz.Padre.Raiz.Hizq=null;
                            return root;
                        }
                }
                MainRaiz.Raiz.value = ultimo.Raiz.value;
                aux = MainRaiz;
                //if(ultimo.Raiz.Padre.Raiz.Hder == ultimo)
                        if(ultimo.Raiz.Padre.Raiz.Hder!= null){
                            ultimo.Raiz.Padre.Raiz.Hder=null;
                        }else{
                            ultimo.Raiz.Padre.Raiz.Hizq=null;
                        }
                    EncontrarUltimo();
                while (true) {
                    if(aux.Raiz.Hder == null && aux.Raiz.Hizq == null){
                        return root;
                    }else if(aux.Raiz.Hder != null && aux.Raiz.Hizq == null){
                        if(aux.Raiz.Hder.Raiz.value > aux.Raiz.value){
                            vaux = aux.Raiz.value;
                            aux.Raiz.value = aux.Raiz.Hder.Raiz.value;
                            aux.Raiz.Hder.Raiz.value = vaux;
                            aux = aux.Raiz.Hder;
                        }else{
                            return root;
                        }

                    }else if(aux.Raiz.Hder == null && aux.Raiz.Hizq != null){
                        if(aux.Raiz.Hizq.Raiz.value > aux.Raiz.value){
                            vaux = aux.Raiz.value;
                            aux.Raiz.value = aux.Raiz.Hizq.Raiz.value;
                            aux.Raiz.Hizq.Raiz.value = vaux;
                              aux =  aux.Raiz.Hizq;
                        }else{
                            return root;
                        }
   
                    }else{
                            //MainRaiz.bfs();
                            if(aux.Raiz.Hder.Raiz.value > aux.Raiz.Hizq.Raiz.value && aux.Raiz.Hder.Raiz.value >aux.Raiz.value ){
                                swap = aux.Raiz.value;
                                aux.Raiz.value = aux.Raiz.Hder.Raiz.value;
                                aux.Raiz.Hder.Raiz.value = swap;
                                aux = aux.Raiz.Hder;
                                
                            }else if(aux.Raiz.Hder.Raiz.value < aux.Raiz.Hizq.Raiz.value && aux.Raiz.Hizq.Raiz.value >aux.Raiz.value){
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
        }else{
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

                if (MainRaiz.Raiz.value == ultimo.Raiz.value) {
                        if(ultimo.Raiz.Padre.Raiz.Hder!= null){
                            ultimo.Raiz.Padre.Raiz.Hder=null;
                            return root;
                        }else{
                            ultimo.Raiz.Padre.Raiz.Hizq=null;
                            return root;
                        }
                }
                MainRaiz.Raiz.value = ultimo.Raiz.value;
                aux = MainRaiz;
                //if(ultimo.Raiz.Padre.Raiz.Hder == ultimo)
                        if(ultimo.Raiz.Padre.Raiz.Hder!= null){
                            ultimo.Raiz.Padre.Raiz.Hder=null;
                        }else{
                            ultimo.Raiz.Padre.Raiz.Hizq=null;
                        }
                    EncontrarUltimo();
                while (true) {
                    if(aux.Raiz.Hder == null && aux.Raiz.Hizq == null){
                        return root;
                    }else if(aux.Raiz.Hder != null && aux.Raiz.Hizq == null){
                        if(aux.Raiz.Hder.Raiz.value < aux.Raiz.value){
                            vaux = aux.Raiz.value;
                            aux.Raiz.value = aux.Raiz.Hder.Raiz.value;
                            aux.Raiz.Hder.Raiz.value = vaux;
                            aux = aux.Raiz.Hder;
                        }else{
                            return root;
                        }

                    }else if(aux.Raiz.Hder == null && aux.Raiz.Hizq != null){
                        if(aux.Raiz.Hizq.Raiz.value < aux.Raiz.value){
                            vaux = aux.Raiz.value;
                            aux.Raiz.value = aux.Raiz.Hizq.Raiz.value;
                            aux.Raiz.Hizq.Raiz.value = vaux;
                              aux =  aux.Raiz.Hizq;
                        }else if(aux.Raiz.Hder.Raiz.value == aux.Raiz.Hizq.Raiz.value && aux.Raiz.Hizq.Raiz.value >aux.Raiz.value){
                            vaux = aux.Raiz.value;
                            aux.Raiz.value = aux.Raiz.Hizq.Raiz.value;
                            aux.Raiz.Hizq.Raiz.value = vaux;
                              aux =  aux.Raiz.Hizq;
                        }else{
                            return root;
                        }
   
                    }else{
                            //MainRaiz.bfs();
                            if(aux.Raiz.Hder.Raiz.value < aux.Raiz.Hizq.Raiz.value && aux.Raiz.Hder.Raiz.value <aux.Raiz.value ){
                                swap = aux.Raiz.value;
                                aux.Raiz.value = aux.Raiz.Hder.Raiz.value;
                                aux.Raiz.Hder.Raiz.value = swap;
                                aux = aux.Raiz.Hder;
                                
                            }else if(aux.Raiz.Hder.Raiz.value > aux.Raiz.Hizq.Raiz.value && aux.Raiz.Hizq.Raiz.value <aux.Raiz.value){
                                swap = aux.Raiz.value;
                                aux.Raiz.value = aux.Raiz.Hizq.Raiz.value;
                                aux.Raiz.Hizq.Raiz.value = swap;
                                aux = aux.Raiz.Hizq;
                            }else if(aux.Raiz.Hder.Raiz.value == aux.Raiz.Hizq.Raiz.value && aux.Raiz.Hizq.Raiz.value <aux.Raiz.value ){
                                System.out.println("??");
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
        
     

    }




        /**
        * Obtiene el último nodo en el Heap utilizando un BFS.
        *
        * @return El último nodo en el Heap.
        */
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
