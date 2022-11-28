package ru.mos.tygras.eve.planned_assistance.service.response;

import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.eve.industry.IndustryJob;

import java.util.List;

public interface IndustryResponseService {

    void saveIndustryJobsResponse(List<IndustryJob> industryJobList, Character character);

    List<IndustryJob> getIndustryJobListByCharacter(Character character);
}
