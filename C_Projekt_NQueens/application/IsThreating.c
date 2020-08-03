/**
* @file IsThreating.c
* @brief check out threats for the acctually queen
* @author Katharina Schwab
* @date 11.01.2016
*/
#include"..\common_includes\ProjectSettings.h"

/**
* @fn int fIsThreating(const struct nQueens* const psnQueens, const iRow, const iCol)
* @brief find queens over the acctually setted queen, in row and diagonal left hand and right hand
* @param psnQueens, iRow, iCol
* @return iThreat
* @author Katharina Schwab
* @date 11.01.2016
*/
int fIsThreating(const struct nQueens* const psnQueens, const int iRow, const int iCol)
{
	int iThreat = 1;
	int i;
	if (!(iRow == 0 && iCol == 0))                              //if not in the field 0,0
	{
		if (iRow != 0)                                           //if not first row
		{
			for (i = 1; i <= iRow ; i++)
			{
				if (psnQueens->acBoard[iRow - i][iCol] == 'X')     //check row over the queen
				{
					iThreat = 0;                                    //there is a second queen in the row 
				}
			}
		}

      if (iThreat == 1)                                        //if no threat until yet
      {
		   if (iCol < (psnQueens->iBoardSize - 1))               //if not last column
		   {
			   for (i = 1; i < psnQueens->iBoardSize; i++)
			   {
				   if ((iCol + i) < psnQueens->iBoardSize)         //check until last column
				   {
                  if ((iRow - i) >= 0)                         //check until the first row
                  {
					      if (psnQueens->acBoard[iRow - i][iCol + i] == 'X')  //check diagonal right over the queen
					      {
						      iThreat = 0;                           //there is a second queen in the row 
					      }
                  }
				   }
			   }
		   }
      }

      if (iThreat == 1)                                        //if no threat until yet
      {
		   if (iCol != 0)                                        //if not firt column
		   {
			   for (i = 1; i < psnQueens->iBoardSize; i++)
			   {
				   if ((iRow - i) >= 0)                            //check until first rom
				   {
                  if ((iCol - i) >= 0)                         //check until first column
                  {
					      if (psnQueens->acBoard[iRow - i][iCol - i] == 'X')  //check diagonal right over the queen
					      {
						      iThreat = 0;                           //there is a second queen in the row 
					      }
                  }
				   }
			   }
		   }
      }
	}
	return iThreat;
}