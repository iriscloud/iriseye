package me.zhengjie.service.watcher.modules.source.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.service.watcher.modules.source.domain.DataSource;
import me.zhengjie.service.watcher.modules.source.service.dto.DataSourceDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:40+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DataSourceMapperImpl implements DataSourceMapper {

    @Override
    public DataSource toEntity(DataSourceDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DataSource dataSource = new DataSource();

        dataSource.setCreateBy( arg0.getCreateBy() );
        dataSource.setUpdateBy( arg0.getUpdateBy() );
        dataSource.setCreateTime( arg0.getCreateTime() );
        dataSource.setUpdateTime( arg0.getUpdateTime() );
        dataSource.setId( arg0.getId() );
        dataSource.setName( arg0.getName() );
        dataSource.setUrl( arg0.getUrl() );
        dataSource.setUserName( arg0.getUserName() );
        dataSource.setPwd( arg0.getPwd() );

        return dataSource;
    }

    @Override
    public DataSourceDto toDto(DataSource arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DataSourceDto dataSourceDto = new DataSourceDto();

        dataSourceDto.setCreateBy( arg0.getCreateBy() );
        dataSourceDto.setUpdateBy( arg0.getUpdateBy() );
        dataSourceDto.setCreateTime( arg0.getCreateTime() );
        dataSourceDto.setUpdateTime( arg0.getUpdateTime() );
        dataSourceDto.setId( arg0.getId() );
        dataSourceDto.setName( arg0.getName() );
        dataSourceDto.setUrl( arg0.getUrl() );
        dataSourceDto.setPwd( arg0.getPwd() );
        dataSourceDto.setUserName( arg0.getUserName() );

        return dataSourceDto;
    }

    @Override
    public List<DataSource> toEntity(List<DataSourceDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DataSource> list = new ArrayList<DataSource>( arg0.size() );
        for ( DataSourceDto dataSourceDto : arg0 ) {
            list.add( toEntity( dataSourceDto ) );
        }

        return list;
    }

    @Override
    public List<DataSourceDto> toDto(List<DataSource> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DataSourceDto> list = new ArrayList<DataSourceDto>( arg0.size() );
        for ( DataSource dataSource : arg0 ) {
            list.add( toDto( dataSource ) );
        }

        return list;
    }
}
