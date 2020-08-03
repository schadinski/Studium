#pragma once


class ConcreteSubject;

class ConcreteObserver : public Observer
{
   private:
      int observerState;
         ConcreteSubject* pMySubject;
         //int signal;        
   public: 
      ConcreteObserver(ConcreteSubject* mySubject)
      {
         pMySubject = mySubject;
         observerState = 0;
         //signal = 0;           //if signal = 1 call getObserverState for new data
      }
      
      void update()
      {
         observerState = pMySubject->getState();
         //signal = 1;
      }
      int getObserverState()
      {
         return observerState;
      }
      void print()
      {
         cout<<observerState<<endl;
      }

};
