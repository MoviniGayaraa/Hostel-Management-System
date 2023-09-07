package lk.ijse.d24hostelsystem.bo.custom.impl;

import lk.ijse.d24hostelsystem.bo.custom.ReservationBO;
import lk.ijse.d24hostelsystem.dao.DAOFactory;
import lk.ijse.d24hostelsystem.dao.custom.QueryDAO;
import lk.ijse.d24hostelsystem.dao.custom.ReservationDAO;
import lk.ijse.d24hostelsystem.dao.custom.RoomsDAO;
import lk.ijse.d24hostelsystem.dao.custom.StudentDAO;
import lk.ijse.d24hostelsystem.dto.ReservationDTO;
import lk.ijse.d24hostelsystem.dto.RoomDTO;
import lk.ijse.d24hostelsystem.dto.StudentDTO;
import lk.ijse.d24hostelsystem.entity.Reservation;
import lk.ijse.d24hostelsystem.entity.Room;
import lk.ijse.d24hostelsystem.entity.Student;
import lk.ijse.d24hostelsystem.projection.StudentDetailsDTO;
import lk.ijse.d24hostelsystem.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    private Session session;

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    RoomsDAO roomsDAO = (RoomsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Rooms);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Reservation);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Query);
    @Override
    public List<ReservationDTO> loadAll() throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        reservationDAO.setSession(session);

        try {
            List<Reservation> reservations = reservationDAO.loadAll();
            List<ReservationDTO> reservationDTOS=new ArrayList<>();

            for (Reservation reservation:reservations) {
                reservationDTOS.add(
                        new ReservationDTO(
                                reservation.getResID(),
                                reservation.getDate(),
                                new StudentDTO(
                                        reservation.getStudent().getStudentId(),
                                        reservation.getStudent().getStudentName(),
                                        reservation.getStudent().getNic(),
                                        reservation.getStudent().getHomeTown(),
                                        reservation.getStudent().getPhoneNumber(),
                                        reservation.getStudent().getGender(),
                                        reservation.getStudent().getUniversity()
                                ),
                                new RoomDTO(
                                        reservation.getRoom().getId(),
                                        reservation.getRoom().getType(),
                                        reservation.getRoom().getKeyMoney(),
                                        reservation.getRoom().getQty()
                                ),
                                reservation.getStatus()
                        )
                );
            }

            return reservationDTOS;
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            reservationDAO.setSession(session);

            reservationDAO.save(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            new Student(
                                    reservationDTO.getStudent().getStudentId(),
                                    reservationDTO.getStudent().getStudentName(),
                                    reservationDTO.getStudent().getNic(),
                                    reservationDTO.getStudent().getHomeTown(),
                                    reservationDTO.getStudent().getGender(),
                                    reservationDTO.getStudent().getPhoneNumber(),
                                    reservationDTO.getStudent().getUniversity()
                            ),
                            new Room(
                                    reservationDTO.getRoom().getId(),
                                    reservationDTO.getRoom().getType(),
                                    reservationDTO.getRoom().getKeyMoney(),
                                    reservationDTO.getRoom().getQty()
                            ),
                            reservationDTO.getStatus()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            reservationDAO.setSession(session);
            reservationDAO.update(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            new Student(
                                    reservationDTO.getStudent().getStudentId(),
                                    reservationDTO.getStudent().getStudentName(),
                                    reservationDTO.getStudent().getNic(),
                                    reservationDTO.getStudent().getHomeTown(),
                                    reservationDTO.getStudent().getGender(),
                                    reservationDTO.getStudent().getPhoneNumber(),
                                    reservationDTO.getStudent().getUniversity()
                            ),
                            new Room(
                                    reservationDTO.getRoom().getId(),
                                    reservationDTO.getRoom().getType(),
                                    reservationDTO.getRoom().getKeyMoney(),
                                    reservationDTO.getRoom().getQty()
                            ),
                            reservationDTO.getStatus()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public StudentDTO getStudent(String id) {
        session= SessionFactoryConfig.getInstance().getSession();

        try {
            studentDAO.setSession(session);
            Student student = studentDAO.getObject(id);
            session.close();
            return new StudentDTO(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getNic(),
                    student.getHomeTown(),
                    student.getGender(),
                    student.getPhoneNumber(),
                    student.getUniversity()
            );
        } catch (Exception ex){
            session.close();
            return null;
        }
    }

    @Override
    public RoomDTO getRoom(String id) {
        session= SessionFactoryConfig.getInstance().getSession();

        try {
            roomsDAO.setSession(session);
            Room room = roomsDAO.getObject(id);
            session.close();
            return new RoomDTO(
                    room.getId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            );

        } catch (Exception ex){
            session.close();
            return null;
        }
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            reservationDAO.setSession(session);
            reservationDAO.delete(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            new Student(
                                    reservationDTO.getStudent().getStudentId(),
                                    reservationDTO.getStudent().getStudentName(),
                                    reservationDTO.getStudent().getNic(),
                                    reservationDTO.getStudent().getHomeTown(),
                                    reservationDTO.getStudent().getGender(),
                                    reservationDTO.getStudent().getPhoneNumber(),
                                    reservationDTO.getStudent().getUniversity()
                            ),
                            new Room(
                                    reservationDTO.getRoom().getId(),
                                    reservationDTO.getRoom().getType(),
                                    reservationDTO.getRoom().getKeyMoney(),
                                    reservationDTO.getRoom().getQty()
                            ),
                            reservationDTO.getStatus()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }

        return false;
    }

    @Override
    public String generateNextReservationID(){
        try{
            session= SessionFactoryConfig.getInstance().getSession();
            reservationDAO.setSession(session);
            return reservationDAO.generateID();
        }catch (Exception e){
            session.close();
        }
        return null;
    }

    @Override
    public List<String> getStudentIds() {
        try{
            session= SessionFactoryConfig.getInstance().getSession();
            studentDAO.setSession(session);
            return studentDAO.getIds();
        }catch (Exception e) {
            session.close();
            return null;
        }
    }

    @Override
    public List<String> getRoomIds() {
        try{
            session= SessionFactoryConfig.getInstance().getSession();
            roomsDAO.setSession(session);
            return roomsDAO.getIds();
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean updateRoomQty(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomsDAO.setSession(session);
            roomsDAO.update(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public List<StudentDetailsDTO> getUnpaidStudents() {
        return queryDAO.getUnpaidStudents();
    }

    @Override
    public boolean changePaidStatus(String id, String status) {
        boolean isUpdated = false;
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            isUpdated = reservationDAO.changePaidStatus(id,status);
            transaction.commit();
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }


        return isUpdated;
    }
}
