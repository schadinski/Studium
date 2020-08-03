#pragma once
#include"Beverage.h"

class DarkRoast : public Beverage
{
private:
   string description = "DarkRoast";
   double price = 2.50;
public:
   DarkRoast(){}
   ~DarkRoast() {}
   double DarkRoast::cost()
   {
      return price;
   }
   string DarkRoast::getDescription()
   {
      return description;
   }
};
