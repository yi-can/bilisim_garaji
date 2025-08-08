package com.example.springcase.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class TeacherClassroomAssignmentRequest {

    @NotNull(message = "TeacherId boş olamaz")
    private UUID teacherId;

    @NotNull(message = "ClassroomId boş olamaz")
    private UUID classroomId;

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }
}

