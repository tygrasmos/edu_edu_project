package ru.mos.tygras.eve.planned_assistance.service.repository.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterIndustryJob;
import ru.mos.tygras.eve.planned_assistance.repository.CharacterIndustryJobRepository;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterIndustryJobService;
import ru.mos.tygras.eve.planned_assistance.service.security.PermissionService;

import java.util.List;

@Service
public class CharacterIndustryJobServiceImpl implements CharacterIndustryJobService {

    private final CharacterIndustryJobRepository characterIndustryJobRepository;
    private final PermissionService permissionService;

    public CharacterIndustryJobServiceImpl(CharacterIndustryJobRepository characterIndustryJobRepository,
                                           PermissionService permissionService){
        this.characterIndustryJobRepository = characterIndustryJobRepository;
        this.permissionService = permissionService;
    }

    @Override
    public List<CharacterIndustryJob> findByCharacter(Character character) {
        return characterIndustryJobRepository.findCharacterIndustryJobByCharacter(character);
    }

    @Override
    public List<CharacterIndustryJob> findByCharacterAndApproved(Character character, Integer approved) {
        return characterIndustryJobRepository.findCharacterIndustryJobByCharacterAndApproved(character, approved);
    }

    @Override
    public CharacterIndustryJob findByJobId(Long jobId) {
        return characterIndustryJobRepository.findCharacterIndustryJobByJobId(jobId);
    }

    @Override
    public CharacterIndustryJob findById(Long id) {
        return characterIndustryJobRepository.findById(id).get();
    }

    @Override
    public CharacterIndustryJob save(CharacterIndustryJob characterIndustryJob) {
        CharacterIndustryJob addedJob = characterIndustryJobRepository.save(characterIndustryJob);
        permissionService.addPermissionForUser(addedJob);
        return addedJob;
    }

    @Override
    public void delete(CharacterIndustryJob characterIndustryJob) {
        characterIndustryJobRepository.delete(characterIndustryJob);
    }
}
