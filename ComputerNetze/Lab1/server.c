
#include <unistd.h>
#include <ctype.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
    myAddr.sin_port = htons(53);
    myAddr.sin_addr.s_addr = inet_addr("192.168.178.43");           //loopback addr 127.0.0.1
    struct sockaddr_in* myAddrP = &myAddr;

    struct sockaddr_in getMassageFromAddr;
    struct sockaddr_in* getMassageFromAddrP = &getMassageFromAddr;

    int socketID;
    int bindReturn;
    int recvReturn;
    int sendToReturn;
    unsigned int addrLengthSource = sizeof(struct sockaddr);
    int lengthAllMassages = 0;
    int lengthForSendingMessage = 0;
    int nrOfMessages = 0;
    int average = 0;
    char myExitBuffer[] = "Exit";



    socketID = socket(AF_INET, SOCK_DGRAM, 0);
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

//just end with exit command
    while(1) {
        //memory for massage to receive and to send back
        char* myReceivingBuffer = (char*) calloc(255, (sizeof(char) ));
        char* myBufferToSend = (char*) calloc(255, (sizeof(char) ));

//call blocks
        recvReturn = recvfrom(socketID, myReceivingBuffer, 255, 0, (struct sockaddr*) &getMassageFromAddrP, &addrLengthSource);
//Error check
        if(recvReturn < 0) {
            printf("Error at revcfrom in server.c");
        } else {
            printf("recvfrom in server correct\n");
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

//param dest_addr_len
        unsigned int addrLengthDestiny = sizeof(struct sockaddr);

//call function to analyse received message
        analayseReceivedMessage(&(*myBufferToSend), &(*myReceivingBuffer), average);

        lengthForSendingMessage = strlen(myBufferToSend);
//memory for massage to send
        char* mySendingBuffer = (char*) calloc (lengthForSendingMessage, (sizeof(char) ));
        memcpy(mySendingBuffer, myBufferToSend, lengthForSendingMessage);

        sendToReturn = sendto( socketID, mySendingBuffer, lengthForSendingMessage, 0, (struct sockaddr*) &getMassageFromAddrP, addrLengthDestiny);
        if(sendToReturn < 0) {
            printf("Error in sendto in server.c");
        } else {
            printf("sendto in server correct\n");
        }

        free(myReceivingBuffer);
        free(myBufferToSend);
        free(mySendingBuffer);
        lengthForSendingMessage = 0;
        average = 0;
    }

    return 0;
}


