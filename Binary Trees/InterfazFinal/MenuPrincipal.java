package InterfazFinal;
import Arboles.ArbolAVL.InterfazP.AVLGUI;
import Arboles.ArbolAritmetico.InterfazP.DibujarArbolGUI;
import Arboles.ArbolHeap.InterfazP.HeapGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class MenuPrincipal extends JFrame {
    private Stack<JFrame> frameStack = new Stack<>();

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton btnArbolAVL = new JButton("Arbol AVL");
        JButton btnArbolHeap = new JButton("Arbol Heap");
        JButton btnArbolAritmetico = new JButton("Arbol Aritmetico");

        btnArbolAVL.addActionListener(e -> abrirNuevoFrame(new AVLGUI()));
        btnArbolHeap.addActionListener(e -> abrirNuevoFrame(new HeapGUI()));
        btnArbolAritmetico.addActionListener(e -> abrirNuevoFrame(new DibujarArbolGUI()));

        panel.add(btnArbolAVL);
        panel.add(btnArbolHeap);
        panel.add(btnArbolAritmetico);

        add(panel);
        setVisible(true);
    }

    private void abrirNuevoFrame(JFrame nuevoFrame) {
        if (!frameStack.isEmpty()) {
            frameStack.peek().setVisible(false);
        }

        frameStack.push(nuevoFrame);

        nuevoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuevoFrame.setSize(300, 200);
        nuevoFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximiza la ventana
        nuevoFrame.setLocationRelativeTo(null);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> regresarAlMenuPrincipal());

        nuevoFrame.add(btnRegresar, BorderLayout.SOUTH);
        nuevoFrame.setVisible(true);
    }

    private void regresarAlMenuPrincipal() {
        if (!frameStack.isEmpty()) {
            frameStack.pop().dispose();

            if (!frameStack.isEmpty()) {
                frameStack.peek().setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}