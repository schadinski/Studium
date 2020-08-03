using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ClassLibrary2
{
    public class ForwardState : State
    {
        public override State Handle(VHSContext.Events currentEvent, TapeState tapeState)
        {
            switch (currentEvent)
            {
                case VHSContext.Events.PlayClicked:
                    return tapeState != TapeState._tapeEnd ? _play : this;
                case VHSContext.Events.StopClicked:
                    return tapeState == TapeState._tapeIn ? _standBy : this;
                case VHSContext.Events.RewindClicked:
                    return tapeState == TapeState._tapeIn ? _reward : this;
                default:
                    //no switch from forward to record
                    return this;
            }
        }

        public override string Entry()
        {
            return "Foward";
        }

        public override void Exit()
        {
            throw new NotImplementedException();
        }
    }
}
