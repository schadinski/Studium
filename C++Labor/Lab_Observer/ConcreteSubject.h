#pragma once


class ConcreteSubject : public Subject
{
   private:
      int subjectState;
   public:
      ConcreteSubject()
      {
         subjectState = 0;
      }
      void setSubjectState(int subjectState)
      {
         this->subjectState = subjectState;
      }
      void notify()
      {
         for (list<Observer*>::iterator itObserverList = observerList.begin(); itObserverList != observerList.end(); itObserverList++)
         {
            //itObserverList.update();
            (*(*itObserverList)).update();
         }
      }
      int getState()
      {
         return subjectState;
      }
};
