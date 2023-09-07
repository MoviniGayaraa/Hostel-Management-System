package lk.ijse.d24hostelsystem.bo.custom.impl;

import lk.ijse.d24hostelsystem.bo.custom.LoginBO;
import lk.ijse.d24hostelsystem.dao.DAOFactory;
import lk.ijse.d24hostelsystem.dao.custom.UserDAO;
import lk.ijse.d24hostelsystem.dto.UsersDTO;
import lk.ijse.d24hostelsystem.entity.Users;
import lk.ijse.d24hostelsystem.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.util.List;

public class LoginBOImpl implements LoginBO {
    private Session session;
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Users);

    @Override
    public UsersDTO getUsersDto(String userName) throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);

        Users users = userDAO.getUsers(userName);
        return new UsersDTO(
                users.getId(),
                users.getName(),
                users.getUserName(),
                users.getPassword(),
                users.getEmail()
        );
    }

    @Override
    public List<UsersDTO> loadAll() throws Exception {
        return null;
    }
}
