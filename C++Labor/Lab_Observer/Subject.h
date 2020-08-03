#pragma once
#include<iostream>

#include<list>

class Subject
{
   protected:
      list<Observer*> observerList;
   public:
      void attach(Observer* pobs)
      {
         observerList.push_back(pobs);
      }
      void detach(Observer* pobs)
      {
         observerList.remove(pobs);
      }
      void print()
      {
         for (list<Observer*>::iterator it = observerList.begin(); it != observerList.end(); it++)
         {
            cout<<"Observer State is: ";
            (*it)->print();
         }
      }
      virtual void notify() = 0;

};