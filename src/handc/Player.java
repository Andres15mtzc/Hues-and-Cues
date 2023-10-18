/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handc;

import java.util.ArrayList;
import javafx.scene.layout.HBox;


public class Player extends HBox{
    //Variables del jugador
    private String color;
    private String playerNumber;
    private ArrayList<Token> introducedTokens;
    private int points=0;
    private int playerNumberInt;
        
    public Player(String color, int playerNumber){
        //Caracteristicas del jugador
        setStyle("-fx-spacing: 15;");
        setColor(color);
        setPlayerNumber(playerNumber);
        
        //Arreglo de tokens
        introducedTokens = new ArrayList();
        
        //Creador de los 3 tokens por jugador
        for(int i=0; i<3; i++){
            Token token = new Token(color, getPlayerNumber(), playerNumber);
            introducedTokens.add(token);
            if(i<2)
                this.getChildren().add(token);
            //else
            //    HandC.stackPanePoints.get(0).getChildren().add(token);
        }
        
    }
    
    //Getters y setters
    public String getColor(){
        return this.color;
    }
    
    public void setColor(String receivedColor){
        this.color = receivedColor;
    }
    
    public String getPlayerNumber(){
        return this.playerNumber;
    }
    
    public void setPlayerNumber(int receivedPlayerNumber){
        this.playerNumber = "P"+Integer.toString(receivedPlayerNumber);
    }
    
    public int getPlayerNumberInt(){
        return this.playerNumberInt;
    }
    
    public void setPlayerNumberInt(int receivedPlayerNumberInt){
        this.playerNumberInt = receivedPlayerNumberInt;
    }
    
    public ArrayList<Token> getIntroducedTokens(){
        return this.introducedTokens;
    }
    
    public void setIntroducedTokens(ArrayList<Token> receivedIntroducedTokens){
        this.introducedTokens = receivedIntroducedTokens;
    }
    
    public void setPoints(int rNo){
        this.points = this.points + rNo;
    }
    
    public int getPoints(){
        return points;
    }
}
