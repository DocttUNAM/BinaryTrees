/**
 * La clase AVL representa un árbol de búsqueda binario auto-balanceado utilizando la estructura de árbol AVL.
 * Incluye métodos para insertar nodos, actualizar alturas de nodos y realizar rotaciones para mantener
 * la propiedad AVL. La clase también proporciona métodos para búsqueda, eliminación de nodos y equilibrado
 * del árbol después de inserciones o eliminaciones.
 *
 * El árbol está representado por nodos de la clase 'Nodo', y cada nodo contiene un valor, referencias a los
 * hijos izquierdo y derecho, referencia al padre, altura y factor de equilibrio.
 *
 * @author [Daniel Hernandez]
 * @version 1.0
 */
package Arboles.ArbolAVL.AVL;
import java.util.LinkedList;
import java.util.Queue;

public class AVL {
    /**
     * El nodo raíz del árbol AVL.
     */
    public Nodo Raiz;
    /**
     * Referencia estática al árbol AVL principal.
     */
    public static AVL MainTree;
    /**
     * Variable estática para rastrear el número de nodos en el árbol AVL.
     */
    public static int nv = 0;
    /**
     * Construye un árbol AVL con el nodo raíz dado.
     *
     * @param Raiz El nodo raíz del árbol AVL.
     */
    public AVL(Nodo Raiz) {
        this.Raiz = Raiz;
    }
    /**
     * Construye un árbol AVL con el nodo raíz dado e inicializa la referencia MainTree.
     *
     * @param Raiz El nodo raíz del árbol AVL.
     * @param b    Un valor booleano, para aprovechar la sobrecarga de metodos.
     */
    public AVL(Nodo Raiz, boolean b){
        this.Raiz = Raiz;
        AVL.MainTree = this;
        nv=0;
    }
    /**
     * Obtiene el nodo raíz del árbol AVL.
     *
     * @return El nodo raíz del árbol AVL.
     */
    public Nodo getRaiz() {
        return this.Raiz;
    }
    /**
     * Establece el nodo raíz del árbol AVL.
     *
     * @param Raiz El nuevo nodo raíz del árbol AVL.
     */
    public void setRaiz(Nodo Raiz) {
        this.Raiz = Raiz;
    }
    /**
     * Inserta un nodo en el árbol AVL.
     *
     * @param node El nodo a insertar.
     * @return true si la inserción fue exitosa, false de lo contrario.
     */
    //Inserta un valorm en este caso se crea un arbol como si cada subnodo fuera un subarbol
    public boolean insertar(Nodo node){
        if(node.value> this.Raiz.value){
            if(this.Raiz.Hder==null){
                this.Raiz.Hder = new AVL(node);
                this.Raiz.Hder.Raiz.Padre= this;
                AVL.nv++;
                actualizarAlturaTD(MainTree.getRaiz());
                AVL aux =MainTree.dfsderAVL();
                if(aux ==null){
                    AVL.Equilibrar(MainTree.dfsizqAVL());
                }else{
                    AVL.Equilibrar(aux);
                }
                
                return true;
            }
            return this.Raiz.Hder.insertar(node);
        }else if(node.value < this.Raiz.value){
            if(this.Raiz.Hizq==null){
                this.Raiz.Hizq = new AVL(node);
                this.Raiz.Hizq.Raiz.Padre= this;
                AVL.nv++;
                actualizarAlturaTD(MainTree.getRaiz());
                AVL aux =MainTree.dfsderAVL();
                if(aux ==null){
                    AVL.Equilibrar(MainTree.dfsizqAVL());
                }else{
                    AVL.Equilibrar(aux);
                }
                return true;
            }
            return this.Raiz.Hizq.insertar(node);    
        }
        return false;
    }
     /**
     * Actualiza las alturas de los nodos en el subárbol dado.
     *
     * @param nodo El nodo raíz del subárbol.
     */
    public static void actualizarAlturaTD(Nodo nodo) {
 
        if (nodo == null) {
            return;
        }
        if(nodo.Hizq!=null)
        actualizarAlturaTD(nodo.Hizq.getRaiz()); 
        if(nodo.Hder != null)
        actualizarAlturaTD(nodo.Hder.getRaiz()); 

        int alturaIzquierda = (nodo.Hizq != null) ? nodo.Hizq.getRaiz().deep : 0;
        int alturaDerecha = (nodo.Hder != null) ? nodo.Hder.getRaiz().deep : 0;    
        nodo.deep = 1 + Math.max(alturaIzquierda, alturaDerecha);
        nodo.eq = alturaDerecha - alturaIzquierda;
        nodo.deepder = alturaDerecha;
        nodo.deepizq = alturaIzquierda;
    }
    /**
     * Realiza una rotación simple a la izquierda en el subárbol dado.
     *
     * @param subtree El subárbol en el que se realizará la rotación.
     */
    public static void RSI(AVL subtree){
        if(subtree == null)
        return;

        Nodo aux2 = subtree.Raiz;
        AVL aux = null;
        AVL Padre = null;
        if(aux2.Hder.getRaiz().Hizq!= null)
        aux = aux2.Hder.getRaiz().Hizq;

        if(subtree.getRaiz().value == MainTree.getRaiz().value){
            MainTree = subtree.getRaiz().getHder();
        }else{
            Padre = subtree.getRaiz().Padre;
        }

        subtree.setRaiz(subtree.getRaiz().getHder().getRaiz());
        subtree.Raiz.Hizq = new AVL(aux2);
        subtree.Raiz.Hizq.getRaiz().Padre = subtree;

        if(subtree.getRaiz().value == MainTree.getRaiz().value){
            subtree.getRaiz().Padre = null;
        }else{
            subtree.getRaiz().Padre = Padre;
        }
        subtree.Raiz.Hizq.Raiz.Hder = null;
        if(subtree.Raiz.Hizq.Raiz.Hizq!=null)
        subtree.Raiz.Hizq.Raiz.Hizq.Raiz.Padre = subtree.Raiz.Hizq;
        if(aux != null)
            subtree.Raiz.Hizq.insertar(aux.getRaiz());
        actualizarAlturaTD(MainTree.getRaiz());       
    }
    /**
     * Realiza una rotación simple a la derecha en el subárbol dado.
     *
     * @param subtree El subárbol en el que se realizará la rotación.
     */
   public static void RSD(AVL subtree){
        if(subtree == null)
        return;

        Nodo aux2 = subtree.Raiz;
        AVL aux = null;
        AVL Padre = null;
        if(aux2.Hizq.getRaiz().Hder!= null)
        aux = aux2.Hizq.getRaiz().Hder;

        if(subtree.getRaiz().value == MainTree.getRaiz().value){
            MainTree = subtree.getRaiz().getHizq();
        }else{
            Padre = subtree.getRaiz().Padre;
        }

        subtree.setRaiz(subtree.getRaiz().getHizq().getRaiz());
        subtree.Raiz.Hder = new AVL(aux2);
        subtree.Raiz.Hder.getRaiz().Padre = subtree;
        if(subtree.getRaiz().value == MainTree.getRaiz().value){
            subtree.getRaiz().Padre = null;
        }else{
            subtree.getRaiz().Padre = Padre;
            
        }
        subtree.Raiz.Hder.Raiz.Hizq =null;
        if(subtree.Raiz.Hder.Raiz.Hder!=null)
        subtree.Raiz.Hder.Raiz.Hder.Raiz.Padre = subtree.Raiz.Hder;
        if(aux != null)
            subtree.Raiz.Hder.insertar(aux.getRaiz());
        actualizarAlturaTD(MainTree.getRaiz());
    }




     /**
     * Realiza una rotación doble a la izquierda
     *
     * @param subtree El subárbol en el que se realizará la rotación doble.
     */
    public static void RDI(AVL subtree){
        if(subtree ==null) return;
        RSI(subtree.Raiz.Hizq);
        RSD(subtree);
  
    }
    /**
     * Realiza una rotación doble a la derecha
     *
     * @param subtree El subárbol en el que se realizará la rotación doble.
     */
    public static void RDD(AVL subtree){
        RSD(subtree.Raiz.Hder);
        RSI(subtree);
    }

     /**
     * BFS del árbol AVL e imprime la información de cada nodo.
     */
    public void bfs(){
        if (this.Raiz == null) {
            return;
        }
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(this.Raiz);
        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            for(int i = 0; i< nodosEnNivel; i++){
                Nodo actual = cola.poll();
                /* */
                System.out.print("||Node: " + (char)actual.value  + " Eq: " + actual.eq);
                if(actual.Padre != null)
                    System.out.print(" Father: " + (char)actual.Padre.Raiz.getValue());
                if(actual.Hder != null)
                    System.out.print(" Hder: " +(char) actual.Hder.getRaiz().getValue());
                if(actual.Hizq != null)
                    System.out.print(" Hizq: " + (char)actual.Hizq.getRaiz().getValue());
                System.out.print("||");
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
     * Realiza un DFS en el árbol AVL
     * y devuelve el primer subárbol desequilibrado encontrado.
     *
     * @return El primer subárbol desequilibrado encontrado.
     */
     public AVL dfsizqAVL(){
        if(this.Raiz.Hizq != null)
            if( this.getRaiz().Hizq.getRaiz().getEq() <2 && this.getRaiz().Hizq.getRaiz().getEq() >1 ||  this.getRaiz().Hizq.getRaiz().getEq() <2 && this.getRaiz().Hizq.getRaiz().getEq() <-1)
                return this.Raiz.Hizq.dfsizqAVL();
        if(this.Raiz.Hder !=null)
            if(this.getRaiz().Hder.getRaiz().getEq() <2 && this.getRaiz().Hder.getRaiz().getEq() >1 || this.getRaiz().Hder.getRaiz().getEq() <2 && this.getRaiz().Hder.getRaiz().getEq() <-1)
                return this.Raiz.Hder.dfsderAVL();
        if(this.Raiz.eq <=-2 || this.Raiz.eq >=2 )
        return this;
        if(this.Raiz.eq <=-2){
            if(this.Raiz.Padre!=null)
                return this.Raiz.Padre;
            return MainTree;
        }else{
            if(this.Raiz.Hizq!= null){
                return this.Raiz.Hizq.dfsizqAVL();
            }
        }
        return null;
    }
    /**
     * Realiza un DFS en el árbol AVL
     * y devuelve el primer subárbol desequilibrado encontrado.
     *
    * @return El primer subárbol desequilibrado encontrado.
     */
     public AVL dfsderAVL(){

        if(this.Raiz.Hder !=null)
            if(this.getRaiz().Hder.getRaiz().getEq() <2 && this.getRaiz().Hder.getRaiz().getEq() >1 || this.getRaiz().Hder.getRaiz().getEq() <2 && this.getRaiz().Hder.getRaiz().getEq() <-1)
                return this.Raiz.Hder.dfsderAVL();
        if(this.Raiz.Hizq != null)
            if( this.getRaiz().Hizq.getRaiz().getEq() <2 && this.getRaiz().Hizq.getRaiz().getEq() >1 ||  this.getRaiz().Hizq.getRaiz().getEq() <2 && this.getRaiz().Hizq.getRaiz().getEq() <-1)
                return this.Raiz.Hizq.dfsizqAVL();

        if(this.Raiz.eq <=-2 || this.Raiz.eq >=2)
        return this;
        
        if(this.Raiz.Hder == null)
        return null;

        if(this.Raiz.eq >=2 ){
            if(this.Raiz.Padre != null)
                return this.Raiz.Hder.dfsderAVL();
            return MainTree;
        }else{
            if(this.Raiz.Hder !=null)
                return this.Raiz.Hder.dfsderAVL();
        }
        return null;
    }
     /**
     * Realiza el equilibrado del árbol AVL después de una inserción o eliminación.
     *
     * @param subtree El subárbol a equilibrar.
     */
    public static void Equilibrar(AVL subtree){
        if(subtree == null)
        return;
        System.out.println("A balancear:  " + subtree.getRaiz().getValue() + " " + subtree.getRaiz().getEq());
        //subtree.bfs();
        if(subtree.getRaiz().getEq()>1){
                    if(subtree.getRaiz().getHder().getRaiz().getEq() >=0){
                        AVL.RSI(subtree);
                    }else{
                        AVL.RDD(subtree);
                    }
                }else if(subtree.getRaiz().getEq()<-1){
                    if(subtree.getRaiz().getHizq().getRaiz().getEq() <=0){
                        AVL.RSD(subtree);
                    }else{
                        AVL.RDI(subtree);
                    }

                }
            //actualizarAlturaTD(MainTree.Raiz);
    }
    /**
     * Busca un nodo con el valor dado en el árbol AVL.
     *
     * @param ToSearch El nodo a buscar.
     * @return El nodo encontrado o null si no se encontró.
     */
        public Nodo search(Nodo ToSearch){
        if (this.Raiz == null) {
            return null;
        }
        if(this.Raiz.value == ToSearch.value){
            return this.Raiz;
        }else if(ToSearch.value > this.Raiz.value && this.Raiz.Hder!=null){
            
                return this.Raiz.Hder.search(ToSearch );
        }else if(ToSearch.value < this.Raiz.value && this.Raiz.Hizq!=null ){
                return this.Raiz.Hizq.search(ToSearch);
        }
        return null;
    }
    
    /**
     * Elimina un nodo con el valor dado del árbol AVL.
     *
     * @param nodo El nodo a eliminar.
     * @return true si la eliminación fue exitosa, false de lo contrario.
     */
    public boolean Delete(Nodo nodo){

        if(nodo == null){
            System.out.println("No exite el nodo en el arbol");
            return false;
        }
         
        //Es una hoja?
        if(nodo.Hder==null && nodo.Hizq == null){
            //probablemente, esta hoja es la raiz?
            if(nodo.Padre == null){
                AVL.nv =0;
                AVL.MainTree =null;
                this.Raiz =null;
            }else{
                if(nodo.Padre.Raiz.Hder!=null && nodo.Padre.Raiz.Hizq !=null)
                    if(nodo.Padre.Raiz.Hder.Raiz.value == nodo.value){
                        nodo.Padre.Raiz.Hder=null;
                    }else{
                        nodo.Padre.Raiz.Hizq = null;
                }else if(nodo.Padre.Raiz.Hder==null)
                    nodo.Padre.Raiz.Hizq = null;
                else if(nodo.Padre.Raiz.Hizq==null)
                    nodo.Padre.Raiz.Hder = null;
            }
            AVL.actualizarAlturaTD(MainTree.Raiz);
                AVL aux2 =MainTree.dfsderAVL();
                if(aux2==null){
                    AVL.Equilibrar(MainTree.dfsizqAVL());
                }else{
                    AVL.Equilibrar(aux2);
                }
            return true;
        }
        //No es una hoja pero no tiene subarbol derecho
        if(nodo.Hder == null){
            if(nodo.Padre ==null){
                AVL.MainTree = nodo.Hizq;
                AVL.MainTree.Raiz.Padre = null;
            }else{
                nodo.Hizq.Raiz.Padre = nodo.Padre;
                nodo.Padre.Raiz.Hizq = nodo.Hizq;
            }
            AVL.actualizarAlturaTD(MainTree.Raiz);
            AVL aux2 =MainTree.dfsderAVL();
                if(aux2==null){
                    AVL.Equilibrar(MainTree.dfsizqAVL());
                }else{
                    AVL.Equilibrar(aux2);
                }
            return true;
           
        }
         //No es una hoja pero no tiene subarbol izquierdo
        if(nodo.Hizq == null){
            if(nodo.Padre ==null){
                AVL.MainTree = nodo.Hder;
                AVL.MainTree.Raiz.Padre = null;
            }else{
                nodo.Hder.Raiz.Padre = nodo.Padre;
                nodo.Padre.Raiz.Hder = nodo.Hder;
            }
            AVL.actualizarAlturaTD(MainTree.Raiz);
            AVL aux2 =MainTree.dfsderAVL();
                if(aux2==null){
                    AVL.Equilibrar(MainTree.dfsizqAVL());
                }else{
                    AVL.Equilibrar(aux2);
                }
            return true;
        }
        
        //No es una hoja pero tiene ambos subarboles
        Nodo intecambar = null;
        intecambar = nodo.Hder.getRaiz();
        while (intecambar.Hizq!=null) {
            intecambar = intecambar.Hizq.getRaiz();
        }
        int aux;
        aux = nodo.value;
        nodo.value = intecambar.value;
        intecambar.value= aux;
        //System.out.println("Borrando: " + intecambar.value);
        this.Delete(intecambar);
            AVL aux2 =MainTree.dfsderAVL();
                if(aux2==null){
                    AVL.Equilibrar(MainTree.dfsizqAVL());
                }else{
                    AVL.Equilibrar(aux2);
                }


                return true;
    }


}
