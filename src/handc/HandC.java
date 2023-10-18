package handc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;

//////////////////////////////////////////////Hues&Cues of Raquel Ochoa, Annia Navarro and Andrés Martínez//////////////////////////////////////////////////

public class HandC extends Application {
    public static Scene sceneMain;
    public static Deck pile;
    public static Deck discardPile;
    public static Player p1;
    public static Player p2;
    public static Player p3;
    public static Player p4;
    public static Player p5;
    public static Player p6;
    public static Player p7;
    public static Player p8;
    public static Player p9;
    public static Player p10;
    public static int noPlayers;//cantidad de jugadores
    public static Player players[];
    public static ArrayList<Card> introducedCards;
    public static ArrayList<String> cues;
    public static ArrayList<StackPane> stackPaneSquares;
    public static ArrayList<StackPane> stackPanePoints;
    public static String cue="";
    public static boolean validCue=false;
    public static boolean win=false;
    public static Scanner sc;
    public static Random rand;
    public static GridPane playersBoard;
    public static ArrayList<ColorSquare> colors;
    public static GridPane board;
    public static GridPane scoreBoard;
    public static GridPane discardPileGp;
    public static BorderPane table;
    public static BorderPane cardTable;
    public static BorderPane leftBoard;
    public static Label textHint;
    public static Stage stage;
    public static Token actualToken;
    public static StackPane stackPaneSquare;
    public static StackPane stackPanePoint;
    public static int actualPlayerNo;
    public static int actualCueGiver;
    public static int actualRoad;
    public static int actualTokenNo;
    public static int winner;
    public static ColorSquare arraySquares[][];
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        createPanes();
                       
        //Randomizador
        rand = new Random();
        sc = new Scanner(System.in);
        
        createDecks();
        
        createArrayLists();
                               
        //Crea los tableros
        createBoards();
        
        //Crea las cartas
        createCards();
        
        setPanes();
                
        //Crear la escena
        sceneMain = new Scene(table, 1200, 680);
        primaryStage.setTitle("Hues&Cues");
        primaryStage.setScene(sceneMain);
        primaryStage.show();
        
        getNoPlayers();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void createPanes(){
        //Mesa de juego
        table = new BorderPane();
        //Tablero principal
        board = new GridPane();
        board.setAlignment(Pos.CENTER);
        //Tablero de puntuaciones
        scoreBoard = new GridPane();
        scoreBoard.setAlignment(Pos.CENTER);
        //Tablero de discardpile
        discardPileGp = new GridPane();
        discardPileGp.setAlignment(Pos.CENTER);
        
        //Tablero izquierdo
        leftBoard = new BorderPane();
        //Caja de fichas
        playersBoard = new GridPane();
        playersBoard.setAlignment(Pos.CENTER);
        playersBoard.setStyle("-fx-spacing: 30;");
        leftBoard.setCenter(playersBoard);
        //Tablero de cartas
        cardTable = new BorderPane();
    }
    
    public void createDecks(){
        //Definir mazos
        HandC.pile = new Deck();
        pile.setAlignment(Pos.CENTER);
        HandC.discardPile = new Deck();
        discardPile.setAlignment(Pos.TOP_CENTER);
    }
    
    public void createArrayLists(){
        //Definir ArrayList
        introducedCards = new ArrayList();
        colors = new ArrayList();
        players=new Player[10];
        cues = new ArrayList();
        stackPaneSquares = new ArrayList();
        stackPanePoints = new ArrayList();
        arraySquares=new ColorSquare[30][16];
    }
    
    public void setPanes(){
        //Colocar cartas en mazo
        Collections.shuffle(introducedCards);
        HandC.pile.initializeDeck(introducedCards);
        
        //Letrero Pistas
        GridPane hintGp =new GridPane();
        Label hintT = new Label("Hint: ");
        hintT.setStyle("-fx-text-fill: black;");
        hintT.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 25));
        hintT.setAlignment(Pos.CENTER);
        hintGp.add(hintT, 0, 0);
        textHint = new Label(cue);
        textHint.setStyle("-fx-text-fill: red;");
        textHint.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 25));
        textHint.setAlignment(Pos.CENTER);
        hintGp.add(textHint, 0, 1);
        leftBoard.setTop(hintGp);
        
        //Actual card deck
        Label actualCardT = new Label("Actual Card:");
        actualCardT.setAlignment(Pos.CENTER);
        actualCardT.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 24));
        discardPileGp.add(actualCardT, 0, 0);
        discardPileGp.add(discardPile, 0, 1);
        Label actualCardMsg = new Label("Cue Giver Select a Color");
        actualCardMsg.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 12));
        discardPileGp.add(actualCardMsg, 0, 2);
        
        //Definir tablero de cartas
        cardTable.setCenter(HandC.discardPileGp);
                       
        //Definir el border pane
        table.setCenter(board);
        table.setRight(cardTable);
        table.setBottom(scoreBoard);
        table.setLeft(leftBoard);
    }
    
    //Creador de ebordes del tablero
    public void createCorner(GridPane board, int i, int x, String text){
        Label corner = new Label(text);
        corner.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        corner.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 15));
        corner.setAlignment(Pos.CENTER);
        corner.setMinSize(30, 30);
        corner.setMaxSize(30, 30);
        board.add(corner, i, x);
    }
    
    //Creador del tablero de puntuaciones
    public void createScore(GridPane scoreBoard, int i, int x, String text){
        stackPanePoint = new StackPane();
        Label corner = new Label(text);
        corner.setStyle("-fx-border-color:black; -fx-background-color: gray; -fx-text-fill: white;");
        corner.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 17));
        corner.setAlignment(Pos.BOTTOM_CENTER);
        corner.setMinSize(30, 60);
        corner.setMaxSize(30, 60);
        stackPanePoint.getChildren().add(corner);
        scoreBoard.add(stackPanePoint, i, x);
        stackPanePoints.add(stackPanePoint);
    }
    
    public void createPlayers(){
        //Creacion de jugadores
        HandC.p1 = new Player("blue", 1);
        players[0]=p1;
        HandC.p2 = new Player("green", 2);
        players[1]=p2;
        HandC.p3 = new Player("red", 3);
        players[2]=p3;
        HandC.p4 = new Player("gray", 4);
        players[3]=p4;
        HandC.p5 = new Player("purple", 5);
        players[4]=p5;
        HandC.p6 = new Player("brown", 6);
        players[5]=p6;
        HandC.p7 = new Player("orange", 7);
        players[6]=p7;
        HandC.p8 = new Player("pink", 8);
        players[7]=p8;
        HandC.p9 = new Player("cyan", 9);
        players[8]=p9;
        HandC.p10 = new Player("darkgreen", 10);
        players[9]=p10;
        
        //Colocar jugadores en su gridpane
        playersBoard.add(HandC.p1, 0, 0);
        playersBoard.add(HandC.p2, 0, 1);
        playersBoard.add(HandC.p3, 0, 2);
        for(int i=3; i<noPlayers; i++)
            playersBoard.add(players[i], 0, i);
    }
    
    public void createCards(){
        //Creador de cartas
        for(int i=0; i<100; i++){
            ColorSquare color1= colors.get(rand.nextInt(colors.size()));
            ColorSquare color2= colors.get(rand.nextInt(colors.size()));
            ColorSquare color3= colors.get(rand.nextInt(colors.size()));
            ColorSquare color4= colors.get(rand.nextInt(colors.size()));
            Card card = new Card(color1, color2, color3, color4);
            introducedCards.add(card);
        }
    }
    
    public void createBoards() throws FileNotFoundException, IOException{
        //Creador de Esquinas
        createCorner(board, 0, 0, "");
        createCorner(board, 31, 0, "");
        createCorner(board, 31, 17, "");
        createCorner(board, 0, 17, "");
        
        //Creador de Bordes Horizontales
        for(int i=1; i<31; i++){
            String text= Integer.toString(i);
            createCorner(board, i, 0, text);
            createCorner(board, i, 17, text);
        }
        //Creador de Bordes Verticales
        for(int i=1; i<17; i++){
            String text= Character.toString((char)(i+96));
            createCorner(board, 0, i, text);
            createCorner(board, 31, i, text);
        }
        //Creador de ColorSquare
        FileReader fr = new FileReader("src/handc/colorMatrix.csv");
        BufferedReader br2 = new BufferedReader(fr);
        String line = "";
        int x=1;
        while( (line = br2.readLine()) != null ){
            String attrs[] = line.split(",");
            for(int i=0; i<31; i++){
                if(i!=0){
                    String color = attrs[i];
                    stackPaneSquare = new StackPane();
                    ColorSquare colorSquare = new ColorSquare(color, i, x+64, stackPaneSquare);
                    stackPaneSquare.getChildren().add(colorSquare);
                    board.add(stackPaneSquare, i, x);
                    colors.add(colorSquare);
                    stackPaneSquares.add(stackPaneSquare);
                    arraySquares[i-1][x-1]=colorSquare;
                }
            }
            x++;
        }
        //Creador de Tablero de Puntaje
        int n=1;
        for(int i=1; i<51; i++){
            String text="";
            int m=33;
            if(i>25)
                m++;
            if(i%5==0)
                text=Integer.toString(i);
            createScore(scoreBoard, n-1, m, text);
            if(i<25)
                n++;
            else if(i>25)
                n--;
        }
    }
    
    public void getNoPlayers(){
        //Pantalla No Jugadores
        Popup popupGetNoPlayers = new Popup();
        GridPane gpNoPlayers = new GridPane();
        gpNoPlayers.setAlignment(Pos.CENTER);
        
        Label noPlayersL = new Label("\nSelect a number of players between 3-10: ");
        noPlayersL.setStyle("-fx-text-fill: white;");
        TextField noPlayersF = new TextField();
        Button setBtn = new Button("Set");
        
        setBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int myNoPlayers=0;
                try{
                    myNoPlayers=Integer.parseInt(noPlayersF.getText());
                }catch(NumberFormatException error){
                    System.out.println("\nInvalid number of players!");
                }
                if (myNoPlayers>=3 && myNoPlayers<=10){
                    popupGetNoPlayers.hide();
                    noPlayers=myNoPlayers;
                    //Crea a los jugadores
                    createPlayers();
                    //Realiza los turnos de juego
                    //Turnos del juego
                    actualRoad=0;
                                           
                    actualCueGiver=0;
                    
                    getFirstClue();
                            
                }else{
                    System.out.println("\nInvalid number of players!");
                }
            }
        });
        
        gpNoPlayers.add(noPlayersL, 0, 1);
        gpNoPlayers.add(noPlayersF, 0, 2);
        gpNoPlayers.add(setBtn, 0, 3, 2, 2);
        
        popupGetNoPlayers.getContent().add(gpNoPlayers);
        
        popupGetNoPlayers.show(stage);
    }
    
    public static void getFirstClue(){
        //Pantalla Primera Pista
        Popup popupFirstCue = new Popup();
        GridPane gpFirstCue = new GridPane();
        gpFirstCue.setAlignment(Pos.CENTER);

        Label cueGiverL = new Label("\nTurn of Player " + (actualCueGiver+1) + " to be the CueGiver.");
        cueGiverL.setStyle("-fx-text-fill: white;");
        //Label cardMsgL = new Label("\nChoose a color of card on the top.");
        Label firstCueL = new Label("\nGive a cue to the other players: ");
        firstCueL.setStyle("-fx-text-fill: white;");
        TextField firstCueF = new TextField();
        Button confirmBtn = new Button("Set");

        HandC.pile.getChildren().remove(introducedCards.get(introducedCards.size()-1));
        HandC.discardPile.getChildren().add(introducedCards.get(introducedCards.size()-1));
        introducedCards.remove(introducedCards.get(introducedCards.size()-1));
                       
        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!firstCueF.getText().isEmpty()){
                    if(cues.size()!=0){
                        validCue=true;
                        for(int e=0; validCue==true && e < cues.size(); e++){
                            if(firstCueF.getText().equals(cues.get(e)))
                                validCue=false;
                        }
                    }else{
                        validCue=true;
                    }
                }else{
                    validCue=false;
                }
                if (validCue==true){
                    popupFirstCue.hide();
                    cues.add(firstCueF.getText());
                    textHint.setText(firstCueF.getText());
                                        
                    actualTokenNo=0;
                    
                    if(actualCueGiver==0)
                        actualPlayerNo=1;
                    else
                        actualPlayerNo=0;
                    
                    actualToken=players[actualPlayerNo].getIntroducedTokens().get(actualTokenNo);
                    
                    Button finishCue = new Button("Finish Cue");
                    cardTable.setBottom(finishCue);
                    
                    finishCue.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if(actualPlayerNo>=noPlayers){
                                Label temporalLabel = new Label();
                                cardTable.setBottom(temporalLabel);
                                        
                                goSecondCue();
                            }
                        }
                    });
                    
                    
                   
                }else{
                    System.out.println("\nInvalid Cue!");
                }
            }
        });

        gpFirstCue.add(cueGiverL, 0, 1);
        gpFirstCue.add(firstCueL, 0, 2);
        gpFirstCue.add(firstCueF, 0, 3);
        gpFirstCue.add(confirmBtn, 0, 4, 2, 2);

        popupFirstCue.getContent().add(gpFirstCue);
        
        popupFirstCue.show(stage);
    }
    
    public static void goSecondCue(){
        //Pantalla Second Clue
        Popup popupContinue = new Popup();
        GridPane gpContinue = new GridPane();
        gpContinue.setAlignment(Pos.CENTER);

        Label continueL = new Label("Do u want to give a second cue?");
        continueL.setStyle("-fx-text-fill: white;");
        Button yesBtn = new Button("YES");
        Button noBtn = new Button("NO");

        yesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popupContinue.hide();
                getSecondClue();
            }
        });
        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popupContinue.hide();
                
                finishRoundBtn();
            }
        });

        gpContinue.add(continueL, 0, 1);
        gpContinue.add(yesBtn, 0, 2);
        gpContinue.add(noBtn, 1, 2, 2, 2);

        popupContinue.getContent().add(gpContinue);

        popupContinue.show(stage);
    }
    
    public static void getSecondClue(){
        //Pantalla SegundaPista
        Popup popupSecondCue = new Popup();
        GridPane gpSecondCue = new GridPane();
        gpSecondCue.setAlignment(Pos.CENTER);

        Label cueGiverL = new Label("\nTurn of Player " + (actualCueGiver+1) + " to be the CueGiver.");
        cueGiverL.setStyle("-fx-text-fill: white;");
        //Label cardMsgL = new Label("\nChoose a color of card on the top.");
        Label secondCueL = new Label("\nGive a cue to the other players: ");
        secondCueL.setStyle("-fx-text-fill: white;");
        TextField secondCueF = new TextField();
        Button confirmBtn = new Button("Set");

        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!secondCueF.getText().isEmpty()){
                    if(cues.size()!=0){
                        validCue=true;
                        for(int e=0; validCue==true && e < cues.size(); e++){
                            if(secondCueF.getText().equals(cues.get(e)))
                                validCue=false;
                        }
                    }else{
                        validCue=true;
                    }
                }else{
                    validCue=false;
                }
                if (validCue==true){
                    popupSecondCue.hide();
                    cues.add(secondCueF.getText());
                    textHint.setText(secondCueF.getText());
                    
                    actualTokenNo=1;
                    
                    if(actualCueGiver==0)
                        actualPlayerNo=1;
                    else
                        actualPlayerNo=0;
                    
                    actualToken=players[actualPlayerNo].getIntroducedTokens().get(actualTokenNo);
                    
                    Button finishCue = new Button("Finish Cue");
                    cardTable.setBottom(finishCue);
                    
                    finishCue.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if(actualPlayerNo>=noPlayers){
                                finishRoundBtn();                                
                            }
                        }
                    });
                        
                }else{
                    System.out.println("\nInvalid Cue!");
                }
            }
        });

        gpSecondCue.add(cueGiverL, 0, 1);
        gpSecondCue.add(secondCueL, 0, 2);
        gpSecondCue.add(secondCueF, 0, 3);
        gpSecondCue.add(confirmBtn, 0, 4, 2, 2);

        popupSecondCue.getContent().add(gpSecondCue);
        
        popupSecondCue.show(stage);
    }
    
    //Boton para finalizar la ronda
    public static void finishRoundBtn(){
        actualToken=players[actualCueGiver].getIntroducedTokens().get(actualTokenNo);
        int myActualCueGiver=actualCueGiver;
        int myActualRoad=actualRoad;

        Button finishRound = new Button("Finish Round");
        cardTable.setBottom(finishRound);

        finishRound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(win==true){
                    stage.hide();
                    System.out.println("\nThe winner is " + winner);
                }else{
                    if(actualCueGiver>myActualCueGiver){
                        Label temporalLabel = new Label();
                        cardTable.setBottom(temporalLabel);

                        getFirstClue();
                    }else if(actualRoad>myActualRoad){
                        Label temporalLabel = new Label();
                        cardTable.setBottom(temporalLabel);

                        getFirstClue();
                    }
                }
            }
        });
    }
    
    }
//////////////////////////////////PlisGive us the 10 (://///////////////////////////////////////////////////