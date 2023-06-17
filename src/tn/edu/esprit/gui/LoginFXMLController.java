/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.sanctionco.jmail.JMail;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tn.edu.esprit.utils.DataSource;
import tn.edu.esprit.utils.data;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.ValidationException;
import tn.edu.esprit.entities.Admin;
import tn.edu.esprit.entities.Member;
import tn.edu.esprit.utils.EmailSender;
import tn.edu.esprit.utils.ValidationUtils;

/**
 * FXML Controller class
 *
 * @author agn
 */
public class LoginFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblResetPassword;
    @FXML
    private Button BtnSendEmail;

    @FXML
    private TextField tfEmail2;

    @FXML
    private AnchorPane formResetPassword;
    @FXML
    private AnchorPane formMain;

    @FXML
    private AnchorPane subForm;

    @FXML
    private Button subBtnSignUp;

    @FXML
    private Button subBtnLogin;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private AnchorPane formSignUp;

    @FXML
    private TextField subTfLogin;

    @FXML
    private PasswordField subTfPassword;

    @FXML
    private Button subBtnSignup;

    @FXML
    private TextField subTfEmail;

    @FXML
    private AnchorPane formLogin;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button bntClose;
    @FXML
    private FontAwesomeIcon icnClose;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void login() {

        String sql = "SELECT * FROM admin WHERE login = ? and password = ?";

        connect = DataSource.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, tfLogin.getText());
            prepare.setString(2, tfPassword.getText());
            result = prepare.executeQuery();

            Alert alert;

            if (tfLogin.getText().isEmpty() || tfPassword.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else
            if (result.next()) {

                data.login = tfLogin.getText();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login!");
                alert.showAndWait();

                btnLogin.getScene().getWindow().hide();

                // LINK YOUR DASHBOARD FORM 
                Parent root = FXMLLoader.load(getClass().getResource("DashboardFXML.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void signup() {
        String sql = "INSERT INTO admin (email, login, password) VALUES(?,?,?)";

        connect = DataSource.getInstance().getCnx();

        try {
            Alert alert;

            if (subTfEmail.getText().isEmpty() || subTfLogin.getText().isEmpty()
                    || subTfPassword.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (subTfPassword.getText().length() < 6) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid password !");
                alert.showAndWait();
            } else {
                if (!validateAdmin()) {
                    return;
                }
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, subTfEmail.getText());
                prepare.setString(2, subTfLogin.getText());
                prepare.setString(3, subTfPassword.getText());

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully create new account!");
                alert.showAndWait();

                prepare.executeUpdate();

                subTfEmail.setText("");
                subTfLogin.setText("");
                subTfPassword.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signupSlider() {

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(subForm);
        slider1.setToX(400);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            lblCreateAccount.setText("Login Account");

            subBtnSignUp.setVisible(false);
            subBtnLogin.setVisible(true);
            icnClose.setFill(Color.valueOf("#fff"));
        });

    }

    public void loginSlider() {

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(subForm);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            lblCreateAccount.setText("Create Account");

            subBtnSignUp.setVisible(true);
            subBtnLogin.setVisible(false);
            icnClose.setFill(Color.valueOf("#000"));

        });

    }

    public void resetPassword() {
        formLogin.setVisible(false);
        formResetPassword.setVisible(true);

    }

    public void sendEmail() {

        Alert alert;
        if (tfEmail2.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }
        if (!JMail.isValid(tfEmail2.getText())) 
            return;
        // Generate a new password
        String newPassword = generateNewPassword(4, 3);

        // Send the password reset email
        EmailSender.sendPasswordResetEmail(newPassword,tfEmail2.getText());

        // Update the user's password in the database
        updatePasswordInDatabase(newPassword, "admin");

    }

    

    private void updatePasswordInDatabase(String newPassword, String userId) {
        String sql = "UPDATE admin SET password = '"
                + newPassword + "' WHERE email = '"
                + tfEmail2.getText() + "'";
        connect = DataSource.getInstance().getCnx();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Password reset successfully!");
            alert.showAndWait();

        } catch (Exception e) {
        }

    }

    private String generateNewPassword(int numCharacters, int numDigits) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";

        SecureRandom random = new SecureRandom();

        StringBuilder passwordBuilder = new StringBuilder();

        // Generate characters
        for (int i = 0; i < numCharacters; i++) {
            int randomIndex = random.nextInt(characters.length());
            passwordBuilder.append(characters.charAt(randomIndex));
        }

        // Generate digits
        for (int i = 0; i < numDigits; i++) {
            int randomIndex = random.nextInt(digits.length());
            passwordBuilder.append(digits.charAt(randomIndex));
        }

        return passwordBuilder.toString();
    }

    public void returnToLogin() {
        formResetPassword.setVisible(false);
        formLogin.setVisible(true);
    }

    public void close() {
        javafx.application.Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean validateAdmin() {
        try {
            Admin admin = new Admin(subTfLogin.getText(), subTfPassword.getText());
            
            return ValidationUtils.validate(admin);
        } catch (ValidationException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
