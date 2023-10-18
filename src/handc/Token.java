/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handc;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Token extends Label{
    private static int CARD_WIDTH = 20;
    private static int CARD_HEIGHT = 20;
    private int no;
    
    public Token(String color, String playerNumber, int number){
        //Características del Token
        setMinSize(Token.CARD_WIDTH, Token.CARD_HEIGHT);
        setMaxSize(Token.CARD_WIDTH, Token.CARD_HEIGHT);
        setText(playerNumber);
        setStyle("-fx-border-color:white; -fx-background-color: "+color+"; -fx-text-fill: white;");
        setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 8));
        setAlignment(Pos.CENTER);
        setPlayerNumber(number);
    }
    
    public void setPlayerNumber(int rNo){
        this.no=rNo;
    }
    
    public int getPlayerNumber(){
        return no;
    }            
}
