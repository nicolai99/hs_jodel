/*City*/
INSERT into
    city (id, name, latitude, longitude)
values (
        1,
        'Esslingen',
        48.738406,
        9.30811
    );

INSERT into
    city (id, name, latitude, longitude)
values (2, 'Marbach', 48.9396, 9.2646);

ALTER SEQUENCE city_id_seq RESTART WITH 3;

/*User*/
INSERT into
    user_account (id, name)
values (
        '60a6cae6-5fb4-4d50-9d16-ced3b3cc82cf',
        'nicolai'
    );

INSERT into
    user_account (id, name)
values (
        '6b6dc989-8ade-493a-a459-eddc42fd4671',
        'linus'
    );

/*Jodel*/
INSERT into
    jodel (
        id,
        f_city,
        f_user,
        text,
        timestemp
    )
values (
        1,
        1,
        '60a6cae6-5fb4-4d50-9d16-ced3b3cc82cf',
        'Das ist das erste Jodel von nicolai, cool oder?',
        '2024-05-23 15:30:45'
    );

INSERT into
    jodel (
        id,
        f_city,
        f_user,
        text,
        timestemp
    )
values (
        2,
        1,
        '6b6dc989-8ade-493a-a459-eddc42fd4671',
        'Das ist das erste Jodel von linus, cool oder?',
        '2024-05-23 15:30:45'
    );

ALTER SEQUENCE jodel_id_seq RESTART WITH 3;

/*Comments*/
INSERT into
    comment (
        id,
        f_jodel,
        f_user,
        text,
        timestemp
    )
values (
        1,
        1,
        '60a6cae6-5fb4-4d50-9d16-ced3b3cc82cf',
        'Das ist ein Kommentar zum ersten Jodel',
        '2024-05-23 15:30:45'
    );

/*Vote for jodel*/
-- INSERT into vote (id,f_jodel,f_user,direction) values (1,1,'UserId1',1);
-- INSERT into vote (id,f_jodel,f_user,direction) values (2,1,'UserId1',-1);

/*SELECT jodel.text AS JodelText, comment.text  AS KommentarText FROM jodel INNER JOIN comment ON jodel.id=comment.f_jodel;*/
/*SELECT jodel.text , SUM(vote.direction) AS SummeVouts  FROM jodel INNER JOIN vote ON vote.f_jodel=jodel.id  GROUP BY jodel.text;*/