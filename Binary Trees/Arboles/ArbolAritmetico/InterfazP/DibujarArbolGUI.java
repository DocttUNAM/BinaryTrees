package Arboles.ArbolAritmetico.InterfazP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import Arboles.ArbolAritmetico.Aritmetico.Aritmetico;
import Arboles.ArbolAritmetico.Aritmetico.Nodo;
class ArbolPanel extends JPanel {
    private Aritmetico arbol;

    private static void calcularPosiciones(Nodo nodo, int x, int y, int nivel, int distanciaHorizontal, Map<Nodo, Point> posiciones) {
        if (nodo != null) {
            int nuevaDistancia = distanciaHorizontal / 2;
            if (nodo.getHizq() != null) {
                calcularPosiciones(nodo.getHizq(), x - nuevaDistancia, y + 80, nivel + 1, nuevaDistancia, posiciones);
            }
            posiciones.put(nodo, new Point(x, y));
            if (nodo.getHder() != null) {
                calcularPosiciones(nodo.getHder(), x + nuevaDistancia, y + 80, nivel + 1, nuevaDistancia, posiciones);
            }
        }
    }

    public Map<Nodo, Point> generarPosiciones(Aritmetico tree) {
        Nodo raiz = this.arbol.MainRaiz;
        Map<Nodo, Point> posiciones = new HashMap<>();
        calcularPosiciones(raiz, getWidth() / 2, 50, 0, 300, posiciones);
        return posiciones;
    }

    public void setArbol(Aritmetico arbol) {
        this.arbol = arbol;
    }

    public ArbolPanel(Aritmetico arbol) {
        this.arbol = arbol;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
            if (nodo.getOp() == null) {
                valorNodoStr = String.valueOf(nodo.getValue());
            }else{
                valorNodoStr =  nodo.getOp();
            }
            g.drawString(valorNodoStr, punto.x - 5, punto.y + 5);

            if (nodo.getHizq() != null) {
                Point hijoIzquierdo = posiciones.get(nodo.getHizq());
                g.setColor(Color.BLACK);
                g.drawLine(punto.x, punto.y, hijoIzquierdo.x, hijoIzquierdo.y);
            }

            if (nodo.getHder() != null) {
                Point hijoDerecho = posiciones.get(nodo.getHder());
                g.setColor(Color.BLACK);
                g.drawLine(punto.x, punto.y, hijoDerecho.x, hijoDerecho.y);
            }
        }
    }
}

public class DibujarArbolGUI extends JFrame {
    private Aritmetico arbol;
    private ArbolPanel arbolPanel;
    public boolean b = false;
    public DibujarArbolGUI() {
        super("Dibujar Arbol");
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOperaciones = new JMenu("Operaciones");
        JMenuItem crearArbolItem = new JMenuItem("Crear Arbol (Ingresar expresion)");
        JMenuItem eliminarNodoItem = new JMenuItem("Resolver");
        menuOperaciones.add(crearArbolItem);
        menuOperaciones.add(eliminarNodoItem);
        menuBar.add(menuOperaciones);
        setJMenuBar(menuBar);  // ¡Importante para mostrar la barra de menú correctamente!

  

        // Acciones para los elementos del menú
        crearArbolItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarArbol(); // Lógica para inicializar el arbol
            }
        });

        eliminarNodoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(!b)
                    arbol.NotacionPolacaInversa(arbol.MainRaiz, arbol.pila);
                    float r = (float)arbol.pila.peek();
                    JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Resolucion: " + r);
                    b = true;
                    arbolPanel.repaint();
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
     String valorRaizStr = JOptionPane.showInputDialog(this, "Introduce la expresion todo separado con espacios: ");
     // Intenta convertir el valor de la raíz a entero
            arbol = new Aritmetico();
            arbol.construir(valorRaizStr);
            arbolPanel = new ArbolPanel(arbol); // Crear ArbolPanel después de inicializar el arbol
            b = false;
            add(arbolPanel); // Agregar ArbolPanel al JFrame
            revalidate(); // Actualizar el contenido del JFrame
            JOptionPane.showMessageDialog(DibujarArbolGUI.this, "Arbol creado");
    }
     /* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DibujarArbolGUI());
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
    }*/
}