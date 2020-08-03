#pragma once

using namespace std;

class Observer
{
   public:
      virtual void update() = 0;
      void print();
};