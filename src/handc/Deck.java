
package handc;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class Deck extends StackPane{
    public Deck(){
        //Caracteristicas del mazo
        setAlignment(Pos.CENTER);
    }
    
    //Colocar el mazo 
    public void initializeDeck(ArrayList<Card> cards){
        for ( Card card : cards){
            this.getChildren().add(card);
        }
    }    
}
