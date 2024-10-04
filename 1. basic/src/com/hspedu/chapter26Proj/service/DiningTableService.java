package com.hspedu.chapter26Proj.service;

import com.hspedu.chapter26Proj.dao.DiningTableDAO;
import com.hspedu.chapter26Proj.domain.DiningTable;

import java.util.List;

public class DiningTableService {
    private final DiningTableDAO diningTableDAO = new DiningTableDAO();

    /**
     * 获取所有餐桌
     *
     * @return List<DiningTable>
     */
    public List<DiningTable> getAllDiningTables() {
        String sql = "select * from diningTable";
        return diningTableDAO.queryMulti(sql, DiningTable.class);
    }

    /**
     * 根据具体id查一个餐桌
     *
     * @param id
     * @return
     */
    public DiningTable getDiningTableById(int id) {
        String sql = "select * from diningTable where id=?";
        return diningTableDAO.querySingle(sql, DiningTable.class, id);
    }

    /**
     * 预定餐桌
     *
     * @param id
     * @param empId
     * @param orderName
     * @param orderTel
     * @return
     */
    public boolean orderDiningTable(int id, String empId, String orderName, String orderTel) {
        String sql = "update diningTable set status=0,empId=?,orderName=?,orderTel=? where id=?";
        int updateRowsNum = diningTableDAO.update(sql, empId, orderName, orderTel, id);
        return updateRowsNum > 0;
    }

    /**
     * 根据Id初始化餐桌
     *
     * @param id
     * @return
     */
    public boolean initDiningTableByIdl(int id) {
        String sql = "update diningTable set status=1,orderName='',orderTel='' where id=?";
        int updateRowsNum = diningTableDAO.update(sql, id);
        return updateRowsNum > 0;
    }
}
