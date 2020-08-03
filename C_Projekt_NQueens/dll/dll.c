#pragma warning(disable:4996)
/**
 * @file dll.c
 * @brief dll for nQueens
 * @author Katharina Schwab
 * @date 10.01.2016
 */
#include<string.h>
#include<stdio.h>
#include"Utilities.h"

/**
 * @fn _declspec(dllexport) void PrintToFile(short int siBoardPrinting, char acFileName[],int iNumbersOfSolutions, char acBoard[][], int iBoardSize)
 * @brief open file and print results in it
 * @param siBoardPrinting, acFileName[], iNumbersOfSolutions, acBoard[][], iBoardSize
 * @author Katharina Schwab
 * @date 10.01.2016
 */
_declspec(dllexport) void PrintToFile(short int siBoardPrinting, char acFileName[255],int iNumbersOfSolutions, char acBoard[12][12], int iBoardSize)
{
   char acActuallyFileName[255];
   strcpy(acActuallyFileName, acFileName);         //copy filename in acActuallyFileName

   short int siYValue = siBoardPrinting;                   //value is x-position to print 

   FILE* psFile;
   psFile = fopen(acActuallyFileName, "a");        //open txt for results

   if (psFile == NULL)                             //if file can not opened print error string
   {
      printf("Cannot open file");
   }


   //print the results in file
   fprintf(psFile, "Loesung Nummer %d\n", iNumbersOfSolutions);

   int i;                              //rows
   int j;                              //columns
   
   short int siXValue;                        //value is y-position to print
   siXValue = 3;

   //count through all rows af boardarray until the last used row by this boardsize
   for (i = 0; i <= (iBoardSize - 1); i++)
   {
      //start printing at position 3 in linie YValue, which get from calcnQueens
      _gotoxy(siXValue,siYValue);
      //print all columns of array-row i until last used column by this boardsize
      for (j = 0; j <= (iBoardSize - 1); j++)
      {
         fprintf(psFile, "%c", acBoard[i][j]);
      }
      //after printing all used places in column set columncounter + 1 
      fprintf(psFile, "\n");
      siYValue++;
   }
   fclose(psFile);                        //close file
   _gotoxy(0,0);
}