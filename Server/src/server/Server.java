/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaz.IClient;
import interfaz.IServer;
import interfaz.Image;
import interfaz.ReporteDescarga;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author soy-y
 */
public class Server extends UnicastRemoteObject implements IServer{

    private static final long serialVersionUID = 9090898209349823403L;
    private static final int port = 3232;
    private ArrayList<Image> lista;
    private ArrayList<IClient> clientes;
    private int id = 0;
    
  public Server() throws RemoteException {
    clientes = new ArrayList();
    lista = new ArrayList();

    
    clientes = new ArrayList();
    Conection conexion = new Conection();

    lista = conexion.obtieneListaImagenes();
  }
    
    public void iniciar() {
        try {
            String direccion = (InetAddress.getLocalHost()).toString();
            System.out.println("Iniciando servidor en " + direccion + ":" + this.port);
            
            Registry registro = LocateRegistry.createRegistry(this.port);
            registro.bind("MiServidor", (IServer) this);
            
        }
        catch(Exception ex) {
            System.out.println("Error en" + ex.getMessage());
        }
    }

    public ArrayList<Image> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Image> lista) {
        this.lista = lista;
    }

    public ArrayList<IClient> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<IClient> clientes) {
        this.clientes = clientes;
    }
    
    @Override
    public void notificaPorcentaje(ReporteDescarga rd) throws RemoteException {
        Conection conexion = new Conection();
        conexion.reportarDescarga(rd);
        
    }

    @Override
    public int registraCallBackCliente(IClient cliente) throws RemoteException {
        if (this.clientes.size() <= 9) {
            this.clientes.add(cliente);
            return id++;
        }
        return -1;
    }

    @Override
    public void desregistraCallBackCliente(IClient cliente) throws RemoteException {
        this.clientes.remove(cliente);
    }
    
}
