#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<unistd.h>
#include<ctype.h>
#include<arpa/inet.h>

int main()
{
  int socketID;
  int connectRet;
  int sendRet;
  int countOfMessages;
  int lenOfMessages;
  int SEND_NO = 0;		
  int i, k;
  
  //server Adresse
  struct sockaddr_in myServerAddr;
  myServerAddr.sin_family = AF_INET;
  myServerAddr.sin_port = htons(2304);
  myServerAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
  
  socketID = socket(AF_INET, SOCK_STREAM, 0);
  
  if(socketID<0)
  {
    printf("Error at socket in client\n");
  }
  /*else
  {
    printf("socket in client correct\n");
  }*/
  
  //Verbindungsaufbau mit dem Server
  connectRet = connect(socketID, (struct sockaddr * ) &myServerAddr, sizeof(struct sockaddr) );
    if(connectRet<0)
  {
    printf("Error at connect in client\n");
  }
  /*else
  {
    printf("connect in client correct\n");
  }*/
  
  //Endlosschleife zum Abfragen der Nachricht und deren Länge
  while(1)
  {
    printf("Anzahl und Länge der Nachricht eingeben\n");
    scanf("%d %d", &countOfMessages, &lenOfMessages);
    
    //Puffer für Nachrichten in der gegebenen Länge erstellen
    // plus 2 Byte für die Länge
    char buf[lenOfMessages + 2];
    char* dataPtr = buf +2;
    
    short int* ptr;
    ptr = (short int*) buf;
    *ptr = lenOfMessages;
    
    //Schleife um gegebene Anzahl an Nachrichten zu versenden
    for(k= 0; k<countOfMessages; k++)
    {      
      //Puffer mit Nachricht füllen
      for(i = 0; i<lenOfMessages; i++)
      {
	*(dataPtr+i) = SEND_NO % 256;
      }
      
      //senden der Nachricht und des Längenheaders
      sendRet = send(socketID, buf, (lenOfMessages +2), 0);
      if(sendRet<0)
      {
	printf("Error at send in client\n");
      }
      /*else
      {
	printf("send in client correct\n");
      }*/

      //zählt bereits versendete Nachrichten mit
      SEND_NO++;
    }//Ende der for->eine Nachricht versendet
 
  }// Ende der while-Schleife , das ja nicht eintreten wird...
  //hier käme das close, wenn man aus der while tatsächloch rauskäme
  return 0;
}