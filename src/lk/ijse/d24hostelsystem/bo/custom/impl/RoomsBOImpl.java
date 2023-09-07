package lk.ijse.d24hostelsystem.bo.custom.impl;


import lk.ijse.d24hostelsystem.bo.custom.RoomsBO;
import lk.ijse.d24hostelsystem.dao.DAOFactory;
import lk.ijse.d24hostelsystem.dao.custom.RoomsDAO;
import lk.ijse.d24hostelsystem.dto.RoomDTO;
import lk.ijse.d24hostelsystem.entity.Room;
import lk.ijse.d24hostelsystem.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomsBOImpl implements RoomsBO {
    private Session session;
    RoomsDAO roomsDAO = (RoomsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Rooms);
    @Override
    public List<RoomDTO> loadAll() throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        roomsDAO.setSession(session);
        List<Room> rooms = roomsDAO.loadAll();
        List<RoomDTO> roomDTOS=new ArrayList<>();

        for(Room room: rooms){
            roomDTOS.add(
                    new RoomDTO(
                            room.getId(),
                            room.getType(),
                            room.getKeyMoney(),
                            room.getQty()
                    )
            );
        }

        return roomDTOS;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomsDAO.setSession(session);
            roomsDAO.save(
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
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
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
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomsDAO.setSession(session);
            roomsDAO.delete(
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
    public String generateNextRoomID() throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        roomsDAO.setSession(session);
        return roomsDAO.generateID();
    }
}
