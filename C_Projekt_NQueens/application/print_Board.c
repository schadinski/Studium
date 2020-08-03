/**
* @file print_Board.c
* @brief print board in step-by-step
* @author Katharina Schwab
* @date 29.12.2015
*/
#include"..\common_includes\ProjectSettings.h"
/**
* @fn void fprint_board(const struct nQueens* const psnQueens)
* @brief print board in the middle of console
* @param psnQueens
* @author Katharina Schwab
* @date 29.12.2015
*/
void fprint_board(const struct nQueens* const psnQueens)
{
   int iRow;                              //rows
   int iCol;                              //columns
   short int siPrintRow;                  //new row for printing
   siPrintRow = 4;                        //start printing at this position
   
   for (iRow = 0; iRow < psnQueens->iBoardSize; iRow++)  //count the columns
   {
      _gotoxy(31,siPrintRow);                            //switch to that point
      for (iCol = 0; iCol < psnQueens->iBoardSize; iCol++)// count the rows
      {
         printf("%c", psnQueens->acBoard[iRow][iCol]);   // print the value of board at fields i, j         
      } 
      printf("\n");                       //new line
      siPrintRow++;
   }
}