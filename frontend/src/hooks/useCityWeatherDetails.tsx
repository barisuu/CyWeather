import {useQuery} from "@tanstack/react-query";
import agent from "../agent";

export const useCityWeatherDetails= (id?: string) => {
    const {data, isLoading, error} = useQuery(
        {
            queryKey:['cityWeather',id],
            queryFn: async () => {
                const url = `/cities/${id}`;
                console.log("Requesting url:", url);
                const response = await agent.get<CityWeatherDetails>(url, {
                    params:{
                        daysInPast: 3,
                        daysInFuture:5
                    }
                });
                return response.data;
            },
            enabled: !!id
        }
    );

    return {
        data,
        isLoading,
        error
    };
};
