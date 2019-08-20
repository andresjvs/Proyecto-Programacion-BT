/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp.pkg2019.ii.arraycirculation;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author 19473409
 */
public class VentanaFXMLDocumentController implements Initializable {
    int n;
    private Twitter twitter;
    
    // Labels
    @FXML private Label contadorCaracteres;
    
    // Botones
    @FXML private Button botonTweet, botonRetweet, botonSpam, botonAtras, publicarid;

    // Contenedores
    @FXML private HBox hboxBotones;
    @FXML private AnchorPane paneTwittear;
    
    // Textfield 
    @FXML private TextArea textTwit;
    
    // cambio de ventana
    /**
     * Cambio de la ventana principal a la ventana de twittear.
     * @param event presionar boton
     */
    public void AlertaSituacional(String situacion){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Su tweet "+situacion);

            alert.showAndWait();
    }
    
    @FXML private void presionar_twittear(ActionEvent event) {
        hboxBotones.setVisible(false);
        paneTwittear.setVisible(true);
        /*
        FadeTransition ft = new FadeTransition(Duration.seconds(1), hboxBotones);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.play();
        */
        /*
        try{ Thread.sleep(1000);
        }catch(Exception i){
            System.out.println("Error 001 \n-Thread.sleep-");
        } 
        */   
        
    }
    @FXML private void publicar(ActionEvent event) throws TwitterException, IOException{
        String situacion = "";
        if (n<141){
            Twitter twitter = TwitterFactory.getSingleton();
            Status status = twitter.updateStatus(textTwit.getText());
            situacion="ha sido publicado exitosamente";
            presionar_atras(event);
            
        }
        else{
            situacion="no cumple con el límite de carácteres";
            
        }
        AlertaSituacional(situacion);
    }
    /**
     * Vuelve a las opciones principales.
     * @param event presionar boton
     */
    @FXML private void presionar_atras(ActionEvent event){
        paneTwittear.setVisible(false);
        textTwit.clear();
        contadorCaracteres.setText("0/140");
        hboxBotones.setVisible(true);
    }
    
    /**
     * Modifica el contador de caracteres en tiempo real.
     * @param event 
     */
    @FXML public void contar_caracteres(KeyEvent event){
        n = textTwit.getText().length();
        contadorCaracteres.setText(n+"/140");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // funcionamiento del area de texto
        textTwit.setWrapText(true);
        
        
        
        // Iconos de los botones principales
        botonTweet.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/imagenes/tweetF.png"))));
        botonRetweet.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/imagenes/retweetF.png"))));
        botonSpam.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/imagenes/spamF.png"))));
        botonRetweet.setDisable(true);
        botonSpam.setDisable(true);
        // TODO
    }    
    
}