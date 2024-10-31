-- Field of study
create table fields_of_study(
    name varchar primary key
);

insert into fields_of_study values('Gene-culture coevolution');
insert into fields_of_study values('Evolutionary anthropology');
insert into fields_of_study values('Forensic anthropology');
insert into fields_of_study values('Human behavioral ecology');
insert into fields_of_study values('Human evolution');
insert into fields_of_study values('Medical anthropology');
insert into fields_of_study values('Molecular anthropology');
insert into fields_of_study values('Neuroanthropology');
insert into fields_of_study values('Nutritional anthropology');
insert into fields_of_study values('Paleoanthropology');
insert into fields_of_study values('Population genetics');
insert into fields_of_study values('Primatology');

-- Courses
create table courses(

    id int primary key auto_increment,
    name varchar,
    description varchar,
    hours int,
    create_time datetime not null,
    update_time datetime
);

insert into courses(name, description, hours, create_time) values('Course of Population genetics', 'Population genetics - desc', 100, current_timestamp());
insert into courses(name, description, hours, create_time) values('Course of Neuroanthropology', 'Neuroanthropology - desc', 120, current_timestamp());

-- Lectures
create table lectures(
    id int primary key auto_increment,
    course_id int,
    title varchar,
    field_of_study varchar,
    create_time datetime not null,
    update_time datetime
);

alter table lectures add constraint fk_lecture_course foreign key (course_id) references courses(id);
alter table lectures add constraint fk_lectures_fields_of_study foreign key (field_of_study) references fields_of_study(name);

insert into lectures(course_id, title, field_of_study, create_time) values(1, 'Introduction to Population genetics', 'Population genetics', current_timestamp());
insert into lectures(course_id, title, field_of_study, create_time) values(1, 'Introduction to Population genetics - cont', 'Population genetics', current_timestamp());
insert into lectures(course_id, title, field_of_study, create_time) values(2, 'Introduction to Neuroanthropology', 'Neuroanthropology', current_timestamp());
insert into lectures(course_id, title, field_of_study, create_time) values(2, 'Introduction to Neuroanthropology - cont', 'Neuroanthropology', current_timestamp());

-- Course lecture
create table lectures_courses(

    course_id int not null,
    lecture_id int not null,
    create_time datetime not null,
    update_time datetime,

    primary key (course_id, lecture_id)
);

alter table lectures_courses add constraint fk_courses_lectures_course foreign key (course_id) references courses(id);
alter table lectures_courses add constraint fk_courses_lectures_lectures foreign key (lecture_id) references lectures(id);

insert into lectures_courses(course_id, lecture_id, create_time) values(1, 1, current_timestamp());
insert into lectures_courses(course_id, lecture_id, create_time) values(1, 2, current_timestamp());
insert into lectures_courses(course_id, lecture_id, create_time) values(2, 1, current_timestamp());
insert into lectures_courses(course_id, lecture_id, create_time) values(2, 2, current_timestamp());

-- Course instance
create table course_instances(
    id int primary key auto_increment,
    course_id int not null,
    start_date date not null,
    create_time datetime not null,
    update_time datetime
);

alter table course_instances add constraint fk_course_inst_course_id foreign key (course_id) references courses(id);

insert into course_instances(course_id, start_date, create_time) values(1, '2024-10-15', current_timestamp());
insert into course_instances(course_id, start_date, create_time) values(1, '2025-10-15', current_timestamp());
insert into course_instances(course_id, start_date, create_time) values(2, '2024-10-15', current_timestamp());
insert into course_instances(course_id, start_date, create_time) values(2, '2025-10-15', current_timestamp());

create table course_instance_lecture_hours (

    id int primary key auto_increment,
    course_instance_id int,
    lecture_id int,
    start_time datetime,
    end_time datetime,
    create_time datetime not null,
    update_time datetime
);

alter table course_instance_lecture_hours add constraint fk_course_lecture_hours_course_instance foreign key (course_instance_id) references course_instances(id);
alter table course_instance_lecture_hours add constraint fk_course_lecture_hours_lecture foreign key (lecture_id) references lectures(id);

insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(1, 1, '2024-10-15 14:00:00', '2024-10-15 14:45:00', current_timestamp());
insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(1, 2, '2024-10-15 14:00:00', '2024-10-15 14:45:00', current_timestamp());
insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(2, 1, '2024-10-16 14:00:00', '2024-10-15 14:45:00', current_timestamp());
insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(2, 2, '2024-10-17 14:00:00', '2024-10-15 14:45:00', current_timestamp());
insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(3, 1, '2025-10-15 14:00:00', '2025-10-15 14:45:00', current_timestamp());
insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(2, 2, '2025-10-15 14:00:00', '2025-10-15 14:45:00', current_timestamp());
insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(4, 1, '2025-10-15 14:00:00', '2025-10-15 14:45:00', current_timestamp());
insert into course_instance_lecture_hours(course_instance_id, lecture_id, start_time, end_time, create_time) values(4, 2, '2025-10-15 14:00:00', '2025-10-15 14:45:00', current_timestamp());

-- Students
create table students(

    id int primary key auto_increment,
    first_name varchar not null,
    last_name varchar not null,
    email varchar,
    create_time datetime not null,
    update_time datetime
);

insert into students(first_name, last_name, create_time) values('Siena', 'Newman', current_timestamp());
insert into students(first_name, last_name, create_time) values('Anderson', 'Vincent', current_timestamp());
insert into students(first_name, last_name, create_time) values('Allyson', 'Phillips', current_timestamp());
insert into students(first_name, last_name, create_time) values('Andrew', 'King', current_timestamp());
insert into students(first_name, last_name, create_time) values('Victoria', 'Rosales', current_timestamp());
insert into students(first_name, last_name, create_time) values('Wilder', 'Parks', current_timestamp());
insert into students(first_name, last_name, create_time) values('Ainsley', 'Woods', current_timestamp());
insert into students(first_name, last_name, create_time) values('Zion', 'Williams', current_timestamp());
insert into students(first_name, last_name, create_time) values('Ava', 'Pacheco', current_timestamp());
insert into students(first_name, last_name, create_time) values('Erik', 'Park', current_timestamp());
insert into students(first_name, last_name, create_time) values('Lia', 'Graves', current_timestamp());
insert into students(first_name, last_name, create_time) values('Cesar', 'Berger', current_timestamp());
insert into students(first_name, last_name, create_time) values('Laylah', 'Snow', current_timestamp());

-- Students to courses
create table course_instance_students (

    id int primary key auto_increment,
    course_instance_id int,
    student_id int,
    create_time datetime not null,
    update_time datetime
);

alter table course_instance_students add constraint fk_course_students_course_instance foreign key (course_instance_id) references course_instances(id);
alter table course_instance_students add constraint fk_course_students_student foreign key (student_id) references students(id);

insert into course_instance_students(course_instance_id, student_id, create_time) values(1, 1, current_timestamp());
insert into course_instance_students(course_instance_id, student_id, create_time) values(1, 2, current_timestamp());
insert into course_instance_students(course_instance_id, student_id, create_time) values(2, 3, current_timestamp());
insert into course_instance_students(course_instance_id, student_id, create_time) values(2, 4, current_timestamp());
insert into course_instance_students(course_instance_id, student_id, create_time) values(3, 4, current_timestamp());
insert into course_instance_students(course_instance_id, student_id, create_time) values(3, 6, current_timestamp());
insert into course_instance_students(course_instance_id, student_id, create_time) values(4, 7, current_timestamp());
insert into course_instance_students(course_instance_id, student_id, create_time) values(4, 8, current_timestamp());

commit;

