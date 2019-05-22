/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaz.Image;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

/**
 *
 * @author soy-y
 */
public class Main {

    private static JFrame frame;
    private static JDesktopPane desktopPane;
    private static JButton boton;
    private static JLabel label;
    private static JLabel numeroClientes;
    private static JInternalFrame jif;
    private static JInternalFrame jifProgreso;
    private static JPanel panel;
    private static  JPanel panelProgreso;
    private  static JTable tabla;
    private static Server server;

    private static void gui() {
        iniciarGUI();
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    panel = new JPanel();
                    int numcli = server.getClientes().size();
                    numeroClientes.setText(Integer.toString(numcli));

                    repartirImagenes(server.getLista());
                } catch (RemoteException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });


    }
    
    private static void iniciarGUI() {
        frame = new JFrame("Examen");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        desktopPane = new JDesktopPane();

        frame.setContentPane(desktopPane);
        frame.setVisible(true);        

        boton = new JButton();
        boton.setText("Iniciar");
        boton.setSize(100, 100);
        boton.setVisible(true);
        
        frame.add(boton);

        label = new JLabel();
        label.setText("Numero de clientes");
        label.setVisible(true);
        label.setHorizontalAlignment(JLabel.CENTER);

        jif = new JInternalFrame();
        jif.setSize(200, 100);
        jif.setVisible(true);   
        
        frame.add(jif);
        
        panel = new JPanel();
        panel.setVisible(true);
        panel.add(label);
        
        numeroClientes = new JLabel();
        numeroClientes.setText("0");
        
        panel.add(numeroClientes);

        jif.add(panel);
        
        jifProgreso = new JInternalFrame("Progreso");
        jifProgreso.setSize(200, 400);
        jifProgreso.setVisible(true);   
        
        frame.add(jifProgreso);
        
        panelProgreso = new JPanel();
        panelProgreso.setVisible(true);
        panelProgreso.setSize(200,400);

        jifProgreso.add(panelProgreso);
        
        JScrollPane scroll = new JScrollPane(panelProgreso, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jifProgreso.add(scroll);
        
        panelProgreso.setLayout(new BoxLayout(panelProgreso,BoxLayout.Y_AXIS));
    }
    
    public static void mostrarProgreso(int porcentaje, int id) {
        panelProgreso.add(new JLabel("Porcentaje: " + 
                Integer.toString(porcentaje) + 
                    "  " + "ID: " + 
                Integer.toString(id) + ""));
        
        
    }

    
    
    public static void repartirImagenes(ArrayList<Image> imagenes) throws RemoteException {

        if (server.getClientes().isEmpty()) {
            return;
        }
        
        
        int tamaño = imagenes.size();
        int jugador = server.getClientes().size();
        System.out.println("Tamaño: " + jugador);


        for (Image imagen : imagenes) {
           
             if (jugador == 0) { 
                 jugador = server.getClientes().size();
             }
             jugador--;
            server.getClientes().get(jugador).iniciaProcesamiento(imagen);
            
            
        }
        
    }

    public Main() {
        gui();
        try {
            server = new Server();

            server.iniciar();
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Main();

    }

}
