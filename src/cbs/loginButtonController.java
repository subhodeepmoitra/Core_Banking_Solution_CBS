/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cbs;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Subhodeep
 */
public class loginButtonController implements Initializable {
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    @FXML
    private Label label;
    
    @FXML
            private TextField userName;
    
    @FXML
            private PasswordField loginPassword;
    
     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight();

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cbs-db","root","");
            String sql = "SELECT `branchId`, `password` FROM `branch_user-db` WHERE `branchId`=? AND `password`=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, userName.getText());
            pst.setString(2, loginPassword.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                Parent root = FXMLLoader.load(getClass().getResource("branchModule.fxml"));
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(root, width, height));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } else{
                //JOptionPane.showMessageDialog(null, "Wrong credentials... :( ");
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
}
