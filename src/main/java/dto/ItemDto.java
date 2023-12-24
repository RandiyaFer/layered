package dto;

import entity.Item;
import lombok.*;

public class ItemDto {

    public ItemDto(String code, String description, double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public ItemDto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getqtyOnHand() {
        return qtyOnHand;
    }

    public void setqtyOnHand(int qty) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "code='" + code + '\'' +
                ", desc='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qtyOnHand +
                '}';
    }

    private String code;

    private String description;
    private double unitPrice;
    private int qtyOnHand;
}


