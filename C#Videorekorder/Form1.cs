using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClassLibrary2;


namespace WindowsFormsApp1
{
    public partial class Videorecorder : Form


    {
        private VHSContext myVHS = new VHSContext();
        public Videorecorder()
        {
            InitializeComponent();
            //new
            myVHS.MyFinished += SetLabelText;
        }

        //eigener Eventhandler
        //new
        private void SetLabelText(object sender, MyEventArgs e)
        {
            StatusLabel.Text = e.Result;
            //calc_button.Enabled = true;
        }

        private void RewardButton_Click(object sender, EventArgs e)
        {
                myVHS.Request(VHSContext.Events.RewindClicked);
        }

        private void StartButton_Click(object sender, EventArgs e)
        {
                myVHS.Request(VHSContext.Events.PlayClicked);
        }

        private void StopButton_Click_1(object sender, EventArgs e)
        {
                myVHS.Request(VHSContext.Events.StopClicked);
        }

        private void RecordButton_Click_1(object sender, EventArgs e)
        {
                myVHS.Request(VHSContext.Events.RecordClicked);
        }

        private void ForwardButton_Click_1(object sender, EventArgs e)
        {
                myVHS.Request(VHSContext.Events.ForwardClicked);
        }
    }

    
}
