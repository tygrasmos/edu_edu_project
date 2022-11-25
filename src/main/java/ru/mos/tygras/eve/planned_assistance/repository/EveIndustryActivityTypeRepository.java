package ru.mos.tygras.eve.planned_assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.mos.tygras.eve.planned_assistance.model.entity.EveIndustryActivityType;

public interface EveIndustryActivityTypeRepository
        extends JpaRepository<EveIndustryActivityType, Long>, JpaSpecificationExecutor<EveIndustryActivityType> {

    EveIndustryActivityType findEveIndustryActivityTypeByActivityId(Integer activityId);

    EveIndustryActivityType findEveIndustryActivityTypeByActivityName(String activityName);

}
