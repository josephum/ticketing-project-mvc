package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList = findAll().stream()
                .filter(projectDTO -> projectDTO.getAssignedManager().equals(manager))
                .map( projectDTO -> {
                    // one goal: build that projectDTO - with AllArgConstructor
                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);
                             int completeTaskCounts= (int) taskList.stream()
                                     .filter(taskDTO -> taskDTO.getProject().equals(projectDTO) &&
                                             taskDTO.getTaskStatus()==Status.COMPLETE).count();

                             int unfinishedTaskCounts= (int) taskList.stream()
                                     .filter(taskDTO -> taskDTO.getProject().equals(projectDTO) &&
                                             taskDTO.getTaskStatus()!=Status.COMPLETE ).count();

                            projectDTO.setCompleteTaskCounts(completeTaskCounts);
                            projectDTO.setUnfinishedTaskCounts(unfinishedTaskCounts);
                            return projectDTO;
                        }
                ).collect(Collectors.toList());



        return projectList;
    }
}
