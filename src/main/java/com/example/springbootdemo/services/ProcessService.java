package com.example.springbootdemo.services;

import java.util.List;

/**
 * AuthRoleService
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface ProcessService {

    void processAtrasos(List<String> jisUsernames);
}
