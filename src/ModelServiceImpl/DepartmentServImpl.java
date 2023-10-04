package ModelServiceImpl;

import DaoService.DepartmentDao;
import Database.Dao;
import Model.Department;
import ModelService.DepartmentService;

import java.util.List;

public class DepartmentServImpl implements DepartmentService {
    private Dao dao;
    public DepartmentDao departmentDao;

    public DepartmentServImpl(Dao dao, DepartmentDao departmentDao) {
        this.dao = dao;
        this.departmentDao = departmentDao;
    }

    @Override
    public String addDepartmentToHospital(Long id, Department department) {
        return departmentDao.addDepartmentToHospital(id,department);
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return departmentDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentDao.deleteDepartmentById(id);
    }

    @Override
    public String updateDepartmentById(Long id, Department department) {
        return departmentDao.updateDepartmentById(id,department);
    }
}
