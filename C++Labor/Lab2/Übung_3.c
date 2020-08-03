#include <stdio.h>
#include <Windows.h>
#include <string.h>

int main(int argc, char* argv[]) {

//achtung in argc paramanzahl, aber erster steht für programmname

   char** pcc1 = (char**) malloc(sizeof(char*)*argc);


   for (int i = 0; i < argc; i++) {
   char temp[100];
   pcc1[i]= (char*) malloc(sizeof(char)*20);
//printf("strlen argv[%d] is %d", i+1, strlen(argv[i + 1]));strlen(argv[i+1]+1) )
   strcpy( temp, argv[i+1]);
   strcpy(*(pcc1 + i), temp);
   }

   for (int i = 0; i < argc; i++) {
      printf("param %d is %s\n", i, *(pcc1+i));
   }


   for (int i = argc; i < 0; i++) {
   free(pcc1+i);
   }
   free(pcc1);
   

   Sleep(6000);
   return 0;
}