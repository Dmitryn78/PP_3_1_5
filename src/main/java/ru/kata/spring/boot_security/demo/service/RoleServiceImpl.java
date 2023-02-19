package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleDAO roleDAO;

    @PersistenceContext
    private EntityManager entityManager;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
//        Query query = entityManager.createQuery("select role from Role role where role.name = :name", Role.class);
//        query.setParameter("name", name);
//        return (Role) query.getSingleResult();
        return roleDAO.getRoleByName(name);
    }

}
