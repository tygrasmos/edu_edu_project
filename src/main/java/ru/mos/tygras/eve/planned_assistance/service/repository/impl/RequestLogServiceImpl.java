package ru.mos.tygras.eve.planned_assistance.service.repository.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.RequestLog;
import ru.mos.tygras.eve.planned_assistance.repository.RequestLogRepository;
import ru.mos.tygras.eve.planned_assistance.service.repository.RequestLogService;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private final RequestLogRepository requestLogRepository;

    public RequestLogServiceImpl(RequestLogRepository requestLogRepository){
        this.requestLogRepository = requestLogRepository;
    }

    @Override
    public RequestLog save(RequestLog requestLog) {
        return requestLogRepository.save(requestLog);
    }

    @Override
    public RequestLog findByState(String state) {
        return requestLogRepository.findRequestLogByState(state);
    }
}
