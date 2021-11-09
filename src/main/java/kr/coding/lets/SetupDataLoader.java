package kr.coding.lets;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.coding.lets.model.Roles;
import kr.coding.lets.model.UserRole;
import kr.coding.lets.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{
    boolean alreadySetup = false;
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup)return;
        log.info("onApplicationEvent");
        for(UserRole role: UserRole.values()){
            Roles createdRole = createRoleIfNotFound(new StringBuilder(role.name()).toString());
            log.info(createdRole.toString());
        }
    }
    
    @Transactional
    Roles createRoleIfNotFound(String name){
        Optional<Roles> findResult = rolesRepository.findByName(name);
        if(!findResult.isPresent()){
            Roles roles = Roles.builder()
                        .name(name).build();
            rolesRepository.save(roles);
            return roles;
        }else{
            return findResult.get();
        }        
    }
}
