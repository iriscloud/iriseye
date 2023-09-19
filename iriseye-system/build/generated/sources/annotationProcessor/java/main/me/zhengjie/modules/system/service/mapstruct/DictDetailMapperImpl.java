package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.DictDetail;
import me.zhengjie.modules.system.service.dto.DictDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DictDetailMapperImpl implements DictDetailMapper {

    @Autowired
    private DictSmallMapper dictSmallMapper;

    @Override
    public DictDetail toEntity(DictDetailDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DictDetail dictDetail = new DictDetail();

        dictDetail.setCreateBy( arg0.getCreateBy() );
        dictDetail.setUpdateBy( arg0.getUpdateBy() );
        dictDetail.setCreateTime( arg0.getCreateTime() );
        dictDetail.setUpdateTime( arg0.getUpdateTime() );
        dictDetail.setId( arg0.getId() );
        dictDetail.setDict( dictSmallMapper.toEntity( arg0.getDict() ) );
        dictDetail.setLabel( arg0.getLabel() );
        dictDetail.setValue( arg0.getValue() );
        dictDetail.setDictSort( arg0.getDictSort() );

        return dictDetail;
    }

    @Override
    public DictDetailDto toDto(DictDetail arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DictDetailDto dictDetailDto = new DictDetailDto();

        dictDetailDto.setCreateBy( arg0.getCreateBy() );
        dictDetailDto.setUpdateBy( arg0.getUpdateBy() );
        dictDetailDto.setCreateTime( arg0.getCreateTime() );
        dictDetailDto.setUpdateTime( arg0.getUpdateTime() );
        dictDetailDto.setId( arg0.getId() );
        dictDetailDto.setDict( dictSmallMapper.toDto( arg0.getDict() ) );
        dictDetailDto.setLabel( arg0.getLabel() );
        dictDetailDto.setValue( arg0.getValue() );
        dictDetailDto.setDictSort( arg0.getDictSort() );

        return dictDetailDto;
    }

    @Override
    public List<DictDetail> toEntity(List<DictDetailDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DictDetail> list = new ArrayList<DictDetail>( arg0.size() );
        for ( DictDetailDto dictDetailDto : arg0 ) {
            list.add( toEntity( dictDetailDto ) );
        }

        return list;
    }

    @Override
    public List<DictDetailDto> toDto(List<DictDetail> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DictDetailDto> list = new ArrayList<DictDetailDto>( arg0.size() );
        for ( DictDetail dictDetail : arg0 ) {
            list.add( toDto( dictDetail ) );
        }

        return list;
    }
}
