
#include <unistd.h>
#include <ctype.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    //server adress
    struct sockaddr_in serverAddr;
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(53);
    serverAddr.sin_addr.s_addr = inet_addr("192.168.178.43");           //loopback addr  127.0.0.1
    struct sockaddr_in* serverAddrP = &serverAddr;

    struct sockaddr_in getMassageFromAddr;
    struct sockaddr_in* getMassageFromAddrP = &getMassageFromAddr;

    unsigned int addrLengthSource = sizeof(struct sockaddr);


    int socketID;
    int sendToReturn;
    int recvReturn;
    int lengthForSendingMessage = 0;
    char myExitBuffer[] = "Exit";
    unsigned int flag = 0;						//flag = 0 -> no exit command

    socketID = socket(AF_INET, SOCK_DGRAM, 0);
//Error check
    if(socketID < 0) {
        printf("Error at socket in client.c %d\n", socketID);
    } else {
        //printf("socketID in client correct\n");
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

//param dest_addr_len
        unsigned int addrLengthDestiny = sizeof(struct sockaddr);

        lengthForSendingMessage = strlen(myTempBuffer);

        int exitResult = strncmp(myTempBuffer, myExitBuffer, 4);
        if(exitResult == 0) {
            flag = 1;
        }
//memory for massage to send
        char* myBufferToSend = (char*) calloc (lengthForSendingMessage, (sizeof(char) ) );
//copy only the real message, not whole buffer
        strncpy(myBufferToSend, myTempBuffer, lengthForSendingMessage);

        sendToReturn = sendto( socketID, myBufferToSend, lengthForSendingMessage, 0, (struct sockaddr*) serverAddrP, addrLengthDestiny);
        if(sendToReturn < 0) {
            printf("Error in sendto in client.c");
        } else {
            //printf("sendTo in client correct\n");
        }

//got an exit command
        if(flag == 1) {
            printf("Bye bye\n");
            return 0;
        }

//memory for massage to receive
        char* myReceivingBuffer = (char*) calloc(255, (sizeof(char) ));
//call is a blocking call
        recvReturn = recvfrom(socketID, myReceivingBuffer, 255, 0, (struct sockaddr*) &getMassageFromAddrP, &addrLengthSource);
//Error check
        if(recvReturn < 0) {
            printf("Error at revcfrom in server.c");
        } else {
            //printf("recvfrom in client correct\n");
        }
        printf("Statistics for your messages: %s\n", myReceivingBuffer);

        free(myReceivingBuffer);
        free(myBufferToSend);
        free(myTempBuffer);
        lengthForSendingMessage = 0;
    }

    return 0;
}