CREATE TABLE IF NOT EXISTS stargate.chevrons(
   area text,
   code int ,
   name text,
   picture text,
   PRIMARY KEY ((area), code)
) WITH CLUSTERING ORDER BY (code ASC);

INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 1, 'Earth', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/001glyph-earth.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 2, 'Crater', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/002glyph-crater.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 3, 'Virgo', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/003glyph-virgo.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 4, 'Bootes', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/004glyph-bootes.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 5, 'Centaurus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/005glyph-centarus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 6, 'Libra', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/006glyph-libra.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 7, 'Serpenscaput', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/007glyph-serpenscaput.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 8, 'Norma', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/008glyph-norma.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 9, 'Scorpio', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/009glyph-scorpio.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 10, 'Cra', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/010glyph-cra.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 11, 'Scutum', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/011glyph-scutum.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 12, 'Sagitarus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/012glyph-sagittarius.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 13, 'Aquila', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/013glyph-aquila.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 14, 'Mic', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/014glyph-mic.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 15, 'Capricorn', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/015glyph-capricorn.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 16, 'Piscesaustrinus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/016glyph-piscesaustrinus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 17, 'Equuleus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/017glyph-equuleus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 18, 'Aquarius', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/018glyph-aquarius.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 19, 'Pegasus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/019glyph-pegasus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 20, 'Sculptor', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/020glyph-sculptor.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 21, 'Pisces', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/021glyph-pisces.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 22, 'Andromeda', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/022glyph-andromeda.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 23, 'Triangulum', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/023glyph-triangulum.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 24, 'Aries', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/024glyph-aries.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 25, 'Perseus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/025glyph-perseus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 26, 'Cetus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/026glyph-cetus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 27, 'Taurus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/027glyph-taurus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 28, 'Auriga', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/028glyph-auriga.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 29, 'Eridanus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/029glyph-eridanus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 30, 'Orion', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/030glyph-orion.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 31, 'Canisminor', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/031glyph-canisminor.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 32, 'Monoceros', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/032glyph-monoceros.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 33, 'Gemini', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/033glyph-gemini.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 34, 'Hydra', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/034glyph-hydra.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 35, 'Lynx', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/035glyph-lynx.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 36, 'Cancer', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/036glyph-cancer.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 37, 'Sextans', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/037glyph-sextans.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 38, 'Leominor', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/038glyph-leominor.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 39, 'Leo', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/039glyph-leo.jpg?raw=true');


CREATE TABLE IF NOT EXISTS stargate.destinations (
   name text,
   chevron1 int,
   chevron2 int,
   chevron3 int,
   chevron4 int,
   chevron5 int,
   chevron6 int,
   chevron_area int,
   type text,
   description,
   PRIMARY KEY ((name), chevron1, chevron2, chevron3, chevron4, chevron5, chevron6)
) WITH CLUSTERING ORDER BY (chevron1 ASC, chevron2 ASC, chevron3 ASC, chevron4 ASC, chevron5 ASC, chevron6 ASC);



INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('Abydos', 27,7,15,32,12,30,'Planet','Home of Daniels family by marriage') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('Chulak', 9,2,23,16,37,20, 'Planet','Home base of Apophis and his Jaffa') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3A-575', 4,7,12,29,11,31,'Planet','First planet SG-1 explores with Tealc as member of the unit') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3A-577', 27,7,15,32,12,30,'Planet','Planet scheduled for SG-2s first solo mission.') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('Simarka', 27,7,15,32,12,29,'Planet','') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3X-595', 27,7,15,32,12,28,'Planet','') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3X-797', 27,7,15,32,12,27,'Planet','Land of light') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3X-513', 27,7,15,32,12,26,'Planet','') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3X-562', 27,7,15,32,12,26,'Planet','') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3X-513', 27,7,15,32,12,26,'Planet','') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3C-117', 27,7,15,32,12,26,'Planet','') ;
INSERT INTO destinations (name,chevron1,chevron2,chevron3,chevron4,chevron5,chevron6,type,description) VALUES('P3X-774', 27,7,15,32,12,26,'Planet','') ;


