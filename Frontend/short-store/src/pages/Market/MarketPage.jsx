import React from "react";
import styles from "./MarketPage.module.css";
import Slider from "../../components/Banner.js";
import { DataGrid } from '@mui/x-data-grid';
import Divider from '@mui/material/Divider';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

const bull = (
  <Box
    component="span"
    sx={{ display: 'inline-block', mx: '2px', transform: 'scale(0.8)' }}
  >
    •
  </Box>
);

const columns = [
  { field: 'id', headerName: '번호', width: 70 },
  { field: 'Name', headerName: '부스 이름', width: 300 },
  { field: 'Subject', headerName: '부스 품목', width: 300 },
];

const rows = [
  { id: 1, Subject: 'Snow', Name: 'Jon', age: 35 },
  { id: 2, Subject: 'Lannister', Name: 'Cersei', age: 42 },
  { id: 3, Subject: 'Lannister', Name: 'Jaime', age: 45 },
  { id: 4, Subject: 'Stark', Name: 'Arya', age: 16 },
  { id: 5, Subject: 'Targaryen', Name: 'Daenerys', age: null },
  { id: 6, Subject: 'Melisandre', Name: null, age: 150 },
  { id: 7, Subject: 'Clifford', Name: 'Ferrara', age: 44 },
  { id: 8, Subject: 'Frances', Name: 'Rossini', age: 36 },
  { id: 9, Subject: 'Roxie', Name: 'Harvey', age: 65 },
];

function MarketPage() {
  return (
    <div className="area-3">
      <h1>마켓상세페이지</h1>
      <div className={styles.column_wrap}>
        <div className={styles.column_is_fixed}>
          <h1>마켓 사진들</h1>
          <Slider />
        </div>
        <div className={styles.column}>
          <h1>부스 정보</h1>
          <div style={{ height: 400, width: '95%' }}>
            <DataGrid
              rows={rows}
              columns={columns}
              pageSize={5}
              rowsPerPageOptions={[5]}
              checkboxSelection
            />
            <div>
              <Card sx={{ minWidth: 275 }}>
                <CardContent>
                  <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                    Word of the Day
                  </Typography>
                  <Typography variant="h5" component="div">
                    be{bull}nev{bull}o{bull}lent
                  </Typography>
                  <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    adjective
                  </Typography>
                  <Typography variant="body2">
                    well meaning and kindly.
                    <br />
                    {'"a benevolent smile"'}
                  </Typography>
                </CardContent>
                <CardActions>
                  <Button size="small">Learn More</Button>
                </CardActions>
              </Card>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.map_area} >
        <h1>마켓 지도 팜플렛</h1>

      </div>
      <div className={styles.others_area}>
        <h1>ㅇㅇ 지역의 다른 마켓 둘러보기</h1>
      </div>
    </div>
  );
}

export default MarketPage;

