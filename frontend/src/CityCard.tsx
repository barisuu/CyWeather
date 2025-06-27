import {
    Box,
    Card,
    CardContent,
    CardHeader,
    Divider,
    Typography
} from "@mui/material";
import {useNavigate} from "react-router-dom";

type Props = {
    city: City
    current: CurrentWeather
}

export default function CityCard({city, current}: Props) {
    const navigate = useNavigate();
    return (
        <Card
            sx={{
                height: 300,
                width: 200,
                borderRadius: 3,
                boxShadow: "5px 5px 10px rgba(0,0,0,0.5)",
                '&:hover': {
                    backgroundColor: 'grey.200',
                    cursor: 'pointer',
                    border: "2px solid blue"
                }
            }}
            onClick={() => navigate(`/city/${city.id}`)}
        >
            <CardHeader
                title={city.name}
                slotProps={{
                    title: {
                        fontWeight: "bold",
                        fontSize: 20,
                    }
                }}
                sx={{bgcolor: "#3fd0e8"}}
            />
            <Divider/>
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'center',

                }}
            >
                <img
                    src={current.condition.icon}
                    alt={current.condition.text}
                    style={{
                        width: 80,
                        height: 80,
                        objectFit: 'contain',
                    }}
                />
            </Box>

            <Divider/>

            <CardContent sx={{backgroundColor:"grey.200"}}>
                <Box display={"flex"} alignItems={"center"} mb={2} px={2}>
                    <Typography variant={"body2"} noWrap={true}>
                        Condition: {current.condition.text}
                    </Typography>
                </Box>
                <Divider/>
                <Box display={"flex"} gap={2} sx={{py: 3, pl: 2}}>
                    <Typography variant={"body2"} noWrap={true}>
                        Temperature: {current.temp_c} °C
                    </Typography>
                </Box>
            </CardContent>
        </Card>
    )
}
