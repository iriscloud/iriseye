package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.service.dto.RoleSmallDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class RoleSmallMapperImpl implements RoleSmallMapper {

    @Override
    public Role toEntity(RoleSmallDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( arg0.getId() );
        role.setName( arg0.getName() );
        role.setDataScope( arg0.getDataScope() );
        role.setLevel( arg0.getLevel() );

        return role;
    }

    @Override
    public RoleSmallDto toDto(Role arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RoleSmallDto roleSmallDto = new RoleSmallDto();

        roleSmallDto.setId( arg0.getId() );
        roleSmallDto.setName( arg0.getName() );
        roleSmallDto.setLevel( arg0.getLevel() );
        roleSmallDto.setDataScope( arg0.getDataScope() );

        return roleSmallDto;
    }

    @Override
    public List<Role> toEntity(List<RoleSmallDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( arg0.size() );
        for ( RoleSmallDto roleSmallDto : arg0 ) {
            list.add( toEntity( roleSmallDto ) );
        }

        return list;
    }

    @Override
    public List<RoleSmallDto> toDto(List<Role> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RoleSmallDto> list = new ArrayList<RoleSmallDto>( arg0.size() );
        for ( Role role : arg0 ) {
            list.add( toDto( role ) );
        }

        return list;
    }
}
