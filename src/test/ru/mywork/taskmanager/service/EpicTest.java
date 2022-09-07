package ru.mywork.taskmanager.service;

import ru.mywork.taskmanager.model.Epic;
import ru.mywork.taskmanager.model.Subtask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static ru.mywork.taskmanager.model.Status.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    private TaskManager manager;
    private Epic epic;

    @BeforeEach
    public void beforeEach() throws IOException {
        manager = Managers.getDefault();
        epic = new Epic("epic", "epic decr");
        manager.addNewEpic(epic);
    }

    @Test
    public void epicWithOutSubTasks() {
        assertEquals(NEW, manager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void epicWithSubTasksStatusNew() {
        Subtask sub = new Subtask("111", "111", epic.getId(), NEW);
        Subtask sub2 = new Subtask("222", "222", epic.getId(), NEW);
        manager.addNewSubTask(sub);
        manager.addNewSubTask(sub2);
        assertEquals(NEW, manager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void epicWithSubTasksStatusDone() {
        Subtask sub = new Subtask("111", "111", epic.getId(), DONE);
        Subtask sub2 = new Subtask("222", "222", epic.getId(), DONE);
        manager.addNewSubTask(sub);
        manager.addNewSubTask(sub2);
        assertEquals(DONE, manager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void epicWithSubTasksStatusNewAndDone() {
        Subtask sub = new Subtask("111", "111", epic.getId(), NEW);
        Subtask sub2 = new Subtask("222", "222", epic.getId(), DONE);
        manager.addNewSubTask(sub);
        manager.addNewSubTask(sub2);
        assertEquals(IN_PROGRESS, manager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void epicWithSubTasksStatusInProgress() {
        Subtask sub = new Subtask("111", "111", epic.getId(), IN_PROGRESS);
        Subtask sub2 = new Subtask("222", "222", epic.getId(), IN_PROGRESS);
        manager.addNewSubTask(sub);
        manager.addNewSubTask(sub2);
        assertEquals(IN_PROGRESS, manager.getEpicById(epic.getId()).getStatus());
    }
}