package lk.ijse.d24hostelsystem.dao.custom.impl;

import lk.ijse.d24hostelsystem.dao.custom.StudentDAO;
import lk.ijse.d24hostelsystem.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    @Override
    public List<Student> loadAll() throws Exception {
        String sqlQuery="From Student";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Student student) throws Exception {
        return (String) session.save(student);
    }

    @Override
    public void update(Student student) throws Exception {
        session.update(student);
    }

    @Override
    public void delete(Student student) throws Exception {
        session.delete(student);
    }

    @Override
    public Student getObject(String id) throws Exception {
        return session.get(Student.class,id);
    }

    @Override
    public String generateID() throws Exception {
        String sqlQuery="FROM Student ORDER BY id DESC";
        Student student = (Student) session.createQuery(sqlQuery).setMaxResults(1).uniqueResult();
        session.close();

        if (student != null){
            String lastID=student.getStudentId();
            int newStudentID=Integer.parseInt(lastID.replace("STU-",""))+1;
            return String.format("STU-%03d",newStudentID);
        }
        return "STU-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public List<String> getIds() throws Exception {
        String sqlQuery="SELECT id FROM Student ";
        Query query = session.createQuery(sqlQuery);
        List<String> list = query.list();
        session.close();
        System.out.println(list);
        return list;
    }
}
