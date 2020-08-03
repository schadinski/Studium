#include"Shapes.h"

class Rectangle : public Shapes
{
public:
   void draw();
   Rectangle()
   {
      cout << "Konstruktor Rectangle" << endl;
      Shapes::uiNumbersOfInstances++;
   }
   ~Rectangle()
   {
      cout << "Destruktor Rectangle" << endl;
      Shapes::uiNumbersOfInstances--;
   }
};
