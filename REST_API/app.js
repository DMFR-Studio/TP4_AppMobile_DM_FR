const express = require("express");
const app = express();
app.use(express.json());

const conn = require("./connexion");

app.post("/addUser", (req, res) => {
  const { first_name, last_name, email, password, country } = req.body;
  conn.query(
    "INSERT INTO User(first_name, last_name, email, password, country) VALUES(?,?,?,?,?);",
    [first_name, last_name, email, password, country],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.post("/login", (req, res) => {
  const { email, password } = req.body;
  conn.query(
    "SELECT * from User WHERE email = ? AND password = ?",
    [email, password],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.get("/getUser/:id", (req, res) => {
  const id = req.params.id;
  conn.query("SELECT * from User WHERE id = ?", [id], (err, rows, fields) => {
    !err ? res.sendStatus(200) : res.sendStatus(400);
  });
});

app.post("/addScore", (req, res) => {
  const { score, date, user_id } = req.body;
  conn.query(
    "INSERT INTO Score (value, date, user_id) VALUES (?,?,?)",
    [score, date, user_id],
    (err, rows, fields) => {
      !err ? res.sendStatus(200) : res.sendStatus(400);
    }
  );
});

app.get("/getScores", (req, res) => {
  conn.query("SELECT * FROM Score", (err, rows, fields) => {
    !err ? res.status(200).send(rows) : res.sendStatus(400);
  });
});

module.exports = app;
