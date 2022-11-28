package ru.mos.tygras.eve.planned_assistance.service.repository.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.EveType;
import ru.mos.tygras.eve.planned_assistance.repository.EveTypeRepository;
import ru.mos.tygras.eve.planned_assistance.service.repository.EveTypeService;

import java.util.List;

@Service
public class EveTypeServiceImpl implements EveTypeService {

    private final static Integer notFoundTypeId = 0;

    private final EveTypeRepository eveTypeRepository;

    public EveTypeServiceImpl(EveTypeRepository eveTypeRepository){
        this.eveTypeRepository = eveTypeRepository;
    }

    @Override
    public EveType findByTypeId(Integer typeId) {
        EveType type = eveTypeRepository.findEveTypeByTypeId(typeId);
        if (type == null){
            return eveTypeRepository.findEveTypeByTypeId(notFoundTypeId);
        }
        return type;
    }

    @Override
    public EveType findById(Long id) {
        return eveTypeRepository.findById(id).get();
    }

    @Override
    public EveType findByName(String typeName) {
        return eveTypeRepository.findEveTypeByTypeName(typeName);
    }

    @Override
    public List<EveType> findAll() {
        return eveTypeRepository.findAll();
    }

    @Override
    public EveType save(EveType eveType) {
        return eveTypeRepository.save(eveType);
    }

    @Override
    public void delete(EveType eveType) {
        eveTypeRepository.delete(eveType);
    }
}
