package dao.custom;

import dao.CrudDao;
import dto.OrderDto;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrderDto> {
//    boolean delete(String value) throws SQLException, ClassNotFoundException;

    OrderDto getLastOrder() throws SQLException, ClassNotFoundException;
}
