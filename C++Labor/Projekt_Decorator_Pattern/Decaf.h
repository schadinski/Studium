#pragma once
#include"Beverage.h"

class Decaf : public Beverage
{
private:
   string description = "Decaf";
   double price = 1.80;
public:
   Decaf() {}
   ~Decaf() {}
   double Decaf::cost()
   {
      return price;
   }
   string Decaf::getDescription()
   {
      return description;
   }
};

