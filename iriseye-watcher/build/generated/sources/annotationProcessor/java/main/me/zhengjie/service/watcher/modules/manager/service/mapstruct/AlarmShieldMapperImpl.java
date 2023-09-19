package me.zhengjie.service.watcher.modules.manager.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.service.watcher.modules.manager.domain.AlarmShield;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmShieldDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:40+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class AlarmShieldMapperImpl implements AlarmShieldMapper {

    @Override
    public AlarmShield toEntity(AlarmShieldDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        AlarmShield alarmShield = new AlarmShield();

        alarmShield.setCreateBy( arg0.getCreateBy() );
        alarmShield.setUpdateBy( arg0.getUpdateBy() );
        alarmShield.setCreateTime( arg0.getCreateTime() );
        alarmShield.setUpdateTime( arg0.getUpdateTime() );
        alarmShield.setId( arg0.getId() );
        alarmShield.setName( arg0.getName() );
        alarmShield.setLevel( arg0.getLevel() );
        alarmShield.setRuleType( arg0.getRuleType() );
        alarmShield.setRuleContent( arg0.getRuleContent() );
        alarmShield.setRuleContentRefer( arg0.getRuleContentRefer() );
        alarmShield.setCompare( arg0.getCompare() );
        alarmShield.setExpected( arg0.getExpected() );
        alarmShield.setExecTime( arg0.getExecTime() );
        alarmShield.setExecCount( arg0.getExecCount() );
        alarmShield.setDealUrl( arg0.getDealUrl() );
        alarmShield.setNotifyType( arg0.getNotifyType() );
        alarmShield.setCallBackUrl( arg0.getCallBackUrl() );

        return alarmShield;
    }

    @Override
    public AlarmShieldDto toDto(AlarmShield arg0) {
        if ( arg0 == null ) {
            return null;
        }

        AlarmShieldDto alarmShieldDto = new AlarmShieldDto();

        alarmShieldDto.setCreateBy( arg0.getCreateBy() );
        alarmShieldDto.setUpdateBy( arg0.getUpdateBy() );
        alarmShieldDto.setCreateTime( arg0.getCreateTime() );
        alarmShieldDto.setUpdateTime( arg0.getUpdateTime() );
        alarmShieldDto.setId( arg0.getId() );
        alarmShieldDto.setName( arg0.getName() );
        alarmShieldDto.setLevel( arg0.getLevel() );
        alarmShieldDto.setRuleType( arg0.getRuleType() );
        alarmShieldDto.setRuleContent( arg0.getRuleContent() );
        alarmShieldDto.setRuleContentRefer( arg0.getRuleContentRefer() );
        alarmShieldDto.setCompare( arg0.getCompare() );
        alarmShieldDto.setExpected( arg0.getExpected() );
        alarmShieldDto.setExecTime( arg0.getExecTime() );
        alarmShieldDto.setExecCount( arg0.getExecCount() );
        alarmShieldDto.setDealUrl( arg0.getDealUrl() );
        alarmShieldDto.setNotifyType( arg0.getNotifyType() );
        alarmShieldDto.setCallBackUrl( arg0.getCallBackUrl() );

        return alarmShieldDto;
    }

    @Override
    public List<AlarmShield> toEntity(List<AlarmShieldDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AlarmShield> list = new ArrayList<AlarmShield>( arg0.size() );
        for ( AlarmShieldDto alarmShieldDto : arg0 ) {
            list.add( toEntity( alarmShieldDto ) );
        }

        return list;
    }

    @Override
    public List<AlarmShieldDto> toDto(List<AlarmShield> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AlarmShieldDto> list = new ArrayList<AlarmShieldDto>( arg0.size() );
        for ( AlarmShield alarmShield : arg0 ) {
            list.add( toDto( alarmShield ) );
        }

        return list;
    }
}
