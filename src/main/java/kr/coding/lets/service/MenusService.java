package kr.coding.lets.service;

import kr.coding.lets.model.Menus;

public interface MenusService {
    Menus findByName(String name);
}
