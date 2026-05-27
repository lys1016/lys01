package org.example.authrole.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.authrole.dto.AuthRoleDTO;
import org.example.authrole.entity.Role;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
