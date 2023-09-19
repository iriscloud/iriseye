package me.zhengjie.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.domain.SysLog;
import me.zhengjie.service.dto.SysLogSmallDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:22+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class LogSmallMapperImpl implements LogSmallMapper {

    @Override
    public SysLog toEntity(SysLogSmallDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysLog sysLog = new SysLog();

        sysLog.setDescription( arg0.getDescription() );
        sysLog.setRequestIp( arg0.getRequestIp() );
        sysLog.setAddress( arg0.getAddress() );
        sysLog.setBrowser( arg0.getBrowser() );
        sysLog.setTime( arg0.getTime() );
        sysLog.setCreateTime( arg0.getCreateTime() );

        return sysLog;
    }

    @Override
    public SysLogSmallDto toDto(SysLog arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysLogSmallDto sysLogSmallDto = new SysLogSmallDto();

        sysLogSmallDto.setDescription( arg0.getDescription() );
        sysLogSmallDto.setRequestIp( arg0.getRequestIp() );
        sysLogSmallDto.setTime( arg0.getTime() );
        sysLogSmallDto.setAddress( arg0.getAddress() );
        sysLogSmallDto.setBrowser( arg0.getBrowser() );
        sysLogSmallDto.setCreateTime( arg0.getCreateTime() );

        return sysLogSmallDto;
    }

    @Override
    public List<SysLog> toEntity(List<SysLogSmallDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SysLog> list = new ArrayList<SysLog>( arg0.size() );
        for ( SysLogSmallDto sysLogSmallDto : arg0 ) {
            list.add( toEntity( sysLogSmallDto ) );
        }

        return list;
    }

    @Override
    public List<SysLogSmallDto> toDto(List<SysLog> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SysLogSmallDto> list = new ArrayList<SysLogSmallDto>( arg0.size() );
        for ( SysLog sysLog : arg0 ) {
            list.add( toDto( sysLog ) );
        }

        return list;
    }
}
