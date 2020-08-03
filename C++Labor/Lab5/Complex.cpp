#include"Complex.h"
#define _USE_MATH_DEFINES
#include<math.h>


double Complex::getAbsolutValue()
{
   return sqrt(real*real + imag*imag);
}

double Complex::getDegree()
{
   return 360 / (2* M_PI ) * atan2(imag,real);
}

Complex Complex::konjungiertKomplex()
{
   Complex temp(0,0);
   temp.real = real;
   temp.imag = (-1)* imag;
   
   return temp;
}

Complex Complex::operator+ (const Complex& c)
{
   Complex temp(0, 0);
   temp.real = (real+c.real);
   temp.imag = (imag+c.imag);

   return temp;
}

Complex Complex::operator- (const Complex& c)
{
   Complex temp(0, 0);
   temp.real = (real - c.real);
   temp.imag = (imag - c.imag);

   return temp;
}

Complex Complex::operator* (const Complex& c)
{
   Complex temp(0, 0);
   temp.real = (real*c.real - imag*c.imag);
   temp.imag = (real*c.imag + imag*c.real);

   return temp;
}

Complex Complex::operator! ()
{
   Complex cNenner(*this);

   Complex cZaehler(0,0);
   Complex temp1 = cNenner.konjungiertKomplex();
   cZaehler = cNenner * temp1;
   Complex temp2 (0,0);
   temp2 =  cNenner/cZaehler ;
   
   return temp2;
}


Complex Complex::operator/ (const Complex& c)
{
   Complex temp3 (c);
   Complex temp = temp3.konjungiertKomplex();
   Complex cZaehler (0,0);

   cZaehler = (*this) * temp;
   double dNenner = (c.real*c.real) + (c.imag * c.imag);

   Complex temp2 (0,0);
   temp2.real = cZaehler.real / dNenner;
   temp2.imag = cZaehler.imag / dNenner;
   return temp2;
}

void Complex::print()
{
   cout<<real<<imag<<"j"<<endl;
}

ostream& operator<<(ostream& ostr, const Complex& c)
{
   ostr<<c.real<<"+("<<c.imag<<")*j"<<endl;
    return ostr;
}