#include"Header.h"



int main()
{
   ConcreteSubject mySubject;
   mySubject.setSubjectState(23);
   ConcreteSubject* pSub = &mySubject;

   ConcreteObserver obs1(pSub);
   ConcreteObserver obs2(pSub);
   ConcreteObserver obs3(pSub);
   ConcreteObserver obs4(pSub);

   mySubject.attach(&obs1);
   mySubject.attach(&obs2);
   mySubject.attach(&obs3);
   mySubject.attach(&obs4);

   mySubject.setSubjectState(42);
   mySubject.notify();
   mySubject.print();

   

   system("Pause");
   return 0;
}