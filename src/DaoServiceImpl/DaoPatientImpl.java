package DaoServiceImpl;

import DaoService.PatientDao;
import Database.Dao;

public class DaoPatientImpl implements PatientDao {
    private Dao dao;

    public DaoPatientImpl(Dao dao) {
        this.dao = dao;
    }
}
