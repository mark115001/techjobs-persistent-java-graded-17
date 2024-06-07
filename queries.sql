--Part 1
-- Columns
-- id int PK
-- employer varchar(255)
-- name varchar(255)
-- skills varchar(255)

--Part 2
--SELECT name from employer where location="St. Louis City"
SELECT name FROM employer WHERE location = "St. Louis City";

--Part 3

DROP TABLE job;

--Part 4

SELECT * FROM skill *(LEFT|INNER)? JOIN job_skills ON (skill.id = job_skills.skills_id|job_skills.skills_id = skill.id)
(*WHERE job_skills.jobs_id IS NOT NULL)? *ORDER BY name ASC, (Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
