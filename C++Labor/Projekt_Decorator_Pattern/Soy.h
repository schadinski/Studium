#pragma once
#include"Beverage.h"

class Soy : public Beverage
{
   private:
      string description = ", Soy";
      double price = 0.60;
   public:
      Soy(Beverage* pBeverage)
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