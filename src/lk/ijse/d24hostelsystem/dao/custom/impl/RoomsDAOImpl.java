package lk.ijse.d24hostelsystem.dao.custom.impl;


import lk.ijse.d24hostelsystem.dao.custom.RoomsDAO;
import lk.ijse.d24hostelsystem.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomsDAOImpl implements RoomsDAO {
    private Session session;
    @Override
    public List<Room> loadAll() throws Exception {
        String sqlQuery="From Room ";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Room room) throws Exception {
        return (String) session.save(room);
    }

    @Override
    public void update(Room room) throws Exception {
        session.update(room);
    }

    @Override
    public void delete(Room room) throws Exception {
        session.delete(room);
    }

    @Override
    public Room getObject(String id) throws Exception {
        return session.get(Room.class,id);
    }

    @Override
    public String generateID() throws Exception {
        String sqlQuery="FROM Room ORDER BY id DESC";
        Room room = (Room) session.createQuery(sqlQuery).setMaxResults(1).uniqueResult();
        session.close();

        if (room != null){
            String lastID=room.getId();
            int newStudentID=Integer.parseInt(lastID.replace("RM-",""))+1;
            return String.format("RM-%03d",newStudentID);
        }
        return "RM-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public List<String> getIds() throws Exception {
        String hql = "SELECT id from Room ";
        Query<String> query = session.createQuery(hql);
        List<String> results = query.list();
        session.close();
        return results;
    }
}
