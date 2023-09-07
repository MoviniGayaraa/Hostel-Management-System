package lk.ijse.d24hostelsystem.dao.custom.impl;


import lk.ijse.d24hostelsystem.dao.custom.ReservationDAO;
import lk.ijse.d24hostelsystem.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private Session session;
    @Override
    public List<Reservation> loadAll() {
        try{
            String sqlQuery="From Reservation ";
            Query query = session.createQuery(sqlQuery);
            List list = query.list();
            session.close();
            return list;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public String save(Reservation reservation) {
        System.out.println(reservation);
        return (String) session.save(reservation);
    }

    @Override
    public void update(Reservation reservation) throws Exception {
        session.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) throws Exception {
        session.delete(reservation);
    }

    @Override
    public Reservation getObject(String id) throws Exception {
        return session.get(Reservation.class,id);
    }

    @Override
    public String generateID() throws Exception {
        String sqlQuery="FROM Reservation ORDER BY id DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(1);
        Reservation reservation = (Reservation) query.uniqueResult();


        if (reservation != null){
            String lastID=reservation.getResID();
            int newReserveID=Integer.parseInt(lastID.replace("REV-",""))+1;
            return String.format("REV-%03d",newReserveID);
        }
        return "REV-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public boolean changePaidStatus(String reserveId, String paidStatus) {
        String hql="update Reservation r set r.status=:sts where r.id=:rid";
        Query query = session.createQuery(hql);
        query.setParameter("sts",paidStatus);
        query.setParameter("rid",reserveId);
        int count = query.executeUpdate();
        return count >= 0;
    }
}
