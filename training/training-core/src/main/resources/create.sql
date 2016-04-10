INSERT INTO public.role( id, name)  VALUES (1,'ROLE_ADMIN');
INSERT INTO public.role( id, name)  VALUES (2,'ROLE_INSTRUCTOR');
INSERT INTO public.role( id, name)  VALUES (3,'ROLE_STUDENT');
INSERT INTO public.role( id, name)  VALUES (4,'ROLE_OBSERVER');
INSERT INTO public.role( id, name)  VALUES (5,'ROLE_GUEST');
INSERT INTO public."user"( id, password, username) VALUES (1,'$2a$10$I4X.U473il3rFqFcxl6UruK5TshrlXs/opqLM0hifX5Jelcm4InTG','admin');
INSERT INTO public.role_to_user(user_id, role_id) VALUES (1, 1);
