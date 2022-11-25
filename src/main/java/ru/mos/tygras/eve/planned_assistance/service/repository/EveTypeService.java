package ru.mos.tygras.eve.planned_assistance.service.repository;

import ru.mos.tygras.eve.planned_assistance.model.entity.EveType;

import java.util.List;

public interface EveTypeService {

    EveType findByTypeId(Integer typeId);

    EveType findById(Long id);

    EveType findByName(String typeName);

    List<EveType> findAll();

    EveType save(EveType eveType);

    void delete(EveType eveType);
}
