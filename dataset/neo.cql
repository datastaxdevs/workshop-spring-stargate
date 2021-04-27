CREATE TABLE IF NOT EXISTS stargate.near_earth_object(
  orbit_class    text,
  designation    text,
  discovery_date date,
  h_mag          double,
  moid_au        double,
  q_au_1         double,
  q_au_2         double,
  period_yr      double,
  i_deg          double,
  pha            text,
  PRIMARY KEY((orbit_class), designation)
);
