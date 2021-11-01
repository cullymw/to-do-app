package com.jmuraski.toDoApp.Service;

import com.jmuraski.toDoApp.Model.ToDoItem;
import com.jmuraski.toDoApp.Model.ToDoList;
import com.jmuraski.toDoApp.Repository.ToDoItemRepository;
import com.jmuraski.toDoApp.Repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoItemService {
    private final ToDoItemRepository toDoItemRepository;
    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoItemService(ToDoItemRepository toDoItemRepository, ToDoListRepository toDoListRepository) {
        this.toDoItemRepository = toDoItemRepository;
        this.toDoListRepository = toDoListRepository;
    }

    public List<ToDoItem> getToDoItemsByList(Long listId) {
        //Update "findAll() to correct search
        return toDoItemRepository.findAll();
    }

    public void addToDoItem(Long listId, ToDoItem toDoItem) {
        ToDoList toDoList = toDoListRepository.getById(listId);
        toDoItem.setToDoList(toDoList);
        toDoItemRepository.save(toDoItem);
    }

    public void updateToDoItem(Long id, ToDoItem toDoItem) {
        ToDoItem updatedToDoItem = toDoItemRepository.getById(id);
        updatedToDoItem.setToDo(toDoItem.getToDo());
        toDoItemRepository.save(updatedToDoItem);
    }

    public void removeToDoItem(Long id) {
        toDoItemRepository.deleteById(id);
    }
}
