/**
 * @file main.c
 * @brief main in nQueens
 * @author Katharina Schwab
 * @date 23.12.2015
 */
#include"..\common_includes\ProjectSettings.h"

 /**
  * @fn int main(void)
  * @brief call some functions
  * @return always 0
  * @author Katharina Schwab
  * @date 23.12.2015
  */
int main(void)
{
   struct nQueens snQueens;               //initial a counter of struct nQueens
   snQueens.cUserInput = 'x';             //set cUserInput to default
  
   while (snQueens.cUserInput != 'e')
   {
      fprint_menue();                     //call function which prints the menue

      fFill_nQueens(&snQueens);           //call function to print status and get user input

      if (snQueens.cUserInput != 'e')     //check exit counter before continue
      {
         fprint_menue();                  //call function which prints the menue
         fprint_status(&snQueens);        //call function which prints the status
   
         _gotoxy(0,7);
         printf("Press any key to start");//output user instruction
         snQueens.cUserInput = (char)_getch();//stop point to see menue and status before start queen setting

         if (snQueens.cUserInput != 'e')  //check exit counter before continue
         {
            _gotoxy(0, 7);
            printf("                      ");//clear last string

            fInitial_Board(&snQueens);       //call function which starts the queen setting process
            
            if (snQueens.cUserInput != 'e')
            {
               _gotoxy(0, 7);
               printf("Show results.\nPress any key to continue");//output user instruction  
               _getch();                           //show user the result
            }
         }
      
     
      }
      
      _clrscr();                          //clear screen before print new menue and status, in while loop
   }
   return 0;
}