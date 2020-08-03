/**
 * @file initial_Board.c
 * @brief initial board array and call function calcnQueens
 * @author Katharina Schwab
 * @date 08.01.2016
 */
#include"..\common_includes\ProjectSettings.h"

 /**
  * @fn void fInitial_Board(struct nQueens* const psnQueens)
  * @brief initial board array with 0 and call calcnQueens
  * @param psnQueens
  * @author Katharina Schwab
  * @date 08.01.2016
  */
void fInitial_Board(struct nQueens* const psnQueens)
{
   int iRow;
   int iCol;
    
   for (iRow = 0; iRow < SIZE; iRow++)                      //fill actually array with 0 until boardsize,count through rows
   {
      for (iCol = 0; iCol < SIZE; iCol++)                   //count through the columns
      {
         psnQueens->acBoard[iRow][iCol] = '0';
      }
   }

   //psnQueens->lStartTimeStamp = clock();        //get ticks since programm was started and change it to seconds

   iRow = 0;
   fcalcnQueens(psnQueens, iRow);                    //call recursiv function to find solutions
}