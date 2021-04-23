CREATE TABLE IF NOT EXISTS stargate.planetary_systems_composite(
  pl_name          text,   // Planet Name
  hostname         text,   // Host Name
  sy_snum          int,    // Number of Stars
  sy_pnum          int,    // Number of Planets
  discoverymethod  text,   // Discovery Method
  disc_year        int,    // Discovery Year
  disc_facility    text,   // Discovery Facility
PRIMARY KEY((pl_name))
);
