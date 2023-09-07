package lk.ijse.d24hostelsystem.util;

import lk.ijse.d24hostelsystem.entity.Reservation;
import lk.ijse.d24hostelsystem.entity.Room;
import lk.ijse.d24hostelsystem.entity.Student;
import lk.ijse.d24hostelsystem.entity.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;
    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();
    }
    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }
    public Session getSession() throws HibernateException {
        // Opens a new Session through the Session Factory & returns the Session created
        return sessionFactory.openSession();
    }
}
