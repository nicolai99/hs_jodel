/*City*/
INSERT into
    city (id, name, latitude, longitude)
values (1, 'Esslingen', 48.73, 9.30);

INSERT into
    city (id, name, latitude, longitude)
values (2, 'Marbach', 48.93, 9.26);

INSERT INTO
    city (id, name, latitude, longitude)
VALUES (3, 'Stuttgart', 48.78, 9.18);

-- Ludwigsburg (48.89, 9.19)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (4, 'Ludwigsburg', 48.89, 9.19);

-- Fellbach (48.81, 9.27)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (5, 'Fellbach', 48.81, 9.27);

-- Waiblingen (48.83, 9.31)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (6, 'Waiblingen', 48.83, 9.31);

-- Kornwestheim (48.83, 9.18)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (
        7,
        'Kornwestheim',
        48.83,
        9.18
    );

INSERT INTO
    city (id, name, latitude, longitude)
VALUES (8, 'Esslingen', 48.74, 9.31);

-- Plochingen (48.72, 9.39)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (9, 'Plochingen', 48.72, 9.39);

-- Wendlingen am Neckar (48.69, 9.34)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (
        10,
        'Wendlingen am Neckar',
        48.69,
        9.34
    );

-- Kirchheim unter Teck (48.65, 9.45)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (
        11,
        'Kirchheim unter Teck',
        48.65,
        9.45
    );

-- Nürtingen (48.63, 9.32)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (12, 'Nürtingen', 48.63, 9.32);

-- Göppingen (48.70, 9.65)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (13, 'Göppingen', 48.70, 9.65);

INSERT INTO
    city (id, name, latitude, longitude)
VALUES (14, 'Berlin', 52.52, 13.41);

-- Potsdam (52.39, 13.07)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (15, 'Potsdam', 52.39, 13.07);

-- München (48.14, 11.58)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (16, 'München', 48.14, 11.58);

-- Augsburg (48.37, 10.90)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (17, 'Augsburg', 48.37, 10.90);

-- Hamburg (53.55, 9.99)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (18, 'Hamburg', 53.55, 9.99);

-- Bremen (53.08, 8.80)
INSERT INTO
    city (id, name, latitude, longitude)
VALUES (19, 'Bremen', 53.08, 8.80);

INSERT into
    city (id, name, latitude, longitude)
values (
        20,
        'Erdmannhausen',
        48.94,
        9.29
    );
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
        13,
        '6b6dc989-8ade-493a-a459-eddc42fd4671',
        'Das ist das erste Jodel von linus, cool oder?',
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
        3,
        14,
        '6b6dc989-8ade-493a-a459-eddc42fd4671',
        'Das ist das erste Jodel von linus, cool oder?',
        '2024-05-23 15:30:45'
    );

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

ALTER TABLE city ALTER COLUMN id RESTART WITH 21;

ALTER TABLE jodel ALTER COLUMN id RESTART WITH 4;

ALTER TABLE comment ALTER COLUMN id RESTART WITH 2;