package Arboles.ArbolAritmetico.Aritmetico;

import Arboles.ArbolAritmetico.Aritmetico.*;

public class Main {
    public static void main(String[] args) {
        String expresion = "2 √ 25 ";
        
        // Construir el árbol en infix
        Aritmetico T1 = new Aritmetico();
        T1.construir(expresion);
        System.out.println("Test 1");
        T1.bfs();
        T1.NotacionPolacaInversa(T1.MainRaiz, Aritmetico.pila);
        System.out.println("Answer: " + Aritmetico.pila.pop() );
 
    }
}
