package ru.mos.tygras.eve.planned_assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.access.prepost.PostFilter;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterIndustryJob;

import java.util.List;

public interface CharacterIndustryJobRepository extends JpaRepository<CharacterIndustryJob, Long>, JpaSpecificationExecutor<CharacterIndustryJob> {

    List<CharacterIndustryJob> findCharacterIndustryJobByCharacterAndApproved(Character character, Integer approved);

    CharacterIndustryJob findCharacterIndustryJobByJobId(Long jobId);

    List<CharacterIndustryJob> findCharacterIndustryJobByCharacter(Character character);

    @PostFilter("hasPermission(filterObject, 'ADMINISTRATION')")
    List<CharacterIndustryJob> findAll();
}
