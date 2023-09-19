package me.zhengjie.service.watcher.modules.manager.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.service.watcher.modules.manager.domain.AlarmHistory;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmHistoryDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:41+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class AlarmHistoryMapperImpl implements AlarmHistoryMapper {

    @Override
    public AlarmHistory toEntity(AlarmHistoryDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        AlarmHistory alarmHistory = new AlarmHistory();

        alarmHistory.setId( arg0.getId() );
        alarmHistory.setName( arg0.getName() );
        alarmHistory.setLevel( arg0.getLevel() );
        alarmHistory.setRuleType( arg0.getRuleType() );
        alarmHistory.setService( arg0.getService() );
        alarmHistory.setServiceLevel( arg0.getServiceLevel() );
        alarmHistory.setDetail( arg0.getDetail() );
        alarmHistory.setDealUrl( arg0.getDealUrl() );
        alarmHistory.setNotifyType( arg0.getNotifyType() );
        alarmHistory.setCallBackUrl( arg0.getCallBackUrl() );

        return alarmHistory;
    }

    @Override
    public AlarmHistoryDTO toDto(AlarmHistory arg0) {
        if ( arg0 == null ) {
            return null;
        }

        AlarmHistoryDTO alarmHistoryDTO = new AlarmHistoryDTO();

        alarmHistoryDTO.setId( arg0.getId() );
        alarmHistoryDTO.setName( arg0.getName() );
        alarmHistoryDTO.setLevel( arg0.getLevel() );
        alarmHistoryDTO.setRuleType( arg0.getRuleType() );
        alarmHistoryDTO.setService( arg0.getService() );
        alarmHistoryDTO.setServiceLevel( arg0.getServiceLevel() );
        alarmHistoryDTO.setDetail( arg0.getDetail() );
        alarmHistoryDTO.setDealUrl( arg0.getDealUrl() );
        alarmHistoryDTO.setNotifyType( arg0.getNotifyType() );
        alarmHistoryDTO.setCallBackUrl( arg0.getCallBackUrl() );

        return alarmHistoryDTO;
    }

    @Override
    public List<AlarmHistory> toEntity(List<AlarmHistoryDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AlarmHistory> list = new ArrayList<AlarmHistory>( arg0.size() );
        for ( AlarmHistoryDTO alarmHistoryDTO : arg0 ) {
            list.add( toEntity( alarmHistoryDTO ) );
        }

        return list;
    }

    @Override
    public List<AlarmHistoryDTO> toDto(List<AlarmHistory> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AlarmHistoryDTO> list = new ArrayList<AlarmHistoryDTO>( arg0.size() );
        for ( AlarmHistory alarmHistory : arg0 ) {
            list.add( toDto( alarmHistory ) );
        }

        return list;
    }
}
