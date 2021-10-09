package RMI;


import java.rmi.Remote; 
import java.rmi.RemoteException;

public interface ClienteIF extends Remote {

  String getMsgEnviada() throws  RemoteException;
  
  void recebeMsg(String mensagem) throws  RemoteException;
  
  String getUrlCliente() throws  RemoteException;

}


