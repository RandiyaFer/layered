package bo.custom.impl;

import bo.custom.OrderDetailBo;
import dao.custom.OrderDetailDao;
import dao.custom.impl.OrderDetailDaoImpl;
import dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDetailBoImpl implements OrderDetailBo {
    private OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();


    @Override
    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
        return false;
    }




    @Override
    public List<OrderDetailDto> allOrders() throws SQLException, ClassNotFoundException {
        List<OrderDetailDto> entityList = orderDetailDao.getAll();
        List<OrderDetailDto> list = new ArrayList<>();
        for (OrderDetailDto orderDetail:entityList) {
            list.add(new OrderDetailDto(
                    orderDetail.getOrderId(),
                    orderDetail.getItemCode(),
                    orderDetail.getQty(),
                    orderDetail.getUnitPrice()
            ));
        }
        return list;
    }
}
