package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Dict;
import me.zhengjie.modules.system.service.dto.DictSmallDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:57+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DictSmallMapperImpl implements DictSmallMapper {

    @Override
    public Dict toEntity(DictSmallDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Dict dict = new Dict();

        dict.setId( arg0.getId() );

        return dict;
    }

    @Override
    public DictSmallDto toDto(Dict arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DictSmallDto dictSmallDto = new DictSmallDto();

        dictSmallDto.setId( arg0.getId() );

        return dictSmallDto;
    }

    @Override
    public List<Dict> toEntity(List<DictSmallDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Dict> list = new ArrayList<Dict>( arg0.size() );
        for ( DictSmallDto dictSmallDto : arg0 ) {
            list.add( toEntity( dictSmallDto ) );
        }

        return list;
    }

    @Override
    public List<DictSmallDto> toDto(List<Dict> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DictSmallDto> list = new ArrayList<DictSmallDto>( arg0.size() );
        for ( Dict dict : arg0 ) {
            list.add( toDto( dict ) );
        }

        return list;
    }
}
