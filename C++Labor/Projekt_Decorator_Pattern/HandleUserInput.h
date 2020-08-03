#pragma once
#include"Beverage.h"
#include"myHeader.h"


class HandleUserInput
{
   public:
      HandleUserInput() {};
      ~HandleUserInput() {};
      Beverage* createCoffee(int coffee);
      Beverage* createCondiments(Beverage* myCoffee, int condiment);
};

Beverage* HandleUserInput::createCoffee(int coffee)
{
   Beverage* myCoffee;
   switch (coffee)
   {
   case 1:
      myCoffee = new DarkRoast();
      break;
   case 2:
      myCoffee = new HouseBlend();
      break;
   case 3:
      myCoffee = new Decaf();
      break;
   default:
      myCoffee = new Espresso();
      break;
   }
   return myCoffee;    
}

//1= SteamedMilk; 2= WhippedMilk; 3= soy; 4 = mocha; 5= continue
Beverage* HandleUserInput::createCondiments(Beverage* myCoffee, int condiment)
{
   if (condiment == 5)
   {
      return false;
   }
   Beverage* coffeeWithCondiments;
   switch (condiment)
   {
   case 1:
      coffeeWithCondiments = new SteamedMilk(myCoffee);
      break;
   case 2:
      coffeeWithCondiments = new WhippedMilk(myCoffee);
      break;
   case 3:
      coffeeWithCondiments = new Soy(myCoffee);
      break;
   default:
      coffeeWithCondiments = new Mocha(myCoffee);
      break;
   }
   return coffeeWithCondiments;
}