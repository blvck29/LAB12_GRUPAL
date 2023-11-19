#Usuarios

insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Alex",16,"sol@gmail.com","Ares","dinosaurio");
insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Maria",19,"alo@gmail.com","Sdeath","solymarns");
insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Sonia",15,"son@gmail.com","Arlet","manilamia");
insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Diego",27,"di@gmail.com","Disaster","nocturno1");

#Civilizaciones
insert into civilizaciones(id_jugadores,nombre) values (1,"La mancha");
insert into civilizaciones(id_jugadores,nombre) values (2,"Narnia");
insert into civilizaciones(id_jugadores,nombre) values (3,"Zagreb");
insert into civilizaciones(id_jugadores,nombre) values (4,"Helheim");

#Personas
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (1,"O",16,20,null,160,"Mario","Granjero");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (1,"F",40,40,null,null,"Mariana","Ninguna");

insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (2,"M",70,40,20,20,"Lancelot","Soldado");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (2,"F",30,50,null,null,"Luciana","Ninguna");

insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (3,"M",60,30,10,18,"Bob","Constructor");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (3,"M",45,46,null,null,"Arturo","Ninguna");


insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (4,"F",70,40,16,20,"Mario","Constructor");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (4,"M",38,50,null,null,"Alice","Ninguna");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (4,"F",46,40,null,null,"Alonso","Ninguna");

