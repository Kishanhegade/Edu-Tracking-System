package com.jsp.ets.user;

import java.util.Arrays;
import java.util.List;

public enum Stack {

	JAVA_FULL_STACK(Arrays.asList(Subject.CORE_JAVA, Subject.SQL, Subject.HIBERNATE, Subject.SPRING, Subject.SPRING_BOOT,Subject.HTML, Subject.CSS, Subject.JAVASCRIPT)),
	MERN_STACK(Arrays.asList(Subject.HTML, Subject.CSS, Subject.JAVASCRIPT, Subject.REACTJS)),
	PYTHON_FULL_STACK(Arrays.asList(Subject.PYTHON,Subject.SQL, Subject.HTML, Subject.CSS,Subject.JAVASCRIPT,Subject.DJANGO));

	private final List<Subject> subjects;

	Stack(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

}
