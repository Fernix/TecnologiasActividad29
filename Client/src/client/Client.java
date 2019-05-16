/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaz.IClient;
import interfaz.Image;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author soy-y
 */
public class Client extends UnicastRemoteObject implements IClient{
    
    private int ventanas = 0;
    
    public Client() throws RemoteException {
        
    }

    @Override
    public void iniciaProcesamiento(Image imagenes) throws RemoteException {
           new Thread(new FrameImage(imagenes, ventanas++)).start();
        
    }
    
}
