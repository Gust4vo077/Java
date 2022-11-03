import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Arrays;



public class Main extends Thread{
  static int MaxNos=5;
  static int ponteiro;
  static No[] nos = new No[MaxNos];
  static int[][] matriz_tabela = new int[MaxNos][2];
  static int[] matriz_conteudo = new int[MaxNos];


  public static void main(String[] args) {

      Configurar();
      Main thread = new Main();
      thread.start();

  }



  public void run() {
    int ponteiro=0;
    nos[ponteiro].FazAlgo();// para o 1 no fazer algo  

    while(true){
      
      
      try {

       nos[ponteiro].MandarTolken(); // manda um mensagem "1", a qual significa uqe o tolken foi mandado e trata se o algum No cair
        
       nos[ponteiro+1].Entrada();// na funcao entra ele recebe o input o "1" e faz algo na sessão critica
       
       //
       //se o no cair evoca o metodo ConsertarAestrutura() para concertar a estrura e verificar se tem a necessidade de eleição
       //


      } catch (IOException e) {
        e.printStackTrace();
      }

      ponteiro=ponteiro+1;
      ponteiro=ponteiro%MaxNos;//fica no loop e o % não permite ele tem um valor no ponteiros maior que MaxNos
    }
  }

   
   public void ConsertarAestrutura(){

      int flagEleição= 0;
    if( PrecisadeEleição() == 1  )// set uma flaga se a necessidade  de eleiçao para arrrumar no final 
     flagEleição= 1;
    
     if(ponteiro == MaxNos-1 ){
        matriz_tabela[ponteiro][1]=matriz_tabela[1][1];  //pega o proximo no do no que acabou de cair;
      nos[ponteiro].proxEndereço=nos[1].proxEndereço;
      }
      else{
      matriz_tabela[ponteiro][1]=matriz_tabela[ponteiro+1][1];  //pega o proximo no do no que acabou de cair;
      nos[ponteiro].proxEndereço=nos[ponteiro+1].proxEndereço; //pega o proximo no do no que acabou de cair;
      }
      
      MaxNos=MaxNos-1; // numerosd de nos diminuiu 
       if(ponteiro == MaxNos)// se estive no ultimo lugar do array ele volta para o inicio
        ponteiro=0;

      for(int i =ponteiro;i<MaxNos;i++)
       matriz_conteudo[i]=matriz_conteudo[i+1]; // coloca a posiçao a frente atras, recolocando cada posiçao no lugar certo 

       if(flagEleição == 1)
        ponteiro=Eleição(); // set o ponteiro para o no com conteudo maior depois de tirar um o maior no setando o tolken para a posicao do ponteiro

      
   } 

   static public int Eleição(){
     //manda mensagem para tdos nos acima do no que percebeu que ele estava fantando
     return acharMaiorConteudoNaeleicao();
   }

   static public int PrecisadeEleição(){
      
    if( maiorCounteudo() == nos[ponteiro+1].conteudo )
     return 1; // precisa
    else
     return 0; // não precisa
  }
  static public int  maiorCounteudo(){
    int maior=0;
  for (int j = 0; j < matriz_conteudo.length; j++) {  
    if(matriz_conteudo[j] > maior){  
      maior= matriz_conteudo[j];  
     }  
   }  
   return maior;
  }

  static public int  acharMaiorConteudoNaeleicao(){

    int[] array = matriz_conteudo;
    int i, j; 
    for (i = 0; i < array.length-1; i++)       
    for (j = 0; j < array.length-i-1; j++) if (array[j] > array[j+1]) 
    {int temp = array[j]; 
    array[j] = array[j+1]; 
    array[j+1] = temp;} 

    for (i = 0; i < array.length-1; i++)   {    
     if ( nos[i].conteudo == array[array.length-2])// compara o  penultimo maior valor do array ordenado para achar a posição no array de Nos
        break;
    }

   return i; // i seria a posiçao no array de nos que representa o maior counteudo virando o maior no 
  }

  static public void ConectarNos(){
    for(int i=0;i<5;i++){
        nos[i+1].Escutar();// nos da frente se deixar seu servidor escutando 
        nos[i].Conectar();// o no de tras tenta se conectar com o da frente para criar essa corrente 
      
    }



  }


  // configução inicial dos nós
  public static void Configurar(){
    Random gerador = new Random();
    for(int i=0;i<5;i++)
    matriz_conteudo[i]=gerador.nextInt(61);

    nos[0]= new No(1,"127.0.0.2","127.0.0.3",1000,2000,matriz_conteudo[0]);
    nos[1]= new No(2,"127.0.0.3","127.0.0.4",2000,3000,matriz_conteudo[1]);
    nos[2]= new No(3,"127.0.0.4","127.0.0.5",3000,4000,matriz_conteudo[2]);
    nos[3]= new No(4,"127.0.0.5","127.0.0.6",4000,5000,matriz_conteudo[3]);
    nos[4]= new No(5,"127.0.0.6","127.0.0.2",5000,1000,matriz_conteudo[4]);
    //faz uma tabela com as rotas dos nos, atribuindo as portas de origem pra o destino
    for(int i=0,valores=1000;i<5;i++,valores=valores-1000){
      for(int j=0;j<2;j++,valores=valores+1000){
        matriz_tabela[i][j]= valores;
        
      }
    }
    matriz_tabela[4][1]= 1000;

  }
 
}
    	 
    	 
    	 
      
	 

