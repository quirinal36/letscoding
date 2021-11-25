package kr.coding.lets.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.coding.lets.model.Menus;
import kr.coding.lets.repository.MenusRepository;
import kr.coding.lets.service.MenusService;

@Service
public class MenusServiceImpl implements MenusService{
    @Autowired
    private MenusRepository menusRepository;

    @Override
    public Menus findByName(String name) {
        Optional<Menus> result = menusRepository.findByName(name);
        if(result.isEmpty()) return null;
        else return result.get();
    }
    
}
