#include"GUI.h"

int GUI::printChooseCoffee()
{
   
   /*DarkRoast myDarkRoast;
   Decaf myDecaf;
   HouseBlend myHouseBlend;
   Espresso myEspresso;*/
   while (iCoffee < 1 || iCoffee>5)
   {
      cout << "Please choose your Coffee" << endl;
      cout << "1    Dark Roast" << endl << "2    House Blend" << endl;
      cout << "3    Decaf" << endl << "4    Espresso" << endl;
      cin >> iCoffee;
   }
   return iCoffee;
   /*switch (uiCoffee)
   {
   case 1:
      myBeverage = &myDarkRoast;
      break;
   case 2:
      myBeverage = &myHouseBlend;
      break;
   case 3:
      myBeverage = &myDecaf;
      break;
   default:
      myBeverage = &myEspresso;
      break;
   }*/
   
}

//3= soy; 2= WhippedMilk; 1= SteamedMilk; 4 = mocha; 5= continue
int GUI::printChooseExtras()
{
   iExtras = 0;
   while ( iExtras <1 || iExtras >6 )
   {
      cout<<"Please choose your ingredients"<<endl;
      cout<<"1    Steamed Milk"<<endl<<"2    Whipped Milk"<<endl;
      cout<<"3    Soy"<<endl<<"4    Mocha"<<endl;
      cout<<"5    Continue"<<endl;   
      cin >> iExtras;
   }
   return iExtras;
   //switch (iExtras)
   //{
   //case 5:
   //   myBeverage = &mySteamedMilk;
   //   break;
   //case 6:
   //   myBeverage = &myWhippedMilk;
   //   break;
   //case 7:
   //   myBeverage = &mySoy;
   //   break;
   //case 8:
   //   myBeverage = &myMocha;
   //default:
   //   //setze abbruchvariable!
   //   break;
   //}
}

void GUI::printPrice()
{ 
   /*double result = (*myBeverage).cost();
   cout<<"total price is "<<result<<endl;;*/

}
void GUI::printSalesVolume()
{

}
void GUI::printAllBeverages()
{

}