package kr.coding.lets.service;

import kr.coding.lets.model.Roles;

public interface RolesService {
    Roles findByName(String name);
}
