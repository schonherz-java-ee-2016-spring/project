INSERT INTO public.role( id, name, roleCode)  VALUES (2001, 'ROLE_1','1');
INSERT INTO public.role( id, name, roleCode)  VALUES (2002,'ROLE_2','2');
INSERT INTO public.role( id, name, roleCode)  VALUES (2003,'ROLE_3','3');
INSERT INTO public.role( id, name, roleCode)  VALUES (2004,'ROLE_4','4');
INSERT INTO public.role( id, name, roleCode)  VALUES (2005,'ROLE_5','5');
INSERT INTO public.rolegroup( id, name)  VALUES (2001,'ROLE_ADMIN');
INSERT INTO public.rolegroup( id, name)  VALUES (2002,'ROLE_INSTRUCTOR');
INSERT INTO public.rolegroup( id, name)  VALUES (2003,'ROLE_STUDENT');
INSERT INTO public.rolegroup( id, name)  VALUES (2004,'ROLE_OBSERVER');
INSERT INTO public.rolegroup( id, name)  VALUES (2005,'ROLE_GUEST');     
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2001, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2001, 2002);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2002, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2003, 2003);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2004, 2004);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2005, 2005);

INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2001, 'email@localhost.com', 'Admin', 1, '$2a$10$I4X.U473il3rFqFcxl6UruK5TshrlXs/opqLM0hifX5Jelcm4InTG','admin');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2002, 'email2@localhost.com', 'Instructor', 1, '$2a$10$bRsjrjA9RLw5KLga6NZlouR4K/jK4xrLO6ahPl1SgOMv3dfYof.Ve','instructor');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2003, 'email3@localhost.com', 'Student', 1, '$2a$10$WsPLcPRqMEmhocByrPrq5./ZxBmoqKvtG9WRyKMiRHZ8dR779Uo3O','student');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2004, 'email4@localhost.com', 'Observer', 1, '$2a$10$0DjpO.K9ZF61wSGCltEYMuCxw30ayi.hlkyGakjrQNdpboVFv67yG','observer');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2005, 'email5@localhost.com', 'Guest', 1, '$2a$10$bsK0W.yFGYivJFw1bYfFL.WjKIF6smUfzmUIgIKjOe7jzGMw8Kb56','guest');
INSERT INTO public.usergroup(id, groupname,description,recuser,recdate)  VALUES (2001,'JavaEE 2016 Tavasz 1','JavaEE 2016 Tavasz 1','CREATE SQL',now());  
INSERT INTO public.usergroup(id, groupname,description,recuser,recdate)  VALUES (2002,'JavaEE 2016 Tavasz 2','JavaEE 2016 Tavasz 2','CREATE SQL',now());  

INSERT INTO public.group_to_user(user_id, group_id) VALUES (2001, 2001);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2002, 2001);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2003, 2001);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2004, 2001);

INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2001, 2001);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2001, 2002);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2001, 2003);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2001, 2004);

INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2001, 2005);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2001, 2001);

INSERT INTO public.interview(id, company, description, interview_date, interviewed_id, interviewer_id) VALUES (3001, 'Fiction Software', 'Dear Student! We want to invite you to a job interview!', to_date('05 Dec 2045', 'DD Mon YYYY'), 2003, 2004);

INSERT INTO public.feedback(id, detailed_feedback, feedbackdate, is_public, score, interview_id, rated_id, sender_id) VALUES (4001, 'The student was great, he/she will start to work next week.', to_date('06 Dec 2045', 'DD Mon YYYY'), TRUE, 5, 3001, 2003, 2004);

-- Exam-module
INSERT INTO public.exam(id, title) VALUES(2001, 'Maven');
INSERT INTO public.exam(id, title) VALUES(2002, 'JPA');
INSERT INTO public.exam(id, title) VALUES(2003, 'EJB');
INSERT INTO public.exam(id, title) VALUES(2004, 'JSF');
INSERT INTO public.exam(id, title) VALUES(2005, 'Kacsa');
INSERT INTO public.exam(id, title) VALUES(2006, 'Filltest');


INSERT INTO public.question_type(id, name) VALUES(1, 'Single');
INSERT INTO public.question_type(id, name) VALUES(2, 'Multiple');
INSERT INTO public.question_type(id, name) VALUES(3, 'Text');

INSERT INTO public.question(id, text, exam_id) VALUES(2001, 'Lorem ipsum dolor. Sit amet dolor vestibulum condimentum lacinia quis?', 2001);
INSERT INTO public.question(id, text, exam_id) VALUES(2002, 'Lorem ipsum dolor sit amet ad. Nulla felis id taciti?', 2002);
INSERT INTO public.question(id, text, exam_id) VALUES(2003, 'Lorem ipsum dolor sit amet metus. Eu ut adipiscing elit tristique facilisis. Dolor malesuada nullam?', 2003);
INSERT INTO public.question(id, text, exam_id) VALUES(2004, 'Lorem ipsum dolor sit amet euismod. Risus amet dui. Amet donec suspendisse proin mollis dolor?', 2004);
INSERT INTO public.question(id, text, exam_id) VALUES(2005, 'Lorem ipsum dolor sit amet pulvinar. Aenean fusce non. Ante orci dolor aliquet eu aliquam?', 2004);
INSERT INTO public.question(id, text, type_id, exam_id) VALUES(2006, 'Question with long options', 1 , 2001);

INSERT INTO public.question(id, text, type_id, exam_id) VALUES(2007, 'Single Q with 2 options', 1 , 2005);
INSERT INTO public.question(id, text, type_id, exam_id) VALUES(2008, 'Single Q with 3 options', 1 , 2005);
INSERT INTO public.question(id, text, type_id, exam_id) VALUES(2009, 'Multi Q with 2 options', 2 , 2005);
INSERT INTO public.question(id, text, type_id, exam_id) VALUES(2010, 'Multi Q with 3 options', 2 , 2005);
INSERT INTO public.question(id, text, type_id, exam_id) VALUES(2011, 'Text based Q', 3 , 2005);
INSERT INTO public.question(id, text, type_id, exam_id) VALUES(2011, 'Another text based Q', 3 , 2005);

INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2001, true, 'Option1', 2007);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2002, false, 'Option2', 2007);

INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2003, true, 'Option3', 2008);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2004, false, 'Option4', 2008);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2005, false, 'Option5', 2008);

INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2006, true, 'Option6', 2009);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2007, false, 'Option7', 2009);

INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2009, true, 'Option8', 2010);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2010, false, 'Option9', 2010);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2011, false, 'Option10', 2010);


INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2011, 'Keremijabeideaszoveget', 2011);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2012, 'justtryout', 2011);



INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2013, true, 'Lorem ipsum dolor sit amet, a sollicitudin, commodo sit. Vitae eu lectus nulla, dictum in mauris, ornare donec vel quisque aenean erat, nullam neque cubilia cubilia vehicula, etiam est ultrices et repudiandae', 2006);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2014, false, 'Lorem ipsum dolor sit amet nec. Elit sollicitudin vel. Fringilla laoreet in cras perspiciatis sociosqu sed et sed a nulla laoreet. Pede maecenas sed. Vestibulum ac proin sem augue eget.', 2006);
INSERT INTO public.option(id, correct, option_text,question_id) VALUES (2015, false, 'Elit ut imperdiet, facilisis praesent, rutrum urna et egestas est sodales ante. Nunc quis curabitur aliquam faucibus', 2006);

























ALTER SEQUENCE hibernate_sequence RESTART WITH 10000;