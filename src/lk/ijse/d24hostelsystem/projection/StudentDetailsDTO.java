package lk.ijse.d24hostelsystem.projection;

import lk.ijse.d24hostelsystem.entity.Room;

import java.util.Date;

public class StudentDetailsDTO {
    private String studentID;
    private String name;
    private String contactNo;

    private Date date;
    private String resID;

    private String roomID;

    private Room room;

    public StudentDetailsDTO() {
    }

    public StudentDetailsDTO(String studentID, String name, String contactNo, Date date, String resID, Room room) {
        this.studentID = studentID;
        this.name = name;
        this.contactNo = contactNo;
        this.date = date;
        this.resID = resID;
        this.room=room;
        roomID=room.getId();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        roomID=room.getId();
    }

    @Override
    public String toString() {
        return "StudentDetailsDTO{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", date=" + date +
                ", resID='" + resID + '\'' +
                ", room=" + roomID +
                '}';
    }
}
