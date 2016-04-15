INSERT INTO public.role( id, name)  VALUES (2001,'ROLE_1');
INSERT INTO public.role( id, name)  VALUES (2002,'ROLE_2');
INSERT INTO public.role( id, name)  VALUES (2003,'ROLE_3');
INSERT INTO public.role( id, name)  VALUES (2004,'ROLE_4');
INSERT INTO public.role( id, name)  VALUES (2005,'ROLE_5');
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
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2001,'JavaEE 2016 Tavasz 1','JavaEE 2016 Tavasz 1');  
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2002,'JavaEE 2016 Tavasz 2','JavaEE 2016 Tavasz 2');  
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2003,'JavaEE 2016 Tavasz 3','JavaEE 2016 Tavasz 3');  
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2004,'JavaEE 2016 Tavasz 4','JavaEE 2016 Tavasz 4');  
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2005,'JavaEE 2016 Tavasz 5','JavaEE 2016 Tavasz 5');  
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2006,'JavaEE 2016 Tavasz 6','JavaEE 2016 Tavasz 6');  
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2007,'JavaEE 2016 Tavasz 7','JavaEE 2016 Tavasz 7');  
INSERT INTO public.usergroup(id, groupname,description)  VALUES (2008,'JavaEE 2016 Tavasz 8','JavaEE 2016 Tavasz 8');  

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
