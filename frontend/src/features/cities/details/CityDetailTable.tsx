import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

type Props = {
    city: City
    forecast: ForecastWeather[]
    history: ForecastWeather[]
}

export default function CityDetailTable({city, forecast, history}: Props) {
    const combinedData = [...history, ...forecast];

    combinedData.sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());

    return (
        <TableContainer component={Paper} sx={{borderRadius:2, boxShadow:"10px 10px 10px rgba(0,0,0,0.5)", width:"100%", overflowX:"auto"}}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow sx={{backgroundColor: '#3fd0e8'}}>
                        <TableCell>City</TableCell>
                        <TableCell align="right">Date</TableCell>
                        <TableCell align="right">Condition</TableCell>
                        <TableCell align="right">Max/Min Temp (°C)</TableCell>
                        <TableCell align="right">Avg Temp (°C)</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {combinedData.map((row) => (
                        <TableRow
                            key={row.date}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } ,
                                backgroundColor: "whitesmoke",
                            }}
                        >
                            <TableCell component="th" scope="row">
                                {city.name}
                            </TableCell>
                            <TableCell align="right">
                                {new Date(row.date).toLocaleDateString()}
                            </TableCell>
                            <TableCell align="right">{row.day.condition.text}</TableCell>
                            <TableCell align="right">{row.day.maxtemp_c} (°C) / {row.day.mintemp_c} (°C)</TableCell>
                            <TableCell align="right">{row.day.avgtemp_c} (°C)</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
