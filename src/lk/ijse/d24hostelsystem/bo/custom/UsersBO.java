package lk.ijse.d24hostelsystem.bo.custom;



import lk.ijse.d24hostelsystem.bo.SuperBO;
import lk.ijse.d24hostelsystem.dto.UsersDTO;

import java.util.List;

public interface UsersBO extends SuperBO {
    List<UsersDTO> loadAll() throws Exception;
    boolean saveUsers(UsersDTO usersDTO) throws Exception;
    boolean updateUsers(UsersDTO usersDTO) throws Exception;
    boolean deleteUsers(UsersDTO usersDTO) throws Exception;

    String generateNextUserID() throws Exception;
}
