insert into country_master values(1,"India");
insert into country_master values(2,"USA");
#------------------------------------------------------
insert into state_master(state_id,state_name,country_id) values(1,"Odisha",1);
insert into state_master(state_id,state_name,country_id) values(2,"Telangana",1);

insert into state_master(state_id,state_name,country_id) values(3,"New York",2);
insert into state_master(state_id,state_name,country_id) values(4,"Texas",2);
#----------------------------------------------------
insert into city_master(city_id,city_name,state_id) values(1,"Bhubaneswar",1);
insert into city_master(city_id,city_name,state_id) values(2,"Rourkela",1);

insert into city_master(city_id,city_name,state_id) values(3,"Hyderabad",2);
insert into city_master(city_id,city_name,state_id) values(4,"Warangal",2);

insert into city_master(city_id,city_name,state_id) values(5,"Albany",3);
insert into city_master(city_id,city_name,state_id) values(6,"Buffalo",3);

insert into city_master(city_id,city_name,state_id) values(7,"San Antonio",4);
insert into city_master(city_id,city_name,state_id) values(8,"Dallas",4);

