package me.zhengjie.modules.mnt.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.mnt.domain.Database;
import me.zhengjie.modules.mnt.service.dto.DatabaseDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DatabaseMapperImpl implements DatabaseMapper {

    @Override
    public Database toEntity(DatabaseDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Database database = new Database();

        database.setCreateBy( arg0.getCreateBy() );
        database.setUpdateBy( arg0.getUpdateBy() );
        database.setCreateTime( arg0.getCreateTime() );
        database.setUpdateTime( arg0.getUpdateTime() );
        database.setId( arg0.getId() );
        database.setName( arg0.getName() );
        database.setJdbcUrl( arg0.getJdbcUrl() );
        database.setPwd( arg0.getPwd() );
        database.setUserName( arg0.getUserName() );

        return database;
    }

    @Override
    public DatabaseDto toDto(Database arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DatabaseDto databaseDto = new DatabaseDto();

        databaseDto.setCreateBy( arg0.getCreateBy() );
        databaseDto.setUpdateBy( arg0.getUpdateBy() );
        databaseDto.setCreateTime( arg0.getCreateTime() );
        databaseDto.setUpdateTime( arg0.getUpdateTime() );
        databaseDto.setId( arg0.getId() );
        databaseDto.setName( arg0.getName() );
        databaseDto.setJdbcUrl( arg0.getJdbcUrl() );
        databaseDto.setPwd( arg0.getPwd() );
        databaseDto.setUserName( arg0.getUserName() );

        return databaseDto;
    }

    @Override
    public List<Database> toEntity(List<DatabaseDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Database> list = new ArrayList<Database>( arg0.size() );
        for ( DatabaseDto databaseDto : arg0 ) {
            list.add( toEntity( databaseDto ) );
        }

        return list;
    }

    @Override
    public List<DatabaseDto> toDto(List<Database> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DatabaseDto> list = new ArrayList<DatabaseDto>( arg0.size() );
        for ( Database database : arg0 ) {
            list.add( toDto( database ) );
        }

        return list;
    }
}
