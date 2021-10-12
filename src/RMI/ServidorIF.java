package RMI;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote; 
import java.rmi.RemoteException;

public interface ServidorIF extends Remote {
  
  void registraCliente(String urlCliente) throws  RemoteException, MalformedURLException, NotBoundException;
  
  @Deprecated
  void transmiteMsg(String urlClienteOrigem, String mensagem) throws  RemoteException;

}
