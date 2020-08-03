#include<curses.h>
#include"myCurses.h"
#include"Sales.h"
#include"myHeader.h"
#include"HandleUserInput.h"

int main()
{
   Sales starbuzz;
   myCurses myCurses;
   int myChoose = 0;
   Beverage* myCoffee;
   HandleUserInput myHandle;
   bool myExit = false;
   double dailySales;
   
   //ncurses
   initscr();
   clear();
   noecho();
   cbreak();

   while (true)
   {
      //Kopfzeile 
      myCurses.printHeader();

      //User input coffee
      myChoose = myCurses.printCoffeeMenu();
      myCoffee = myHandle.createCoffee(myChoose);

      //user input condiments
      while (myChoose != 5)
      {
           while (myChoose != 5)
         {
            myChoose = myCurses.printCondimnetsMenu();
            if (myChoose == 5)
            {
               break;
            }   
            myCoffee = myHandle.createCondiments(myCoffee, myChoose);
         }
      }
      starbuzz.addBeverage(myCoffee);

      //actually coffee
      string str = (*myCoffee).getDescription();
      char* cstr = new char[str.length() + 1];
      strcpy(cstr, str.c_str());

      double price = (*myCoffee).cost();
      myCurses.printCurrentCoffee(cstr, price);
   
      //sales volume
      dailySales = starbuzz.getSalesVolume();
      myCurses.printSales(dailySales);
   }
   endwin();
   starbuzz.deleteList();
   return 0;
}