#pragma once
#include<iostream>

using namespace std;

class Shapes
{
   public:
     static unsigned int  getNumbersOfInstances()
      {
         return uiNumbersOfInstances;
      };

   protected:
      static unsigned int uiNumbersOfInstances;
      
      Shapes() {};
      

   public:
      void virtual draw() = 0;
      virtual ~Shapes() {};
};