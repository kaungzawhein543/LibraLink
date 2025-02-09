package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.Category;
import com.elibrary.LibraLink.entities.Permission;
import com.elibrary.LibraLink.repositories.PermissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    // CONSTANT VALUES
    private final PermissionRepository permissionRepository;

    // CONSTRUCTOR
    public PermissionService(PermissionRepository permissionRepository){
        this.permissionRepository = permissionRepository;
    }

    //CREATE PERMISSION
    public Permission addPermission(Permission permission){
        return permissionRepository.save(permission);
    }

    // GET PERMISSION By ID
    public Optional<Permission> findPermissionById(Integer id){
        return permissionRepository.findById(id);
    }

    // GET ALL PERMISSIONS
    public List<Permission> findAllPermissions(){
        return permissionRepository.findAll();
    }

    // UPDATE PERMISSION BY ID
    public Permission updatePermission(Permission permission){
        Optional<Permission> originalPermission = permissionRepository.findById(permission.getId());

        if(originalPermission.isPresent()){
            Permission permissionForUpdate = originalPermission.get();
            permissionForUpdate.setPermission_name(permission.getPermission_name());
            permissionForUpdate.setDescription(permission.getDescription());
            permissionForUpdate.setUpdated_at(LocalDateTime.now());
            return permissionRepository.save(permissionForUpdate);
        }else {
            throw new Error("Permission Not Found With Id "+permission.getId());
        }
    }

    // DELETE PERMISSION (SOFT)
    public void softDeletePermission(Integer id){
        Optional<Permission> permission = permissionRepository.findById(id);
        if(permission.isPresent()){
            Permission permissionForDle = permission.get();
            permissionForDle.setStatus(false);

            permissionRepository.save(permissionForDle);
        }else{
            throw new Error("Permission Not Found With Id "+id);
        }
    }

}
