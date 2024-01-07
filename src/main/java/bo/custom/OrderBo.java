package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {


    boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;
    List<OrderDto> allOrders() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDto dto)throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;


}
