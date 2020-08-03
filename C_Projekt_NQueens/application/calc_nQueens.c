/**
 * @file calc_nQueens.c
 * @brief set queens
 * @author Katharina Schwab
 * @date 30.12.2015
 */
#include"..\common_includes\ProjectSettings.h"

 /**
  * @fn void fcalcnQueens(struct nQueens* const psnQueens, int iRow)
  * @brief set queens
  * @param psnQueens, iRow
  * @author Katharina Schwab
  * @date 30.12.2015
  */
void fcalcnQueens(struct nQueens* const psnQueens,int iRow)
{
   int iCol = 0;                                               //start in column 0 every time                             
   int iThreat;                                                //get returnd as 0 if queen can stay at the actually field, as 1 if not
                          
      if (_kbhit())                                            //if there was a user input
         {
            psnQueens->cUserInput =(char) _getch();            //return from _getch() is int, cast is to char andwrite it to cUserInput            
         }
      
      //have to be not the last column, and no user input with 'e'
      while ((iCol < psnQueens->iBoardSize) && (psnQueens->cUserInput != 'e') && (psnQueens->cUserInput != 's'))
      {
         psnQueens->acBoard[iRow][iCol] = 'X';                 //set queen
                        
          iThreat= fIsThreating(psnQueens,iRow, iCol);   //call function to check out queen get threating
      
         if (iThreat == 1)                                     //queen is not threated
         {
            if (iRow == (psnQueens->iBoardSize-1))             //if it is the last row
            {
               psnQueens->iNumbersOfSolution++;                //save solution
               fprint_status(psnQueens);                   //call function to update numbers of solution
               if (psnQueens->eAppMode == step)                //if app mode is step by step
               {
                  fprint_board(psnQueens);                 //call function to print solution 
                  _gotoxy(0, 7);
                  printf("Press any key \nfor next solution"); //output user instruction
                  psnQueens->cUserInput = (char) _getch();     //return from _getch() is int, cast is to char and write it to cUserInput

                     if (psnQueens->cUserInput == 'p')         //if char was 'c' change app mode to continuous
                     {
                        psnQueens->eAppMode = continuous;
                        _clrscr();                             //clear screen because the board should not longer be printed
                        fprint_menue();                        //call function which prints the menue again
                     }
               }
            
               if (psnQueens->eSaveMode == saving_on)          //if true print actually solution to txt
               {
                  PrintToFile(psnQueens->iBoardPrinting, psnQueens->acFileName, psnQueens->iNumbersOfSolution, psnQueens->acBoard, psnQueens->iBoardSize);
                  psnQueens->iBoardPrinting = psnQueens->iBoardPrinting + psnQueens->iBoardSize +3;//actually position to start printing
               }

                  psnQueens->lStartTimeStamp = clock();        //get ticks since programm was started and change it to seconds
                  psnQueens->dRuntime = (double) psnQueens->lStartTimeStamp / CLOCKS_PER_SEC;
                  fprint_status(psnQueens);                //call function to update the actually runtime 
            }

            else                                               //if it is not last row
            {
               if (psnQueens->cUserInput != 'e')               //check exit char
               {
                  if (psnQueens->cUserInput != 's')            //check char for back to menue
                  {
                     fcalcnQueens(psnQueens, iRow+1);          //recursion with row +1, means search next allowed queen position
                  }
               }
            }
         }

         psnQueens->acBoard[iRow][iCol] = '0';                 //reset queen
         iCol++;                                               //try it in next column  
      }
}