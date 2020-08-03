using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ClassLibrary2
{
    public class StandByState : State
    {
        //state immer erreichbar
        public override State Handle(VHSContext.Events currentEvent, TapeState tapeState) 
        {
            switch (currentEvent)
            {
                case VHSContext.Events.PlayClicked: //& tapeState==!TapeState._tapeEnd:
                    //if Tape in and not end
                    return tapeState == TapeState._tapeIn ? _play : this;
                case VHSContext.Events.ForwardClicked:
                    //if Tape in and not end
                    return tapeState == TapeState._tapeIn ? _forward : this;
                case VHSContext.Events.RewindClicked:
                    //if Tape in and not begin
                    return tapeState == TapeState._tapeIn ? _reward : this;
                case VHSContext.Events.RecordClicked:
                    //if Tape in and not end
                    return tapeState == TapeState._tapeIn ? _record : this;
                default:
                    //pressed StopButton again
                    return this;

            }
        }

        public override string Entry()
        {
            return "StandBy";
        }

        public override void Exit()
        {
            throw new NotImplementedException();
        }
    }
}
