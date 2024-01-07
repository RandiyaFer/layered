package bo.custom.impl;

import bo.custom.OrderBo;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;


public class OrderBoImpl implements OrderBo {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDto> allOrders() throws SQLException, ClassNotFoundException {
        return orderDao.getAll();
    }

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.save(dto);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        try {
            OrderDto dto = orderDao.getLastOrder();
            if (dto!=null){
                String id = dto.getId();
                int num = Integer.parseInt(id.split("[D]")[1]);
                num++;
                return String.format("D%03d",num);
            }else{
                return "D001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }




}
