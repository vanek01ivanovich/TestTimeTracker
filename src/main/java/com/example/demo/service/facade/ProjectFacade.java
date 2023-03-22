package com.example.demo.service.facade;

import com.example.demo.web.dto.ProjectRequestDto;

public interface ProjectFacade {

    void deleteProject(String id);

    void updateProject(String id, ProjectRequestDto projectRequest);

}
