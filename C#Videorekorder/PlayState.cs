using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ClassLibrary2
{
    public class PlayState : State
    {
        //nur ereichbar wenn tape in
        public override State Handle(VHSContext.Events currentEvent, TapeState tapeState)
        {
            switch (currentEvent)
            {
                case VHSContext.Events.StopClicked:
                    return tapeState != TapeState._tapeOut ? _standBy : this;
                case VHSContext.Events.ForwardClicked:
                    return tapeState != TapeState._tapeEnd ? _forward : this;
                case VHSContext.Events.RewindClicked:
                    return tapeState == TapeState._tapeIn ? _reward : this;
                default:
                    //no switch from play to record
                    return this;
            }
        }

        public override string Entry()
        {
            return "Play";
        }

        public override void Exit()
        {
            throw new NotImplementedException();
        }
    }
}
