#pragma once

#include<iostream>
#include<string>

using namespace std;

class Complex
{
   private:
      double real;
      double imag;
   
   public:
      Complex()
      {
         this->real = 0.0;
         this->imag = 0.0;
         //cout<<"Constructor Complex"<<endl;
      }

      Complex(double a, double b)
      {
         this->real = a;
         this->imag = b;
         //cout << "Constructor Complex" << endl;
      }

      Complex(const Complex& c)
      {
         real = c.real;
         imag = c.imag;
         //cout << "Copy-Constructor Complex" << endl;
      }

      ~Complex()
      {
         //cout << "Destructor Complex" << endl;
      }

      void setReal(double dr)
      {
         real = dr;
      }
      void setImag(double di)
      {
         imag=di;
      }

      double getReal()
      {
         return ((*this).real);
      }
      double getImag()
      {
         return ((*this).imag);
      }
      double getAbsolutValue();
      double getDegree();

      void print();

      Complex konjungiertKomplex();

      Complex operator/ (const Complex& c);
      Complex operator+ (const Complex& c);
      Complex operator- (const Complex& c);
      Complex operator* (const Complex& c);
      Complex operator! ();

      friend ostream& operator<<(ostream& ostr, const Complex& c);
      /*
         Muss friend sein, weil Funktionen mit mehr als einem Übergabeparameter friend sein müssen, wg. this-Zeiger
         des weiteren ist << ein unärer operator
      */
};
