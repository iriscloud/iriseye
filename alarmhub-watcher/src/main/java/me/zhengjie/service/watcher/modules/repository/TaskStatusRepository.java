package me.zhengjie.service.watcher.modules.repository;

import me.zhengjie.service.watcher.modules.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long>, JpaSpecificationExecutor<TaskStatus> {
    List<TaskStatus> findByTaskName(String name);
}
