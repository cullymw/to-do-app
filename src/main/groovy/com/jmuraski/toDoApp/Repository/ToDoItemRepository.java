package com.jmuraski.toDoApp.Repository;

import com.jmuraski.toDoApp.Model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    List<ToDoItem> findByToDoListId(Long id);
}
