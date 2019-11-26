create table AccountType (
  accountTypeId int auto_increment,
  accountTypeName varchar(20) unique not null,
  primary key(accountTypeId) 
);
insert into AccountType (accountTypeName)
values ('student'),
	   ('administration'),
       ('TA'),
       ('professor');


create table Account (
  accountId int auto_increment,
  username varchar(30) unique not null,
  password varchar(200) not null,
  accountType int not null,
  firstName varchar(25) not null,
  lastName varchar(30) not null,
  primary key(accountId),
  foreign key(accountType) references AccountType(accountTypeId)
);
insert into Account (username,password,accountType,firstName,lastName)
values ('root',sha1('password'),'root','root');

create table Course (
  courseId int,
  courseNumber varchar(10) unique not null,
  courseDescription text not null,
  primary key(courseId)
);

CREATE TABLE CourseOfferingInfo
(
    courseOfferingId INT AUTO_INCREMENT,
    /* varchar for same reason as above */
   professorId INT NOT NULL,
   taId INT NOT NULL,
    roomNum VARCHAR(10) NOT NULL,
    courseId INT NOT NULL,
   term VARCHAR(10) NOT NULL,
   year YEAR(4) NOT NULL,
   length INT NOT NULL CHECK (length > 0),
   time INT NOT NULL,
    PRIMARY KEY (courseOfferingId),
    FOREIGN KEY (courseId) REFERENCES Course(courseId),
   FOREIGN KEY (professorId) REFERENCES Account(accountId),
   FOREIGN KEY (taId) REFERENCES Account(accountId)
);

CREATE TABLE CourseInfo (
   courseInfoId INT AUTO_INCREMENT,
   title VARCHAR(200) NOT NULL,
   body TEXT,
   courseId INT NOT NULL,
   PRIMARY KEY (courseInfoId),
   FOREIGN KEY (courseId)
   REFERENCES Course (courseId)
);

create table CourseRegistration (
  accountId int,
  courseOfferingId int not null, 
  primary key(accountId),
  foreign key(accountId) references Account(accountId),
  foreign key(courseOfferingId) references CourseOfferingInfo(courseOfferingId)
);

create table AdminAccount (
  accountId int,
  position varchar(60) not null,
  primary key(accountId),
  foreign key(accountId) references Account(accountId)
);


create table StudentAccount (
  accountId int,
  hasReadNotifications boolean not null default false,
  primary key(accountId),
  foreign key(accountId) references Account(accountId)
);


CREATE TABLE TA_Account
(
    accountId INT,
    email VARCHAR(30),
    PRIMARY KEY (accountId),
    FOREIGN KEY (accountId) REFERENCES Account(accountId)
);


create table ProfessorAccount (
  accountId int,
  faculty varchar(30),
  primary key(accountId),
  foreign key(accountId) references Account(accountId)
);


CREATE TABLE Assignment
(
    assignmentId INT AUTO_INCREMENT,
    courseOfferingId INT NOT NULL,
    assignmentName VARCHAR(200) NOT NULL,
    assignmentFile BLOB,
    dueDate TIMESTAMP NOT NULL,
    PRIMARY KEY (assignmentId),
    FOREIGN KEY (courseOfferingId) REFERENCES CourseOfferingInfo(courseOfferingId)
);

CREATE TABLE AssignmentSubmission
(
    assignmentSubmissionId INT AUTO_INCREMENT,
    feedbackRead BOOLEAN NOT NULL DEFAULT FALSE,
    accountId INT NOT NULL,
    assignmentId INT NOT NULL,
    submissionFile BLOB,
    PRIMARY KEY (assignmentSubmissionId),
    FOREIGN KEY (assignmentId) REFERENCES Assignment(assignmentId),
    FOREIGN KEY (accountId) REFERENCES StudentAccount(accountId)
);

CREATE TABLE Message
(
    messageId INT AUTO_INCREMENT,
    sentTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    messageText TEXT NOT NULL,
    from_accountId INT NOT NULL,
    to_accountId INT NOT NULL,
    PRIMARY KEY(messageId),
    FOREIGN KEY(from_accountId) REFERENCES Account(accountId),
    FOREIGN KEY(to_accountId) REFERENCES Account(accountId)
);

CREATE TABLE Notification
(
    notificationId INT AUTO_INCREMENT,
    courseOfferingId INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    body TEXT, /* Null allowed */
    PRIMARY KEY(notificationId),
    FOREIGN KEY(courseOfferingId) REFERENCES CourseOfferingInfo(courseOfferingId)
);
