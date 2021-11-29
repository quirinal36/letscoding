package kr.coding.lets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.coding.lets.model.Menus;
import kr.coding.lets.model.Roles;
import kr.coding.lets.model.enums.MenuEnum;
import kr.coding.lets.model.enums.UserRole;
import kr.coding.lets.repository.MenusRepository;
import kr.coding.lets.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{
    boolean alreadySetup = false;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private MenusRepository menusRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup)return;
        for(UserRole role: UserRole.values()){
            Roles createdRole = createRoleIfNotFound(new StringBuilder(role.name()).toString());
        }
        createMenuIfNotFound();
        alreadySetup = true;
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

    @Transactional
    List<Menus> createMenuIfNotFound(){
        List<Menus> list = new ArrayList<>();
        for(MenuEnum menu : MenuEnum.values()){
            log.info(menu.toString());
            Optional<Menus> findResult = menusRepository.findByName(menu.getName());
            if(!findResult.isPresent()){
                Menus menus = Menus.builder()
                            .name(menu.getName())
                            .uri(menu.getUri())
                            .build();
                            
                menusRepository.save(menus);
                list.add(menus);
            }
        }
        return list;
    }
}
