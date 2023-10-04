package ModelServiceImpl;

import DaoService.DepartmentDao;
import DaoService.PatientDao;
import Database.Dao;
import ModelService.PatientService;

public class PatientServImpl implements PatientService {
    private Dao dao;
    public PatientDao patientDao;

    public PatientServImpl(Dao dao, PatientDao patientDao) {
        this.dao = dao;
        this.patientDao = patientDao;
    }
}
