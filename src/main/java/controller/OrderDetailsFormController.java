package controller;

import bo.BoFactory;
import bo.custom.OrderBo;
import bo.custom.OrderDetailBo;
import com.jfoenix.controls.JFXButton;
import dao.util.BoType;
import dto.OrderDto;
import dto.tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsFormController {

    public TextField txtSearch;
    public TableView<OrderTm> tblOrders;
    public TableColumn colCode;
    public TableColumn colDesc;
    public TableColumn colQty;
    public TableColumn colOption;
    public TableView tblOrdDetail;
    public TableColumn colItemCode;
    public TableColumn colOrdId;
    public TableColumn colUnitPrice;
    public TableColumn colOption1;
    public Button srchId;
    public TableColumn colId;
    public TableColumn colDate;
    public TableColumn colCusId;

    private TextField txtCode;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private TextField txtQtyOnHand;

    private OrderDetailBo orderDetailBo = BoFactory.getInstance().getBo(BoType.ORDER_DETAIL);

    private OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);

//    private OrdersBo ordersBo = BoFactory.getInstance().getBo(BoType.ORDER);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadOrderTable();

        tblOrders.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {});
    }

    private void loadOrderTable() {
        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();
        try {
            List<OrderDto> dtoList  = orderBo.allOrders();
            for (OrderDto dto:dtoList) {
                JFXButton btn = new JFXButton("Delete");
                OrderTm tm = new OrderTm(
                        dto.getId(),
                        dto.getDate(),
                        dto.getCustomerId(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteOrder(tm.getId());
                });
                tmList.add(tm);
            }

            tblOrders.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteOrder(String id) {

    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) tblOrders.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
