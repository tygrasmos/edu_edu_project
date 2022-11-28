package ru.mos.tygras.eve.planned_assistance.service.repository;

import ru.mos.tygras.eve.planned_assistance.model.entity.EveIndustryActivityType;

import java.util.List;

public interface EveIndustryActivityTypeService {

    EveIndustryActivityType findById(Long id);

    EveIndustryActivityType findByActivityId(Integer activityId);

    EveIndustryActivityType findByActivityName(String activityName);

    List<EveIndustryActivityType> findAll();

}
