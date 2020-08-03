/*! 
 * @file Utilities.c
 * @brief Collection of useful functions
 * @author Badura/Fischer
 * @date 01.10.2009
 * Additional Infos:
 * void _clrscr(void)
 * void _gotoxy(short x, short y);
 ************************************************************************/

#include <windows.h>

/*!
 * @fn void _gotoxy(short x, short y)
 * @brief Set the cursor in the console at x (horizontal) and y (vertical)
 *
 * @author Badura
 * @date 02.10.2009
 * @note Formerly part of conio
 * @param[in] x short
 * @param[in] y short
 ************************************************************************/
void _gotoxy(short x, short y)
{
   HANDLE hStdout = GetStdHandle(STD_OUTPUT_HANDLE);
	
	COORD sXY;
	
	sXY.X = x;
	sXY.Y = y;

	SetConsoleCursorPosition(hStdout, sXY);
}

/*!
 * @fn void _clrscr(void)
 * @brief Clear screen - deletes content of console window
 * @author internet source 
 * @date 03.10.2009
 * @note Formerly part of conio
 ************************************************************************/
void _clrscr(void)
{
  COORD coordScreen = {0, 0};
  DWORD cCharsWritten;
  CONSOLE_SCREEN_BUFFER_INFO csbi;
  DWORD dwConSize;
  HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

  GetConsoleScreenBufferInfo(hConsole, &csbi);
  
  dwConSize = csbi.dwSize.X * csbi.dwSize.Y;
  
  FillConsoleOutputCharacter(hConsole, TEXT(' '), dwConSize, coordScreen, &cCharsWritten);
  GetConsoleScreenBufferInfo(hConsole, &csbi);
  FillConsoleOutputAttribute(hConsole, csbi.wAttributes, dwConSize, coordScreen, &cCharsWritten);
  SetConsoleCursorPosition(hConsole, coordScreen);
}

