package me.zhengjie.modules.mnt.service.mapstruct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.mnt.domain.Deploy;
import me.zhengjie.modules.mnt.domain.ServerDeploy;
import me.zhengjie.modules.mnt.service.dto.DeployDto;
import me.zhengjie.modules.mnt.service.dto.ServerDeployDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DeployMapperImpl implements DeployMapper {

    @Autowired
    private AppMapper appMapper;
    @Autowired
    private ServerDeployMapper serverDeployMapper;

    @Override
    public Deploy toEntity(DeployDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Deploy deploy = new Deploy();

        deploy.setCreateBy( arg0.getCreateBy() );
        deploy.setUpdateBy( arg0.getUpdateBy() );
        deploy.setCreateTime( arg0.getCreateTime() );
        deploy.setUpdateTime( arg0.getUpdateTime() );
        if ( arg0.getId() != null ) {
            deploy.setId( Long.parseLong( arg0.getId() ) );
        }
        deploy.setDeploys( serverDeployDtoSetToServerDeploySet( arg0.getDeploys() ) );
        deploy.setApp( appMapper.toEntity( arg0.getApp() ) );

        return deploy;
    }

    @Override
    public DeployDto toDto(Deploy arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DeployDto deployDto = new DeployDto();

        deployDto.setCreateBy( arg0.getCreateBy() );
        deployDto.setUpdateBy( arg0.getUpdateBy() );
        deployDto.setCreateTime( arg0.getCreateTime() );
        deployDto.setUpdateTime( arg0.getUpdateTime() );
        if ( arg0.getId() != null ) {
            deployDto.setId( String.valueOf( arg0.getId() ) );
        }
        deployDto.setApp( appMapper.toDto( arg0.getApp() ) );
        deployDto.setDeploys( serverDeploySetToServerDeployDtoSet( arg0.getDeploys() ) );

        return deployDto;
    }

    @Override
    public List<Deploy> toEntity(List<DeployDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Deploy> list = new ArrayList<Deploy>( arg0.size() );
        for ( DeployDto deployDto : arg0 ) {
            list.add( toEntity( deployDto ) );
        }

        return list;
    }

    @Override
    public List<DeployDto> toDto(List<Deploy> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DeployDto> list = new ArrayList<DeployDto>( arg0.size() );
        for ( Deploy deploy : arg0 ) {
            list.add( toDto( deploy ) );
        }

        return list;
    }

    protected Set<ServerDeploy> serverDeployDtoSetToServerDeploySet(Set<ServerDeployDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ServerDeploy> set1 = new HashSet<ServerDeploy>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServerDeployDto serverDeployDto : set ) {
            set1.add( serverDeployMapper.toEntity( serverDeployDto ) );
        }

        return set1;
    }

    protected Set<ServerDeployDto> serverDeploySetToServerDeployDtoSet(Set<ServerDeploy> set) {
        if ( set == null ) {
            return null;
        }

        Set<ServerDeployDto> set1 = new HashSet<ServerDeployDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServerDeploy serverDeploy : set ) {
            set1.add( serverDeployMapper.toDto( serverDeploy ) );
        }

        return set1;
    }
}
