using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ClassLibrary2
{
    public class RewardState : State
    {
        //state nur erreichbar wenn tapestate == in

        public override State Handle(VHSContext.Events currentEvent, TapeState tapeState)
        {
            switch (currentEvent)
            {
                case VHSContext.Events.PlayClicked:
                    //if Tape not end
                    return tapeState != TapeState._tapeEnd ? _play: this;
                case VHSContext.Events.StopClicked:
                    //if tape in 
                    return tapeState == TapeState._tapeIn ? _standBy : this;
                case VHSContext.Events.ForwardClicked:
                    //if tape not end
                    return tapeState != TapeState._tapeEnd ? _forward : this;
                default:
                    //no switch from reward to record
                    return _reward;
            }
        }

        public override string Entry()
        {
            return "Reward";
        }

        public override void Exit()
        {
            throw new NotImplementedException();
        }
    }
}
