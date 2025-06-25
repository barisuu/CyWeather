import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function createData(
    name: string,
    condition: string,
    temperature: number,
) {
    return { name, condition, temperature};
}

const rows = [
    createData('Guzelyurt', "Rainy", 18),
    createData('Lefke', "Cloudy", 20),
    createData('Iskele', "Partly Cloudy", 23),
    createData('Kalkanli', "Rainy", 19),
    createData('Karpaz', "Sunny", 30),
];

export default function CityTable() {
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
                    {rows.map((row) => (
                        <TableRow
                            key={row.name}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } ,
                                backgroundColor: "whitesmoke",
                                '&:hover': {
                                    backgroundColor: 'grey.200',
                                    cursor: 'pointer',
                                },
                            }}
                        >
                            <TableCell component="th" scope="row">
                                {row.name}
                            </TableCell>
                            <TableCell align="right">{row.condition}</TableCell>
                            <TableCell align="right">{row.temperature}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
