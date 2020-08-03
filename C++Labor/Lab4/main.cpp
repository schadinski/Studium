#include<cstdlib>

#include"Circle.h"
#include"Triangle.h"
#include"Rectangle.h"


int main(void) 
{
   // unsigned int result;
   
  /* Circle myCircle;
   result = myCircle.getNumbersOfInstances();*/
   /*result = myArray[0]->getNumbersOfInstances();
   cout<<result<<endl;*/

   Shapes* myArray[10];
   myArray[0] = new Circle();
   myArray[1] = new Circle();
   myArray[2] = new Triangle();
   myArray[3] = new Triangle();
   myArray[4] = new Rectangle();
   myArray[5] = new Rectangle();
   myArray[6] = new Circle();
   myArray[7] = new Circle();
   myArray[8] = new Triangle();
   myArray[9] = new Rectangle();

   int iNumberOfObjects = myArray[9]->getNumbersOfInstances();

   cout<<iNumberOfObjects<<endl;

   for(int i = 0;i<iNumberOfObjects; i++)
   {
      myArray[i]->draw();
   }

   delete myArray[0];
   delete myArray[1];
   delete myArray[2];
   delete myArray[3];
   delete myArray[4];
   delete myArray[5];
   delete myArray[6];
   delete myArray[7];
   delete myArray[8];
   delete myArray[9];
   
   system("PAUSE");
   return 0;
}