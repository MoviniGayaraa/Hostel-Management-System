package lk.ijse.d24hostelsystem.dto;

import java.sql.Date;

public class ReservationDTO {
    private String resID;
    private Date date;
    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
    private String status;
    private String studentID;
    private String roomID;

    public ReservationDTO() {
    }

    public ReservationDTO(String resID, Date date, String status) {
        this.resID = resID;
        this.date = date;
        this.status = status;
    }

    public ReservationDTO(String resID, Date date, StudentDTO student, RoomDTO room, String status) {
        this.resID = resID;
        this.date = date;
        this.studentDTO = student;
        this.roomDTO = room;
        this.status = status;
        studentID=student.getStudentId();
        roomID=room.getId();
    }

    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StudentDTO getStudent() {
        return studentDTO;
    }

    public void setStudent(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
        studentID=studentDTO.getStudentId();
    }

    public RoomDTO getRoom() {
        return roomDTO;
    }

    public void setRoom(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
        roomID=roomDTO.getId();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "resID='" + resID + '\'' +
                ", date=" + date +
                ", student=" + studentDTO +
                ", room=" + roomDTO +
                ", status='" + status + '\'' +
                '}';
    }
}
