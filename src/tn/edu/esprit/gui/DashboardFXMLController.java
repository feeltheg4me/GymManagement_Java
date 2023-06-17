/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.github.sarxos.webcam.Webcam;
import com.sanctionco.jmail.JMail;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.bind.ValidationException;
import tn.edu.esprit.entities.Coach;
import tn.edu.esprit.entities.Member;
import tn.edu.esprit.utils.ValidationUtils;
import tn.edu.esprit.utils.DataSource;
import tn.edu.esprit.utils.EmailSender;
import tn.edu.esprit.utils.data;

/**
 *
 * @author MarcoMan Subscribe our YouTube Channel:
 * https://www.youtube.com/@marcomanchannel
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button coaches_btn;

    @FXML
    private Button members_btn;

    @FXML
    private Button logout;

    @FXML
    private Button payment_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_NM;

    @FXML
    private Label dashboard_NC;

    @FXML
    private Label dashboard_TI;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private AnchorPane coaches_form;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private TextField coaches_coachID;

    @FXML
    private TextField coaches_name;

    @FXML
    private TextArea coaches_address;

    @FXML
    private ComboBox<String> coaches_gender;

    @FXML
    private TextField coaches_phoneNum;

    @FXML
    private Button coaches_addBtn;

    @FXML
    private Button coaches_updateBtn;

    @FXML
    private Button coaches_resetBtn;

    @FXML
    private Button coaches_deleteBtn;

    @FXML
    private ComboBox<String> coaches_status;

    @FXML
    private TableView<Coach> coaches_tableView;

    @FXML
    private TableColumn<Coach, String> coaches_col_coachID;

    @FXML
    private TableColumn<Coach, String> coaches_col_name;

    @FXML
    private TableColumn<Coach, String> coaches_col_address;

    @FXML
    private TableColumn<Coach, String> coaches_col_gender;

    @FXML
    private TableColumn<Coach, String> coaches_col_phoneNum;

    @FXML
    private TableColumn<Coach, String> coaches_col_status;

    @FXML
    private AnchorPane members_form;

    @FXML
    private TextField members_customerId;

    @FXML
    private TextField members_name;

    @FXML
    private TextArea members_caddress;

    @FXML
    private TextField members_phoneNum;

    @FXML
    private ComboBox<String> members_gender;

    @FXML
    private ComboBox<String> members_schedule;

    @FXML
    private DatePicker members_startDate;

    @FXML
    private DatePicker members_endDate;

    @FXML
    private ComboBox<String> members_status;

    @FXML
    private Button members_addBtn;

    @FXML
    private Button members_clearBtn;

    @FXML
    private Button members_updateBtn;

    @FXML
    private Button members_deleteBtn;

    @FXML
    private TableView<Member> members_tableView;

    @FXML
    private TableColumn<Member, String> members_col_customerID;

    @FXML
    private TableColumn<Member, String> members_col_name;

    @FXML
    private TableColumn<Member, String> members_col_address;

    @FXML
    private TableColumn<Member, String> members_col_phoneNum;

    @FXML
    private TableColumn<Member, String> members_col_gender;

    @FXML
    private TableColumn<Member, String> members_col_schedule;

    @FXML
    private TableColumn<Member, String> members_col_startDate;

    @FXML
    private TableColumn<Member, String> members_col_endDate;

    @FXML
    private TableColumn<Member, String> members_col_status;

    @FXML
    private AnchorPane payment_Form;

    @FXML
    private TableView<Member> payment_tableView;

    @FXML
    private TableColumn<Member, String> payment_col_customerID;

    @FXML
    private TableColumn<Member, String> payment_col_name;

    @FXML
    private TableColumn<Member, String> payment_col_startDate;

    @FXML
    private TableColumn<Member, String> payment_col_endDate;

    @FXML
    private TableColumn<Member, String> payment_col_status;

    @FXML
    private ComboBox<?> payment_customerID;

    @FXML
    private ComboBox<?> payment_name;

    @FXML
    private Label payment_total;

    @FXML
    private TextField payment_amount;

    @FXML
    private Label payment_change;

    @FXML
    private Button payment_payBtn;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void emptyFields() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }

    public void dashboardNM() {

        String sql = "SELECT COUNT(id) FROM member WHERE status = 'Paid'";

        connect = DataSource.getInstance().getCnx();

        int nm = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nm = result.getInt("COUNT(id)");
            }

            dashboard_NM.setText(String.valueOf(nm));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTC() {

        String sql = "SELECT COUNT(id) FROM coach WHERE status = 'Active'";

        connect = DataSource.getInstance().getCnx();

        int tc = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tc = result.getInt("COUNT(id)");
            }
            dashboard_NC.setText(String.valueOf(tc));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTI() {

        String sql = "SELECT SUM(price) FROM member WHERE status = 'Paid'";

        connect = DataSource.getInstance().getCnx();

        double ti = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getDouble("SUM(price)");
            }

            dashboard_TI.setText( String.valueOf(ti) + "DT");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardChart() {

        dashboard_incomeChart.getData().clear();

        String sql = "SELECT startDate, SUM(price) FROM member WHERE status = 'Paid' GROUP BY startDate ORDER BY TIMESTAMP(startDate) ASC LIMIT 10";

        connect = DataSource.getInstance().getCnx();

        XYChart.Series chart = new XYChart.Series();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
            }

            dashboard_incomeChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesAddBtn() {

        String sql = "INSERT INTO coach (coachId, nom, adress, gender, phone, status) "
                + "VALUES(?,?,?,?,?,?)";

        if (!validateCurrentCoach()) {
            return;
        }
        connect = DataSource.getInstance().getCnx();

        try {

            Alert alert;

            String checkData = "SELECT coachId FROM coach WHERE coachId = '"
                    + coaches_coachID.getText() + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(checkData);

            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Coach ID: " + coaches_coachID.getText() + " was already taken!");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, coaches_coachID.getText());
                prepare.setString(2, coaches_name.getText());
                prepare.setString(3, coaches_address.getText());
                prepare.setString(4, (String) coaches_gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, coaches_phoneNum.getText());
                prepare.setString(6, (String) coaches_status.getSelectionModel().getSelectedItem());

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                // TO INSERT all DATA
                prepare.executeUpdate();
                // TO UPDATE TABLEVIEW
                coachesShowData();
                // TO CLEAR ALL FIELDS
                coachesClearBtn();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesUpdateBtn() {

        String sql = "UPDATE coach SET nom = '"
                + coaches_name.getText() + "', adress = '"
                + coaches_address.getText() + "', gender = '"
                + coaches_gender.getSelectionModel().getSelectedItem() + "', phone = '"
                + coaches_phoneNum.getText() + "', status = '"
                + coaches_status.getSelectionModel().getSelectedItem() + "' WHERE coachId = '"
                + coaches_coachID.getText() + "'";

        if (!validateCurrentCoach()) {
            return;
        }

        connect = DataSource.getInstance().getCnx();

        try {
            Alert alert;
            if (coaches_coachID.getText().isEmpty() || coaches_name.getText().isEmpty()
                    || coaches_address.getText().isEmpty()
                    || coaches_gender.getSelectionModel().getSelectedItem() == null
                    || coaches_phoneNum.getText().isEmpty()
                    || coaches_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Coach ID: " + coaches_coachID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);

                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    // TO UPDATE TABLEVIEW
                    coachesShowData();
                    // TO CLEAR ALL FIELDS
                    coachesClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update!");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesDeleteBtn() {
        String sql = "DELETE FROM coach WHERE coachId = '"
                + coaches_coachID.getText() + "'";

        connect = DataSource.getInstance().getCnx();

        try {
            Alert alert;
            if (coaches_coachID.getText().isEmpty() || coaches_name.getText().isEmpty()
                    || coaches_address.getText().isEmpty()
                    || coaches_gender.getSelectionModel().getSelectedItem() == null
                    || coaches_phoneNum.getText().isEmpty()
                    || coaches_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Coach ID: " + coaches_coachID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);

                    // TO EXECUTE THE QUERY YOU TYPED
                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    // TO UPDATE TABLEVIEW
                    coachesShowData();
                    // TO CLEAR ALL FIELDS
                    coachesClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update!");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesClearBtn() {
        coaches_coachID.setText("");
        coaches_name.setText("");
        coaches_address.setText("");
        coaches_gender.getSelectionModel().clearSelection();
        coaches_phoneNum.setText("");
        coaches_status.getSelectionModel().clearSelection();
    }

    public ObservableList<Coach> coachesDataList() {

        ObservableList<Coach> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM coach";

        connect = DataSource.getInstance().getCnx();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Coach cd;

            while (result.next()) {
                cd = new Coach(result.getInt("id"), result.getString("coachId"),
                        result.getString("nom"), result.getInt("phone"),
                        result.getString("status"), result.getString("adress"),
                        result.getString("gender"));

                listData.add(cd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<Coach> coachesListData;

    public void coachesShowData() {

        coachesListData = coachesDataList();

        coaches_col_coachID.setCellValueFactory(new PropertyValueFactory<>("coachId"));
        coaches_col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coaches_col_address.setCellValueFactory(new PropertyValueFactory<>("adress"));
        coaches_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coaches_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phone"));
        coaches_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        coaches_tableView.setItems(coachesListData);

    }

    public void coachesSelect() {
        Coach cd = coaches_tableView.getSelectionModel().getSelectedItem();
        int num = coaches_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        coaches_coachID.setText(cd.getCoachId());
        coaches_name.setText(cd.getNom());
        coaches_address.setText(cd.getAdress());
        coaches_phoneNum.setText(String.valueOf(cd.getPhone()));
        String _status = String.valueOf(cd.getStatus());
        String _gender = String.valueOf(cd.getGender());
        coaches_gender.setValue(_gender);
        coaches_status.setValue(_status);
    }

    private String gender[] = {"Male", "Female", "Others"};

    public void coachGenderList() {
        List<String> genderList = new ArrayList<>();

        for (String data : gender) {
            genderList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(genderList);
        coaches_gender.setItems(listData);
    }

    private String coachStatus[] = {"Active", "Inactive"};

    public void coachStatusList() {
        List<String> coachList = new ArrayList<>();

        for (String data : coachStatus) {
            coachList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(coachList);
        coaches_status.setItems(listData);
    }

    private int totalDay;
    private double price;

    public void membersAddBtn() {

        String sql = "INSERT INTO member (memberId, nom, adresse, phone, gender, trainingType, startDate, endDate, price, status) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = DataSource.getInstance().getCnx();

        if (!validateCurrentMember()) {
            return;
        }

        try {
            Alert alert;

            String checkData = "SELECT memberId FROM member WHERE memberId = '"
                    + members_customerId.getText() + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(checkData);

            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Member ID: " + members_customerId.getText() + " was already taken!");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, members_customerId.getText());
                prepare.setString(2, members_name.getText());
                prepare.setString(3, members_caddress.getText());
                prepare.setString(4, members_phoneNum.getText());
                prepare.setString(5, (String) members_gender.getSelectionModel().getSelectedItem());
                prepare.setString(6, (String) members_schedule.getSelectionModel().getSelectedItem());
                prepare.setString(7, String.valueOf(members_startDate.getValue()));
                prepare.setString(8, String.valueOf(members_endDate.getValue()));

                totalDay = (int) ChronoUnit.DAYS.between(members_startDate.getValue(), members_endDate.getValue());

                price = (totalDay * 50);

                prepare.setString(9, String.valueOf(price));
                prepare.setString(10, (String) members_status.getSelectionModel().getSelectedItem());

                prepare.executeUpdate();
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfuly Added!");
                alert.showAndWait();
                if(!tfEmail.getText().isEmpty())
                {
                    if (JMail.isValid(tfEmail.getText())) 
                    EmailSender.SendWelcomeEmail(tfEmail.getText());
                }
                membersShowData();
                membersClear();
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void membersUpdate() {

        if (!validateCurrentMember()) {
            return;
        }

        totalDay = (int) ChronoUnit.DAYS.between(members_startDate.getValue(), members_endDate.getValue());
        price = (totalDay * 50);

        String sql = "UPDATE member SET nom = '"
                + members_name.getText() + "', adresse = '"
                + members_caddress.getText() + "', phone = '"
                + members_phoneNum.getText() + "', gender = '"
                + members_gender.getSelectionModel().getSelectedItem() + "', trainingType = '"
                + members_schedule.getSelectionModel().getSelectedItem() + "', startDate = '"
                + members_startDate.getValue() + "', endDate = '"
                + members_endDate.getValue() + "', price = '"
                + String.valueOf(price) + "', status = '"
                + members_status.getSelectionModel().getSelectedItem() + "' WHERE memberId = '"
                + members_customerId.getText() + "'";

        connect = DataSource.getInstance().getCnx();

        try {
            Alert alert;

            if (members_customerId.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_caddress.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null
                    || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null
                    || members_endDate.getValue() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Member ID: " + members_customerId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly Updated!");
                    alert.showAndWait();

                    membersShowData();
                    membersClear();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update!!");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void membersDelete() {

        String sql = "DELETE FROM member WHERE memberId = '"
                + members_customerId.getText() + "'";

        connect = DataSource.getInstance().getCnx();

        try {
            Alert alert;

            if (members_customerId.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_caddress.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null
                    || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null
                    || members_endDate.getValue() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Member ID: " + members_customerId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly Deleted!");
                    alert.showAndWait();

                    membersShowData();
                    membersClear();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Delete!!");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void membersClear() {
        members_customerId.setText("");
        members_name.setText("");
        members_caddress.setText("");
        members_phoneNum.setText("");
        members_gender.getSelectionModel().clearSelection();
        members_schedule.getSelectionModel().clearSelection();
        members_startDate.setValue(null);
        members_endDate.setValue(null);
        members_status.getSelectionModel().clearSelection();
    }

    public void membersGender() {
        List<String> genderList = new ArrayList<>();

        for (String data : gender) {
            genderList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(genderList);
        members_gender.setItems(listData);

    }

    private String[] scheduleList = {"9AM - 11AM", "11AM - 1PM", "1PM - 3PM", "3PM - 5PM", "5PM - 7PM"};

    public void membersSchedule() {

        List<String> sl = new ArrayList<>();

        for (String data : scheduleList) {
            sl.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(sl);
        members_schedule.setItems(listData);

    }

    private String memberStatus[] = {"Paid", "Unpaid"};

    public void membersStatus() {

        List<String> ms = new ArrayList<>();

        for (String data : memberStatus) {
            ms.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(memberStatus);
        members_status.setItems(listData);

    }

    public ObservableList<Member> membersDataList() {

        ObservableList<Member> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM member";

        connect = DataSource.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Member md;

            while (result.next()) {
                md = new Member(result.getInt("id"),
                        result.getString("memberId"),
                        result.getString("nom"),
                        result.getString("adresse"),
                        result.getString("gender"),
                        result.getString("trainingType"),
                        result.getInt("phone"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getDouble("price"),
                        result.getString("status"));

                listData.add(md);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Member> membersListData;

    public void membersShowData() {
        membersListData = membersDataList();

        members_col_customerID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        members_col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        members_col_address.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        members_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phone"));
        members_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        members_col_schedule.setCellValueFactory(new PropertyValueFactory<>("trainingType"));
        members_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        members_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        members_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        members_tableView.setItems(membersListData);
    }

    public void membersSelect() {

        Member md = members_tableView.getSelectionModel().getSelectedItem();
        int num = members_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        members_customerId.setText(md.getMemberId());
        members_name.setText(md.getNom());
        members_caddress.setText(md.getAdresse());
        members_phoneNum.setText(String.valueOf(md.getPhone()));
        members_startDate.setValue(LocalDate.parse(String.valueOf(md.getStartDate())));
        members_endDate.setValue(LocalDate.parse(String.valueOf(md.getEndDate())));
        String _status = String.valueOf(md.getStatus());
        String _gender = String.valueOf(md.getGender());
        String _schedule = String.valueOf(md.getTrainingType());
        members_gender.setValue(_gender);
        members_schedule.setValue(_schedule);
        members_status.setValue(_status);
    }

    public void paymentMemberId() {

        String sql = "SELECT memberId FROM member WHERE status = 'Unpaid'";

        connect = DataSource.getInstance().getCnx();

        try {
            ObservableList listData = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("memberId"));
            }
            payment_customerID.setItems(listData);

            paymentName();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paymentName() {

        String sql = "SELECT nom FROM member WHERE memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem() + "'";

        connect = DataSource.getInstance().getCnx();

        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                listData.add(result.getString("nom"));
            }
            payment_name.setItems(listData);

            paymentDisplayTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double totalP;

    public void displayTotal() {

        String sql = "SELECT price FROM member WHERE nom = '"
                + payment_name.getSelectionModel().getSelectedItem() + "' and memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem() + "'";

        connect = DataSource.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("price");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paymentDisplayTotal() {
        displayTotal();
        payment_total.setText(String.valueOf(totalP) + "DT");
    }

    private double amount;
    private double change;

    public void paymentAmount() {
        displayTotal();

        Alert alert;

        if (payment_amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid !");
            alert.showAndWait();

            payment_amount.setText("");
        } else {
            amount = Double.parseDouble(payment_amount.getText());

            if (amount >= totalP) {
                change = (amount - totalP);
                payment_change.setText(String.valueOf(change) + "DT" );
            } else {
                payment_amount.setText("");
                change = 0;
                amount = 0;
            }
        }
    }

    public void paymentPayBtn() {

        String sql = "UPDATE member SET status = 'Paid' WHERE memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem() + "'";

        connect = DataSource.getInstance().getCnx();

        try {
            Alert alert;
            if (totalP == 0 || change == 0 || payment_amount.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something Wrong !");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();
                    
                    //PaymentBancaire();
                    
                    paymentShowData();
                    paymentClear();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paymentClear() {
        payment_customerID.getSelectionModel().clearSelection();
        payment_name.getSelectionModel().clearSelection();
        payment_total.setText("0.0DT");
        payment_amount.setText("");
        payment_change.setText("0.0DT");

        amount = 0;
        change = 0;
        totalP = 0;
    }

    public ObservableList<Member> paymentDataList() {

        ObservableList<Member> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM member";

        connect = DataSource.getInstance().getCnx();

        try {
            Member md;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                md = new Member(result.getInt("id"),
                        result.getString("memberId"),
                        result.getString("nom"),
                        result.getString("adresse"),
                        result.getString("gender"),
                        result.getString("trainingType"),
                        result.getInt("phone"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getDouble("price"),
                        result.getString("status"));

                listData.add(md);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Member> paymentListData;

    public void paymentShowData() {

        paymentListData = paymentDataList();

        payment_col_customerID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        payment_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        payment_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        payment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        payment_tableView.setItems(paymentListData);

    }

    public void displayUsername() {

        String user = data.login;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {

            dashboard_form.setVisible(true);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(false);

            dashboardNM();
            dashboardTC();
            dashboardTI();
            dashboardChart();

        } else if (event.getSource() == coaches_btn) {

            dashboard_form.setVisible(false);
            coaches_form.setVisible(true);
            members_form.setVisible(false);
            payment_Form.setVisible(false);

            // TO UPDATE WHEN YOU CLICK THE MENU BUTTON LIKE COACHES BUTTON
            coachGenderList();
            coachStatusList();
            coachesShowData();

        } else if (event.getSource() == members_btn) {

            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(true);
            payment_Form.setVisible(false);

            membersShowData();
            membersGender();
            membersSchedule();
            membersStatus();

        } else if (event.getSource() == payment_btn) {

            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(true);

            paymentShowData();
            paymentMemberId();
            paymentName();

        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void minimize() {

        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);

    }

    public void close() {

        javafx.application.Platform.exit();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayUsername();

        dashboardNM();
        dashboardTC();
        dashboardTI();
        dashboardChart();

        coachGenderList();
        coachStatusList();
        coachesShowData();

        membersShowData();
        membersGender();
        membersSchedule();
        membersStatus();

        paymentMemberId();
        paymentName();
        paymentShowData();

    }

    private boolean validateCurrentMember() {
        if (members_customerId.getText().isEmpty() || members_name.getText().isEmpty()
                || members_phoneNum.getText().isEmpty() || members_caddress.getText().isEmpty()
                || members_gender.getSelectionModel().getSelectedItem() == null
                || members_schedule.getSelectionModel().getSelectedItem() == null
                || members_startDate.getValue() == null
                || members_endDate.getValue() == null) {
            emptyFields();
            return false;
        }

        String phoneNumberText = members_phoneNum.getText();
        int parsedPhoneNumber = 0; // Default value when parsing fails

        try {
            parsedPhoneNumber = Integer.parseInt(phoneNumberText);
        } catch (NumberFormatException e) {
            // Parsing failed, do nothing (parsedPhoneNumber will remain 0)
        }

        Member mb = new Member(
                members_customerId.getText(),
                members_name.getText(),
                members_caddress.getText(),
                (String) members_gender.getSelectionModel().getSelectedItem(),
                (String) members_schedule.getSelectionModel().getSelectedItem(),
                parsedPhoneNumber,
                Date.valueOf(members_startDate.getValue()),
                Date.valueOf(members_endDate.getValue()),
                price,
                (String) members_status.getSelectionModel().getSelectedItem()
        );
        try {
            return ValidationUtils.validate(mb);
        } catch (ValidationException ex) {
            return false;
        }
    }

    private boolean validateCurrentCoach() {
        if (coaches_coachID.getText().isEmpty() || coaches_name.getText().isEmpty()
                || coaches_address.getText().isEmpty()
                || coaches_gender.getSelectionModel().getSelectedItem() == null
                || coaches_phoneNum.getText().isEmpty()
                || coaches_status.getSelectionModel().getSelectedItem() == null) {
            emptyFields();
            return false;
        }

        String phoneNumberText = coaches_phoneNum.getText();
        int parsedPhoneNumber = 0; // Default value when parsing fails

        try {
            parsedPhoneNumber = Integer.parseInt(phoneNumberText);
        } catch (NumberFormatException e) {
            // Parsing failed, do nothing (parsedPhoneNumber will remain 0)
        }

        Coach c = new Coach(
                coaches_coachID.getText(),
                coaches_name.getText(),
                parsedPhoneNumber,
                (String) coaches_status.getSelectionModel().getSelectedItem(),
                coaches_address.getText(),
                (String) coaches_gender.getSelectionModel().getSelectedItem()
        );
        try {
            return ValidationUtils.validate(c);
        } catch (ValidationException ex) {
            return false;
        }
    }

    private void dateStartSelected() {
        LocalDate startDate = members_startDate.getValue();

    }
    
    private ImageView imageView;

    public void takePhoto() {

        imageView = new ImageView();
        Button captureButton = new Button("Capture");
        captureButton.setOnAction(e -> capturePhoto());
        VBox root = new VBox(imageView, captureButton);
        Scene scene = new Scene(root, 400, 300);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

    }

    private void capturePhoto() {
        // Create a webcam instance
    Webcam webcam = Webcam.getDefault();

    if (webcam != null) {
        try {
            // Open the webcam and capture an image
            webcam.open();
            BufferedImage image = webcam.getImage();

            // Create a file to store the captured photo
            File photoFile = new File("captured_photo.png");

            // Write the captured image to the file
            ImageIO.write(image, "PNG", photoFile);

            // Close the webcam
            webcam.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    private void PaymentBancaire() {

        // Set your Stripe API secret key
        Stripe.apiKey = "sk_test_51NHS4sHxU5l7DItcUP7vYc2lv77B8FtWz3OGFHMX69A3KXqxSQLTTufapLPz9EtnPsAdSpgpTAKzga2YUNQEw1Bm00qzSTgXm0";
        long amount =  Long.parseLong(payment_amount.getText());
        // Create a charge
        ChargeCreateParams chargeParams = ChargeCreateParams.builder()
                .setAmount(amount) 
                .setCurrency("usd")
                .setDescription("Example Charge")
                .setSource("tok_visa") // Use a test token generated by Stripe.js or Checkout
                .build();

        try {
            Charge charge = Charge.create(chargeParams);
            System.out.println("Payment successful. Charge ID: " + charge.getId());
        } catch (StripeException e) {
            e.printStackTrace();
            // Handle any Stripe API errors
        }

    }
}
