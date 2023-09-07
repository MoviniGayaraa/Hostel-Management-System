package lk.ijse.d24hostelsystem.dao.custom.impl;

import lk.ijse.d24hostelsystem.dao.custom.UserDAO;
import lk.ijse.d24hostelsystem.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Session session;
    @Override
    public List<Users> loadAll() throws Exception {
        String sqlQuery="From Users ";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Users users) throws Exception {
        return (String) session.save(users);
    }

    @Override
    public void update(Users users) throws Exception {
        session.update(users);
    }

    @Override
    public void delete(Users users) throws Exception {
        session.delete(users);
    }

    @Override
    public Users getObject(String id) throws Exception {
        return session.get(Users.class,id);
    }

    @Override
    public String generateID() throws Exception {

        String sqlQuery="FROM Users ORDER BY id DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(1);
        Users users = (Users) query.uniqueResult();

        if (users != null){
            String lastID=users.getId();
            int newUserID=Integer.parseInt(lastID.replace("U-",""))+1;
            return String.format("U-%03d",newUserID);
        }
        return "U-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public Users getUsers(String userName) {
        String sql="from Users where userName LIKE :UN";
        Query query = session.createQuery(sql);
        query.setParameter("UN",userName);
        Users users = (Users) query.uniqueResult();

        return users;
    }


}
