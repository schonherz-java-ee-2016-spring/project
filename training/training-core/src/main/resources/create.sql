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

INSERT INTO public.interview(id, company, interviewdate) VALUES (3001, 'Fiction Software', to_date('05 Dec 2045', 'DD Mon YYYY'));
INSERT INTO public.interviewed_to_interview(interviewed_id, interview_id) VALUES (2003, 3001);
INSERT INTO public.interviewer_to_interview(interviewer_id, interview_id) VALUES (2004, 3001);

INSERT INTO public.feedback(id, detailed_feedback, is_public, score, recdate) VALUES (5000, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur molestie nibh eget tellus interdum, id vestibulum erat sollicitudin. Nam ut sapien ut neque feugiat pellentesque. Mauris quis pellentesque erat, sit amet elementum dui. Nulla ornare nulla ac dolor tempor, in elementum justo mattis. In ultrices massa eget est finibus scelerisque. Suspendisse laoreet purus ac mauris consequat rutrum. Proin lacinia porttitor felis, consequat congue sapien tristique eu.', TRUE, 5, to_date('06 Dec 2045', 'DD Mon YYYY'));
INSERT INTO public.interview_to_feedback(feedback_id, interview_id) VALUES (3001, 5000);
INSERT INTO public.rated_to_feedback(feedback_id, rated_id) VALUES (5000, 2003);
INSERT INTO public.sender_to_feedback(feedback_id, sender_id) VALUES (5000, 2004);

