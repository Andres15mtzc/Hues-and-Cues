/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handc;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class ColorSquare extends Button{
    private String color;
    private String letter;
    private String number;
    private int x;
    private int y;
    public boolean beToken=false;
    public int id;
    
    private ColorSquare(){}
    //Creacion de los botones de colores dentro del tablero
    public ColorSquare(String color, int number, int letter, StackPane stackPaneSquare){
        setStyle("-fx-border-color:black; -fx-background-color: #" + color + ";" );
        setMinSize(30, 30);
        setMaxSize(30, 30);
        setNumber(number);
        setLetter(letter);
        setColor(color);
        setX(number-1);
        setY(letter-65);
        
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //Final de Turnos o Rondas
                if(HandC.actualPlayerNo>=HandC.noPlayers && HandC.actualToken==HandC.players[HandC.actualCueGiver].getIntroducedTokens().get(HandC.actualTokenNo)){
                    
                    //Borrar puntos anteriores
                    for(int i=0; i<HandC.stackPanePoints.size(); i++){
                        ObservableList<Node> myPlayer = HandC.stackPanePoints.get(i).getChildren();
                        while(myPlayer.size()>1){
                            myPlayer.remove(myPlayer.get(1));
                        }
                    }
                    
                    //Sumar puntos
                    addPoints();
                                        
                    //Colocar puntos
                    for(int i=0; i<HandC.noPlayers; i++){
                        Player myPlayer = HandC.players[i];
                        if(myPlayer.getPoints()>=50){
                            HandC.stackPanePoints.get(49).getChildren().add(myPlayer.getIntroducedTokens().get(2));
                            HandC.win=true;
                            HandC.winner=myPlayer.getPlayerNumberInt();
                        }else if(myPlayer.getPoints()!=0)
                            HandC.stackPanePoints.get(myPlayer.getPoints()-1).getChildren().add(myPlayer.getIntroducedTokens().get(2));
                    }
                    
                    //Limpiar tablero
                    for(int i=0;i<HandC.colors.size();i++){
                        if(HandC.colors.get(i).getBeToken()==true){
                            HandC.stackPaneSquares.get(i).getChildren().remove(HandC.stackPaneSquares.get(i).getChildren().get(1));
                            HandC.colors.get(i).setBeToken(false);
                        }
                    }
                    
                    //Setear en HBox de players
                    for(int i=0; i<HandC.noPlayers; i++){
                        if(i!=HandC.actualCueGiver){
                            HandC.players[i].getChildren().add(HandC.players[i].getIntroducedTokens().get(0));
                            if(HandC.actualTokenNo==1){
                                HandC.players[i].getChildren().add(HandC.players[i].getIntroducedTokens().get(1));
                            }
                        }
                    } 
                    
                    //Cambiar Turnos o vueltas
                    if(HandC.actualCueGiver<HandC.noPlayers-1){
                        HandC.actualCueGiver++;
                    }else{
                        HandC.actualRoad++;           
                        HandC.actualCueGiver=0;
                    }
                    if(HandC.actualRoad==3){
                        int biggerPoints=0;
                        for(int i=0; i<HandC.noPlayers; i++){
                            Player myPlayer = HandC.players[i];
                            if(myPlayer.getPoints()>biggerPoints){
                                HandC.win=true;
                                biggerPoints=myPlayer.getPlayerNumberInt();
                            }
                        }
                        HandC.winner=biggerPoints;
                        if(HandC.win==true){
                            HandC.stage.hide();
                            System.out.println("\nThe winner is " + HandC.winner);
                        }
                    }
                        
                }
                
                //Colocar fichas del turno
                if(HandC.actualPlayerNo<HandC.noPlayers && HandC.actualPlayerNo<HandC.noPlayers){
                    if(beToken==false){
                        stackPaneSquare.getChildren().add(HandC.actualToken);
                        HandC.players[HandC.actualPlayerNo].getChildren().remove(HandC.players[HandC.actualPlayerNo].getIntroducedTokens().get(HandC.actualTokenNo));
                        setID(HandC.actualToken.getPlayerNumber()-1);
                        setBeToken(true);
                        HandC.actualPlayerNo++;
                        if(HandC.actualPlayerNo==HandC.actualCueGiver)
                            HandC.actualPlayerNo++;
                        HandC.actualToken=HandC.players[HandC.actualPlayerNo].getIntroducedTokens().get(HandC.actualTokenNo);
                    }
                }
            }
        });
    }
    //Getters y setters
    public String getColor(){
        return this.color;
    }
    
    public void setColor(String receivedColor){
        this.color = receivedColor;
    }
    
    public String getLetterNumber(){
        return this.letter+this.number;
    }
    
    public void setNumber(int receivedNumber){
        this.number = Integer.toString(receivedNumber);
    }
    
    public void setLetter(int receivedLetter){
        this.letter = Character.toString((char)(receivedLetter));
    }
    
    public int getX(){
        return this.x;
    }
    
    public void setX(int receivedX){
        this.x = receivedX;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setY(int receivedY){
        this.y = receivedY;
    }
    
    public boolean getBeToken(){
        return this.beToken;
    }
    
    public void setBeToken(boolean receivedBeToken){
        this.beToken = receivedBeToken;
    }
    
    public int getID(){
        return this.id;
    }
    
    public void setID(int receivedId){
        this.id = receivedId;
    }
    
    public void checkBox5x5(int n, int m){
        if(HandC.arraySquares[n][m].getBeToken()==true){
            Player myPlayer = HandC.players[HandC.arraySquares[n][m].getID()];
            if(n==x-2 || n==x+2 || m==y-2 || m==y+2){
                myPlayer.setPoints(1);
            }else if(n==x && m==y){
                myPlayer.setPoints(3);
            }else{
                myPlayer.setPoints(2);
            }
        }
    }
    
    public void addPoints(){
        if(y>=2 && y<=13 && x>=2 && x<=27){
            for(int m=y-2;m<(y+3);m++){
                for(int n=x-2;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(y==1 && x>1 && x<28){
           for(int m=y-1;m<(y+3);m++){
                for(int n=x-2;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            } 
        }
        if(y==14 && x>1 && x<28){
            for(int m=y-2;m<(y+2);m++){
                for(int n=x-2;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==1 && y>1 && x<14){
            for(int m=y-2;m<(y+3);m++){
                for(int n=x-1;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==28 && y>1 && x<14){
            for(int m=y-2;m<(y+3);m++){
                for(int n=x-2;n<(x+2);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==1 && y==1){
            for(int m=y-1;m<(y+3);m++){
                for(int n=x-1;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==1 && y==14){
            for(int m=y-2;m<(y+2);m++){
                for(int n=x-1;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==28 && y==1){
            for(int m=y-1;m<(y+3);m++){
                for(int n=x-2;n<(x+2);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==28 && y==14){
            for(int m=y-2;m<(y+2);m++){
                for(int n=x-2;n<(x+2);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(y==0 && x>0 && x<29){
           for(int m=y;m<(y+3);m++){
                for(int n=x-2;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            } 
        }
        if(y==15 && x>0 && x<29){
            for(int m=y-2;m<(y+1);m++){
                for(int n=x-2;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==0 && y>0 && x<15){
            for(int m=y-2;m<(y+3);m++){
                for(int n=x;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==29 && y>0 && x<15){
            for(int m=y-2;m<(y+3);m++){
                for(int n=x-2;n<(x+1);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==0 && y==0){
            for(int m=y;m<(y+3);m++){
                for(int n=x;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==0 && y==15){
            for(int m=y-2;m<(y+1);m++){
                for(int n=x;n<(x+3);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==29 && y==0){
            for(int m=y;m<(y+3);m++){
                for(int n=x-2;n<(x+1);n++){
                    checkBox5x5(n, m);
                }
            }
        }
        if(x==29 && y==15){
            for(int m=y-2;m<(y+1);m++){
                for(int n=x-2;n<(x+1);n++){
                    checkBox5x5(n, m);
                }
            }
        }
    }
}
