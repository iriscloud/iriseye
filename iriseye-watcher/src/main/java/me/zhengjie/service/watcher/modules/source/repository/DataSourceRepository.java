package me.zhengjie.service.watcher.modules.source.repository;

import me.zhengjie.service.watcher.modules.source.domain.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DataSourceRepository extends JpaRepository<DataSource, String>, JpaSpecificationExecutor<DataSource> {
}
