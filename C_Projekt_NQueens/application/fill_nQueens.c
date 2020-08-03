/**
 * @file fill_nQueens.c
 * @brief get user input
 * @author Katharina Schwab
 * @date 18.01.2016
 */

#include<string.h>
#include"..\common_includes\ProjectSettings.h"



 /**
  * @fn void fFill_nQueens(struct nQueens* const psnQueens)
  * @brief get user input
  * @param psnQueens
  * @author Katharina Schwab
  * @date 18.01.2016
  */
void fFill_nQueens(struct nQueens* const psnQueens)
{
   psnQueens->iBoardSize = 4;                         //fill snQueens for printing the status
   psnQueens->eAppMode = continuous;
   psnQueens->eSaveMode = saving_off;
   psnQueens->dRuntime = 0.00;
   psnQueens->iNumbersOfSolution = 0;
   psnQueens->iExit = 0;
   psnQueens->iBoardPrinting = 1;
   int iFileNameRight = 1;

   fprint_status(psnQueens);

   if (psnQueens->iExit != 1)
   {
      fGetBoardsize(psnQueens);                          //call function to get boardsize from user
      psnQueens->cUserInput = 'x';                       //reset cUserInput
   }

   if (psnQueens->iExit != 1)
   {
      fGetAppMode (psnQueens);                           //call function to get app mode from user
      psnQueens->cUserInput = 'x';                       //reset cUserInput
   }

   if (psnQueens->iExit != 1)
   {
      fGetSaveMode(psnQueens);                           //call function to get save mode from user
   }

   if (psnQueens->iExit != 1)
   {
      if (psnQueens->eSaveMode == saving_on)             
      {
         iFileNameRight = fFileName(psnQueens);          //call function to get filename and check is it correct
      }
      while (iFileNameRight == 0)                        //if filename is not correct output error string 
      {
         printf("The filename is not accept. Try again. Please note only numbers, letters  and underscores are allowed.");
         iFileNameRight = fFileName(psnQueens);          //call function to get and check filename again
      }
      _clrscr();                                         //reset board for next print in main
      psnQueens->cUserInput = 'x';                       //reset cUserInput
   }
   
   if (psnQueens->iExit == 1)
   {
      psnQueens->cUserInput = 'e';
   }
}




/**
 * @fn void fGetBoardsize(struct nQueens* const psnQueens)
 * @brief ask user for boardsize and controll input
 * @param psnQueens
 * @author Katharina Schwab
 * @date 18.01.2016
 */

void fGetBoardsize(struct nQueens* const psnQueens)
{
   while ((psnQueens->cUserInput != 'c') && (psnQueens->iExit != 1))
   {
      _gotoxy(25,6);                                  //print some user information
      printf("Actually boardsize: %d\n", psnQueens->iBoardSize);
      _gotoxy(25,8);
      printf("Press\n");
      _gotoxy(25,9);
      printf("i to increase the boardsize or\n");
      _gotoxy(25,10);
      printf("d to decrease the boardsize or\n");
      _gotoxy(25,11);
      printf("c to continue.\n");
      psnQueens->cUserInput = (char)_getch();         //get user input
      
      switch (psnQueens->cUserInput)                  //check user input
      {
         case 'i':
         case 'I':                                    //if its i/I 
            if (psnQueens->iBoardSize < 12)           //and if its not the highest boardsize
            {
               psnQueens->iBoardSize++;               //increase boardsize
            }
            break;
         case 'd':
         case 'D':                                    //if its d/D
            if (psnQueens->iBoardSize > 4)            //and not the lowest boardsize
            {
               psnQueens->iBoardSize--;               //decrease boardsize
            }
            break;
         case 'e':
         case 'E':                                    //if its e/E
            psnQueens->iExit = 1;                     //set exit counter to 1 for exit programm
            break;
      }
      
      if (psnQueens->iExit != 1)                      //check exit counter before continue
      {
         _clrscr();                                    //clear screen, print menue and actually status again
         fprint_menue();
         fprint_status(psnQueens);
      }
   }
}


/**
 * @fn void fGetAppMode(struct nQueens* const psnQueens)
 * @brief ask user for app mode and controll input
 * @param psnQueens
 * @author Katharina Schwab
 * @date 18.01.2016
 */
void fGetAppMode(struct nQueens* const psnQueens)
{
   while ((psnQueens->cUserInput != 'c') && (psnQueens->iExit != 1))
   {
      _gotoxy(25, 6);
      if (psnQueens->eAppMode == continuous)             
      {
         printf("Actually app mode: permanent\n");
      }
      else if (psnQueens->eAppMode == step)
      {
         printf("Actually app mode: step by step\n");
      }
      _gotoxy(25, 8);                                 //output user informations
      printf("Press\n");
      _gotoxy(25, 9);
      printf("s for step by step mode or\n");
      _gotoxy(25, 10);
      printf("p for permanent mode or\n");
      _gotoxy(25, 11);
      printf("c to continue.\n");
      psnQueens->cUserInput = (char)_getch();

      switch (psnQueens->cUserInput)                  //check user input 
      {
         case 's':
         case 'S':
            psnQueens->eAppMode = step;               //if its s/S set app mode to step
            break;
         case 'p':
         case 'P':
            psnQueens->eAppMode = continuous;         //if its p/P set app mode to step
            break;
         case 'e':
         case 'E':
            psnQueens->iExit = 1;                     //if its e/E set exit counter to 1
            break;
      }
   
      if (psnQueens->iExit != 1)
      {
         _clrscr();
         fprint_menue();
         fprint_status(psnQueens);
      }
   }
}

/**
* @fn void fGetSaveMode(struct nQueens* const psnQueens)
* @brief ask user for save mode and controll input
* @param psnQueens
* @author Katharina Schwab
* @date 18.01.2016
*/
void fGetSaveMode(struct nQueens* const psnQueens)
{
   strcpy(psnQueens->acFileName,"default.txt");         //copy default name to acFileName
   while ((psnQueens->cUserInput != 'c') && (psnQueens->iExit != 1))
   {
      _gotoxy(25, 6);
      if (psnQueens->eSaveMode == saving_off)            
      {
         printf("Actually save mode: off\n");
      }
      else if (psnQueens->eSaveMode == saving_on)
      {
         printf("Actually save mode: on\n");
      }
      _gotoxy(25, 8);                                    //output user informations
      printf("Press\n");
      _gotoxy(25, 9);
      printf("o for saving on mode or\n");
      _gotoxy(25, 10);
      printf("l for saving off mode or\n");
      _gotoxy(25, 11);
      printf("c to continue.\n");
      psnQueens->cUserInput = (char)_getch();

      switch (psnQueens->cUserInput)
      {
      case 'o':
      case 'O':
         psnQueens->eSaveMode = saving_on;            //if its o/O set save mode to on
         break;
      case 'l':
      case 'L':
         psnQueens->eSaveMode = saving_off;           //if its l/L set save mode to off
         break;
      case 'e':
      case 'E':
         psnQueens->iExit = 1;                        //if its e/E set exit counter to 1
         break;
      }

      if (psnQueens->iExit != 1)
      {
         _clrscr();
         fprint_menue();
         fprint_status(psnQueens);
      }
   }
}

/**
* @fn int fFileName(struct nQueens* const psnQueens)
* @brief get filename from user input and check it
* @param psnQueens
* @return iCheckFileName
* @author Katharina Schwab
* @date 27.12.2015
*/
int fFileName(struct nQueens* const psnQueens)
{
   int iCheckFileName;                                         //get set as 1 if filename correct, as 0 if is not correct
   int i;                                                      //loop counter
   char acGetFileName[255];                                    //write filename first in it
   int iLengthOfFileName;                                      //get the length of filename 

   _gotoxy(25,6);
   printf("Create a filename without file ending\n");
   _gotoxy(25,7);
   scanf("%s", acGetFileName);                                 // get filename from user input
   getchar();
   iLengthOfFileName = strlen(acGetFileName);                  //find out length of the file name
   strcpy(psnQueens->acFileName, acGetFileName);

   //check out the filename, are all chars in it allowed?    
   for (i = 0; i < iLengthOfFileName; i++)                     //check every char until the end
   {
      if (isalnum(psnQueens->acFileName[i]) || (psnQueens->acFileName[i] == '_'))//if it is an number, a letter or a underscore
      {
         iCheckFileName = 1;                                   //it is correct
      }
      else
      {
         iCheckFileName = 0;                                   //it is not correct
         i = iLengthOfFileName;
      }
   }
   if (iCheckFileName == 1)                                    //if filename correct add the file ending to the string
   {
      char acFileEnding[5] = { ".txt" };                        //file ending is .txt

      strcat(psnQueens->acFileName, acFileEnding);             //merge both strings
   }

   return iCheckFileName;                                      //return it to see if is not correct
}