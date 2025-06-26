import { useQuery } from "@tanstack/react-query";
import agent from "../agent";
import { defaultCityIds } from "../constants/defaultCityIds";

export const useCityCurrentWeather = (ids?: string[]) => {
    const cityIds = ids && ids.length > 0 ? ids : defaultCityIds;

    const { data, isLoading, error } = useQuery({
        queryKey: ['cityCurrentWeather', cityIds],
        queryFn: async () => {
            const response = await agent.get<CityCurrentWeather[]>(`/homepage`,{
                params: {ids : cityIds.join(",")}
            });
            return response.data;
        },
        enabled: cityIds.length>0
    });

    return { data, isLoading, error };
};
