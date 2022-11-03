import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Random;


public class No{
  Socket socketRecebe = null;// socket que sera aceito pelo server socket
  Socket socketDestino = null;// socket que voce tentará se conectar
  ServerSocket servidor = null;
  int porta;
  int portaProximoNo; // ip do proximo no
  int conteudo; // valor que determinado qual no é maior que o outro 
  int NumerodoNo; // ordem do no como no 1, 2 , 3 ...
  String endereço;
  String proxEndereço;
  int tolken;
  public No(int NumerodoNo,String endereço, String proxEndereço,int porta,int portaProximoNo,int conteudo){
	this.porta=porta;
        this.endereço=endereço;
        this.proxEndereço=proxEndereço;
        this.portaProximoNo=portaProximoNo;
        this.conteudo=conteudo; 			 
        this.NumerodoNo=NumerodoNo;

        try {
         InetAddress addr = InetAddress.getByName(endereço);       
         servidor= new ServerSocket(this.porta,1,addr);
        } catch (IOException e) {
                e.printStackTrace();
        }
        
      
        
       }
    	 
  public void Conectar(){
        try {
                socketDestino = new Socket(this.proxEndereço,this.portaProximoNo);
        } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }  
        }   	 
 
  public void Escutar(){
        try {
                socketRecebe = servidor.accept();
                

        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        }

  public void MandarTolken() {
        try {
                DataOutputStream  out  = new DataOutputStream(socketDestino.getOutputStream()); // mandar o 1 para o proximo no, que significa o tolken esta sendo mandado
                out.write(1);
        } catch (IOException e) {
                CaiuNo();
                // No metodo run na main arruma a estrutura se cair na função ConsertarAestrutura()
        }
 
  }

  public void CaiuNo(){
        System.out.println("O "+this.portaProximoNo+" Caiu, e esta arrumando estrutura");
  } 



  public void Entrada() throws IOException{
        DataInputStream in = new DataInputStream( new BufferedInputStream(socketRecebe.getInputStream()) );
        
        if( in.equals("1")){ // se o in é igual a 1, o tolken foi mandado e pode usar a seesão critica
                   System.out.println("O "+this.portaProximoNo+" esta com o tolken ");
                   this.tolken=1;
                   FazAlgo();
                   this.tolken=0;

         }
        }

  public void FazAlgo(){
        System.out.println("O no "+this.NumerodoNo+" Esta com o tolkien e fazendo algo na Sessão critica e esta com o tolken");
        }  
      
 	 
}
