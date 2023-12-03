package Arboles.ArbolHeap.InterfazP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import Arboles.ArbolHeap.Heap.Heap;
import Arboles.ArbolHeap.Heap.Nodo;
class ArbolPanel extends JPanel {
    private Heap arbol;

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

    public Map<Nodo, Point> generarPosiciones(Heap tree) {
        Nodo raiz = this.arbol.MainRaiz.Raiz;
        Map<Nodo, Point> posiciones = new HashMap<>();
        calcularPosiciones(raiz, getWidth() / 2, 50, 0, 160, posiciones);
        return posiciones;
    }

    public void setArbol(Heap arbol) {
        this.arbol = arbol;
    }

    public ArbolPanel(Heap arbol) {
        this.arbol = arbol.MainRaiz;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarArbol(g);
    }

    private void dibujarArbol(Graphics g) {
        Map<Nodo, Point> posiciones = generarPosiciones(this.arbol.MainRaiz);

        for (Map.Entry<Nodo, Point> entry : posiciones.entrySet()) {
            Nodo nodo = entry.getKey();
            Point punto = entry.getValue();

            g.setColor(Color.BLACK);
            g.fillOval(punto.x - 20, punto.y - 20, 40, 40);
            Font originalFont = g.getFont();
            Font nuevaFuente = new Font(originalFont.getName(), Font.BOLD, 16); // 16 es el nuevo tamano
            g.setFont(nuevaFuente);
            g.setColor(Color.WHITE);
            String valorNodoStr;
            if (HeapGUI.modoNumerico) {
                valorNodoStr = String.valueOf(nodo.getValue());
            }else{
            char asciiChar = (char) nodo.getValue();
                valorNodoStr =  Character.toString(asciiChar);
            }
            g.drawString(valorNodoStr, punto.x - 5, punto.y + 5);

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

public class HeapGUI extends JFrame {
    private Heap arbol;
    public static boolean modoNumerico = true; // Modo predeterminado
    private ArbolPanel arbolPanel;
    public static boolean MaxHeap = true; // Modo predeterminado
    public HeapGUI() {
        super("Dibujar Arbol");
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOperaciones = new JMenu("Operaciones");
        JMenuItem crearArbolItem = new JMenuItem("Crear Arbol");
        JMenuItem agregarNodoItem = new JMenuItem("Agregar Nodo");
        JMenuItem eliminarNodoItem = new JMenuItem("Extraer Raiz");
        menuOperaciones.add(crearArbolItem);
        menuOperaciones.add(agregarNodoItem);
        menuOperaciones.add(eliminarNodoItem);
        menuBar.add(menuOperaciones);
        setJMenuBar(menuBar);  // ¡Importante para mostrar la barra de menú correctamente!

  

        // Acciones para los elementos del menú
        crearArbolItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar un JOptionPane con opciones para el modo
                Object[] opciones = {"Modo Numerico", "Modo Letras"};
                int seleccion = JOptionPane.showOptionDialog(
                        HeapGUI.this,
                        "Selecciona el modo:",
                        "Modo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );
                Object[] modos = {"MaxHeap", "MinHeap"};
                int seleccion2 = JOptionPane.showOptionDialog(
                        HeapGUI.this,
                        "Selecciona el modo:",
                        "Modo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        modos,
                        modos[0]
                );
                // Determinar el modo seleccionado por el usuario
                if (seleccion == 0) {
                    modoNumerico = true;
                    JOptionPane.showMessageDialog(HeapGUI.this, "Modo Numerico seleccionado");
                } else if (seleccion == 1) {
                    modoNumerico = false;
                    JOptionPane.showMessageDialog(HeapGUI.this, "Modo Letras seleccionado");
                }
                if (seleccion2 == 0) {
                    MaxHeap = true;
                    JOptionPane.showMessageDialog(HeapGUI.this, "Max Heap seleccionado");
                } else if (seleccion2 == 1) {
                    MaxHeap = false;
                    JOptionPane.showMessageDialog(HeapGUI.this, "MinHeap Seleccionado");
                }
                inicializarArbol(); // Lógica para inicializar el arbol
            }
        });

        agregarNodoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pide al usuario que introduzca el valor del nuevo nodo
                String valorNodoStr = JOptionPane.showInputDialog(HeapGUI.this, "Introduce el valor del nuevo nodo:");
                boolean b;
                // Intenta convertir el valor del nuevo nodo a entero
                try {
                            if (!modoNumerico) {
                    throw new NumberFormatException();
                            }
                    int valorNodo = Integer.parseInt(valorNodoStr);
                            b=arbol.MainRaiz.insertar(new Nodo(valorNodo));
                            revalidate(); // Actualizar el contenido del JFrame
                            repaint();
                            if(b)
                            JOptionPane.showMessageDialog(HeapGUI.this, "Nodo anadido con exito");
                            else
                            JOptionPane.showMessageDialog(HeapGUI.this, "Nodo ya existente");
                } catch (NumberFormatException ex) {
                    if(modoNumerico == true){
                        JOptionPane.showMessageDialog(HeapGUI.this, "Introduce un valor valido para el nuevo nodo", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if (valorNodoStr.length() == 1) {
                            char valorCaracter = valorNodoStr.charAt(0);
                            int valorAscii = (int) valorCaracter;
                            b= arbol.MainRaiz.insertar(new Nodo(valorAscii));
                            revalidate();
                            repaint();
                            if(b)
                            JOptionPane.showMessageDialog(HeapGUI.this, "Nodo anadido con exito");
                            else
                            JOptionPane.showMessageDialog(HeapGUI.this, "Nodo ya existente");
                        } else {
                            // Si no es ni número ni un caracter, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(HeapGUI.this, "Introduce un valor valido para el nuevo nodo", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }


                }
            }
        });

        eliminarNodoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    arbol.MainRaiz.extraerRaiz();
                    if(arbol.MainRaiz != null){
                    revalidate(); // Actualizar el contenido del JFrame
                    repaint();
                    }else{
                        getContentPane().removeAll(); // Eliminar todos los componentes del JFrame
                        revalidate(); // Actualizar el contenido del JFrame
                        repaint();
                    }

                    JOptionPane.showMessageDialog(HeapGUI.this, "Raiz extraida con exito");
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inicializarArbol() {
     // Pide al usuario que introduzca el valor de la raíz
     String valorRaizStr = JOptionPane.showInputDialog(this, "Introduce el valor de la raiz:");
     // Intenta convertir el valor de la raíz a entero
     try {
        if (!modoNumerico) {
            throw new NumberFormatException();
        }
         int valorRaiz = Integer.parseInt(valorRaizStr);
         // Crea el arbol con el valor de la raíz
            arbol = new Heap(new Nodo(valorRaiz),MaxHeap);
            arbolPanel = new ArbolPanel(arbol); // Crear ArbolPanel después de inicializar el arbol
            add(arbolPanel); // Agregar ArbolPanel al JFrame
            revalidate(); // Actualizar el contenido del JFrame
                JOptionPane.showMessageDialog(HeapGUI.this, "Arbol creado");
     } catch (NumberFormatException e) {
                      if(modoNumerico == true){
                        JOptionPane.showMessageDialog(HeapGUI.this, "Introduce un valor valido para la raiz", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if (valorRaizStr.length() == 1) {
                            char valorCaracter = valorRaizStr.charAt(0);
                            int valorAscii = (int) valorCaracter;
                            arbol = new Heap(new Nodo(valorAscii),MaxHeap);
                            arbolPanel = new ArbolPanel(arbol); // Crear ArbolPanel después de inicializar el arbol
                            add(arbolPanel); // Agregar ArbolPanel al JFrame
                            revalidate(); // Actualizar el contenido del JFrame
                            JOptionPane.showMessageDialog(HeapGUI.this, "Nodo anadido con exito (modo letras)");
                        } else {
                            // Si no es ni número ni un caracter, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(HeapGUI.this, "Introduce un valor valido para la raiz", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } }
    }
    /* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HeapGUI());
    }
    */
}