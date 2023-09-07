package lk.ijse.d24hostelsystem.dao.custom;


import lk.ijse.d24hostelsystem.dao.CrudDAO;
import lk.ijse.d24hostelsystem.entity.Users;
import org.hibernate.Session;

public interface UserDAO extends CrudDAO<Users> {
    void setSession(Session session) throws Exception;

    Users getUsers(String userName);

}
