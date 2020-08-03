// Diese importierten Klassen werden Sie vermutlich brauchen 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Praktikum OOP AI-2
 * Aufgabenblatt 3, Aufgabe 3 
 * 
 * @author [Ihr Name]
 */
public class Stoppuhr extends Application  {

	// Felder
	private Stage primaryStage;		// Hauptfenster
	private Button startStopButton;
	private Button resetButton;
	private Label zeitAnzeige;		
	private Timeline timer;			// s. Methode 'start'
	
	private int hundSec;
	private int sec;
	private int min;
	
	boolean isRunning = false;
	
	// Zusätzlich brauchen Sie noch eine oder mehrere int-Variablen, um die Zeit zu speichern

	
	// Methoden
	@Override
	public void start(Stage primaryStage) {
		// Der Timer löst jede hundertstel Sekunde (alle 10 Millisekunden) ein Event aus, 
		// welches die Methode incrementTime() aufruft.
		// Der Timer kann mit play() und pause() gestartet und gestoppt werden.
		timer = new Timeline(new KeyFrame(Duration.millis(10), event -> incrementTime()));
		timer.setCycleCount(Animation.INDEFINITE);
		
		this.primaryStage = primaryStage;
		initView();
	}
	
	/**https://docs.oracle.com/javafx/2/api/
	 * ww.tcs.ifi.lmu.de/lehre/ws-2015-16/sep/material/folien-fx1
	 * Erstellt alle Komponenten und ordnet sie im Fenster an.
	 */
	private void initView() { 
	
		// Label für Zeitanzeige erstellen
		zeitAnzeige = new Label(myString());
		zeitAnzeige.setFont(new Font(80));
		
		// Menübar erstellen und hinzufügen
		 MenuBar menuBar = new MenuBar();
		 Menu menuAktion = new Menu("Aktion");
		 Menu menuHilfe = new Menu("Hilfe");
		 
		 MenuItem itemStart_Stop = new MenuItem("Start/Stop");
		 itemStart_Stop.setOnAction(new InnerClassHandler());
		 MenuItem itemReset = new MenuItem("Reset");
		 itemReset.setOnAction(new EventHandler<ActionEvent>(){
			 @Override
			 public void handle(ActionEvent event) {
				 resetTime();
			 }
		 }
				 );
		 SeparatorMenuItem itemLine = new SeparatorMenuItem();
		 MenuItem itemBeenden = new MenuItem("Beenden");
		 itemBeenden.setOnAction(exitEvent -> System.exit( 0 ));
		 
		 MenuItem itemInfo = new MenuItem("Info");
		 itemInfo.setOnAction(event -> dialogInfo());
		 
		 menuBar.getMenus().addAll(menuAktion,menuHilfe);
		 menuAktion.getItems().addAll(itemStart_Stop,itemReset,itemLine,itemBeenden);
		menuHilfe.getItems().add(itemInfo);
		 
		// Buttons erstellen
		startStopButton = new Button("Start");
		startStopButton.setOnAction(event -> startOrStopCounting());
		resetButton = new Button("Reset");
		resetButton.setOnAction(new EventHandler<ActionEvent>(){
			 @Override
			 public void handle(ActionEvent event) {
				 resetTime();
			 }
		}
		);
		
		// Buttons horizontal nebeneinander anordnen in einer HBox
		HBox hbox = new HBox(startStopButton,resetButton);
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(10,15,10,15));
		hbox.setAlignment(Pos.CENTER);
		
		
		// Zeitanzeige, Buttons und Menü anordnen in BorderPane
		BorderPane pane = new BorderPane();
		
		pane.setTop(menuBar);
	    pane.setCenter(zeitAnzeige);
	    pane.setBottom(hbox);

	    
	    Label text = new Label();
	    text.setAlignment(Pos.CENTER);
			
		
		// Alles zusammen in Hauptfenster darstellen
	     primaryStage.setTitle("Stoppuhr");
	     primaryStage.setScene(new Scene(pane, 400, 300));
		 primaryStage.show();
	}


	/**
	 * Aktualisiert die Zeitanzeige. 
	 */
	private void updateTimeLabel() {
		// Text im Label neu setzen
		 zeitAnzeige.setText(myString());  
	}

	/**
	 * Diese Methode startet bzw. stoppt die Uhr.
	 * Sie soll beim Drücken des Start/Stop-Buttons aufgerufen werden.
	 */
	private void startOrStopCounting() {
		// Je nachdem, ob die Uhr gerade läuft oder nicht:
		// Timer pausieren oder starten, dann
		
		//Timer läuft, soll gestoppt werden 
		if(isRunning == true){
			isRunning = false;
			startStopButton.setText("Start");
			timer.stop();
			
		}
		
		//timer ist gestoppt, soll gestartet werden
		else if(isRunning == false){
			isRunning = true;
			startStopButton.setText("Stop");
//			this.start(primaryStage);
			timer.play();
		}
		
		
		
	}
	
	/**
	 * Diese Methode setzt die Uhr auf 00:00:00 zurück.
	 * Sie soll beim Drücken des Start/Stop-Buttons aufgerufen werden.
	 */
	private void resetTime() {
		// Zeitvariable(n) auf 0 setzen
		// und Anzeige aktualisieren
		min = 0;
		sec = 0;
		hundSec = 0;
		timer.stop();
		isRunning = false;
		startStopButton.setText("Start");
		
		updateTimeLabel();
	}
	
	/**
	 * Diese Methode erhöht die Zeit um eine hunderstel Sekunde.
	 * Sie muss vom Timer (s. Methode 'start') jede hundertstel Sekunde aufgerufen werden.
	 */
	private void incrementTime() {
		// Zeitvariable(n) erhöhen
		// und Anzeige aktualisieren
		if(hundSec == 99){
			sec++;
			if(sec==59){
				min++;
				sec = 0;
				if(min == 59){
					min = 0;
				}
			}
			hundSec = 0;
		}
		else{
			hundSec++;	
		}
		updateTimeLabel();
	}
	
	private void dialogInfo(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Author is Katharina Schwab");
		alert.setContentText("");
		alert.showAndWait();
	}
	
	private String myString(){
		String myString;
		if(hundSec <10 && sec<10 && min <10){
			myString = "0"+min+":"+"0"+sec+":"+"0"+hundSec;
		}
		else if(hundSec <10 && sec<10 && min >=10){
			myString = min+":"+"0"+sec+":"+"0"+hundSec;
		}
		else if(hundSec >=10 && sec<10 && min <10){
			myString = "0"+min+":"+"0"+sec+":"+hundSec;	
				}
		else if(hundSec <10 && sec>=10 &&min <10){
			myString = "0"+min+":"+sec+":"+"0"+hundSec;
				}
		else if(hundSec <10 && sec>=10 && min >=10){
			myString = min+":"+sec+":"+"0"+hundSec;
		}
		else if(hundSec >=10 && sec<10 && min >=10){
			myString = min+":"+"0"+sec+":"+hundSec;
		}
		else if(hundSec >=10 && sec>=10 && min <10){
			myString = "0"+min+":"+sec+":"+hundSec;
		}
		else{
			myString = min+":"+sec+":"+hundSec;
		}
		return myString;
	}


	public static void main(String[] args) {
		// JavaFX-Anwendung starten
		launch("Stoppuhr");
	}
	
	class InnerClassHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			startOrStopCounting();
		}
	}
	
}
