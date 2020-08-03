
#include <unistd.h>
#include <ctype.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<errno.h>

int main()
{
    //server adress 
    struct sockaddr_in serverAddr;
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(2053);
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");           //loopback addr  127.0.0.1

    int socketID;
    int sendToReturn;
    int recvReturn;
    int lengthForSendingMessage = 0;
    char myExitBuffer[] = "Exit";
    unsigned int flag = 0;			//flag = 0 -> no exit command
    int connectRet;
    void perror (const char* prefix_text);
    

    socketID = socket(AF_INET, SOCK_DGRAM, 0);
//Error check
    if(socketID < 0) {
        printf("Error at socket in client.c %d\n", socketID);
    } else {
        printf("socketID in client correct\n");
    }
    
    //Verbindungsaufbau mit dem Server
  connectRet = connect(socketID, (struct sockaddr * ) &serverAddr, sizeof(struct sockaddr) );
  if(connectRet<0)
  {
    printf("Error at connect in client\n");
  }
  else
  {
    printf("connect in client correct\n");
  }

//just end with exit command
    while(1) {
//memory for massage to send
        char* myTempBuffer = (char*) calloc (255, (sizeof(char) ));

//get massage to send
        printf("your massage please\n");
        fgets( myTempBuffer, 255, stdin);
//just for client.out
        printf("Your message is %s", myTempBuffer);

        lengthForSendingMessage = strlen(myTempBuffer);

        int exitResult = strncmp(myTempBuffer, myExitBuffer, 4);
        if(exitResult == 0) {
            flag = 1;
        }
//memory for massage to send
        char* myBufferToSend = (char*) calloc (lengthForSendingMessage, (sizeof(char) ) );
//copy only the real message, not whole buffer
        strncpy(myBufferToSend, myTempBuffer, lengthForSendingMessage);

        sendToReturn = send( socketID, myBufferToSend, lengthForSendingMessage, 0);
        if(sendToReturn < 0) {
            printf("Error in send in client.c");
        } else {
            printf("send in client correct\n");
        }

//got an exit command
        if(flag == 1) {
            printf("Bye bye\n");
	    close(socketID);
            return 0;
        }

//memory for massage to receive
        char* myReceivingBuffer = (char*) calloc(255, (sizeof(char) ));
//call is a blocking call
        recvReturn = recv(socketID, myReceivingBuffer, 255, 0);
//Error check
        if(recvReturn == -1) {
            //printf("Error at revc in client");
	    perror ("Fehler nach recv in client: ");
        } else {
            printf("recv in client correct\n");
        }
        printf("Statistics for your messages: %s\n", myReceivingBuffer);

        free(myReceivingBuffer);
        free(myBufferToSend);
        free(myTempBuffer);
        lengthForSendingMessage = 0;
    }

    return 0;
}