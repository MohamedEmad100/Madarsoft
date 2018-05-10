package com.m.emad.madarsoft.data.source.database.DAO;

import com.m.emad.madarsoft.base.BaseDAO;
import com.m.emad.madarsoft.data.model.Coord;

import javax.inject.Inject;

public class CoordDAO extends BaseDAO<Coord> {

    @Inject
    public CoordDAO() { super(Coord.class); }
}
