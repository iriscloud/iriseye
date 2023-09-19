package me.zhengjie.service.watcher.modules.manager.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.service.watcher.modules.manager.domain.AlarmRule;
import me.zhengjie.service.watcher.modules.manager.service.dto.AlarmRuleDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:41+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class AlarmRuleMapperImpl implements AlarmRuleMapper {

    @Override
    public AlarmRule toEntity(AlarmRuleDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        AlarmRule alarmRule = new AlarmRule();

        alarmRule.setCreateBy( arg0.getCreateBy() );
        alarmRule.setUpdateBy( arg0.getUpdateBy() );
        alarmRule.setCreateTime( arg0.getCreateTime() );
        alarmRule.setUpdateTime( arg0.getUpdateTime() );
        alarmRule.setId( arg0.getId() );
        alarmRule.setName( arg0.getName() );
        alarmRule.setLevel( arg0.getLevel() );
        alarmRule.setRuleType( arg0.getRuleType() );
        alarmRule.setRuleContent( arg0.getRuleContent() );
        alarmRule.setRuleContentRefer( arg0.getRuleContentRefer() );
        alarmRule.setCompare( arg0.getCompare() );
        alarmRule.setExpected( arg0.getExpected() );
        alarmRule.setExecTime( arg0.getExecTime() );
        alarmRule.setExecCount( arg0.getExecCount() );
        alarmRule.setDealUrl( arg0.getDealUrl() );
        alarmRule.setNotifyType( arg0.getNotifyType() );
        alarmRule.setCallBackUrl( arg0.getCallBackUrl() );

        return alarmRule;
    }

    @Override
    public AlarmRuleDto toDto(AlarmRule arg0) {
        if ( arg0 == null ) {
            return null;
        }

        AlarmRuleDto alarmRuleDto = new AlarmRuleDto();

        alarmRuleDto.setCreateBy( arg0.getCreateBy() );
        alarmRuleDto.setUpdateBy( arg0.getUpdateBy() );
        alarmRuleDto.setCreateTime( arg0.getCreateTime() );
        alarmRuleDto.setUpdateTime( arg0.getUpdateTime() );
        alarmRuleDto.setId( arg0.getId() );
        alarmRuleDto.setName( arg0.getName() );
        alarmRuleDto.setLevel( arg0.getLevel() );
        alarmRuleDto.setRuleType( arg0.getRuleType() );
        alarmRuleDto.setRuleContent( arg0.getRuleContent() );
        alarmRuleDto.setRuleContentRefer( arg0.getRuleContentRefer() );
        alarmRuleDto.setCompare( arg0.getCompare() );
        alarmRuleDto.setExpected( arg0.getExpected() );
        alarmRuleDto.setExecTime( arg0.getExecTime() );
        alarmRuleDto.setExecCount( arg0.getExecCount() );
        alarmRuleDto.setDealUrl( arg0.getDealUrl() );
        alarmRuleDto.setNotifyType( arg0.getNotifyType() );
        alarmRuleDto.setCallBackUrl( arg0.getCallBackUrl() );

        return alarmRuleDto;
    }

    @Override
    public List<AlarmRule> toEntity(List<AlarmRuleDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AlarmRule> list = new ArrayList<AlarmRule>( arg0.size() );
        for ( AlarmRuleDto alarmRuleDto : arg0 ) {
            list.add( toEntity( alarmRuleDto ) );
        }

        return list;
    }

    @Override
    public List<AlarmRuleDto> toDto(List<AlarmRule> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AlarmRuleDto> list = new ArrayList<AlarmRuleDto>( arg0.size() );
        for ( AlarmRule alarmRule : arg0 ) {
            list.add( toDto( alarmRule ) );
        }

        return list;
    }
}
