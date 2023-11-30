const express = require("express");
const app = express();
app.use(express.json());

const conn = require("./connexion");

//Client
app.post("/addClient", (req, res) => {
  let nom = req.body.nom;
  let courriel = req.body.courriel;
  let mot_de_passe = req.body.mot_de_passe;
  let adresse = req.body.adresse;
  let telephone = req.body.telephone;
  let points = 0;

  conn.query(
    "INSERT INTO client(nom, courriel, mot_de_passe, adresse, telephone, points) VALUES(?,?,?,?,?,?);",
    [nom, courriel, mot_de_passe, adresse, telephone, points],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.post("/logIn", (req, res) => {
  const { courriel, mot_de_passe } = req.body;
  conn.query(
    "SELECT * from client WHERE courriel = ? AND mot_de_passe = ?",
    [courriel, mot_de_passe],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.get("/getClient", (req, res) => {
  const id = req.body.id;
  conn.query("SELECT * from client WHERE id = ?", [id], (err, rows, fields) => {
    !err ? res.send(rows) : res.sendStatus(400);
  });
});

app.post("/addPoint", (req, res) => {
  const { pointsAjout, id } = req.body;
  conn.query(
    "UPDATE client SET points = points + ? WHERE id = ?",
    [pointsAjout, id],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.get("/getPoints", (req, res) => {
  const id = req.body.id;
  conn.query(
    "SELECT points FROM client WHERE id = ?",
    [id],
    (err, rows, fields) => {
      !err ? res.send(rows) : res.sendStatus(400);
    }
  );
});

//Commande
app.post("/addCommande", (req, res) => {
  const { montant, adresse, date, client_id } = req.body;
  conn.query(
    "INSERT INTO commande (numero_commande, montant, adresse, date_commande, client_id) SELECT COALESCE(MAX(numero_commande), 0) + 1,?,?,?,? FROM commande;",
    [montant, adresse, date, client_id],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.get("/getCommandes", (req, res) => {
  const client_id = req.body.client_id;
  conn.query(
    "SELECT * from commande WHERE client_id = ?",
    [client_id],
    (err, rows, fields) => {
      !err ? res.send(rows) : res.sendStatus(400);
    }
  );
});

//Pizza
app.post("/addPizza", (req, res) => {
  const { sorte, type, prix } = req.body;
  conn.query(
    "INSERT INTO pizza(sorte, type, prix) VALUES(?,?,?);",
    [sorte, type, prix],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.get("/getPizza", (req, res) => {
  const id = req.body.id;
  conn.query("SELECT * from pizza WHERE id = ?", [id], (err, rows, fields) => {
    !err ? res.send(rows) : res.sendStatus(400);
  });
});

module.exports = app;
