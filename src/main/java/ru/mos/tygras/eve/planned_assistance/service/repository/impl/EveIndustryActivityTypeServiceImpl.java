package ru.mos.tygras.eve.planned_assistance.service.repository.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.EveIndustryActivityType;
import ru.mos.tygras.eve.planned_assistance.repository.EveIndustryActivityTypeRepository;
import ru.mos.tygras.eve.planned_assistance.service.repository.EveIndustryActivityTypeService;

import java.util.List;

@Service
public class EveIndustryActivityTypeServiceImpl implements EveIndustryActivityTypeService {

    private final static Integer notFoundActivityTYpeId = 0;

    private final EveIndustryActivityTypeRepository eveIndustryActivityTypeRepository;

    public EveIndustryActivityTypeServiceImpl(EveIndustryActivityTypeRepository eveIndustryActivityTypeRepository){
        this.eveIndustryActivityTypeRepository = eveIndustryActivityTypeRepository;
    }

    @Override
    public EveIndustryActivityType findById(Long id) {
        return eveIndustryActivityTypeRepository.findById(id).get();
    }

    @Override
    public EveIndustryActivityType findByActivityId(Integer activityId) {
        EveIndustryActivityType type = eveIndustryActivityTypeRepository.findEveIndustryActivityTypeByActivityId(activityId);
        if (type == null){
            return eveIndustryActivityTypeRepository.findEveIndustryActivityTypeByActivityId(notFoundActivityTYpeId);
        }
        return type;
    }

    @Override
    public EveIndustryActivityType findByActivityName(String activityName) {
        return eveIndustryActivityTypeRepository.findEveIndustryActivityTypeByActivityName(activityName);
    }

    @Override
    public List<EveIndustryActivityType> findAll() {
        return eveIndustryActivityTypeRepository.findAll();
    }
}
