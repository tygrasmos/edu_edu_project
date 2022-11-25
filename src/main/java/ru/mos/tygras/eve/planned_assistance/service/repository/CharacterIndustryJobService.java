package ru.mos.tygras.eve.planned_assistance.service.repository;

import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterIndustryJob;

import java.util.List;

public interface CharacterIndustryJobService {

    List<CharacterIndustryJob> findByCharacter(Character character);

    List<CharacterIndustryJob> findByCharacterAndApproved(Character character, Integer approved);

    CharacterIndustryJob findByJobId(Long jobId);

    CharacterIndustryJob findById(Long id);

    CharacterIndustryJob save(CharacterIndustryJob characterIndustryJob);

    void delete(CharacterIndustryJob characterIndustryJob);
}
