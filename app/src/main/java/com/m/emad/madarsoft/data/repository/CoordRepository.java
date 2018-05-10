package com.m.emad.madarsoft.data.repository;

import com.m.emad.madarsoft.data.model.Coord;
import com.m.emad.madarsoft.data.source.database.DAO.CoordDAO;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class CoordRepository {

    CoordDAO coordDAO;

    @Inject
    public CoordRepository(CoordDAO coordDAO) {
        this.coordDAO = coordDAO;
    }

    public void saveCoord(Coord coord) throws SQLException {
        coordDAO.create(coord);
    }

    public void saveAll(List<Coord> coord) throws SQLException {
        coordDAO.createAll(coord);
    }


    public List<Coord> getAllCoord() throws SQLException{
        List<Coord> list = coordDAO.getAll();
        if (list != null && !list.isEmpty()){
            return list;
        }
        return null;
    }

    public Coord getCoord(String id)throws SQLException{
        return coordDAO.getOne(id);
    }

    public void clearCoord () throws SQLException{
        coordDAO.deleteAll();
    }

    public void deleteCoord(Coord coord)throws SQLException{
        coordDAO.deleteOne(coord);
    }


}
