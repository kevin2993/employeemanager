package SCJ.family.employeemanager.service;

import SCJ.family.employeemanager.Dao.EmployeeDao;
import SCJ.family.employeemanager.exception.UserNotFoundException;
import SCJ.family.employeemanager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao){
        this.employeeDao=employeeDao;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeDao.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeDao.findAll();
    }

    public Employee findEmployeeById(Long id){
        return employeeDao.findEmployeeById(id).orElseThrow(()->new UserNotFoundException("User by id"+id+"was not found"));
    }

    public void deleteEmployee(Long id){

        employeeDao.deleteEmployeeById(id);
    }
}
