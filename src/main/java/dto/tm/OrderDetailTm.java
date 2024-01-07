package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.awt.*;

import javafx.scene.control.Button;

public class OrderDetailTm extends RecursiveTreeObject<OrderDetailTm> {
    private String id;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private Button btn;

    public OrderDetailTm(String id, String itemCode, int qty, double unitPrice,Button btn) {
        this.id = id;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.btn = btn;
    }

    public OrderDetailTm(){}

    @Override
    public String toString() {
        return "OrderDetailTm{" +
                "id='" + id + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", btn=" + btn +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
