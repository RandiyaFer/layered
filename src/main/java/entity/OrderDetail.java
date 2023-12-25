package entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
}
