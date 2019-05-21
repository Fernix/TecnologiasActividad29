/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaz.IServer;
import interfaz.ReporteDescarga;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author soy-y
 */
public class Main {

    private static final int PORT = 3232;
    private static final String NAMESERVICE = "MiServidor";
    private static final String HOSTNAMESERVER = "localhost";
    
    private static JFrame frame;
    private static JDesktopPane desktopPane;
    private static IServer server;
    private static Client cliente;
    
    private static int id;
    
    private static ReporteDescarga rd;

    public static ReporteDescarga getRd() {
        return rd;
    }

    public static void setRd(ReporteDescarga rd) {
        Main.rd = rd;
    }

    public static IServer getServer() {
        return server;
    }

    public static void setServer(IServer server) {
        Main.server = server;
    }

    public static JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public static void setDesktopPane(JDesktopPane desktopPane) {
        Main.desktopPane = desktopPane;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Main.id = id;
    }
    
    public Main() throws RemoteException {
        gui();
        registro();
    }
    
    
    private static void registro() {
        try {
            
            cliente = new Client();
            Registry registro = LocateRegistry.getRegistry(HOSTNAMESERVER, PORT);
            server = (IServer) registro.lookup(NAMESERVICE);
            id = server.registraCallBackCliente(cliente);
            rd = new ReporteDescarga();
            rd.setId(id);
            rd.setNombreHost(HOSTNAMESERVER);
        }
        catch(Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void gui() {
        frame = new JFrame("Examen");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        desktopPane = new JDesktopPane();
        
        frame.setContentPane(desktopPane);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
               int i = JOptionPane.showConfirmDialog(desktopPane, "¿Salir de la aplicación?");
               if(i == 0) {
                   
                   try {
                       server.desregistraCallBackCliente(cliente);
                   } catch (RemoteException ex) {
                       Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
               }
            }
            
        });
        
        
    }
    
    public static void main(String[] arg) throws RemoteException {
        SwingUtilities.invokeLater(new Runnable(){
        
            public void run() {
                try {
                    new Main();
                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
        });
    }
}
