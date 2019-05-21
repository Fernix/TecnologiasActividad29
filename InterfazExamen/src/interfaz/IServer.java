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
public interface IServer extends Remote{

/**
 * Notifica el porcentaje al Servidor.
 * @param porcentaje
 * @param id
 * @throws RemoteException 
 */    
    
public void notificaPorcentaje(ReporteDescarga rd) throws RemoteException;

/**
 * Inicia el registro del cliente en el Servidor, El servidor devuelve su Id de cliente para su posterior uso.
 * @param cliente
 * @return
 * @throws RemoteException 
 */
 
public int registraCallBackCliente(IClient cliente) throws RemoteException;

/**
 * Quita el registro del cliente en el Servidor.
 * @param cliente
 * @throws RemoteException 
 */

public void desregistraCallBackCliente(IClient cliente) throws RemoteException;
}
