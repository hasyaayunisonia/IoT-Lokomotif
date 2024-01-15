import React from "react";
import { Table as AntTable } from "antd";

const Table = ({ data }) => {
	const columns = [
		{
			title: "No",
			dataIndex: "index",
			key: "index",
		},
		{
			title: "Timestamps",
			dataIndex: "timestamps",
			key: "timestamps",
			align: "center",
			render: (text) => {
				const dateObject = new Date(text);
				const monthName = dateObject.toLocaleString("default", {
					month: "long",
				});
				const formattedDateTime = `${dateObject.getDate()} ${monthName} ${dateObject.getFullYear()}, ${dateObject.toLocaleTimeString()}`;
				return <div style={{ textAlign: "left" }}>{formattedDateTime}</div>;
			},
		},
		{
			title: "Total Aktif",
			dataIndex: "totalAktif",
			key: "totalAktif",
			align: "center",
		},
		{
			title: "Total Nonaktif",
			dataIndex: "totalNonaktif",
			key: "totalNonaktif",
			align: "center",
		},
		{
			title: "Total Maintenance",
			dataIndex: "totalMaintenance",
			key: "totalMaintenance",
			align: "center",
		},
		{
			title: "Jumlah Total",
			dataIndex: "totalLoko",
			key: "totalLoko",
			align: "center",
		},
	];

	// Tambahkan nomor sebagai indeks array
	const dataWithIndex = data.map((item, index) => ({
		...item,
		index: index + 1,
	}));

	return (
		<AntTable
			dataSource={dataWithIndex}
			columns={columns}
			pagination={{
				pageSize: 5, // Jumlah data per halaman
			}}
		/>
	);
};

export default Table;
