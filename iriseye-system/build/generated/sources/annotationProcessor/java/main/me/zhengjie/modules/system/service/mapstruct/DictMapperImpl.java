package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Dict;
import me.zhengjie.modules.system.domain.DictDetail;
import me.zhengjie.modules.system.service.dto.DictDetailDto;
import me.zhengjie.modules.system.service.dto.DictDto;
import me.zhengjie.modules.system.service.dto.DictSmallDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DictMapperImpl implements DictMapper {

    @Override
    public Dict toEntity(DictDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Dict dict = new Dict();

        dict.setCreateBy( arg0.getCreateBy() );
        dict.setUpdateBy( arg0.getUpdateBy() );
        dict.setCreateTime( arg0.getCreateTime() );
        dict.setUpdateTime( arg0.getUpdateTime() );
        dict.setId( arg0.getId() );
        dict.setDictDetails( dictDetailDtoListToDictDetailList( arg0.getDictDetails() ) );
        dict.setName( arg0.getName() );
        dict.setDescription( arg0.getDescription() );

        return dict;
    }

    @Override
    public DictDto toDto(Dict arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DictDto dictDto = new DictDto();

        dictDto.setCreateBy( arg0.getCreateBy() );
        dictDto.setUpdateBy( arg0.getUpdateBy() );
        dictDto.setCreateTime( arg0.getCreateTime() );
        dictDto.setUpdateTime( arg0.getUpdateTime() );
        dictDto.setId( arg0.getId() );
        dictDto.setDictDetails( dictDetailListToDictDetailDtoList( arg0.getDictDetails() ) );
        dictDto.setName( arg0.getName() );
        dictDto.setDescription( arg0.getDescription() );

        return dictDto;
    }

    @Override
    public List<Dict> toEntity(List<DictDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Dict> list = new ArrayList<Dict>( arg0.size() );
        for ( DictDto dictDto : arg0 ) {
            list.add( toEntity( dictDto ) );
        }

        return list;
    }

    @Override
    public List<DictDto> toDto(List<Dict> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DictDto> list = new ArrayList<DictDto>( arg0.size() );
        for ( Dict dict : arg0 ) {
            list.add( toDto( dict ) );
        }

        return list;
    }

    protected Dict dictSmallDtoToDict(DictSmallDto dictSmallDto) {
        if ( dictSmallDto == null ) {
            return null;
        }

        Dict dict = new Dict();

        dict.setId( dictSmallDto.getId() );

        return dict;
    }

    protected DictDetail dictDetailDtoToDictDetail(DictDetailDto dictDetailDto) {
        if ( dictDetailDto == null ) {
            return null;
        }

        DictDetail dictDetail = new DictDetail();

        dictDetail.setCreateBy( dictDetailDto.getCreateBy() );
        dictDetail.setUpdateBy( dictDetailDto.getUpdateBy() );
        dictDetail.setCreateTime( dictDetailDto.getCreateTime() );
        dictDetail.setUpdateTime( dictDetailDto.getUpdateTime() );
        dictDetail.setId( dictDetailDto.getId() );
        dictDetail.setDict( dictSmallDtoToDict( dictDetailDto.getDict() ) );
        dictDetail.setLabel( dictDetailDto.getLabel() );
        dictDetail.setValue( dictDetailDto.getValue() );
        dictDetail.setDictSort( dictDetailDto.getDictSort() );

        return dictDetail;
    }

    protected List<DictDetail> dictDetailDtoListToDictDetailList(List<DictDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<DictDetail> list1 = new ArrayList<DictDetail>( list.size() );
        for ( DictDetailDto dictDetailDto : list ) {
            list1.add( dictDetailDtoToDictDetail( dictDetailDto ) );
        }

        return list1;
    }

    protected DictSmallDto dictToDictSmallDto(Dict dict) {
        if ( dict == null ) {
            return null;
        }

        DictSmallDto dictSmallDto = new DictSmallDto();

        dictSmallDto.setId( dict.getId() );

        return dictSmallDto;
    }

    protected DictDetailDto dictDetailToDictDetailDto(DictDetail dictDetail) {
        if ( dictDetail == null ) {
            return null;
        }

        DictDetailDto dictDetailDto = new DictDetailDto();

        dictDetailDto.setCreateBy( dictDetail.getCreateBy() );
        dictDetailDto.setUpdateBy( dictDetail.getUpdateBy() );
        dictDetailDto.setCreateTime( dictDetail.getCreateTime() );
        dictDetailDto.setUpdateTime( dictDetail.getUpdateTime() );
        dictDetailDto.setId( dictDetail.getId() );
        dictDetailDto.setDict( dictToDictSmallDto( dictDetail.getDict() ) );
        dictDetailDto.setLabel( dictDetail.getLabel() );
        dictDetailDto.setValue( dictDetail.getValue() );
        dictDetailDto.setDictSort( dictDetail.getDictSort() );

        return dictDetailDto;
    }

    protected List<DictDetailDto> dictDetailListToDictDetailDtoList(List<DictDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<DictDetailDto> list1 = new ArrayList<DictDetailDto>( list.size() );
        for ( DictDetail dictDetail : list ) {
            list1.add( dictDetailToDictDetailDto( dictDetail ) );
        }

        return list1;
    }
}
