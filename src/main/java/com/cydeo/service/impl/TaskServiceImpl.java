package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO object) {

        // If there is no id, put random
        if (object.getId()==null){
            Long maxId = findAll().stream().max(Comparator.comparing(TaskDTO::getId)).get().getId();
            object.setId(maxId+1);
        }

        // If there is no id, put Open
        if (object.getTaskStatus()==null){
            object.setTaskStatus(Status.OPEN);
        }

        // If there is no id, put now
        if (object.getAssignedDate()==null){
            object.setAssignedDate(LocalDate.now());
        }

        return super.save(object.getId(),object);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(TaskDTO object) {

        if (object.getTaskStatus()==null){
            object.setTaskStatus(super.findById(object.getId()).getTaskStatus());
        }

        if (object.getAssignedDate()==null){
            object.setAssignedDate(super.findById(object.getId()).getAssignedDate());
        }


        super.update(object.getId(), object);
    }
}
