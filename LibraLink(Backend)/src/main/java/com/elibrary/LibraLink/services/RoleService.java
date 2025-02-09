package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Role;
import com.elibrary.LibraLink.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    // CONSTANT VALUES
    private final RoleRepository roleRepository;

    // CONSTRUCTOR
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    // CREATE ROLE
    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    // GET ROLE BY ID
    public Optional<Role> findRoleById(Integer id){
        return roleRepository.findById(id);
    }

    // GET All ROLES
    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    //UPDATE ROLE BY ID
    public Role updateRole(Role role){
        Optional<Role> originalRole = roleRepository.findById(role.getId());

        if(originalRole.isPresent()){
            Role roleForUpdate = originalRole.get();
            roleForUpdate.setRole_name(role.getRole_name());

            return roleRepository.save(roleForUpdate);
        }else {
            throw new Error("Role Not Found With Id "+role.getId());
        }
    }

    // DELETE ROLE (SOFT)
    public void softDeleteRole(Integer id){
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            Role roleForDle = role.get();
            roleForDle.setStatus(false);

            roleRepository.save(roleForDle);
        }else{
            throw new Error("Role Not Found With Id "+id);
        }
    }

}
