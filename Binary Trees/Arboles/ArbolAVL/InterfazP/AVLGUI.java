package Arboles.ArbolAVL.InterfazP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import Arboles.ArbolAVL.AVL.Nodo;
import Arboles.ArbolAVL.AVL.AVL;
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
            Font originalFont = g.getFont();
            Font nuevaFuente = new Font(originalFont.getName(), Font.BOLD, 16); // 16 es el nuevo tamano
            g.setFont(nuevaFuente);
            g.setColor(Color.WHITE);
            String valorNodoStr;
            if (AVLGUI.modoNumerico) {
                valorNodoStr = String.valueOf(nodo.getValue());
            }else{
            char asciiChar = (char) nodo.getValue();
                valorNodoStr =  Character.toString(asciiChar);
            }
            g.drawString(valorNodoStr, punto.x - 5, punto.y + 5);
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

public class AVLGUI extends JFrame {
    private AVL arbol;
    public static boolean modoNumerico = true; // Modo predeterminado
    private ArbolPanel arbolPanel;

    public AVLGUI() {
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
                // Mostrar un JOptionPane con opciones para el modo
                Object[] opciones = {"Modo Numerico", "Modo Letras"};
                int seleccion = JOptionPane.showOptionDialog(
                        AVLGUI.this,
                        "Selecciona el modo:",
                        "Modo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                // Determinar el modo seleccionado por el usuario
                if (seleccion == 0) {
                    modoNumerico = true;
                    JOptionPane.showMessageDialog(AVLGUI.this, "Modo Numerico seleccionado");
                } else if (seleccion == 1) {
                    modoNumerico = false;
                    JOptionPane.showMessageDialog(AVLGUI.this, "Modo Letras seleccionado");
                }
                inicializarArbol(); // Lógica para inicializar el arbol
            }
        });

        agregarNodoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pide al usuario que introduzca el valor del nuevo nodo
                String valorNodoStr = JOptionPane.showInputDialog(AVLGUI.this, "Introduce el valor del nuevo nodo:");
                boolean b;
                // Intenta convertir el valor del nuevo nodo a entero
                try {
                            if (!modoNumerico) {
                    throw new NumberFormatException();
                            }
                    int valorNodo = Integer.parseInt(valorNodoStr);
                            b=arbol.MainTree.insertar(new Nodo(valorNodo));
                            revalidate(); // Actualizar el contenido del JFrame
                            repaint();
                            if(b)
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo anadido con exito");
                            else
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo ya existente");
                } catch (NumberFormatException ex) {
                    if(modoNumerico == true){
                        JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para el nuevo nodo", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if (valorNodoStr.length() == 1) {
                            char valorCaracter = valorNodoStr.charAt(0);
                            int valorAscii = (int) valorCaracter;
                            b= arbol.MainTree.insertar(new Nodo(valorAscii));
                            revalidate();
                            repaint();
                            if(b)
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo anadido con exito");
                            else
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo ya existente");
                        } else {
                            // Si no es ni número ni un caracter, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para el nuevo nodo", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }


                }
            }
        });

        eliminarNodoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pide al usuario que introduzca el valor del nuevo nodo
                String valorNodoStr = JOptionPane.showInputDialog(AVLGUI.this, "Introduce el valor del nodo a eliminar");
                 boolean b;
                // Intenta convertir el valor del nuevo nodo a entero
                try {
                    if (!modoNumerico) {
                    throw new NumberFormatException();
                    }
                    int valorNodo = Integer.parseInt(valorNodoStr);
                            


                           
  
                            try {
                            b = arbol.MainTree.Delete(arbol.MainTree.search(new Nodo(valorNodo)));
                                if(arbol.MainTree == null)
                                    getContentPane().removeAll(); // Eliminar todos los componentes del JFrame
                            } catch (Exception ef) {
                                b=true;
                            getContentPane().removeAll(); // Eliminar todos los componentes del JFrame
                            }

                            revalidate(); // Actualizar el contenido del JFrame
                            repaint();
                           

                            if(b)
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo eliminado con exito");
                            else
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo no existente");
                } catch (NumberFormatException ex) {
                    if(modoNumerico == true){
                        JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if (valorNodoStr.length() == 1) {
                            char valorCaracter = valorNodoStr.charAt(0);
                            int valorAscii = (int) valorCaracter;
                            b =arbol.MainTree.Delete(arbol.MainTree.search(new Nodo(valorAscii)));
                            revalidate();
                            repaint();
                            if(b)
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo eliminado con exito");
                            else
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo no existente");
                        } else {
                            // Si no es ni número ni un caracter, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        buscarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pide al usuario que introduzca el valor del nuevo nodo
                String valorNodoStr = JOptionPane.showInputDialog(AVLGUI.this, "Introduce el valor del nodo a buscar");
                // Intenta convertir el valor del nuevo nodo a entero
                try {
                    if (!modoNumerico) {
                    throw new NumberFormatException();
                    }
                    int valorNodo = Integer.parseInt(valorNodoStr);

                           Nodo searched = arbol.MainTree.search(new Nodo(valorNodo));
                            revalidate(); // Actualizar el contenido del JFrame
                            repaint();
                            if(searched!=null){
                                JOptionPane.showMessageDialog(AVLGUI.this, "Nodo "+ searched.getValue() + " encontrado con exito");
                            }else{
                                JOptionPane.showMessageDialog(AVLGUI.this, "Nodo no encontrado");
                            }

                } catch (NumberFormatException ex) {
                     if(modoNumerico == true){
                        JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para buscar", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if (valorNodoStr.length() == 1) {
                            char valorCaracter = valorNodoStr.charAt(0);
                            int valorAscii = (int) valorCaracter;
                            Nodo searched = arbol.MainTree.search(new Nodo(valorAscii));
                            repaint();
                            if(searched!=null){
                                JOptionPane.showMessageDialog(AVLGUI.this, "Nodo "+ (char) searched.getValue() + " encontrado con exito");
                            }else{
                                JOptionPane.showMessageDialog(AVLGUI.this, "Nodo no encontrado");
                            }
                        } else {
                            // Si no es ni número ni un caracter, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para encontrar", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }}
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
            arbol = new AVL(new Nodo(null, null, null, valorRaiz, 0, 0, 0), true);
            arbolPanel = new ArbolPanel(arbol); // Crear ArbolPanel después de inicializar el arbol
            add(arbolPanel); // Agregar ArbolPanel al JFrame
            revalidate(); // Actualizar el contenido del JFrame
                JOptionPane.showMessageDialog(AVLGUI.this, "Arbol creado");
     } catch (NumberFormatException e) {
                      if(modoNumerico == true){
                        JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para la raiz", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if (valorRaizStr.length() == 1) {
                            char valorCaracter = valorRaizStr.charAt(0);
                            int valorAscii = (int) valorCaracter;
                            arbol = new AVL(new Nodo(null, null, null, valorAscii, 0, 0, 0), true);
                            arbolPanel = new ArbolPanel(arbol); // Crear ArbolPanel después de inicializar el arbol
                            add(arbolPanel); // Agregar ArbolPanel al JFrame
                            revalidate(); // Actualizar el contenido del JFrame
                            JOptionPane.showMessageDialog(AVLGUI.this, "Nodo anadido con exito (modo letras)");
                        } else {
                            // Si no es ni número ni un caracter, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(AVLGUI.this, "Introduce un valor valido para la raiz", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } }
    }
     /* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AVLGUI());
    }
    */
}