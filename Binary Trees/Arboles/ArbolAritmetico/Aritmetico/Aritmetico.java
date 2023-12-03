// -*- coding: utf-8 -*-
package Arboles.ArbolAritmetico.Aritmetico;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * La clase Aritmetico representa una expresión aritmética y su estructura de árbol asociada.
 * Se utiliza para construir un árbol aritmético a partir de una expresión.
 * La clase utiliza la clase Nodo para representar los nodos del árbol.
 */
public class Aritmetico {
  /**
   * Raíz principal del árbol aritmético.
   */
   public Nodo MainRaiz;
   /**
   * Pila estática utilizada en la construcción del árbol aritmético, almaecena el resultado final.
   */
   public static Stack<Float> pila = new Stack<>();
   
   /**
    * Constructor predeterminado de la clase Aritmetico.
   */
   public Aritmetico() {
   }
   /**
     * Construye el árbol aritmético a partir de una expresión
     *
     * @param s La expresión
     * @return true si la construcción es exitosa, false en caso contrario.
     */
 public boolean construir(String s){
   Aritmetico.pila.clear();
   String[] tokens = s.split(" ");
   Stack<Nodo> pila = new Stack<>();
   Stack<String> pila2 = new Stack<>();
   for (String token : tokens) {
      if(!esOperador(token)){
         pila.push(new Nodo(Integer.parseInt(token)));
      }else if( token.charAt(0) == '('){
         pila2.push(token);
      }else if(token.charAt(0) == ')'){
         while (!pila2.isEmpty() && pila2.peek().charAt(0) != '(') {
            Nodo node = new Nodo(pila2.pop());
            node.Hder = pila.pop();
            node.Hizq = pila.pop();
            node.Hder.Padre = node;
            node.Hizq.Padre = node;
            pila.push(node);
         }
            pila2.pop();
         }else if(esOperador(token))
       {
         while (!pila2.isEmpty() && prioridad(token) <= prioridad(pila2.peek())) {
            Nodo node = new Nodo(pila2.pop());
            node.Hder = pila.pop();
            node.Hizq = pila.pop();
            node.Hder.Padre = node;
            node.Hizq.Padre = node;
            pila.push(node);
         }
         pila2.push(token);
      }
   }
   while (!pila2.isEmpty()) {
      Nodo node = new Nodo(pila2.pop());
      node.Hder = pila.pop();
      node.Hizq = pila.pop();
      node.Hder.Padre = node;
      node.Hizq.Padre = node;
      pila.push(node);
   }
   this.MainRaiz = pila.pop();
    return true;
 }

 /**
   * Verifica si un token es un operador aritmético.
   *
   * @param token  El token (caracter) a verificar.
   * @return true si el token es un operador, false en caso contrario.
   */
 public static boolean esOperador(String token) {
   return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("(") || token.equals(")" ) || token.equals("^" ) || token.equals("√" );
}

   /**
   * Realiza un BFS en el árbol aritmético e imprime los nodos
   * junto con información sobre sus padres, hijos izquierdos y derechos.
   */
    public void bfs(){
        if (this.MainRaiz == null) {
            return;
        }
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(this.MainRaiz);
        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            for(int i = 0; i< nodosEnNivel; i++){
                Nodo actual = cola.poll();
                /* */
                if(actual.op != null){
                System.out.print("||Node: " + actual.op);
                }else{
                     System.out.print("||Node: " + actual.value);
                }

                if(actual.Padre != null)
                if(actual.Padre.op != null){
                System.out.print(" Padre: " + actual.Padre.op);
                }else{
                     System.out.print(" Padre: " + actual.Padre.value);
                }
                if(actual.Hizq != null)
                if(actual.Hizq.op != null){
                System.out.print(" Hizq: " + actual.Hizq.op);
                }else{
                     System.out.print(" Hizq: " + actual.Hizq.value);
                }
                //System.out.print( (char) actual.value + " ");

               if(actual.Hder != null)
                if(actual.Hder.op != null){
                System.out.print(" Hder: " + actual.Hder.op);
                }else{
                     System.out.print(" Hder: " + actual.Hder.value);
                }
               System.out.print("||");

                if (actual.Hizq != null) {
                cola.add(actual.Hizq);
                }
                if (actual.Hder != null) {
                cola.add(actual.Hder);
                }
            }
            System.out.println();
        }
    }
    /**
   * Realiza un recorrido en postorden en el árbol aritmético y evalúa las expresiones
   * utilizando la notación polaca inversa. El resultado se almacena en una pila.
   *
   * @param node El nodo actual en el recorrido.
   * @param pila La pila utilizada para almacenar los resultados parciales.
   */
    public void NotacionPolacaInversa(Nodo node, Stack<Float> pila){
      if(node != null){
      if(node.Hizq!=null)
      NotacionPolacaInversa(node.Hizq, pila);
      if(node.Hder!=null)
      NotacionPolacaInversa(node.Hder, pila);

      if(node.Hder == null && node.Hizq == null){
         pila.push(node.value);
      }else if(pila.size() >=2) {
         float derecha;
         float izquierda;
         derecha =pila.pop();
         izquierda = pila.pop();
         System.out.println(izquierda);
         System.out.println(derecha);
         switch (node.op.charAt(0)) {
            case '+': pila.push(izquierda + derecha); break;
            case '-': pila.push(izquierda - derecha); break;
            case '*': pila.push(izquierda * derecha); break;
            case '/': pila.push(izquierda / derecha); break;
            case '\u005E': pila.push((float)Math.pow(izquierda, derecha));break;
            default: pila.push((float)Math.pow(derecha, 1.0 / izquierda)); break;
        }
      }
      }
    }
   /**
     * Obtiene la prioridad de un operador aritmético.
     *
     * @param operador El operador del cual se obtiene la prioridad.
     * @return La prioridad del operador.
   */
    public static int prioridad(String operador) {
      switch (operador) {
          case "+": return 1;
          case "-": return 1;
          case "*": return 2;
          case "/": return 2;
          case "^": return 3;
          case "√": return 3;
          default: return 0;
      }
  }










}
