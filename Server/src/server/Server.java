/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaz.IClient;
import interfaz.IServer;
import interfaz.Image;
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
    private ArrayList<Image> listaImagenes;
    private int id = 0;
    
  public Server() throws RemoteException {
    lista = new ArrayList();
    Image imagen1 = new Image("imagen 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRivmLWgLM0PEyuvm-V1MZz0NFdx5L_FVA3zTetcbUx4NKGabdh");
    Image imagen2 = new Image("imagen 2", "https://mondrian.mashable.com/uploads%252Fcard%252Fimage%252F640145%252F4f6bf4e0-8921-4169-87ca-2ae56c9cf5be.jpg%252F950x534__filters%253Aquality%252890%2529.jpg?signature=vN7lrDvBVyxOWhIBIVFOwJhAaTo=&source=https%3A%2F%2Fblueprint-api-production.s3.amazonaws.com");
    Image imagen3 = new Image("imagen 3", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUQZDNKc-zKvFg7JJRVVUdmW7Qb-Aadm1TnEEzHVWp6EANPf_nbw");
    Image imagen4 = new Image("imagen 4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm2f9w5fRz1ZeI9uctxSFbbmRC2BCrVk10T2XSxdS5Aw7I7tM0");
    Image imagen5 = new Image("imagen 5", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgRFCk5-Aaqk880qlGroO3AZdzy0knslvSKwJPMx8VApqtNxit4g");
    Image imagen6 = new Image("imagen 6", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYphEqgesFOpDkrCkpWgnL3G-nN5QIVCMpl-3Rg4ZPdOVwxg9B");
    Image imagen7 = new Image("imagen 7", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRq0onbn-UVguMQM1Bzr28Xqo4nMyVArFimfS3SMb0D_joXcjQ6");
    Image imagen8 = new Image("imagen 8", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcGiUiMV6vvTOmTi7Vjk9uEd9lnVj4C2Ioa2UvfTRga_PdVxHy");
    Image imagen9 = new Image("imagen 9", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMJPVf9-KjFk05_Tnyuiy1Y3poNI0gRDTEinwDBrZHCArqDNH0");
    Image imagen10 = new Image("imagen 10", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRivmLWgLM0PEyuvm-V1MZz0NFdx5L_FVA3zTetcbUx4NKGabdh");
    clientes = new ArrayList();
    Conection conexion = new Conection();
    conexion.añadeImagen(imagen1);
    conexion.añadeImagen(imagen2);
    conexion.añadeImagen(imagen3);
    conexion.añadeImagen(imagen4);
    conexion.añadeImagen(imagen5);
    conexion.añadeImagen(imagen6);
    conexion.añadeImagen(imagen7);
    conexion.añadeImagen(imagen8);
    conexion.añadeImagen(imagen10);
    listaImagenes = conexion.obtieneListaImagenes();
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
    public void notificaPorcentaje(int porcentaje, int id) throws RemoteException {
        Main.mostrarProgreso(porcentaje, id);
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
