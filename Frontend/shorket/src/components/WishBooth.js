import React from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Divider from '@mui/material/Divider';

function WishBooth() {

  const btnStyle2 = {
    // fontSize: "25px",
    // padding: "2rem 2rem",
    margin: "1.2rem",
    textAlign: 'left',
  }

  function createData(market, booth, date, location) {
    return { market, booth, date, location };
  }

  const rows = [
    createData('ㅇㅇㅇㅇㅇ', '코딩동아리', '2022.09.01', '아주대학교병원'),
    createData('ㅁㅁㅁㅁㅁ', '한이음동아리', 9.0, 37),
    createData('ㄴㄴㄴㄴㄴ', '모각소', 16.0, 24),
    createData('ㄹㄹㄹㄹㄹ', '사진동아리', 3.7, 67),
    createData('ㅂㅂㅂㅂㅂ', '여행동아리', 16.0, 49),
  ];

  return (
    <div>
      <h1 style={btnStyle2}>관심 부스</h1>
      <Divider />
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <TableCell>마켓</TableCell>
              <TableCell align="center">부스</TableCell>
              <TableCell align="center">기간</TableCell>
              <TableCell align="center">위치</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow
                key={row.market}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {row.market}
                </TableCell>
                <TableCell align="center">{row.booth}</TableCell>
                <TableCell align="center">{row.date}</TableCell>
                <TableCell align="center">{row.location}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default WishBooth;
