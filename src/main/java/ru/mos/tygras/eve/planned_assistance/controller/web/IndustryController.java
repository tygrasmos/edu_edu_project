package ru.mos.tygras.eve.planned_assistance.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.mos.tygras.eve.planned_assistance.dto.CharacterIndustryJobDto;
import ru.mos.tygras.eve.planned_assistance.dto.mapper.CharacterDtoMapper;
import ru.mos.tygras.eve.planned_assistance.dto.mapper.CharacterIndustryDtoMapper;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterIndustryJobService;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndustryController {

    private final static Integer notApproved = 0;
    private final static Integer reactionActivityType = 9;
    private final static Integer productionActivityType = 1;
    private final static Integer researchMeActivityType = 4;
    private final static Integer researchPeActivityType = 3;
    private final static Integer inventionActivityType = 8;
    private final static Integer copyingActivityType = 5;

    private final CharacterService characterService;
    private final CharacterIndustryDtoMapper characterIndustryDtoMapper;
    private final CharacterDtoMapper characterDtoMapper;
    private final CharacterIndustryJobService characterIndustryJobService;

    public IndustryController(CharacterService characterService,
                              CharacterIndustryJobService characterIndustryJobService,
                              CharacterIndustryDtoMapper characterIndustryDtoMapper,
                              CharacterDtoMapper characterDtoMapper){
        this.characterService = characterService;
        this.characterIndustryJobService = characterIndustryJobService;
        this.characterIndustryDtoMapper = characterIndustryDtoMapper;
        this.characterDtoMapper = characterDtoMapper;
    }

    /** Просмотр работ по постановке реакций на всех персонажах.*/
    @GetMapping(path = "/industry/reactions/{sortedType}")
    public String getIndustryJobReactionListOnAllCharacter(
            Model model, @PathVariable("sortedType") String sortedType){
        model.addAttribute("allReactionList",
                getSortedListByActivityType(characterService.findAll(), List.of(reactionActivityType), sortedType));
        return "industryAllReactionList";
    }

    /** Просмотр работ по производству на всех персонажах. */
    @GetMapping(path = "/industry/production/{sortedType}")
    public String getIndustryJobProductionListOnAllCharacter(Model model, @PathVariable("sortedType") String sortedType){
        model.addAttribute("allProductionList",
                getSortedListByActivityType(characterService.findAll(), List.of(productionActivityType), sortedType));
        return "industryAllProductionList";
    }

    /** Просмотр работ по исслдедованию и копированию БПО на всех персонажах. */
    @GetMapping(path = "/industry/bpoJob/{sortedType}")
    public String getIndustryJobBpoListOnAllCharacter(Model model, @PathVariable("sortedType") String sortedType){
        model.addAttribute("allBpoJobList",
                getSortedListByActivityType(
                        characterService.findAll(),
                        List.of(researchMeActivityType, researchPeActivityType, inventionActivityType, copyingActivityType),
                        sortedType));
        return "industryAllBpoJobList";
    }

    private List<CharacterIndustryJobDto> getSortedListByActivityType(
            List<Character> characterList, List<Integer> activityTypeList, String sortedType){
        List<CharacterIndustryJobDto> jobDtoList = new ArrayList<>();

        characterList.forEach(c ->{
            jobDtoList.addAll(characterIndustryDtoMapper.entityListToDtoList(
                    characterIndustryJobService.findByCharacterAndApproved(c, notApproved)));

        });

        return jobDtoList.stream()
                .filter(dto ->
                        activityTypeList.stream()
                                .anyMatch(a -> dto.getActivity().getActivityId().equals(a)))
                .sorted(getComparator(sortedType))
                .collect(Collectors.toList());
    }

    private Comparator<? super CharacterIndustryJobDto> getComparator(String sortedType){
        switch (sortedType) {
            case "byChar":
                return new CharacterIndustryDtoMapper.CharacterComparator();
            case "byProject":
                return new CharacterIndustryDtoMapper.ProjectComparator();
            default:
                return new CharacterIndustryDtoMapper.EndDateJobComparator();
        }
    }
}
