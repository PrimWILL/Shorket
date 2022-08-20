import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

import {
  CardContent,
  CardMedia,
  Card,
  CardButton,
  CardActionArea,
  CardActions,
  Button,
  Typography,
} from "@mui/material";

import styles from "./Card.module.css";
import img1 from "../images/market_img.png";

function MarketCard({
  id,
  img,
  url,
  name,
  location,
  time,
  period,
  like,
  view,
}) {
  let temp = img;
  if (temp[0] === ".") temp = img1;

  return (
    <Card
      sx={{
        height: "100%",
        display: "flex",
        flexDirection: "column",
      }}
    >
      <CardActionArea
        style={{ cursor: "pointer" }}
        component={Link}
        to={`/market/${id}`}
      >
        <CardMedia
          component="img"
          sx={{
            height: "50%",
          }}
          image={temp}
          alt="market image"
          style={{ height: "260px" }}
        />
        <CardContent sx={{ flexGrow: 1 }}>
          <Typography gutterBottom variant="h5" component="h2">
            {name}
          </Typography>
          <Typography>
            <p className={styles.location}> 위치 : {location}</p>
          </Typography>
          <Typography>
            <p className={styles.location}> 운영시간 : {time}</p>
          </Typography>
          <Typography>
            <p className={styles.due}> 기간 : {period}</p>
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions className={styles.interest_figure}>
        <Button size="small">관심👍</Button> {like}
        <Button size="small">뷰 </Button> {view}
      </CardActions>
    </Card>
  );
}

MarketCard.propTypes = {
  id: PropTypes.number.isRequired,
  img: PropTypes.string.isRequired,
  url: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  location: PropTypes.string.isRequired,
  time: PropTypes.string.isRequired,
  period: PropTypes.string.isRequired,
  like: PropTypes.number.isRequired,
  view: PropTypes.number.isRequired,
};

export default MarketCard;
