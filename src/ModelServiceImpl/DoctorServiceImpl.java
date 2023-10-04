package ModelServiceImpl;

import DaoService.DoctorDao;
import Database.Dao;
import Model.Doctor;
import ModelService.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private Dao dao;
    public DoctorDao doctorDao;
    public DoctorServiceImpl(Dao dao, DoctorDao doctorDao) {
        this.dao = dao;
        this.doctorDao = doctorDao;
    }

    @Override
    public String addDoctorToHospital(Long id, Doctor doctor) {
        return doctorDao.addDoctorToHospital(id,doctor);
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorDao.findDoctorById(id);
    }

    @Override
    public String updateDoctor(Long id, Doctor doctor) {
        return doctorDao.updateDoctor(id,doctor);
    }

    @Override
    public void deleteDoctorById(Long id) {
        doctorDao.deleteDoctorById(id);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return doctorDao.assignDoctorToDepartment(departmentId,doctorsId);
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return doctorDao.getAllDoctorsByHospitalId(id);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return doctorDao.getAllDoctorsByDepartmentId(id);
    }
}
