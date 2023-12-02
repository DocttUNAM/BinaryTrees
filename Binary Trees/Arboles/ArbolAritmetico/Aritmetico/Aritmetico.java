package Arboles.ArbolAritmetico.Aritmetico;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Aritmetico {
 public Nodo MainRaiz;
public static Stack<Integer> pila = new Stack<>();
    public Aritmetico() {
    }

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

 public static boolean esOperador(String token) {
   return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("(") || token.equals(")");
}

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

    public void NotacionPolacaInversa(Nodo node, Stack<Integer> pila){
      if(node != null){
      if(node.Hizq!=null)
      NotacionPolacaInversa(node.Hizq, pila);
      if(node.Hder!=null)
      NotacionPolacaInversa(node.Hder, pila);

      if(node.Hder == null && node.Hizq == null){
         pila.push(node.value);
      }else if(pila.size() >=2) {
         int derecha;
         int izquierda;
         derecha =pila.pop();
         izquierda = pila.pop();
         switch (node.op.charAt(0)) {
            case '+': pila.push(izquierda + derecha); break;
            case '-': pila.push(izquierda - derecha); break;
            case '*': pila.push(izquierda * derecha); break;
            case '/': pila.push(izquierda / derecha); break;
        }
      }
      }
    }

    public static int prioridad(String operador) {
      switch (operador) {
          case "+": return 1;
          case "-": return 1;
          case "*": return 2;
          case "/": return 2;
          default: return 0;
      }
  }










}
