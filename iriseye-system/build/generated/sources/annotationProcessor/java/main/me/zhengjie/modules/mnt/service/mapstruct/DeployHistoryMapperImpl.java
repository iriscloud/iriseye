package me.zhengjie.modules.mnt.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.mnt.domain.DeployHistory;
import me.zhengjie.modules.mnt.service.dto.DeployHistoryDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class DeployHistoryMapperImpl implements DeployHistoryMapper {

    @Override
    public DeployHistory toEntity(DeployHistoryDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DeployHistory deployHistory = new DeployHistory();

        deployHistory.setId( arg0.getId() );
        deployHistory.setAppName( arg0.getAppName() );
        deployHistory.setIp( arg0.getIp() );
        deployHistory.setDeployDate( arg0.getDeployDate() );
        deployHistory.setDeployUser( arg0.getDeployUser() );
        deployHistory.setDeployId( arg0.getDeployId() );

        return deployHistory;
    }

    @Override
    public DeployHistoryDto toDto(DeployHistory arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DeployHistoryDto deployHistoryDto = new DeployHistoryDto();

        deployHistoryDto.setId( arg0.getId() );
        deployHistoryDto.setAppName( arg0.getAppName() );
        deployHistoryDto.setIp( arg0.getIp() );
        deployHistoryDto.setDeployDate( arg0.getDeployDate() );
        deployHistoryDto.setDeployUser( arg0.getDeployUser() );
        deployHistoryDto.setDeployId( arg0.getDeployId() );

        return deployHistoryDto;
    }

    @Override
    public List<DeployHistory> toEntity(List<DeployHistoryDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DeployHistory> list = new ArrayList<DeployHistory>( arg0.size() );
        for ( DeployHistoryDto deployHistoryDto : arg0 ) {
            list.add( toEntity( deployHistoryDto ) );
        }

        return list;
    }

    @Override
    public List<DeployHistoryDto> toDto(List<DeployHistory> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DeployHistoryDto> list = new ArrayList<DeployHistoryDto>( arg0.size() );
        for ( DeployHistory deployHistory : arg0 ) {
            list.add( toDto( deployHistory ) );
        }

        return list;
    }
}
