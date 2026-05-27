package org.example.authrole.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.authrole.dto.AuthRoleDTO;
import org.example.authrole.entity.Role;
import org.example.authrole.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/role")
public class AuthController {

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping("/authRole")
        public String authRole(@RequestBody AuthRoleDTO authRoleDto){
        log.info("角色ID{}",authRoleDto.getRoleId());
        log.info("权限ID{}",authRoleDto.getPermissionIds());
        roleMapper.authRole(authRoleDto);
            return "";
        }




}

