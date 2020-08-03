#pragma once

class DataClass
{
   private:
      int myCoffee;
      int extras[20];      
      int sizeOfExtras;

   public:
      DataClass()
      {
         myCoffee = 0;
         sizeOfExtras = 0;
      }
      void setMyCoffee(int a)
      {
         this->myCoffee = a;
      }
      int getMyCoffee(void)
      {
         return myCoffee;
      }
      void setExtras(int extra)
      {
         extras[sizeOfExtras]= extra;
         sizeOfExtras++;
      }
      int getsizeOfExtras(void)
      {
         return sizeOfExtras;
      }
      int getExtras(int index)
      {
         return extras[index];
      }
};