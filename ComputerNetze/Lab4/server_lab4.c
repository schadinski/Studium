
#include <unistd.h>
#include <ctype.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<errno.h>

//function to get string to return at client
void analayseReceivedMessage(char* myBufferToSend, char* myReceivingBuffer, int average)
{
    int strLength;
    int i;
    int countLower = 0;
    int countUpper = 0;

    strLength = strlen(myReceivingBuffer);
//printf("Len = %d\n", strLength);

    for(i = 0; i < strLength; i++) {
        if(islower(myReceivingBuffer[i])) {
            countLower++;
        }
        if(isupper(myReceivingBuffer[i])) {
            countUpper++;
        }
    }
    strLength--;
//printf("Up = %d and low = %d\n", countUpper, countLower);
    sprintf(myBufferToSend, "Len = %d Upper = %d Lower = %d AvgLen = %d", strLength, countLower, countUpper, average);
//printf("%s\n",myBufferToSend);
    return;
}

int main()
{
    //server adress
    struct sockaddr_in myAddr;
    myAddr.sin_family = AF_INET;
    myAddr.sin_port = htons(2053);
    myAddr.sin_addr.s_addr = inet_addr("127.0.0.1");           //loopback addr 127.0.0.1
    struct sockaddr_in* myAddrP = &myAddr;
    
    //client adresse, wird in accept empfangen
  struct sockaddr_in myClientAddr;
  struct sockaddr_in* pMyClientAddr = &myClientAddr;
  struct sockaddr* pClientAddr = (struct sockaddr*) pMyClientAddr;
  unsigned int lenOfClientAddr = sizeof(struct sockaddr);
  unsigned int *pLenOfClientAddr = &lenOfClientAddr;

    int socketID;
    int bindReturn;
    int recvReturn;
    int sendToReturn;
    int lengthAllMassages = 0;
    int lengthForSendingMessage = 0;
    int nrOfMessages = 0;
    int average = 0;
    char myExitBuffer[] = "Exit";
    int socketIDfSendAndRecv;
    int listenRet;
    void perror (const char* prefix_text);


    socketID = socket(AF_INET, SOCK_STREAM, 0);
//Error check
    if(socketID < 0) {
        printf("Error at socket in server.c %d\n", socketID);
    } else {
        printf("socketID in server correct\n");
    }

    bindReturn = bind(socketID, (struct sockaddr*) myAddrP, sizeof(struct sockaddr));
//Error check
    if(bindReturn != 0) {
        printf("Error at bind\n");
    } else {
        printf("bind in server correct\n");
    }

   
//Server in ListeningMode versetzen
  listenRet = listen(socketID, 5);
  if(listenRet==-1)
  {
    //printf("Error at listen in server\n");
    perror ("Fehler nach listen: ");
  }
  else
  {
    printf("listen in server correct\n");
  }
  printf("so weit\n");
   //return wird für send und recv verwendet
  //2. Parameter enthält nach accept pointer auf Adressdaten des client
  socketIDfSendAndRecv = accept(socketID, pClientAddr, pLenOfClientAddr);
  if(socketIDfSendAndRecv==-1)
  {
    printf("Error at accept in server\n socketID is %d\n",socketIDfSendAndRecv);
    sleep(10000);
  }
  else
  {
    printf("accept in server correct\n");
  }
  printf("socketID is %d\n",socketIDfSendAndRecv);
  printf("auch bis hierhin\n");
//just end with exit command
    while(1) {
        //memory for massage to receive and to send back
        char* myReceivingBuffer = (char*) calloc(255, (sizeof(char) ));
        char* myBufferToSend = (char*) calloc(255, (sizeof(char) ));

//call blocks
        recvReturn = recv(socketIDfSendAndRecv, myReceivingBuffer, 255, 0);
//Error check
        if(recvReturn == -1) {
            //printf("Error at revc in server.c");
	  perror ("Fehler nach recv: ");
        } else {
            printf("recv in server correct\n");
        }
//printf("Message: %s\n", myReceivingBuffer);
        int exitResult = strncmp(myReceivingBuffer, myExitBuffer, 4);
//if true got an exit command from client
        if(exitResult == 0) {
            printf("Bye bye\n");
            return 0;
        }


//average uprounded
        lengthAllMassages += strlen(myReceivingBuffer);
        lengthAllMassages--;
        nrOfMessages++;
        average = (lengthAllMassages / nrOfMessages);

//call function to analyse received message
        analayseReceivedMessage(&(*myBufferToSend), &(*myReceivingBuffer), average);

        lengthForSendingMessage = strlen(myBufferToSend);
//memory for massage to send
        char* mySendingBuffer = (char*) calloc (lengthForSendingMessage, (sizeof(char) ));
        memcpy(mySendingBuffer, myBufferToSend, lengthForSendingMessage);

        sendToReturn = send( socketIDfSendAndRecv, mySendingBuffer, lengthForSendingMessage, 0);
        if(sendToReturn < 0) {
            printf("Error in send in server.c");
        } else {
            printf("send in server correct\n");
        }

        free(myReceivingBuffer);
        free(myBufferToSend);
        free(mySendingBuffer);
        lengthForSendingMessage = 0;
        average = 0;
    }

    return 0;
}


