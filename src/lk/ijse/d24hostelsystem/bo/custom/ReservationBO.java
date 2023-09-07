package lk.ijse.d24hostelsystem.bo.custom;



import lk.ijse.d24hostelsystem.bo.SuperBO;
import lk.ijse.d24hostelsystem.dto.ReservationDTO;
import lk.ijse.d24hostelsystem.dto.RoomDTO;
import lk.ijse.d24hostelsystem.dto.StudentDTO;
import lk.ijse.d24hostelsystem.projection.StudentDetailsDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    List<ReservationDTO> loadAll() throws Exception;
    boolean saveReservation(ReservationDTO reservationDTO) throws Exception;
    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;
    StudentDTO getStudent(String id) throws Exception;
    RoomDTO getRoom(String id) throws Exception;

    boolean deleteReservation(ReservationDTO reservationDTO) throws Exception;

    String generateNextReservationID() throws Exception;

    List<String> getStudentIds() throws Exception;

    List<String> getRoomIds() throws Exception;

    boolean updateRoomQty(RoomDTO roomDTO) throws Exception;

    List<StudentDetailsDTO> getUnpaidStudents();

    boolean changePaidStatus(String id,String status) throws Exception;
}
