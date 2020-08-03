#include <stdio.h>
#include <Windows.h>

void f1(unsigned int);
void f2(unsigned int);
void f3(unsigned int);


//int main(void) {
//   unsigned int uiselected;
//
//   void(*(fparray[3]))(unsigned int) = { f1,f2,f3 };
//   //fparray[0] = &f1;
//
//   for (int i = 1; i<4; i++) {
//      uiselected = i;
//      switch (uiselected)
//      {
//      case 1:
//         f1(100u);
//         break;
//      case 2:
//         f2(uiselected);
//         break;
//      case 3:
//         f3(50u);
//         break;
//      default:
//         printf("Wrong value for uiselected in function X");
//         break;
//      }
//   }
//   Sleep(600000);
//   return 0;
//}


void f1(unsigned int ui1) {
 printf("I am funktion f1!\n");
}
void f2(unsigned int ui2) {
   printf("And here is funktion f2!\n");
}
void f3(unsigned int ui3) {
   printf("Here is funktion f3!");
}





