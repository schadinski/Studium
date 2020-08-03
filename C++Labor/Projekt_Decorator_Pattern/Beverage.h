#pragma once
#include<string>
#include<iostream>
using namespace std;

class Beverage
{
   private:
      string description;
      double price;
   protected:
      Beverage* pBeverage;
   public:
      virtual~Beverage() {};
      string virtual getDescription() = 0;
      double virtual cost() = 0;
};