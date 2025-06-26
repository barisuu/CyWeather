import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useNavigate } from 'react-router-dom';

type Props = {
    cityCurrentWeather: CityCurrentWeather[]
}

export default function CityTable({cityCurrentWeather}: Props) {
    const navigate = useNavigate();
    return (
        <TableContainer component={Paper} sx={{borderRadius:2, boxShadow:"10px 10px 10px rgba(0,0,0,0.5)"}}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow sx={{backgroundColor: 'grey.300'}}>
                        <TableCell>City</TableCell>
                        <TableCell align="right">Condition</TableCell>
                        <TableCell align="right">Temperature</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {cityCurrentWeather.map((row) => (
                        <TableRow
                            key={row.city.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } ,
                                backgroundColor: "whitesmoke",
                                '&:hover': {
                                    backgroundColor: 'grey.200',
                                    cursor: 'pointer',
                                },
                            }}
                            onClick={() => navigate(`/city/${row.city.id}`)}
                        >
                            <TableCell component="th" scope="row">
                                {row.city.name}
                            </TableCell>
                            <TableCell align="right">{row.current.condition.text}</TableCell>
                            <TableCell align="right">{row.current.temp_c}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
