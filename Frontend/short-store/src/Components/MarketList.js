import React from "react";

import {
  Grid,
  Button,
  Box,
  Stack,
  CardContent,
  CardMedia,
  Card,
  CardButton,
  CardActionArea,
  CardActions,
  Typography,
  Container,
} from "@mui/material";

import { createTheme, ThemeProvider } from "@mui/material/styles";

import MarketCard from "./MarketCard.js";
import { marketListData } from "../constants/data";

const marketList = marketListData;

const theme = createTheme();
function MarketList() {
  return (
    <ThemeProvider theme={theme}>
      <Container sx={{ py: 8 }} maxWidth="lg" style={{ paddingTop: "10px" }}>
        <Grid container spacing={3}>
          {marketList.map((market) => (
            <Grid item xs={12} sm={6} md={3}>
              <MarketCard
                key={market.id}
                id={market.id}
                img={market.img}
                url={market.url}
                name={market.name}
                location={market.location}
                time={market.time}
                period={market.period}
                like={market.like}
                view={market.view}
              />
            </Grid>
          ))}
        </Grid>
      </Container>
    </ThemeProvider>
  );
}

export default MarketList;
