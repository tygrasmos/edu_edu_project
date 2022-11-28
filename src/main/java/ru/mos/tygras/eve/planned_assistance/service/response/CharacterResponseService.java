package ru.mos.tygras.eve.planned_assistance.service.response;

import ru.mos.tygras.eve.planned_assistance.model.entity.Character;

import java.util.List;

public interface CharacterResponseService {

    void updateAllInfo(List<Character> characterList);

    String getUrlForAddNewCharacter();
}
