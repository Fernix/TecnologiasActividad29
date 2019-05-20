/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author soy-y
 */
public interface IClient extends Remote{
    
    /**
     * Inicia en el cliente la descarga paralelamente de las imágenes, las guarda en disco duro y notifica el avance (porcentaje) del procesamiento al servidor.
     * @param imagenes 
     */
    
<<<<<<< HEAD
public void iniciaProcesamiento(Image imagenes) throws RemoteException;
=======
public void iniciaProcesamiento(Image imagen) throws RemoteException;
>>>>>>> master
    
    
}
