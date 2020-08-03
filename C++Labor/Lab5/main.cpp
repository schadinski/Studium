#include"Complex.h"

int main()
{
   //Complex cOne(5,2);
   //Complex cTwo(5,6);

   Complex cResult(0,0);

   ////cResult = cOne.konjungiertKomplex();
   //cResult = cOne/cTwo;
   //cout<<cResult<<endl;

   Complex ZL1(0,100);
   //cResult = !ZL1;


   Complex ZL3(0, 50);
   Complex ZL2(0, 100);
   Complex ZR1(50, 0);
   Complex ZR2(50,0);
   Complex ZR3(100,0);
   Complex ZR4(200,0);
   Complex ZC1(0, -100);
   Complex ZC2(0, -100);

  
   cResult = !(!(ZR1+ZL1)+!(ZC1+ZR2))+!(!(ZC2+ZR3+ZL3)+!(ZL2+ZR4));
   cout << cResult << endl;
   ZL1.print();

   system("Pause");
   return 0;
}