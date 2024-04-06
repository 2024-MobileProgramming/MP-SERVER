--<User 5 insert>--
INSERT INTO User (user_id, user_name, user_nickname, user_kakao_email, user_birthday, user_birthyear, user_phone)
VALUES
    (1, 'John', 'johny', 'john@example.com', '1990-01-01', '1990', '123-456-7890'),
    (2, 'Emma', 'em', 'emma@example.com', '1995-05-15', '1995', '987-654-3210'),
    (3, 'Michael', 'mike','michael@example.com', '1988-09-30', '1988', '111-222-3333'),
    (4, 'Sophia', 'sophie','sophia@example.com', '1992-11-20', '1992', '444-555-6666'),
    (5, 'William', 'will','william@example.com', '1985-03-10', '1985', '777-888-9999');

--<Mission_5 insert>
INSERT INTO Mission (mission_name)
VALUES
    ('Mission 1'),
    ('Mission 2'),
    ('Mission 3'),
    ('Mission 4'),
    ('Mission 5');

--<Part_miss 받아오기>
INSERT INTO Part_miss (user_id, mission_id, mission_name, mission_photo)
SELECT
    u.user_id,
    m.mission_id,
    m.mission_name,
    NULL AS mission_photo
FROM
    (SELECT 1 AS user_id UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS u
CROSS JOIN
    (SELECT mission_id, mission_name FROM Mission) AS m;

--<UserCheck>
--ex) 만약에 1번 host_id = 1번 주인이, 3,4번 user에게 mission_id 3번을 checked를 받는다. 
INSERT INTO UserCheck (host_id, user_id, mission_id, checked)
VALUES
    (1, 3, 3, 1),
    (1, 4, 3, 1);
-- -> UserCheck구문

UPDATE Part_miss AS pm
JOIN (
    SELECT host_id, mission_id, SUM(checked) AS total_checked
    FROM UserCheck
    GROUP BY host_id, mission_id
) AS uc ON pm.user_id = uc.host_id AND pm.mission_id = uc.mission_id
SET pm.checked_count = uc.total_checked
-- ->checked_count Update구문.