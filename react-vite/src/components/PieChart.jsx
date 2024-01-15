import React, { useEffect, useRef } from "react";
import Chart from "chart.js/auto";
import "chartjs-plugin-datalabels";

const PieChart = ({ totalAktif, totalNonaktif, totalMaintenance }) => {
	const chartRef = useRef(null);
	const chartInstance = useRef(null);

	useEffect(() => {
		if (chartInstance.current) {
			chartInstance.current.destroy();
		}

		const myChartRef = chartRef.current.getContext("2d");

		const calculateSize = () => {
			const container = chartRef.current.parentElement;
			const containerWidth = container.clientWidth;
			const containerHeight = container.clientHeight;

			return {
				width: containerWidth,
				height: containerHeight,
			};
		};

		chartInstance.current = new Chart(myChartRef, {
			type: "pie",
			data: {
				labels: ["Status Aktif", "Status Nonaktif", "Status Maintenance"],
				datasets: [
					{
						data: [totalAktif, totalNonaktif, totalMaintenance],
						backgroundColor: ["#66ff66", "#ff6666", "#fdb137"],
					},
				],
			},
			options: {
				plugins: {
					tooltip: {
						callbacks: {
							label: (context) => {
								const labelIndex = context.dataIndex;
								const value = context.dataset.data[labelIndex];
								const total = context.dataset.data.reduce(
									(acc, val) => acc + val,
									0
								);
								const percentage = ((value / total) * 100).toFixed(2) + "%";
								return `${context.label}: ${value} (${percentage})`;
							},
						},
					},
				},
			},
		});

		return () => {
			if (chartInstance.current) {
				chartInstance.current.destroy();
			}
		};
	}, [totalAktif, totalNonaktif, totalMaintenance]);
	return (
		<div
			style={{
				width: "350px",
				height: "350px",
			}}>
			<canvas ref={chartRef} />
		</div>
	);
};

export default PieChart;
