package kr.coding.lets.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.coding.lets.model.Roles;
import kr.coding.lets.repository.RolesRepository;
import kr.coding.lets.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Roles findByName(String name) {
        Optional<Roles> result = rolesRepository.findByName(name);
        if(result.isEmpty())return null;
        else return result.get();
    }
    
}
