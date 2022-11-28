package ru.mos.tygras.eve.planned_assistance.service.repository;

import ru.mos.tygras.eve.planned_assistance.model.entity.RequestLog;

public interface RequestLogService {

    RequestLog save(RequestLog requestLog);

    RequestLog findByState(String state);
}
