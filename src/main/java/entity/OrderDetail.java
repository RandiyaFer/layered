package entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderDetail {
    @EmbeddedId
    private OrderDetailsKey id;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "itemCode")
    Item item;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    Orders orders;

    private int qty;
    private double unitPrice;

    public OrderDetailsKey getId() {
        return id;
    }

    public void setId(OrderDetailsKey id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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
}
