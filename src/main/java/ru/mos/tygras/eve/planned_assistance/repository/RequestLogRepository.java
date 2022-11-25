package ru.mos.tygras.eve.planned_assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.mos.tygras.eve.planned_assistance.model.entity.RequestLog;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long>, JpaSpecificationExecutor<RequestLog> {

    RequestLog findRequestLogByState(String state);
}
