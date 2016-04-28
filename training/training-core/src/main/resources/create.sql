INSERT INTO public.role( id, name, roleCode)  VALUES (2001, 'User Group Role','ROLE_UGS');
INSERT INTO public.role( id, name, roleCode)  VALUES (2002, 'Users Role','ROLE_USERS');
INSERT INTO public.role( id, name, roleCode)  VALUES (2003, 'Students Role','ROLE_STUDENT');
INSERT INTO public.rolegroup( id, name)  VALUES (2001,'Admin Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2002,'Instructor Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2003,'Student Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2004,'Observer Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2005,'Guest Role Group');     
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2001, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2002, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2002, 2002);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2003, 2003);

INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2001, 'email@localhost.com', 'Admin', 1, '$2a$10$I4X.U473il3rFqFcxl6UruK5TshrlXs/opqLM0hifX5Jelcm4InTG','admin');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2002, 'email2@localhost.com', 'Instructor', 1, '$2a$10$bRsjrjA9RLw5KLga6NZlouR4K/jK4xrLO6ahPl1SgOMv3dfYof.Ve','instructor');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2003, 'email3@localhost.com', 'Student', 1, '$2a$10$WsPLcPRqMEmhocByrPrq5./ZxBmoqKvtG9WRyKMiRHZ8dR779Uo3O','student');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2004, 'email4@localhost.com', 'Observer', 1, '$2a$10$0DjpO.K9ZF61wSGCltEYMuCxw30ayi.hlkyGakjrQNdpboVFv67yG','observer');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2005, 'email5@localhost.com', 'Guest', 1, '$2a$10$bsK0W.yFGYivJFw1bYfFL.WjKIF6smUfzmUIgIKjOe7jzGMw8Kb56','guest');
INSERT INTO public.usergroup(id, groupname,description,recuser,recdate)  VALUES (2001,'JavaEE 2016 Tavasz 1','JavaEE 2016 Tavasz 1','CREATE SQL',now());  
INSERT INTO public.usergroup(id, groupname,description,recuser,recdate)  VALUES (2002,'JavaEE 2016 Tavasz 2','JavaEE 2016 Tavasz 2','CREATE SQL',now());  

INSERT INTO public.group_to_user(user_id, group_id) VALUES (2001, 2001);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2002, 2002);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2003, 2002);

INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2001, 2001);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2002, 2002);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2002, 2003);

INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2001, 2001);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2002, 2002);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2003, 2003);

-- Supervisor

INSERT INTO event(id, recdate, recuser, date, description, name, place, type) VALUES (4001, current_timestamp, 'SQL_script', to_date('12 08 2016', 'DD MM YYYY'), 'Lorem ipsum dolor sit amet.', 'Spring Security', 'Java EE Training', 'Homework');
INSERT INTO group_to_event(event_id, group_id) VALUES (4001, 2002);
INSERT INTO user_to_event(event_id, group_id) VALUES (4001, 2003);

INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (5001, current_timestamp, 'SQL_script', 'An solum graece urbanitas vix, vis primis commodo at. His an dolore appetere ponderum, eu consul ubique mentitum eam. In nisl numquam vix, est tractatos consulatu at. Ut mel dicat equidem constituto, pri at ferri quodsi, ne stet quando his.', true);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2003, 5001);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2004, 5001);

INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (4001, 5001);

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

INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2007, 'Single Q with 2 options', 1 , 2005, 'First Note');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2008, 'Single Q with 3 options', 1 , 2005, 'Note 2');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2009, 'Multi Q with 2 options', 2 , 2005, 'Atka');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2010, 'Multi Q with 3 options', 2 , 2005, 'There is a cat');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2011, 'Text based Q', 3 , 2005, 'No hope');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2012, 'Another text based Q', 3 , 2005, 'Magic Note');

INSERT INTO public.option(id, correct, text, question_id) VALUES (2001, true, 'Option1', 2007);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2002, false, 'Option2', 2007);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2003, true, 'Option3', 2008);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2004, false, 'Option4', 2008);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2005, false, 'Option5', 2008);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2006, true, 'Option6', 2009);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2007, true, 'Option7', 2009);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2009, true, 'Option8', 2010);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2010, false, 'Option9', 2010);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2011, false, 'Option10', 2010);

INSERT INTO public.option(id, correct, question_id) VALUES (2012, false, 2011);
INSERT INTO public.option(id, correct, question_id) VALUES (2013, false, 2012);



-- Answers

INSERT INTO public.answer(id, user_id, option_id, good) VALUES (1, 2001, 2001, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (2, 2001, 2004, false);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (3, 2001, 2006, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (4, 2001, 2007, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (5, 2001, 2009, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (6, 2001, 2011, false);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (7, 2001, 2012, false);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (8, 2001, 2013, true);

INSERT INTO public.answer_text(id, answer_id, text) VALUES (200, 7, 'Atkaaaaaaaaaaaaaaaaaa');
INSERT INTO public.answer_text(id, answer_id, text) VALUES (201, 8, 'Filitáááááááááán');


-- Answer texts

INSERT INTO public.answer_text(id, answer_id, text) VALUES (10, 4, 'EZ EGY VALASZ PLS OLVASODJ BE LEGYSZI LEGYSZI')


-- Exam - User - Relation
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(100, 2005, 2001)
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(101, 2006, 2001)
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(102, 2006, 2002)

ALTER SEQUENCE hibernate_sequence RESTART WITH 10000;
