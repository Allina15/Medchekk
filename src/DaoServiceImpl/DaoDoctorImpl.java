package DaoServiceImpl;

import DaoService.DoctorDao;
import Database.Dao;
import Model.Department;
import Model.Doctor;
import Model.Hospital;

import java.util.List;
import java.util.Optional;

public class DaoDoctorImpl implements DoctorDao {
    private Dao dao;

    public DaoDoctorImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public String addDoctorToHospital(Long id, Doctor doctor) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            if (hospital.getInstanceId()==(id)) {
                hospital.getDoctors().add(doctor);
                return "Доктор успешно добавлен";
            }
        }

        return null;
    }


    @Override
    public Doctor findDoctorById(Long id) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            List<Doctor> doctors = hospital.getDoctors();
            for (Doctor doctor : doctors) {
                if (doctor.getInstanceId()==(id)) {
                    return doctor;
                }
            }
        }

        return null;
    }


    @Override
    public String updateDoctor(Long id, Doctor doctor) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            List<Doctor> doctors = hospital.getDoctors();
            for (int i = 0; i < doctors.size(); i++) {
                if (doctors.get(i).getInstanceId()==(id)) {
                    doctors.set(i, doctor); // Заменяем старого доктора на нового
                    return "Доктор успешно обновлен";
                }
            }
        }

        return null;
    }


    @Override
    public void deleteDoctorById(Long id) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            List<Doctor> doctors = hospital.getDoctors();
            doctors.removeIf(doctor -> doctor.getInstanceId()==(id));
        }
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        Optional<Department> departmentOptional = dao.getHospitals()
                .stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getInstanceId()==(departmentId))
                .findFirst();

        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            for (Long doctorId : doctorsId) {
                Optional<Doctor> doctorOptional = dao.getHospitals()
                        .stream()
                        .flatMap(hospital -> hospital.getDoctors().stream())
                        .filter(doctor -> doctor.getInstanceId()==(doctorId))
                        .findFirst();

                if (doctorOptional.isPresent()) {
                    Doctor doctor = doctorOptional.get();
                    department.addDoctor(doctor);
                } else {
                    return "Доктор с идентификатором " + doctorId + " не найден.";
                }
            }
            return "Докторы успешно добавлены в департмент.";
        } else {
            return "Департмент с указанным идентификатором не найден.";
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return dao.getHospitals().stream()
                .filter(hospital -> hospital.getInstanceId()==(id))
                .flatMap(hospital -> hospital.getDoctors().stream())
                .toList();
    }


    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return dao.getHospitals().stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getInstanceId()==(id))
                .flatMap(department -> department.getDoctors().stream())
                .toList();
    }
}
