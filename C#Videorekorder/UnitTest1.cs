using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ClassLibrary2;

namespace UnitTestProject1
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestMethod1()
        {
            //test PlayState
           PlayState currState = new PlayState();
            var result = currState.Handle(VHSContext.Events.PlayClicked, new TapeInState() );

            Assert.AreEqual(currState, result);

            StandByState expectedState = new StandByState();
            result = currState.Handle(VHSContext.Events.StopClicked, new TapeInState());
            //objektvergleich, failed
            //Assert.AreEqual(expectedState, result);

            Assert.IsTrue(result is StandByState);
        }


        //[TestMethod] ist wichtig, sonst wirds nich erkannt
        [TestMethod]
        public void TestMethod2()
        {
            //test Record State
            RecordState currState = new RecordState();
            PlayState wrongState = new PlayState();
            var result = currState.Handle(VHSContext.Events.StopClicked, new TapeInState());
            Assert.AreNotEqual(wrongState,result);



        }
        //void methoden mittels den variablen testen, die sie beeinflussen, 
        //z.b interne änderung der property oder ob Event ausgelöst wurde
        //whitebox test, d.h sourcecode bekannt
        //Wurde Event geworfen? -> neuer eventhandler in testklasse schreiben, nicht in Methode 
        //auch bool in Klasse nicht in Methode anlegen, aus false setzen
        // in diesem wird z.b. bool is executed = true gesetzt, das kann man dann abfragen
        //assert.isTrue(isExecuted);
    }
}
