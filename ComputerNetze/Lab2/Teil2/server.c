#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<unistd.h>
#include<ctype.h>
#include<arpa/inet.h>
#include<string.h>

int main()
{
  int socketID;
  int bindRet;
  int listenRet;
  int socketIDfSendAndRecv;
  int recvRet;
  int RECV_NO = 0;	
  int i;
  int length;
  int recBytes;
  
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
  unsigned int lenOfClientAddr = sizeof(struct sockaddr);//oder sizeof(struct sockaddr_in); ?
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
    //reset der empfangenen Bytes
    recBytes = 0; 
    //Puffer für die zu empfangende Nachricht, max 64 kb =64000Byte
    char buf[64000];
    
    //empfange zuerst nur die Länge, die ersten 2 Bytes
    recvRet = recv(socketIDfSendAndRecv, &buf, sizeof(short int), 0);
    if(recvRet<0)
    {
      printf("Error at recv in server \n");
    }
    /*else
    {
      printf("recv in server correct\n");
    }*/
    
    //short int pointer seigt auf buf
    short int* ptr;
    ptr = (short int*) buf;
 
    //weise die empfangene Länge aus buf einer int-Variablen zu
    length = *ptr;
    
    //lege Puffer an, in den die komplette Nachricht soll
    char finalBuf[length];

    //solange ich weniger als length Bytes habe ist die Nachricht nicht vollständig
    while (recBytes<length)
    { 
      //schreibe empfangenes in buf max 64000Byte
      recvRet = recv(socketIDfSendAndRecv, &buf, length, 0);
      if(recvRet<0)
      {
	printf("Error at recv in server\n");
      }
      /*else
      {
	printf("recv in server correct\n");
      }*/
  
      //zähle empfangene Bytes
      recBytes += recvRet;
  
      //setze empfangenes an die richtige Stelle in finalBuf
      memcpy(finalBuf, buf, length);

    }//Ende innerer while mit recv => Nachricht komplett da 
    
    //nachricht ausgeben
    printf("RECV_NO: %d, Länge: %d, Inhalt: %x,", RECV_NO, length, finalBuf[0]);
    for(i=1; i<length; i++)
    {
      printf("%x,",finalBuf[i]);
    }
    printf("\n");
      
    RECV_NO++; 
  }//Ende der while, wird aber nie erreicht  
  return 0;
}