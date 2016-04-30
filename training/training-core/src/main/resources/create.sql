INSERT INTO public."role"( id, name, roleCode)VALUES(2001, 'User groups managing','ROLE_UGS');
INSERT INTO public."role"( id, name, roleCode)VALUES(2002, 'User managing','ROLE_USERS');
INSERT INTO public."role"( id, name, roleCode)VALUES(2003, 'Feedbacks','ROLE_FEEDBACKS');
INSERT INTO public."role"( id, name, roleCode)VALUES(2004, 'Student','ROLE_STUDENT');
INSERT INTO public."role"( id, name, roleCode)VALUES(2005, 'Instructor','ROLE_INSTRUCTOR');

INSERT INTO public.rolegroup( id, name)  VALUES (2001,'Admin Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2002,'Instructor Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2003,'Student Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2004,'Observer Role Group');
INSERT INTO public.rolegroup( id, name)  VALUES (2005,'Guest Role Group');     

INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2001, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2002, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2003, 2001);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2004, 2003);
INSERT INTO public.role_to_rolegroup(role_id, rolegroup_id) VALUES (2005, 2002);


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

INSERT INTO public.user(id, recdate, recuser, email, fullname, isactive,  password, username) VALUES (2300, current_timestamp, 'CREATE SQL', 'szentmiklosi@example.com', 'Szentmiklósi Anikó', 1,  '$2a$04$p61zGM70P7NuCMtxDquusO1rHQC.q4IMEFx6/9PStDTBUMSNlTwn6', 'szentmiklosianiko');

-- interview PL
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3100, current_timestamp, 'CREATE SQL', to_date('2016 06 19', 'yyyy MM dd'), 'Állásinterjú', 'Java EE Junior Developer',  'Neuron Software', 'Job Interview');
-- interview BM
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3101, current_timestamp, 'CREATE SQL', to_date('2016 06 22', 'yyyy MM dd'), 'Állásinterjú', 'Java EE Junior Developer',  'Survey Sampling International', 'Job Interview');

INSERT INTO user_to_event(event_id, group_id) VALUES (3100, 2101);
INSERT INTO user_to_event(event_id, group_id) VALUES (3101, 2102);

INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3200, current_timestamp, 'CREATE SQL', to_date('2016 05 19', 'yyyy MM dd'), 'Házifeladat', 'Object Oriented Design (OOD)',  'GitHUB "homework" repository', 'Homework');
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3201, current_timestamp, 'CREATE SQL', to_date('2016 05 26', 'yyyy MM dd'), 'Házifeladat', 'Spring Security',  'GitHUB "homework" repository', 'Homework');
INSERT INTO event(id, recdate, recuser, date, description, name,  place, type) VALUES (3202, current_timestamp, 'CREATE SQL', to_date('2016 06 01', 'yyyy MM dd'), 'Házifeladat', 'JSF Login Screen',  'GitHUB "homework" repository', 'Homework');

INSERT INTO group_to_event(event_id, group_id) VALUES (3200, 2001);
INSERT INTO group_to_event(event_id, group_id) VALUES (3201, 2001);
INSERT INTO group_to_event(event_id, group_id) VALUES (3202, 2001);

INSERT INTO user_to_event(event_id, group_id) VALUES (3200, 2100);
INSERT INTO user_to_event(event_id, group_id) VALUES (3200, 2101);
INSERT INTO user_to_event(event_id, group_id) VALUES (3200, 2102);

INSERT INTO user_to_event(event_id, group_id) VALUES (3201, 2100);
INSERT INTO user_to_event(event_id, group_id) VALUES (3201, 2101);
INSERT INTO user_to_event(event_id, group_id) VALUES (3201, 2102);

INSERT INTO user_to_event(event_id, group_id) VALUES (3202, 2100);
INSERT INTO user_to_event(event_id, group_id) VALUES (3202, 2101);
INSERT INTO user_to_event(event_id, group_id) VALUES (3202, 2102);

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

INSERT INTO public.exam(id, title) VALUES(2001, 'Maven');

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

INSERT INTO public.exam(id, title) VALUES(2002, 'JPA');
INSERT INTO public.exam(id, title) VALUES(2003, 'EJB');
INSERT INTO public.exam(id, title) VALUES(2004, 'JSF');
INSERT INTO public.exam(id, title) VALUES(2005, 'Kacsa');
INSERT INTO public.exam(id, title) VALUES(2006, 'Filltest');




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
INSERT INTO public.option(id, correct, text, question_id) VALUES (2011, true, 'Option10', 2010);

INSERT INTO public.option(id, correct, question_id) VALUES (2012, false, 2011);
INSERT INTO public.option(id, correct, question_id) VALUES (2013, false, 2012);



-- Answers

INSERT INTO public.answer(id, user_id, option_id, good) VALUES (1, 2001, 2001, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (2, 2001, 2004, false);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (3, 2001, 2006, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (4, 2001, 2007, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (5, 2001, 2009, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (7, 2001, 2012, false);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (8, 2001, 2013, true);

INSERT INTO public.answer_text(id, answer_id, text) VALUES (200, 7, 'Atkaaaaaaaaaaaaaaaaaa');
INSERT INTO public.answer_text(id, answer_id, text) VALUES (201, 8, 'Filitáááááááááán');




INSERT INTO public.answer(id, user_id, option_id, good) VALUES (10, 2003, 2001, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (11, 2003, 2004, false);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (12, 2003, 2006, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (13, 2003, 2007, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (14, 2003, 2009, true);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (15, 2003, 2012, false);
INSERT INTO public.answer(id, user_id, option_id, good) VALUES (16, 2003, 2013, true);

INSERT INTO public.answer_text(id, answer_id, text) VALUES (202, 15, 'Atkaaaaaaaaaaaaaaaaaa');
INSERT INTO public.answer_text(id, answer_id, text) VALUES (203, 16, 'Filitáááááááááán');


-- Exam - User - Relation
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(100, 2005, 2001)
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(101, 2006, 2001)
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(102, 2006, 2002)
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(103, 2005, 2003)
INSERT INTO public.exam_user_relation(id, exam_id, user_id) VALUES(104, 2006, 2003)

--Test Option for Text-based Question
-- INSERT INTO public.option(id, correct, question_id) VALUES (2012, false, 2011);

ALTER SEQUENCE hibernate_sequence RESTART WITH 10000;
