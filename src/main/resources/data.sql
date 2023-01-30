INSERT INTO customers (id, dob, first_name, last_name, gender, weight, height, is_vegan, is_vegetarian)
VALUES
    (1000, '1992-11-09', 'Milord','Snow','Water bender',100,180,true, false),
    (1001, '1990-10-19', 'Test1','Apple','Mega tron',120,180,false, false),
    (1002, '1892-08-29', 'test2','Pewpew','Avatar',130,180,true, true);

insert into goal (id, description, name)
values
    (100,'Calories based on base metobolic rate','BMR-Kcal' ),
    (101,'LOW Calorie meals','Low-KCal'),
    (102, 'High kcal based bromeals','High-Kcal');

insert into diet (goal_id, description, name)
values
    (100, 'Calories based on base metobolic rate', 'BMR-Kcal' ),
    (101, 'LOW Calorie meals', 'Low-KCal'),
    (102, 'High kcal based bromeals', 'High-Kcal');


insert into recipes(id, is_vegan, is_vegetarian, name)
values
    (400, false, false, 'Chicken-Salad'),
    (401, false, true, 'Fish-Bowl'),
    (402, true, true, 'Tofu-Madness');


insert into product (id, description, name, recipe_id)
values
    (300, 'yumyum chicken protein', 'Chicken', 400 ),
    (301, 'yumyum fish protein', 'Fish', 401 ),
    (302, 'yumyum Vegan protein', 'Tofu', 402 );


insert into roles(rolename) values ('USER'), ('ADMIN');