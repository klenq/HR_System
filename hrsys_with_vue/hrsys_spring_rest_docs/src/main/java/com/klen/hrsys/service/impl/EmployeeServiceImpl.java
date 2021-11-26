package com.klen.hrsys.service.impl;

import com.klen.hrsys.dao.EmployeeDao;
import com.klen.hrsys.entity.Employee;
import com.klen.hrsys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Jianyu Qiu (Kalen)
 * @CreateTime: 2021/11/16
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao empDao;

    @Override
    public List<Employee> search(Employee condition) {
        List<Employee> list = null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(condition==null){
                    return null;
                }
                if (condition.getNumber() != 0) {
                    Predicate predicate = criteriaBuilder.equal(root.get("number").as(Integer.class), condition.getNumber());
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getGender())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("gender").as(String.class), condition.getGender());
                    predicates.add(predicate);
                }
                if (condition.getAge() != 0) {
                    Predicate predicate = criteriaBuilder.equal(root.get("age").as(Integer.class), condition.getAge());
                    predicates.add(predicate);
                }
                if (condition.getDep() != null && condition.getDep().getId() != 0) {
                    Predicate predicate = criteriaBuilder.equal(root.get("dep").get("id").as(Integer.class), condition.getDep().getId());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };
        list = empDao.findAll(specification);
        return list;
    }

    @Override
    public Employee searchById(Integer id) {
        Employee emp = empDao.findById(id).get();
        return emp;
    }

    @Override
    public boolean add(Employee emp) {
        Employee newEmp = empDao.save(emp);
        return newEmp != null;
    }

    @Override
    public boolean update(Employee emp) {
        Employee newEmp = empDao.save(emp);
        return newEmp != null;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            empDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

