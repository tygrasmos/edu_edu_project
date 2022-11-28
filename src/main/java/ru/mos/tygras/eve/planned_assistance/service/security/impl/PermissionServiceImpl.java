package ru.mos.tygras.eve.planned_assistance.service.security.impl;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterIndustryJob;
import ru.mos.tygras.eve.planned_assistance.service.repository.UserService;
import ru.mos.tygras.eve.planned_assistance.service.security.PermissionService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    /** Переопределяем получение последовательности для PostgreSQL */
    private final static String classIdentityQueryValue = "select currval(pg_get_serial_sequence('acl_class', 'id'))";
    private final static String sidIdentityQueryValue = "select currval(pg_get_serial_sequence('acl_sid', 'id'))";

    private final JdbcMutableAclService jdbcMutableAclService;
    private final UserService userService;

    public PermissionServiceImpl(JdbcMutableAclService jdbcMutableAclService,
                                 UserService userService){
        this.jdbcMutableAclService = jdbcMutableAclService;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        jdbcMutableAclService.setSidIdentityQuery(sidIdentityQueryValue);
        jdbcMutableAclService.setClassIdentityQuery(classIdentityQueryValue);
    }

    @Override
    public void addPermissionForUser(Object o){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Sid owner = new PrincipalSid(authentication);

        String role = getGrantedAuthorityRole(authentication.getName());

        final Sid admin = new GrantedAuthoritySid(role == null ? "UNKNOWN" : role);
        MutableAcl acl = jdbcMutableAclService.createAcl(createObjectIdentity(o));

        acl.setOwner(owner);
        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, admin, true);

        jdbcMutableAclService.updateAcl(acl);
    }

    private ObjectIdentity createObjectIdentity(Object o){
        if(o.getClass().equals(Character.class)){
            Character character = (Character) o;
            return new ObjectIdentityImpl(character.getClass(), character.getId());
        } else if(o.getClass().equals(CharacterIndustryJob.class)){
            CharacterIndustryJob job = (CharacterIndustryJob) o;
            return new ObjectIdentityImpl(job.getClass(), job.getId());
        }
        throw new RuntimeException("Ошибка создания правила ACL. Переданный объект не определен");
    }

    private String getGrantedAuthorityRole(String login){
        userService.findUserByLogin(login);
        String[] strings = userService.findUserByLogin(login).getRoles().split(",");
        for(String str : strings){
            if(str.toUpperCase().contains(login.toUpperCase())){
                return str;
            }
        }
        return null;
    }
}
