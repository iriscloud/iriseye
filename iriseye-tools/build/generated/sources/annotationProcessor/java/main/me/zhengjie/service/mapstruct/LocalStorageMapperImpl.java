package me.zhengjie.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.service.dto.LocalStorageDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:26+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class LocalStorageMapperImpl implements LocalStorageMapper {

    @Override
    public LocalStorage toEntity(LocalStorageDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LocalStorage localStorage = new LocalStorage();

        localStorage.setCreateBy( arg0.getCreateBy() );
        localStorage.setUpdateBy( arg0.getUpdateBy() );
        localStorage.setCreateTime( arg0.getCreateTime() );
        localStorage.setUpdateTime( arg0.getUpdateTime() );
        localStorage.setId( arg0.getId() );
        localStorage.setRealName( arg0.getRealName() );
        localStorage.setName( arg0.getName() );
        localStorage.setSuffix( arg0.getSuffix() );
        localStorage.setType( arg0.getType() );
        localStorage.setSize( arg0.getSize() );

        return localStorage;
    }

    @Override
    public LocalStorageDto toDto(LocalStorage arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LocalStorageDto localStorageDto = new LocalStorageDto();

        localStorageDto.setCreateBy( arg0.getCreateBy() );
        localStorageDto.setUpdateBy( arg0.getUpdateBy() );
        localStorageDto.setCreateTime( arg0.getCreateTime() );
        localStorageDto.setUpdateTime( arg0.getUpdateTime() );
        localStorageDto.setId( arg0.getId() );
        localStorageDto.setRealName( arg0.getRealName() );
        localStorageDto.setName( arg0.getName() );
        localStorageDto.setSuffix( arg0.getSuffix() );
        localStorageDto.setType( arg0.getType() );
        localStorageDto.setSize( arg0.getSize() );

        return localStorageDto;
    }

    @Override
    public List<LocalStorage> toEntity(List<LocalStorageDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<LocalStorage> list = new ArrayList<LocalStorage>( arg0.size() );
        for ( LocalStorageDto localStorageDto : arg0 ) {
            list.add( toEntity( localStorageDto ) );
        }

        return list;
    }

    @Override
    public List<LocalStorageDto> toDto(List<LocalStorage> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<LocalStorageDto> list = new ArrayList<LocalStorageDto>( arg0.size() );
        for ( LocalStorage localStorage : arg0 ) {
            list.add( toDto( localStorage ) );
        }

        return list;
    }
}
