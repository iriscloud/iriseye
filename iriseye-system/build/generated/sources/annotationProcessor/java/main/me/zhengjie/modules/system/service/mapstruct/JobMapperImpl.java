package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.service.dto.JobDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:57+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public Job toEntity(JobDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Job job = new Job();

        job.setCreateBy( arg0.getCreateBy() );
        job.setUpdateBy( arg0.getUpdateBy() );
        job.setCreateTime( arg0.getCreateTime() );
        job.setUpdateTime( arg0.getUpdateTime() );
        job.setId( arg0.getId() );
        job.setName( arg0.getName() );
        if ( arg0.getJobSort() != null ) {
            job.setJobSort( arg0.getJobSort().longValue() );
        }
        job.setEnabled( arg0.getEnabled() );

        return job;
    }

    @Override
    public JobDto toDto(Job arg0) {
        if ( arg0 == null ) {
            return null;
        }

        JobDto jobDto = new JobDto();

        jobDto.setCreateBy( arg0.getCreateBy() );
        jobDto.setUpdateBy( arg0.getUpdateBy() );
        jobDto.setCreateTime( arg0.getCreateTime() );
        jobDto.setUpdateTime( arg0.getUpdateTime() );
        jobDto.setId( arg0.getId() );
        if ( arg0.getJobSort() != null ) {
            jobDto.setJobSort( arg0.getJobSort().intValue() );
        }
        jobDto.setName( arg0.getName() );
        jobDto.setEnabled( arg0.getEnabled() );

        return jobDto;
    }

    @Override
    public List<Job> toEntity(List<JobDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Job> list = new ArrayList<Job>( arg0.size() );
        for ( JobDto jobDto : arg0 ) {
            list.add( toEntity( jobDto ) );
        }

        return list;
    }

    @Override
    public List<JobDto> toDto(List<Job> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<JobDto> list = new ArrayList<JobDto>( arg0.size() );
        for ( Job job : arg0 ) {
            list.add( toDto( job ) );
        }

        return list;
    }
}
