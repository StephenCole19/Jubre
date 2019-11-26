/* Keeping admin account on their own, because they have nothing in common w/ anyone else */
CREATE TABLE adminAccount
(
	id INT NOT NULL AUTO_INCREMENT;
	username VARCHAR(200) NOT NULL UNIQUE,
	password VARCHAR(200) NOT NULL UNIQUE,
	PRIMARY KEY id
);

CREATE TABLE userAccount
(
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(200) NOT NULL UNIQUE,
	password VARCHAR(200) NOT NULL UNIQUE,
	userRole INT NOT NULL DEFAULT 0,
	PRIMARY KEY id
);

/* Nothing special about a prof, so they don't need a table at all */

CREATE TABLE studentAccount
(
	id INT NOT NULL AUTO_INCREMENT,
	hasUnreadNotifications INT NOT NULL DEFAULT 0,
	userId INT NOT NULL,
	PRIMARY KEY id,
	FOREIGN KEY userId REFERENCES userAccount(id)
);

CREATE TABLE course
(
	id INT NOT NULL AUTO_INCREMENT,
	profId INT NOT NULL,
	/* This is a varchar because course "numbers" are CS1073, BIO1001, etc */
	courseNumber VARCHAR(10) NOT NULL UNIQUE,
	PRIMARY KEY id,
	FOREIGN KEY profId REFERENCES userAccount(id)
);

CREATE TABLE enrollment /*Called CourseRegistration on diagram */
(
	userId INT NOT NULL AUTO_INCREMENT,
	courseId INT NOT NULL AUTO_INCREMENT,
	FOREIGN KEY userId REFERENCES userAccount(id)
	FOREIGN KEY courseId REFERENCES course(id)
);


CREATE TABLE taAccount
(
	id INT NOT NOT NULL AUTO_INCREMENT,
	userId INT NOT NULL,
	courseId INT NOT NULL,
	PRIMARY KEY id,
	FOREIGN KEY userId REFERENCES userAccount(id),
	FOREIGN KEY courseId REFERENCES course(id)
);

CREATE TABLE courseOfferingInfo
(
	id INT NOT NULL AUTO_INCREMENT,
	/* varchar for same reason as above */
	roomNum VARCHAR(10) NOT NULL AUTO_INCREMENT,
	courseId ID NOT NULL,
	/* todo */
	PRIMARY KEY id,
	FOREIGN KEY courseId REFERENCES course(id)
);

CREATE TABLE courseInfo
(
	id NOT NULL AUTO_INCREMENT,
	title VARCHAR(200) NOT NULL,
	body TEXT,
	courseId INT NOT NULL,
	PRIMARY KEY id,
	FOREIGN KEY courseId REFERENCES course(id)
);

CREATE TABLE assignment
(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(200) NOT NULL,
	file BLOB,
	specifications TEXT NOT NULL,
	PRIMARY KEY id
);

CREATE TABLE assignmentSubmission
(
	id INT NOT NULL AUTO_INCREMENT,
	feedbackRead INT NOT NULL DEFAULT 0,
	studentId INT NOT NULL,
	courseId INT NOT NULL,
	PRIMARY KEY id,
	FOREIGN KEY studentID REFERENCES studentAccount(id),
	FOREIGN KEY courseID REFERENCES course(id)
);

CREATE TABLE message
(
	id INT NOT NULL AUTO_INCREMENT,
	sentTimestamp INT NOT NULL,
	message TEXT NOT NULL,
	userFrom INT NOT NULL,
	userTo INT NOT NULL,
	PRIMARY KEY id,
	FOREIGN KEY userFrom REFERENCES userAccount(id),
	FOREIGN KEY userTo REFERENCES userAccount(id)
);

CREATE TABLE notification
(
	id INT NOT NULL AUTO_INCREMENT,
	courseId INT NOT NULL,
	title VARCHAR(50) NOT NULL,
	body TEXT, /* Null allowed */
	PRIMARY KEY id,
	FOREIGN KEY courseId REFERENCES course(id)
);
