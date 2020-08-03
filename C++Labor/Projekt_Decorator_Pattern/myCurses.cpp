#include"myCurses.h"

void myCurses::printHeader()
{
   WINDOW* kopfzeile_win;
   kopfzeile_win = newwin((HEIGHT / 4), WIDTH, 0, 0);
   mvwaddstr(kopfzeile_win, 1, (WIDTH/2)- 10, "WELCOME TO STARBUZZ");
   //mvhline(2, 1, ACS_BULLET, 20);
   mvwaddstr(kopfzeile_win, 4, 2, "Use key up and key down, select with enter ");
   //mvwaddstr(kopfzeile_win, 4, WIDTH/2, "Press F1 to exit");
   box(kopfzeile_win, 0, 0);
   wrefresh(kopfzeile_win);
}
int myCurses::printCoffeeMenu()
{
   int highlight = 1;
   int choice = 0;
   int c = 0; 

   WINDOW* user_win;
   user_win = newwin((HEIGHT / 2), (WIDTH / 2), (HEIGHT / 4), 0);
   keypad(user_win, TRUE);
   mvwaddstr(user_win, 1, 2, "Coffee menu");
   print_menu(user_win, highlight);
   
   while (c != '\n')
   {
      c = wgetch(user_win);
      switch (c)
      {
      case KEY_UP:
         if (highlight == 1)
            highlight = n_choices;
         else
            --highlight;
         break;
      case KEY_DOWN:
         if (highlight == n_choices)
            highlight = 1;
         else
            ++highlight;
         break;
      case 10:
         choice = highlight;
         break;
      default:
         break;
      }
      print_menu(user_win, highlight);
   }     //highlight ist my coffee number

   box(user_win, 0, 0);
   wrefresh(user_win);

   return highlight;
}
int myCurses::printCondimnetsMenu()
{
   
   int choice = 0;
   int c = 0;

   WINDOW* user_win;
   user_win = newwin((HEIGHT / 2), (WIDTH / 2), (HEIGHT / 4), 0);
   keypad(user_win, TRUE);
   mvwaddstr(user_win, 1, 2, "Condiments menu");
   print_menu_Condiments(user_win, highlight);
   box(user_win, 0, 0);

   while (c != '\n')
   {
      c = wgetch(user_win);
      switch (c)
      {
      case KEY_UP:
         if (highlight == 1)
            highlight = n_choices_Condiments;
         else
            --highlight;
         break;
      case KEY_DOWN:
         if (highlight == n_choices_Condiments)
            highlight = 1;
         else
            ++highlight;
         break;
      case 10:
         choice = highlight;
         break;
      default:
         break;
      }
      wrefresh(user_win);
      print_menu_Condiments(user_win, highlight);
   }     
   wrefresh(user_win);
   return highlight;
}
void myCurses::printSales(double result)
{
   WINDOW* sales_win;
   sales_win = newwin(((HEIGHT / 4) ), (WIDTH / 2), (HEIGHT / 4), (WIDTH / 2));
   mvwaddstr(sales_win, 1, 1, "Daily sale:");
   mvwprintw(sales_win, 2, 2, "%.2lf", result);
   box(sales_win, 0, 0);
   wrefresh(sales_win);
}
void myCurses::printCurrentCoffee(const char* description, const double price)
{
   WINDOW* actuallyCoffee_win;
   
   actuallyCoffee_win = newwin((HEIGHT / 4), (WIDTH / 2), ((HEIGHT / 4) * 3), 0);
   mvwaddstr(actuallyCoffee_win, 1, 2, "                            ");
   mvwaddstr(actuallyCoffee_win, 1, 2, description);
   mvwaddstr(actuallyCoffee_win, 2, 2, "costs ");
   mvwprintw(actuallyCoffee_win, 3, 2, "%.2lf", price);
   box(actuallyCoffee_win, 0, 0);
   wrefresh(actuallyCoffee_win);
}

void myCurses::print_menu(WINDOW *menu_win, int highlight)
{
   int x, y, i;

   x = 2;
   y = 3;
   box(menu_win, 0, 0);
   for (i = 0; i < n_choices; ++i)
   {
      if (highlight == i + 1) 
      {
         wattron(menu_win, A_REVERSE);
         mvwprintw(menu_win, y, x, "%s", choices[i]);
         wattroff(menu_win, A_REVERSE);
      }
      else
         mvwprintw(menu_win, y, x, "%s", choices[i]);
      ++y;
   }
   wrefresh(menu_win);
}
void myCurses::print_menu_Condiments(WINDOW *menu_win, int highlight)
{
   
   int x, y, i;

   x = 2;
   y = 3;
   box(menu_win, 0, 0);
   for (i = 0; i < n_choices_Condiments; ++i)
   {
      if (highlight == i + 1) 
      {
         wattron(menu_win, A_REVERSE);
         mvwprintw(menu_win, y, x, "%s", choicesCondiment[i]);
         wattroff(menu_win, A_REVERSE);
      }
      else
         mvwprintw(menu_win, y, x, "%s", choicesCondiment[i]);
      ++y;
   }
   wrefresh(menu_win);
}

void printDeco()
{
   WINDOW* deco_win;
   deco_win = newwin(((HEIGHT / 2)), (WIDTH / 2), (HEIGHT / 2), (WIDTH / 2));
}