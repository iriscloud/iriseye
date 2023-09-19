package me.zhengjie.modules.mnt.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.mnt.domain.App;
import me.zhengjie.modules.mnt.service.dto.AppDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class AppMapperImpl implements AppMapper {

    @Override
    public App toEntity(AppDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        App app = new App();

        app.setCreateBy( arg0.getCreateBy() );
        app.setUpdateBy( arg0.getUpdateBy() );
        app.setCreateTime( arg0.getCreateTime() );
        app.setUpdateTime( arg0.getUpdateTime() );
        app.setId( arg0.getId() );
        app.setName( arg0.getName() );
        if ( arg0.getPort() != null ) {
            app.setPort( arg0.getPort() );
        }
        app.setUploadPath( arg0.getUploadPath() );
        app.setDeployPath( arg0.getDeployPath() );
        app.setBackupPath( arg0.getBackupPath() );
        app.setStartScript( arg0.getStartScript() );
        app.setDeployScript( arg0.getDeployScript() );

        return app;
    }

    @Override
    public AppDto toDto(App arg0) {
        if ( arg0 == null ) {
            return null;
        }

        AppDto appDto = new AppDto();

        appDto.setCreateBy( arg0.getCreateBy() );
        appDto.setUpdateBy( arg0.getUpdateBy() );
        appDto.setCreateTime( arg0.getCreateTime() );
        appDto.setUpdateTime( arg0.getUpdateTime() );
        appDto.setId( arg0.getId() );
        appDto.setName( arg0.getName() );
        appDto.setPort( arg0.getPort() );
        appDto.setUploadPath( arg0.getUploadPath() );
        appDto.setDeployPath( arg0.getDeployPath() );
        appDto.setBackupPath( arg0.getBackupPath() );
        appDto.setStartScript( arg0.getStartScript() );
        appDto.setDeployScript( arg0.getDeployScript() );

        return appDto;
    }

    @Override
    public List<App> toEntity(List<AppDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<App> list = new ArrayList<App>( arg0.size() );
        for ( AppDto appDto : arg0 ) {
            list.add( toEntity( appDto ) );
        }

        return list;
    }

    @Override
    public List<AppDto> toDto(List<App> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AppDto> list = new ArrayList<AppDto>( arg0.size() );
        for ( App app : arg0 ) {
            list.add( toDto( app ) );
        }

        return list;
    }
}
