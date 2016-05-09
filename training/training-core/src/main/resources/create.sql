INSERT INTO public."role"( id, name, roleCode)VALUES(2001, 'User groups managing','ROLE_UGS');
INSERT INTO public."role"( id, name, roleCode)VALUES(2002, 'User managing','ROLE_USERS');
INSERT INTO public."role"( id, name, roleCode)VALUES(2007, 'Role managing','ROLE_ROLES');
INSERT INTO public."role"( id, name, roleCode)VALUES(2008, 'Role groups managing','ROLE_RGS');
INSERT INTO public."role"( id, name, roleCode)VALUES(2003, 'Feedbacks','ROLE_FEEDBACKS');
INSERT INTO public."role"( id, name, roleCode)VALUES(2004, 'Student','ROLE_STUDENT');
INSERT INTO public."role"( id, name, roleCode)VALUES(2005, 'Instructor','ROLE_INSTRUCTOR');
INSERT INTO public."role"( id, name, roleCode)VALUES(2006, 'Observer Feedbacks','ROLE_WRITE_OBS_FEEDBACK');
INSERT INTO public."role"( id, name, roleCode)VALUES(2009, 'Student Feedbacks','ROLE_WRITE_STUDENT_FEEDBACK');
INSERT INTO public."role"( id, name, roleCode)VALUES(2010, 'Themes','ROLE_THEMEMANAGEMENT');
INSERT INTO public."role"( id, name, roleCode)VALUES(2011, 'Trainings','ROLE_TRAININGS');

INSERT INTO public.rolegroup( id, name)  VALUES (2001,'Admin Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2002,'Instructor Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2003,'Student Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2004,'Observer Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2005,'Guest Role Group');     

INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2001, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2002, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2003, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2007, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2008, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2010, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2011, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2004, 2003);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2005, 2002);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2006, 2004);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2003, 2004);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2009, 2003);


INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2001, 'email@localhost.com', 'Admin', 1, '$2a$10$I4X.U473il3rFqFcxl6UruK5TshrlXs/opqLM0hifX5Jelcm4InTG','admin');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2002, 'email2@localhost.com', 'Instructor', 1, '$2a$10$bRsjrjA9RLw5KLga6NZlouR4K/jK4xrLO6ahPl1SgOMv3dfYof.Ve','instructor');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2003, 'email3@localhost.com', 'Student', 1, '$2a$10$WsPLcPRqMEmhocByrPrq5./ZxBmoqKvtG9WRyKMiRHZ8dR779Uo3O','student');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2004, 'email4@localhost.com', 'Observer', 1, '$2a$10$0DjpO.K9ZF61wSGCltEYMuCxw30ayi.hlkyGakjrQNdpboVFv67yG','observer');
INSERT INTO public."user"( id, email, fullname, isactive, password, username) VALUES (2005, 'email5@localhost.com', 'Guest', 1, '$2a$10$bsK0W.yFGYivJFw1bYfFL.WjKIF6smUfzmUIgIKjOe7jzGMw8Kb56','guest');
INSERT INTO public.usergroup(id, groupname,description,recuser,recdate)  VALUES (2001,'JavaEE 2016 Tavasz 1','JavaEE 2016 Tavasz 1','CREATE SQL',now());  
INSERT INTO public.usergroup(id, groupname,description,recuser,recdate)  VALUES (2002,'JavaEE 2016 Tavasz 2','JavaEE 2016 Tavasz 2','CREATE SQL',now());  

INSERT INTO public.group_to_user(user_id, group_id) VALUES (2001, 2001);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2002, 2002);
--INSERT INTO public.group_to_user(user_id, group_id) VALUES (2003, 2002);

INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2001, 2001);
INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2002, 2002);
--INSERT INTO public.rolegroup_to_usergroup(usergroup_id, rolegroup_id) VALUES (2002, 2003);

INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2001, 2001);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2002, 2002);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2003, 2003);

-- Supervisor

INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2100, current_timestamp, 'CREATE SQL', 'namenyi.janos@gmail.com', 'Naményi János', 1,  '$2a$04$bemW2oiLxHkEkFhW6NctYezV/SJYa8fM8NVKbPLaQQlQAx5cNaNu6', 'namenyijanos');
INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2101, current_timestamp, 'CREATE SQL', 'preznyak@example.com', 'Preznyák László', 1,  '$2a$04$VKC49xvBjIcmnvHWfhvwvO2yKAK7HwgRucFX4cWIH1l5gnC7g8hY2', 'preznyaklaszlo');
INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2102, current_timestamp, 'CREATE SQL', 'bohan@example.com', 'Bohán Márk', 1,  '$2a$04$mlMv4/px78aNm5W6TD.5/Oy1U7bqnBtOz1Vzmusx3js6i/DCaow5q', 'bohanmark');

INSERT INTO public.group_to_user(user_id, group_id) VALUES (2100, 2001);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2101, 2001);
INSERT INTO public.group_to_user(user_id, group_id) VALUES (2102, 2001);

INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2003, 2100);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2003, 2101);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2003, 2102);

INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2200, current_timestamp, 'CREATE SQL', 'pelsoczi@example.com', 'Pelsoczi János Pál', 1,  '$2a$04$Er0QAIFuaaNSINBi7dgMbOT8hEzA5wlDdIByggqxWVTG13dTH.GiW', 'pelsoczijanos');
INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2201, current_timestamp, 'CREATE SQL', 'farkas@example.com', 'Farkas László', 1,  '$2a$04$HJr9TmEawDfHgBgwxieueuZWMsXfrTYOe.PKJashPHJhh.6rTTDru', 'farkaslaszlo');
INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2202, current_timestamp, 'CREATE SQL', 'zelei@example.com', 'Zelei Attila', 1,  '$2a$04$p61zGM70P7NuCMtxDquusO1rHQC.q4IMEFx6/9PStDTBUMSNlTwn6', 'zeleiattila');
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2002, 2200);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2002, 2201);
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2002, 2202);

INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2300, current_timestamp, 'CREATE SQL', 'szentmiklosi@example.com', 'Szentmiklósi Anikó', 1,  '$2a$04$p61zGM70P7NuCMtxDquusO1rHQC.q4IMEFx6/9PStDTBUMSNlTwn6', 'szentmiklosianiko');
INSERT INTO public.rolegroup_to_user(rolegroup_id, user_id) VALUES (2004, 2300);

-- interview PL
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3100, current_timestamp, 'CREATE SQL', to_date('2016 06 19', 'yyyy MM dd'), 'Állásinterjú', 'Java EE Junior Developer',  'Neuron Software', 'Job Interview');
-- interview BM
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3101, current_timestamp, 'CREATE SQL', to_date('2016 06 22', 'yyyy MM dd'), 'Állásinterjú', 'Java EE Junior Developer',  'Survey Sampling International', 'Job Interview');

INSERT INTO user_to_event(event_id, user_id) VALUES (3100, 2101);
INSERT INTO user_to_event(event_id, user_id) VALUES (3101, 2102);
INSERT INTO user_to_event(event_id, user_id) VALUES (3100, 2300);
INSERT INTO user_to_event(event_id, user_id) VALUES (3101, 2300);

INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3200, current_timestamp, 'CREATE SQL', to_date('2016 05 19', 'yyyy MM dd'), 'Házifeladat', 'Verzió kezelés',  'GitHUB "homework" repository', 'Homework');
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3201, current_timestamp, 'CREATE SQL', to_date('2016 05 26', 'yyyy MM dd'), 'Házifeladat', 'Fejesztői eszközök',  'GitHUB "homework" repository', 'Homework');
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3202, current_timestamp, 'CREATE SQL', to_date('2016 06 01', 'yyyy MM dd'), 'Házifeladat', 'Java alapok',  'GitHUB "homework" repository', 'Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3203, current_timestamp, 'CREATE SQL', to_date('2016 05 20', 'yyyy MM dd'), 'Házifeladat','Objektum orientált design','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3204, current_timestamp, 'CREATE SQL', to_date('2016 05 21', 'yyyy MM dd'), 'Házifeladat','Maven','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3205, current_timestamp, 'CREATE SQL', to_date('2016 05 22', 'yyyy MM dd'), 'Házifeladat','Web Előismeretek','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3206, current_timestamp, 'CREATE SQL', to_date('2016 05 23', 'yyyy MM dd'), 'Házifeladat','Servlet API','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3207, current_timestamp, 'CREATE SQL', to_date('2016 04 18', 'yyyy MM dd'), 'Házifeladat','SQL','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3208, current_timestamp, 'CREATE SQL', to_date('2016 05 11', 'yyyy MM dd'), 'Házifeladat','JDBC','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3209, current_timestamp, 'CREATE SQL', to_date('2016 05 25', 'yyyy MM dd'), 'Házifeladat','Multitier architecture','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3210, current_timestamp, 'CREATE SQL', to_date('2016 04 03', 'yyyy MM dd'), 'Házifeladat','Spring','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3211, current_timestamp, 'CREATE SQL', to_date('2016 04 24', 'yyyy MM dd'), 'Házifeladat','Security','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3212, current_timestamp, 'CREATE SQL', to_date('2016 04 30', 'yyyy MM dd'), 'Házifeladat','JPA','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3213, current_timestamp, 'CREATE SQL', to_date('2016 04 27', 'yyyy MM dd'), 'Házifeladat','JEE Alapismeretek','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3214, current_timestamp, 'CREATE SQL', to_date('2016 05 20', 'yyyy MM dd'), 'Házifeladat','JSF','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3215, current_timestamp, 'CREATE SQL', to_date('2016 05 27', 'yyyy MM dd'), 'Házifeladat','EJB','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3216, current_timestamp, 'CREATE SQL', to_date('2016 04 11', 'yyyy MM dd'), 'Házifeladat','Webservice','GitHUB "homework" repository','Homework');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3217, current_timestamp, 'CREATE SQL', to_date('2016 05 14', 'yyyy MM dd'), 'Házifeladat','Fejlesztési módszertanok','GitHUB "homework" repository','Homework');


INSERT INTO group_to_event(event_id, group_id) VALUES (3200, 2001);
INSERT INTO group_to_event(event_id, group_id) VALUES (3201, 2001);
INSERT INTO group_to_event(event_id, group_id) VALUES (3202, 2001);

INSERT INTO user_to_event(event_id, user_id) VALUES (3200, 2100);
INSERT INTO user_to_event(event_id, user_id) VALUES (3200, 2101);
INSERT INTO user_to_event(event_id, user_id) VALUES (3200, 2102);
INSERT INTO user_to_event(event_id, user_id) VALUES (3200, 2200);

INSERT INTO user_to_event(event_id, user_id) VALUES (3201, 2100);
INSERT INTO user_to_event(event_id, user_id) VALUES (3201, 2101);
INSERT INTO user_to_event(event_id, user_id) VALUES (3201, 2102);
INSERT INTO user_to_event(event_id, user_id) VALUES (3201, 2201);

INSERT INTO user_to_event(event_id, user_id) VALUES (3202, 2100);
INSERT INTO user_to_event(event_id, user_id) VALUES (3202, 2101);
INSERT INTO user_to_event(event_id, user_id) VALUES (3202, 2102);
INSERT INTO user_to_event(event_id, user_id) VALUES (3202, 2202);

-- job interview PL
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4100, current_timestamp, 'CREATE SQL', 'Kedves Preznyák László! Köszönöm, hogy a 2016.06.19-ei interjún  (Java EE Junior Developer pozíció) megjelentél. Az alábbiakban néhány észrevételt írok le, hogy visszajelzést kaphass az interjún való szerepléseddel kapcsolatban. Szakmai tudásod elbeszélgetésünk alapján megfelelőnek bizonyult a legtöbb témakörben. Kérdéseimre a beszélgetés során érintett témákban kielégítő válaszokat adtál a legtöbb esetben, és helytelen válasz esetén sem hibáztal nagyot. A rövid angol nyelvű elbeszélgetés alapján szóbeli angol nyelvtudásodat középszintűnek ítéltem meg, írásbeli nyelvtudásod a bemeneti teszt alapján felső-középszintű. Életrajzod és az interjún nyújtott teljesítményed alapján megfelesz a pozíció betöltésére, jelentkezésedet továbbítottuk a Neuron Software felé. A Neuron Software-től előreláthatólag egy héten belül kapsz értesítést. Üdvözlettel, Schönherz Iskolaszövetkezet csapata!', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3100, 4100);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2101, 4100);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2300, 4100);
-- job interview BM
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4101, current_timestamp, 'CREATE SQL', 'Kedves Bohán Márk! Köszönöm, hogy a 2016.06.22-ei interjún (Java EE Junior Developer pozíció) megjelentél. Az alábbiakban néhány észrevételt írok le, hogy visszajelzést kaphass az interjún való szerepléseddel kapcsolatban. Szakmai tudásod elbeszélgetésünk alapján megfelelőnek bizonyult a legtöbb témakörben. Kérdéseimre a beszélgetés során érintett témákban kielégítő válaszokat adtál a legtöbb esetben, és helytelen válasz esetén sem hibáztal nagyot. A rövid angol nyelvű elbeszélgetés alapján szóbeli angol nyelvtudásodat felső-középszintűnek ítéltem meg, írásbeli nyelvtudásod a bemeneti teszt alapjánközépszintű. Életrajzod és az interjún nyújtott teljesítményed alapján megfelesz a pozíció betöltésére, jelentkezésedet továbbítottuk a Survey Sampling International felé. A Survey Sampling International-től előreláthatólag egy héten belül kapsz értesítést. Üdvözlettel, Schönherz Iskolaszövetkezet csapata!', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3101, 4101);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2102, 4101);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2300, 4101);
-- homework OOD NJ
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4102, current_timestamp, 'CREATE SQL', 'Feladat: A képzésben résztvevő hallgató kiválaszt három tetszóleges tervezési mintát (egy létrehozási, egy struktúrális és egy viselkedési minta; kivéve Singleton), amelyeket áttanulmányoz és implementál egy szabadon választott funkcionalitású alkalmazásban (amely nem egyezik meg a mintakóddal). Valamint JUnit tesztet ír az alkalmazás teszteléséhez. Értékelés: A házifeladat nem érkezett be sem határidőre, sem az értékelés írásának pillanatáig.', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3200, 4102);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2100, 4102);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2200, 4102);
-- homework OOD PL
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4103, current_timestamp, 'CREATE SQL', 'Feladat: A képzésben résztvevő hallgató kiválaszt három tetszóleges tervezési mintát (egy létrehozási, egy struktúrális és egy viselkedési minta; kivéve Singleton), amelyeket áttanulmányoz és implementál egy szabadon választott funkcionalitású alkalmazásban (amely nem egyezik meg a mintakóddal). Valamint JUnit tesztet ír az alkalmazás teszteléséhez. Értékelés: Az alkalmazás lefordul és működik. JUnit teszt nem érkezett be az alkalmazással.', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3200, 4103);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2101, 4103);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2200, 4103);
-- homework OOD BM
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4104, current_timestamp, 'CREATE SQL', 'Feladat: A képzésben résztvevő hallgató kiválaszt három tetszóleges tervezési mintát (egy létrehozási, egy struktúrális és egy viselkedési minta; kivéve Singleton), amelyeket áttanulmányoz és implementál egy szabadon választott funkcionalitású alkalmazásban (amely nem egyezik meg a mintakóddal). Valamint JUnit tesztet ír az alkalmazás teszteléséhez. Értékelés: Az alkalmazás lefordul és működik. A JUnit teszt lefut. A választott viselkedési minta majdnem teljes mértékben megegyezik a példakódban szereplővel.', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3200, 4104);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2102, 4104);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2200, 4104);
-- homework Spring NJ
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4105, current_timestamp, 'CREATE SQL', 'Feedback message comes here', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3201, 4105);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2100, 4105);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2201, 4105);
-- homework Spring PL
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4106, current_timestamp, 'CREATE SQL', 'Feedback message comes here', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3201, 4106);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2101, 4106);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2201, 4106);
-- homework Spring BM
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4107, current_timestamp, 'CREATE SQL', 'Feedback message comes here', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3201, 4107);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2102, 4107);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2201, 4107);
-- homework JSF NJ
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4108, current_timestamp, 'CREATE SQL', 'Feedback message comes here', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3202, 4108);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2100, 4108);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2202, 4108);
-- homework JSF PL
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4109, current_timestamp, 'CREATE SQL', 'Feedback message comes here', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3202, 4109);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2101, 4109);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2202, 4109);
-- homework JSF BM
INSERT INTO feedback(id, recdate, recuser, feedback_message, is_public) VALUES (4110, current_timestamp, 'CREATE SQL', 'Feedback message comes here', TRUE);
INSERT INTO event_to_feedback(event_id, feedback_id) VALUES (3202, 4110);
INSERT INTO rated_to_feedback(rated_id, feedback_id) VALUES (2102, 4110);
INSERT INTO sender_to_feedback(sender_id, feedback_id) VALUES (2202, 4110);

-- ///////////
-- Exam module
-- ///////////

-- Exam QuestionType Enumerations (DO NOT MODIFY)

INSERT INTO public.question_type(id, name) VALUES(1, 'Single');
INSERT INTO public.question_type(id, name) VALUES(2, 'Multiple');
INSERT INTO public.question_type(id, name) VALUES(3, 'Text');

-- Test Exams

INSERT INTO public.exam(id, title) VALUES(2001, 'Verzió kezelés');

-- Test Questions

INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(3001, 'Melyik nem tartozik a Maven tulajdonsagai koze?', 1, 2001, 'Elso kerdes');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(3002, 'Melyik nem igaz a Repository-kra?', 1, 2001, 'Masodik kerdes');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(3003, 'Melyik ervenyes fuggoseg hataskor (Dependency Scope) megallapitas az alabbiak kozul?', 1, 2001, 'Harmadik kerdes');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(3004, 'Melyik igaz a Maven verziokezelesere?', 1, 2001, 'Negyedik kerdes');

-- Test Options

INSERT INTO public.option(id, correct, text, question_id) VALUES (4001, true, 'Szoftver projektek menedzselese', 3001);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4002, false, 'Build folyamatok automatizalasa', 3001);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4003, false, 'Csak meglevo projektekhez kapcsolhato', 3001);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4004, false, 'Plugin alapu architektura', 3001);

INSERT INTO public.option(id, correct, text, question_id) VALUES (4005, false, 'Kesz termekek tarolasa', 3002);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4006, true, 'SNAPSHOT vegzodesu fuggosegek csak a verzioszam valtozasaval toltodnek le', 3002);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4007, false, 'A helyi es tavoli repository-k felepitese azonos', 3002);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4008, false, 'SNAPSHOT vegzodesu fuggosegek letoltodhetnek a verzioszam valtozasa nelkul ugy, hogy a valtozat ujabb', 3002);

INSERT INTO public.option(id, correct, text, question_id) VALUES (4009, false, 'Provided Scope az alapertelmezett hataskor, ha nem adunk meg', 3003);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4010, false, 'Runtime Scope eseten a fuggosegre szukseg van a forditas es futtatas soran is', 3003);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4011, true, 'Test Scope eseten a fuggoseg csak a tesztek szamara kell', 3003);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4012, false, 'System Scope eseten a fuggoseg helyet explicit nem kell megadni', 3003);

INSERT INTO public.option(id, correct, text, question_id) VALUES (4013, false, 'Csak konkret verzioszamot lehet megadni', 3004);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4014, false, 'Csak verzioszamok egy tartomanyat lehet megadni', 3004);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4015, false, 'A verzioszamokat nem lehet megadni, a maven implicit kezeli', 3004);
INSERT INTO public.option(id, correct, text, question_id) VALUES (4016, true, 'Konkret verzioszamot es verzioszamok tartomanyat is meg lehet adni', 3004);




-- Exam-module

INSERT INTO public.exam(id, title) VALUES(2002, 'Fejesztői eszközök');
INSERT INTO public.exam(id, title) VALUES(2003, 'Java alapok');
INSERT INTO public.exam(id, title) VALUES(2004, 'Objektum orientált design');
INSERT INTO public.exam(id, title) VALUES(2005, 'Maven');
INSERT INTO public.exam(id, title) VALUES(2006, 'Web Előismeretek');
INSERT INTO public.exam(id, title) VALUES (2007, 'Servlet API');
INSERT INTO public.exam(id, title) VALUES (2008, 'SQL');
INSERT INTO public.exam(id, title) VALUES (2009, 'JDBC');
INSERT INTO public.exam(id, title) VALUES (2010, 'Multitier architecture');
INSERT INTO public.exam(id, title) VALUES (2011, 'Spring');
INSERT INTO public.exam(id, title) VALUES (2012, 'Security');
INSERT INTO public.exam(id, title) VALUES (2013, 'JPA');
INSERT INTO public.exam(id, title) VALUES (2014, 'JEE Alapismeretek');
INSERT INTO public.exam(id, title) VALUES (2015, 'JSF');
INSERT INTO public.exam(id, title) VALUES (2016, 'EJB');
INSERT INTO public.exam(id, title) VALUES (2017, 'Webservice');
INSERT INTO public.exam(id, title) VALUES (2018, 'Fejlesztési módszertanok');





INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2007, 'Single Q 1 jo és válasszal jelölés alapjan is(forditva)', 1 , 2005, 'First Note');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2008, 'Single Q 1 jo és válasszal jelölés alapjan is', 1 , 2005, 'Note 2');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2009, 'Multi Q Ahol egyik válasz se jo és nincs bejlölve semmi', 2 , 2005, 'Atka');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2010, 'Multi Q Ahol kettő helyes és 1 rossz, csak a jok vannak jelölve', 2 , 2005, 'There is a cat');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2011, 'Multi Q Ahol kettő helyes és 1 rossz, csak az első van bejelölve', 2 , 2005, 'There is a cat');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2012, 'Multi Q Ahol 1 helyes, bevan jelölve az + 1 rossz', 2 , 2005, 'There is a cat');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2013, 'Multi Q Ahol nincs jo válasz, de kettő van jelölve(first,third)', 2 , 2005, 'There is a cat');

INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2020, 'TextBased ahol nincs még kiértékelve a válasz', 3 , 2005, 'There is a cat');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2021, 'TextBased ahol jo a válasz', 3 , 2005, 'There is a cat');
INSERT INTO public.question(id, text, type_id, exam_id, note) VALUES(2022, 'TextBased ahol rossz a válasz', 3 , 2005, 'There is a cat');




INSERT INTO public.option(id, correct, text, question_id) VALUES (2001, true, 'true1', 2007);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2002, false, 'false1', 2007);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2003, false, 'false1', 2008);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2004, true, 'true', 2008);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2005, false, 'false2', 2008);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2006, false, 'false1', 2009);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2007, false, 'false2', 2009);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2008, false, 'false3', 2009);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2009, false, 'false4', 2009);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2010, false, 'false4', 2009);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2011, true, 'true1', 2010);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2012, false, 'false1', 2010);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2013, true, 'true2', 2010);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2014, true, 'true1', 2011);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2015, false, 'false1', 2011);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2016, true, 'true2', 2011);


INSERT INTO public.option(id, correct, text, question_id) VALUES (2017, true, 'true1-bejelölt', 2012);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2018, false, 'false1', 2012);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2019, false, 'bejlelölt-false', 2012);

INSERT INTO public.option(id, correct, text, question_id) VALUES (2020, false, 'false-bejelölt', 2013);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2021, false, 'false1', 2013);
INSERT INTO public.option(id, correct, text, question_id) VALUES (2022, false, 'bejlelölt-false', 2013);



-- TextBased
INSERT INTO public.option(id, correct, question_id) VALUES (2025, null, 2020);
INSERT INTO public.option(id, correct, question_id) VALUES (2026, true, 2021);
INSERT INTO public.option(id, correct, question_id) VALUES (2027, false, 2022);


-- Exam - User - Relation
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(102, 2006, 2002)
--INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(103, 2005, 2003)
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(104, 2006, 2003)

-- ExamResults

-- Namenyi
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5000, current_timestamp, 'CREATE SQL', 9, 2001, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5001, current_timestamp, 'CREATE SQL', 6, 2002, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5002, current_timestamp, 'CREATE SQL', 11, 2003, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5003, current_timestamp, 'CREATE SQL', 10, 2004, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5004, current_timestamp, 'CREATE SQL', 7, 2005, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5005, current_timestamp, 'CREATE SQL', 6, 2006, 2100);
-------------------------------------------------------------------
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5006, current_timestamp, 'CREATE SQL', 3, 2007, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5007, current_timestamp, 'CREATE SQL', 4, 2008, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5008, current_timestamp, 'CREATE SQL', 10, 2009, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5009, current_timestamp, 'CREATE SQL', 7, 2010, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5010, current_timestamp, 'CREATE SQL', 6, 2011, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5011, current_timestamp, 'CREATE SQL', 4, 2012, 2100);
------------------------------------------------------------------
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5012, current_timestamp, 'CREATE SQL', 9, 2013, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5013, current_timestamp, 'CREATE SQL', 8, 2014, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5014, current_timestamp, 'CREATE SQL', 5, 2015, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5015, current_timestamp, 'CREATE SQL', 8, 2016, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5016, current_timestamp, 'CREATE SQL', 6, 2017, 2100);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5017, current_timestamp, 'CREATE SQL', 10, 2018, 2100);

--Preznyak
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5018, current_timestamp, 'CREATE SQL', 9, 2001, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5019, current_timestamp, 'CREATE SQL', 5, 2002, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5020, current_timestamp, 'CREATE SQL', 12, 2003, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5021, current_timestamp, 'CREATE SQL', 10, 2004, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5022, current_timestamp, 'CREATE SQL', 5, 2005, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5023, current_timestamp, 'CREATE SQL', 5, 2006, 2101);
-------------------------------------------------------------------
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5024, current_timestamp, 'CREATE SQL', 9, 2007, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5025, current_timestamp, 'CREATE SQL', 7, 2008, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5026, current_timestamp, 'CREATE SQL', 7, 2009, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5027, current_timestamp, 'CREATE SQL', 7, 2010, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5028, current_timestamp, 'CREATE SQL', 2, 2011, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5029, current_timestamp, 'CREATE SQL', 4, 2012, 2101);
------------------------------------------------------------------
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5030, current_timestamp, 'CREATE SQL', 9, 2013, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5031, current_timestamp, 'CREATE SQL', 8, 2014, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5032, current_timestamp, 'CREATE SQL', 5, 2015, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5033, current_timestamp, 'CREATE SQL', 8, 2016, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5034, current_timestamp, 'CREATE SQL', 6, 2017, 2101);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5035, current_timestamp, 'CREATE SQL', 10, 2018, 2101);

--Bohan
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5036, current_timestamp, 'CREATE SQL', 10, 2001, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5037, current_timestamp, 'CREATE SQL', 9, 2002, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5038, current_timestamp, 'CREATE SQL', 13, 2003, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5039, current_timestamp, 'CREATE SQL', 6, 2004, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5040, current_timestamp, 'CREATE SQL', 7, 2005, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5041, current_timestamp, 'CREATE SQL', 6, 2006, 2102);
-------------------------------------------------------------------
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5042, current_timestamp, 'CREATE SQL', 9, 2007, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5043, current_timestamp, 'CREATE SQL', 9, 2008, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5044, current_timestamp, 'CREATE SQL', 10, 2009, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5045, current_timestamp, 'CREATE SQL', 2, 2010, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5046, current_timestamp, 'CREATE SQL', 6, 2011, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5047, current_timestamp, 'CREATE SQL', 4, 2012, 2102);
------------------------------------------------------------------
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5048, current_timestamp, 'CREATE SQL', 9, 2013, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5049, current_timestamp, 'CREATE SQL', 8, 2014, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5050, current_timestamp, 'CREATE SQL', 5, 2015, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5051, current_timestamp, 'CREATE SQL', 8, 2016, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5052, current_timestamp, 'CREATE SQL', 6, 2017, 2102);
INSERT INTO public.exam_result(id, recdate, recuser, points, exam_id, user_id) VALUES (5053, current_timestamp, 'CREATE SQL', 10, 2018, 2102);

--Trainings

INSERT INTO public.training(id, recdate, recuser, beginning, description, enddate, name) VALUES (7000, current_timestamp, 'CREATE SQL', to_date('2016 02 22', 'yyyy MM dd'), 'Java Training for students', to_date('2016 05 22', 'yyyy MM dd'), 'Schönherz Java EE Training 2016 Spring');
INSERT INTO public.training(id, recdate, recuser, beginning, description, enddate, name) VALUES (7001, current_timestamp, 'CREATE SQL', to_date('2016 06 22', 'yyyy MM dd'), 'Java Training for students', to_date('2016 09 22', 'yyyy MM dd'), 'Schönherz Java EE Training 2016 Summer');
INSERT INTO public.training(id, recdate, recuser, beginning, description, enddate, name) VALUES (7002, current_timestamp, 'CREATE SQL', to_date('2016 09 22', 'yyyy MM dd'), 'Java Training for students', to_date('2016 12 22', 'yyyy MM dd'), 'Schönherz Java EE Training 2016 Autumn');


-- Homeworks

INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5100, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Tortoise Git funkciók kipróbálása.', 10, 'Verzió kezelés');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5101, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Eclipse testreszabása, Gyorsgombok beállítása.', 10, 'Fejesztői eszközök');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5102, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Ismétlés. Java kódok készítése.', 10, 'Java alapok');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5103, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Példaprogramok írása.', 10, 'Objektum orientált design');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5104, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Egy saját maven projekt elkészítése.', 10, 'Maven');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5105, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'HTML&CSS átnézése, használatuk a gyakorlatban', 10, 'Web Előismeretek');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5106, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Saját servlet létrehozása.', 10, 'Servlet API');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5107, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'SQL ismétlés. Tölts fel egy adatbázist egy autóalkatrész kereskedő cég számára szükséges táblákkal, adatokkal.', 10, 'SQL');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5108, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'JDBC használata az órán vett kódban.', 10, 'JDBC');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5109, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Készítsd el a saját többrétegű architektúrával ellátott projektedet.', 10, 'Multitier architecture');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5110, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Az órai feladat befejezése.', 10, 'Spring');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5111, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'RSA kulcs generálás.', 10, 'Security');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5112, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'JPQL használata gyakorlatban.', 10, 'JPA');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5113, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), '', 10, 'JEE Alapismeretek');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5114, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'JSF login képernyő elkészítése.', 10, 'JSF');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5115, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), '', 10, 'EJB');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5116, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Description', 10, 'Webservice');
INSERT INTO public.homework(id, recdate, recuser, deadline_date, description, maximumscore, title) VALUES (5117, current_timestamp, 'CREATE SQL', to_date('2016 05 19','yyyy MM dd'), 'Projekt vázának elkészítése.', 10, 'Fejlesztési módszertanok');

--Themes
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7100, current_timestamp, 'CREATE SQL', 4, 'Verziókezelés');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7101, current_timestamp, 'CREATE SQL', 4, 'Fejesztői eszközök');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7102, current_timestamp, 'CREATE SQL', 16, 'Java alapok');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7103, current_timestamp, 'CREATE SQL', 4, 'Objektum orientált design');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7104, current_timestamp, 'CREATE SQL', 4, 'Maven');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7105, current_timestamp, 'CREATE SQL', 4, 'Web Előismeretek');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7106, current_timestamp, 'CREATE SQL', 8, 'Servlet API');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7107, current_timestamp, 'CREATE SQL', 8, 'SQL');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7108, current_timestamp, 'CREATE SQL', 4, 'JDBC');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7109, current_timestamp, 'CREATE SQL', 2, 'Multitier architecture');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7110, current_timestamp, 'CREATE SQL', 8, 'Spring');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7111, current_timestamp, 'CREATE SQL', 4, 'Security');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7112, current_timestamp, 'CREATE SQL', 8, 'JPA');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7113, current_timestamp, 'CREATE SQL', 4, 'JEE Alapismeretek');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7114, current_timestamp, 'CREATE SQL', 12, 'JSF');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7115, current_timestamp, 'CREATE SQL', 12, 'EJB');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7116, current_timestamp, 'CREATE SQL', 4, 'Webservice');
INSERT INTO public.theme(id, recdate, recuser, hours, name) VALUES (7117, current_timestamp, 'CREATE SQL', 4, 'Fejlesztési módszertanok');

--Themes to training

INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7100);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7101);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7102);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7103);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7104);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7105);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7106);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7107);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7108);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7109);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7110);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7111);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7112);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7113);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7114);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7115);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7116);
INSERT INTO public.theme_to_training(training_id, theme_id) VALUES (7000, 7117);

--Exam events

INSERT INTO public.event(id, recdate, recuser, date, description, name,  place, type) VALUES (3300, current_timestamp, 'CREATE SQL', to_date('2016 05 19', 'yyyy MM dd'), 'Teszt', 'Verzió kezelés',  'Debrecen, Böszörményi út 68. H-4032 - Hungary', 'Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name,  place, type) VALUES (3301, current_timestamp, 'CREATE SQL', to_date('2016 05 26', 'yyyy MM dd'), 'Teszt', 'Fejesztői eszközök',  'Debrecen, Böszörményi út 68. H-4032 - Hungary', 'Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name,  place, type) VALUES (3302, current_timestamp, 'CREATE SQL', to_date('2016 06 01', 'yyyy MM dd'), 'Teszt', 'Java alapok',  'Debrecen, Böszörményi út 68. H-4032 - Hungary', 'Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3303, current_timestamp, 'CREATE SQL', to_date('2016 05 20', 'yyyy MM dd'), 'Teszt','Objektum orientált design','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3304, current_timestamp, 'CREATE SQL', to_date('2016 05 21', 'yyyy MM dd'), 'Teszt','Maven','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3305, current_timestamp, 'CREATE SQL', to_date('2016 05 22', 'yyyy MM dd'), 'Teszt','Web Előismeretek','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3306, current_timestamp, 'CREATE SQL', to_date('2016 05 23', 'yyyy MM dd'), 'Teszt','Servlet API','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3307, current_timestamp, 'CREATE SQL', to_date('2016 04 18', 'yyyy MM dd'), 'Teszt','SQL','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3308, current_timestamp, 'CREATE SQL', to_date('2016 05 11', 'yyyy MM dd'), 'Teszt','JDBC','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3309, current_timestamp, 'CREATE SQL', to_date('2016 05 25', 'yyyy MM dd'), 'Teszt','Multitier architecture','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3310, current_timestamp, 'CREATE SQL', to_date('2016 04 03', 'yyyy MM dd'), 'Teszt','Spring','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3311, current_timestamp, 'CREATE SQL', to_date('2016 04 24', 'yyyy MM dd'), 'Teszt','Security','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3312, current_timestamp, 'CREATE SQL', to_date('2016 04 30', 'yyyy MM dd'), 'Teszt','JPA','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3313, current_timestamp, 'CREATE SQL', to_date('2016 04 27', 'yyyy MM dd'), 'Teszt','JEE Alapismeretek','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3314, current_timestamp, 'CREATE SQL', to_date('2016 05 20', 'yyyy MM dd'), 'Teszt','JSF','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3315, current_timestamp, 'CREATE SQL', to_date('2016 05 27', 'yyyy MM dd'), 'Teszt','EJB','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3316, current_timestamp, 'CREATE SQL', to_date('2016 04 11', 'yyyy MM dd'), 'Teszt','Webservice','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');
INSERT INTO public.event(id, recdate, recuser, date, description, name, place, type) VALUES (3317, current_timestamp, 'CREATE SQL', to_date('2016 05 14', 'yyyy MM dd'), 'Teszt','Fejlesztési módszertanok','Debrecen, Böszörményi út 68. H-4032 - Hungary','Exam');



--Homework results

--Namenyi
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5500, current_timestamp, 'CREATE SQL', 8, 3200, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5501, current_timestamp, 'CREATE SQL', 6, 3201, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5502, current_timestamp, 'CREATE SQL', 9, 3202, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5503, current_timestamp, 'CREATE SQL', 5, 3203, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5504, current_timestamp, 'CREATE SQL', 4, 3204, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5505, current_timestamp, 'CREATE SQL', 10, 3205, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5506, current_timestamp, 'CREATE SQL', 10, 3206, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5507, current_timestamp, 'CREATE SQL', 8, 3207, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5508, current_timestamp, 'CREATE SQL', 7, 3208, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5509, current_timestamp, 'CREATE SQL', 7, 3209, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5510, current_timestamp, 'CREATE SQL', 5, 3210, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5511, current_timestamp, 'CREATE SQL', 7, 3211, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5512, current_timestamp, 'CREATE SQL', 9, 3212, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5513, current_timestamp, 'CREATE SQL', 10, 3213, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5514, current_timestamp, 'CREATE SQL', 8, 3214, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5515, current_timestamp, 'CREATE SQL', 10, 3215, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5516, current_timestamp, 'CREATE SQL', 9, 3216, 2100);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5517, current_timestamp, 'CREATE SQL', 6, 3217, 2100);

--Preznyak

INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5518, current_timestamp, 'CREATE SQL', 7, 3200, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5519, current_timestamp, 'CREATE SQL', 6, 3201, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5520, current_timestamp, 'CREATE SQL', 10, 3202, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5521, current_timestamp, 'CREATE SQL', 6, 3203, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5522, current_timestamp, 'CREATE SQL', 7, 3204, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5523, current_timestamp, 'CREATE SQL', 9, 3205, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5524, current_timestamp, 'CREATE SQL', 10, 3206, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5525, current_timestamp, 'CREATE SQL', 9, 3207, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5526, current_timestamp, 'CREATE SQL', 7, 3208, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5527, current_timestamp, 'CREATE SQL', 6, 3209, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5528, current_timestamp, 'CREATE SQL', 10, 3210, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5529, current_timestamp, 'CREATE SQL', 5, 3211, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5530, current_timestamp, 'CREATE SQL', 7, 3212, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5531, current_timestamp, 'CREATE SQL', 9, 3213, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5532, current_timestamp, 'CREATE SQL', 8, 3214, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5533, current_timestamp, 'CREATE SQL', 10, 3215, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5534, current_timestamp, 'CREATE SQL', 11, 3216, 2101);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5535, current_timestamp, 'CREATE SQL', 6, 3217, 2101);

--Bohan
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5536, current_timestamp, 'CREATE SQL', 6, 3200, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5537, current_timestamp, 'CREATE SQL', 10, 3201, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5538, current_timestamp, 'CREATE SQL', 10, 3202, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5539, current_timestamp, 'CREATE SQL', 8, 3203, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5540, current_timestamp, 'CREATE SQL', 7, 3204, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5541, current_timestamp, 'CREATE SQL', 8, 3205, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5542, current_timestamp, 'CREATE SQL', 10, 3206, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5543, current_timestamp, 'CREATE SQL', 9, 3207, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5544, current_timestamp, 'CREATE SQL', 8, 3208, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5545, current_timestamp, 'CREATE SQL', 5, 3209, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5546, current_timestamp, 'CREATE SQL', 9, 3210, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5547, current_timestamp, 'CREATE SQL', 10, 3211, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5548, current_timestamp, 'CREATE SQL', 10, 3212, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5549, current_timestamp, 'CREATE SQL', 7, 3213, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5550, current_timestamp, 'CREATE SQL', 8, 3214, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5551, current_timestamp, 'CREATE SQL', 10, 3215, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5552, current_timestamp, 'CREATE SQL', 4, 3216, 2102);
INSERT INTO public.homework_result(id, recdate, recuser, score, homework_id, user_id) VALUES (5553, current_timestamp, 'CREATE SQL', 5, 3217, 2102);


--User to training
INSERT INTO public.user_to_training(training_id, user_id) VALUES (7000, 2100);
INSERT INTO public.user_to_training(training_id, user_id) VALUES (7000, 2101);
INSERT INTO public.user_to_training(training_id, user_id) VALUES (7000, 2102);

--Test Option for Text-based Question
-- INSERT INTO public.option(id, correct, question_id) VALUES (2012, false, 2011);

ALTER SEQUENCE hibernate_sequence RESTART WITH 10000;
