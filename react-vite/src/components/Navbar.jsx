import React from "react";
import { Link } from "react-router-dom";
import { Layout, Menu, Typography } from "antd";

const { Header } = Layout;

const Navbar = () => {
	return (
		<Header
			style={{ position: "fixed", zIndex: 1, width: "100%", left: 0, top: 0 }}>
			<Menu
				theme="dark"
				mode="horizontal"
				defaultSelectedKeys={["1"]}>
				<Link
					to="/"
					style={{ textDecoration: "none" }}>
					<Typography
						level={1}
						style={{
							color: "white",
							lineHeight: "64px",
							fontWeight: "bold",
							fontSize: "18px",
						}}>
						Dashboard Lokomotif
					</Typography>
				</Link>
			</Menu>
		</Header>
	);
};

export default Navbar;
