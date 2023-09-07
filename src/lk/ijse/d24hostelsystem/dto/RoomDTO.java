package lk.ijse.d24hostelsystem.dto;


public class RoomDTO {
    private String id;
    private String type;
    private String keyMoney;
    private int qty;

    //private List<ReservationDTO> reservationList;

    public RoomDTO() {
    }

    public RoomDTO(String id, String type, String keyMoney, int qty) {
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
        return "RoomDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                '}';
    }
}
