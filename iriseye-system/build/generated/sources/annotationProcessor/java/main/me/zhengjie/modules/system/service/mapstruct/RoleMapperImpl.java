package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.Menu;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.service.dto.DeptDto;
import me.zhengjie.modules.system.service.dto.MenuDto;
import me.zhengjie.modules.system.service.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Role toEntity(RoleDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Role role = new Role();

        role.setCreateBy( arg0.getCreateBy() );
        role.setUpdateBy( arg0.getUpdateBy() );
        role.setCreateTime( arg0.getCreateTime() );
        role.setUpdateTime( arg0.getUpdateTime() );
        role.setId( arg0.getId() );
        role.setMenus( menuDtoSetToMenuSet( arg0.getMenus() ) );
        role.setDepts( deptDtoSetToDeptSet( arg0.getDepts() ) );
        role.setName( arg0.getName() );
        role.setDataScope( arg0.getDataScope() );
        role.setLevel( arg0.getLevel() );
        role.setDescription( arg0.getDescription() );

        return role;
    }

    @Override
    public RoleDto toDto(Role arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setCreateBy( arg0.getCreateBy() );
        roleDto.setUpdateBy( arg0.getUpdateBy() );
        roleDto.setCreateTime( arg0.getCreateTime() );
        roleDto.setUpdateTime( arg0.getUpdateTime() );
        roleDto.setId( arg0.getId() );
        roleDto.setMenus( menuSetToMenuDtoSet( arg0.getMenus() ) );
        roleDto.setDepts( deptSetToDeptDtoSet( arg0.getDepts() ) );
        roleDto.setName( arg0.getName() );
        roleDto.setDataScope( arg0.getDataScope() );
        roleDto.setLevel( arg0.getLevel() );
        roleDto.setDescription( arg0.getDescription() );

        return roleDto;
    }

    @Override
    public List<Role> toEntity(List<RoleDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( arg0.size() );
        for ( RoleDto roleDto : arg0 ) {
            list.add( toEntity( roleDto ) );
        }

        return list;
    }

    @Override
    public List<RoleDto> toDto(List<Role> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( arg0.size() );
        for ( Role role : arg0 ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    protected Set<Menu> menuDtoSetToMenuSet(Set<MenuDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Menu> set1 = new HashSet<Menu>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MenuDto menuDto : set ) {
            set1.add( menuMapper.toEntity( menuDto ) );
        }

        return set1;
    }

    protected Set<Dept> deptDtoSetToDeptSet(Set<DeptDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Dept> set1 = new HashSet<Dept>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( DeptDto deptDto : set ) {
            set1.add( deptMapper.toEntity( deptDto ) );
        }

        return set1;
    }

    protected Set<MenuDto> menuSetToMenuDtoSet(Set<Menu> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuDto> set1 = new HashSet<MenuDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Menu menu : set ) {
            set1.add( menuMapper.toDto( menu ) );
        }

        return set1;
    }

    protected Set<DeptDto> deptSetToDeptDtoSet(Set<Dept> set) {
        if ( set == null ) {
            return null;
        }

        Set<DeptDto> set1 = new HashSet<DeptDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Dept dept : set ) {
            set1.add( deptMapper.toDto( dept ) );
        }

        return set1;
    }
}
