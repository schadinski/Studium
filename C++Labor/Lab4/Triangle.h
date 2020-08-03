#include"Shapes.h"

class Triangle : public Shapes
{
public:
   void draw();
   Triangle()
   {
      cout << "Konstruktor Triangle" << endl;
      Shapes::uiNumbersOfInstances++;
   }
   ~Triangle()
   {
      cout << "Destruktor Triangle" << endl;
      Shapes::uiNumbersOfInstances--;
   }
};
