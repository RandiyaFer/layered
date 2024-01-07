package dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    @Getter
    private String id;
    @Getter
    private String date;
    @Getter
    private String customerId;
    @Getter
    private List<OrderDetailDto> list;

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setList(List<OrderDetailDto> list) {
        this.list = list;
    }
}
