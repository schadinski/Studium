#pragma once
#include"Beverage.h"

class WhippedMilk : public Beverage
{
private:
   string description = ", WhippedMilk";
   double price = 0.30;
public:
   WhippedMilk(Beverage* pBeverage)
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