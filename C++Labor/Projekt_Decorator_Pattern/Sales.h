#pragma once

#include<list>

#include"Beverage.h"

using namespace std;

class Sales
{
   private:
      list<Beverage*> listBeverage;
   public:
      Sales() {}
      double getSalesVolume()
      {
         double result = 0;
         for (list<Beverage*>::iterator it = listBeverage.begin(); it != listBeverage.end(); it++)
         {
            result += (*(*it)).cost();
         }
         return result;
      }
      void addBeverage(Beverage* nextBeverage)
      {
         listBeverage.push_front(nextBeverage);
      }
      void deleteList()
      {
         list<Beverage*>::iterator it;
         for (it = listBeverage.begin(); it != listBeverage.end(); it++)
         {
            delete (*it);
         }
      }
};