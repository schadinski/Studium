#pragma once
#include"Beverage.h"

class HouseBlend : public Beverage
{
   private:
      string description = "HouseBlend";
      double price = 2.20;
   public:
      HouseBlend() {}
      ~HouseBlend() {}
      double cost()
      {
         return price;
      }
      string HouseBlend::getDescription()
      {
         return description;
      }
};
