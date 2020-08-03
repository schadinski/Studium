#pragma once
#include"myHeader.h"
#include"DataClass.h"

using namespace std;

class GUI
{
      private:
   //protected:
      int iCoffee;
      int iExtras;
      

      /*DarkRoast myDarkRoast;
      Decaf myDecaf;
      HouseBlend myHouseBlend;
      Espresso myEspresso;*/

      /*Beverage* myBeverage = &myEspresso;
   
      Mocha myMocha(myBeverage);
      SteamedMilk mySteamedMilk(myBeverage);
      WhippedMilk myWhippedMilk(myBeverage);
      Soy mySoy(myBeverage);*/

      public:
   
      GUI()
      {
         iCoffee = 0;
         iExtras = 0;
      }
   
      int printChooseCoffee();
      int printChooseExtras();
   void printPrice();
   void printSalesVolume();
   void printAllBeverages();
};