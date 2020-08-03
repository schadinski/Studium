#pragma once
#include"Beverage.h"

class Mocha : public Beverage
{
private:
   string description = ", Mocha";
   double price = 0.50;
public:
   Mocha(Beverage* pBeverage)
   {
      this->pBeverage = pBeverage;
   }
   string getDescription()
   {
      string result;
      result = (*pBeverage).getDescription();
      result = result + description;
      return result;
   }
   double cost()
   {
      double result = 0;
      result = (*pBeverage).cost();
      result += price;
      return result;
   }
};