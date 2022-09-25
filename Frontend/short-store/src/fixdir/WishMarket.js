import React from "react";
import Divider from '@mui/material/Divider';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
// import marketListData from "constants/data.js";

function WishMarket() {

  const btnStyle2 = {
    // fontSize: "25px",
    // padding: "2rem 2rem",
    margin: "1.2rem",
    textAlign: 'left',
  }

  const itemData = [
    {
      img: 'https://www.ikea.com/images/43/0c/430c2e84bb4ccdfffdce161ef9f271ec.jpg?f=xxxl',
      title: 'IKEA 플리마켓',
      period: '22.08.06.(토) ~ 22.08.07.(일)',
    },
    {
      img: "https://busandabom.net/images/main/top_banner.jpg",
      title: "문화예술 플리마켓 <부기상회>",
      period: "22.06.04.(토) ~ 22.08.24.(토)",

    },
    {
      img: 'https://images.unsplash.com/photo-1522770179533-24471fcdba45',
      title: 'Camera',
      period: '@helloimnik',
    },
    {
      img: 'https://images.unsplash.com/photo-1444418776041-9c7e33cc5a9c',
      title: 'Coffee',
      period: '@nolanissac',
    },
    {
      img: 'https://images.unsplash.com/photo-1533827432537-70133748f5c8',
      title: 'Hats',
      period: '@hjrc33',
    },
    {
      img: 'https://images.unsplash.com/photo-1558642452-9d2a7deb7f62',
      title: 'Honey',
      period: '@arwinneil',
    },
    {
      img: 'https://images.unsplash.com/photo-1516802273409-68526ee1bdd6',
      title: 'Basketball',
      period: '@tjdragotta',
    },
    {
      img: 'https://images.unsplash.com/photo-1518756131217-31eb79b20e8f',
      title: 'Fern',
      period: '@katie_wasserman',
    },
    {
      img: 'https://images.unsplash.com/photo-1597645587822-e99fa5d45d25',
      title: 'Mushrooms',
      period: '@silverdalex',
    },
    {
      img: 'https://images.unsplash.com/photo-1567306301408-9b74779a11af',
      title: 'Tomato basil',
      period: '@shelleypauls',
    },
    {
      img: 'https://images.unsplash.com/photo-1471357674240-e1a485acb3e1',
      title: 'Sea star',
      period: '@peterlaster',
    },
    {
      img: 'https://images.unsplash.com/photo-1589118949245-7d38baf380d6',
      title: 'Bike',
      period: '@southside_customs',
    },
  ];

  return (
    <div>
      <h1 style={btnStyle2}>관심마켓</h1>
      <Divider />
      <ImageList sx={{ width: 700, height: 450 }}>
        {itemData.map((item) => (
          <ImageListItem key={item.img}>
            <img
              src={`${item.img}?w=248&fit=crop&auto=format`}
              srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
              alt={item.title}
              loading="lazy"
            />
            <ImageListItemBar
              title={item.title}
              subtitle={<span>{item.period}</span>}
              position="below"
            />
          </ImageListItem>
        ))}
      </ImageList>
    </div>
  );
}

export default WishMarket;
