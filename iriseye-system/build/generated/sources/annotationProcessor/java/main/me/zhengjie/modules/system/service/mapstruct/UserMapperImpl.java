package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.service.dto.DeptSmallDto;
import me.zhengjie.modules.system.service.dto.JobSmallDto;
import me.zhengjie.modules.system.service.dto.RoleSmallDto;
import me.zhengjie.modules.system.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        User user = new User();

        user.setCreateBy( arg0.getCreateBy() );
        user.setUpdateBy( arg0.getUpdateBy() );
        user.setCreateTime( arg0.getCreateTime() );
        user.setUpdateTime( arg0.getUpdateTime() );
        user.setId( arg0.getId() );
        user.setRoles( roleSmallDtoSetToRoleSet( arg0.getRoles() ) );
        user.setJobs( jobSmallDtoSetToJobSet( arg0.getJobs() ) );
        user.setDept( deptSmallDtoToDept( arg0.getDept() ) );
        user.setUsername( arg0.getUsername() );
        user.setNickName( arg0.getNickName() );
        user.setEmail( arg0.getEmail() );
        user.setPhone( arg0.getPhone() );
        user.setGender( arg0.getGender() );
        user.setAvatarName( arg0.getAvatarName() );
        user.setAvatarPath( arg0.getAvatarPath() );
        user.setPassword( arg0.getPassword() );
        user.setEnabled( arg0.getEnabled() );
        user.setIsAdmin( arg0.getIsAdmin() );
        user.setPwdResetTime( arg0.getPwdResetTime() );

        return user;
    }

    @Override
    public UserDto toDto(User arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setCreateBy( arg0.getCreateBy() );
        userDto.setUpdateBy( arg0.getUpdateBy() );
        userDto.setCreateTime( arg0.getCreateTime() );
        userDto.setUpdateTime( arg0.getUpdateTime() );
        userDto.setId( arg0.getId() );
        userDto.setRoles( roleSetToRoleSmallDtoSet( arg0.getRoles() ) );
        userDto.setJobs( jobSetToJobSmallDtoSet( arg0.getJobs() ) );
        userDto.setDept( deptToDeptSmallDto( arg0.getDept() ) );
        userDto.setUsername( arg0.getUsername() );
        userDto.setNickName( arg0.getNickName() );
        userDto.setEmail( arg0.getEmail() );
        userDto.setPhone( arg0.getPhone() );
        userDto.setGender( arg0.getGender() );
        userDto.setAvatarName( arg0.getAvatarName() );
        userDto.setAvatarPath( arg0.getAvatarPath() );
        userDto.setPassword( arg0.getPassword() );
        userDto.setEnabled( arg0.getEnabled() );
        userDto.setIsAdmin( arg0.getIsAdmin() );
        userDto.setPwdResetTime( arg0.getPwdResetTime() );

        return userDto;
    }

    @Override
    public List<User> toEntity(List<UserDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( arg0.size() );
        for ( UserDto userDto : arg0 ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<User> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( arg0.size() );
        for ( User user : arg0 ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    protected Role roleSmallDtoToRole(RoleSmallDto roleSmallDto) {
        if ( roleSmallDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleSmallDto.getId() );
        role.setName( roleSmallDto.getName() );
        role.setDataScope( roleSmallDto.getDataScope() );
        role.setLevel( roleSmallDto.getLevel() );

        return role;
    }

    protected Set<Role> roleSmallDtoSetToRoleSet(Set<RoleSmallDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleSmallDto roleSmallDto : set ) {
            set1.add( roleSmallDtoToRole( roleSmallDto ) );
        }

        return set1;
    }

    protected Job jobSmallDtoToJob(JobSmallDto jobSmallDto) {
        if ( jobSmallDto == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( jobSmallDto.getId() );
        job.setName( jobSmallDto.getName() );

        return job;
    }

    protected Set<Job> jobSmallDtoSetToJobSet(Set<JobSmallDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Job> set1 = new HashSet<Job>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( JobSmallDto jobSmallDto : set ) {
            set1.add( jobSmallDtoToJob( jobSmallDto ) );
        }

        return set1;
    }

    protected Dept deptSmallDtoToDept(DeptSmallDto deptSmallDto) {
        if ( deptSmallDto == null ) {
            return null;
        }

        Dept dept = new Dept();

        dept.setId( deptSmallDto.getId() );
        dept.setName( deptSmallDto.getName() );

        return dept;
    }

    protected RoleSmallDto roleToRoleSmallDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleSmallDto roleSmallDto = new RoleSmallDto();

        roleSmallDto.setId( role.getId() );
        roleSmallDto.setName( role.getName() );
        roleSmallDto.setLevel( role.getLevel() );
        roleSmallDto.setDataScope( role.getDataScope() );

        return roleSmallDto;
    }

    protected Set<RoleSmallDto> roleSetToRoleSmallDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleSmallDto> set1 = new HashSet<RoleSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleSmallDto( role ) );
        }

        return set1;
    }

    protected JobSmallDto jobToJobSmallDto(Job job) {
        if ( job == null ) {
            return null;
        }

        JobSmallDto jobSmallDto = new JobSmallDto();

        jobSmallDto.setId( job.getId() );
        jobSmallDto.setName( job.getName() );

        return jobSmallDto;
    }

    protected Set<JobSmallDto> jobSetToJobSmallDtoSet(Set<Job> set) {
        if ( set == null ) {
            return null;
        }

        Set<JobSmallDto> set1 = new HashSet<JobSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Job job : set ) {
            set1.add( jobToJobSmallDto( job ) );
        }

        return set1;
    }

    protected DeptSmallDto deptToDeptSmallDto(Dept dept) {
        if ( dept == null ) {
            return null;
        }

        DeptSmallDto deptSmallDto = new DeptSmallDto();

        deptSmallDto.setId( dept.getId() );
        deptSmallDto.setName( dept.getName() );

        return deptSmallDto;
    }
}
