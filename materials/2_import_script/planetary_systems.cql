CREATE TABLE IF NOT EXISTS stargate.planetary_systems(
  pl_name          text,   // Planet Name
  hostname         text,   // Host Name
  pl_letter        text,   // Planet Letter
  hd_name          text,   // HD ID
  hip_name         text,   // HIP ID
  tic_id           text,   // TIC ID
  gaia_id          text,   // GAIA ID
  default_flag     text,   // Default Parameter Set
  sy_snum          int,    // Number of Stars
  sy_pnum          int,    // Number of Planets
  sy_mnum          int,    // Number of Moons
  cb_flag          text,   // Circumbinary Flag
  discoverymethod  text,   // Discovery Method
  disc_year        int,    // Discovery Year
  disc_refname     text,   // Discovery Reference
  disc_pubdate     date,   // Discovery Publication Date
  disc_locale      text,   // Discovery Locale
  disc_facility    text,   // Discovery Facility
  disc_telescope   text,   // Discovery Telescope
  disc_instrument  text,   // Discovery Instrument
PRIMARY KEY((tic_id))
);
