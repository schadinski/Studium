#pragma once
#include"Beverage.h"

class Espresso : public Beverage
{
private:
   string description = "Espresso";
   double price = 1.90;
public:
   Espresso() {}
   ~Espresso() {};
   double Espresso::cost()
   {
      return price;
   }
   string Espresso::getDescription()
   {
      return description;
   }
};
