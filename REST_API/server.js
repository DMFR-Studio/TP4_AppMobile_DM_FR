const http = require("http");
const app = require("./app.js");

const server = http.createServer(app);

server.listen(process.env.PORT || 8081);
