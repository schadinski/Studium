using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ClassLibrary2
{
    public abstract class State
    {
       // private String text;

        public static State _play = new PlayState();
        public static State _record = new RecordState();
        public static State _standBy = new StandByState();
        public static State _forward = new ForwardState();
        public static State _reward = new RewardState();
        public abstract State Handle(VHSContext.Events currentEvent, TapeState tapeState);

        //Entry() returns String to MyEventArgs
        public abstract String Entry();
        public abstract void Exit();
    }
}
