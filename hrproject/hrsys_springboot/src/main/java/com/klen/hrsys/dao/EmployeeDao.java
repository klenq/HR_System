package com.klen.hrsys.dao;

import com.klen.hrsys.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Jianyu Qiu (Kalen)
 * @CreateTime: 2021/11/16
 */
@Repository
public interface EmployeeDao {
    List<Employee> search(Employee condition);
    Employee searchById(int id);
    int add(Employee emp);
    int update(Employee emp);
    int delete(int id);
    int updateByDep(int depId);
}
