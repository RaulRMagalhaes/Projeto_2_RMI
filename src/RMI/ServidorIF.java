package RMI;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServidorIF extends Remote {
  
  void registraCliente(String urlCliente) throws  RemoteException, MalformedURLException, NotBoundException;
  
  String getMsgServidor() throws RemoteException;
  
  ArrayList<String> getListaUrlClientes() throws RemoteException;
}
