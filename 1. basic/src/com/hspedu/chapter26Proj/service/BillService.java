package com.hspedu.chapter26Proj.service;

import com.hspedu.chapter26Proj.dao.BillDAO;
import com.hspedu.chapter26Proj.dao.MultiTableBillDAO;
import com.hspedu.chapter26Proj.domain.Bill;
import com.hspedu.chapter26Proj.domain.MultiTableBill;

import java.util.List;

public class BillService {
    private final BillDAO billDAO = new BillDAO();
    private final MultiTableBillDAO multiTableBillDAO = new MultiTableBillDAO();

    /**
     * 获取所有账单
     *
     * @return
     */
    public List<Bill> getAllBills(int empId) {
        String sql = "select * from bill where empId=?";
        return billDAO.queryMulti(sql, Bill.class, empId);
    }

    /**
     * 获取所有未支付的账单
     *
     * @param empId
     * @return
     */
    public List<Bill> getAllUnPaidBills(int empId) {
        String sql = "select * from bill where empId=? and status=0";
        return billDAO.queryMulti(sql, Bill.class, empId);
    }

    /**
     * 添加未支付的账单
     *
     * @param billId
     * @param empId
     * @param diningTableId
     * @param menuId
     * @param num
     * @param billDateTime
     * @param money
     * @return
     */
    public boolean addBill(String billId, Integer empId, Integer diningTableId, Integer menuId, Integer num, String billDateTime, Double money) {
        String sql = "insert into bill values(null,?,?,?,?,?,?,?,0)";
        System.out.println(num);
        System.out.println(money);
        int resRows = billDAO.update(sql, billId, empId, diningTableId, menuId, num, billDateTime, money);
        return resRows > 0;
    }

    /**
     * 根据id，修改订单支付状态为已支付
     *
     * @param id id
     * @return boolean
     */
    public boolean payBillById(int id) {
        String sql = "update bill set status=1 where id=?";
        int resRows = billDAO.update(sql, id);
        return resRows > 0;
    }

    /**
     * 获取所有账单，多表查询，有预订人姓名和菜品的name字段
     *
     * @return List<MultiTableBill>
     */
    public List<MultiTableBill> getAllMultiTableBills() {
        String sql = "select bill.*,employee.name as orderName,menu.name as menuName from bill,employee,menu where bill.empId=employee.id and bill.menuId=menu.id";
        return multiTableBillDAO.queryMulti(sql, MultiTableBill.class);
    }
}
