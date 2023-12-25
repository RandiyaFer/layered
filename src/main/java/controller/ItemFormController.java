package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.CustomerDto;
import dto.ItemDto;
import dto.tm.ItemTm;
import entity.Item;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.Double.parseDouble;

public class ItemFormController {
    @FXML
    private BorderPane pane;
    @FXML
    public JFXTextField txtCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<ItemTm> tblItem;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colDesc;
    @FXML
    private TableColumn colUnitPrice;
    @FXML
    private TableColumn colQtyOnHand;
    @FXML
    private TableColumn colOption;

    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);


    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("item"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("uniPrize"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadItemTable();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        try {
            List<ItemDto> dtoList  = itemBo.allItems();
            for (ItemDto dto:dtoList) {
                JFXButton btn = new JFXButton("Delete");

                ItemTm tm = new ItemTm(
                        dto.getCode(),
                        dto.getDescription(),
                        dto.getUnitPrice(),
                        dto.getQtyOnHand(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    //deleteItem(c.getCode());
                });
                tmList.add(tm);
            }
            tblItem.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {
            txtCode.setEditable(false);
            txtCode.setText(newValue.getCode());
            txtDescription.setText(newValue.getDescription());
            txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
        }
    }
    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        ItemDto dto = new ItemDto(txtCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQtyOnHand.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );

        try {
            boolean isSaved = itemBo.saveItem(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                loadItemTable();;
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tblItem.refresh();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDescription.clear();
        txtCode.clear();
        txtCode.setEditable(true);
    }

    @FXML
    public void updateButtonOnAction(ActionEvent actionEvent) {
        ItemDto dto = new ItemDto(
                txtCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQtyOnHand.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );

        try {
            boolean isUpdated = itemBo.updateItem(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Item "+dto.getCode()+" Updated!").show();
                loadItemTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteItem(String code) {

        try {
            boolean isDeleted = itemBo.deleteItem(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
