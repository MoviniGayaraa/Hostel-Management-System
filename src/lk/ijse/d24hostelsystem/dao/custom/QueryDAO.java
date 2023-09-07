package lk.ijse.d24hostelsystem.dao.custom;

import lk.ijse.d24hostelsystem.dao.SuperDAO;
import lk.ijse.d24hostelsystem.projection.StudentDetailsDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    //List<StudentDetailsDTO> getUnpaidStudents();
    List<StudentDetailsDTO> getUnpaidStudents();
}
