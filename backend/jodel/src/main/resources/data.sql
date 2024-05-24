/*City*/
INSERT into city  (id,name,longitude,latitude) values (1,'Esslingen',48.738406,9.30811);

/*User*/
INSERT into user_account (id,name) values ('UserId1','nicolai');
INSERT into user_account (id,name) values ('UserId2','linus');

/*Jodel*/
INSERT into jodel (id,f_city,f_user,text,timestemp) values (1,1,'UserId1','Das ist das erste Jodel von nicolai, cool oder?','2024-05-23 15:30:45');
INSERT into jodel (id,f_city,f_user,text,timestemp) values (2,1,'UserId2','Das ist das erste Jodel von linus, cool oder?','2024-05-23 15:30:45');

/*Comments*/
INSERT into comment (id,f_jodel,f_user,text,timestemp) values (1,1,'UserId1','Das ist ein Kommentar zum ersten Jodel','2024-05-23 15:30:45');

/*Vote for jodel*/
-- INSERT into vote (id,f_jodel,f_user,direction) values (1,1,'UserId1',1);
-- INSERT into vote (id,f_jodel,f_user,direction) values (2,1,'UserId1',-1);

/*SELECT jodel.text AS JodelText, comment.text  AS KommentarText FROM jodel INNER JOIN comment ON jodel.id=comment.f_jodel;*/
/*SELECT jodel.text , SUM(vote.direction) AS SummeVouts  FROM jodel INNER JOIN vote ON vote.f_jodel=jodel.id  GROUP BY jodel.text;*/