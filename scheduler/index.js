const express = require("express");
const bodyParser = require("body-parser");
const { sendMessageToKafka } = require("./kafka-producer"); // Sesuaikan path file jika berbeda
const { saveDataToMongoDB } = require("./mongodb"); // Sesuaikan path file jika berbeda
const { runConsumer } = require("./kafka-consumer");

const app = express();
const port = 8080;

app.use(bodyParser.json());

// Endpoint untuk menerima data dari Java Spring Boot
app.post("/api/receive-data", (req, res) => {
	const receivedData = req.body;
	// Kirim data ke Kafka
	sendMessageToKafka("loko-data", receivedData);
	res.send("Data received and sent to Kafka");
	// saveDataToMongoDB(JSON.parse(receivedData.value.toString()););
});

app.listen(port, () => {
	console.log(`Server is running on port ${port}`);
});

runConsumer();
