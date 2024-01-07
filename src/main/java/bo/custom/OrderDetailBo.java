package bo.custom;

import bo.SuperBo;
import dto.ItemDto;
import dto.OrderDetailDto;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBo extends SuperBo {
    boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;

    List<OrderDetailDto> allOrders() throws SQLException, ClassNotFoundException;
}
