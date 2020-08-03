#pragma once
#include<curses.h>
#include<cstdlib>

class myCurses
{
   private:
      //complete size of terminal
      #define WIDTH 80
      #define HEIGHT 25   

      char* choices[4] = {
         "Dark Roast",
         "House Blend",
         "Decaf",
         "Espresso",
      };
      int n_choices = sizeof(choices) / sizeof(char*);

      //1= SteamedMilk; 2= WhippedMilk; 3= soy; 4 = mocha; 5= continue
      char* choicesCondiment[5] = {
         "Steamed Milk",
         "Whipped Milk",
         "Soy",
         "Mocha",
         "Continue",
      };
      int n_choices_Condiments = sizeof(choicesCondiment) / sizeof(char*);

      int highlight = 1;

   public:
      myCurses() { }
      ~myCurses() {}
      void printHeader();
      int printCoffeeMenu();
      int printCondimnetsMenu();
      void printSales(double result);
      void printCurrentCoffee(const char* description, const double price);
      void print_menu(WINDOW *menu_win, int highlight);
      void print_menu_Condiments(WINDOW *menu_win, int highlight);
};


