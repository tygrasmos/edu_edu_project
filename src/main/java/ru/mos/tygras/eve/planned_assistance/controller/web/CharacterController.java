package ru.mos.tygras.eve.planned_assistance.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mos.tygras.eve.planned_assistance.dto.mapper.CharacterDtoMapper;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.service.response.CharacterResponseService;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterService;

import java.util.List;

@Controller
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterDtoMapper characterDtoMapper;
    private final CharacterResponseService characterResponseService;

    public CharacterController(CharacterService characterService,
                               CharacterResponseService characterResponseService,
                               CharacterDtoMapper characterDtoMapper){
        this.characterService = characterService;
        this.characterResponseService = characterResponseService;
        this.characterDtoMapper = characterDtoMapper;
    }


    /** Список всех персонажей */
    @GetMapping("/characters")
    public String listPageCharacters(Model model){
        List<Character> characterList = characterService.findAll();
        characterResponseService.updateAllInfo(characterList);
        model.addAttribute("characters",
                characterDtoMapper.entityListToDtoList(characterList));
        return "characterList";
    }

    /** Информация о персонаже */
    @GetMapping("/character/{characterId}/info")
    public String getCharacterInfo(Model model, @PathVariable("characterId") Long characterId){
        return null;
    }

    /** Добавление нового персонажа */
    @GetMapping("/character/add")
    public String addNewCharacter(Model model){
        model.addAttribute("char", "");
        return "addCharacter";
    }

    /** Добавление нового персонажа редирект на страницу авторизации */
    @PostMapping("/character/add")
    public String redirectToAuthorisePage(Model model){
        return "redirect:".concat(characterResponseService.getUrlForAddNewCharacter());
    }

}
