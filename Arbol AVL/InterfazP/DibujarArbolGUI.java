package InterfazP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import AVL.AVL;
import AVL.Nodo;

class ArbolPanel extends JPanel {
    private AVL arbol;

    private static void calcularPosiciones(Nodo nodo, int x, int y, int nivel, int distanciaHorizontal, Map<Nodo, Point> posiciones) {
        if (nodo != null) {
            int nuevaDistancia = distanciaHorizontal / 2;
            if (nodo.getHizq() != null) {
                calcularPosiciones(nodo.getHizq().getRaiz(), x - nuevaDistancia, y + 80, nivel + 1, nuevaDistancia, posiciones);
            }
            posiciones.put(nodo, new Point(x, y));
            if (nodo.getHder() != null) {
                calcularPosiciones(nodo.getHder().getRaiz(), x + nuevaDistancia, y + 80, nivel + 1, nuevaDistancia, posiciones);
            }
        }
    }

    public Map<Nodo, Point> generarPosiciones(AVL tree) {
        Nodo raiz = this.arbol.MainTree.getRaiz();
        Map<Nodo, Point> posiciones = new HashMap<>();
        calcularPosiciones(raiz, getWidth() / 2, 50, 0, 160, posiciones);
        return posiciones;
    }

    public void setArbol(AVL arbol) {
        this.arbol = arbol;
    }

    public ArbolPanel(AVL arbol) {
        this.arbol = arbol.MainTree;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarArbol(g);
    }

    private void dibujarArbol(Graphics g) {
        Map<Nodo, Point> posiciones = generarPosiciones(this.arbol);

        for (Map.Entry<Nodo, Point> entry : posiciones.entrySet()) {
            Nodo nodo = entry.getKey();
            Point punto = entry.getValue();

            g.setColor(Color.BLACK);
            g.fillOval(punto.x - 20, punto.y - 20, 40, 40);
            g.setColor(Color.WHITE);
            char asciiChar = (char) nodo.getValue();
            String asciiString = Character.toString(asciiChar);
            g.drawString(asciiString, punto.x - 5, punto.y + 5);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(nodo.getEq()), punto.x - 5, punto.y - 20);

            if (nodo.getHizq() != null) {
                Point hijoIzquierdo = posiciones.get(nodo.getHizq().Raiz);
                g.setColor(Color.BLACK);
                g.drawLine(punto.x, punto.y, hijoIzquierdo.x, hijoIzquierdo.y);
            }

            if (nodo.getHder() != null) {
                Point hijoDerecho = posiciones.get(nodo.getHder().Raiz);
                g.setColor(Color.BLACK);
                g.drawLine(punto.x, punto.y, hijoDerecho.x, hijoDerecho.y);
            }
        }
    }
}

public class DibujarArbolGUI extends JFrame {
    private AVL arbol;
    private ArbolPanel arbolPanel;

    public DibujarArbolGUI() {
        super("Dibujar Arbol");
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOperaciones = new JMenu("Operaciones");
        JMenuItem crearArbolItem = new JMenuItem("Crear Arbol");
        JMenuItem agregarNodoItem = new JMenuItem("Agregar Nodo");
        JMenuItem eliminarNodoItem = new JMenuItem("Eliminar Nodo");
        JMenuItem buscarItem = new JMenuItem("Buscar");
        menuOperaciones.add(crearArbolItem);
        menuOperaciones.add(agregarNodoItem);
        menuOperaciones.add(eliminarNodoItem);
        menuOperaciones.add(buscarItem);
        menuBar.add(menuOperaciones);
        setJMenuBar(menuBar);  // ¡Importante para mostrar la barra de menú correctamente!

  

        // Acciones para los elementos del menú
        crearArbolItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarArbol(); // Lógica para inicializar el árbol
                arbolPanel = new ArbolPanel(arbol); // Crear ArbolPanel después de inicializar el árbol
                add(arbolPanel); // Agregar ArbolPanel al JFrame
                revalidate(); // Actualizar el contenido del JFrame
                JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Arbol creado");
            }
        });

        agregarNodoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pide al usuario que introduzca el valor del nuevo nodo
                String valorNodoStr = JOptionPane.showInputDialog(DibujarArbolGUI.this, "Introduce el valor del nuevo nodo:");
        
                // Intenta convertir el valor del nuevo nodo a entero
                try {
                    int valorNodo = Integer.parseInt(valorNodoStr);
                        // Si el nodo padre existe, agrega el nuevo nodo
                            arbol.MainTree.insertar(new Nodo(valorNodo));
                            revalidate(); // Actualizar el contenido del JFrame
                            repaint();
                            JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Nodo añadido con éxito");
                } catch (NumberFormatException ex) {
                    // Si la conversión del valor del nuevo nodo falla, muestra un mensaje de error
                    JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Introduce un valor válido para el nuevo nodo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarNodoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pide al usuario que introduzca el valor del nuevo nodo
                String valorNodoStr = JOptionPane.showInputDialog(DibujarArbolGUI.this, "Introduce el valor del nodo a eliminar");
        
                // Intenta convertir el valor del nuevo nodo a entero
                try {
                    int valorNodo = Integer.parseInt(valorNodoStr);
                        // Si el nodo padre existe, agrega el nuevo nodo
                            arbol.MainTree.Delete(arbol.MainTree.search(new Nodo(valorNodo)));
                            revalidate(); // Actualizar el contenido del JFrame
                            repaint();
                            JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Nodo eliminado con éxito");
                } catch (NumberFormatException ex) {
                    // Si la conversión del valor del nuevo nodo falla, muestra un mensaje de error
                    JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Introduce un valor válido para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buscarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pide al usuario que introduzca el valor del nuevo nodo
                String valorNodoStr = JOptionPane.showInputDialog(DibujarArbolGUI.this, "Introduce el valor del nodo a buscar");
                // Intenta convertir el valor del nuevo nodo a entero
                try {
                    int valorNodo = Integer.parseInt(valorNodoStr);
                        // Si el nodo padre existe, agrega el nuevo nodo
                           Nodo searched = arbol.MainTree.search(new Nodo(valorNodo));
                            revalidate(); // Actualizar el contenido del JFrame
                            repaint();
                            if(searched!=null){
                                JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Nodo "+ searched.getValue() + " encontrado con éxito");
                            }else{
                                JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Nodo no encontrado");
                            }

                } catch (NumberFormatException ex) {
                    // Si la conversión del valor del nuevo nodo falla, muestra un mensaje de error
                    JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Introduce un valor válido a buscar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inicializarArbol() {
     // Pide al usuario que introduzca el valor de la raíz
     String valorRaizStr = JOptionPane.showInputDialog(this, "Introduce el valor de la raíz:");
     // Intenta convertir el valor de la raíz a entero
     try {
         int valorRaiz = Integer.parseInt(valorRaizStr);
         // Crea el árbol con el valor de la raíz
         arbol = new AVL(new Nodo(null, null, null, valorRaiz, 0, 0, 0), true);
         
     } catch (NumberFormatException e) {
         // Si la conversión falla, muestra un mensaje de error
         JOptionPane.showMessageDialog(this, "Introduce un valor válido para la raíz", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DibujarArbolGUI());
    }
}