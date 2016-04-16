INSERT INTO public.role( id, name)  VALUES (1,'ROLE_1');
INSERT INTO public.role( id, name)  VALUES (2,'ROLE_2');
INSERT INTO public.role( id, name)  VALUES (3,'ROLE_3');
INSERT INTO public.role( id, name)  VALUES (4,'ROLE_4');
INSERT INTO public.role( id, name)  VALUES (5,'ROLE_5');
INSERT INTO public.rolegroup( id, name)  VALUES (1,'ROLE_ADMIN');
INSERT INTO public.rolegroup( id, name)  VALUES (2,'ROLE_INSTRUCTOR');
INSERT INTO public.rolegroup( id, name)  VALUES (3,'ROLE_STUDENT');
INSERT INTO public.rolegroup( id, name)  VALUES (4,'ROLE_OBSERVER');
INSERT INTO public.rolegroup( id, name)  VALUES (5,'ROLE_GUEST');     
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (1, 1);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (1, 2);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2, 1);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (3, 3);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (4, 4);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (5, 5);

INSERT INTO public."user"(id, email, fullname, isactive, password, username) VALUES (1, 'email@localhost.com', 'Admin', 1, '$2a$10$I4X.U473il3rFqFcxl6UruK5TshrlXs/opqLM0hifX5Jelcm4InTG','admin');
INSERT INTO public."user"(id, email, fullname, isactive, password, username) VALUES (2, 'email2@localhost.com', 'Instructor', 1, '$2a$10$bRsjrjA9RLw5KLga6NZlouR4K/jK4xrLO6ahPl1SgOMv3dfYof.Ve','instructor');
INSERT INTO public."user"(id, email, fullname, isactive, password, username) VALUES (3, 'email3@localhost.com', 'Student', 1, '$2a$10$WsPLcPRqMEmhocByrPrq5./ZxBmoqKvtG9WRyKMiRHZ8dR779Uo3O','student');
INSERT INTO public."user"(id, email, fullname, isactive, password, username) VALUES (4, 'email4@localhost.com', 'Observer', 1, '$2a$10$0DjpO.K9ZF61wSGCltEYMuCxw30ayi.hlkyGakjrQNdpboVFv67yG','observer');
INSERT INTO public."user"(id, email, fullname, isactive, password, username) VALUES (5, 'email5@localhost.com', 'Guest', 1, '$2a$10$bsK0W.yFGYivJFw1bYfFL.WjKIF6smUfzmUIgIKjOe7jzGMw8Kb56','guest');
INSERT INTO public.usergroup(id, groupname)  VALUES (1,'USERS_1');  
INSERT INTO public.group_to_user(user_id, group_id) VALUES (1, 1);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2, 1);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (3, 1);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (4, 1);

INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (1, 1);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (1, 2);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (1, 3);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (1, 4);

INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (1, 5);




-- Exam-module
INSERT INTO public.question_type(id, name) VALUES(1, 'One answer1');
INSERT INTO public.question_type(id, name) VALUES(2, 'Many answer');
INSERT INTO public.question_type(id, name) VALUES(3, 'Text answer');