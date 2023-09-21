package me.zhengjie.service.watcher.modules.source.repository;

import me.zhengjie.service.watcher.modules.source.domain.WatcherSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WatcherSourceRepository extends JpaRepository<WatcherSource, String>, JpaSpecificationExecutor<WatcherSource> {
    WatcherSource findByName(String name);
}
