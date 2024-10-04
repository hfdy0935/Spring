package com.hspedu.chapter08.homeRentSys;

public class HouseService {
    private House[] houses; // 房屋数组
    private int idCounter = 1; // 房屋id
    private int houseNum = 1; // 已有房屋数量

    public HouseService(int size) {
        this.houses = new House[size];
        this.houses[0] = new House(1, "jack", "112", "海淀区", 2000, "未出租");
    }

    public House findById(int id) {
        for (House h : this.houses) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        int indexNeedDelete = -1; // 记录需要删除房屋的索引
        for (int i = 0; i < this.houseNum; i++) {
            if (this.houses[i].getId() == id) {
                indexNeedDelete = i;
            }
        }
        if (indexNeedDelete == -1) {
            return false;
        } else {
            // index后面的都向前复制，并把最后一个置空
            for (int i = indexNeedDelete; i < this.houseNum - 1; i++) {
                this.houses[i] = this.houses[i + 1];
            }
            this.houses[--this.houseNum] = null;
            return true;
        }
    }

    public boolean addHouse(House house) {
        System.out.println(this.houses.length);
        System.out.println(this.houseNum);
        if (this.houses.length == this.houseNum) {
            System.out.println("房屋已满，不能继续添加");
            return false;
        }
        this.houses[this.houseNum++] = house;
        house.setId(++this.idCounter);
        return true;
    }

    public House[] getHouses() {
        return this.houses;
    }
}
