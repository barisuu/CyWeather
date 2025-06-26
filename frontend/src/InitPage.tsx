import { Button, Box, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function InitPage() {
    const navigate = useNavigate();

    const handleInit = async () => {
        try {
            await fetch('http://localhost:8080/weather/init', { method: 'POST' }); // Adjust URL if needed
            navigate('/home');
        } catch (error) {
            console.error('Initialization failed:', error);
        }
    };

    return (
        <Box
            minHeight="100vh"
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
        >
            <Typography variant="h4" gutterBottom>
                Welcome to Cyprus Weather
            </Typography>
            <Button variant="contained" onClick={handleInit}>
                Start
            </Button>
        </Box>
    );
}
