const mysql = require("mysql2");

var conn = mysql.createConnection({
  database: "defaultdb",
  host: "tp3-appweb-dm-fr-cegeplimoilou-tp3-app-mobile.a.aivencloud.com",
  user: "felix-rheaume",
  password: "AVNS_V4i-HP6nBvWzKkTp4Gu",
  port: 21479,
});

conn.connect(function (err) {
  if (err) throw err;
  console.log("Connexion a la base de donnees reussie!");
});

module.exports = conn;
