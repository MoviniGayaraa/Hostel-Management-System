package lk.ijse.d24hostelsystem.bo.custom;

import lk.ijse.d24hostelsystem.bo.SuperBO;
import lk.ijse.d24hostelsystem.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> loadAll() throws Exception;
    boolean saveStudent(StudentDTO studentDTO) throws Exception;
    boolean updateStudent(StudentDTO studentDTO) throws Exception;
    boolean deleteStudent(StudentDTO studentDTO) throws Exception;
    String generateNextStudentID() throws Exception;
}
