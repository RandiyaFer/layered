package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderTm extends RecursiveTreeObject<OrderTm> {
    private String id;
    private String date;
    private String customerId;
    private JFXButton btn;
}

