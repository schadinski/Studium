/**
* @file print_Status.c
* @brief output statuslines
* @author Katharina Schwab
* @date 27.12.2015
*/
#include"..\common_includes\ProjectSettings.h"
/**
* @fn void fprint_status(struct nQueens* const psnQueens)
* @brief output strings for status and get actually runtime
* @param psnQueens
* @author Katharina Schwab
* @date 27.12.2015
*/
void fprint_status(struct nQueens* const psnQueens)
{
   //output three strings at a selected position
   _gotoxy(0,16);
   printf("--------------------------------------------------------------------------------\n");
   printf("[Status]\n\nBoardsize: %dx%d", psnQueens->iBoardSize, psnQueens->iBoardSize);   //output actually boardsize

   _gotoxy(40,20);
   if (psnQueens->eAppMode == step)                                           //print actually app mode 
   {
      printf("App mode: step by step");
   }
   else if (psnQueens->eAppMode == continuous)
   {
      printf("App mode: permanent ");
   }

   _gotoxy(0,21);
   if (psnQueens->eSaveMode == saving_on)                                     //print actually save mode 
   {
      printf("Save mode: On, file name is %s", psnQueens->acFileName);        //is it on print filename too
   }
   else if (psnQueens->eSaveMode == saving_off)
   {
      printf("Save Mode: Off                                                                                        ");
   }

   _gotoxy(0,22);
   printf("Actually numbers of solutions:%d", psnQueens->iNumbersOfSolution); //print actually numbers of solutions

   _gotoxy(40,22);
   printf("Actually runtime: %.2f sec              \n", psnQueens->dRuntime); //print actually runtime

   _gotoxy(0,23);
   printf("--------------------------------------------------------------------------------");
}