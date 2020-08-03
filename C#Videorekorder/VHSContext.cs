using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Cache;
using System.Text;
using System.Threading.Tasks;

namespace ClassLibrary2
{
    public class VHSContext
    {
        private State CurrentState;
        private TapeContext mytape = new TapeContext();
        private TapeState tapeState;
        public enum Events { PlayClicked, StopClicked, RecordClicked, RewindClicked, ForwardClicked }


        //new
        public delegate void MyEventHandler(object sender, MyEventArgs e);
        public event MyEventHandler MyFinished;

        public VHSContext()
        {
            CurrentState = State._standBy;
            tapeState = mytape.currTape;
        }

        public void Request(Events currentEvent)
        {

            CurrentState = CurrentState.Handle(currentEvent, tapeState);

            //feuert Event MyFinished um Labeltext zu setzen
            MyFinished?.Invoke(this, new MyEventArgs(CurrentState));
        }
    }

    //new
    //rein für den StyleGuide
    public class MyEventArgs : EventArgs
    {
        public string Result { get; private set; }

        public MyEventArgs(State currentState)
        {
            Result = currentState.Entry();
        }
    }
}
