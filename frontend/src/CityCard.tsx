import {
    Avatar,
    Box,
    Card,
    CardContent,
    CardHeader,
    Divider,
    Typography
} from "@mui/material";

type Props = {
    cityName: string,
    condition: string
    temperature: number
}

export default function CityCard({cityName, condition, temperature}: Props) {
    return (
        <Card elevation={3}
              sx={{
                  borderRadius: 3,
                  boxShadow: "5px 5px 10px rgba(0,0,0,0.5)",
                  border: "2px solid transparent",
                  '&:hover': {
                      backgroundColor: 'grey.200',
                      cursor: 'pointer',
                      border: "2px solid blue"
                  }
              }}
        >
            <Box display="flex" alignItems={"center"} justifyContent="space-between">
                <CardHeader
                    avatar={<Avatar sx={{height: 80, width: 80}}/>}
                    title={cityName}
                    slotProps={{
                        title: {
                            fontWeight: "bold",
                            fontSize: 20,
                        }
                    }}
                />
            </Box>
            <Divider sx={{mb: 3}}/>
            <CardContent sx={{p: 0}}>
                <Box display={"flex"} alignItems={"center"} mb={2} px={2}>
                    <Box display={'flex'} alignItems={"self-end"}>
                        <Typography variant={"body2"} noWrap={true}>
                            {condition}
                        </Typography>
                    </Box>
                </Box>
                <Divider/>
                <Box display={"flex"} gap={2} sx={{backgroundColor: "grey.200", py: 3, pl: 3}}>
                    <Typography variant={"body2"} noWrap={true}>
                        {temperature} C
                    </Typography>
                </Box>
            </CardContent>
        </Card>
    )
}
