package DaoServiceImpl;

import DaoService.DepartmentDao;
import Database.Dao;
import Model.Department;
import Model.Hospital;

import java.util.List;
import java.util.Optional;

public class DaoDepartmentImpl implements DepartmentDao {

    private Dao dao;

    public DaoDepartmentImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public String addDepartmentToHospital(Long id, Department department) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            if (hospital.getInstanceId()==(id)) {
                hospital.getDepartments().add(department);
                return "Отдел успешно добавлен!";
            }
        }

        return "Госпиталь с указанным ID не найден.";
    }


    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            if (hospital.getInstanceId()==(id)) {
                return hospital.getDepartments();
            }
        }
        return null;
    }


    @Override
    public Department findDepartmentByName(String name) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            Optional<Department> department = hospital.getDepartments()
                    .stream()
                    .filter(dept -> dept.getDepartmentName().equalsIgnoreCase(name))
                    .findFirst();

            if (department.isPresent()) {
                return department.get();
            }
        }

        return null;
    }


    @Override
    public void deleteDepartmentById(Long id) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            List<Department> departments = hospital.getDepartments();
            departments.removeIf(depart -> depart.getInstanceId()==(id));
        }

    }
    @Override
    public String updateDepartmentById(Long id, Department department) {
        List<Hospital> hospitals = dao.getHospitals();

        for (Hospital hospital : hospitals) {
            List<Department> departments = hospital.getDepartments();

            for (int i = 0; i < departments.size(); i++) {
                if (departments.get(i).getInstanceId()==(id)) {
                    departments.set(i, department);
                    return "Успешно обновлено!";
                }
            }
        }

        return null;
    }

}
