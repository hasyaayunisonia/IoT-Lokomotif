import React from "react";
import { Card as AntCard } from "antd";
const { Meta } = AntCard;

const Card = ({ title, value, style }) => {
	return (
		<AntCard
			style={{
				backgroundColor: style,
			}}>
			<Meta title={title} />
			<br></br>
			<div
				style={{
					textAlign: "right",
					fontSize: "22px",
					fontWeight: "bold",
				}}>
				{value}
			</div>
		</AntCard>
	);
};

export default Card;
