package lk.ijse.d24hostelsystem.bo.custom;



import lk.ijse.d24hostelsystem.bo.SuperBO;
import lk.ijse.d24hostelsystem.dto.RoomDTO;

import java.util.List;

public interface RoomsBO extends SuperBO {
    List<RoomDTO> loadAll() throws Exception;
    boolean saveRoom(RoomDTO roomDTO) throws Exception;
    boolean updateRoom(RoomDTO roomDTO) throws Exception;
    boolean deleteRoom(RoomDTO roomDTO) throws Exception;
    String generateNextRoomID() throws Exception;
}
