package com.jsp.ets.user;

import java.util.List;

public enum Role {
	
	SUPER_ADMIN(List.of("CREATE_USER", "DELETE_USER", "VIEW_ALL")),
    ADMIN(List.of("CREATE_USER", "VIEW_ALL")),
    HR(List.of("VIEW_ALL")),
    TRAINER(List.of("VIEW_STUDENTS")),
    STUDENT(List.of("VIEW_SELF"));

    private final List<String> privileges;

    Role(List<String> privileges) {
        this.privileges = privileges;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

}
