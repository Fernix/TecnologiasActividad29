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
    
    public Client() throws RemoteException {
        
    }

    @Override
    public void iniciaProcesamiento(List<Image> imagenes) throws RemoteException {
        int i=1;
        for(Image img : imagenes) {
            Main.getServer().notificaPorcentaje(
                    (100 / imagenes.size()) * i
                    , Main.getId()); 
            new Thread(new FrameImage(img, i++)).start();
            
             
            
        }
    }
    
}
