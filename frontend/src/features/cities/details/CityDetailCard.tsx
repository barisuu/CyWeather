import {
    Card,
    CardContent,
    CardHeader,
    Divider,
    List,
    ListItem,
    ListItemText,
    Box,
} from "@mui/material";

type Props = {
    city: City
    current: CurrentWeather
}

export default function CityDetailCard({city, current}: Props) {
    return (
        <Card sx={{height: 300,width: 400, borderRadius: 5}}>
            <CardHeader
                title={city.name}
                subheader={city.region + ", " + city.country + " | " + city.lat +" / " + city.lon}
                sx={{
                    bgcolor: "#3fd0e8",
                    '& .MuiCardHeader-title': { fontSize: '2rem', fontWeight: 'bold' },
                    '& .MuiCardHeader-subheader': { fontSize: '1.25rem' },
                }}
                action={
                    <Box
                        component="img"
                        src={current.condition.icon}
                        alt="Weather icon"
                        sx={{
                            width: 64,
                            height: 64,
                            objectFit: 'contain',
                            mr: 2,
                            mt: 1,
                        }}
                    />
                }
            />
            <Divider />
            <CardContent sx={{ height: 'calc(100%)' }}>
                <List dense sx={{ width: "100%" }}>
                    <ListItem sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                        <ListItemText
                            primary={"Condition: " + current.condition.text}
                            primaryTypographyProps={{ fontSize: '1.5rem' }}
                        />
                    </ListItem>
                    <Divider />
                    <ListItem>
                        <ListItemText
                            primary={"Temperature: " + current.temp_c + " °C"}
                            primaryTypographyProps={{ fontSize: '1.5rem' }}
                        />
                    </ListItem>
                </List>
            </CardContent>
        </Card>
    );
}
