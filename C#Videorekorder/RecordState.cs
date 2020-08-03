using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ClassLibrary2
{
    //
    public class RecordState : State
    {
        public override State Handle(VHSContext.Events currentEvent, TapeState tapeState)
        {
            switch (currentEvent)
            {
                case VHSContext.Events.StopClicked:
                    return tapeState != TapeState._tapeOut ? _standBy : this;
                default:
                    //no switch from record to rewind
                    //no switch from record to forward
                    //no switch from record to play
                    return _record;
            }
        }

        public override string Entry()
        {
            return "Record";
        }

        public override void Exit()
        {
            throw new NotImplementedException();
        }
    }
}
