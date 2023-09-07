package lk.ijse.d24hostelsystem.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @Column(length = 10,name = "room_type_id")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "key_money")
    private String keyMoney;

    @Column(name = "Qty")
    private int qty;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "room")
    private List<Reservation> reservationList;

    public Room() {
    }

    public Room(String id, String type, String keyMoney, int qty) {
        this.id = id;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                ", reservationList=" + reservationList +
                '}';
    }
}
