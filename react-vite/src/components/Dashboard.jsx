import React from "react";
import Navbar from "./Navbar";
import Card from "./Card";
import PieChart from "./PieChart";
import Table from "./Table";
import { Breadcrumb, Layout, Menu, theme, Row, Col } from "antd";
import { Doughnut } from "react-chartjs-2";

import { useState, useEffect } from "react";
import axios from "axios";

const { Header, Content, Footer } = Layout;

const Dashboard = () => {
	const [summaryData, setSummaryData] = useState([]);
	const [summaryLastData, setSummaryLastData] = useState(null);
	const [totalLoko, setTotalLoko] = useState(null);
	const [totalAktif, setTotalAktif] = useState(null);
	const [totalNonaktif, setTotalNonaktif] = useState(null);
	const [totalMaintenance, setTotalMaintenance] = useState(null);

	useEffect(() => {
		// Ambil data terbaru dari endpoint
		axios
			.get("http://localhost:8081/api/summary-loko/latest")
			.then((response) => {
				const data = response.data;
				setSummaryData(data);

				// Ambil data terakhir dari array
				const latestData = data[0]; // Karena data diambil dari endpoint latest, maka ambil data pertama

				setTotalLoko(latestData.totalLoko);
				setTotalAktif(latestData.totalAktif);
				setTotalNonaktif(latestData.totalNonaktif);
				setTotalMaintenance(latestData.totalMaintenance);
			})
			.catch((error) => {
				console.error("Error:", error);
			});
	}, []);

	return (
		<div>
			<Navbar />
			<Content
				style={{ padding: "20px 48px", width: "100%", marginTop: "64px" }}>
				<Row gutter={[20, 20]}>
					<Col span={6}>
						<Card
							title="Jumlah Total Lokomotif"
							value={totalLoko}
							style={"#f2787d"}
						/>
					</Col>
					<Col span={6}>
						<Card
							title="Total Status Aktif"
							value={totalAktif}
							style={"#7c9ff9"}
						/>
					</Col>
					<Col span={6}>
						<Card
							title="Total Status Nonaktif"
							value={totalNonaktif}
							style={"#7877e8"}
						/>
					</Col>
					<Col span={6}>
						<Card
							title="Total Status Maintenance"
							value={totalMaintenance}
							style={"#eea990"}
						/>
					</Col>
				</Row>
				<br></br>
				<Row gutter={[20, 20]}>
					<Col span={12}>
						<div
							style={{
								width: "100%",
								display: "flex",
								justifyContent: "center",
								alignItems: "center",
							}}>
							<PieChart
								totalAktif={totalAktif}
								totalNonaktif={totalNonaktif}
								totalMaintenance={totalMaintenance}
							/>
						</div>
					</Col>
					<Col span={12}>
						<Table data={summaryData} />
					</Col>
				</Row>
			</Content>
			<Footer style={{ textAlign: "center" }}>
				Copyright &#169; By Hasya Ayuni Sonia
			</Footer>
		</div>
	);
};

export default Dashboard;
