package me.zhengjie.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.domain.SysLog;
import me.zhengjie.service.dto.SysLogErrorDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:22+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class LogErrorMapperImpl implements LogErrorMapper {

    @Override
    public SysLog toEntity(SysLogErrorDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysLog sysLog = new SysLog();

        sysLog.setId( arg0.getId() );
        sysLog.setUsername( arg0.getUsername() );
        sysLog.setDescription( arg0.getDescription() );
        sysLog.setMethod( arg0.getMethod() );
        sysLog.setParams( arg0.getParams() );
        sysLog.setRequestIp( arg0.getRequestIp() );
        sysLog.setAddress( arg0.getAddress() );
        sysLog.setBrowser( arg0.getBrowser() );
        sysLog.setCreateTime( arg0.getCreateTime() );

        return sysLog;
    }

    @Override
    public SysLogErrorDto toDto(SysLog arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysLogErrorDto sysLogErrorDto = new SysLogErrorDto();

        sysLogErrorDto.setId( arg0.getId() );
        sysLogErrorDto.setUsername( arg0.getUsername() );
        sysLogErrorDto.setDescription( arg0.getDescription() );
        sysLogErrorDto.setMethod( arg0.getMethod() );
        sysLogErrorDto.setParams( arg0.getParams() );
        sysLogErrorDto.setBrowser( arg0.getBrowser() );
        sysLogErrorDto.setRequestIp( arg0.getRequestIp() );
        sysLogErrorDto.setAddress( arg0.getAddress() );
        sysLogErrorDto.setCreateTime( arg0.getCreateTime() );

        return sysLogErrorDto;
    }

    @Override
    public List<SysLog> toEntity(List<SysLogErrorDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SysLog> list = new ArrayList<SysLog>( arg0.size() );
        for ( SysLogErrorDto sysLogErrorDto : arg0 ) {
            list.add( toEntity( sysLogErrorDto ) );
        }

        return list;
    }

    @Override
    public List<SysLogErrorDto> toDto(List<SysLog> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SysLogErrorDto> list = new ArrayList<SysLogErrorDto>( arg0.size() );
        for ( SysLog sysLog : arg0 ) {
            list.add( toDto( sysLog ) );
        }

        return list;
    }
}
