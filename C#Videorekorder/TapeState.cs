using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClassLibrary2
{
    public abstract class TapeState
    {
        public static TapeState _tapeIn = new TapeInState();
        public static TapeState _tapeEnd = new TapeEndState();
        public static TapeState _tapeOut = new TapeOutState();
    }
}
