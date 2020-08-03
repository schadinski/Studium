#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<unistd.h>
#include<ctype.h>
#include<arpa/inet.h>
#include<stdlib.h>

void myPrint(char[], int, int);

int main()
{
  int socketID;
  int bindRet;
  int listenRet;
  int socketIDfSendAndRecv;
  int recvRet;
  int RECV_NO = 0;	
  
  //struct sockaddr mySockAddr;
  //server Adresse
  struct sockaddr_in myServerAddr;
  myServerAddr.sin_family = AF_INET;
  myServerAddr.sin_port = htons(2304);
  myServerAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
  //client adresse, wird in accept empfangen
  struct sockaddr_in myClientAddr;
  struct sockaddr_in* pMyClientAddr = &myClientAddr;
  struct sockaddr* pClientAddr = (struct sockaddr*) pMyClientAddr;
  unsigned int lenOfClientAddr = sizeof(struct sockaddr);
  unsigned int *pLenOfClientAddr = &lenOfClientAddr;
  
  socketID = socket(AF_INET, SOCK_STREAM, 0);
  
  if(socketID<0)
  {
    printf("Error at socket in server\n");
  }
  /*else
  {
    printf("socket in server correct\n");
  }*/
 
  //Festlegen der Server-Adresse
  bindRet = bind(socketID, (struct sockaddr * ) &myServerAddr, sizeof(myServerAddr) );
  if(bindRet<0)
  {
    printf("Error at bind in server\n");
  }
  /*else
  {
    printf("bind in server correct\n");
  }*/
  
  //Server in ListeningMode versetzen
  listenRet = listen(socketID, 5);
  if(listenRet<0)
  {
    printf("Error at listen in server\n");
  }
  /*else
  {
    printf("listen in server correct\n");
  }*/
 
  //return wird für send und recv verwendet
  //2. Parameter enthält nach accept pointer auf Adressdaten des client
  socketIDfSendAndRecv = accept(socketID, pClientAddr, pLenOfClientAddr);
  if(listenRet<0)
  {
    printf("Error at listen in server\n");
  }
  /*else
  {
    printf("listen in server correct\n");
  }*/
  
  //endlosschleife
  while(1)
  {
    //Puffer für die zu empfangende Nachricht, max 64 kb =64000Byte
    char buf[64000];
    recvRet = recv(socketIDfSendAndRecv, &buf, sizeof(buf), 0);
    if(recvRet<0)
    {
      printf("Error at recv in server\n");
    }
    /*else
    {
      printf("recv in server correct\n");
    }*/
 
    myPrint(buf, RECV_NO, recvRet);
    /* nicht optimiertes Output
    printf("RECV_NO: %d, Länge: %d, Inhalt: ", RECV_NO, recvRet);
    for(i=0; i<recvRet; i++)
    {
      printf("%X,",buf[i]);
    }
    printf("\n");*/
    
    
    RECV_NO++; 
  }//Ende der while, wird aber nie erreicht  
  return 0;
}

void myPrint(char buf[], int RECV_NO, int recvRet)
{
  char lastRead = '#';
  int flag = 0;
  int j;
 
  printf("RECV_NO: %d, Länge: %d, Inhalt: ", RECV_NO, recvRet);
  
  for(j=0; j<recvRet; j++)
  {
    //ist es das selbe Zeichen wie das letzte gedruckte?
    if((lastRead == buf[j]) )
    {
      //geschachtelte if-Abfrage nötig, weil sonst else ausgeführt wird wenn gleiches Zeichen und schon Punkte gedruckt
      //wurden für dieses Zeichen noch keine Punkte gedruckt?
      if( flag == 0) 
      {
	printf(".. ");
	flag = 1;
      }
      //else-fall: es wurden schon Punkte gedruckt, also  mache nichts und gehe zum nächsten Zeichen
    }
    //anderes Zeichen => drucke es
    else
    {
      printf("%x", buf[j]);
      flag = 0;
    }
    //merke das eben behandelte Zeichen
    lastRead = buf[j];
  }
      printf("\n"); 
}