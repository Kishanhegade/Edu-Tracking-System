package com.jsp.ets.user;

import java.util.EnumSet;
import java.util.Set;

public enum Stack {

	JAVA_FULL_STACK(EnumSet.of(Subject.CORE_JAVA, Subject.SQL, Subject.HIBERNATE, Subject.SPRING, Subject.SPRING_BOOT,Subject.HTML, Subject.CSS, Subject.JAVASCRIPT)),
	MERN_STACK(EnumSet.of(Subject.HTML, Subject.CSS, Subject.JAVASCRIPT, Subject.REACTJS)),
	PYTHON_FULL_STACK(EnumSet.of(Subject.PYTHON,Subject.SQL, Subject.HTML, Subject.CSS,Subject.JAVASCRIPT,Subject.DJANGO));

	private final Set<Subject> subjects;

	Stack(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

}
