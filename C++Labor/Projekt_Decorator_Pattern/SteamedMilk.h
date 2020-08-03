#pragma once
#include"Beverage.h"

class SteamedMilk : public Beverage
{
private:
   string description = ", SteamedMilk";
   double price = 0.40;
public:
   SteamedMilk(Beverage* pBeverage)
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
