package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.service.dto.DeptDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DeptMapperImpl implements DeptMapper {

    @Override
    public Dept toEntity(DeptDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Dept dept = new Dept();

        dept.setCreateBy( arg0.getCreateBy() );
        dept.setUpdateBy( arg0.getUpdateBy() );
        dept.setCreateTime( arg0.getCreateTime() );
        dept.setUpdateTime( arg0.getUpdateTime() );
        dept.setId( arg0.getId() );
        dept.setDeptSort( arg0.getDeptSort() );
        dept.setName( arg0.getName() );
        dept.setEnabled( arg0.getEnabled() );
        dept.setPid( arg0.getPid() );
        dept.setSubCount( arg0.getSubCount() );

        return dept;
    }

    @Override
    public DeptDto toDto(Dept arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DeptDto deptDto = new DeptDto();

        deptDto.setCreateBy( arg0.getCreateBy() );
        deptDto.setUpdateBy( arg0.getUpdateBy() );
        deptDto.setCreateTime( arg0.getCreateTime() );
        deptDto.setUpdateTime( arg0.getUpdateTime() );
        deptDto.setId( arg0.getId() );
        deptDto.setName( arg0.getName() );
        deptDto.setEnabled( arg0.getEnabled() );
        deptDto.setDeptSort( arg0.getDeptSort() );
        deptDto.setPid( arg0.getPid() );
        deptDto.setSubCount( arg0.getSubCount() );

        return deptDto;
    }

    @Override
    public List<Dept> toEntity(List<DeptDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Dept> list = new ArrayList<Dept>( arg0.size() );
        for ( DeptDto deptDto : arg0 ) {
            list.add( toEntity( deptDto ) );
        }

        return list;
    }

    @Override
    public List<DeptDto> toDto(List<Dept> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DeptDto> list = new ArrayList<DeptDto>( arg0.size() );
        for ( Dept dept : arg0 ) {
            list.add( toDto( dept ) );
        }

        return list;
    }
}
