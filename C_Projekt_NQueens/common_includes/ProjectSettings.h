#pragma warning(disable:4996)
/**
 * @file ProjectSettings.h
 * @brief header for nqueens
 * @author Katharina Schwab
 * @date 10.01.2016
 */
#include<time.h>
#include<stdio.h>
#include<conio.h>
#include<ctype.h>
#include<stdlib.h>
#include"../common_includes/Utilities.h"


#define SIZE 12
 /**
  * @enum AppMode
  * @brief enum for Mode
  */
enum AppMode
{
   continuous,
   step
};
/**
* @enum SaveMode
* @brief enum for saving
*/
enum SaveMode
{
   saving_off,
   saving_on
};
/**
* @struct nQueens
* @brief struct for nQueens
*/
struct nQueens
{
   int iBoardSize;
   char acBoard[SIZE][SIZE];
   char acFileName[255];
   enum AppMode eAppMode;
   enum SaveMode eSaveMode;
   double dRuntime;
   clock_t lStartTimeStamp;
   int iNumbersOfSolution;
   char cUserInput;
   int iExit;
   int iBoardPrinting;
};

 //declaration of all files
void fFill_nQueens(struct nQueens* const);
void fprint_menue(void);
void fprint_status(struct nQueens* const);
void fInitial_Board(struct nQueens* const);
int fIsThreating(const struct nQueens* const, const int, const int);
void fprint_board(const struct nQueens* const);
void fcalcnQueens(struct nQueens* const, int);
_declspec(dllexport) void PrintToFile(int iBoardPrinting, char acFileName[255],int iNumbersOfSolutions, char acBoard[12][12], int iBoardSize);
void fGetBoardsize(struct nQueens* const psnQueens);
void fGetAppMode(struct nQueens* const psnQueens);
void fGetSaveMode(struct nQueens* const psnQueens);
int fFileName(struct nQueens* const psnQueens);

