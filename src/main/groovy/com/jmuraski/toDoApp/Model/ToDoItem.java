package com.jmuraski.toDoApp.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "to_do_item")
public class ToDoItem {
    @Id
    @SequenceGenerator(
            name = "todoitem_sequence",
            sequenceName = "todoitem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todoitem_sequence"
    )
    private Long id;
    private String toDo;
    @ManyToOne(targetEntity = ToDoList.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_do_list_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToDoList toDoList;

    public ToDoItem() {
        this.toDo = "Nothing to do.";
    }

    public ToDoItem(String toDo) {
        this.toDo = toDo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", toDo='" + toDo + '\'' +
                ", toDoList=" + toDoList +
                '}';
    }
}
