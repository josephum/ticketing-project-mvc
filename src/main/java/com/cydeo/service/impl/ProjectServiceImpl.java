package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {

        if (projectDTO.getProjectStatus()==null){
            projectDTO.setProjectStatus(Status.OPEN);
        }

        return super.save(projectDTO.getProjectCode(),projectDTO);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void update(ProjectDTO object) {

        ProjectDTO newProject = findById(object.getProjectCode());

        if(object.getProjectStatus() == null)
            object.setProjectStatus(newProject.getProjectStatus());
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void completeById(String projectCode) {
        super.findById(projectCode).setProjectStatus(Status.COMPLETE);
    }
}
