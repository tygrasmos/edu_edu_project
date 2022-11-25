package ru.mos.tygras.eve.planned_assistance.service.response.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.*;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.eve.industry.IndustryJob;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthorizedRequest;
import ru.mos.tygras.eve.planned_assistance.service.*;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterIndustryJobService;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterTokenService;
import ru.mos.tygras.eve.planned_assistance.service.repository.EveIndustryActivityTypeService;
import ru.mos.tygras.eve.planned_assistance.service.repository.EveTypeService;
import ru.mos.tygras.eve.planned_assistance.service.response.IndustryResponseService;
import ru.mos.tygras.eve.planned_assistance.utils.DateUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class IndustryResponseServiceImpl implements IndustryResponseService {

    private final static String industryIdent = "industry";
    private final static Integer notApproved = 0;

    private final CharacterIndustryJobService characterIndustryJobService;
    private final EveIndustryActivityTypeService activityTypeService;
    private final EveTypeService typeService;
    private final CharacterTokenService characterTokenService;
    private final RequestCreatorService requestCreatorService;
    private final DateUtils dateUtils;
    private final RestTemplateService restTemplateService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    public static final Logger log = LoggerFactory.getLogger(IndustryResponseServiceImpl.class);

    public IndustryResponseServiceImpl(CharacterIndustryJobService characterIndustryJobService,
                                       EveIndustryActivityTypeService activityTypeService,
                                       EveTypeService typeService,
                                       CharacterTokenService characterTokenService,
                                       RequestCreatorService requestCreatorService,
                                       RestTemplateService restTemplateService,
                                       DateUtils dateUtils){
        this.characterIndustryJobService = characterIndustryJobService;
        this.activityTypeService = activityTypeService;
        this.typeService = typeService;
        this.characterTokenService = characterTokenService;
        this.requestCreatorService = requestCreatorService;
        this.restTemplateService = restTemplateService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void saveIndustryJobsResponse(List<IndustryJob> industryJobList, Character character) {
        industryJobList.forEach(job ->{
            CharacterIndustryJob currIndJob = characterIndustryJobService.findByJobId(Long.valueOf(job.getJobId()));
            if(currIndJob == null){
                characterIndustryJobService.save(createIndustryJob(job, character));
            } else {
                currIndJob.setEndDateTime(dateUtils.parseToDate(job.getEndDate()));
                currIndJob.setStatus(job.getStatus());
                currIndJob.setDuration(job.getDuration().longValue());
                characterIndustryJobService.save(currIndJob);
            }
        });
    }

    @Override
    public List<IndustryJob> getIndustryJobListByCharacter(Character character) {
        CharacterToken characterToken = characterTokenService.findByCharacter(character);
        AuthorizedRequest request = requestCreatorService.getAuthorizedRequestParam(characterToken, industryIdent);
        try {
            return Arrays.asList(
                    objectMapper.readValue(
                            restTemplateService.getResponseFromRestApi(request), IndustryJob[].class));
        } catch (JsonProcessingException e){
            log.error(e.getMessage());
        }
        return null;
    }

    /** Создадим новую сущноть - заполним */
    private CharacterIndustryJob createIndustryJob(IndustryJob job, Character character){
        CharacterIndustryJob indJob = new CharacterIndustryJob();
        EveType type = typeService.findByTypeId(job.getBlueprintTypeId());
        EveIndustryActivityType activityType = activityTypeService.findByActivityId(job.getActivityId());
        indJob.setBlueprintType(type);
        indJob.setActivity(activityType);
        indJob.setCharacter(character);
        indJob.setRuns(job.getRuns());
        indJob.setDuration(job.getDuration().longValue());
        indJob.setJobId(job.getJobId().longValue());
        indJob.setStatus(job.getStatus());
        indJob.setStartDateTime(dateUtils.parseToDate(job.getStartDate()));
        indJob.setEndDateTime(dateUtils.parseToDate(job.getEndDate()));
        indJob.setLocationId(job.getOutputLocationId());
        indJob.setStationId(job.getStationId());
        indJob.setApproved(notApproved);
        return indJob;
    }

}
