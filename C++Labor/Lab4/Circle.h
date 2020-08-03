#include"Shapes.h"

class Circle : public Shapes
{
   public:
      void draw();
      Circle()
      {
         cout << "Konstruktor Circle" << endl;
         Shapes::uiNumbersOfInstances++;   
      }
      ~Circle()
      {
         cout<<"Destruktor Circle"<<endl;
         Shapes::uiNumbersOfInstances--;
      }
};
