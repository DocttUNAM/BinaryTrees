package Arboles.ArbolAVL.AVL;
public class Main {
    public static void main(String[] args) {
        System.out.println("Test 1");
        AVL T1 = new AVL(new Nodo(null, null, null, 77, 0, 0, 0),true);
        T1.MainTree.bfs();
        System.out.println("Test 2");
        T1.insertar(new Nodo(78));
        T1.MainTree.bfs();
        System.out.println("Test 3");
        T1.insertar(new Nodo(79));
        T1.MainTree.bfs();
       
        System.out.println("Test 4");
        T1.insertar(new Nodo(76));
        T1.MainTree.bfs();
   
        System.out.println("Test 5");
        T1.insertar(new Nodo(75));
        T1.MainTree.bfs();
  
         System.out.println("Test 6");
        T1.insertar(new Nodo(81));
        T1.MainTree.bfs();

        System.out.println("Test 7");
        T1.insertar(new Nodo(80));
        T1.MainTree.bfs();
        System.out.println("Test 8");
        T1.insertar(new Nodo(72));
        T1.MainTree.bfs();
       
        System.out.println("Test 9");
        T1.insertar(new Nodo(73));
        T1.MainTree.bfs();
   
        System.out.println("Test 10");
        T1.insertar(new Nodo(65));
        T1.MainTree.bfs();
 
        System.out.println("Test 11");
        T1.Delete(T1.search(new Nodo(73)));
        T1.MainTree.bfs();
            
        System.out.println("Test 12");
        T1.Delete(T1.search(new Nodo(78)));
        T1.MainTree.bfs();
           
        System.out.println("Test 13");
        T1.Delete(T1.search(new Nodo(79)));
        T1.MainTree.bfs();
        
                   
        System.out.println("Test 13");
        T1.Delete(T1.search(new Nodo(80)));
        T1.MainTree.bfs();
    }

}
