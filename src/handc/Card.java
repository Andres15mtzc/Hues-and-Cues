
package handc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Card extends StackPane{
    private static int CARD_WIDTH = 100;
    private static int CARD_HEIGHT = 140;
    
    private Card(){}
    
    public Card(ColorSquare color1, ColorSquare color2, ColorSquare color3, ColorSquare color4){
        //Caracteristicas de la carta
        setStyle("-fx-background-color: black;");
        this.setMinSize(Card.CARD_WIDTH, Card.CARD_HEIGHT);
        setMaxSize(Card.CARD_WIDTH, Card.CARD_HEIGHT);
        
        //Crear la fisica de la carta
        GridPane cardForms = new GridPane();
        cardForms.setAlignment(Pos.CENTER);
        
        //Colocar todas las partes de la carta
        creatorColorSquare(cardForms, 0, 0, "", 30, 30, color1.getColor());
        creatorLabel(cardForms, 1, 0, "", 10, 30);
        creatorColorSquare(cardForms, 2, 0, "", 30, 30, color2.getColor());

        creatorLabel(cardForms, 0, 1, color1.getLetterNumber(), 30, 20);
        creatorLabel(cardForms, 1, 1, "", 10, 20);
        creatorLabel(cardForms, 2, 1, color2.getLetterNumber(), 30, 20);
        
        creatorLabel(cardForms, 0, 2, "", 30, 10);
        creatorLabel(cardForms, 1, 2, "", 10, 10);
        creatorLabel(cardForms, 2, 2, "", 30, 10);
        
        creatorColorSquare(cardForms, 0, 3, "", 30, 30, color3.getColor());
        creatorLabel(cardForms, 1, 3, "", 10, 30);
        creatorColorSquare(cardForms, 2, 3, "", 30, 30, color4.getColor());
        
        creatorLabel(cardForms, 0, 4, color3.getLetterNumber(), 30, 20);
        creatorLabel(cardForms, 1, 4, "", 10, 20);
        creatorLabel(cardForms, 2, 4, color4.getLetterNumber(), 30, 20);
        
        /*//Crear Boton de Card
        Button cardBtn = new Button();
        cardBtn.setMinSize(Card.CARD_WIDTH, Card.CARD_HEIGHT);
        cardBtn.setMaxSize(Card.CARD_WIDTH, Card.CARD_HEIGHT);
        cardBtn.setStyle("-fx-background-color: transparent;");
        
        cardBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Button sourceBtn = (Button) event.getSource();
                Card sourceParent = (Card) sourceBtn.getParent();
                
                int discardPileSize = HandC.discardPile.getChildren().size();
                Card topCard = null;
                
                if (discardPileSize > 0){
                    topCard = (Card) HandC.discardPile.getChildren().get(discardPileSize-1);
                }
                if (){
                    HandC.pile.getChildren().remove(sourceParent);
                    HandC.discardPile.getChildren().add(sourceParent);
                }
            }
        });*/
        
        //Colocar fisicas de la carta
        getChildren().add(cardForms);
        //getChildren().add(cardBtn);
    }
    
    
    //Colocar espacios en cartas 
    public void creatorLabel(GridPane cardForms, int i, int x, String text, double widht, double height){
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white;");
        label.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 10));
        label.setAlignment(Pos.CENTER);
        label.setMinSize(widht, height);
        label.setMaxSize(widht, height);
        cardForms.add(label, i, x);
    }
    //Colocar cuadros de color en cartas
    public void creatorColorSquare(GridPane cardForms, int i, int x, String text, double widht, double height, String color){
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white; -fx-background-color: #" + color + ";");
        label.setFont(Font.font("Arial BLACK", FontWeight.EXTRA_BOLD, 8));
        label.setAlignment(Pos.BOTTOM_CENTER);
        label.setMinSize(widht, height);
        label.setMaxSize(widht, height);
        cardForms.add(label, i, x);
    }
    
}
