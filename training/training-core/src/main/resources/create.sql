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

INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (1, 5);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2001, 2001);

INSERT INTO public.interview(id, company, description, interview_date, interviewed_id, interviewer_id) VALUES (3001, 'Fiction Software', 'Dear Student! We want to invite you to a job interview!', to_date('05 Dec 2045', 'DD Mon YYYY'), 2003, 2004);

INSERT INTO public.feedback(id, detailed_feedback, feedbackdate, is_public, score, interview_id, rated_id, sender_id) VALUES (4001, 'The student was great, he/she will start to work next week.', to_date('06 Dec 2045', 'DD Mon YYYY'), TRUE, 5, 3001, 2003, 2004);

-- Exam-module

INSERT INTO public.exam(id, title) VALUES(2001, 'JPA');
INSERT INTO public.exam(id, title) VALUES(2002, 'EJB');
INSERT INTO public.exam(id, title) VALUES(2003, 'JSF');
INSERT INTO public.exam(id, title) VALUES(2004, 'Macska');


INSERT INTO public.question(id, text, exam_id) VALUES(2001, 'First question for JPA', 2001);
INSERT INTO public.question(id, text, exam_id) VALUES(2002, 'First question for EJB', 2002);
INSERT INTO public.question(id, text, exam_id) VALUES(2003, 'First question for JSF', 2003);
INSERT INTO public.question(id, text, exam_id) VALUES(2004, 'First question for Macska', 2004);
INSERT INTO public.question(id, text, exam_id) VALUES(2005, 'Second question for Macska', 2004);

INSERT INTO public.question_type(id, name) VALUES(1, 'Single');
INSERT INTO public.question_type(id, name) VALUES(2, 'Multiple');
INSERT INTO public.question_type(id, name) VALUES(3, 'Text');


ALTER SEQUENCE hibernate_sequence RESTART WITH 10000;