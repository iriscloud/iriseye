package me.zhengjie.modules.mnt.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.mnt.domain.ServerDeploy;
import me.zhengjie.modules.mnt.service.dto.ServerDeployDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class ServerDeployMapperImpl implements ServerDeployMapper {

    @Override
    public ServerDeploy toEntity(ServerDeployDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ServerDeploy serverDeploy = new ServerDeploy();

        serverDeploy.setCreateBy( arg0.getCreateBy() );
        serverDeploy.setUpdateBy( arg0.getUpdateBy() );
        serverDeploy.setCreateTime( arg0.getCreateTime() );
        serverDeploy.setUpdateTime( arg0.getUpdateTime() );
        serverDeploy.setId( arg0.getId() );
        serverDeploy.setName( arg0.getName() );
        serverDeploy.setIp( arg0.getIp() );
        serverDeploy.setPort( arg0.getPort() );
        serverDeploy.setAccount( arg0.getAccount() );
        serverDeploy.setPassword( arg0.getPassword() );

        return serverDeploy;
    }

    @Override
    public ServerDeployDto toDto(ServerDeploy arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ServerDeployDto serverDeployDto = new ServerDeployDto();

        serverDeployDto.setCreateBy( arg0.getCreateBy() );
        serverDeployDto.setUpdateBy( arg0.getUpdateBy() );
        serverDeployDto.setCreateTime( arg0.getCreateTime() );
        serverDeployDto.setUpdateTime( arg0.getUpdateTime() );
        serverDeployDto.setId( arg0.getId() );
        serverDeployDto.setName( arg0.getName() );
        serverDeployDto.setIp( arg0.getIp() );
        serverDeployDto.setPort( arg0.getPort() );
        serverDeployDto.setAccount( arg0.getAccount() );
        serverDeployDto.setPassword( arg0.getPassword() );

        return serverDeployDto;
    }

    @Override
    public List<ServerDeploy> toEntity(List<ServerDeployDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ServerDeploy> list = new ArrayList<ServerDeploy>( arg0.size() );
        for ( ServerDeployDto serverDeployDto : arg0 ) {
            list.add( toEntity( serverDeployDto ) );
        }

        return list;
    }

    @Override
    public List<ServerDeployDto> toDto(List<ServerDeploy> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ServerDeployDto> list = new ArrayList<ServerDeployDto>( arg0.size() );
        for ( ServerDeploy serverDeploy : arg0 ) {
            list.add( toDto( serverDeploy ) );
        }

        return list;
    }
}
