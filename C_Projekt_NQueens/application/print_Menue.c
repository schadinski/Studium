/**
* @file print_Menue.c
* @brief print menue 
* @author Katharina Schwab
* @date 27.12.2015
*/
#include"..\common_includes\ProjectSettings.h"
/**
* @fn void fprint_menue(void)
* @brief output menue
* @author Katharina Schwab
* @date 27.12.2015
*/
void fprint_menue(void)
{
   //output three strings
   printf("[Menue]\n");
   printf("--------------------------------------------------------------------------------");
   printf("-s- (back to menue)");             //press s to change app mode
   //output strings at a selected position
   _gotoxy(20,2);
   printf("-e- (exit)");                                  //press e to leave the programm

   _gotoxy(31,2);
   printf("If step mode on: -p- (change to permanent mode)\n");             //press s to change app mode
   printf("--------------------------------------------------------------------------------");

}