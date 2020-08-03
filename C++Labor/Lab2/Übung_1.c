#include <stdio.h>
#include <Windows.h>

unsigned int app(unsigned short int us1, unsigned short int us2);


//int main(void) {
//   unsigned int (*ptf) (int);
//   ptf = app;
//
//   unsigned short int us1 = 47837;
//   unsigned short int us2 = 0xCAFE;    //51966
//
//   unsigned int result = ptf(us1, us2);
//
//   printf("result is %X", result);
//
//   Sleep(600000);
//
//   return 0;
//}

unsigned int app(unsigned short int us1, unsigned short int us2) {
   unsigned int uiresult = (us1<<16|us2);
   return uiresult;
}