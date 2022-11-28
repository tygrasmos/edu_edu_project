package ru.mos.tygras.eve.planned_assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.mos.tygras.eve.planned_assistance.model.entity.EveType;

public interface EveTypeRepository extends JpaRepository<EveType, Long>, JpaSpecificationExecutor<EveType> {

    EveType findEveTypeByTypeName(String typeName);

    EveType findEveTypeByTypeId(Integer typeId);

}
